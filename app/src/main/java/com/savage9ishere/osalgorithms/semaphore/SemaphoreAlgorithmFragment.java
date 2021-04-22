package com.savage9ishere.osalgorithms.semaphore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentSemaphoreAlgorithmBinding;

import java.util.concurrent.Semaphore;

public class SemaphoreAlgorithmFragment extends Fragment {

     private String process1, process2;
     private int count1, count2;

     private TextView outputTextView;
     private String output = "";

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          Bundle bundle = getArguments();
          process1 = bundle.getString("process1");
          process2 = bundle.getString("process2");
          count1 = bundle.getInt("p1CountValue");
          count2 = bundle.getInt("p2CountValue");

     }

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          FragmentSemaphoreAlgorithmBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_semaphore_algorithm, container, false);

          outputTextView = binding.outputTextView;

          startCalculation();

          return binding.getRoot();
     }

     private void startCalculation() {

          // creating a Semaphore object
          // with number of permits 1
          Semaphore sem = new Semaphore(1);

          String s = process1;
          String c = process2;
          // creating two threads with name A and B
          // Note that thread A will increment the count
          // and thread B will decrement the count
          MyThread mt1 = new MyThread(sem, s, count1);
          MyThread mt2 = new MyThread(sem, c, count2);

          // stating threads A and B
          mt1.start();
          mt2.start();

          // waiting for threads A and B
          try {
               mt1.join();
               mt2.join();

          } catch (InterruptedException e) {
               e.printStackTrace();
          }
          output+= mt1.getOutputString();
          output+= mt2.getOutputString();

          // count will always remain 0 after
          // both threads will complete their execution
          //System.out.println("count: " + Shared.count);
          output += "count: " + Shared.count;
          outputTextView.setText(output);
     }

}