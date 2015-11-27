package com.nfyg.hswx.views.subView;

import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nfyg.hswx.Engine;
import com.nfyg.hswx.R;
import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.biz.manager.SystemManager;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.biz.signals.SignalListener;
import com.nfyg.hswx.utils.common.LogUtil;
import com.nfyg.hswx.views.Vu;
import com.nfyg.hswx.views.fragment.adapter.NewsFragmentPagerAdapter;
import com.nfyg.hswx.views.widget.ImageCycleView;
import com.nfyg.hswx.views.widget.NewsChannelTopBar;
import com.nfyg.hswx.views.widget.SlidingUpPanelLayout;

/**
 * Created by shengming.yang on 2015/11/23.
 */
public class MainNewsVu  implements Vu {

    private String TAG = "MainNewsVu";

    private View view;

//    private ListView lstView;

    private ImageCycleView imageCycleView;

    private ImageView top_head;
    private ImageView top_more;
    private ImageView top_refresh;
    private ProgressBar top_progress;
    private SlidingMenu side_drawer;
    private SlidingUpPanelLayout upPanelLayout;

    private LinearLayout main_content_layout;
//    private SwipeRefreshLayout swipeRefreshLayout;

    private  View channel;
    private RelativeLayout main_layout;

    private boolean isbackhome = false;
    private ViewPager vpager;
    private NewsFragmentPagerAdapter pagerAdpter;

    private NewsChannelTopBar channleBar;
    private View tempView;


    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {

        this.view = inflater.inflate(R.layout.hs_main, rootView);

        this.imageCycleView = (ImageCycleView) view.findViewById(R.id.main_banner_iamgecycleview);
        this.upPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.sliding_layout);
        this.top_head = (ImageView) view.findViewById(R.id.top_head);

        this.main_content_layout = (LinearLayout) view.findViewById(R.id.main_content_layout);
        this.main_layout = (RelativeLayout) view.findViewById(R.id.main_layout);
        this.vpager = (ViewPager)view.findViewById(R.id.mian_viewpager);

        this.tempView = (View)view.findViewById(R.id.tempView);
    }

    /**
     * 添加初始化频道栏
     */
    private void addChannelBar() {
        channleBar = new NewsChannelTopBar(Engine.application);
        channel =  channleBar.initView();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        main_layout.addView(channel, layoutParams);
        //channel.setVisibility(View.GONE);
        channel.scrollTo(0, 120);

        this.channleBar.setOnChannelItemClick(new NewsChannelTopBar.channelItemSelectedListener() {
            @Override
            public void onItemSelect(int position) {
                vpager.setCurrentItem(position);
            }
        });


    }

    /**
     *  初始化Fragment
     * */
    private void initFragment() {

        vpager.setAdapter(pagerAdpter);
        vpager.setOnPageChangeListener(pageListener);
    }
    /**
     *  ViewPager切换监听方法
     * */
    public ViewPager.OnPageChangeListener pageListener= new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            vpager.setCurrentItem(position);
            channleBar.selectTab(position);
        }
    };

    @Override
    public void initListener() {



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
                LogUtil.logDebug("SlidingUpPanelLayout", "onPanelSlide：" + slideOffset);
                channel.scrollTo(0, (int) ((slideOffset) * 120));
                if (channel.getVisibility() != View.VISIBLE && slideOffset > 0.5 && !isbackhome) {
                    channel.setVisibility(View.VISIBLE);
                }
                if (slideOffset <= 0.06&&tempView.getVisibility()!=View.VISIBLE){
                    tempView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPanelCollapsed(View panel) {
                isbackhome = false;
                LogUtil.logDebug("SlidingUpPanelLayout", "onPanelCollapsed");
            }

            @Override
            public void onPanelExpanded(View panel) {
                LogUtil.logDebug("SlidingUpPanelLayout", "onPanelExpanded");
            }


        });


        /** 响应返回主页*/
        Engine.getInstance().viewBackSignal.addListener(new SignalListener() {
            @Override
            public void execute(Signal signal, Message msg) {
                isbackhome = true;
                tempView.setVisibility(View.GONE);
                upPanelLayout.collapsePane();
            }
        }, 0);


    }

    @Override
    public void onStartView() {

        int upslideHeigh = Engine.getInstance().systemManager.getWindowHeightOfPix() - main_content_layout.getMeasuredHeight();

        LogUtil.logDebug(TAG,"upSlideHeight："+upslideHeigh);

        upPanelLayout.setPanelHeight(1200);

        addChannelBar();
        initFragment();

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


    public void initSlidingMenu(SlidingMenu sidedrawer) {
        this.side_drawer = sidedrawer;
    }

    public void initActionBar() {

    }

    public  void  setFragmentAdapter(NewsFragmentPagerAdapter fragmentPagerAdapter){

        this.pagerAdpter = fragmentPagerAdapter;

    }

}
