package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
//import android.util.Log;
import android.view.View;
//import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

//import HttpClient.HttpClient;

public class MainActivity extends AppCompatActivity {
   // TextView tv;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv = (TextView) findViewById(R.id.text);
        StrictMode.enableDefaults();

        timePicker = (TimePicker) findViewById(R.id.timepicker);

        findViewById(R.id.buttonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                if(Build.VERSION.SDK_INT>=23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH), timePicker.getHour(),
                            timePicker.getMinute(), 0

                    );
                }else {
                    calendar.set(
                            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH), timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(), 0

                    );
                }
                setAlarm(calendar.getTimeInMillis());


            }
        });
    }

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MyAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC, timeInMillis,
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alaram is set ",Toast.LENGTH_SHORT).show();


    }


}
