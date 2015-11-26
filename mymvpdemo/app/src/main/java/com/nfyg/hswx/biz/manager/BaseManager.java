
package com.nfyg.hswx.biz.manager;

import com.nfyg.hswx.fwinterface.IManager;

import java.util.Map;

public abstract class BaseManager implements IManager
{
    public boolean isManagerOk = false;

    public BaseManager()
    {
        this.isManagerOk = false;
    }

    public void init(Map<String, Object> params)
    {
        this.isManagerOk = true;
    }

    public void destory(Map<String, Object> params)
    {
        this.isManagerOk = false;
    }
}
