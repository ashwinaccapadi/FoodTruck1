package com.example.foodtruck1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    private double totalPrice;
    private ItemManager itemManager;
    public static ArrayList<IceCream> allOrders;

    @Override
    @SuppressLint("RestrictedApi")
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemManager = new ItemManager(this);
        totalPrice = 0.0;
        allOrders = new ArrayList<IceCream>();

        //updateView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView = findViewById(R.id.icecream_image);
       RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
       params.width = 1000;
       params.height = 1000;

// existing height is ok as is, no need to edit it
       imageView.setLayoutParams(params);

    }

    public void startOrder(View view)
    {
        Intent containerIntent = new Intent(
                getApplicationContext(), ContainerActivity.class );
        this.startActivity( containerIntent );
    }

    public void updateView()
    {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent insertIntent = new Intent(
                        this, InsertPasswordActivity.class);
                this.startActivity(insertIntent);
                break;

            case R.id.action_delete: {
                Intent deleteIntent = new Intent(this, DeletePasswordActivity.class);
                this.startActivity(deleteIntent);
                break;

            }
            case R.id.action_update: {
                Intent updateIntent = new Intent(this, UpdatePasswordActivity.class);
                this.startActivity(updateIntent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);

    }



}