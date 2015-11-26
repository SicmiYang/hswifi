package com.nfyg.hswx.activities;

import android.widget.Toast;

import com.nfyg.hswx.AppConfig;
import com.nfyg.hswx.BasePresentActivity;
import com.nfyg.hswx.Engine;
import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.events.MainEvent;
import com.nfyg.hswx.views.adapter.NewListAdapter;
import com.nfyg.hswx.views.subView.DrawerView;
import com.nfyg.hswx.views.subView.MainNewsVu;

/**
 * Created by shengming.yang on 2015/11/13.
 */
public class YmActivity extends BasePresentActivity<MainNewsVu>   {



    @Override
    protected void onBindVu() {

        Engine.getInstance().initEngine(YmActivity.this, new AppConfig());
        Engine.busManager.addBus(VCMainBus.class.getSimpleName(), new VCMainBus());
        Engine.getInstance().init(null);
        super.onBindVu();
//        fm.beginTransaction()
//                .replace(vu.getContainerId(), NewsListFragment.newInstance())
//                .commit();
        vu.setListAdapter(new NewListAdapter(VCMainBus.getInstanceBus().news));

        vu.initSlidingMenu(new DrawerView(YmActivity.this).initSlidingMenu());


    }

    @Override
    protected void befrorePuse() {
        super.befrorePuse();
        envetBus.unregister(this);
    }

    @Override
    protected void afterResume() {
        super.afterResume();
        envetBus.registerSticky(this);
    }

    @Override
    protected boolean handleBackPressed() {
        envetBus.removeAllStickyEvents();
        return super.handleBackPressed();
    }

    @Override
    protected Class<MainNewsVu> getVClass() {
        return MainNewsVu.class;
    }

    @Override
    protected void onUnBindVu() {
        super.onUnBindVu();
    }

    public void onEvent(MainEvent mEvent){

        Toast.makeText(this,mEvent.str,Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Engine.getInstance().dispose(null);
        Engine.getInstance().destory(null);
    }
}
