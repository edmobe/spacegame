package com.example.edmobe.app;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private int y;

    private SensorManager SM;
    private Sensor mySensor;

    private String shoot = "f";
    private String dir = "c";
    private String levelMessage = "waiting for computer.";
    private String scoreMessage = "waiting for computer.";
    private String rowTypeMessage = "waiting for computer.";
    private String nextRowMessage = "waiting for computer.";

    private TextView level;
    private TextView score;
    private TextView rowType;
    private TextView nextRow;

    private static Socket s;
    private static DataInputStream dIn;
    private static DataOutputStream dOut;

    private static String messageIn;
    private static String messageOut;
    private static String[] gameInfo;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            level.setText("Level: " + levelMessage);
            score.setText("Score: " + scoreMessage);
            rowType.setText("Row class: " + rowTypeMessage);
            nextRow.setText("Next row class: " + nextRowMessage);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_GAME);

        level = findViewById(R.id.textView3);
        score = findViewById(R.id.textView4);
        rowType = findViewById(R.id.textView5);
        nextRow = findViewById(R.id.textView6);

        Runnable client = new Runnable() {
            @Override
            public void run() {

                synchronized (this) {

                    try {
                        s = new Socket("192.168.43.240", 5000);
                        dIn = new DataInputStream(s.getInputStream());
                        dOut = new DataOutputStream(s.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while (true) {
                        try {
                            messageIn = dIn.readUTF();
                            messageOut = dir + shoot;
                            dOut.writeUTF(messageOut);

                            System.out.println("Sent: " + messageOut);
                            System.out.println("Received: " + messageIn);

                            gameInfo = messageIn.split("/");

                            levelMessage = gameInfo[0];
                            scoreMessage = gameInfo[1];
                            rowTypeMessage = gameInfo[2];
                            nextRowMessage = gameInfo[3];

                            handler.sendEmptyMessage(0);

                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                }
            }
        };

        Thread clientThread = new Thread(client);
        clientThread.start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        y = (int) event.values[1];

        if (y < -2) {
            dir = "l";
        } else if (y > 2) {
            dir = "r";
        } else {
            dir = "c";
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