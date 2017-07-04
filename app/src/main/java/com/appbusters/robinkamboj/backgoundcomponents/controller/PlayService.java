package com.appbusters.robinkamboj.backgoundcomponents.controller;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.appbusters.robinkamboj.backgoundcomponents.R;

public class PlayService extends Service{

    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service onCreate() called", Toast.LENGTH_SHORT).show();
        player = MediaPlayer.create(this, R.raw.song);
        player.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service onStartCommand() called", Toast.LENGTH_SHORT).show();
        player.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service onDestroy() called", Toast.LENGTH_SHORT).show();
        player.stop();
    }
}
