package com.giri.test1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    VideoView video;
    ImageView rotate;
    Button download;
    boolean videoRotate = true;

    String url= "https://developers.google.com/training/images/tacoma_narrows.mp4";

    int a = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        toolbar = findViewById(R.id.toolbar);
        video = findViewById(R.id.video);

        rotate = findViewById(R.id.rotate);
        rotate.setOnClickListener(this);

        download = findViewById(R.id.download);
        download.setOnClickListener(this);

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                video.start();
            }
        });
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,a);



            }
        });

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle("Gallery");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,a);

    }

    @Override
    public void onClick(View v) {

        if ( v == rotate){
            if(videoRotate){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                videoRotate=false;

            }
            else{
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                videoRotate=true;
            }
        }
        if(v == download){

            Uri uri = Uri.parse(url);
            video.setVideoURI(uri);
            //video.start();
        }
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == a && data != null && data.getData()!= null)
            {
                Uri uri = data.getData();
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(video);

                video.setMediaController(mediaController);
                video.setVideoURI(uri);
                video.requestFocus();
                video.start();


            }

        }
    }

    @Override
    protected void onPause() {
        video.suspend();
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        video.stopPlayback();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        video.resume();
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
    }


