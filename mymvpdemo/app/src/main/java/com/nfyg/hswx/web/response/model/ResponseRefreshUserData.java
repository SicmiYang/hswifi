package com.nfyg.hswx.web.response.model;

import java.io.Serializable;

/**
 * refresh user data
 */
public class ResponseRefreshUserData implements Serializable{
    private String code;
    private String codemsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodemsg() {
        return codemsg;
    }

    public void setCodemsg(String codemsg) {
        this.codemsg = codemsg;
    }
}
