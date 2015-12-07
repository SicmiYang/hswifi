package com.nfyg.hswx;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.nfyg.hswx.views.Vu;

/**
 * Created by shengming.yang on 2015/11/13.
 */
public abstract class BasePresentActivity<V extends Vu> extends FragmentActivity {

    protected FragmentManager fm;
    protected V vu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(0xffff00);
//            window.setNavigationBarColor(0xffff00);
//        }

        fm = getFragmentManager();

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
        vu.onStartView();
    }

    @Override
    protected void onStart() {
        super.onStart();

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
