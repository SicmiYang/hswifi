package com.nfyg.hswx.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shengming.yang on 2015/11/25.
 */
public interface VuItem {

     void init(LayoutInflater inflater,ViewGroup rootView,int viewType) ;

    void initListener();

    void onStartView();

    void onResumeView();

    void onPauseView();

    void onStopView();

    void  onDestroyView();

    View getView();
}
