package com.nfyg.hswx.utils.httpUtils;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;
import com.nfyg.hswx.Engine;

/**
 * Created by shengming.yang on 2015/11/23.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    public BitmapCache() {

    }
    @Override
    public Bitmap getBitmap(String url) {

        return  Engine.cacheManager.aCache.getAsBitmap(url);
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {

        Engine.cacheManager.svae(url,bitmap);
    }
}
