package com.nfyg.hswx.views.subView;

import android.view.LayoutInflater;
import android.view.View;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.R;

/**
 * Created by shengming.yang on 2015/11/25.
 */
public class SwipeHeaderView {

    public static View createHeaderView() {
        View headerView = LayoutInflater.from(Engine.application)
                .inflate(R.layout.swipe_header_view, null);
        return headerView;
    }
}
