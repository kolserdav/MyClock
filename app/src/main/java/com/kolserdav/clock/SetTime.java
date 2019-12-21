package com.kolserdav.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class SetTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String oldHour = getIntent().getStringExtra("EXTRA_HOUR");
        String oldMinute = getIntent().getStringExtra("EXTRA_MINUTE");
        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        if (oldHour != null) {
            timePicker.setHour(Integer.parseInt(oldHour));
        }
        if (oldMinute != null) {
            timePicker.setMinute(Integer.parseInt(oldMinute));
        }
    }

    public void saveTime(View v) {
        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        Integer hour = timePicker.getHour();
        Integer minute = timePicker.getMinute();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("EXTRA_NEW_HOUR", hour.toString());
        intent.putExtra("EXTRA_NEW_MINUTE", minute.toString());
        startActivity(intent);
    }
}
