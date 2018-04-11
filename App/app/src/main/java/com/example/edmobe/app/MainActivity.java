package com.example.edmobe.app;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.edmobe.app.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private int y;

    private SensorManager SM;
    private Sensor mySensor;

    private MessageSender messageSender;

    private String shoot = "f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        y = (int) event.values[1];

        messageSender = new MessageSender();

        if (y < -3) {
            messageSender.execute("l" + shoot);
        } else if (y > 3) {
            messageSender.execute("r" + shoot);
        } else {
            messageSender.execute("c" + shoot);
        }

        shoot = "f";

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used.
    }

    public void buttonOnClick(View v) {

        shoot = "t";

    }
}