package com.example.android.disherv1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class itemAdapter extends RecyclerView.Adapter<itemAdapter.viewHolder>{

    public interface onLongClickListener{
        void onItemLongClicked(int position);

    }
    List<String> toDoItems = new ArrayList<>();
    onLongClickListener longClickListener;
    public itemAdapter(List<String>toDoItems, onLongClickListener longClickListener){
        this.toDoItems = toDoItems;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use a layout view to inflate a view; inflate means to instantiate the view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false );
        return new viewHolder(todoView);

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
                currentItem = itemView.findViewById(android.R.id.text1);
            }
//bind will update view inside the viewholder with the current string
        public void bind(String item) {
                currentItem.setText(item);
                currentItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        longClickListener.onItemLongClicked(getAdapterPosition());
                        return true;
                    }
                });
        }
    }

    }