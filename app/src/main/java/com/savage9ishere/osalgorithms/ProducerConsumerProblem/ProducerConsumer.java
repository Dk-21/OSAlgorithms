package com.savage9ishere.osalgorithms.ProducerConsumerProblem;

import java.util.Scanner;
import java.util.Vector;

public class ProducerConsumer{

     public static void main(String args[]) {
          Vector sharedQueue = new Vector();
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter Size:");
          int size = sc.nextInt();
          Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
          Thread consThread = new Thread(new Consumer(sharedQueue, size), "Consumer");
          prodThread.start();
          consThread.start();
     }
}