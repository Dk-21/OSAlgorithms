package com.savage9ishere.osalgorithms.petersonAlgo;

import java.util.Random;

/**
 *
 * @author denish
 */
public class Writer extends Thread {

     private Buffer buffer;
     protected int process;

     public Writer(Buffer buffer, int process) {
          this.buffer = buffer;
          this.process = process;
     }

     @Override
     public void run() {
          buffer.enterRegion(process);
          buffer.write(new Random().nextInt(5));
          buffer.leaveRegion(process);
     }
}

