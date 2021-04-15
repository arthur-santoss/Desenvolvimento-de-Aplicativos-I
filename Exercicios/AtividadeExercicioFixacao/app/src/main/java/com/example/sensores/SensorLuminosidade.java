package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SensorLuminosidade extends AppCompatActivity {
    private TextView visual;
    SensorManager sm;
    SensorEventListener listener;
    Sensor luz;
    Button btnVoltarP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luminosidade);

        visual = findViewById(R.id.visual);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        luz = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        btnVoltarP = findViewById(R.id.btnVoltarP);


        btnVoltarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarP();
            }
        });

        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event)
            {
                visual.setText(String.valueOf(event.values[0]));
                int grayShade = (int) event.values[0];

                if(grayShade <= 0){
                    abrirActivity4();
                };

                visual.setTextColor(Color.rgb( 255 - grayShade, 255 - grayShade, 255 - grayShade ) );
                visual.setBackgroundColor(Color.rgb(grayShade,grayShade,grayShade));

            }

           @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sm.registerListener(listener,luz,SensorManager.SENSOR_DELAY_FASTEST);

    }

    private void abrirActivity4(){
        Intent janelaAbrirActivity4 = new Intent(this, Activity4.class);
        startActivity(janelaAbrirActivity4);

    }


    public void voltarP(){
        Intent janelaV = new Intent(this, MainActivity.class);
        startActivity(janelaV);
    }
    @Override
    protected void onPause() {
        sm.unregisterListener(listener,luz);
        super.onPause();
    }
}