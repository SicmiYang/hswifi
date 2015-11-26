package com.nfyg.hswx.web.rplistener;

/**
 * Created by pocktynox on 2015/8/28.
 */
public interface OnFileDownloadListener<T> {
    void onResponse(T file);
    void onError(String error);
}
