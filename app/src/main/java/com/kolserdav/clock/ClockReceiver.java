package com.kolserdav.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClockReceiver extends BroadcastReceiver {

    Context contextActivity;

    public ClockReceiver(Context context) {
        contextActivity = context;
    }

    SharedPreferences sharedHour;
    SharedPreferences sharedMinute;
    SharedPreferences sharedIsChecked;

    protected void getSharedPreferences(Context context) {
        sharedHour = context.getSharedPreferences(
                contextActivity.getString(R.string.def_hour), Context.MODE_PRIVATE);
        sharedMinute = context.getSharedPreferences(
                contextActivity.getString(R.string.def_minute), Context.MODE_PRIVATE);
        sharedIsChecked = context.getSharedPreferences(
                contextActivity.getString(R.string.is_checked), Context.MODE_PRIVATE);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        getSharedPreferences(context);
        if(intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0) {
            String hour = sharedHour.getString(contextActivity.getString(R.string.def_hour), "07");
            String minute = sharedMinute.getString(contextActivity.getString(R.string.def_minute), "00");
            String isChecked = sharedIsChecked.getString(
                    contextActivity.getString(R.string.is_checked), "false");
            Date date = new Date();
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);
            Integer currentHour = calendar.get(Calendar.HOUR);
            Integer currentMinute = calendar.get(Calendar.MINUTE);
            Integer currentIs24Hour = calendar.get(Calendar.AM_PM);
            Integer newCurrentHour = currentHour + 12;
            currentHour = (currentIs24Hour == 1 && newCurrentHour <= 24)? newCurrentHour : currentHour;
            if (currentHour == Integer.parseInt(hour) && currentMinute == Integer.parseInt(minute)
                && isChecked.equals("true")) {
                Intent i = new Intent();
                i.setClassName("com.kolserdav.clock", "com.kolserdav.clock.Alert");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }
}
