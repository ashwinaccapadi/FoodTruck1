package com.example.foodtruck1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdatePasswordActivity extends AppCompatActivity
{

    private PasswordManager passwordManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        passwordManager = new PasswordManager(this);
    }


    public void check(View v)
    {
        EditText passwordEditText =
                ( EditText) findViewById( R.id.input_password );
        EditText usernameEditText =
                ( EditText) findViewById( R.id.input_username );

        String password = passwordEditText.getText( ).toString( );
        String username = usernameEditText.getText().toString();

        ArrayList<Employee> allEmployees = passwordManager.selectAll();

        boolean test = false;

        for (Employee e: allEmployees)
        {
            if (e.getUsername().equals(username) && e.getPassword().equals(password))
            {
                Intent insertIntent = new Intent(
                        this, UpdateActivity.class);
                this.startActivity(insertIntent);
                Toast.makeText( this, "Password Correct", 	Toast.LENGTH_LONG ).show( );
                test = true;
            }
        }
        if (test == false)
        {
            Toast.makeText(this, "Password Incorrect", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack( View v )
    {
        this.finish( );
    }
}
