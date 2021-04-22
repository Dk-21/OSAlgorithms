package com.savage9ishere.osalgorithms.testAndSetLock;

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
import com.savage9ishere.osalgorithms.databinding.FragmentParamsForTestAndSetLockBinding;


public class ParamsForTestAndSetLock extends Fragment {

     private NumberPicker processCount;

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment

          FragmentParamsForTestAndSetLockBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_params_for_test_and_set_lock, container, false);

          processCount = binding.numberPicker;
          processCount.setMaxValue(10);
          processCount.setMinValue(1);

          Button proceedButton = binding.proceedButton;
          proceedButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    int threadCount = processCount.getValue();
                    Bundle bundle = new Bundle();
                    bundle.putInt("threadCount", threadCount);
                    Navigation.findNavController(view).navigate(R.id.action_paramsForTestAndSetLock_to_testAndSetLockAlgorithmFragment, bundle);
               }
          });

          return binding.getRoot();
     }
}