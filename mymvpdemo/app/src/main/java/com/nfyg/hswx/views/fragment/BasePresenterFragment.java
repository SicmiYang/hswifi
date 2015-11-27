package com.nfyg.hswx.views.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nfyg.hswx.views.Vu;

import de.greenrobot.event.EventBus;

/**
 * Created by shengming.yang on 2015/11/16.
 */
public abstract class BasePresenterFragment<V extends Vu>  extends Fragment {

    protected V vu;
    protected EventBus bus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus = EventBus.getDefault();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            vu = getVuClass().newInstance();
            vu.init(inflater, container);
            vu.initListener();
            onBindVu();
            view = vu.getView();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        vu.onStartView();
    }

    @Override
    public final void onDestroyView() {
        onDestroyVu();
        vu = null;
        super.onDestroyView();
    }

    protected void onDestroyVu() {};

    @Override
    public final void onPause() {
        beforePause();
        vu.onPauseView();
        super.onPause();
    }

    protected void beforePause(){}

    @Override
    public final void onResume() {
        super.onResume();
        vu.onResumeView();
        afterResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        vu.onStopView();
    }

    protected void afterResume(){}

    protected void onBindVu(){};

    protected abstract Class<V> getVuClass();
}
