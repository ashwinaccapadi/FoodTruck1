package com.example.foodtruck1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity
{
    private ItemManager itemManager;
    Spinner spinner;
    private String[] paths = {"Container", "Flavor", "Topping"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        itemManager = new ItemManager(this);
        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(InsertActivity.this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void insert( View v) {
        // retrieve name and price
        EditText nameEditText =
                ( EditText) findViewById( R.id.input_name );

        EditText priceEditText =
                ( EditText) findViewById( R.id.input_price );
        String name = nameEditText.getText( ).toString( );
        String category = spinner.getSelectedItem().toString();
        Double price =
                Double.parseDouble(priceEditText.getText( ).toString( ));

        // insert new Item in database
        itemManager.insert(new Item(0,name, category,price));
        Toast.makeText( this, "Item added", 	Toast.LENGTH_LONG ).show( );

        // clear data in the two EditTexts
        nameEditText.setText( "" );
        priceEditText.setText( "" );

        ArrayList<Item> items = itemManager.selectAll( );
        for( Item item : items )
            Log.w( "MainActivity", "Item = " + item.toString( ) );


    }

    public void goBack( View v )
    {
        this.finish( );
    }

}

