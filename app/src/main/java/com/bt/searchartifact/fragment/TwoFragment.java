package com.bt.searchartifact.fragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bt.searchartifact.R;
import com.bt.searchartifact.adapter.LocalDataAdapter1;
import com.bt.searchartifact.adapter.NetDataAdapter1;
import com.bt.searchartifact.base.BaseFragment;
import com.bt.searchartifact.bean.LocalDataBean;
import com.bt.searchartifact.bean.NetDataBean;
import com.bt.searchartifact.jsoup.MYBT;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.bt.searchartifact.utils.FilesUtil.filterVideo;

/**
 * Created by CWJ on 2019/7/20.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class TwoFragment extends BaseFragment {
    private int indexs=1;
    public View view;
    private Button btn;
    private EditText editText;
    private RecyclerView recyclerView;
    private NetDataAdapter1 adapter1;
    private int listsize;
    private String key;
    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.two_fragment, container, false);
        btn=view.findViewById(R.id.search_btn);
        editText=view.findViewById(R.id.input_et);
        recyclerView=view.findViewById(R.id.recy_list2);
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (indexs==1){
                }else{
                    searchBt(key,indexs--);
                }
                //成功获取更多数据
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if (listsize<20){
                    Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                }else{
                    searchBt(key,indexs++);
                }
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "请输入你要查询的关键字", Toast.LENGTH_SHORT).show();
                }else{
                    key=editText.getText().toString().trim();
                    indexs=1;
                    searchBt(editText.getText().toString().trim(),indexs);
                }
            }
        });
        return view;
    }

    @Override
    protected void netData() {

    }

    @Override
    protected void initView() {

    }


    @SuppressLint("WrongConstant")
    public void setAdapter1(List<NetDataBean> list){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter1 = new NetDataAdapter1(list, getActivity());
        //获取布局文件
        View v=getLayoutInflater().inflate(R.layout.empty,null);
        //一句话为null
        adapter1.setEmptyView(v);
        adapter1.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter1.setUpFetchEnable(true);
        recyclerView.setAdapter(adapter1);
        adapter1.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {

            }
        });
//        adapter1.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                recyclerView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (listsize <20 ){
//                            //数据全部加载完毕
//                            adapter1.loadMoreEnd();
//                        }else{
//                            if (listsize==20){
//                                searchBt(key,indexs++);
//                                //成功获取更多数据
//                                adapter1.loadMoreComplete();
//                            }else{
//                                //获取更多数据失败
//                                adapter1.loadMoreFail();
//                                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        }
//                    }
//                },2000);
//            }
//        },recyclerView);
        adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //下载按钮 启动迅雷 并复制到剪切板
//                Intent intent=new Intent(this,);
                copy(list.get(position).getDownurl());
                Toast.makeText(getContext(), "已复制到剪切板", Toast.LENGTH_SHORT).show();
                if (checkPackInfo("com.xunlei.downloadprovider")){
                    Intent intent = getContext().getPackageManager().getLaunchIntentForPackage("com.xunlei.downloadprovider");
                        startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "请使用迅雷APP进行下载", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private boolean copy(String copyStr) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getContext().getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 搜索!
     * @param key
     */
    public void searchBt(String key,int index) {
        Observable.create(new ObservableOnSubscribe<List<NetDataBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NetDataBean>> emitter) throws Exception {
                emitter.onNext(MYBT.queryNetDataList(key,index));
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<NetDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(List<NetDataBean> NetDataBean) {
                        Log.e("2222", NetDataBean.size() + "");
                        listsize=NetDataBean.size();
                        setAdapter1(NetDataBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
