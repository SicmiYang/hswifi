package com.nfyg.hswx.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nfyg.hswx.views.Vu;


/**
 * Created by shengming.yang on 2015/11/16.
 */
public abstract class BasePresenterAdapter<V extends Vu> extends BaseAdapter {

    protected V vu;

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            try {
                vu = (V) getVuClass().newInstance();

                getTypeCode(position);
                vu.init(inflater, parent);
                vu.initListener();
                convertView = vu.getView();
                convertView.setTag(vu);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            vu = (V) convertView.getTag();
        }
        if(convertView!=null) {
            onBindListItemVu(position);
        }
        return convertView;
    }


    protected abstract void onBindListItemVu(int position);

    protected abstract Class<V> getVuClass();

    protected abstract  void  getTypeCode(int position);


}

