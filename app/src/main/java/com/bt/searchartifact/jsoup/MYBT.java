package com.bt.searchartifact.jsoup;

import android.util.Log;

import com.bt.searchartifact.bean.NetDataBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by CWJ on 2019/7/20.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class MYBT {
    /**
     * 获取查询列表
     */
    public static List<NetDataBean> queryNetDataList(String keyword)   {
        List<NetDataBean> list = null;
        String url1 = "http://www.btaxt.com/search/"+keyword+"-first-asc-1";
        Log.e("lianjie:--",url1);
        try {
            Document document = Jsoup.connect(url1)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    //如果是这种方式，这里务必带上
                    .header("Connection", "close")
                    //超时时间
                    .timeout(8000)
                    .get();
            Element wall=document.getElementById("wall");
            //获取标题
            Elements lie=wall.getElementsByAttributeValue("class","search-item");
            Elements lianjie=wall.getElementsByAttributeValue("class","item-bar");
            Elements xiayiye=wall.getElementsByAttributeValue("class","bottom-pager");
            for (int i = 0; i <xiayiye.size() ; i++) {
                Log.d(TAG, "下一页: "+lianjie.get(i).select("a[href]").text());
            }
            //打印出标题
            for (int i = 0; i < lie.size(); i++) {
                Log.d(TAG, "queryNetDataList: "+lie.get(i).select("a[href]").text());
                //获取大小
                Log.d(TAG, "lianjie: "+lianjie.get(i).select("span").get(3).text());
                //磁力链接
                Log.d(TAG, "lianjie: "+lianjie.get(i).select("a[href]").get(0).select("a").attr("href"));
                list.add(new NetDataBean(lie.get(i).select("a[href]").text(),lianjie.get(i).select("a[href]").get(0).select("a").attr("href"),lianjie.get(i).select("span").get(3).text()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
