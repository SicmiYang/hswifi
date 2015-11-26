
package com.nfyg.hswx.fwinterface;

import com.nfyg.hswx.biz.signals.Signal;

import java.util.Map;


/**
 * 业务接口，通常用于处理与后端交互的API，本地数据库操作，以及一些缓存处理
 *   
 * @author  [y]
 */
public interface IBus
{

    /**
     * bus的test入口
     * 
     * @param api
     * @param dataBackSignal
     * @retruntype [void]
     * @exception
     */
    public void testBus(String api, Signal dataBackSignal);

    /**
     * 预初始化bus,通常是构造函数调用,用于构建类的一些变量参数
     * 
     * @author [y]
     * @method [preInit]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void preInit(Map<String, Object> params);

    /**
     * 初始化bus,通常是引擎调用
     * 
     * @author [y]
     * @method [init]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void init(Map<String, Object> params);

    /**
     * 重置bus,通常是引擎调用
     * 
     * @author [y]
     * @method [reset]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void reset(Map<String, Object> params);

    /**
     * 暂停bus,通常是引擎调用
     * 
     * @author [y]
     * @method [pause]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void pause(Map<String, Object> params);

    /**
     * 启动bus，通常是引擎调用
     * 
     * @author [y]
     * @method [start]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void start(Map<String, Object> params);

    /**
     * 释放bus,通常是引擎调用
     * 
     * @author [y]
     * @method [dispose]
     * @param params
     * @exception
     */
    public void dispose(Map<String, Object> params);

    /**
     * 销毁bus，通常是引擎调用
     * 
     * @author [y]
     * @method [destory]
     * @param params
     * @retruntype [void]
     * @exception
     */
    public void destory(Map<String, Object> params);
}
