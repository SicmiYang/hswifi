package com.nfyg.hswx.views.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.nfyg.hswx.model.entity.TestNewsEntiy;
import com.nfyg.hswx.views.adapter.itemView.NewsListItemVu;

import java.util.List;

/**
 * Created by shengming.yang on 2015/11/16.
 */
public  class NewListAdapter extends BasePresenterAdapter<NewsListItemVu> {

   List<TestNewsEntiy> news ;


    private NewListAdapter(){};

    public NewListAdapter(List<TestNewsEntiy> resoures){
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
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
