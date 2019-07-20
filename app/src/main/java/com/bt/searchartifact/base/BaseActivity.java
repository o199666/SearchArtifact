package com.bt.searchartifact.base;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

/**
 * Created by CWJ on 2019/7/2.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    /***封装toast对象**/
    private static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_PHONE_STATE
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.SEND_SMS)
                        /*以下为自定义提示语、按钮文字
                        .setDeniedMessage()
                        .setDeniedCloseBtn()
                        .setDeniedSettingBtn()
                        .setRationalMessage()
                        .setRationalBtn()*/
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                    }
                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(BaseActivity.this, permissions.toString() + "权限拒绝", Toast.LENGTH_SHORT).show();
                    }
                });
        initView();
        initDate();
    }

    /**
     * 所有初始化的空间放这个里面。
     * @param
     */
    public abstract void initView();

    /**
     * 数据初始化
     */
    public abstract void initDate();
    @Override
    public void onRestart() {
        super.onRestart();
    }
}
