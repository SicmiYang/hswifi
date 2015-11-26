package com.nfyg.hswx.web.response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pocktynox on 2015/6/9.
 * 适用于HS数据
 */
public abstract class JsonResponseParser2<T> {
    public abstract T parse(JSONObject jsonObject) throws JSONException;
}
