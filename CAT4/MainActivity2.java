package com.example.cat4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button button2;
    SharedPreferences sharedpreferences;
    EditText name;
    EditText email;
    EditText roll;
    EditText myclass;
    DBHelper DB;

    public static final String mypreference = "mcapref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Roll = "rollKey";
    public static final String Myclass = "myclassKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        roll = (EditText) findViewById(R.id.etRollno);
        myclass=(EditText) findViewById(R.id.etClass);
        button2=(Button)findViewById(R.id.submit);


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

    public void submit(View view){

        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(intent);
        finish();
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