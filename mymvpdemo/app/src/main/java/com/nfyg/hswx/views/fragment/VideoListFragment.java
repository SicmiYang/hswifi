package com.nfyg.hswx.views.fragment;

import android.os.Message;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.EngineOptions;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.biz.signals.SignalListener;
import com.nfyg.hswx.views.adapter.NewsVideoListAdapter;
import com.nfyg.hswx.views.subView.NewsListVu;

/**
 * Created by shengming.yang on 2015/11/30.
 */
public class VideoListFragment extends BasePresenterFragment<NewsListVu>{

    NewsVideoListAdapter adapter;
    @Override
    protected void onBindVu() {
        super.onBindVu();

        adapter =  new NewsVideoListAdapter();
        vu.setAdapter(adapter);
        initListener();

    }

     private void  initListener() {
         Engine.getInstance().viewBackSignal.addListener(new SignalListener() {
             @Override
             public void execute(Signal signal, Message msg) {
                 if (msg.getData().getString(EngineOptions.flag_flag).equals(EngineOptions.flag_notify_adapter)){
                     if (adapter!=null)
                     adapter.notifyDataSetChanged();
                 }
             }
         }, 0);

    }

    @Override
    protected Class<NewsListVu> getVuClass() {
        return NewsListVu.class;
    }
}
