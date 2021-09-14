package com.example.pizza;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity4 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button b1;
    private TextView dateText;

    Spinner spin;
    String spin_val;
    String[] type = {"Domestic", "International"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        dateText = findViewById(R.id.datetxt);

        b1 = (Button) findViewById(R.id.checkout);

        spin = (Spinner) findViewById(R.id.type);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                spin_val = type[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });

        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(MainActivity4.this, android.R.layout.simple_spinner_item, type);
        spin.setAdapter(spin_adapter);





        findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity4.this,"Thanks you! Your Flight booking has been confirmed. ",Toast.LENGTH_LONG).show();
            }
        });
    }



    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog( this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "Preferred Date: " + dayOfMonth + "/" + month + "/" + year;
        dateText.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem items){
        Intent i = new Intent(this, MainActivity.class);
        Intent j = new Intent(this, MainActivity3.class);
        switch (items.getItemId()) {
            case R.id.context_website:
                String url="https://www.makemytrip.co.in/";
                Intent s=new Intent(Intent.ACTION_VIEW);
                s.setData(Uri.parse(url));
                startActivity(s);
                return true;
            case R.id.context_rate:
                startActivity(j);
                finish();
                return true;
            case R.id.context_locate:
                Intent a=new Intent(Intent.ACTION_VIEW);
                a.setData(Uri.parse("https://maps.app.goo.gl/gs6RaT9EN8Kjdsn39"));
                Intent chooser= Intent.createChooser(a, "Reach us via: ");
                startActivity(chooser);
                return true;
            case R.id.context_contact:
                Intent m= new Intent(Intent.ACTION_DIAL);
                m.setData(Uri.parse("tel:+919886993743"));
                startActivity(m);
                return true;
            case R.id.context_exit:
                finish();
                return true;
            case R.id.context_home:
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(items);
        }
    }
}