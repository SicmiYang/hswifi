package com.nfyg.hswx.web.response;


import com.nfyg.hswx.utils.common.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * JsonResponseParser
 * 用于解析服务器返回的整个JSON数据
 */
public abstract class JsonResponseParser<T> {

    private ResponseData<T> responseData = new ResponseData<>();

    public ResponseData<T> parse(JSONObject jsonObject) throws JSONException {
        LogUtil.logDebug(this.getClass().getName(), "jsonObject: " + jsonObject);
        responseData.setHdata(parseHData(jsonObject.getJSONObject("hdata")));
        responseData.setDdata(parseDData(jsonObject.getJSONObject("ddata")));
        return responseData;
    };



    private ResponseHeaderData parseHData(JSONObject jsonObject) throws JSONException {
        ResponseHeaderData data = new ResponseHeaderData();

        data.setVer(jsonObject.getInt("ver"));
        data.setErrcode(jsonObject.getInt("errcode"));
        data.setErrmsg(jsonObject.getString("errmsg"));
        data.setSeqno(jsonObject.getString("seqno"));
        data.setResv(jsonObject.getInt("resv"));

        return data;
    }

    protected abstract T parseDData(JSONObject jsonObject) throws JSONException;
}
