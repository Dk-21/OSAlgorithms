package com.savage9ishere.osalgorithms.bankersAlgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentAlgorithmParametersForBankersAlgoBinding;
import com.savage9ishere.osalgorithms.mainComponents.myApplication;

import java.util.concurrent.ExecutorService;


public class AlgorithmParametersForBankersAlgoFragment extends Fragment {

     private NumberPicker numberPickerProcess, numberPickerResources;
     private EditText allocationMatrixEditText, maxMatrixEditText, availableMatrixEditText;
     private ProgressBar progressBar;

     private ExecutorService executorService;
     private Handler handler;

     private View view;

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          myApplication myApplication = (com.savage9ishere.osalgorithms.mainComponents.myApplication) getActivity().getApplicationContext();
          executorService = myApplication.getExecutorService();
          handler = myApplication.getMainThreadHandler();

     }

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          FragmentAlgorithmParametersForBankersAlgoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_algorithm_parameters_for_bankers_algo, container, false);

          numberPickerProcess = binding.numberPickerProcess;
          numberPickerResources = binding.numberPickerResource;
          allocationMatrixEditText = binding.allocationMatrixEditText;
          maxMatrixEditText = binding.maxMatrixEditText;
          availableMatrixEditText = binding.availableMatrixEditText;
          Button proceedButton = binding.proceedButton;
          progressBar = binding.progressBar;

          numberPickerProcess.setMaxValue(10);
          numberPickerProcess.setMinValue(1);
          numberPickerResources.setMaxValue(10);
          numberPickerResources.setMinValue(1);

          proceedButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    final int processCount = numberPickerProcess.getValue();
                    final int resourcesCount = numberPickerResources.getValue();
                    final String allocationMatrix = allocationMatrixEditText.getText().toString();
                    final String maxMatrix = maxMatrixEditText.getText().toString();
                    final String availableMatrix = availableMatrixEditText.getText().toString();

                    if(!allocationMatrix.isEmpty() && !maxMatrix.isEmpty() && !availableMatrix.isEmpty()){
                         progressBar.setVisibility(View.VISIBLE);
                         //start a process to validate the input in background
                         executorService.execute(new Runnable() {
                              @Override
                              public void run() {
                                   startValidatingInputs(processCount, resourcesCount, allocationMatrix, maxMatrix, availableMatrix);
                              }
                         });
                    }
                    else {
                         Toast.makeText(getContext(), "Fill Every Matrix First", Toast.LENGTH_LONG).show();
                    }

               }
          });

          view = binding.getRoot();
          return binding.getRoot();
     }

     private void startValidatingInputs(int processCount, int resourcesCount, String allocationMatrix, String maxMatrix, String availableMatrix) {
          String[] processWiseAllocationMatrix = allocationMatrix.split("\n");
          String[] processWiseMaxMatric = maxMatrix.split("\n");
          String[] resourceWiseAvailableMatrix = availableMatrix.split(" ");
          int[][] allocationMatArray = new int[processCount][resourcesCount];
          int[][] maxMatArray = new int[processCount][resourcesCount];
          int[][] availMatArray = new int[1][resourcesCount];

          //allocation matrix checking
          if(processWiseAllocationMatrix.length == processCount){
               boolean invalid = false;
               for(String str : processWiseAllocationMatrix) {
                    String[] countResourceValues = str.split(" ");
                    if(countResourceValues.length != resourcesCount){
                         invalid = true;
                         break;
                    }
               }

               if(invalid){
                    //invalid input for allocationMatrix
                    sendWarningForInvalidInput();
                    return;
               }
               else {
                    //good to make allocation matrix
                    for(int i=0; i<processCount; i++){
                         String[] resourceValStringArray = processWiseAllocationMatrix[i].split(" ");
                         for(int j=0; j<resourcesCount; j++){
                              allocationMatArray[i][j] = Integer.parseInt(resourceValStringArray[j]);
                         }
                    }
               }
          }
          else {
               //invalid input for allocationMatrix
               sendWarningForInvalidInput();
               return;
          }

          //maxMatrix checking
          if(processWiseMaxMatric.length == processCount){
               boolean invalid = false;
               for (String str: processWiseMaxMatric){
                    String[] countResourceValues = str.split(" ");
                    if(countResourceValues.length != resourcesCount){
                         invalid = true;
                         break;
                    }
               }

               if (invalid){
                    //invalid input for maxMatrix
                    sendWarningForInvalidInput();
                    return;
               }
               else {
                    //good to make maxMatrix
                    for(int i=0; i<processCount; i++){
                         String[] resourceValStringArray = processWiseMaxMatric[i].split(" ");
                         for(int j=0; j<resourcesCount; j++){
                              maxMatArray[i][j] = Integer.parseInt(resourceValStringArray[j]);
                         }
                    }
               }
          }
          else {
               //invalid input for maxMatrix
               sendWarningForInvalidInput();
               return;
          }

          //available matrix checking
          if(resourceWiseAvailableMatrix.length == resourcesCount){
               for(int i=0; i<resourcesCount; i++){
                    availMatArray[0][i] = Integer.parseInt(resourceWiseAvailableMatrix[i]);
               }

               goodToGoToNextFragment(allocationMatArray, maxMatArray, availMatArray, processCount, resourcesCount);
          }
          else {
               //invalid input for availableMatrix
               sendWarningForInvalidInput();
          }
     }

     private void goodToGoToNextFragment(final int[][] allocationMatArray, final int[][] maxMatArray, final int[][] availMatArray, final int processCount, final int resourcesCount) {
          handler.post(new Runnable() {
               @Override
               public void run() {
                    progressBar.setVisibility(View.INVISIBLE);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("allocationMatrix", allocationMatArray);
                    bundle.putSerializable("maxMatrix", maxMatArray);
                    bundle.putSerializable("availableMatrix", availMatArray);
                    bundle.putInt("processCount", processCount);
                    bundle.putInt("resourcesCount", resourcesCount);
                    Navigation.findNavController(view).navigate(R.id.action_algorithmParameterFragment_to_bankersAlgorithmFragment, bundle);
               }
          });
     }

     private void sendWarningForInvalidInput() {
          handler.post(new Runnable() {
               @Override
               public void run() {
                    Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
               }
          });
     }


}