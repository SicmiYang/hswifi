
package com.nfyg.hswx.biz;

import android.os.Bundle;

import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.fwinterface.IBus;

import java.util.Map;


public abstract class BaseBus implements IBus
{

    public boolean isBusOk = false;

    public BaseBus()
    {
        this.isBusOk = false;
    }

    @Override
    public void testBus(String api , Signal dataBackSignal)
    {

    }

    /**
     * 回调
     * 
     * 
     * @param signal
     * @param bundle
     * @retruntype [void]
     * @exception
     */
    public void callBack(Signal signal , Bundle bundle)
    {
        if (signal != null)
        {
            signal.disptach(bundle);
        }
    }

    /*******************************************************************
     * 
     * 接口方法
     * 
     * 
     *******************************************************************/

    @Override
    public void init(Map<String, Object> params)
    {
        this.isBusOk = true;
    }

    @Override
    public void preInit(Map<String, Object> params)
    {
    }

    @Override
    public void reset(Map<String, Object> params)
    {
    }

    @Override
    public void pause(Map<String, Object> params)
    {
    }

    @Override
    public void start(Map<String, Object> params)
    {
    }

    @Override
    public void dispose(Map<String, Object> params)
    {
    }

    @Override
    public void destory(Map<String, Object> params)
    {

        this.isBusOk = false;
    }

}
