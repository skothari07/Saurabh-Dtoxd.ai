package com.example.skoth.accelerogyro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity{

    TextView textX, textY, textZ, textAccX,textAccY,textAccZ;
    protected SensorManager sensorManager;
    Sensor sensor1 ,sensor2;
    private static DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void onStart(){
        super.onStart();
        sensorManager.registerListener(gyroListener, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(AcceleroListener, sensor2, SensorManager.SENSOR_DELAY_NORMAL);


    }


    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(AcceleroListener, sensor2, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
        sensorManager.unregisterListener(AcceleroListener);
    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) { }

        @SuppressLint("SetTextI18n")
        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textX.setText("X : " + df.format(x) + " rad/s");
            textY.setText("Y : " + df.format(y) + " rad/s");
            textZ.setText("Z : " + df.format(z) + " rad/s");
        }
    };

    public SensorEventListener AcceleroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        @SuppressLint("SetTextI18n")
        public void onSensorChanged(SensorEvent event) {

            float a = event.values[0];
            float b = event.values[1];
            float c = event.values[2];

            textAccX.setText("X : " + (int)a + " m/s");
            textAccY.setText("Y : " + (int)b + " m/s");
            textAccZ.setText("Z : " + (int)c + " m/s");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }


        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        textZ = findViewById(R.id.textZ);
        textAccX = findViewById(R.id.textAccX);
        textAccY = findViewById(R.id.textAccY);
        textAccZ = findViewById(R.id.textAccZ);

    }
}
