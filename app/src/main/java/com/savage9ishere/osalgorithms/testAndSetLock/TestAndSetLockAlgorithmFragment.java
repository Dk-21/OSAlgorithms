package com.savage9ishere.osalgorithms.testAndSetLock;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentTestAndSetLockAlgorithmBinding;
import com.savage9ishere.osalgorithms.mainComponents.myApplication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class
TestAndSetLockAlgorithmFragment extends Fragment {
     
     private int threadCount;
     
     private TextView outputTextView;
     private String totalOutputString = "";

     private Handler handler;
     private ExecutorService executor;

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          
          Bundle bundle = getArguments();
          threadCount = bundle.getInt("threadCount", 1);

          myApplication myApplication = (com.savage9ishere.osalgorithms.mainComponents.myApplication) getActivity().getApplicationContext();
          executor = Executors.newFixedThreadPool(threadCount);
          handler = new Handler(Looper.getMainLooper());
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          
          FragmentTestAndSetLockAlgorithmBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_and_set_lock_algorithm, container, false);
          
          outputTextView = binding.outputTextView;
          
          startProcess();
          
          return binding.getRoot();
     }

     private void startProcess() {
          //ExecutorService executor = Executors.newFixedThreadPool(5);
          final FL rl = new FL();
          Queue<String> lockQueue = new LinkedList<String>();
          Queue<String> outQueue = new LinkedList<String>();

          // start five threads, each one with a delay of 100ms from the previous
          int s;
          //Scanner sc = new Scanner(System.in);
          s = threadCount;

          for (int i = 0; i < s; i++) {
               executor.execute(new Worker("" + i, 50, rl, lockQueue, outQueue));
               try {
                    Thread.sleep(10);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
          }

          try {
               executor.awaitTermination(s, TimeUnit.SECONDS);
          } catch (InterruptedException ie) {
               ie.printStackTrace();
          }
          executor.shutdownNow();
          for (int i = 0; i < s; i++) {
               String outStr = outQueue.remove();
               if (outStr.compareTo("" + i) != 0) {
                    //System.err.println("TEST FAILED");
                    totalOutputString += "TEST FAILED";
                    updateOutputTextView();
                    System.exit(1);
               }
          }
          //System.out.println("TEST PASSED");
          //totalOutputString += "TEST PASSED";
          //updateOutputTextView();
     }

     class Worker implements Runnable {
          private String name;
          private long worktime;
          private FL lock;
          Queue<String> outQueue;

          //private String output = "";

          Worker(String name, long worktime, FL lock, Queue<String> lockQueue, Queue<String> outQueue) {
               this.name = name;
               this.worktime = worktime;
               this.lock = lock;
               this.outQueue = outQueue;
          }

          @Override
          public void run() {
               //System.out.printf("Object %s is trying to lock.%n", name);
               final String output = "Object " + name + " is trying to lock.\n";
               handler.post(new Runnable() {
                    @Override
                    public void run() {
                         totalOutputString += output;
                         updateOutputTextView();
                    }
               });

               try {
                    lock.lock();
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }

               try {
                    String output2 = "";
                    //System.out.printf("Object %s has entered its critical section.\n", name);
                    //System.out.printf("Object %s is performing work for %d milliseconds\n", name, worktime);
                    output2 += "Object " + name + " has entered its critical section.\n";
                    output2 += "Object " + name + " is performing work for " + worktime + " milliseconds\n";
                    outQueue.add(this.name);
                    try {
                         Thread.sleep(worktime);
                    } catch (InterruptedException ie) {
                         ie.printStackTrace();
                    }
                    //System.out.printf("Object %s has finished working.\n", name);
                    output2 += "Object " + name + " has finished working.\n";
                    final String finalOutput = output2;
                    handler.post(new Runnable() {
                         @Override
                         public void run() {
                              totalOutputString += finalOutput;
                              updateOutputTextView();
                         }
                    });
               } finally {
                    lock.unlock();
               }
          }
     }

     private void updateOutputTextView() {
          outputTextView.setText(totalOutputString);
     }

}

