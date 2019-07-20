package com.bt.searchartifact.bean;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

import java.io.Serializable;

public class LocalDataBean implements Serializable {
    private String fileName;
    private String fileTime;
    private String fileSize;
    private String filePtah;
    private Bitmap fileImag;

    public Bitmap getFileImag() {
        return fileImag;
    }

    public void setFileImag(Bitmap fileImag) {
        this.fileImag = fileImag;
    }

    public LocalDataBean(String fileName, String fileTime, String fileSize, String filePtah, Bitmap fileImag) {
        this.fileName = fileName;
        this.fileTime = fileTime;
        this.fileSize = fileSize;
        this.filePtah = filePtah;
        this.fileImag = fileImag;
    }


    /***
     * 根据播放路径设置缩略图
     * @param filePath 视频资源的路径
     * @return 返回缩略图的Bitmap对象
     */
    public static Bitmap getVideoThumbNail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePtah() {
        return filePtah;
    }
    public void setFilePtah(String filePtah) {
        this.filePtah = filePtah;
    }
}
