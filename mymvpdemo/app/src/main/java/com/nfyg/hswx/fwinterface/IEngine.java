package com.nfyg.hswx.fwinterface;

import android.content.Context;

import com.nfyg.hswx.EngineOptions;

import java.util.Map;

public interface IEngine {

	/**
	 * 预初始化引擎,通常是构造函数调用,用于构建类的一些变量参数
	 * 
	 * @method [preInit]
	 * @param params
	 * @retruntype [void]
	 * @exception
	 */
	public void preInit(Map<String, Object> params);

	/**
	 * 初始化引擎
	 * 
	 * @method [initEngine]
	 * @param app
	 * @param optionsXml
	 * @retruntype [void]
	 * @exception
	 */
	public void initEngine(Context app, String optionsXml);

	/**
	 * 初始化引擎
	 * 
	 * @method [initEngine]
	 * @param app
	 * @param options
	 * @retruntype [void]
	 * @exception
	 */
	public void initEngine(Context app, EngineOptions options);

	/**
	 * 初始化引擎
	 * 
	 * @method [init]
	 * @param params
	 * @retruntype [void]
	 * @exception
	 */
	public void init(Map<String, Object> params);

	/**
	 * 重置引擎
	 * 
	 * @method [reset]
	 * @param params
	 * @retruntype [void]
	 * @exception
	 */
	public void reset(Map<String, Object> params);

	/**
	 * 暂停引擎
	 * 
	 * @method [pause]
	 * @param params
	 * @retruntype [void]
	 * @exception
	 */
	public void pause(Map<String, Object> params);

	/**
	 * 启动引擎
	 * 
	 * @method [start]
	 * @param params
	 * @retruntype [void]
	 * @exception
	 */
	public void start(Map<String, Object> params);

	/**
	 * 释放引擎
	 * 
	 * @method [dispose]
	 * @param params
	 * @exception
	 */
	public void dispose(Map<String, Object> params);

	/**
	 * 销毁引擎
	 * 
	 * @method [destory]
	 * @param params
	 * @retruntype [void]
	 * @exception
	 */
	public void destory(Map<String, Object> params);

	/**
	 * 退出应用程序
	 * 
	 * @param params
	 */
	public void exitApp(Map<String, Object> params);
}
