package com.nfyg.hswx.views.adapter.itemView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by shengming.yang on 2015/11/25.
 */
public class BaseViewHolder {

    public View view;
    public LinearLayout item_layout;
    public RelativeLayout comment_layout;
    public TextView item_title;
    public TextView item_source;
    public TextView list_item_local;
    public TextView comment_count;
    public TextView publish_time;
    public ImageView right_image;
    public LinearLayout item_image_layout;
    public TextView comment_content;



    public static class TextMessageHolder extends BaseViewHolder {

        TextView msg;
    }

    public static class LargeImgMessageHolder extends BaseViewHolder {

        TextView msg;
        ImageView laegeImg;

    }

    public static class MultiImgMessageHolder extends BaseViewHolder {

        TextView msg;
        ImageView rightImg;
        ImageView middleImg;
        ImageView leftImg;

    }

    public static class RightResMessageHolder extends BaseViewHolder {

        TextView msg;
        ImageView rightImg;

    }



}
