package com.example.skoth.simplefileobserver;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Environment;
import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    //public File sdCard;
    //public File imageFolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sdCard = Environment.getExternalStorageDirectory();
        //imageFolder = new File(sdCard.getAbsolutePath() + "");
        //String path = imageFolder.toString();


    }


    public void start(View view){

        Intent intent = new Intent(MainActivity.this , MyService.class);
        startService(intent);
    }

    public void stop(View view){

        Intent intent = new Intent(MainActivity.this , MyService.class);
        stopService(intent);
    }


}
