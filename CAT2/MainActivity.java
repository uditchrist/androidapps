package com.example.pizza;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.pizza.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Button button;
    EditText editText1, editText2;
    String editTextVal;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imageButton=(ImageButton)findViewById(R.id.img);
        button=(Button)findViewById(R.id.login);
        editText1=(EditText)findViewById(R.id.name);
        editText2=(EditText)findViewById(R.id.pwd);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.makemytrip.co.in/";
                Intent s=new Intent(Intent.ACTION_VIEW);
                s.setData(Uri.parse(url));
                startActivity(s);
            }
        });

        button.setOnClickListener(view -> {
            if( editText1.getText().toString().equals("udit") &&
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

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Travel Booking!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
                m.setData(Uri.parse("tel:+91 7891492201"));
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