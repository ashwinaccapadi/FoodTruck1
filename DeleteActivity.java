package com.example.foodtruck1;

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

public class DeleteActivity extends AppCompatActivity {
    private ItemManager itemManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        itemManager = new ItemManager(this);
        updateView();
    }



    public void updateView() {
        ArrayList<Item> items = itemManager.selectAll();
        RelativeLayout layout= new RelativeLayout(this);
        ScrollView scroll=new ScrollView(this);
        RadioGroup group= new RadioGroup(this);
        layout.addView(scroll);
        scroll.addView(group);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                itemManager.deleteById(checkedId);
                Toast.makeText(DeleteActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                updateView();

            }
        });
        for (Item item : items) {
            RadioButton rb = new RadioButton(this);
            rb.setId(item.getId());
            rb.setText(item.otherCutertoString());
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
