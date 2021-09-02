package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    ImageView ibutton;
    TextView dateView,timeView;
    Button dateButton,timeButton;
    int year,month,day,hour,minute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dateView = (TextView)findViewById(R.id.date_text);
        timeView = (TextView)findViewById(R.id.time_text);
        dateButton = (Button)findViewById(R.id.date_button);
        timeButton = (Button)findViewById(R.id.time_button);





        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(MainActivity2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateView.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog.OnTimeSetListener mTimeSetListener = new
                        TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay, int
                                    minute) {
                                timeView.setText(hourOfDay+":"+minute);
                            }
                        };
                TimePickerDialog tpd = new TimePickerDialog(MainActivity2.this,
                        mTimeSetListener, hour, minute, false);
                tpd.show();
            }
        });


        ibutton=findViewById(R.id.imageView);

        ibutton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                                           startActivity(intent);
                                       }
                            });


    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    // Create an ArrayAdapter using the string array and a default spinner layout

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears

 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String text = parent.getItemAtPosition(pos).toString();
       Toast toast= Toast.makeText(this,text, Toast.LENGTH_SHORT);
       toast.show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}