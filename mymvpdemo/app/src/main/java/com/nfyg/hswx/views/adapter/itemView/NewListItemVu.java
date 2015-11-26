package com.nfyg.hswx.views.adapter.itemView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nfyg.hswx.R;
import com.nfyg.hswx.model.entity.NewsEntity;
import com.nfyg.hswx.views.Vu;

import java.util.List;

/**
 * Created by shengming.yang on 2015/11/16.
 */
public class NewListItemVu implements Vu {

    public static final int ITEM_TYPE_INVALID = -1;
    public static final int ITEM_TYPE_ONLY_TEXT = 0x00;
    public static final int ITEM_TYPE_SINGLE_IMAGE = 0x01;
    public static final int ITEM_TYPE_RIGHT_IMAGE = 0x02;
    public static final int ITEM_TYPE_LEFT_IMAGE = 0x03;
    public static final int ITEM_TYPE_MULTI_IMAGE = 0x04;
    public static final int ITEM_TYPE_SINGLE_AUDIO = 0x05;
    public static final int ITEM_TYPE_RIGHT_AUDIO = 0x06;

    public int ITEM_TYPE_CODE;


    private  View view;
    private LinearLayout item_layout;
    private RelativeLayout comment_layout;
    private TextView item_title;
    private TextView item_source;
    private TextView list_item_local;
    private TextView comment_count;
    private TextView publish_time;
    private TextView item_abstract;
    private ImageView alt_mark;
    private ImageView right_image;
    private LinearLayout item_image_layout;
    private ImageView item_image_0;
    private ImageView item_image_1;
    private ImageView item_image_2;
    private ImageView large_image;
    private ImageView popicon;
    private TextView comment_content;
    private View right_padding_view;
    private LinearLayout layout_list_section;
    private TextView section_text;
    private TextView section_day;




    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {

        view = inflater.inflate(R.layout.list_item,rootView,false);

        this.item_layout = (LinearLayout)view.findViewById(R.id.item_layout);
        this.comment_layout = (RelativeLayout)view.findViewById(R.id.comment_layout);
        this.item_title = (TextView)view.findViewById(R.id.item_title);
        this.item_source = (TextView)view.findViewById(R.id.item_source);
        this.list_item_local = (TextView)view.findViewById(R.id.list_item_local);
        this.comment_count = (TextView)view.findViewById(R.id.comment_count);
        this.publish_time = (TextView)view.findViewById(R.id.publish_time);
        this.item_abstract = (TextView)view.findViewById(R.id.item_abstract);
        this.alt_mark = (ImageView)view.findViewById(R.id.alt_mark);
        this.right_image = (ImageView)view.findViewById(R.id.right_image);
        this.item_image_layout = (LinearLayout)view.findViewById(R.id.item_image_layout);
        this.item_image_0 = (ImageView)view.findViewById(R.id.item_image_0);
        this.item_image_1 = (ImageView)view.findViewById(R.id.item_image_1);
        this.item_image_2 = (ImageView)view.findViewById(R.id.item_image_2);
        this.large_image = (ImageView)view.findViewById(R.id.large_image);
        this.popicon = (ImageView)view.findViewById(R.id.popicon);
        this.comment_content = (TextView)view.findViewById(R.id.comment_content);
        this.right_padding_view = (View)view.findViewById(R.id.right_padding_view);
        //头部的日期部分
        this.layout_list_section = (LinearLayout)view.findViewById(R.id.layout_list_section);
        this.section_text = (TextView)view.findViewById(R.id.section_text);
        this.section_day = (TextView)view.findViewById(R.id.section_day);
    }

    @Override
    public void initListener() {

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
    
    public  void  setViewData(NewsEntity news){

        this.item_title.setText(news.getTitle());
        this.item_source.setText(news.getSource());
        this.comment_count.setText("评论" + news.getCommentNum());
        this.publish_time.setText(news.getPublishTime() + "小时前");
        List<String> imgUrlList = news.getPicList();
        if(imgUrlList !=null && imgUrlList.size() !=0){
            if(imgUrlList.size() == 1){
                this.item_image_layout.setVisibility(View.GONE);
                //是否是大图
                if(news.getIsLarge()){
                    this.large_image.setVisibility(View.VISIBLE);
                    this.right_image.setVisibility(View.GONE);
                   // imageLoader.displayImage(imgUrlList.get(0), this.large_image, options);
                    this.popicon.setVisibility(View.GONE);
                    this.comment_count.setVisibility(View.GONE);
                    this.right_padding_view.setVisibility(View.GONE);
                }else{
                    this.large_image.setVisibility(View.GONE);
                    this.right_image.setVisibility(View.VISIBLE);
                    //imageLoader.displayImage(imgUrlList.get(0), this.right_image, options);
                }
            }else{
                this.large_image.setVisibility(View.GONE);
                this.right_image.setVisibility(View.GONE);
                this.item_image_layout.setVisibility(View.VISIBLE);
//                imageLoader.displayImage(imgUrlList.get(0), this.item_image_0, options);
//                imageLoader.displayImage(imgUrlList.get(1), this.item_image_1, options);
//                imageLoader.displayImage(imgUrlList.get(2), this.item_image_2, options);
            }
        }else{
            this.right_image.setVisibility(View.GONE);
            this.item_image_layout.setVisibility(View.GONE);
        }
        //int markResID = getAltMarkResID(news.getMark(),news.getCollectStatus());
//        if(markResID != -1){
//            this.alt_mark.setVisibility(View.VISIBLE);
//            this.alt_mark.setImageResource(markResID);
//        }else{
//            this.alt_mark.setVisibility(View.GONE);
//        }
        //判断该新闻概述是否为空
        if (!TextUtils.isEmpty(news.getNewsAbstract())) {
            this.item_abstract.setVisibility(View.VISIBLE);
            this.item_abstract.setText(news.getNewsAbstract());
        } else {
            this.item_abstract.setVisibility(View.GONE);
        }
        //判断该新闻是否是特殊标记的，推广等，为空就是新闻
        if(!TextUtils.isEmpty(news.getLocal())){
            this.list_item_local.setVisibility(View.VISIBLE);
            this.list_item_local.setText(news.getLocal());
        }else{
            this.list_item_local.setVisibility(View.GONE);
        }
        //判断评论字段是否为空，不为空显示对应布局
        if(!TextUtils.isEmpty(news.getComment())){
            //news.getLocal() != null && 
            this.comment_layout.setVisibility(View.VISIBLE);
            this.comment_content.setText(news.getComment());
        }else{
            this.comment_layout.setVisibility(View.GONE);
        }
        //判断该新闻是否已读
        if(!news.getReadStatus()){
            this.item_layout.setSelected(true);
        }else{
            this.item_layout.setSelected(false);
        }
        //设置+按钮点击效果
//        this.popicon.setOnClickListener(new popAction(position));
//        //头部的相关东西
//        int section = getSectionForPosition(position);
//        if (getPositionForSection(section) == position) {
//            this.layout_list_section.setVisibility(View.VISIBLE);
////			head_title.setText(news.getDate());
//            this.section_text.setText(mSections.get(section));
//            this.section_day.setText("今天");
//        } else {
//            this.layout_list_section.setVisibility(View.GONE);
//        }
    }

    public void setTypeCode(int itemTypeCode){

        ITEM_TYPE_CODE =itemTypeCode;

    }



    
}
