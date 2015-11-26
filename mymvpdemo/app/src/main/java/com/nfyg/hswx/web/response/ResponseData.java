package com.nfyg.hswx.web.response;

/**
 * Created by pocktynox on 2015/6/8.
 */
public class ResponseData<T> {
    private ResponseHeaderData hdata;
    private T ddata;

    public T getDdata() {
        return ddata;
    }

    public void setDdata(T ddata) {
        this.ddata = ddata;
    }

    public ResponseHeaderData getHdata() {
        return hdata;
    }

    public void setHdata(ResponseHeaderData hdata) {
        this.hdata = hdata;
    }
}
