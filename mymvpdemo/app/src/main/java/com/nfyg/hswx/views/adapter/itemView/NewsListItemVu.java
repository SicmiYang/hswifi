package com.nfyg.hswx.views.adapter.itemView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.R;
import com.nfyg.hswx.biz.manager.SystemManager;
import com.nfyg.hswx.model.entity.TestNewsEntiy;
import com.nfyg.hswx.views.Vu;

/**
 * Created by shengming.yang on 2015/11/16.
 */
public class NewsListItemVu implements Vu {

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

    private LayoutInflater minflater;

    private ViewGroup mrootView;

    private BaseViewHolder.TextMessageHolder textMessageHolder;

    private BaseViewHolder.LargeImgMessageHolder largeImgMessageHolder;

    private BaseViewHolder.MultiImgMessageHolder multiImgMessageHolder;

    private BaseViewHolder.RightResMessageHolder rightResMessageHolder;




    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {

        setViewHolder();
        this.minflater = inflater;
        this.mrootView = rootView;

        switch (ITEM_TYPE_CODE){
            case ITEM_TYPE_INVALID:
                view = minflater.inflate(R.layout.news_main_list_item_right_res_ly,mrootView,false);
                fillrightResHolder(rightResMessageHolder);
                break;
            case ITEM_TYPE_ONLY_TEXT:
                view = minflater.inflate(R.layout.news_main_list_item_single_txt_ly,mrootView,false);
                fillTextMessageHolder(textMessageHolder);
                break;
            case ITEM_TYPE_SINGLE_IMAGE:
                view =  minflater.inflate(R.layout.news_main_list_item_large_iamge_ly, mrootView, false);
                filllagerImgHolder(largeImgMessageHolder);
                break;
            case ITEM_TYPE_RIGHT_IMAGE:
                view = minflater.inflate(R.layout.news_main_list_item_right_res_ly,mrootView,false);
                fillrightResHolder(rightResMessageHolder);
                break;
            case ITEM_TYPE_LEFT_IMAGE:
                //TODO: need use left view type
                view = minflater.inflate(R.layout.news_main_list_item_right_res_ly,mrootView,false);
                fillrightResHolder(rightResMessageHolder);
                break;
            case ITEM_TYPE_MULTI_IMAGE:
                view = minflater.inflate(R.layout.news_main_list_item_multi_image_ly,mrootView,false);
                fillmultiImgHolder(multiImgMessageHolder);
                break;
            case ITEM_TYPE_SINGLE_AUDIO:
                //TODO: need use audio view type
                view = minflater.inflate(R.layout.news_main_list_item_large_iamge_ly,mrootView,false);
                filllagerImgHolder(largeImgMessageHolder);
                break;

            case ITEM_TYPE_RIGHT_AUDIO:
                view = minflater.inflate(R.layout.news_main_list_item_right_res_ly,mrootView,false);
                fillrightResHolder(rightResMessageHolder);
                break;
        }

    }

