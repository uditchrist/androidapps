package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        // use the text in a TextView
        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(text);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // start the SecondActivity
                Intent intent = new Intent(ProfileActivity.this, Contactus.class);
                startActivity(intent);
            }
        });
    }
}
