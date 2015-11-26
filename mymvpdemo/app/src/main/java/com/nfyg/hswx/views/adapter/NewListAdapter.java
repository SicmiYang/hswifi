package com.nfyg.hswx.views.adapter;

import com.nfyg.hswx.model.entity.NewsEntity;
import com.nfyg.hswx.views.adapter.itemView.NewsListItemVu;

import java.util.List;

/**
 * Created by shengming.yang on 2015/11/16.
 */
public  class NewListAdapter extends BasePresenterAdapter<NewsListItemVu> {

   List<NewsEntity> news ;


    private NewListAdapter(){};

    public NewListAdapter(List<NewsEntity> resoures){
        super();
        news = resoures;
    }

    @Override
    protected void onBindListItemVu(int position) {
        vu.setViewData(news.get(position));
    }
    @Override
    protected Class getVuClass() {
        return NewsListItemVu.class;
    }

    @Override
    protected void getTypeCode(int position) {
        int itemViewType = getItemViewType(position);
        vu.setTypeCode(itemViewType);
    }


    @Override
    public int getCount() {
        return news == null ? 0 : news.size();
    }

    @Override
    public Object getItem(int i) {
        return news.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return news.get(position).getTypeCode();
    }
}
