package com.nfyg.hswx.web.rplistener;

/**
 * Created by pocktynox on 2015/6/9.
 * 用于程序调用银谷数据Request的ResponseListener
 */
public interface OnResponseListener3<T> {
    void onResponse(T result);
    void onError(String error);
}
