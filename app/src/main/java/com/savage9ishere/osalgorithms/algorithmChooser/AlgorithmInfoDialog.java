package com.savage9ishere.osalgorithms.algorithmChooser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savage9ishere.osalgorithms.R;


public class AlgorithmInfoDialog extends DialogFragment {

     private String algorithmName, longDescription;

     public static AlgorithmInfoDialog newInstance(AlgorithmItem item){
          AlgorithmInfoDialog frag = new AlgorithmInfoDialog();
          Bundle bundle = new Bundle();
          bundle.putString("name", item.getAlgoName());
          bundle.putString("longDescription", item.getLongDescription());
          frag.setArguments(bundle);
          return frag;
     }

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          Bundle bundle = getArguments();
          algorithmName = bundle.getString("name");
          longDescription = bundle.getString("longDescription");
     }

     @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View v = inflater.inflate(R.layout.fragment_algorithm_info_dialog, container, false);

          TextView algorithmNameTextView = v.findViewById(R.id.algoNameTextView);
          TextView algorithmDescriptionTextView = v.findViewById(R.id.longDescriptionTextView);
          TextView closeTextView = v.findViewById(R.id.close_text_view);

          algorithmNameTextView.setText(algorithmName);
          algorithmDescriptionTextView.setText(longDescription);

          closeTextView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    dismiss();
               }
          });


          return v;
     }

//     @NonNull
//     @Override
//     public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//
//          Bundle bundle = getArguments();
//          String algorithmName = bundle.getString("name");
//          String longDescription = bundle.getString("longDescription");
//
//          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//          LayoutInflater inflater = requireActivity().getLayoutInflater();
//
//          View v = inflater.inflate(R.layout.fragment_algorithm_info_dialog, null);
//
//          TextView algorithmNameTextView = v.findViewById(R.id.algoNameTextView);
//          TextView algorithmDescriptionTextView = v.findViewById(R.id.longDescriptionTextView);
//          TextView closeTextView = v.findViewById(R.id.close_text_view);
//
//          algorithmNameTextView.setText(algorithmName);
//          algorithmDescriptionTextView.setText(longDescription);
//
//          closeTextView.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View view) {
//                    dismiss();
//               }
//          });
//
//          return builder.create();
//     }

     @Override
     public void onAttach(@NonNull Context context) {
          super.onAttach(context);

     }
}