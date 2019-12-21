package com.kolserdav.clock;

import android.content.Context;
import android.content.IntentFilter;

public class ClockThread extends Thread {

    Context context;

    @Override
    public void run() {
        super.run();
        ClockReceiver clockReceiver = new ClockReceiver(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        context.registerReceiver(clockReceiver, intentFilter);
    }

    public ClockThread(Context contextActivity) {
        context = contextActivity;
    }
}
