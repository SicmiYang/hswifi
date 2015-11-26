package com.nfyg.hswx.views.subView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nfyg.hswx.Engine;
import com.nfyg.hswx.R;
import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.biz.manager.SystemManager;
import com.nfyg.hswx.utils.common.LogUtil;
import com.nfyg.hswx.views.Vu;
import com.nfyg.hswx.views.widget.ColumnHorizontalScrollView;
import com.nfyg.hswx.views.widget.ImageCycleView;
import com.nfyg.hswx.views.widget.SlidingUpPanelLayout;
import com.nfyg.hswx.views.widget.SuperSwipeRefreshLayout;

/**
 * Created by shengming.yang on 2015/11/23.
 */
public class MainNewsVu implements Vu {

    private String TAG = "MainNewsVu";

    private View view;

    private ListView lstView;

    private ImageCycleView imageCycleView;

    private ImageView top_head;
    private ImageView top_more;
    private ImageView top_refresh;
    private ProgressBar top_progress;
    private SlidingMenu side_drawer;
    private SlidingUpPanelLayout upPanelLayout;

    private LinearLayout main_content_layout;
    private LinearLayout mRadioGroup_content;
    private LinearLayout ll_more_columns;
    private RelativeLayout rl_column;
    private ImageView button_more_columns;
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    private SuperSwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {

        this.view = inflater.inflate(R.layout.hs_main, rootView);

        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) view.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) view.findViewById(R.id.mRadioGroup_content);
        mRadioGroup_content = (LinearLayout) view.findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) view.findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) view.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) view.findViewById(R.id.button_more_columns);

        this.imageCycleView = (ImageCycleView) view.findViewById(R.id.main_banner_iamgecycleview);
        this.lstView = (ListView) view.findViewById(R.id.list);
        this.upPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.sliding_layout);
        this.top_head = (ImageView) view.findViewById(R.id.top_head);

        main_content_layout = (LinearLayout) view.findViewById(R.id.main_content_layout);
        swipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);


    }

    @Override
    public void initListener() {

        this.lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtil.logDebug("ItemClickListener", "ItemClickListener：" + i);
                upPanelLayout.collapsePane();
                lstView.setSelection(0);
//                lstView.smoothScrollToPosition(0);
            }
        });

        this.imageCycleView.setImageResources(VCMainBus.getInstanceBus().adList, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {

                SystemManager.getBaseWebService().displayImg(imageView, imageURL);

            }

            @Override
            public void onImageClick(int position, View imageView) {

            }
        });

        top_head.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (side_drawer.isMenuShowing()) {
                    side_drawer.showContent();
                } else {
                    side_drawer.showMenu();
                }
            }
        });

        upPanelLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                //offset  0.0 ~1.0

                LogUtil.logDebug("SlidingUpPanelLayout", "onPanelSlide：" + slideOffset);

            }

            @Override
            public void onPanelCollapsed(View panel) {

                LogUtil.logDebug("SlidingUpPanelLayout", "onPanelCollapsed");
            }

            @Override
            public void onPanelExpanded(View panel) {

                LogUtil.logDebug("SlidingUpPanelLayout", "onPanelExpanded");

            }


        });

        /**
         * 设置下拉刷新回调
         */
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh(boolean isbackHome) {

                        if (isbackHome) {
                            swipeRefreshLayout.setRefreshing(false);
                            upPanelLayout.collapsePane();
                            return;
                        }

                        //TODO:刷新数据
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 2000);
                    }

                });


    }

    @Override
    public void onStartView() {

        int upslideHeigh = Engine.getInstance().systemManager.getWindowHeightOfPix() - 224;

        upPanelLayout.setPanelHeight(1200);

    }

    @Override
    public void onResumeView() {

        this.imageCycleView.startImageCycle();
    }

    @Override
    public void onPauseView() {

        this.imageCycleView.pushImageCycle();

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

    public void setListAdapter(BaseAdapter adapter) {
        lstView.setAdapter(adapter);
    }

    public void initSlidingMenu(SlidingMenu sidedrawer) {
        this.side_drawer = sidedrawer;
    }

    public void initActionBar() {

    }

}
