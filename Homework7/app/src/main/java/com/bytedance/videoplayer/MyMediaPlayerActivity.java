package com.bytedance.videoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class MyMediaPlayerActivity extends AppCompatActivity {
    VideoView player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        player = findViewById(R.id.player);

        if (getIntent().getData() != null) { // 调起播放器播放
            player.setVideoURI(getIntent().getData());
        } else { // 播放默认视频文件
            player.setVideoPath("android.resource://com.bytedance.videoplayer/" + R.raw.bytedance);
        }

        player.setMediaController(new MediaController(this)); // 通过MediaController方式
        player.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {// 横屏状态下隐藏actionBar
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
        }
    }
}
