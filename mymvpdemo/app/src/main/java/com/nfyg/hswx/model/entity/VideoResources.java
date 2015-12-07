package com.nfyg.hswx.model.entity;

import java.io.Serializable;

/**
 * Created by shengming.yang on 2015/11/30.
 */
public class VideoResources implements Serializable {

    private String videoTitle;

    private String  videoUrl;

    public  VideoResources(String videoTitle,String videoUrl){
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
    }


    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
