package com.nfyg.hswx.web.response;


import com.nfyg.hswx.web.response.model.ResponseRefreshUserData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * refresh user data parser
 */
public class RefreshUserDataParser extends JsonResponseParser<ResponseRefreshUserData> {
    @Override
    public ResponseRefreshUserData parseDData(JSONObject jsonObject) {
        ResponseRefreshUserData model = null;
        try {
            model = new ResponseRefreshUserData();
            model.setCode(jsonObject.getString("code"));
            model.setCodemsg(jsonObject.getString("codemsg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return model;
    }
}
