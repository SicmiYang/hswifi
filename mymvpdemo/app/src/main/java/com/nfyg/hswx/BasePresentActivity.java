package com.nfyg.hswx;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.nfyg.hswx.views.Vu;

import de.greenrobot.event.EventBus;

/**
 * Created by shengming.yang on 2015/11/13.
 */
public abstract class BasePresentActivity<V extends Vu> extends Activity {

    protected FragmentManager fm;
    protected V vu;
    protected EventBus envetBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fm = getFragmentManager();
        envetBus = EventBus.getDefault();

        try {
            vu = getVClass().newInstance();
            vu.init(getLayoutInflater(),null);
            setContentView(vu.getView());
            onBindVu();

            vu.initListener();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        vu.onStartView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vu.onResumeView();
        afterResume();
    }

    protected  void afterResume(){};

    protected  void befrorePuse(){};

    @Override
    protected void onPause() {
        befrorePuse();
        super.onPause();
        vu.onPauseView();
    }


    @Override
    protected void onStop() {
        super.onStop();
        vu.onStopView();
    }

    @Override
    protected void onDestroy() {
        onUnBindVu();
        super.onDestroy();
        vu.onDestroyView();
    }

    @Override
    public void onBackPressed() {

        if (!handleBackPressed())
        super.onBackPressed();
    }

    protected  boolean  handleBackPressed(){return false;}

    protected abstract Class<V> getVClass();

    protected  void onBindVu(){};

    protected  void onUnBindVu(){};


}
