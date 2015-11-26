package com.nfyg.hswx.web.rplistener;

import com.nfyg.hswx.web.response.ResponseData;

/**
 * OnResponseListener
 * 适用于银谷数据
 */
public interface OnResponseListener<T> {
    void onResponse(ResponseData<T> result);
    void onError(String error);
}
