package com.kolserdav.clock;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class ClockService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "ok");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ClockThread clockThread = new ClockThread(getApplicationContext());
        clockThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void f() {
        Log.d("LOaG_TAG", "onStartCommand");
    }
}
