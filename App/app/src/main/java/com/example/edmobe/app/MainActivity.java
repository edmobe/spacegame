package com.example.edmobe.app;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

        TextView level = (TextView) findViewById(R.id.textView3);
        TextView score = (TextView) findViewById(R.id.textView4);
        TextView rowType = (TextView) findViewById(R.id.textView5);
        TextView nextRow = (TextView) findViewById(R.id.textView6);

        level.setText("Level: waiting for computer");
        score.setText("Score: waiting for computer");
        rowType.setText("Row class: waiting for computer");
        nextRow.setText("Next row class: waiting for computer");

        Thread server = new Thread(new Server(level, score, rowType, nextRow));
        server.start();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        y = (int) event.values[1];

        messageSender = new MessageSender();

        if (y < -2) {
            messageSender.execute("l" + shoot);
        } else if (y > 2) {
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