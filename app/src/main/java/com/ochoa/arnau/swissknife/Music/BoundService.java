package com.ochoa.arnau.swissknife.Music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.ochoa.arnau.swissknife.R;

import java.io.IOException;

/**
 * Created by arnau on 07/02/2017.
 */
public class BoundService extends Service {
    private final IBinder binder = new MyBinder();

    MediaPlayer mediaPlayer;

    public class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        mediaPlayer = MediaPlayer.create(this, R.raw.layla);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
        return binder;
    }

    public void play(){
        mediaPlayer.start();
    }

    public void pause(){
        try {
            mediaPlayer.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            mediaPlayer.stop();
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
