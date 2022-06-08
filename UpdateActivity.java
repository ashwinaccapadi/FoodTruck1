package com.example.foodtruck1;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    private ItemManager itemManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemManager = new ItemManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Item> items = itemManager.selectAll();
        GridLayout grid = new GridLayout(this);
        grid.setRowCount(items.size());
        grid.setColumnCount(4);
        TextView[] ids = new TextView[items.size()];
        EditText[][] namesAndPrices = new EditText[items.size()][2];
        Button[] buttons = new Button[items.size()];
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
// Capture the width of screen
        int width = size.x;

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            ids[i] = new TextView(this);
            ids[i].setText("" + item.getId());

            namesAndPrices[i][0] = new EditText(this);
            namesAndPrices[i][1] = new EditText(this);
            namesAndPrices[i][0].setText(item.getName());
            namesAndPrices[i][1].setText("" + item.getPrice());

            namesAndPrices[i][0].setId(10 * item.getId());
            namesAndPrices[i][1].setId(10 * item.getId() + 1);

            buttons[i] = new Button(this);
            buttons[i].setText("Update");
            buttons[i].setId(item.getId());
            ButtonHandler bh = new ButtonHandler();
            buttons[i].setOnClickListener(bh);

            grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][0], width / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], width * 3 / 20, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(buttons[i], width / 4, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.setBackgroundColor(getResources().getColor(R.color.employeeColor));

        }


        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(grid);
        setContentView(scrollView);
    }

    private class ButtonHandler implements
            View.OnClickListener {
        public void onClick(View v) {
            int candyId = v.getId( );
            EditText nameET = ( EditText ) findViewById( 10 * candyId );
            EditText priceET = ( EditText ) findViewById( 10 * candyId + 1 );
            String name = nameET.getText( ).toString( );
            String priceString = priceET.getText( ).toString( );
            Double price = Double.parseDouble( priceString );
            itemManager.updateById( candyId, name, price );
            Toast.makeText( UpdateActivity.this, "Item updated", Toast.LENGTH_SHORT ).show( );
            updateView( );


        }
    }
}
