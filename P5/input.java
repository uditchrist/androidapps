package com.example.inputcontrol;



import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.Rating;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class input extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    CheckBox dev1, dev2;
    RadioButton app1, app2, app3, app4;
    RatingBar ratinginput;
    String totalStars, rating;
    Button sub;
    Button btnStartProgress;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        addListenerOnButtonClick();

        dev1 = (CheckBox) findViewById(R.id.dev1);
        dev2 = (CheckBox) findViewById(R.id.dev2);
        app1 = (RadioButton) findViewById(R.id.select1);
        app2 = (RadioButton) findViewById(R.id.select2);
        app3 = (RadioButton) findViewById(R.id.select3);
        app4 = (RadioButton) findViewById(R.id.select4);
        ratinginput = (RatingBar) findViewById(R.id.ratinginput);
        sub = (Button) findViewById(R.id.submit);

        if (dev1.isChecked()) {
            if (app1.isChecked()) {
                totalStars = "Total Stars:: " + ratinginput.getNumStars();
                rating = "Rating :: " + ratinginput.getRating();
            } else if (app3.isChecked()) {
                totalStars = "Total Stars:: " + ratinginput.getNumStars();
                rating = "Rating :: " + ratinginput.getRating();

            } else {
                totalStars = "Total Stars:: " + ratinginput.getNumStars();
                rating = "Rating :: " + ratinginput.getRating();
            }

        } else if (dev2.isChecked()) {
            if (app2.isChecked()) {
                totalStars = "Total Stars:: " + ratinginput.getNumStars();
                rating = "Rating :: " + ratinginput.getRating();
            } else if (app4.isChecked()) {
                totalStars = "Total Stars:: " + ratinginput.getNumStars();
                rating = "Rating :: " + ratinginput.getRating();

            } else {
                totalStars = "Total Stars:: " + ratinginput.getNumStars();
                rating = "Rating :: " + ratinginput.getRating();
            }
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalStars = "Total Stars:: " + ratinginput.getNumStars();
                rating = "Rating :: " + ratinginput.getRating();
                Snackbar.make(view, "your stars " + totalStars + " " + "your rating " + rating, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void addListenerOnButtonClick() {
        btnStartProgress = findViewById(R.id.button3);
        btnStartProgress.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // creating progress bar dialog
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("File downloading ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                //reset progress bar and filesize status
                progressBarStatus = 0;
                fileSize = 0;

                new Thread(new Runnable() {
                    public void run() {
                        while (progressBarStatus < 100) {
                            // performing operation
                            progressBarStatus = doOperation();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // Updating the progress bar
                            progressBarHandler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }
                        // performing operation if file is downloaded,
                        if (progressBarStatus >= 100) {
                            // sleeping for 1 second after operation completed
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // close the progress bar dialog
                            progressBar.dismiss();
                        }
                    }
                }).start();
            }//end of onClick method
        });
    }
    // checking how much file is downloaded and updating the filesize
    public int doOperation() {
        //The range of ProgressDialog starts from 0 to 10000
        while (fileSize <= 10000) {
            fileSize++;
            if (fileSize == 1000) {
                return 10;
            } else if (fileSize == 2000) {
                return 20;
            } else if (fileSize == 3000) {
                return 30;
            } else if (fileSize == 4000) {
                return 40; // you can add more else if
            }
         /* else {
                return 100;
            }*/
        }//end of while
        return 100;
    }//end of doOperation

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

