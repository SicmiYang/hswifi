package com.nfyg.hswx.activities;

import android.os.Message;

import com.nfyg.hswx.AppConfig;
import com.nfyg.hswx.BasePresentActivity;
import com.nfyg.hswx.Engine;
import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.biz.bus.VCViewEventBus;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.biz.signals.SignalListener;
import com.nfyg.hswx.utils.SignalBuilder;
import com.nfyg.hswx.views.fragment.adapter.NewsFragmentPagerAdapter;
import com.nfyg.hswx.views.subView.MainNewsVu;
import com.nfyg.hswx.views.widget.DrawerView;

/**
 * Created by shengming.yang on 2015/11/13.
 */
public class YmActivity extends BasePresentActivity<MainNewsVu>   {


    @Override
    protected void onBindVu() {

        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        Engine.getInstance().initEngine(YmActivity.this, new AppConfig());
        initBus();
        Engine.getInstance().init(null);
        super.onBindVu();
//        fm.beginTransaction()
//                .replace(vu.getContainerId(), NewsListFragment.newInstance())
//                .commit();
//        vu.setListAdapter(new NewListAdapter(VCMainBus.getInstanceBus().news));

        final NewsFragmentPagerAdapter adapter = new NewsFragmentPagerAdapter(getSupportFragmentManager(), VCMainBus.getInstanceBus().fragments);
        vu.setFragmentAdapter(adapter);
        vu.initSlidingMenu(new DrawerView(YmActivity.this).initSlidingMenu());

        VCMainBus.getInstanceBus().getNewsData(SignalBuilder.build(new SignalListener() {
            @Override
            public void execute(Signal signal, Message msg) {
                adapter.notifyDataSetChanged();
            }
        }, 0));


    }

    private void initBus() {
        Engine.busManager.addBus(VCMainBus.class.getSimpleName(), new VCMainBus());
        Engine.busManager.addBus(VCViewEventBus.class.getSimpleName(), new VCViewEventBus());
    }

    @Override
    protected void befrorePuse() {
        super.befrorePuse();
    }










    @Override
    protected void afterResume() {
        super.afterResume();
    }

    @Override
    protected boolean handleBackPressed() {
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Engine.getInstance().dispose(null);
        Engine.getInstance().destory(null);
    }

    @Override
    public void onBackPressed() {
        if (vu.isPanelSlideExpanded){
            VCViewEventBus.getInstanceBus().callUpswipeLayoutDown();
            return;
        }
        super.onBackPressed();
    }
}
