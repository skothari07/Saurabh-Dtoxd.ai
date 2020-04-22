package com.example.skoth.simplefileobserver;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.FileObserver;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by skoth on 18-04-2020.
 */

public class MyService extends Service {


    static final String TAG  = "FILEOBSERVERSERVICE";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service Started!");
        Toast.makeText(this ,"Service Starting!",Toast.LENGTH_SHORT).show();
        File sdCard = Environment.getExternalStorageDirectory();
        MainActivity.fileObserver = new MyObserver(sdCard.getAbsolutePath());
        MainActivity.fileObserver.startWatching();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service Destroyed");
        Toast.makeText(this, "Service Destroyed!", Toast.LENGTH_SHORT).show();
    }

}
