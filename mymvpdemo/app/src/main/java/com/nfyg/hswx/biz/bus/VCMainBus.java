package com.nfyg.hswx.biz.bus;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.EngineOptions;
import com.nfyg.hswx.biz.BaseBus;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.data.Constants;
import com.nfyg.hswx.model.entity.NewsEntity;
import com.nfyg.hswx.utils.BundleBuilder;
import com.nfyg.hswx.utils.common.LogUtil;
import com.nfyg.hswx.utils.httpUtils.JsonBuilder;
import com.nfyg.hswx.web.request.RefreshUserRequest;
import com.nfyg.hswx.web.response.model.ResponseRefreshUserData;
import com.nfyg.hswx.web.rplistener.OnResponseListener3;

import java.util.ArrayList;
import java.util.Map;

/**
 * 业务Bus类  处理主要业务
 * Created by shengming.yang on 2015/11/16.
 */
public class VCMainBus extends BaseBus {

    public Signal mSignal;

    public  ArrayList<NewsEntity> news ;

    public ArrayList<String> adList = new ArrayList<>();

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

                callBack(mSignal, BundleBuilder.build().append(EngineOptions.flag_failure,  error).commit());
            }
        }, JsonBuilder.build().append("phone", loginName).append("pwd", pwd).commit());



    };

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
        if(adList!=null){
            adList.add("http://imgt2.bdstatic.com/it/u=3269155243,2604389213&fm=21&gp=0.jpg");
            adList.add("https://www.baidu.com/img/bd_logo1.png");
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
    }


}
