package com.nfyg.hswx.views.adapter;

import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.model.entity.VideoResources;
import com.nfyg.hswx.views.adapter.itemView.NewsListVideoItemVu;

/**
 * Created by shengming.yang on 2015/11/30.
 */
public class NewsVideoListAdapter extends BasePresenterAdapter<NewsListVideoItemVu>{
    @Override
    protected void onBindListItemVu(int position) {

        vu.setViewData(VCMainBus.getInstanceBus().videoResources.get(position),position);

    }

    @Override
    protected Class<NewsListVideoItemVu> getVuClass() {
        return NewsListVideoItemVu.class;
    }

    @Override
    protected void getTypeCode(int position) {

    }

    @Override
    public int getCount() {
        return VCMainBus.getInstanceBus().videoResources.size();
    }

    @Override
    public VideoResources getItem(int i) {
        return VCMainBus.getInstanceBus().videoResources.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
