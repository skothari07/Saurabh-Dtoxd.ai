package com.example.skoth.simplefileobserver;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public static MyObserver fileObserver;
    static ArrayList <String> myArrayList = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        verifyPermissions();
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }


    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);
        listView.setAdapter(arrayAdapter);
        Log.i("MY LIST", String.valueOf(myArrayList));
    }

    private void verifyPermissions(){
        Log.i("PERMISSIONS","verifyPermissions(): Asking for user permissions");

        String [] Permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Permissions[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(),Permissions[1]) == PackageManager.PERMISSION_GRANTED ){
            Toast.makeText(MainActivity.this,"Permission granted", Toast.LENGTH_SHORT).show();

        }else{
            ActivityCompat.requestPermissions(this,Permissions,1);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this,"Permission granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
