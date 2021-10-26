package com.example.mysharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText name;
    EditText email;
    EditText roll;
    EditText myclass;

    public static final String mypreference = "mcapref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Roll = "rollKey";
    public static final String Myclass = "myclassKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        roll = (EditText) findViewById(R.id.etRollno);
        myclass=(EditText) findViewById(R.id.etClass);


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));

        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(Roll)) {
            roll.setText(sharedpreferences.getString(Roll, ""));

        }
        if (sharedpreferences.contains(Myclass)) {
            myclass.setText(sharedpreferences.getString(Myclass, ""));

        }

    }

    public void Save(View view) {

        String n = name.getText().toString();
        String e = email.getText().toString();
        String f = roll.getText().toString();
        String g = myclass.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.putString(Roll,f);
        editor.putString(Myclass,g);
        editor.commit();
    }

    public void clear(View view) {
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        roll = (EditText) findViewById(R.id.etRollno);
        myclass = (EditText) findViewById(R.id.etClass);
        name.setText("");
        email.setText("");
        roll.setText("");
        myclass.setText("");

    }

    public void Get(View view) {
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        roll = (EditText) findViewById(R.id.etRollno);
        myclass = (EditText) findViewById(R.id.etClass);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(Roll)) {
            roll.setText(sharedpreferences.getString(Roll, ""));
        }
        if (sharedpreferences.contains(Myclass)) {
            myclass.setText(sharedpreferences.getString(Myclass, ""));

        }

    }
}