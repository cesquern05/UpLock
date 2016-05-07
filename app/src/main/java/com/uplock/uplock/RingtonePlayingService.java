package com.uplock.uplock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cesquer on 20/04/2016.
 */
public class RingtonePlayingService extends Service {

    //alarmMensaje = (TextView) findViewById(R.id.alarmMensaje);
    MediaPlayer mediaSong;
    int startId;
    boolean isRunning;
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

        //Convertimos los strings extra del intent
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



        //instrucciones if else
        //si la alarma no esta sonando y el usuario presiono "encender alarma": inicia la alarma
        if(!this.isRunning && startId == 1)
        {
            Log.e("La alarma no suena","La alarma se activara");
            //crea instancia de media player
            mediaSong = MediaPlayer.create(this,R.raw.deadpool);

            //Inicia ringtone
            mediaSong.start();
            Intent intentpop = new Intent(this, Pop.class);
            intentpop.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intentpop);


            this.isRunning=true;
            this.startId = 0;

        }
        //si la alarma esta sonando y el usuario presiona "apagar alarma": apaga alarma
        else if(this.isRunning && startId == 0)
        {
            Log.e("La alarma suena","La alarma se apaga");

            //apagar ringtone
            mediaSong.stop();
            mediaSong.reset();

            this.isRunning = false;
            this.startId = 0;

        }
        //Si el usuario presiona botones al azara
        //si no esta sonando la alarma y el usuario presiona "apagar alarma": no pasa nada
        else if(!this.isRunning && startId == 0)
        {
            Log.e("La alarma no suena","La alarma se apagara");
            this.isRunning = false;
            this.startId=0;
        }
        //si la alarma esta sonando y el usuario presiona "encender alarma": noo se hace nada
        else if(this.isRunning && startId == 1)
        {
            Log.e("La alarma suena","La alarma se activara");
            this.isRunning=true;
            this.startId = 1;
        }
        //cualquier otra cosa
        else
        {
            Log.e("otra cosa","caso extraño");
        }




        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Log.e("onDestroyCalled","La aplicacion cierra");
        super.onDestroy();
        this.isRunning=false;

    }


}
