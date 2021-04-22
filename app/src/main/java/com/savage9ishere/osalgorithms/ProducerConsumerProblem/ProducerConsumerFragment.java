package com.savage9ishere.osalgorithms.ProducerConsumerProblem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentProducerConsumerBinding;

import java.util.Scanner;
import java.util.Vector;

public class ProducerConsumerFragment extends Fragment {

     private TextView outputTextView;
     private int size;

     private String output = "";

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          Bundle bundle = getArguments();
          assert bundle != null;
          size = bundle.getInt("size");
     }

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment

          FragmentProducerConsumerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_producer_consumer, container, false);

          outputTextView = binding.outputTextView;

          startProcess();

          return binding.getRoot();
     }

     private void startProcess() {
          Vector sharedQueue = new Vector();

          //Scanner sc = new Scanner(System.in);
          //System.out.println("Enter Size:");
          //int size = sc.nextInt();
          Producer prod = new Producer(sharedQueue, size);
          Consumer cons = new Consumer(sharedQueue, size);
          Thread prodThread = new Thread(prod, "Producer");
          Thread consThread = new Thread(cons, "Consumer");

          prodThread.start();
          consThread.start();

          try {
               prodThread.join();
               consThread.join();
          }
          catch (InterruptedException e){
               e.printStackTrace();
          }

          output += prod.getOutputString();
          output += cons.getOutputString();

          outputTextView.setText(output);
     }
}