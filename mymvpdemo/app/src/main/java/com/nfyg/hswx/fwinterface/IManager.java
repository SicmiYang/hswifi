package com.nfyg.hswx.fwinterface;

import java.util.Map;

public interface IManager
{

    /**
     * 预初始化manager,通常是构造函数调用,用于构建类的一些变量参数
     * 
     *  []
     * @method [preInit]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void preInit(Map<String, Object> params);

    /**
     * 初始化manager,通常是引擎调用
     * 
     *  []
     * @method [init]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void init(Map<String, Object> params);

    /**
     * 重置manager,通常是引擎调用
     * 
     * @method [reset]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void reset(Map<String, Object> params);

    /**
     * 暂停manager,通常是引擎调用
     * 
     * @method [pause]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void pause(Map<String, Object> params);

    /**
     * 启动manager，通常是引擎调用
     * 
     * @method [start]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void start(Map<String, Object> params);

    /**
     * 释放manager,通常是引擎调用
     * 
     * @method [dispose]
     * @param params
     * @exception
     */
    public void dispose(Map<String, Object> params);

    /**
     * 销毁manager，通常是引擎调用
     * 
     * @method [destory]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void destory(Map<String, Object> params);
}
