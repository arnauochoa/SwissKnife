package com.ochoa.arnau.swissknife.Music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.ochoa.arnau.swissknife.R;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton buttonPlay, buttonPause, buttonStop;

    Boolean playing = false;

    MusicService bService;
    boolean bound = false;
    Intent intent;

    private ServiceConnection connection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            MusicService.MyBinder mBinder = (MusicService.MyBinder) binder;
            bService = mBinder.getService();
            bound = true;
            Log.d("service", "onServiceConnected: SERVICE CONNECTED");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0){
            bound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        buttonPlay = (ImageButton) findViewById(R.id.playButton);
        buttonPlay.setOnClickListener(this);

        buttonPause = (ImageButton) findViewById(R.id.pauseButton);
        buttonPause.setOnClickListener(this);

        buttonStop = (ImageButton) findViewById(R.id.stopButton);
        buttonStop.setOnClickListener(this);

        intent = new Intent(MusicActivity.this, MusicService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playButton:
                if(!playing && bound){
                    bService.play();
                    playing = true;
                }
                break;
            case R.id.pauseButton:
                if(playing && bound){
                    bService.pause();
                    playing = false;
                }
                break;
            case R.id.stopButton:
                if(playing && bound) {
                    bService.stop();
                    playing = false;
                }
                break;
        }
    }
}
