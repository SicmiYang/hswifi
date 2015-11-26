package com.nfyg.hswx.web.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.nfyg.hswx.utils.common.MD5Utils;
import com.nfyg.hswx.utils.common.ConstUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Json web Post request
 * 适用于银谷数据
 */
public class JsonWebPostRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> listener;
    private JSONObject params;

    public JsonWebPostRequest(String url, JSONObject params,
                              Response.Listener<JSONObject> responseListener,
                              Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.listener = responseListener;
        this.params = params;
        setShouldCache(false);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/x-www-form-urlencoded");
        headers.put("Accept", "application/json");
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
//        return super.getBody();
        String body = "_data=" + this.params + "&_sign=" + MD5Utils.encode(params.toString() + ConstUtil.APP_KEY);
        return body.getBytes();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }
}
