package com.bt.searchartifact.bean;

/**
 * Created by CWJ on 2019/7/3.
 * Author:Chen
 * Email:1181620038@qq.com
 * Ver:1
 * DEC:
 */
public class NetDataBean {
    /*
     * 标题
     */
    private String title;
    /**
     * 磁力链接
     */
    private String downurl;

    /**
     * 文件大小
     */
    private String fileSize;

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return downurl;
    }

    public void setUrl(String downurl) {
        this.downurl = downurl;
    }

    public NetDataBean(String title, String downurl, String fileSize) {
        this.title = title;
        this.downurl = downurl;
        this.fileSize = fileSize;
    }
}
