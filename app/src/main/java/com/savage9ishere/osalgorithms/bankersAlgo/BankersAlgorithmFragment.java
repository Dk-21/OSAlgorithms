package com.savage9ishere.osalgorithms.bankersAlgo;

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
import com.savage9ishere.osalgorithms.databinding.FragmentBankersAlgorithmBinding;


public class BankersAlgorithmFragment extends Fragment {

     private int[][] allocate;
     private int[][] max;
     private int[][] avail;
     private int[][] need;
     private int np;
     private int nr;

     private TextView outputTextView;
     private String output = "";

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          Bundle bundle = getArguments();
          np = bundle.getInt("processCount");
          nr = bundle.getInt("resourcesCount");
          allocate = (int[][]) bundle.getSerializable("allocationMatrix");
          max = (int[][]) bundle.getSerializable("maxMatrix");
          avail = (int[][]) bundle.getSerializable("availableMatrix");
     }

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          FragmentBankersAlgorithmBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bankers_algorithm, container, false);

          outputTextView = binding.outputTextView;

          startCalculation();

          return binding.getRoot();
     }

     private void startCalculation() {
          need = new int[np][nr];
          for(int i=0;i<np;i++){
               for(int j=0;j<nr;j++){  //calculating need matrix
                    need[i][j]=max[i][j]-allocate[i][j];
               }
          }

          boolean[] done =new boolean[np];
          int j=0;

          while(j<np){  //loop until all process allocated
               boolean allocated=false;
               for(int i=0;i<np;i++)
                    if(!done[i] && check(i)){  //trying to allocate
                         for(int k=0;k<nr;k++)
                              avail[0][k]=avail[0][k]-need[i][k]+max[i][k];
                         //System.out.println("Allocated process : "+i);
                         output += "Allocated process: "+i+"\n";
                         allocated=done[i]=true;
                         j++;
                    }
               if(!allocated) break;  //if no allocation
          }
          if(j==np) {  //if all processes are allocated
//               System.out.println("\nSafely allocated!");
               output += "\nSafely allocated!";
               outputTextView.setText(output);
          }
          else {
//               System.err.println("\nAll proceess can't be allocated safely!");
               output = "All process can't be allocated safely!";
               outputTextView.setText(output);
          }
     }

     private boolean check(int i) {
          for(int j=0;j<nr;j++) {
               if(avail[0][j]<need[i][j]) {
                    return false;
               }
          }
          return true;
     }
}