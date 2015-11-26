package com.nfyg.hswx.web.request;

import android.content.Context;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.biz.manager.SystemManager;
import com.nfyg.hswx.utils.common.ConstUtil;
import com.nfyg.hswx.web.BaseWebService;
import com.nfyg.hswx.web.rplistener.OnResponseListener3;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Base Request
 */
public abstract class BaseRequest<T> {
    protected Context baseApplication;
    protected String baseUrl = "http://mem.wode20.com/api2/wifiapp";
    protected String api;
    protected BaseWebService service;
    protected String seqNo;

    public BaseRequest(Context application, String api) {
        this.baseApplication = application;
        this.api = baseUrl + api;
        this.service = SystemManager.getBaseWebService();
        generateSeqNo();
    }

    public abstract void request(final OnResponseListener3<T> listener,JSONObject postData);

    private JSONObject getHeaderData() throws JSONException {
        JSONObject hdata = new JSONObject();

        hdata.put("ver", Engine.config.apiVer);
        hdata.put("aid", ConstUtil.API_APP_PRODUCT_ID);
        hdata.put("aver", ConstUtil.API_APP_VER);
        hdata.put("ostype", ConstUtil.API_OS_TYPE);
        hdata.put("dcode", ConstUtil.API_DCODE);
        hdata.put("seqno", seqNo);
        hdata.put("sessid", Engine.config.sessionId);
        hdata.put("resv", ConstUtil.API_RESV);

        return hdata;
    }

    protected JSONObject getPostData(JSONObject ddata) throws JSONException {
        JSONObject postData = new JSONObject();

        postData.put("hdata", getHeaderData());
        postData.put("ddata", ddata);

        return postData;
    }

    protected void generateSeqNo() {
        seqNo = Long.toString(DateTime.now().getMillis());
    }

    protected boolean isResponseCorrect(int code, String seq) {
        return code == 0 && isSeqCorrect(seq);
    }

    protected boolean isSeqCorrect(String seq) {
        return seqNo.equals(seq);
    }
}
