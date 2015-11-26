
package com.nfyg.hswx;

import android.app.Application;

import com.nfyg.hswx.fwinterface.IApplication;

import java.util.Map;

public  class AndroidApplication extends Application implements IApplication
{


    public AndroidApplication()
    {
        super();
        ssPreInit(null);
    }





    /*******************************************************************
     * 
     * application接口方法
     * 
     * 
     *******************************************************************/

    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Override
    public void ssEnterApp()
    {
        Engine.getInstance().isAppOk = true;

    }

    @Override
    public void ssDispose()
    {
        Engine.getInstance().dispose(null);
    }

    @Override
    public void ssDestroy()
    {
        ssStopService();
        Engine.getInstance().dispose(null);
        Engine.getInstance().destory(null);
    }




    @Override
    public void ssPreInit(Map<String, Object> params)
    {
    }

    @Override
    public void ssInitEngine()
    {
    }

    @Override
    public void ssInitBus()
    {
    }

    @Override
    public void ssInitOthers()
    {
    }

    @Override
    public void ssStartService()
    {
    }

    @Override
    public void ssBindService()
    {
    }

    @Override
    public void ssUnBindService()
    {
    }

    @Override
    public void ssStopService()
    {
    }

}
