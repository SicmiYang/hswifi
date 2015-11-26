
package com.nfyg.hswx.biz.manager;

import com.nfyg.hswx.fwinterface.IBus;
import com.nfyg.hswx.fwinterface.IBusManager;

import java.util.HashMap;
import java.util.Map;

public class BusManager extends BaseManager implements IBusManager
{

    public Map<String, IBus> busMap;

    //    public Signal alertSignal;

    public BusManager()
    {
        super();
        preInit(null);
    }

    /*******************************************************************
     * 
     * IBusManager接口方法
     * 
     * 
     *******************************************************************/
    @Override
    public void addBus(String busName , IBus bus)
    {
        if (!this.busMap.containsKey(busName))
        {
            this.busMap.put(busName, bus);
        }
    }

    @Override
    public IBus getBus(String busName)
    {
        if (this.busMap.containsKey(busName))
        {
            return this.busMap.get(busName);
        }
        return null;
    }

    @Override
    public void removeBus(String busName)
    {
        if (this.busMap.containsKey(busName))
        {
            IBus bus = this.busMap.remove(busName);
            bus.destory(null);
            bus = null;
        }
    }

    @Override
    public void removeAll()
    {
        if (this.busMap != null && this.busMap.size() > 0)
        {
            for (IBus bus : this.busMap.values())
            {
                bus.destory(null);
            }
            this.busMap.clear();
        }
    }

    /*******************************************************************
     * 
     * IManager接口方法
     * 
     * 
     *******************************************************************/
    @Override
    public void preInit(Map<String, Object> params)
    {
        this.busMap = new HashMap<String, IBus>();

        //        alertSignal = new Signal();
        //        alertSignal.addListener(new SignalListener()
        //        {
        //
        //            @Override
        //            public void execute(Message msg)
        //            {
        ////                //关闭等待动画（如果有的话）
        ////                Engine.viewManager.dismissProgressDialog();
        ////                //显示内容
        ////                Engine.viewManager.toastMsg(msg.getData().getString(EngineOptions.back_data_flag));
        //            }
        //        }, 0);
    }

    @Override
    public void init(Map<String, Object> params)
    {
        if (this.busMap != null && this.busMap.size() > 0)
        {
            for (IBus bus : this.busMap.values())
            {
                bus.init(params);
            }
        }
        super.init(params);
    }

    @Override
    public void reset(Map<String, Object> params)
    {
        if (this.busMap != null && this.busMap.size() > 0)
        {
            for (IBus bus : this.busMap.values())
            {
                bus.reset(params);
            }
        }
    }

    @Override
    public void pause(Map<String, Object> params)
    {
        if (this.busMap != null && this.busMap.size() > 0)
        {
            for (IBus bus : this.busMap.values())
            {
                bus.pause(params);
            }
        }
    }

    @Override
    public void start(Map<String, Object> params)
    {
        if (this.busMap != null && this.busMap.size() > 0)
        {
            for (IBus bus : this.busMap.values())
            {
                bus.start(params);
            }
        }
    }

    @Override
    public void dispose(Map<String, Object> params)
    {
        if (this.busMap != null && this.busMap.size() > 0)
        {
            for (IBus bus : this.busMap.values())
            {
                bus.dispose(params);
            }
        }
        //        alertSignal.removeAllListener();
    }

    @Override
    public void destory(Map<String, Object> params)
    {
        super.destory(params);
        if (this.busMap != null && this.busMap.size() > 0)
        {
            for (IBus bus : this.busMap.values())
            {
                bus.destory(params);
            }
        }
        this.busMap.clear();
        this.busMap = null;
        //        alertSignal = null;
    }

}
