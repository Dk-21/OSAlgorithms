package com.savage9ishere.osalgorithms.petersonAlgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentPetersonAlgoBinding;

import java.util.Objects;
import java.util.Scanner;


public class PetersonAlgoFragment extends Fragment {

     private TextView outputTextView;
     private int id1, id2;

     private String output = "";
     private PetersonAlgoViewModel viewModel;

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          Bundle bundle = getArguments();
          assert bundle != null;
          id1 = Integer.parseInt(Objects.requireNonNull(bundle.getString("id1")));
          id2 = Integer.parseInt(Objects.requireNonNull(bundle.getString("id2")));

          viewModel = new ViewModelProvider(this).get(PetersonAlgoViewModel.class);
     }

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          FragmentPetersonAlgoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_peterson_algo, container, false);

          outputTextView = binding.outputTextView;

          startProcess();

          viewModel.getOutputTextLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
               @Override
               public void onChanged(String s) {
                    output += s;
                    outputTextView.setText(output);
               }
          });

          return binding.getRoot();
     }

     private void startProcess() {
          Buffer buffer = new Buffer();

          Writer writer = new Writer(buffer, id1);
          Reader reader = new Reader(buffer, id2);

          writer.start();
          reader.start();

          try {
               writer.join();
               reader.join();
          } catch (InterruptedException e) {
               e.printStackTrace();
          }

          viewModel.updateOutputText(buffer.getOutput());
     }
}