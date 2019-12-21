package com.kolserdav.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedHour;
    SharedPreferences sharedMinute;
    SharedPreferences sharedIsChecked;
    String defHour;
    String defMinute;
    String defIsChecked;
    String savedHour;
    String savedMinute;
    String isChecked;
    static Boolean serviceStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSharedContext();
        getTimeData();
        String hour = getIntent().getStringExtra("EXTRA_NEW_HOUR");
        String minute = getIntent().getStringExtra("EXTRA_NEW_MINUTE");
        TextView timeField = (TextView) findViewById(R.id.firstClock);
        if (hour != null && minute != null) {
            setTimeData(hour, minute);
            String time = getTimeValue(hour, minute);
            timeField.setText(time);
        }
        else {
            String time = getTimeValue(savedHour, savedMinute);
            timeField.setText(time);
        }
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkClock);
        Boolean isCheckedBool = isChecked.equals("true");
        checkBox.setChecked(isCheckedBool);
        if (!serviceStarted) {
            startService(new Intent(this, ClockService.class));
            serviceStarted = true;
        }
    }

    public void checkBoxHandler(View v) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkClock);
        Boolean checkBoxIsChecked = checkBox.isChecked();
        setCheckBoxValue(checkBoxIsChecked.toString());
    }

    public MainActivity() {
        defHour = "07";
        defMinute = "00";
        defIsChecked = "false";
        if (serviceStarted == null) {
            serviceStarted = false;
        }
    }

    protected void getSharedContext() {
        Context context = this;
        sharedHour = context.getSharedPreferences(getString(R.string.def_hour), Context.MODE_PRIVATE);
        sharedMinute = context.getSharedPreferences(getString(R.string.def_minute), Context.MODE_PRIVATE);
        sharedIsChecked = context.getSharedPreferences(getString(R.string.is_checked), Context.MODE_PRIVATE);
    }

    protected String getTimeValue(String hour, String minute) {
        String newHour = (hour.length() == 1)? "0" + hour : hour;
        String newMinute = (minute.length() == 1)? "0" + minute : minute;
        return newHour + ":" + newMinute;
    }

    protected void getTimeData() {
        savedHour = sharedHour.getString(getString(R.string.def_hour), defHour);
        savedMinute = sharedMinute.getString(getString(R.string.def_minute), defMinute);
        isChecked = sharedIsChecked.getString(getString(R.string.is_checked), defIsChecked);
    }

    protected void setCheckBoxValue(String isChecked) {
        SharedPreferences.Editor checkBoxEditor = sharedIsChecked.edit();
        checkBoxEditor.putString(getString(R.string.is_checked), isChecked);
        checkBoxEditor.apply();
    }

    protected void setTimeData(String hour, String minute) {
        SharedPreferences.Editor hourEditor = sharedHour.edit();
        hourEditor.putString(getString(R.string.def_hour), hour);
        hourEditor.apply();
        SharedPreferences.Editor minuteEditor = sharedMinute.edit();
        minuteEditor.putString(getString(R.string.def_minute), minute);
        minuteEditor.apply();
    }

    public void setClock(View v) {
        Context context = getBaseContext();
        Intent intent = new Intent(context, SetTime.class);
        getTimeData();
        intent.putExtra("EXTRA_HOUR", savedHour);
        intent.putExtra("EXTRA_MINUTE", savedMinute);
        startActivity(intent);
    }

}
