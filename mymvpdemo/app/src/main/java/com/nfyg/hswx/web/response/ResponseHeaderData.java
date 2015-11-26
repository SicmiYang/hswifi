package com.nfyg.hswx.web.response;

/**
 * Created by pocktynox on 2015/6/8.
 * base response: hdata
 */
public class ResponseHeaderData {
    private int ver;
    private int errcode;
    private String errmsg;
    private String seqno;
    private int resv;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getResv() {
        return resv;
    }

    public void setResv(int resv) {
        this.resv = resv;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }
}
