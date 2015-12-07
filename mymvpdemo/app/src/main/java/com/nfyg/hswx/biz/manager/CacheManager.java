package com.nfyg.hswx.biz.manager;

import android.graphics.Bitmap;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.utils.cacheUtils.ACache;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by shengming.yang on 2015/11/25.
 */
public class CacheManager<T> extends BaseManager {

    private String TAG = this.getClass().getSimpleName();
    public ACache aCache;

    private final static int CACHE_TIME_FOREVER = Integer.MAX_VALUE;

    public static final int CACHE_TIME_HOUR = 60 * 60;

    public static final int CACHE_TIME_DAY = CACHE_TIME_HOUR * 24;


    /**
     * 缓存数据
     *
     * @param key
     * @param objects
     */
    public void svae(String key, T objects) {

        if (objects instanceof Bitmap) {

            aCache.put(key, (Bitmap) objects);

        } else if (objects instanceof JSONObject) {

            aCache.put(key, (JSONObject) objects);

        } else if (objects instanceof JSONArray) {

            aCache.put(key, (JSONArray) objects);

        } else if (objects instanceof String) {

            aCache.put(key, (String) objects);

        } else if (objects instanceof Serializable) {

            aCache.put(key, (Serializable) objects);

        }

    }


    @Override
    public void preInit(Map<String, Object> params) {


    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        aCache = ACache.get(Engine.application);
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

        if (aCache != null) {

            aCache = null;
        }

    }
}
