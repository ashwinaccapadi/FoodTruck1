package com.example.foodtruck1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.foodtruck1.MainActivity.allOrders;

public class FinalActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        updateView();

    }

    public void updateView()
    {
        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundColor(getResources().getColor(R.color.colorLightRed));


        final Activity thisActivity = this;

        double totalPrice = 0.0;

        for(IceCream i: allOrders)
        {
            totalPrice += i.getPrice();
        }

        TextView textView = new TextView(this);
        textView.setText("Thank you for your order! Your total is $" + totalPrice);
        textView.setTextColor(getResources().getColor(R.color.color3));
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextSize(50);
        RelativeLayout.LayoutParams param2= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        param2.addRule(layout.ALIGN_PARENT_BOTTOM);
        param2.addRule(layout.CENTER_HORIZONTAL);
        param2.setMargins(0,0,0,1000);
        layout.addView(textView, param2);
        setContentView(layout);

        Button button= new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finishIntent = new Intent(
                        thisActivity, MainActivity.class );
                thisActivity.startActivity( finishIntent );

            }
        });
        button.setText("NEW CUSTOMER");
        RelativeLayout.LayoutParams param= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        param.addRule(layout.ALIGN_PARENT_BOTTOM);
        param.addRule(layout.CENTER_HORIZONTAL);
        param.setMargins(0,0,0,50);
        layout.addView(button, param);
        setContentView(layout);
    }
}
