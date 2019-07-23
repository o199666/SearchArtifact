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
     *      *  https://btzzii.me/bt/A/time-3.html bt种子
     *      *  https://bttutu.xyz/tu/%E8%8B%8D%E4%BA%95%E7%A9%BA/time-1.html BT兔子
     *      *  http://btbit.icu/list/%E6%96%B0%E9%87%91%E7%93%B6%E6%A2%85%E7%9C%9F%E4%BA%BA%E7%89%88/1-1-0.html btbit
     *      *  http://www.btfx.net/search-index-keyword-%E6%AD%BB%E7%A5%9E.html BT分享
     *      *  http://bobobt.online/search/%20%E6%B3%A2%E5%A4%9A%E9%87%8E%E7%BB%93%E8%A1%A3.html 波波分享
     *      *  https://btdb.cilimm.xyz/s/%E8%8B%8D%E4%BA%95%E7%A9%BA 磁力搜索
     *      *   https://6.cilimm.xyz/s/%E8%8B%8D%E4%BA%95%E7%A9%BA"
     *      *  https://btbt.cilimm.xyz/list/%E8%8B%8D%E4%BA%95%E7%A9%BA
     */


    /**
     * 获取查询列表 7.22 http://www.btawt.com/
     * BT蚂蚁
     */
    public static List<NetDataBean> queryNetDataList(String keyword, int index) {
        List<NetDataBean> list = new ArrayList<>();
        String url1 = "http://www.btawt.com/search/" + keyword + "-first-asc-" + index;
        Log.e("lianjie:--", url1);
        try {
            Document document = Jsoup.connect(url1)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    //如果是这种方式，这里务必带上
                    .header("Connection", "close")
                    //超时时间
                    .timeout(8000)
                    .get();
            Element wall = document.getElementById("wall");
            //获取标题
            Elements lie = wall.getElementsByAttributeValue("class", "search-item");
//            Elements lianjie = wall.getElementsByAttributeValue("class", "item-bar");
            //打印出标题
            // String s="Weapon.mp3";
            //s=s.substring(0, s.lastIndexOf('.'))
            for (int i = 0; i < lie.size(); i++) {
               String name= wall.getElementsByAttributeValue("class", "search-item").get(i).select("a[href]").text();
                //获取大小
                String size =  wall.getElementsByAttributeValue("class", "item-bar").get(i).select("span").get(3).text();
                //磁力链接
                String uri=   wall.getElementsByAttributeValue("class", "item-bar").get(i).select("a[href]").get(0).select("a").attr("href");
                  name= name.substring(0,name.length()-9);
                list.add(new NetDataBean(name,uri,size));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * BT兔子 https://bttutu.xyz/tu/%E8%8B%8D%E4%BA%95%E7%A9%BA/time-1.html
     *
     * @param keyword
     * @param index
     * @return
     */
    public static List<NetDataBean> queryNetDataListTuzzz(String keyword, int index) {
        List<NetDataBean> list = new ArrayList<>();
        String url1 = "https://bttutu.xyz/tu/" + keyword + "/time-" + index + ".html";

        Log.e("lianjie:--", url1);
        try {
            Document document = Jsoup.connect(url1)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    //如果是这种方式，这里务必带上
                    .header("Connection", "close")
                    //超时时间
                    .timeout(8000)
                    .get();
            Element wall = document.getElementById("wall");
            //获取标题
            Elements lie = wall.getElementsByAttributeValue("class", "cili-item");
            //打印出标题
            for (int i = 0; i < lie.size(); i++) {
                String name = lie.get(i).select("a[href]").text();
                String size = wall.getElementsByAttributeValue("class", "item-bar").get(i).select("span").get(2).text();
                String uri = wall.getElementsByAttributeValue("class", "item-bar").get(i).select("a[href]").get(0).select("a").attr("href");
                Log.d(TAG, "标题x: " + name);
                Log.d(TAG, "大小x: " + size);
                Log.d(TAG, "clix: " + uri);
                name= name.substring(0,name.length()-9);

                list.add(new NetDataBean(name,uri,size));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //BT种子
    public static List<NetDataBean> queryNetDataListBtzz(String keyword, int index) {
        List<NetDataBean> list = new ArrayList<>();
        String url1 = "https://btzzii.me/bt/" + keyword + "/time-" + index + ".html";

        Log.e("lianjie:--", url1);
        try {
            Document document = Jsoup.connect(url1)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    //如果是这种方式，这里务必带上
                    .header("Connection", "close")
                    //超时时间
                    .timeout(8000)
                    .get();
            Element wall = document.getElementById("wall");
            //获取标题
            Elements lie = wall.getElementsByAttributeValue("class", "cili-item");
            //打印出标题
            for (int i = 0; i < lie.size(); i++) {
                String name = lie.get(i).select("a[href]").text();
                String size = wall.getElementsByAttributeValue("class", "item-bar").get(i).select("span").get(2).text();
                String uri = wall.getElementsByAttributeValue("class", "item-bar").get(i).select("a[href]").get(0).select("a").attr("href");
                Log.d(TAG, "标题x: " + name);
                Log.d(TAG, "大小x: " + size);
                Log.d(TAG, "clix: " + uri);
                name= name.substring(0,name.length()-9);

                list.add(new NetDataBean(name,uri,size));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    // https://btdb.cilimm.xyz/s/
    //s
    public static List<NetDataBean> queryNetDataListCiLi(String keyword, int index) {
        List<NetDataBean> list = new ArrayList<>();
        String url1 = "https://btdb.cilimm.xyz/s/" + keyword + "/time-" + index + ".html";
        Log.e("lianjie:--", url1);
        try {
            Document document = Jsoup.connect(url1)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    //如果是这种方式，这里务必带上
                    .header("Connection", "close")
                    //超时时间
                    .timeout(8000)
                    .get();
            Element wall = document.getElementById("wall");
            //获取标题
            Elements lie = wall.getElementsByAttributeValue("class", "cili-item");
            //打印出标题
            for (int i = 0; i < lie.size(); i++) {
                String name = lie.get(i).select("a[href]").text();
                String size = wall.getElementsByAttributeValue("class", "item-bar").get(i).select("span").get(2).text();
                String uri = wall.getElementsByAttributeValue("class", "item-bar").get(i).select("a[href]").get(0).select("a").attr("href");
                Log.d(TAG, "标题x: " + name);
                Log.d(TAG, "大小x: " + size);
                Log.d(TAG, "clix: " + uri);
                name= name.substring(0,name.length()-9);

                list.add(new NetDataBean(name,uri,size));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * https://btbt.cilimm.xyz/list/%E8%8B%8D%E4%BA%95%E7%A9%BA/1/time_d
     *list
     * @param keyword
     * @param index
     * @return
     */
    public static List<NetDataBean> queryNetDataListCiLiist(String keyword, int index) {
        List<NetDataBean> list = new ArrayList<>();
        String url1 = "https://btbt.cilimm.xyz/list/" + keyword + "/" + index + "/time_d";
        Log.e("lianjie:--", url1);
        try {
            Document document = Jsoup.connect(url1)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    //如果是这种方式，这里务必带上
                    .header("Connection", "close")
                    //超时时间
                    .timeout(8000)
                    .get();
            Element mlist = document.getElementById("container");
            Elements lis = mlist.getElementsByTag("li");
            Log.d(TAG, "lis: " + lis.size());
            //打印出标题
            for (int i = 0; i < lis.size(); i++) {
                String title = lis.get(i).getElementsByClass("T1").get(0).select("a[href]").text();
                String size = lis.get(i).getElementsByClass("BotInfo").select("span").get(0).text();
                String cili = lis.get(i).getElementsByClass("dInfo").get(0).select("a[href]").get(0).select("a").attr("href");
                Log.d(TAG, "标题x: " + title);
                Log.d(TAG, "大小x: " + size);
                Log.d(TAG, "clix: " + cili);

                list.add(new NetDataBean(title, cili, size));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
