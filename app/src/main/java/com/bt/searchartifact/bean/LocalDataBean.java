package com.bt.searchartifact.bean;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

public class LocalDataBean implements Serializable {
    private String fileName;
    private String fileTime;
    private long fileSize;
    private String filePtah;
    private String fileImag;

    public String getFileImag() {
        return fileImag;
    }

    public void setFileImag(String fileImag) {
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
        this.fileTime =  fileTime;
    }


    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePtah() {
        return filePtah;
    }
    public void setFilePtah(String filePtah) {
        this.filePtah = filePtah;
    }
}
