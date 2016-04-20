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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

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
