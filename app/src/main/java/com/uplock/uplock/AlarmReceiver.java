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

        //obtener extra string de intent
        String obtenerExtraString = intent.getExtras().getString("extra");
        Log.e("String Extra Obtenido: ",obtenerExtraString);

        //crear intent para el servicio del ringtone
        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);

        //pasar extra string de MAn activity a Ringtone Playing service
        serviceIntent.putExtra("extra",obtenerExtraString);

        //iniciar ringtone
        context.startService(serviceIntent);
    }
}
