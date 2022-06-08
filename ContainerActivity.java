package com.example.foodtruck1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ContainerActivity extends AppCompatActivity
{
    private ItemManager itemManager;
    public static ArrayList<Item> orderSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        itemManager = new ItemManager(this);
        updateView();
        orderSummary = new ArrayList<Item>();
    }

    public void updateView() {
        ArrayList<Item> containers = itemManager.selectContainers();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        final Activity thisActivity = this;

        RadioGroup group = new RadioGroup(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // select from database
                Item selected = itemManager.selectById(checkedId);
                orderSummary.add(selected);
                // confirm to the user
                Toast.makeText(ContainerActivity.this, selected.getName(), Toast.LENGTH_SHORT).show();
                Intent flavorIntent = new Intent(
                        thisActivity, FlavorActivity.class );
                thisActivity.startActivity( flavorIntent );
                // update screen (the list has changed)
                updateView();
            }
        });

        for (Item i : containers)
        {
            RadioButton rb = new RadioButton(this);
            rb.setId(i.getId());
            rb.setText(i.cuterToString());
            rb.setTextSize(40);
            // add rb to a RadioGroup group
            // in order to make them mutually exclusive
            group.addView(rb);
        }
        scrollView.addView(group);
        relativeLayout.addView(scrollView);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.color3));


        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(v);
            }
        });
        button.setText("BACK");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(10, 0, 0, 50);
        relativeLayout.addView(button, params);

        /*Button button2 = new Button(this);
        final Activity thisActivity = this;
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flavorIntent = new Intent(
                        thisActivity, FlavorActivity.class );
                thisActivity.startActivity( flavorIntent );
            }
        });
        button2.setText("NEXT");

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params2.setMargins(0, 0, 10, 50);
        relativeLayout.addView(button2, params2);*/


        setContentView(relativeLayout);



    }

    public void goBack( View v )
    {
        this.finish( );
    }
}
