package com.savage9ishere.osalgorithms.semaphore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentParamsForSemaphoreBinding;

public class ParamsForSemaphore extends Fragment {

     private EditText process1EditText, process2EditText;
     private NumberPicker p1NumberPicker, p2NumberPicker;
     private View view;

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          FragmentParamsForSemaphoreBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_params_for_semaphore, container, false);

          process1EditText = binding.process1EditText;
          process2EditText = binding.process2EditText;
          p1NumberPicker = binding.numberPickerProcess1;
          p2NumberPicker = binding.numberPickerProcess2;

          p1NumberPicker.setMinValue(0);
          p1NumberPicker.setMaxValue(10);
          p2NumberPicker.setMinValue(0);
          p2NumberPicker.setMaxValue(10);

          binding.proceedButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    int p1CountValue = p1NumberPicker.getValue();
                    int p2CountValue = p2NumberPicker.getValue();
                    String process1 = process1EditText.getText().toString();
                    String process2 = process2EditText.getText().toString();

                    if(!process1.isEmpty() && !process2.isEmpty()){
                         if((process1.equals("A") || process1.equals("B")) && (process2.equals("A") || process2.equals("B"))){
                              passValuesToNextDestination(p1CountValue, p2CountValue, process1, process2);
                         }
                         else {
                              Toast.makeText(getContext(), "process name can either be A or B", Toast.LENGTH_SHORT).show();
                         }
                    }
                    else {
                         Toast.makeText(getContext(), "Enter both process names", Toast.LENGTH_SHORT).show();
                    }
               }
          });

          view = binding.getRoot();
          return binding.getRoot();
     }

     private void passValuesToNextDestination(int p1CountValue, int p2CountValue, String process1, String process2) {
          Bundle bundle = new Bundle();
          bundle.putString("process1", process1);
          bundle.putString("process2", process2);
          bundle.putInt("p1CountValue", p1CountValue);
          bundle.putInt("p2CountValue", p2CountValue);
          Navigation.findNavController(view).navigate(R.id.action_paramsForSemaphore_to_semaphoreAlgorithmFragment, bundle);
     }
}