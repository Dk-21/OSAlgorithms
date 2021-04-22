package com.savage9ishere.osalgorithms.testAndSetLock;

/**
 *
 * @author denish
 */
import java.util.List;
import java.util.ArrayList;

/**
 * FairLock
 */
public class FL {

     private List<Node> entryQ = new ArrayList<Node>();
     private List<Node> urgentQ = new ArrayList<Node>();
     private Node owner;

     static final class Node {
          private boolean occurred = false;
          volatile Thread thread;

          Node(Thread t) {
               thread = t;
          }

          public synchronized void doWait() throws InterruptedException {
               while (!occurred) {
                    wait();
               }
               occurred = false;
          }

          public synchronized void doNotify() {
               occurred = true;
               notify();
          }

     }

     public final class Condition {

          private List<Node> blocked = new ArrayList<Node>();

          private Condition() {
          }

          private final void doWait(Node n) throws InterruptedException {
               boolean isMyTurn = false;
               while (!isMyTurn) {
                    synchronized (FL.this) {
                         synchronized (this) {
                              isMyTurn = (owner == n) && (blocked.get(0) == n);
                              if (isMyTurn) {
                                   blocked.remove(0);
                                   return;
                              }
                         }
                    }
                    try {
                         n.doWait();
                    } catch (InterruptedException e) {
                         synchronized (this) {
                              blocked.remove(n);
                         }
                         throw e;
                    }
               }
          }

          public final void await() throws InterruptedException {
               Node n = null;
               synchronized (FL.this) {
                    synchronized (this) {
                         blocked.add(owner);
                         n = owner;
                    }
               }
               unlock();
               doWait(n);
          }

          public final void signal() throws InterruptedException {
               Node signaled = null;
               Node urgent = null;
               synchronized (FL.this) {
                    synchronized (this) {
                         if (blocked.size() > 0) {
                              urgentQ.add(owner);
                              urgent = owner;
                              signaled = blocked.get(0);
                              owner = signaled;
                         }
                    }
               }
               if (signaled != null) {
                    signaled.doNotify();
                    monitorWait(urgentQ, urgent);
               }
          }

     }

     public final Condition newCondition() {
          return new Condition();
     }

     private final void monitorWait(List<Node> queue, Node n) throws InterruptedException {
          boolean isMyTurn = false;

          while (!isMyTurn) {
               synchronized (FL.this) {
                    isMyTurn = (owner == null) && (queue.get(0) == n);
                    if (isMyTurn) {
                         owner = n;
                         queue.remove(0);
                         return;
                    }
               }
               try {
                    n.doWait();
               } catch (InterruptedException e) {
                    synchronized (FL.this) {
                         queue.remove(n);
                    }
                    throw e;
               }
          }
     }

     public final void lock() throws InterruptedException {
          Node n = new Node(Thread.currentThread());
          synchronized (this) {
               entryQ.add(n);
          }
          monitorWait(entryQ, n);
     }

     public final void unlock() {
          Node n = null;
          if (!isHeldByCurrentThread()) {
               throw new IllegalMonitorStateException("Calling thread has not locked this lock\n lock owned by: "
                       + owner.thread.getName() + " thread calling lock: " + Thread.currentThread().getName());
          }
          synchronized (FL.this) {
               owner = null;
               if (urgentQ.size() > 0) {
                    n = urgentQ.get(0);
               } else if (entryQ.size() > 0) {
                    n = entryQ.get(0);
               }
          }
          if (n != null)
               n.doNotify();
     }

     public final boolean isLocked() {
          return owner != null;
     }

     public final boolean isHeldByCurrentThread() {
          return (owner == null) ? false : (Thread.currentThread() == owner.thread);
     }

}