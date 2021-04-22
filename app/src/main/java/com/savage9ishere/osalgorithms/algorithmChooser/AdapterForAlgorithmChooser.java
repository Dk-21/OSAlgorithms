package com.savage9ishere.osalgorithms.algorithmChooser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savage9ishere.osalgorithms.R;

import java.util.ArrayList;

public class AdapterForAlgorithmChooser extends RecyclerView.Adapter<AdapterForAlgorithmChooser.ViewHolder> {

     private ArrayList<AlgorithmItem> algorithms;
     private Context context;
     private OnItemClickListener listener;

     public interface OnItemClickListener {
          void onItemClick(int position);
          void onInfoClick(int position);
     }

     public void setOnItemClickListener(OnItemClickListener listener){
          this.listener = listener;
     }

     public AdapterForAlgorithmChooser(Context context, ArrayList<AlgorithmItem> algorithms){
          this.algorithms = algorithms;
          this.context = context;
     }

     @NonNull
     @Override
     public AdapterForAlgorithmChooser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(context).inflate(R.layout.item_algorithm_chooser, parent, false);
          return new ViewHolder(view, listener);
     }

     @Override
     public void onBindViewHolder(@NonNull AdapterForAlgorithmChooser.ViewHolder holder, int position) {
          AlgorithmItem item = algorithms.get(position);
          String currentName = item.getAlgoName();
          String shortDescription = item.getShortDescription();
          holder.AlgorithmNameTextView.setText(currentName);
          holder.shortDescriptionTextView.setText(shortDescription);
     }

     @Override
     public int getItemCount() {
          return algorithms.size();
     }

     static class ViewHolder extends RecyclerView.ViewHolder {
          TextView AlgorithmNameTextView;
          TextView shortDescriptionTextView;
          ImageView infoImageView;

          ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
               super(itemView);
               AlgorithmNameTextView = itemView.findViewById(R.id.algorithm_name_text_view);
               shortDescriptionTextView = itemView.findViewById(R.id.description_txt);
               infoImageView = itemView.findViewById(R.id.info);

               itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         if (listener != null){
                              int position = getAdapterPosition();
                              if(position != RecyclerView.NO_POSITION){
                                   listener.onItemClick(position);
                              }
                         }
                    }
               });

               infoImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         if (listener != null){
                              int position = getAdapterPosition();
                              if(position != RecyclerView.NO_POSITION){
                                   listener.onInfoClick(position);
                              }
                         }
                    }
               });
          }
     }
}
