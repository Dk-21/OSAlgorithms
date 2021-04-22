package com.savage9ishere.osalgorithms.semaphore;

import java.util.concurrent.Semaphore;

class Shared
{
     static int count = 0;
}

class MyThread extends Thread
{
     String output = "";
     Semaphore sem;
     String threadName;
     int n;
     public MyThread(Semaphore sem, String threadName, int n)
     {
          super(threadName);
          this.sem = sem;
          this.threadName = threadName;
          this.n = n;
     }


     @Override
     public void run() {
          //Scanner sc = new Scanner(System.in);
          // run by thread A
          // String s =sc.nextLine();

          if(this.getName().equals("A"))
          {

//               System.out.println("Starting " +  threadName);
               output += "\nStarting " +  threadName + "\n";

               try
               {
                    // First, get a permit.
                    //System.out.println(threadName + " is waiting for a permit.");
                    output += threadName + " is waiting for a permit." + "\n";

                    // acquiring the lock
                    sem.acquire();

                    //System.out.println(threadName + " gets a permit.");
                    output += threadName + " gets a permit." + "\n";

                    // Now, accessing the shared resource.
                    // other waiting threads will wait, until this
                    // thread release the lock

                    for(int i=0; i < n; i++)
                    {
                         Shared.count++;
                         //System.out.println(threadName + ": " + Shared.count);
                         output += threadName + ": " + Shared.count + "\n";

                         // Now, allowing a context switch -- if possible.
                         // for thread B to execute
                         Thread.sleep(100);
                    }
               } catch (InterruptedException exc) {
                    //System.out.println(exc);
                    output += exc + "\n";
               }

               // Release the permit.
//               System.out.println(threadName + " releases the permit.");
          }

          // run by thread B
          else
          {
//               System.out.println("Starting " + threadName);
               output += "Starting " + threadName + "\n";
               try
               {
                    // First, get a permit.
//                    System.out.println(threadName + " is waiting for a permit.");
                    output += threadName + " is waiting for a permit.\n";

                    // acquiring the lock
                    sem.acquire();

//                    System.out.println(threadName + " gets a permit.");
                    output += threadName + " gets a permit.\n";

                    // Now, accessing the shared resource.
                    // other waiting threads will wait, until this
                    // thread release the lock
                    for(int i=0; i < n; i++)
                    {
                         Shared.count--;
//                         System.out.println(threadName + ": " + Shared.count);
                         output += threadName + ": " + Shared.count + "\n";

                         // Now, allowing a context switch -- if possible.
                         // for thread A to execute
                         Thread.sleep(100);
                    }
               } catch (InterruptedException exc) {
//                    System.out.println(exc);
                    output += exc + "\n";
               }
               // Release the permit.
//               System.out.println(threadName + " releases the permit.");
          }
          output += threadName + " releases the permit.\n";
          sem.release();
     }

     public String getOutputString() {
          return output;
     }
}