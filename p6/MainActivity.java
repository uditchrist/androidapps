package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v4.app.RemoteActionCompatParcelizer.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    boolean playing = false;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button3=findViewById(R.id.button3);

         button3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                 startActivity(intent);
             }
         });


        ToggleButton play = (ToggleButton) findViewById(R.id.button_play);

        Button pause = (Button) findViewById(R.id. button_pause);


        Button stop = (Button) findViewById(R.id. button_stop);

        final MediaPlayer mp=MediaPlayer.create(getBaseContext(),R.raw.audio);
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!playing){
                    mp.start();
                    playing=true;
                    ProgressDialog pds = new ProgressDialog(MainActivity.this);

                    pds.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pds.setMessage("Playing song");
                    pds.setIndeterminate(true);
                    pds.setCancelable(true);
                    pds.show();
                }
            }
        });
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(playing){
                    mp.pause();
                    playing=false;
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (playing) {
                    mp.stop();
                    playing = false;
                }

            }
        });
    }

}