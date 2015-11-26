package com.nfyg.hswx.web.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pocktynox on 2015/7/27.
 * 广告点击重定向请求
 */
public class AdClickRedirectVolleyRequest extends Request<JSONObject> {
    private Response.Listener<JSONObject> listener;

    public AdClickRedirectVolleyRequest(String url,
                                        Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        JSONObject res = new JSONObject();
        try {
            res.put("url", getUrl());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
    }
}
