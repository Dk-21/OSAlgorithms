package com.savage9ishere.osalgorithms.ProducerConsumerProblem;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

     private final Vector sharedQueue;
     private final int SIZE;
     String output = "";

     public Producer(Vector sharedQueue, int size) {
          this.sharedQueue = sharedQueue;
          this.SIZE = size;
     }

     @Override
     public void run() {

          for (int i = 1; i <= SIZE; i++) {
               //System.out.println("Produced: " + i);
               output += "Produced: " + i + "\n";
               try {
                    produce(i);
               } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
               }

          }
     }

     private void produce(int i) throws InterruptedException {

          //wait if queue is full
          while (sharedQueue.size() == SIZE) {
               synchronized (sharedQueue) {
                    //System.out.println("Queue is full " + Thread.currentThread().getName()
                    //        + " is waiting , size: " + sharedQueue.size());

                    output += "Queue is full " + Thread.currentThread().getName()
                            + " is waiting , size: " + sharedQueue.size() + "\n";

                    sharedQueue.wait();
               }
          }

          //producing element and notify consumers
          synchronized (sharedQueue) {
               sharedQueue.add(i);
               sharedQueue.notifyAll();
          }
     }

     public String getOutputString() {
          return output;
     }
}