package com.android.rohitt.experiment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity {
    private EditText mSpeedEditText;
    private TextView mDistance;
    private Button mCalculate;
    private Calculator mCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpeedEditText = findViewById(R.id.main_speed);
        mDistance = findViewById(R.id.main_distance);
        mCalculate = findViewById(R.id.main_button);
        mCalculator = new Calculator(this);


        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    String s = mSpeedEditText.getText().toString();
                    if(s.isEmpty()){
                        Toast.makeText(getApplicationContext(), "enter a valid speed in kmph", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        int val = Integer.parseInt(s);
                        if(val < 0){
                            Toast.makeText(getApplicationContext(), "speed cannot be negative", Toast.LENGTH_SHORT).show();
                        }
                        else if(val > 360){
                            Toast.makeText(getApplicationContext(), "engine turned off due to over speeding", Toast.LENGTH_SHORT).show();
                            Calculator.updateSpeed(0);
                        }
                        else{
                            Calculator.updateSpeed(val);
                        }

                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "engine turned off due to over speeding", Toast.LENGTH_SHORT).show();
                    Calculator.updateSpeed(0);
                }


            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("myDistance", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("Distance", mCalculator.getmDistance());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("myDistance", MODE_PRIVATE);
        mCalculator.setmDistance(sharedPreferences.getFloat("Distance", 0.0f));
        mCalculator.setmSpeed();
        mSpeedEditText.setText(String.valueOf(0));
    }
}