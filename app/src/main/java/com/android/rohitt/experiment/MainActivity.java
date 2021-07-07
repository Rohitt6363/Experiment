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
                Calculator.updateSpeed(Integer.parseInt(mSpeedEditText.getText().toString()));
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