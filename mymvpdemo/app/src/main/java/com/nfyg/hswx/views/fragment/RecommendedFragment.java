package com.nfyg.hswx.views.fragment;

import android.os.Bundle;

import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.views.adapter.NewListAdapter;
import com.nfyg.hswx.views.subView.NewsListVu;

/**
 * Created by shengming.yang on 2015/11/27.
 */
public class RecommendedFragment extends BasePresenterFragment<NewsListVu> {

    private NewListAdapter adapter;
    @Override
    protected void onBindVu() {
        super.onBindVu();

        adapter = new NewListAdapter(VCMainBus.getInstanceBus().test_news);
        vu.setAdapter(adapter);

        initListener();
    }


    private void   initListener() {

    }

    @Override
    protected Class<NewsListVu> getVuClass() {
        return NewsListVu.class;
    }

    public static RecommendedFragment newInstance(){
        return new RecommendedFragment();
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }
}
