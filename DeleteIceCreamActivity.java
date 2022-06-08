package com.example.foodtruck1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.foodtruck1.MainActivity.allOrders;

public class DeleteIceCreamActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // itemManager = new ItemManager(this);
        updateView();
    }



    public void updateView() {
       // ArrayList<IceCream> iceCreams = allOrders;
       // ArrayList<String>
        RelativeLayout layout= new RelativeLayout(this);
        ScrollView scroll=new ScrollView(this);
        RadioGroup group= new RadioGroup(this);
        layout.addView(scroll);
        scroll.addView(group);
        final Activity thisActivity = this;

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
               // itemManager.deleteById(checkedId);
                allOrders.remove(i-1);
                for(IceCream x: allOrders)
                {
                    Log.w("ItemManager", "" + x);
                }
                Toast.makeText(DeleteIceCreamActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                updateView();
                Intent summaryIntent = new Intent(
                        thisActivity, OrderSummaryActivity.class );
                thisActivity.startActivity( summaryIntent );

            }
        });
        for (IceCream iceCream : allOrders) {
            RadioButton rb = new RadioButton(this);
            rb.setId(iceCream.getId());
            rb.setText(iceCream.cuterToString());
            // add rb to a RadioGroup group
            // in order to make them mutually exclusive
            group.addView(rb);
        }
        Button button= new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(v);

            }
        });
        button.setText("BACK");
        RelativeLayout.LayoutParams param= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        param.addRule(layout.ALIGN_PARENT_BOTTOM);
        param.addRule(layout.CENTER_HORIZONTAL);
        param.setMargins(0,0,0,50);
        layout.addView(button, param);
        layout.setBackgroundColor(getResources().getColor(R.color.employeeColor));

        setContentView(layout);
    }



    public void goBack(View view) {
        this.finish();
    }
}
