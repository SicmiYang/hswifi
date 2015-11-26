
package com.nfyg.hswx.fwinterface;

import java.util.Map;

/**
 * 通常是程序入口
 *   
 */
public interface IApplication
{

    /**
     * 进入应用
     * 
     * @method [enterApp]
     * @retruntype [void]
     * @exception
     */
    public void ssEnterApp();

    /**
     * 预初始化application,通常是构造函数调用,用于构建类的一些变量参数
     * 
     * @method [preInit]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void ssPreInit(Map<String, Object> params);

    /**
     *  初始化application
     * 
     * @method [ssInit]
     * @retruntype [void]
     * @exception
     */
    public void ssInitEngine();

    /**
     * 初始化业务类
     * 
     
     * @method [ssInitBus]
     * @retruntype [void]
     * @exception
     */
    public void ssInitBus();

    /**
     * 初始化一些其他的东西
     * 
     
     * @method [ssInitOthers]
     * @retruntype [void]
     * @exception
     */
    public void ssInitOthers();

    /**
     * 启动应用所需要的service
     * 
     
     * @method [startService]
     * @retruntype [void]
     * @exception
     */
    public void ssStartService();
    
    
    /**
     * 绑定服务
     * @method [ssBindService]
     * @retruntype [void]
     * @exception 
     */
    public void ssBindService();
    
    /**
     * 服务解除绑定 
     * @method [ssUnBindService]
     * @retruntype [void]
     * @exception
     */
    public void ssUnBindService();

    /**
     * 停止service
     * 
     
     * @method [stopService]
     * @retruntype [void]
     * @exception
     */
    public void ssStopService();

    /**
     * 释放
     * 
     
     * @method [ssDispose]
     * @retruntype [void]
     * @exception
     */
    public void ssDispose();

    /**
     * 销毁
     * 
     * @method [ssDestroy]
     * @retruntype [void]
     * @exception
     */
    public void ssDestroy();
}
