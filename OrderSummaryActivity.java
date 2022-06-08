package com.example.foodtruck1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.foodtruck1.ContainerActivity.orderSummary;
import static com.example.foodtruck1.MainActivity.allOrders;

import java.text.NumberFormat;
import java.util.ArrayList;

public class OrderSummaryActivity extends AppCompatActivity
{
    private IceCream iceCream;
    private int orderNumber;
    private IceCreamManager iceCreamManager;
    private ItemManager itemManager;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        itemManager = new ItemManager(this);
        iceCreamManager = new IceCreamManager(this);
        setContentView(R.layout.order_summary);


        if(!orderSummary.isEmpty()) {
            if (allOrders.isEmpty()) {
                orderNumber = 1;
            } else {
                orderNumber = allOrders.get(allOrders.size() - 1).getID() + 1;
            }
            iceCream = new IceCream(orderNumber, orderSummary.get(0), orderSummary.get(1), orderSummary.get(2));
            orderSummary.clear();

            allOrders.add(iceCream);
            //iceCreamManager.insert(iceCream);
        }

        updateView();

    }

    public void updateView()
    {
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));


        if(allOrders.size() >= 0)
        {
            scrollView.removeAllViewsInLayout();
        }

        GridLayout grid = new GridLayout(this);
        grid.setRowCount((allOrders.size() + 1) / 2);
        grid.setColumnCount(1);

        Button[] buttons = new Button[allOrders.size()];

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        // Capture the width of screen
        int width = size.x;
        int buttonWidth=width;

        for(int i = 0; i < allOrders.size(); i++) {
            IceCream order = allOrders.get(i);
            buttons[i] = new Button(this);
            buttons[i].setText(order.getID() + "\n" + order.getContainer() + "\n" + order.getFlavor() + "\n" + order.getTopping() + "\n" + order.getPrice());
            buttons[i].setId(order.getId());
            int temp = i;
            final Activity thisActivity = this;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allOrders.remove(temp);
                    Intent containerIntent = new Intent(
                            getApplicationContext(), ContainerActivity.class );
                    thisActivity.startActivity( containerIntent );
                }
            });
            grid.addView( buttons[i], buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT );
        }
        scrollView.addView(grid);
        //setContentView(scrollView);
        Button button2 = new Button(this);
        final Activity thisActivity = this;
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent containerIntent = new Intent(
                        thisActivity, ContainerActivity.class );
                thisActivity.startActivity( containerIntent );
            }
        });
        button2.setText("Place New Order");
        button2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        grid.addView( button2, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

        Button button3 = new Button(this);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finishIntent = new Intent(
                        thisActivity, FinalActivity.class );
                thisActivity.startActivity( finishIntent );
            }
        });
        button3.setText("Complete Transaction");
        button3.setBackgroundColor(getResources().getColor(R.color.color1));

        grid.addView( button3, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);



        Button button4 = new Button(this);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allOrders.size()>1) {
                    Intent finishIntent = new Intent(
                            thisActivity, DeleteIceCreamPasswordActivity.class);
                    thisActivity.startActivity(finishIntent);
                }
            }
        });
        button4.setText("Delete any Ice Creams if you have more than one");
        button4.setBackgroundColor(getResources().getColor(R.color.color3));

        grid.addView( button4, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);




        setContentView(scrollView);

    }


}
