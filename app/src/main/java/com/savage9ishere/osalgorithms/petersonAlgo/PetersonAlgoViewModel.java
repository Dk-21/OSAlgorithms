package com.savage9ishere.osalgorithms.petersonAlgo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PetersonAlgoViewModel extends ViewModel {
     private MutableLiveData<String> outputTextMutableLiveData = new MutableLiveData<>();

     public LiveData<String> getOutputTextLiveData() {
          return outputTextMutableLiveData;
     }

     public void updateOutputText(String val){
          outputTextMutableLiveData.setValue(val);
     }

}
