package com.savage9ishere.osalgorithms.dekkerAlgorithm;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentDekkerAlgorithmBinding;

import java.util.Random;
import java.util.Scanner;

public class DekkerAlgorithmFragment extends Fragment {

     private TextView outputTextView;
     private String finalOutputString = "";

     private int proc0iterations, proc1iterations;

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          Bundle bundle = getArguments();
          proc0iterations = bundle.getInt("proc0iterations", 1);
          proc1iterations = bundle.getInt("proc1iterations", 1);

     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment

          FragmentDekkerAlgorithmBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dekker_algorithm, container, false);

          outputTextView = binding.outputTextView;
          startProcess();

          return binding.getRoot();

     }

     private void startProcess() {
          final int N = 2;
          DK[] p = new DK[N];


          p[0] = new DK(0, proc0iterations);
          p[0].start();

          p[1] = new DK(1, proc1iterations);
          p[1].start();


          try {
               p[0].join();
               p[1].join();

          } catch (InterruptedException e) {
               e.printStackTrace();
          }

          finalOutputString += p[0].getOutputString();
          finalOutputString += p[1].getOutputString();

          outputTextView.setText(finalOutputString);
     }

     class DK extends Thread {

          private int id;
          volatile int turn = 0;
          volatile boolean [] flag = new boolean[2];
          private String outputString = "";
          private int s;

          private Random rand = new Random();

          public DK(int i, int s) {
               id = i;
               this.s = s;
          }


          public int switchP(int i) {
               if (i == 1) {
                    return 0;
               } else {
                    return 1;
               }
          }

          public void run() {

               int pID = this.id;

               for (int i = 0; i <= s; i++) {
                    flag[pID] = true;
                    while (flag[switchP(pID)]) {
                         if (turn == switchP(pID)) {
                              flag[pID] = false;
                              while (flag[switchP(pID)] == true) {};
                              flag[pID] = true;
                         }
                    }


                    //System.out.println("Object Thread " + this.id + " is starting iteration " + i);
                    outputString += "Object Thread " + this.id + " is starting iteration " + i + "\n";

                    setSleep(10);

                    //System.out.println("Object Thread " + this.id + " is done with iteration " + i);
                    outputString += "Object Thread " + this.id + " is done with iteration " + i + "\n";

                    setSleep(10);

                    //System.out.println("\n");
                    outputString += "\n";

                    turn = switchP(pID);
                    flag[pID] = false;

               }
          }

          public void setSleep(int time) {
               try {Thread.sleep(rand.nextInt(time));}
               catch (InterruptedException e) {
                    e.printStackTrace();
               }
          }

          public String getOutputString() {
               return outputString;
          }

     }
}