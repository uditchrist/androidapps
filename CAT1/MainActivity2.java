package com.example.cat1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private int mCount=0;
    private int mAmount=0;
    private TextView mShowAmount;
    private TextView mShowCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mShowCount = (TextView) findViewById(R.id.show_count);
        mShowAmount = (TextView) findViewById(R.id.show_amount);
    }

    public void two_wheel(View view) {
        mCount++;
        mAmount=mAmount+100;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        if (mShowAmount != null)
            mShowAmount.setText(Integer.toString(mAmount));

        Toast toast = Toast.makeText(this, "Two Wheeler Added!",
                Toast.LENGTH_SHORT);
        toast.show();

    }
    public void four_wheel(View view) {
        mCount++;
        mAmount=mAmount+200;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        if (mShowAmount != null)
            mShowAmount.setText(Integer.toString(mAmount));

        Toast toast = Toast.makeText(this, "Four Wheeler Added!",
                Toast.LENGTH_SHORT);
        toast.show();

    }
    public void truck_bus(View view) {
        mCount++;
        mAmount=mAmount+400;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        if (mShowAmount != null)
            mShowAmount.setText(Integer.toString(mAmount));
        Toast toast = Toast.makeText(this, "Truck/Bus Added!",
                Toast.LENGTH_SHORT);
        toast.show();

    }
    public void rfid(View view) {
        mCount++;
        mAmount=mAmount+50;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        if (mShowAmount != null)
            mShowAmount.setText(Integer.toString(mAmount));
        Toast toast = Toast.makeText(this, "RFID Added!",
                Toast.LENGTH_SHORT);
        toast.show();

    }
    public void vip_pass(View view) {
        mCount++;
        mAmount=mAmount+0;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        if (mShowAmount != null)
            mShowAmount.setText(Integer.toString(mAmount));
        Toast toast = Toast.makeText(this, "VIP PASS Added!",
                Toast.LENGTH_SHORT);
        toast.show();

    }
}