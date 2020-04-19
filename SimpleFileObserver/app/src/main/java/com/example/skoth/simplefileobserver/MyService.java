package com.example.skoth.simplefileobserver;

import android.app.Service;
import android.content.Intent;
import android.os.FileObserver;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by skoth on 18-04-2020.
 */

public class MyService extends Service {

    String path = "/Phone/WhatsApp/Media/WhatsApp Images/";

    int mask = FileObserver.DELETE;
    MyObserver observer = new MyObserver(path, mask){
        @Override
        public void onEvent(int event, @Nullable String path) {
            super.onEvent(event, path);
            Log.i("YEHHHHHHHH DEKHOOOOOOOO" , "Enter Hua");
            if(path != null){
                Log.e("YESSSSSSSSS", String.valueOf(event) );

                Toast.makeText(MyService.this,"Hogsys" , Toast.LENGTH_LONG).show();
            }

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service Created!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Started!", Toast.LENGTH_LONG).show();
        Log.i("PATH TO WATCH" , path);
        Toast.makeText(this,"Watching" , Toast.LENGTH_SHORT).show();
        observer.startWatching();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed!", Toast.LENGTH_SHORT).show();
    }
}
