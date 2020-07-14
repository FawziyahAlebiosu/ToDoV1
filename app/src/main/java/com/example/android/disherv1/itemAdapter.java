package com.example.android.disherv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class itemAdapter extends RecyclerView.Adapter<itemAdapter.viewHolder>{
    List<String> toDoItems;
    public itemAdapter(List<String>toDoItems){
        this.toDoItems = toDoItems;

    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use a layout view to inflate a view; inflate means to instantiate the view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.Layout.simple_list_item_1, parent, false);
        return new RecyclerView.ViewHolder(todoView);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
       String item =  toDoItems.get(position);
       holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return toDoItems.size();
    }

    class viewHolder extends  RecyclerView.ViewHolder {
        TextView currentItem;

            public viewHolder(@NonNull View itemView) {
                super(itemView);
                currentItem = itemView.findViewById()
            }

        public void bind(String item) {
                currentItem.setText(item);
        }
    }

    }