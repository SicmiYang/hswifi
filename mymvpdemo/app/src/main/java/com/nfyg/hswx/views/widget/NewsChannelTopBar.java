package com.nfyg.hswx.views.widget;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.R;
import com.nfyg.hswx.model.entity.ChannelItem;
import com.nfyg.hswx.model.entity.ChannelManage;

import java.util.List;

/**
 * Created by shengming.yang on 2015/11/26.
 */
public class NewsChannelTopBar {

    private LinearLayout main_content_layout;
    public  LinearLayout mRadioGroup_content;
    private LinearLayout ll_more_columns;
    private RelativeLayout rl_column;
    private ImageView button_more_columns;
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    private ImageView shade_left;
    private ImageView shade_right;

    private  int   windowWidthOfPix;

    /** 当前选中的栏目*/
    private int columnSelectIndex = 0;

    private  channelItemSelectedListener itemSelectedListener;


    public NewsChannelTopBar(Context context){
    }


    public  View initView(){

        View ctopBar =  LayoutInflater.from(Engine.application)
                .inflate(R.layout.news_main_channel_bar, null);

        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) ctopBar.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) ctopBar.findViewById(R.id.mRadioGroup_content);
        mRadioGroup_content = (LinearLayout) ctopBar.findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) ctopBar.findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) ctopBar.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) ctopBar.findViewById(R.id.button_more_columns);
        shade_left = (ImageView) ctopBar.findViewById(R.id.shade_left);
        shade_right = (ImageView) ctopBar.findViewById(R.id.shade_right);

        initTabColumn();
        return ctopBar;

    };

    /**
     *  初始化Column栏目项
     * */
    private void initTabColumn() {

        windowWidthOfPix =   Engine.getInstance().systemManager.getWindowWidthOfPix();

        List<ChannelItem> userChannelList = ChannelManage.defaultUserChannels;

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
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(Engine.application.getResources().getColorStateList(R.color.channel_bar_text));
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

                            int k = localView.getMeasuredWidth();
                            int l = localView.getLeft();
                            int i2 = l + k / 2 - windowWidthOfPix / 2;
                            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
                            localView.setSelected(true);
                            if (itemSelectedListener!=null)
                            itemSelectedListener.onItemSelect(i);
                        }
                    }
                }
            });
            mRadioGroup_content.addView(columnTextView, i ,params);
        }
    }

    public void setOnChannelItemClick(channelItemSelectedListener channelItemSelectedListener){

        this.itemSelectedListener = channelItemSelectedListener;

    }

    public void setColumnSelectIndex(int index){
        this.columnSelectIndex = index;

    }

    /**
     *  选择的Column里面的Tab
     * */
    public  void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - windowWidthOfPix / 2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
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


    public interface channelItemSelectedListener{

       void  onItemSelect(int position);

    }

}
