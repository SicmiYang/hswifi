package com.nfyg.hswx.web.rplistener;

import java.util.ArrayList;

/**
 * JsonArrayResponse listener;
 */
public interface OnArrayResponseListener<T> {
    void onResponse(ArrayList<T> result);
    void onError(String error);
}
