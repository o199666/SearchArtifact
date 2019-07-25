package com.bt.searchartifact.config;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by CWJ on 2019/7/25.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "0c337a03f0", false);
    }
}
