package com.savage9ishere.osalgorithms.testAndSetLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
/**
 * FairLockTest
 */
public class TSL {

     public static void main(String[] args) {

          ExecutorService executor = Executors.newFixedThreadPool(5);
          final FL rl = new FL();
          Queue<String> lockQueue = new LinkedList<String>();
          Queue<String> outQueue = new LinkedList<String>();

          // start five threads, each one with a delay of 100ms from the previous
          int s;
          Scanner sc = new Scanner(System.in);
          s = sc.nextInt();

          for (int i = 0; i < s; i++) {
               executor.execute(new Worker("" + i, 1000, rl, lockQueue, outQueue));
               try {
                    Thread.sleep(100);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
          }

          try {
               executor.awaitTermination(7, TimeUnit.SECONDS);
          } catch (InterruptedException ie) {
               ie.printStackTrace();
          }
          executor.shutdownNow();
          for (int i = 0; i < 5; i++) {
               String outStr = outQueue.remove();
               if (outStr.compareTo("" + i) != 0) {
                    System.err.println("TEST FAILED");
                    System.exit(1);
               }
          }
          System.out.println("TEST PASSED");
          System.exit(0);
     }
}

class Worker implements Runnable {
     private String name;
     private long worktime;
     private FL lock;
     Queue<String> outQueue;

     Worker(String name, long worktime, FL lock, Queue<String> lockQueue, Queue<String> outQueue) {
          this.name = name;
          this.worktime = worktime;
          this.lock = lock;
          this.outQueue = outQueue;
     }

     @Override
     public void run() {
          System.out.printf("Object %s is trying to lock.%n", name);
          try {
               lock.lock();
          } catch (InterruptedException e) {
               e.printStackTrace();
          }

          try {
               System.out.printf("Object %s has entered its critical section.\n", name);
               System.out.printf("Object %s is performing work for %d milliseconds\n", name, worktime);
               outQueue.add(this.name);
               try {
                    Thread.sleep(worktime);
               } catch (InterruptedException ie) {
                    ie.printStackTrace();
               }
               System.out.printf("Object %s has finished working.\n", name);
          } finally {
               lock.unlock();
          }
     }
}
