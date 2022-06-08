package com.example.foodtruck1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.foodtruck1.ContainerActivity.orderSummary;

public class ToppingActivity extends AppCompatActivity
{
    private ItemManager itemManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        itemManager = new ItemManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Item> toppings = itemManager.selectCupToppings();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        final Activity thisActivity = this;

        RadioGroup group = new RadioGroup(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // delete candy from database
                Item selected = itemManager.selectById(checkedId);
                orderSummary.add(selected);
                Intent summaryIntent = new Intent(
                        thisActivity, OrderSummaryActivity.class );
                thisActivity.startActivity( summaryIntent );
                // confirm to the user
                Toast.makeText(ToppingActivity.this, selected.getName(), Toast.LENGTH_SHORT).show();
                // update screen (the list of candies has changed)
                updateView();
            }
        });

        for (Item i : toppings) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i.getId());
            rb.setText(i.cuterToString());
            // add rb to a RadioGroup group
            // in order to make them mutually exclusive
            group.addView(rb);
            rb.setTextSize(40);

        }
        scrollView.addView(group);
        relativeLayout.addView(scrollView);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorYellow));


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
                Intent summaryIntent = new Intent(
                        thisActivity, OrderSummaryActivity.class );
                thisActivity.startActivity( summaryIntent );
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
