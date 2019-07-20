package com.bt.searchartifact.adapter;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.bt.searchartifact.R;
import com.bt.searchartifact.bean.NetDataBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by CWJ on 2019/7/3.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class NetDataAdapter1 extends BaseQuickAdapter<NetDataBean, BaseViewHolder> {
private Activity activity;
    public NetDataAdapter1(@Nullable List<NetDataBean> data,Activity activity) {
        super(R.layout.itme_netdata1, data);
      this.activity=activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, NetDataBean item) {
            helper.setText(R.id.title,item.getTitle())
            .setText(R.id.filesize_tv,item.getFileSize())
            .setText(R.id.dwon_tv,"下载")
            .addOnClickListener(R.id.dwon_tv)
            .linkify(R.id.dwon_tv)
            ;
    }
}
