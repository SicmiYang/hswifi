package com.nfyg.hswx.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shengming.yang on 2015/11/13.
 */
public interface Vu {

     void init(LayoutInflater inflater,ViewGroup rootView) ;

   // void init(LayoutInflater inflater,ViewGroup rootView,int viewType) ;

    void initListener();

    void onStartView();

    void onResumeView();

    void onPauseView();

    void onStopView();

    void  onDestroyView();

    View getView();

}
