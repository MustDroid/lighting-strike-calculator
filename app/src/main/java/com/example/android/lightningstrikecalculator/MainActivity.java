package com.example.android.lightningstrikecalculator;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final double SPEED_OF_SOUND = 343.2;
    // Konstanten in Java werden durch Grossgeschriebene Letters geschrieben
    // Final bedeutet, dass der Wert nie mehr verandert wird

    Button bStart, bStopp;
    Chronometer chronometer;
    long abgelaufeneZeit;

    private boolean isStartPressed = false;
    private double distanceInMetres = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStart = (Button) findViewById(R.id.bStart);
        bStopp = (Button) findViewById(R.id.bStopp);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        abgelaufeneZeit = 0;
    }

    public void btnStart_OnClick(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        isStartPressed = true;
    }

    public void btnStopp_onClick(View view) {
        if(isStartPressed) {
            abgelaufeneZeit = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            distanceInMetres = abgelaufeneZeit * SPEED_OF_SOUND / 1000.0;

            isStartPressed = false;
        }

        Toast.makeText(this, "Der Blitz ist " + distanceInMetres + "m von dir entfernt!", Toast.LENGTH_SHORT).show();
    }
}
