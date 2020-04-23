package com.example.skoth.simplefileobserver;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.FileObserver;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static com.example.skoth.simplefileobserver.App.CHANNEL_ID;

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
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,notificationIntent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("FileObserver is active..")
                .setContentText("Switch to app for list of events")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);

        File sdCard = Environment.getExternalStorageDirectory();
        File file = null;
        try {
            file = sdCard.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainActivity.fileObserver = new MyObserver(file.getAbsolutePath());
        MainActivity.fileObserver.startWatching();
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service Started!");
        Toast.makeText(this ,"Service Starting!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service Destroyed");
        Toast.makeText(this, "Service Destroyed!", Toast.LENGTH_SHORT).show();
    }

}
