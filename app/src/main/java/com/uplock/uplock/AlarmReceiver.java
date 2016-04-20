package com.uplock.uplock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cesquer on 20/04/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receiver worked","Se establecio la Señal!");

        //crear intent para el servicio del ringtone
        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);

        //iniciar ringtone
        context.startService(serviceIntent);
    }
}
