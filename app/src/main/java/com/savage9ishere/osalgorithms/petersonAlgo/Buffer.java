package com.savage9ishere.osalgorithms.petersonAlgo;

/**
 *
 * @author denish
 */
public class Buffer {

     private String output = "";

     private int x;

     private int wait_turn;
     boolean[] interest = new boolean[2];

     public Buffer() {
          x = -1;
     }

     public void read() {
//          System.out.println("Read: " + x);
          output += "Read: " + x + "\n";
     }

     public void write(int x) {
          this.x = x;
//          System.out.println("Write: " + x);
          output += "Write: " + x + "\n";
     }

     /*
      * Métodos para o controle de acesso a região crítica
      */
     @SuppressWarnings("empty-statement")
     protected void enterRegion(int process) {
          int other = 1 - process;
          interest[process] = true;

          wait_turn = process;
          while((wait_turn == process) && interest[other]) {
//               System.out.println("Process " + process + " Waiting");
               //noinspection StringConcatenationInLoop
               output += "Process " + process + " Waiting\n";
          }
//          System.out.println("Process " + process + " enter region");
          output += "Process " + process + " enter region\n";
     }

     protected void leaveRegion(int process) {
          interest[process] = false;
//          System.out.println("Process " + process + " leave region");
          output += "Process " + process + " leave region\n";
     }

     public String getOutput() {
          return output;
     }
}