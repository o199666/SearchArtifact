package com.bt.searchartifact.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bt.searchartifact.R;
import com.bt.searchartifact.activity.PlayActivity;
import com.bt.searchartifact.adapter.LocalDataAdapter1;
import com.bt.searchartifact.base.BaseFragment;
import com.bt.searchartifact.bean.LocalDataBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.bt.searchartifact.utils.FilesUtil.filterVideo;
import static com.bt.searchartifact.utils.FilesUtil.getList;

/**
 * Created by CWJ on 2019/7/20.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class OneFragment extends BaseFragment {
    private View view;
    private TextView title;
    File sd = new File(Environment.getExternalStorageDirectory().getPath());
    List<LocalDataBean> localDataBeans = new ArrayList<>();
    private LocalDataAdapter1 adapter1;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.one_fragment, container, false);
        recyclerView = view.findViewById(R.id.recy_list);
        return view;
    }

    @Override
    protected void netData() {
        searchLocalFiles();
    }

    @Override
    protected void initView() {

        TextView textView = getActivity().findViewById(R.id.title_tv);
        textView.setText("本地视频");
    }

    @SuppressLint("WrongConstant")
    public void setView(List<LocalDataBean> localDataBeans) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter1 = new LocalDataAdapter1(localDataBeans, getContext());
        //获取布局文件
        View v = getLayoutInflater().inflate(R.layout.empty, null);
        //一句话为null
        adapter1.setEmptyView(v);
        adapter1.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter1);
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), PlayActivity.class);
                intent.putExtra("getFileName", localDataBeans.get(position).getFileName());
                intent.putExtra("getFilePtah", localDataBeans.get(position).getFilePtah());
                intent.putExtra("getFileImag", localDataBeans.get(position).getFileImag());
                startActivity(intent);
            }
        });


    }

    public void searchLocalFiles() {
        Observable.create(new ObservableOnSubscribe<List<LocalDataBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<LocalDataBean>> emitter) throws Exception {
                localDataBeans = filterVideo(getList(getContext()));
                emitter.onNext(localDataBeans);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<LocalDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LocalDataBean> localDataBeans) {
                        Log.e("aa", localDataBeans.size() + "");
                        setView(localDataBeans);
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
