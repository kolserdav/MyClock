package com.kolserdav.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class Alert extends AppCompatActivity {

    protected static MediaPlayer mp;
    protected static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        mp = MediaPlayer.create(this, R.raw.sound1);
        playSound();
    }

    public Alert() {
        handler = new Handler();
    }

    public void playSound() {
        mp.start();
        final Integer duration = mp.getDuration();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                boolean playing = mp.isPlaying();
                if (!playing) {
                    mp.start();
                }
                handler.postDelayed(this, duration + 5000);
            }
        };
        handler.post(task);
    }

    public void stopSound(View v) {
        handler.removeCallbacksAndMessages(null);
        mp.stop();
        Context context = getBaseContext();
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}
