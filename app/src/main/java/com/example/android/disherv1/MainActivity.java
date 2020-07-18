package com.example.android.disherv1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> todoItems;
    Button addItemBtn;
    EditText enterItem;
    RecyclerView lists;
    itemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemBtn = findViewById(R.id.addbtn);
        enterItem = findViewById(R.id.enterToDo);
        lists = findViewById(R.id.listOfItems);

        loadItems();

        itemAdapter.OnLongClickListener onLongClickListener = new itemAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position){
                todoItems.remove(position);
                itemAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item deleted successfully", Toast.LENGTH_LONG).show();
                writeItems();
            }
        };
         itemAdapter = new itemAdapter(todoItems, onLongClickListener);
        //construct adapter

        lists.setAdapter(itemAdapter);
        lists.setLayoutManager(new LinearLayoutManager(this));



        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //grab the item in edit text and save it!
                String entered = enterItem.getText().toString();
                todoItems.add(entered);
                itemAdapter.notifyItemInserted(todoItems.size()-1);
                enterItem.setText("");
                Toast.makeText(getApplicationContext(), "Item added successfully",Toast.LENGTH_LONG).show();
                writeItems();
            }
        });

    }
    private File getDataFile(){
        //returns file that will store todo items
        return new File(getFilesDir(), "todo.txt");
    }

    //function that will load items
    private void loadItems() {
        try {
            todoItems = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity", "error getting stuff", e);
            todoItems = new ArrayList<>();
        }
    }
    //functions that will write the actual file
    private void writeItems() {
        try {
            FileUtils.writeLines(getDataFile(), todoItems);
        } catch (IOException e) {
            Log.e("MainActivity", "error writing stuff", e);
        }
    }
}