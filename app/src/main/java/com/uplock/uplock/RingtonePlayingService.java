package com.uplock.uplock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cesquer on 20/04/2016.
 */
public class RingtonePlayingService extends Service {

    MediaPlayer mediaSong;
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //Obtener los valores de los string extra
        String estatus= intent.getExtras().getString("extra");

        Log.e("Estado del RingTone", String.valueOf(estatus));

        assert estatus!= null;
        if (estatus.equals("alarmON")){
            startId = 1;
        }
        else if(estatus.equals("alarmON")){
            startId = 0;
        }
        else{
            startId = 0;
        }

        //crea instancia de media player
        mediaSong = MediaPlayer.create(this,R.raw.deadpool);
        mediaSong.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "On Destroyed Called", Toast.LENGTH_SHORT).show();
    }


}
