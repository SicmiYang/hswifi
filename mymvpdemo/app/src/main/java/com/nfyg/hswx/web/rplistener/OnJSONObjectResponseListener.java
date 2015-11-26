package com.nfyg.hswx.web.rplistener;

import org.json.JSONObject;

/**
 * Created by wyq on 15-9-10.
 */
public interface OnJSONObjectResponseListener {
    void onResponse(JSONObject jsonObject);
    void onError(String error);
}
