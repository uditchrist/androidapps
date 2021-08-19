package com.example.inputcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text to pass
                editText = findViewById(R.id.nameid);
                String textToPass = editText.getText().toString();

                // start the SecondActivity
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, textToPass);
                startActivity(intent);
            }
        });
    }
}