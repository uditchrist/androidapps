package com.example.pizza;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView t1;
    ImageButton ibtn;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=(TextView)findViewById(R.id.title);
        Bundle b = getIntent().getExtras();
        String val =b.getString("Value");
        t1.setText("Welcome\n"+val);


        ibtn=(ImageButton)findViewById(R.id.ibtn);
        registerForContextMenu(ibtn);





        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_contact:
                NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view);
                navigationView2.getMenu().clear(); //clear old inflated items.
                navigationView2.inflateMenu(R.menu.navcontact);
                navigationView2.setNavigationItemSelectedListener(this);
                break;
            case R.id.nav_flight:
                Toast.makeText(getApplicationContext(),
                        "Redirecting to Flight Booking",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_train:
                Intent j = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(j);
                finish();
                break;
            case R.id.nav_bus:
                Intent k = new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(k);
                finish();
                break;

            case R.id.nav_email:
                Intent e = new Intent(Intent.ACTION_SEND);
                e.setData(Uri.parse("email"));
                String[] s = {"udit.ukg@gmail.com"};
                e.putExtra(Intent.EXTRA_EMAIL, s);
                e.putExtra(Intent.EXTRA_SUBJECT, "Complaint register");
                e.setType("message/HELLO");
                Intent chooser = Intent.createChooser(e, "Mail Us Via:");
                startActivity(chooser);
                break;
            case R.id.nav_call:
                Intent m = new Intent(Intent.ACTION_DIAL);
                m.setData(Uri.parse("tel:+917891492201"));
                startActivity(m);
                break;
            case R.id.nav_whatsapp:
                String url = "https://web.whatsapp.com/";
                Intent w = new Intent(Intent.ACTION_VIEW);
                w.setData(Uri.parse(url));
                startActivity(w);
                break;
            case R.id.nav_location:
                Intent a = new Intent(Intent.ACTION_VIEW);
                a.setData(Uri.parse("https://maps.app.goo.gl/gs6RaT9EN8Kjdsn39"));
                Intent l = Intent.createChooser(a, "Reach us via: ");
                startActivity(l);
                break;
            case R.id.nav_logout:
                Intent out = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(out);
                finish();
                break;
            case R.id.nav_BackPress:
                NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
                navView.getMenu().clear();
                navView.inflateMenu(R.menu.navmenu);
                navView.setNavigationItemSelectedListener(this);
                break;
            case R.id.nav_insta:
                String link = "#";
                Intent ins = new Intent(Intent.ACTION_VIEW);
                ins.setData(Uri.parse(link));
                startActivity(ins);
                break;
            case R.id.nav_fb:
                String fb = "#";
                Intent p = new Intent(Intent.ACTION_VIEW);
                p.setData(Uri.parse(fb));
                startActivity(p);
                break;
            case R.id.nav_youtube:
                String v = "#";
                Intent yt = new Intent(Intent.ACTION_VIEW);
                yt.setData(Uri.parse(v));
                startActivity(yt);
                break;
            case R.id.nav_BackPress1:
                NavigationView naviView = (NavigationView) findViewById(R.id.nav_view);
                naviView.getMenu().clear();
                naviView.inflateMenu(R.menu.navmenu);
                naviView.setNavigationItemSelectedListener(this);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START); }
        else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.download){
            Toast.makeText(MainActivity2.this,"You selected "+item.toString(),Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.play){
            Toast.makeText(MainActivity2.this,"You selected "+item.toString(),Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity2.this,"You selected "+item.toString(),Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem items) {
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        Intent j = new Intent(MainActivity2.this, MainActivity3.class);
        if (actionBarDrawerToggle.onOptionsItemSelected(items)) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        switch (items.getItemId()) {
            case R.id.context_website:
                String url = "https://www.makemytrip.co.in/";
                Intent s = new Intent(Intent.ACTION_VIEW);
                s.setData(Uri.parse(url));
                startActivity(s);
                return true;
            case R.id.context_rate:
                startActivity(j);
                finish();
                return true;
            case R.id.context_locate:
                Intent a = new Intent(Intent.ACTION_VIEW);
                a.setData(Uri.parse("https://maps.app.goo.gl/gs6RaT9EN8Kjdsn39"));
                Intent chooser = Intent.createChooser(a, "Reach us via: ");
                startActivity(chooser);
                return true;
            case R.id.context_contact:
                Intent m = new Intent(Intent.ACTION_DIAL);
                m.setData(Uri.parse("tel:+919886993743"));
                startActivity(m);
                return true;
            case R.id.context_exit:
                finish();
                return true;
            case R.id.context_settings:
                startActivity(new Intent(Settings.ACTION_SETTINGS));
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
