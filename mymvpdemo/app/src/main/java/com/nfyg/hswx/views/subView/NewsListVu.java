package com.nfyg.hswx.views.subView;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.R;
import com.nfyg.hswx.activities.otherActivity;
import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.biz.bus.VCViewEventBus;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.biz.signals.SignalListener;
import com.nfyg.hswx.utils.SignalBuilder;
import com.nfyg.hswx.views.YmVu;
import com.nfyg.hswx.views.adapter.BasePresenterAdapter;
import com.nfyg.hswx.views.widget.SwipeRefreshLayout;

/**
 * Created by shengming.yang on 2015/11/27.
 */
public class NewsListVu extends YmVu {

    private  View view;

    private ListView listView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private BasePresenterAdapter mAdapter;
    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {
        this.view =   inflater.inflate(R.layout.news_recommend_list_ly,rootView,false);
        this.listView = (ListView) view.findViewById(R.id.list);
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
    }

    @Override
    public void initListener() {

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Engine.application.startActivity(new Intent(Engine.application, otherActivity.class));
            }
        });

        /**
        * 设置下拉刷新回调
        */
        swipeRefreshLayout
                .setOnPullRefreshListener(new SwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh(boolean isbackHome) {

                        if (isbackHome) {
                            swipeRefreshLayout.setRefreshing(false);
                            VCViewEventBus.getInstanceBus().callUpswipeLayoutDown();
                            return;
                        }

                        VCMainBus.getInstanceBus().getNewsData(SignalBuilder.build(new SignalListener() {
                            @Override
                            public void execute(Signal signal, Message msg) {
                                mAdapter.notifyDataSetChanged();
                            }
                        }, 0));

                        //TODO:刷新数据
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);

                            }
                        }, 2000);
                    }

                });


    }

    @Override
    public void onDestroyView() {

    }

    public  void setAdapter(BasePresenterAdapter adapter){
        this.mAdapter = adapter;
        this.listView.setAdapter(adapter);
    }

    @Override
    public View getView() {
        return view;
    }
}
