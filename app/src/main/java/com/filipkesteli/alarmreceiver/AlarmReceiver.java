package com.filipkesteli.alarmreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    //metoda kada broadcast receiver primmi info:
    @Override
    public void onReceive(Context context, Intent intent) {
        //kad me netko trzne, ja cu to dobit -> najjednostavnije:
        Toast.makeText(context, R.string.broadcast_received, Toast.LENGTH_SHORT).show();
    }
}
