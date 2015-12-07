package com.nfyg.hswx.biz.manager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nfyg.hswx.Engine;

import java.util.Map;

/**
 *
 */
public class RequestManager extends BaseManager{
	
	/**
	 * the queue
	 */
	private static RequestQueue mRequestQueue;

	private RequestManager() {
	 // no instances
	} 


	/**
	 * @return
	 * 		instance of the queue
	 * @throws
	 */
	public static RequestQueue getRequestQueue() {
	    if (mRequestQueue != null) {
	        return mRequestQueue;
	    } else {
	        throw new IllegalStateException("Not initialized");
	    }
	}

	@Override
	public void preInit(Map<String, Object> params) {

	}

	@Override
	public void init(Map<String, Object> params) {
		super.init(params);
		mRequestQueue = Volley.newRequestQueue(Engine.application);
	}

	@Override
	public void reset(Map<String, Object> params) {

	}

	@Override
	public void pause(Map<String, Object> params) {

	}

	@Override
	public void start(Map<String, Object> params) {

	}

	@Override
	public void dispose(Map<String, Object> params) {
		if (mRequestQueue!=null){

			//mRequestQueue.
		}

	}
}
