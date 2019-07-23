package com.bt.searchartifact.jsoup;

import android.content.Context;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.bt.searchartifact.bean.NetDataBean;
import com.bt.searchartifact.config.config;

import java.util.List;

import static com.bt.searchartifact.jsoup.MYBT.queryNetDataList;
import static com.bt.searchartifact.jsoup.MYBT.queryNetDataListBtzz;
import static com.bt.searchartifact.jsoup.MYBT.queryNetDataListCiLi;
import static com.bt.searchartifact.jsoup.MYBT.queryNetDataListCiLiist;
import static com.bt.searchartifact.jsoup.MYBT.queryNetDataListTuzzz;

/**
 * Created by CWJ on 2019/7/23.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class WhileGet {
    public static List<NetDataBean> whileSearch(String keyword, int index ) {
        List<NetDataBean> list = null;
        config.INDEX_FANY=10;
        //兔子
        list = queryNetDataListTuzzz(keyword, index);
        if (list.size() == 0) {
            //BT种子
            list = queryNetDataListBtzz(keyword, index);
            if (list.size() == 0) {
                //磁力s
                list = queryNetDataListCiLi(keyword, index);
                if (list.size() == 0) {
                    //磁力 list
                    list = queryNetDataListCiLiist(keyword, index);
                    if (queryNetDataListCiLiist(keyword, index).size() == 0) {
                        //BT蚂蚁
                        list = queryNetDataList(keyword, index);
                        //查询后，20页的
                        config.INDEX_FANY=20;
                        if (list.size() == 0) {

                        } else {
                            return list;

                        }
                    } else {
                        return list;

                    }
                } else {
                    return list;

                }
            } else {
                return list;

            }
        } else {
            return list;

        }

        return list;
    }
}
