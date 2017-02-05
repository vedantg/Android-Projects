package com.cs442.vgodhamg.androidservicesnew;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Peter Leow on 18/9/2014.
 */
public class StartedService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}