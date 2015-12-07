package com.nfyg.hswx.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shengming.yang on 2015/12/1.
 */
public class TestNewsEntiy implements Serializable{

    public String id;

    public String title;

    public String date;

    public int seq;

    public ArrayList<coverImages> cover_images ;


    public class coverImages implements Serializable{

        public String  url;

        public String path;

        public int width;

        public int height;

    }


}



