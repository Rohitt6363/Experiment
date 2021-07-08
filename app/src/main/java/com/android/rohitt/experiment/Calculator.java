package com.android.rohitt.experiment;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Calculator {
    private static Handler mHandler;
    private static int mSpeed = 0;
    private float mDistance = 0;
    private Context mContext;
    private static float timer;
    private static DecimalFormat df = new DecimalFormat("0.0");
    private static Runnable runnable;

    public Calculator(Context context){
        mContext = context;
        mHandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView) ((Activity)mContext).findViewById(R.id.main_distance);
                textView.setText(df.format(mDistance));
                mHandler.postDelayed(this, (long) (timer*1000));
                if(mSpeed != 0){
                    mDistance = mDistance+0.1f;
                }
                else{
                    mDistance = mDistance;
                }
            }
        };
        mHandler.post(runnable);
    }

    public static void updateSpeed(int speed){
        mSpeed = speed;
        mHandler.removeCallbacks(runnable);
        mHandler.post(runnable);
        if(speed != 0){
            timer = 360/mSpeed;
        }

    }

    public float getmDistance(){
        return mDistance;
    }

    public void setmDistance(float distance){
        mDistance = distance;
    }

    public void setmSpeed(){
        mSpeed = 0;
    }


}
