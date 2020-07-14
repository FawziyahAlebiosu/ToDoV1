package com.example.android.disherv1;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> dishesNames = new ArrayList<>();
    ArrayAdapter<String> dishAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       listView = findViewById(R.id.dishNames);
       dishAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dishesNames);
        dishAdapter.notifyDataSetChanged();
        dishesNames.add(getString(R.string.heading));
        dishAdapter.notifyDataSetChanged();
        listView.setAdapter(dishAdapter);

        findViewById(R.id.addbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go from mainactivity to adddish to get the name!
                Intent intent = new Intent(MainActivity.this, addDish.class);
                MainActivity.this.startActivityForResult(intent, 100);

            }
        });


       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int toRemove = i;
                AlertDialog.Builder confirmDelete = new AlertDialog.Builder(MainActivity.this);
                confirmDelete.setMessage(getString(R.string.confirmDialog))
                        .setPositiveButton(getString(R.string.positiveTxt), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dishesNames.remove(toRemove);
                                dishAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null);
                confirmDelete.show();
            return true;
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String dishName = data.getExtras().getString("name");
            String dishIngredient = data.getExtras().getString("ingredient_One");
            dishesNames.add(dishName);
            dishAdapter.notifyDataSetChanged();
            dishesNames.add(dishIngredient);
            dishAdapter.notifyDataSetChanged();

        }
    }
}
