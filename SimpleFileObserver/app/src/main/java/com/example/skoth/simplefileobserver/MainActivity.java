package com.example.skoth.simplefileobserver;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


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
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);
        listView.setAdapter(arrayAdapter);
        Log.i("Dekhooo", String.valueOf(myArrayList));

        Intent intent = new Intent(this, MyService.class);
        startService(intent);



    }



}
