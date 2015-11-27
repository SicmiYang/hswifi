package com.nfyg.hswx.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shengming.yang on 2015/11/27.
 */
public abstract class YmVu implements Vu{

    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onStartView() {

    }

    @Override
    public void onResumeView() {

    }

    @Override
    public void onPauseView() {

    }

    @Override
    public void onStopView() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public View getView() {
        return null;
    }

}
