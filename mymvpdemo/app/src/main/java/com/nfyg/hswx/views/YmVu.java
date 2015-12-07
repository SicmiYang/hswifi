package com.nfyg.hswx.views;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nfyg.hswx.utils.common.LogUtil;

/**
 * Created by shengming.yang on 2015/11/27.
 */
public abstract class YmVu implements Vu{

    @Override
    public abstract void init(LayoutInflater inflater, ViewGroup rootView);

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
    public abstract View getView();


    private static final void logD(String msg) {
        if (!TextUtils.isEmpty(msg))
            LogUtil.logDebug("YmVu", msg);
    }

}
