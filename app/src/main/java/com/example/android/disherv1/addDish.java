package com.example.android.disherv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addDish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);


        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String dishName = ((EditText) findViewById(R.id.enterName)).getText().toString();
                String ingredient_One = ((EditText) findViewById(R.id.enterIngredient)).getText().toString();
                intent.putExtra("name", dishName);
                intent.putExtra("ingredient_One", ingredient_One);
               // intent.putExtra("ingredient_Two", ingredient_Two);
                setResult(RESULT_OK, intent);
                //dismiss this activity, and send data off to mainactivity
                finish();
            }
        });

    }
}
