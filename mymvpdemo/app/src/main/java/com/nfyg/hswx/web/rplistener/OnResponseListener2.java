package com.nfyg.hswx.web.rplistener;

/**
 * Created by pocktynox on 2015/6/9.
 * 适用于HS数据
 */
public interface OnResponseListener2<T> {
    void onResponse(T result);
    void onError(String error);
}
