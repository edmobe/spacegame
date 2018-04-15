package com.example.edmobe.app;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    Socket s;
    ServerSocket ss;
    InputStreamReader isr;
    BufferedReader bufferedReader;
    Handler handler = new Handler();
    String message;
    String[] gameInfo;

    TextView level;
    TextView score;
    TextView rowType;
    TextView nextRow;

    Server(TextView level, TextView score, TextView rowType, TextView nextRow) {
        this.level = level;
        this.score = score;
        this.rowType = rowType;
        this.nextRow = nextRow;
    }

    @Override
    public void run() {

        try {

            ss = new ServerSocket(5001);

            while(true) {

                s = ss.accept();
                isr = new InputStreamReader(s.getInputStream());
                bufferedReader = new BufferedReader(isr);
                message = bufferedReader.readLine();
                gameInfo = message.split("/");


               handler.post(new Runnable() {
                   @Override
                    public void run() {

                        level.setText("Level: " + gameInfo[0]);
                        score.setText("Score: " + gameInfo[1]);
                        rowType.setText("Row class: " + gameInfo[2]);
                        nextRow.setText("Next row class: " + gameInfo[3]);

                        handler.postDelayed(this, 10000);

                   }
                });


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
