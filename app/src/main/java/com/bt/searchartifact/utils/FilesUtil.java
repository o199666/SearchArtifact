package com.bt.searchartifact.utils;

import android.util.Log;

import com.bt.searchartifact.bean.LocalDataBean;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import static com.bt.searchartifact.bean.LocalDataBean.getVideoThumbNail;

public   class FilesUtil {

    /**
     * 获取视频文件
     * @param list
     * @param file
     * @return
     */
    public static List<LocalDataBean> getVideoFile(final List<LocalDataBean> list, File file) {
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp4")
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".ts")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".m3u8")
                            || name.equalsIgnoreCase(".3gpp")
                            || name.equalsIgnoreCase(".3gpp2")
                            || name.equalsIgnoreCase(".mkv")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".rm")
                            || name.equalsIgnoreCase(".asf")
                            || name.equalsIgnoreCase(".ram")
                            || name.equalsIgnoreCase(".mpg")
                            || name.equalsIgnoreCase(".v8")
                            || name.equalsIgnoreCase(".swf")
                            || name.equalsIgnoreCase(".m2v")
                            || name.equalsIgnoreCase(".asx")
                            || name.equalsIgnoreCase(".ra")
                            || name.equalsIgnoreCase(".ndivx")
                            || name.equalsIgnoreCase(".xvid")) {
                        LocalDataBean video = new LocalDataBean(file.getName(),file.getName(),file.getAbsolutePath(),file.getAbsolutePath(),getVideoThumbNail(file.getAbsolutePath()));
                        file.getUsableSpace();
                        Log.i("tga","name"+video.getFilePtah());
                        list.add(video);
                        return true;
                    }
                    //判断是不是目录
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });

        return list;
    }

    /**10M=10485760 b,小于10m的过滤掉
     * 过滤视频文件
     * @param videoInfos
     * @return
     */
    public static List<LocalDataBean> filterVideo(List<LocalDataBean> videoInfos){
        List<LocalDataBean> newVideos=new ArrayList<LocalDataBean>();
        for(LocalDataBean localDataBean:videoInfos){
            File f=new File(localDataBean.getFilePtah());
            if(f.exists()&&f.isFile()&&f.length()>10485760){
                newVideos.add(localDataBean);
                Log.i("TGA","文件大小"+f.length());
            }else {
                Log.i("TGA","文件太小或者不存在");
            }
        }
        return newVideos;
    }




}


