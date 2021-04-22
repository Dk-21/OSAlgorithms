
package com.savage9ishere.osalgorithms.dekkerAlgorithm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentParamsForDekkerAlgorithmBinding;


public class ParamsForDekkerAlgorithmFragment extends Fragment {

     private NumberPicker numberPicker0, numberPicker1;


     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

          FragmentParamsForDekkerAlgorithmBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_params_for_dekker_algorithm, container, false);

          numberPicker0 = binding.numberPickerProcess0;
          numberPicker1 = binding.numberPickerProcess1;
          numberPicker0.setMinValue(1);
          numberPicker1.setMinValue(1);
          numberPicker0.setMaxValue(10);
          numberPicker1.setMaxValue(10);

          Button proceedButton = binding.proceedButton;
          proceedButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("proc0iterations", numberPicker0.getValue());
                    bundle.putInt("proc1iterations", numberPicker1.getValue());
                    Navigation.findNavController(view).navigate(R.id.action_paramsForDekkerAlgorithmFragment_to_dekkerAlgorithmFragment, bundle);
               }
          });

          return binding.getRoot();
     }
}