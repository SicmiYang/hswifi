package com.nfyg.hswx.web.request;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by shengming.yang on 2015/12/4.
 */
public class HsJsonObjectRequest extends JsonObjectRequest {
    public HsJsonObjectRequest(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    @Override
    public void deliverError(VolleyError error) {
        if (error instanceof NoConnectionError){

            Cache.Entry entry = this.getCacheEntry();
            if (entry != null)
            {
                Response<JSONObject> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                deliverResponse(response.result);
                return ;
            }
        }

        super.deliverError(error);
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        super.deliverResponse(response);
    }
}
