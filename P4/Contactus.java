package com.example.intent;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
public class Contactus extends AppCompatActivity {

    ImageButton imageButton;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        imageButton=(ImageButton)findViewById(R.id.imageButton);  imageButton2=(ImageButton)findViewById(R.id.imageButton2);  imageButton3=(ImageButton)findViewById(R.id.imageButton3);  imageButton4=(ImageButton)findViewById(R.id.imageButton4);
        imageButton.setOnClickListener(new View.OnClickListener() {  @Override
        public void onClick(View view) {
            Intent i= new Intent(Intent.ACTION_DIAL);  i.setData(Uri.parse("tel:+917891492201"));  startActivity(i);
        }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {  @Override
        public void onClick(View view) {
            Intent a=new Intent(Intent.ACTION_VIEW);  a.setData(Uri.parse("geo:12.3656489.88.4569875"));  Intent chooser= Intent.createChooser(a, "Reach us via: ");  startActivity(chooser);
        }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {  @Override
        public void onClick(View view) {
            Intent j=new Intent(Intent.ACTION_SEND);  j.setData(Uri.parse("email"));
            String[] s={"udit.ukg@gmail.com"};
            j.putExtra(Intent.EXTRA_EMAIL,s);
            j.putExtra(Intent.EXTRA_SUBJECT,"Complaint register");  j.putExtra(Intent.EXTRA_TEXT,"");
            j.setType("message/rfc822");
            Intent chooser = Intent.createChooser(j,"Mail Us Via:");  startActivity(chooser);
        }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {  @Override
        public void onClick(View view) {
            String url="https://www.linkedin.com/in/udit-gupta-89563253/";  Intent s=new Intent(Intent.ACTION_VIEW);  s.setData(Uri.parse(url));
            startActivity(s);
        }
        });
    }
}
