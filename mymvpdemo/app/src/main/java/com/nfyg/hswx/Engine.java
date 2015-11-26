
package com.nfyg.hswx;

import android.content.Context;

import com.nfyg.hswx.biz.manager.BusManager;
import com.nfyg.hswx.biz.manager.CacheManager;
import com.nfyg.hswx.biz.manager.SystemManager;
import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.fwinterface.IEngine;

import java.util.Map;


/**
 * 引擎主类
 * 
 * @author ysm
 * 
 */
public class Engine implements IEngine
{
    public static Context application; // 主应用

    public static EngineOptions config; // 配置信息

    public static BusManager busManager; // bus manager

    public  static SystemManager systemManager;// system manger

    public static CacheManager cacheManager;// cachemanager

    public boolean isEngineOk = false;

    public boolean isAppOk = false;

    public boolean isFirstResume = true;

    public Signal viewBackSignal;



    /*************************************************************************
     * 
     * 覆盖方法
     * 
     *************************************************************************/

    @Override
    public void preInit(Map<String, Object> params)
    {

        resetVals();
        this.viewBackSignal = new Signal();
        busManager = new BusManager();
        systemManager = new SystemManager();
        cacheManager = new CacheManager();
    }

    public void initEngine(Context app , String optionsXml)
    {
        initEngine(app, paraseConfig(optionsXml));
    }

    public void initEngine(Context app , EngineOptions options)
    {
        application = app;
        config = options;
        preInit(null);
    }

    @Override
    public void init(Map<String, Object> params)
    {
        busManager.init(null);
        systemManager.init(null);
        cacheManager.init(null);
        this.isEngineOk = true;
    }

    @Override
    public void reset(Map<String, Object> params)
    {
        busManager.reset(params);
        systemManager.reset(params);
        cacheManager.reset(params);
    }

    @Override
    public void pause(Map<String, Object> params)
    {
        busManager.pause(params);
        cacheManager.pause(params);
        systemManager.pause(params

        );
    }

    @Override
    public void start(Map<String, Object> params)
    {
        busManager.start(params);
        systemManager.start(params);
        cacheManager.start(params);
    }

    @Override
    public void dispose(Map<String, Object> params)
    {
        // 当状态为debug时，删除数据库及缓存文件
        //        if (Engine.config.currStatus == RunningStatus.debug)
        //        {
        //            String sdPath = Engine.helperManager.fileHelper.getExternalStoragePath();
        //            helperManager.fileHelper.getFileHandle(sdPath + config.dbPath, FileType.External).deleteDirectory();
        //        }
        busManager.dispose(params);
        systemManager.destory(params);
        cacheManager.destory(params);
        this.viewBackSignal.dispose();
    }

    @Override
    public void destory(Map<String, Object> params)
    {
        busManager.destory(params);
        systemManager.destory(params);
        cacheManager.destory(params);
        config = null;
        application = null;
        this.viewBackSignal = null;
        //
        resetVals();
        System.exit(0);
    }

    @Override
    public void exitApp(Map<String, Object> params)
    {
        if (application != null)
        {
            application = null;
        }
    }

    /*************************************************************************
     * 
     * 私有方法
     * 
     *************************************************************************/
    /**
     * 解析配置文件
     * 
     * @author [ysm]
     * @method [paraseConfig]
     * @param optionsXml
     * @return
     * @retruntype [EngineOptions]
     * @exception
     */
    private EngineOptions paraseConfig(String optionsXml)
    {
        EngineOptions config = new EngineOptions();
        return config;
    }

    /**
     * 重置类变量
     * 
     * @author [ysm]
     * @method [resetVals]
     * @retruntype [void]
     * @exception
     */
    private void resetVals()
    {
        this.isEngineOk = false;
        this.isAppOk = false;
        this.isFirstResume = true;
    }

    /*************************************************************************
     * 
     * 单例
     * 
     *************************************************************************/
    private static Engine instance;

    public static Engine getInstance()
    {
        if (instance == null)
        {
            instance = new Engine();
        }
        return instance;
    }

    private Engine()
    {

    }

}
