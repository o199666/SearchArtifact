package com.bt.searchartifact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bt.searchartifact.R;
import com.bt.searchartifact.base.BaseActivity;
import com.bt.searchartifact.bean.Bt;
import com.bt.searchartifact.config.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.waps.AppConnect;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.bt.searchartifact.config.config.S_BT_TZ_1;

/**
 * Created by CWJ on 2019/7/24.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class LaunchActivity extends BaseActivity {
    private TextView text_time;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Bmob.initialize(this, "c392fc785f10ec48b7e1f8d708f28478");
        AppConnect.getInstance("bff0ba8f0022daf43595d39db941ffeb", "anzhi", this);
        AppConnect.getInstance(this).initPopAd(this);
        AppConnect.getInstance(this).showPopAd(this);
        text_time = findViewById(R.id.tv_time);
        getUril();
    }

    @Override
    public void initView() {
        daojishi(3);
    }

    @Override
    public void initDate() {

    }

    public void getUril() {
        BmobQuery<Bt> query = new BmobQuery<Bt>();
        //按照时间降序
        query.order("-createdAt");
        //执行查询，第一个参数为上下文，第二个参数为查找的回调
        query.findObjects(new FindListener<Bt>() {
            @Override
            public void done(List<Bt> list, BmobException e) {
                for (int i=0;i<list.size();i++){
                    Log.e("bomb-list",list.get(i).getName());
                }
                config.S_BT_TZ_1=list.get(3).getUri();
                config.S_BT_ZZ_1=list.get(2).getUri();
                config.S_BT_CILIMM_SOSO_1=list.get(1).getUri();
                config.S_BT_CILIMM_List=list.get(0).getUri();
            }
        });
    }


    CountDownTimer timer;

    public void daojishi(int time) {
        timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
//                liuzuoDaojishiTimeTv.setText(hemillisUntilFinisd/1000+"秒");
                text_time.setText(millisUntilFinished / 1000 + "后启动");
            }

            @Override
            public void onFinish() {//完毕就启动
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();//关闭当前页面
            }
        }.start();
    }
}
