package com.nfyg.hswx.web.request;

import android.content.Context;

import com.nfyg.hswx.utils.common.ConstUtil;
import com.nfyg.hswx.utils.httpUtils.HttpHeaderStatusUtil;
import com.nfyg.hswx.web.response.RefreshUserDataParser;
import com.nfyg.hswx.web.response.ResponseData;
import com.nfyg.hswx.web.response.ResponseHeaderData;
import com.nfyg.hswx.web.response.model.ResponseRefreshUserData;
import com.nfyg.hswx.web.rplistener.OnResponseListener;
import com.nfyg.hswx.web.rplistener.OnResponseListener3;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * refresh user request
 */
public class RefreshUserRequest extends BaseRequest<ResponseRefreshUserData> {

    public RefreshUserRequest(Context app) {
        super(app, "/freshuser1");
    }

    public void request(final OnResponseListener3<ResponseRefreshUserData> listener, JSONObject ddata) {

        JSONObject postData = null;
        try {
            postData = getPostData(ddata);
        } catch (JSONException e) {
            e.printStackTrace();

            listener.onError("json pares error");
        }

        service.jsonWebPost(api, postData, new RefreshUserDataParser(),
                new OnResponseListener<ResponseRefreshUserData>() {
                    @Override
                    public void onResponse(ResponseData<ResponseRefreshUserData> result) {
                        ResponseHeaderData hdata = result.getHdata();
                        if (isResponseCorrect(hdata.getErrcode(), hdata.getSeqno())) {
                            listener.onResponse(result.getDdata());
                        } else {
                            if (!isSeqCorrect(hdata.getSeqno())) {
                                listener.onError(ConstUtil.ERROR_SEQNO_NOT_EQUAL);
                            } else {
                                listener.onError(HttpHeaderStatusUtil.parseHeaderStatus(hdata.getErrcode()));
                            }
                        }
                    }

                    @Override
                    public void onError(String error) {
                        listener.onError(error);
                    }
                });
    }
}
