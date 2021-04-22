package com.savage9ishere.osalgorithms.petersonAlgo;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentParamsForPetersonBinding;

public class ParamsForPeterson extends Fragment {

     private EditText id1EditText, id2EditText;
     private Button proceedButton;

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          FragmentParamsForPetersonBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_params_for_peterson, container, false);

          id1EditText = binding.id1EditText;
          id2EditText = binding.id2EditText;
          proceedButton = binding.proceedButton;

          proceedButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    String id1 = id1EditText.getText().toString();
                    String id2 = id2EditText.getText().toString();
                    if(!id1.isEmpty() && !id2.isEmpty()){

                         if((id1.equals("0") || id1.equals("1")) && (id2.equals("0") || id2.equals("1")) && !id1.equals(id2)){
                              Bundle bundle = new Bundle();
                              bundle.putString("id1", id1);
                              bundle.putString("id2", id2);
                              Navigation.findNavController(view).navigate(R.id.action_paramsForPeterson_to_petersonAlgoFragment, bundle);
                         }

                         else {
                              Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                         }

                    }
                    else {
                         Toast.makeText(getContext(), "Enter IDs for both processes", Toast.LENGTH_SHORT).show();
                    }
               }
          });

          return binding.getRoot();
     }
}