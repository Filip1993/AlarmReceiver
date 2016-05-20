package com.filipkesteli.alarmreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    //konstanta intervala (1000 milisekundi puta 10 je 10 sekundi):
    //poteskoca je vremenski
    private static final int ALARM_INTERVAL = 1000 * 10;

    //i Broadcast Receiver se moze pozvati eksplicitno!
    //AlarmReceiver je sistemski servis -> mroa imati permission
    //imamo Intent objekt koji nam je kicker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAlarm();
    }

    private void initAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //EKSPLICITNI INTENT prema AlarmReceiver klasi:
        Intent intent = new Intent(this, AlarmReceiver.class);
        //flag -> na taj nacin
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void startAlarm(View view) {
        //Aj ti meni nesto pocni radit:
        Calendar calendar = Calendar.getInstance(); //kreni sada
        //radi svakih 10 sekundi nesto...
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                ALARM_INTERVAL,
                pendingIntent
        );
        Toast.makeText(MainActivity.this, R.string.alarm_startan, Toast.LENGTH_SHORT).show();
    }

    public void stopAlarm(View view) {
        alarmManager.cancel(pendingIntent);
    }
}
