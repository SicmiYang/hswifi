package com.nfyg.hswx.views.adapter.itemView;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.EngineOptions;
import com.nfyg.hswx.R;
import com.nfyg.hswx.biz.bus.VCMainBus;
import com.nfyg.hswx.model.entity.VideoResources;
import com.nfyg.hswx.utils.BundleBuilder;
import com.nfyg.hswx.utils.common.LogUtil;
import com.nfyg.hswx.utils.httpUtils.ProxyUtils;
import com.nfyg.hswx.views.YmVu;
import com.nfyg.hswx.views.widget.HsMediaController;
import com.nfyg.hswx.web.proxy.HttpGetProxy;

/**
 * Created by shengming.yang on 2015/11/30.
 */
public class NewsListVideoItemVu extends YmVu {
    private String TAG = NewsListVideoItemVu.class.getSimpleName();
    View view ;
    private VideoView mVideoView;
    private TextView videoTitle;
    private ImageView videoPlayiV;

    private VideoResources  videoRes;

    private long startTimeMills;
    private HttpGetProxy proxy;

    private long waittime=1000*5;//等待缓冲时间
    private String videoUrl;
    private ProgressBar mprogress;
    private int currentIndex= -1;
    private boolean isPlaying = false;
    private int playPosition = -1;
    private MediaController mMediaCtrl;
    private boolean isPaused = false;

    @Override
    public void init(LayoutInflater inflater, ViewGroup rootView) {

        view = inflater.inflate(R.layout.news_video_list_item_ly, rootView,false);

        videoTitle = (TextView)view.findViewById(R.id.news_item_video_title_txt);
        videoPlayiV = (ImageView)view.findViewById(R.id.news_item_play_img_view);
        mprogress = (ProgressBar)view.findViewById(R.id.news_item_video_progressbar);

        mMediaCtrl	= new HsMediaController(Engine.application);



    }

    @Override
    public void initListener() {

    }


    @Override
    public View getView() {
        return view;
    }

    public void setViewData(final VideoResources videoRes,int position){

        final int mPosition=position;

        this.videoRes = videoRes;

        this.videoTitle.setText(videoRes.getVideoTitle());

        this.mVideoView = (VideoView)view.findViewById(R.id.news_item_video_view);

        if(currentIndex == position){
            videoPlayiV.setVisibility(View.INVISIBLE);
            videoTitle.setVisibility(View.INVISIBLE);

            if(isPlaying || playPosition==-1){
                if(mVideoView!=null){
                    mVideoView.setVisibility(View.GONE);
                    mVideoView.stopPlayback();
                    mprogress.setVisibility(View.GONE);
                }
            }

            mVideoView.setVisibility(View.VISIBLE);
            mMediaCtrl.setAnchorView(mVideoView);
            mMediaCtrl.setMediaPlayer(mVideoView);
            mVideoView.setMediaController(mMediaCtrl);
            mVideoView.requestFocus();
            mprogress.setVisibility(View.VISIBLE);
            if(playPosition>0 && isPaused){
                mVideoView.start();
                isPaused=false;
                isPlaying=true;
                mprogress.setVisibility(View.GONE);
            }else{
                mVideoView.setVideoPath(videoRes.getVideoUrl());
                //mprogress.setVisibility(View.INVISIBLE);
                isPaused=false;
                isPlaying=true;
                System.out.println("播放新的视频");
            }
            mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mVideoView != null) {
                        mVideoView.seekTo(0);
                        mVideoView.stopPlayback();
                        currentIndex = -1;
                        isPaused = false;
                        isPlaying = false;
                        mprogress.setVisibility(View.GONE);

                        //TODO: 刷新adapter
                        delayToStartPlay.sendEmptyMessage(0);
                    }
                }
            });

            mVideoView.setOnPreparedListener(mOnPreparedListener);

        }else{
            videoPlayiV.setVisibility(View.VISIBLE);
            videoTitle.setVisibility(View.VISIBLE);
            mprogress.setVisibility(View.GONE);
            mVideoView.setVisibility(View.GONE);
        }

        videoPlayiV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex=mPosition;
                playPosition=-1;

                LogUtil.logDebug(TAG,"currentIndex:"+mPosition);
//                mprogress.setVisibility(View.VISIBLE);
//                videoPlayiV.setVisibility(View.GONE);

                //TODO: 预加载

                delayToStartPlay.sendEmptyMessageDelayed(0,0);
                //adapter.notifyDataSetChanged();

//                ProxyUtils.clearCacheFile(SystemManager.getBufferDir());//清除前面的预加载文件
//                startProXy();

            }
        });

    }

    /**
     * 设置vido url
     */
    private Handler delayToStartPlay = new Handler() {
        public void handleMessage(Message msg) {
//            startTimeMills=System.currentTimeMillis();
//            mVideoView.setVideoPath(videoRes.getVideoUrl());

            VCMainBus.getInstanceBus().callBack(Engine.getInstance().viewBackSignal,
                    BundleBuilder.build().append(EngineOptions.flag_flag,EngineOptions.flag_notify_adapter).commit());
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener=new MediaPlayer.OnPreparedListener(){

        @Override
        public void onPrepared(MediaPlayer mp) {
            mprogress.setVisibility(View.INVISIBLE);
            mVideoView.start();
            long duration=System.currentTimeMillis() - startTimeMills;
            LogUtil.logDebug(TAG, "等待缓冲时间:" + waittime + ",首次缓冲时间:" + duration);
        }
    };

    private void  startProXy(){
        //初始化代理服务器
        if (proxy == null){
            proxy = new HttpGetProxy(9110);
        }
        proxy.asynStartProxy();

       new Thread(new Runnable() {
            @Override
            public void run() {

                String[] urls = proxy.getLocalURL(videoRes.getVideoUrl());
                String mp4Url=urls[0];
                videoUrl=urls[1];

                try {
                    String prebufferFilePath = proxy.prebuffer(mp4Url,
                            HttpGetProxy.SIZE);

                    Log.e(TAG, "预加载文件：" + prebufferFilePath);
                } catch (Exception ex) {
                    Log.e(TAG, ex.toString());
                    Log.e(TAG, ProxyUtils.getExceptionMessage(ex));
                }

            }
        }).start();

        delayToStartPlay.sendEmptyMessageDelayed(0,waittime);
    }


}
