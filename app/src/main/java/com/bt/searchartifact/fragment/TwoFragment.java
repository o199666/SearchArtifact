package com.bt.searchartifact.fragment;

import android.annotation.SuppressLint;
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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.bt.searchartifact.utils.FilesUtil.filterVideo;
import static com.bt.searchartifact.utils.FilesUtil.getVideoFile;

/**
 * Created by CWJ on 2019/7/20.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class TwoFragment extends BaseFragment {
    public View view;
    private Button btn;
    private EditText editText;
    private RecyclerView recyclerView;
    private NetDataAdapter1 adapter1;
    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.two_fragment, container, false);
        btn=view.findViewById(R.id.search_btn);
        editText=view.findViewById(R.id.input_et);
        recyclerView=view.findViewById(R.id.recy_list2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "请输入你要查询的关键字", Toast.LENGTH_SHORT).show();
                }else{
                    searchBt(editText.getText().toString().trim());
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
        recyclerView.setAdapter(adapter1);
    }
    public void searchBt(String key) {
        Observable.create(new ObservableOnSubscribe<List<NetDataBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NetDataBean>> emitter) throws Exception {
                emitter.onNext(MYBT.queryNetDataList(key));
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
