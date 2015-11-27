package com.nfyg.hswx.views.subView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nfyg.hswx.R;
import com.nfyg.hswx.biz.bus.VCViewEventBus;
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
    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {
        this.view =   inflater.inflate(R.layout.news_recommend_list_ly,rootView,false);
        this.listView = (ListView) view.findViewById(R.id.list);
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
    }

    @Override
    public void initListener() {

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
        this.listView.setAdapter(adapter);
    }

    @Override
    public View getView() {
        return view;
    }
}
