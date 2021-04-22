package com.savage9ishere.osalgorithms.ProducerConsumerProblem;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

     private final Vector sharedQueue;
     private final int SIZE;
     String output = "";

     public Consumer(Vector sharedQueue, int size) {
          this.sharedQueue = sharedQueue;
          this.SIZE = size;
     }

     @Override
     public void run() {
          for (int i=0; i<SIZE; i++){
               try {
                    //System.out.println("Consumed: " + consume());
                    output += "Consumed: " + consume() + "\n";
                    Thread.sleep(100);
               } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
               }

          }
     }

     private int consume() throws InterruptedException {
          //wait if queue is empty
          while (sharedQueue.isEmpty()) {
               synchronized (sharedQueue) {
                    //System.out.println("Queue is empty " + Thread.currentThread().getName()
                    //        + " is waiting , size: " + sharedQueue.size());

                    output += "Queue is empty " + Thread.currentThread().getName()
                            + " is waiting , size: " + sharedQueue.size() + "\n";

                    sharedQueue.wait();
               }
          }

          //Otherwise consume element and notify waiting producer
          synchronized (sharedQueue) {
               sharedQueue.notifyAll();
               return (Integer) sharedQueue.remove(0);
          }
     }

     public String getOutputString() {
          return output;
     }
}