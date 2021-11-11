package com.example.cat4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText1, editText2;
    String editTextVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.login);
        editText1=(EditText)findViewById(R.id.name);
        editText2=(EditText)findViewById(R.id.pwd);

        button.setOnClickListener(view -> {
            if( editText1.getText().toString().equals("udit.ukg@gmail.com") &&
                    editText2.getText().toString().equals("1234")) {
                Toast.makeText(getApplicationContext(),
                        "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getBaseContext(), MainActivity2.class);
                editTextVal = editText1.getText().toString();
                i1.putExtra("Value", editTextVal);
                startActivity(i1);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

            }
        });
    }
}