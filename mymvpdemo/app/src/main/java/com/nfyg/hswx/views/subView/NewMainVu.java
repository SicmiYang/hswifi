package com.nfyg.hswx.views.subView;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nfyg.hswx.R;
import com.nfyg.hswx.Engine;
import com.nfyg.hswx.activities.otherActivity;
import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.biz.signals.SignalListener;
import com.nfyg.hswx.model.entity.ChannelItem;
import com.nfyg.hswx.model.entity.ChannelManage;
import com.nfyg.hswx.views.Vu;
import com.nfyg.hswx.views.VuCallBack;
import com.nfyg.hswx.views.adapter.NewListAdapter;
import com.nfyg.hswx.views.widget.ColumnHorizontalScrollView;

import java.util.List;

/**
 * Created by shengming.yang on 2015/11/16.
 */
public class NewMainVu implements Vu {

    private String TAG = NewMainVu.class.getSimpleName();

    View view;
    NewListAdapter adapter;
    ColumnHorizontalScrollView mColumnHorizontalScrollView;
    VuCallBack<Integer> selectCallback;
    int windowWidthOfPix;
    private LinearLayout mRadioGroup_content;
    private LinearLayout ll_more_columns;
    private RelativeLayout rl_column;
    private ImageView button_more_columns;
   // private ViewPager mViewPager;
    private ImageView shade_left;
    private ImageView shade_right;
    private ImageView top_head;
    private ImageView top_more;
    private ImageView top_refresh;
    private ProgressBar top_progress;
    private SlidingMenu side_drawer;
    private List<ChannelItem> userChannelList;

    /** 当前选中的栏目*/
    private int columnSelectIndex = 0;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.main, container, false);
        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) view.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout)  view.findViewById(R.id.mRadioGroup_content);
        mRadioGroup_content = (LinearLayout) view.findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) view.findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) view.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) view.findViewById(R.id.button_more_columns);
        //mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        shade_left = (ImageView) view.findViewById(R.id.shade_left);
        shade_right = (ImageView) view.findViewById(R.id.shade_right);
        top_head = (ImageView) view.findViewById(R.id.top_head);
        top_more = (ImageView) view.findViewById(R.id.top_more);
        top_refresh = (ImageView) view.findViewById(R.id.top_refresh);
        top_progress = (ProgressBar) view.findViewById(R.id.top_progress);

        windowWidthOfPix = Engine.getInstance().systemManager.getWindowWidthOfPix();


        initChanneData();
        initSlidingMenu();


    }


    /**
     * 初始化数据
     */
    private void initChanneData() {

        userChannelList = ChannelManage.defaultOtherChannels;

        initTabColumn();

    }

    /**
     *  选择的Column里面的Tab
     * */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - windowWidthOfPix / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        //判断是否选中
        for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    /**
     *  初始化Column栏目项
     * */
    private void initTabColumn() {

       int  mItemWidth = windowWidthOfPix/7;
        mRadioGroup_content.removeAllViews();
        int count =  userChannelList.size();
        mColumnHorizontalScrollView.setParam((Activity) Engine.application, windowWidthOfPix, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for(int i = 0; i< count; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            TextView columnTextView = new TextView(Engine.application);
            columnTextView.setTextAppearance(Engine.application, R.style.top_category_scroll_view_item_text);
            columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(Engine.application.getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if(columnSelectIndex == i){
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                          //  mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(Engine.application, userChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i ,params);
        }
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


        VCMainBus.getInstanceBus().mSignal.addListener(new SignalListener() {
            @Override
            public void execute(Signal signal, Message msg) {

                adapter.notifyDataSetChanged();

                Engine.application.startActivity(new Intent(Engine.application, otherActivity.class));

            }
        }, 0);


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

    public void setSelectCallback(VuCallBack<Integer> selectCallback) {
        this.selectCallback = selectCallback;
    }

    protected void initSlidingMenu() {
        side_drawer = new DrawerView((Activity) Engine.application).initSlidingMenu();
    }
}
