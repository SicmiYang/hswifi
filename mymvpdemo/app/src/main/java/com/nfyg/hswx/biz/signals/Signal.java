
package com.nfyg.hswx.biz.signals;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import com.nfyg.hswx.EngineOptions;
import com.nfyg.hswx.fwinterface.IDisposable;
import com.nfyg.hswx.utils.collection.Array;

import java.io.Serializable;


public class Signal extends Handler implements IDisposable, Serializable
{

    private static final long serialVersionUID = -1721981368989730657L;

    private Array<SignalListener> listenerArr = new Array<SignalListener>(false, 8);

    public Signal()
    {
    }

    /**
     * 加入一个信号监听
     * 
     * @param listener
     * @param priority
     */
    public void addListener(SignalListener listener , int priority)
    {
        synchronized (listenerArr)
        {
            listenerArr.add(listener);
        }
    }

    /**
     * 删除所有的监听
     */
    public void removeAllListener()
    {
        removeAllNoOnceListener();
    }

    /**
     * 删除所有的监听
     */
    public void removeAllNoOnceListener()
    {
        synchronized (listenerArr)
        {
            listenerArr.clear();
        }
    }

    /**
     * 删除一个监听
     * 
     * @param listener
     */
    public void removeListener(SignalListener listener)
    {
        listenerArr.removeValue(listener, true);
    }

    /**
     * 广播消息
     * 
     * @param data
     */
    public void disptach(Serializable data)
    {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EngineOptions.back_data_flag, data);
        message.setData(bundle);
        this.sendMessage(message);
    }

    /**
     * 广播消息 主要传递Bitmap,Bitmap 继承Parcelable接口
     * 
     * @param data
     */
    public void disptach(Parcelable data)
    {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EngineOptions.back_data_flag, data);
        message.setData(bundle);
        this.sendMessage(message);
    }

    /**
     * 广播消息
     * 
     */
    public void disptach(Bundle bundle)
    {
        Message message = new Message();
        message.setData(bundle);
        this.sendMessage(message);
    }

    /**
     * 此方法是用于只需要抛出标签的情况
     * 
     */
    public void disptach(String flag , String key)
    {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(flag, key);
        msg.setData(bundle);
        this.sendMessage(msg);
    }

    @Override
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);
        // 首先处理listenerArr
        synchronized (listenerArr)
        {
            for (SignalListener s : listenerArr)
            {
                s.execute(this, msg);
            }
            msg.getData().clear();
            msg = null;
        }
    }

    @Override
    public void dispose()
    {
        removeAllListener();
    }
}
