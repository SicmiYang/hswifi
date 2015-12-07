package com.nfyg.hswx.biz.bus;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.EngineOptions;
import com.nfyg.hswx.biz.BaseBus;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.utils.BundleBuilder;

import java.util.Map;

/**
 * Created by shengming.yang on 2015/11/27.
 */
public class VCViewEventBus extends BaseBus{

    public VCViewEventBus()
    {
        preInit(null);
    }


    /**
     * 单例
     * @return
     */
    public static VCViewEventBus getInstanceBus()
    {
        return (VCViewEventBus) Engine.busManager.getBus(VCViewEventBus.class.getSimpleName());
    }

    /**
     * 通知返回主页
     */
    public void callUpswipeLayoutDown(){

        callBack(Engine.getInstance().viewBackSignal,
                BundleBuilder.build().append(EngineOptions.flag_flag,EngineOptions.flag_back_home).commit());

    };


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
    }

    @Override
    public void destory(Map<String, Object> params) {
        super.destory(params);

    }
}