    private void setViewHolder() {
        if (textMessageHolder ==null){

            textMessageHolder = new BaseViewHolder.TextMessageHolder();
        }
        if (largeImgMessageHolder ==null){

            largeImgMessageHolder = new BaseViewHolder.LargeImgMessageHolder();
        }
        if (multiImgMessageHolder ==null){

            multiImgMessageHolder = new BaseViewHolder.MultiImgMessageHolder();
        }
        if (rightResMessageHolder ==null){

            rightResMessageHolder = new BaseViewHolder.RightResMessageHolder();
        }
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
    
    public  void  setViewData(TestNewsEntiy news){

        switch (ITEM_TYPE_CODE){
            case ITEM_TYPE_INVALID:
                hanlderRightRes(rightResMessageHolder,news);
                break;
            case ITEM_TYPE_ONLY_TEXT:
                hanlderSingleTxt(textMessageHolder,news);
                break;
            case ITEM_TYPE_SINGLE_IMAGE:
                hanlderLargeImg(largeImgMessageHolder,news);
                break;
            case ITEM_TYPE_RIGHT_IMAGE:
                hanlderRightRes(rightResMessageHolder,news);
                break;
            case ITEM_TYPE_LEFT_IMAGE:
                //TODO: need use left view type
                hanlderRightRes(rightResMessageHolder, news);
                break;
            case ITEM_TYPE_MULTI_IMAGE:
                hanlderMultiImg(multiImgMessageHolder,news);
                break;
            case ITEM_TYPE_SINGLE_AUDIO:
                //TODO: need use audio view type
                hanlderLargeImg(largeImgMessageHolder,news);
                break;

            case ITEM_TYPE_RIGHT_AUDIO:
                hanlderRightRes(rightResMessageHolder, news);
                break;

            default:
                hanlderRightRes(rightResMessageHolder, news);
                break;
        }


    }

    /**
     * 设置当前ItemType
     * @param itemTypeCode
     */
    public void setTypeCode(int itemTypeCode){

        ITEM_TYPE_CODE =itemTypeCode;

    }

    /**
     * 公共viewHolder
     * @param holder
     */
    private void fillBaseMessageholder(BaseViewHolder holder) {

        holder.item_source = (TextView) view.findViewById(R.id.item_source);
        holder.item_title = (TextView) view.findViewById(R.id.news_item_txt);
        holder.comment_count = (TextView) view.findViewById(R.id.comment_count);
        holder.list_item_local = (TextView) view.findViewById(R.id.list_item_local);
        holder.publish_time = (TextView) view.findViewById(R.id.publish_time);

    }

    /**
     * 单文本 ViewHolder
     * @param holder
     */
    private void fillTextMessageHolder(BaseViewHolder.TextMessageHolder holder){
        fillBaseMessageholder(holder);
        holder.msg = (TextView) view.findViewById(R.id.news_item_txt);
    }

    /**
     * 大图 ViewHolder
     * @param holder
     */
    private void filllagerImgHolder(BaseViewHolder.LargeImgMessageHolder holder){
        fillBaseMessageholder(holder);
        holder.laegeImg = (ImageView) view.findViewById(R.id.large_image);
        holder.msg = (TextView) view.findViewById(R.id.news_item_txt);
    }

    /**
     * 居右资源 ViewHolder
     * @param holder
     */
    private void fillrightResHolder(BaseViewHolder.RightResMessageHolder holder){
        fillBaseMessageholder(holder);
        holder.msg = (TextView) view.findViewById(R.id.news_item_txt);
        holder.rightImg = (ImageView) view.findViewById(R.id.right_image);

    }

    /**
     * 多图 ViewHolder
     * @param holder
     */
    private void fillmultiImgHolder(BaseViewHolder.MultiImgMessageHolder holder){
        fillBaseMessageholder(holder);
        holder.rightImg = (ImageView)view.findViewById(R.id.item_image_0);
        holder.middleImg = (ImageView)view.findViewById(R.id.item_image_1);
        holder.leftImg = (ImageView)view.findViewById(R.id.item_image_2);

    }


    private void hanlderBaseMessage(BaseViewHolder holder,TestNewsEntiy news){

//        if (news.getComment()!=null){
//            holder.comment_content.setText(news.getComment());
//        }
//        holder.comment_count.setText("评论："+news.getCommentNum());
//        holder.item_source.setText(news.);
        holder.item_title.setText(news.title);
        holder.publish_time.setText(news.date);

    }


    private  void hanlderSingleTxt(BaseViewHolder.TextMessageHolder holder,TestNewsEntiy news){
        hanlderBaseMessage(holder, news);
        holder.msg.setText(news.title);

    }

    private void hanlderMultiImg(BaseViewHolder.MultiImgMessageHolder holder ,TestNewsEntiy news){
        hanlderBaseMessage(holder, news);
//        SystemManager.getBaseWebService().displayImg(holder.rightImg, news.getPicOne());
//        SystemManager.getBaseWebService().displayImg(holder.middleImg,news.getPicOne());
//        SystemManager.getBaseWebService().displayImg(holder.leftImg,news.getPicThr());

    }

    private void hanlderLargeImg(BaseViewHolder.LargeImgMessageHolder holder ,TestNewsEntiy news){
        hanlderBaseMessage(holder, news);
        //SystemManager.getBaseWebService().displayImg(holder.laegeImg, news.getPicOne());
    }

    private void hanlderRightRes(BaseViewHolder.RightResMessageHolder holder ,TestNewsEntiy news){
        hanlderBaseMessage(holder, news);

        if ( news.cover_images !=null&&news.cover_images.size()>0){
            SystemManager.getBaseWebService().displayImg(holder.rightImg, Engine.config.host + news.cover_images.get(0).path);
           // holder.rightImg.setImageUrl(Engine.config.host + news.cover_images.get(0).path,);
        }
    }

}
