package com.savage9ishere.osalgorithms.ProducerConsumerProblem;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentParamsForProducerConsumerBinding;

public class ParamsForProducerConsumer extends Fragment {

     private NumberPicker bufferInputNumberPicker;
     private Button proceedButton;

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          FragmentParamsForProducerConsumerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_params_for_producer_consumer, container, false);

          bufferInputNumberPicker = binding.bufferSizeNumberPicker;
          bufferInputNumberPicker.setMaxValue(10);
          bufferInputNumberPicker.setMinValue(2);

          proceedButton = binding.proceedButton;

          proceedButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    int size =  bufferInputNumberPicker.getValue();
                    Bundle bundle = new Bundle();
                    bundle.putInt("size", size);
                    Navigation.findNavController(view).navigate(R.id.action_paramsForProducerConsumer_to_producerConsumerFragment, bundle);
               }
          });

          return binding.getRoot();
     }
}