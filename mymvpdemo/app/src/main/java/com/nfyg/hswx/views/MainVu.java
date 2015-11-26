package com.nfyg.hswx.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nfyg.hswx.R;


/**
 * Created by shengming.yang on 2015/11/13.
 */
public class MainVu implements Vu {

    private View view;

    private LinearLayout containerView;



    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {
        view = inflater.inflate(R.layout.activity_main,rootView,false);
        containerView = (LinearLayout) view.findViewById(R.id.container);
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
        return view;
    }

    public int getContainerId(){
        return containerView.getId();
    }
}
