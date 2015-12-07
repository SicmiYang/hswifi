package com.nfyg.hswx.biz.bus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nfyg.hswx.Engine;
import com.nfyg.hswx.EngineOptions;
import com.nfyg.hswx.biz.BaseBus;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.data.Constants;
import com.nfyg.hswx.model.entity.ChannelItem;
import com.nfyg.hswx.model.entity.ChannelManage;
import com.nfyg.hswx.model.entity.NewsEntity;
import com.nfyg.hswx.model.entity.TestNewsEntiy;
import com.nfyg.hswx.model.entity.VideoResources;
import com.nfyg.hswx.utils.BundleBuilder;
import com.nfyg.hswx.utils.common.LogUtil;
import com.nfyg.hswx.utils.httpUtils.JsonBuilder;
import com.nfyg.hswx.views.fragment.BasePresenterFragment;
import com.nfyg.hswx.views.fragment.RecommendedFragment;
import com.nfyg.hswx.views.fragment.VideoListFragment;
import com.nfyg.hswx.web.request.RefreshUserRequest;
import com.nfyg.hswx.web.response.model.ResponseRefreshUserData;
import com.nfyg.hswx.web.rplistener.OnResponseListener3;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 业务Bus类  处理主要业务
 * Created by shengming.yang on 2015/11/16.
 */
public class VCMainBus extends BaseBus {

    private  String TAG = VCMainBus.class.getSimpleName();

    public Signal mSignal;

    public  ArrayList<NewsEntity> news ;

    public  ArrayList<TestNewsEntiy> test_news ;

    public ArrayList<String> adList = new ArrayList<>();

    public ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    public ArrayList<VideoResources> videoResources = new ArrayList<>();

    public VCMainBus()
    {
        preInit(null);
    }


    /**
     * 单例
     * @return
     */
    public static VCMainBus getInstanceBus()
    {
        return (VCMainBus) Engine.busManager.getBus(VCMainBus.class.getSimpleName());
    }

    /**
     * 登录接口
     */
    public void login(String loginName,String pwd){

        RefreshUserRequest request = new RefreshUserRequest(Engine.application);
        request.request(new OnResponseListener3<ResponseRefreshUserData>() {
            @Override
            public void onResponse(ResponseRefreshUserData result) {

                LogUtil.logDebug("BundleBuilder", "flag_success");

                callBack(mSignal, BundleBuilder.build().append(EngineOptions.flag_success, result).commit());

            }

            @Override
            public void onError(String error) {

                LogUtil.logDebug("BundleBuilder", "flag_failure");

                callBack(mSignal, BundleBuilder.build().append(EngineOptions.flag_failure, error).commit());
            }
        }, JsonBuilder.build().append("phone", loginName).append("pwd", pwd).commit());



    };

    public void getNewsData(final Signal Signal){

        if (!Engine.getInstance().systemManager.chkNetConnectedStatus()){

            String  cacheData =   Engine.cacheManager.aCache.getAsString("hs_main_news_cache");

            com.alibaba.fastjson.JSONObject jsonCache = JSON.parseObject(cacheData);

            if (jsonCache!=null&&jsonCache.containsKey("data")){

                test_news = (ArrayList<TestNewsEntiy>) JsonBuilder.getObjectLstFromJsonString(
                        jsonCache.getJSONArray("data").toString(), TestNewsEntiy.class);

                callBack(Signal, null);
            }
            LogUtil.logDebug(TAG, cacheData);
        }

        service.jsonGetRequest(Engine.config.http_url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            test_news = (ArrayList<TestNewsEntiy>) JsonBuilder.getObjectLstFromJsonString(
                                    jsonObject.getJSONArray("data").toString(), TestNewsEntiy.class);

                            callBack(Signal, null);

                            Engine.cacheManager.svae("hs_main_news_cache",jsonObject.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        //TODO: 加载出错  去缓存读取
                        Toast.makeText(Engine.application, volleyError.getMessage(), Toast.LENGTH_SHORT);
                    }
                });

    }

    public void readNews(int positions){

        news.get(positions).setReadStatus(true);

    }

    @Override
    public void testBus(String api, Signal dataBackSignal) {
        super.testBus(api, dataBackSignal);
    }


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
    }

    @Override
    public void preInit(Map<String, Object> params) {
        super.preInit(params);
        mSignal = new Signal();
        news = Constants.getNewsList();
        test_news = new ArrayList<>();
        //FIXME:test data
        if(adList!=null){
            adList.add("http://imgt2.bdstatic.com/it/u=3269155243,2604389213&fm=21&gp=0.jpg");
            adList.add("http://img4.cache.netease.com/cnews/2015/12/4/201512041231437adc7.jpg");
        }

        initListFragments();

        //FIXME:test data
        for(int i =0;i<10;i++){
            String url1 = "http://ht-mobile.cdn.turner.com/nba/big/teams/kings/2014/12/12/HollinsGlassmov-3462827_8382664.mp4";
            String url2 = "http://ht-mobile.cdn.turner.com/nba/big/teams/kings/2014/12/12/VivekSilverIndiamov-3462702_8380205.mp4";
            if(i%3==0) {
                videoResources.add(new VideoResources("test video", "http://video.cztv.com/video/rzx/201208/15/1345010952759.mp4"));
            }else if (i%3==1){

                videoResources.add(new VideoResources("test video",url1));
            }else if(i%3==2){

                videoResources.add(new VideoResources("test video",url2));
            }


        }
    }

    /**
     *初始化Fragments
     */
    public void initListFragments(){
        List<ChannelItem> userChannelList = ChannelManage.defaultUserChannels;

        BasePresenterFragment newfragment ;

        for(int i = 0; i< userChannelList.size();i++){
            Bundle data = new Bundle();
            data.putString("text", userChannelList.get(i).getName());
            data.putInt("id", userChannelList.get(i).getId());
            if(4 == userChannelList.get(i).getId()){
                newfragment = new VideoListFragment();
            }else {

                newfragment = new RecommendedFragment();
            }
            newfragment.setArguments(data);
            fragments.add(newfragment);

        }
    }




    @Override
    public void reset(Map<String, Object> params) {
        super.reset(params);
    }

    @Override
    public void pause(Map<String, Object> params) {
        super.pause(params);
    }

    @Override
    public void start(Map<String, Object> params) {
        super.start(params);
    }

    @Override
    public void dispose(Map<String, Object> params) {
        super.dispose(params);
        if(mSignal!=null){
            mSignal.dispose();
        }
        if (news!=null){
            news.clear();
        }
        if(adList!=null){
            adList.clear();
        }

        if(fragments!=null){
            fragments.clear();
        }
    }

    @Override
    public void destory(Map<String, Object> params) {
        super.destory(params);

        if(mSignal!=null){
            mSignal = null;
        }

        if(news!=null){
            news = null;
        }
        if(adList !=null){
            adList = null;
        }
        if (fragments !=null){
            fragments = null;
        }
    }


}
