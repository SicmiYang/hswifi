package com.nfyg.hswx.views.subView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nfyg.hswx.R;
import com.nfyg.hswx.Engine;
import com.nfyg.hswx.views.Vu;
import com.nfyg.hswx.views.widget.DrawerView;

/**
 * Created by shengming.yang on 2015/11/20.
 */
public class TopBarVu implements Vu {

    View view;
    private ImageView top_head;
    private ImageView top_more;
    private ImageView top_refresh;
    private ProgressBar top_progress;
    private SlidingMenu side_drawer;


    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {
        view = inflater.inflate(R.layout.main_head, rootView, false);

        top_head = (ImageView) view.findViewById(R.id.top_head);
        top_more = (ImageView) view.findViewById(R.id.top_more);
        top_refresh = (ImageView) view.findViewById(R.id.top_refresh);
        top_progress = (ProgressBar) view.findViewById(R.id.top_progress);

        initSlidingMenu();
    }

    @Override
    public void initListener() {

        top_head.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(side_drawer.isMenuShowing()){
                    side_drawer.showContent();
                }else{
                    side_drawer.showMenu();
                }
            }
        });
        top_more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(side_drawer.isSecondaryMenuShowing()){
                    side_drawer.showContent();
                }else{
                    side_drawer.showSecondaryMenu();
                }
            }
        });

    }

    @Override
    public void onStartView() {

    }

    @Override
    public void onResumeView() {

    }

    @Override
    public void onPauseView() {

    }

    @Override
    public void onStopView() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public View getView() {
        return view;
    }
    protected void initSlidingMenu() {
        side_drawer = new DrawerView((Activity) Engine.application).initSlidingMenu();
    }
}
