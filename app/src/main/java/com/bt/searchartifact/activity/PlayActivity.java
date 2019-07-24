package com.bt.searchartifact.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bt.searchartifact.R;

import com.bt.searchartifact.base.BaseActivity;
import com.bumptech.glide.Glide;

import java.util.logging.Logger;

import cn.jzvd.JzvdStd;

public class PlayActivity extends BaseActivity {
    //节操浏览器
    public JzvdStd jzvdStd;
    private String paht;
    private String name;
    private String img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        jzvdStd = findViewById(R.id.jzvd);
        paht = getIntent().getStringExtra("getFilePtah");
        Log.d("-------------------",paht) ;
        Toast.makeText(this, paht, Toast.LENGTH_SHORT).show();
        name = getIntent().getStringExtra("getFileName");
        img = getIntent().getStringExtra("getFileImag");
        jzvdStd.setUp(paht
                , name);
        Glide.with(this).load(img).into(jzvdStd.thumbImageView);
        jzvdStd.setScreenFullscreen();
        jzvdStd.startVideo();


    }
    @Override
    public void initView() {

    }
    @Override
    public void initDate() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (jzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
