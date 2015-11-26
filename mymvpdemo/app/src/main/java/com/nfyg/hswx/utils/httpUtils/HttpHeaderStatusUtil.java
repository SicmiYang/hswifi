package com.nfyg.hswx.utils.httpUtils;

import java.util.HashMap;

/**
 * Created by pocktynox on 2015/6/8.
 * 请求头状态解析
 */
public class HttpHeaderStatusUtil {

    public final static String SUCCESS = "SUCCESS";
    public final static String ERROR_MISS_PARAMS = "MISS PARAMS";
    public final static String ERROR_INVALID_PID = "INVALID PRODUCT ID";
    public final static String ERROR_INVALID_API_VER = "INVALID API VER";
    public final static String ERROR_SESSION_TIMEOUT = "SESSION TIMEOUT";
    public final static String ERROR_DATA_STRUCTURE = "DATA STRUCTURE ERROR";

    public final static String ERROR_UNKNOWN = "UNKNOWN ERROR CODE";

    private final static HashMap<Integer, String> errors = new HashMap<>();

    static {
        errors.put(0, SUCCESS);
        errors.put(1, ERROR_MISS_PARAMS);
        errors.put(2, ERROR_INVALID_PID);
        errors.put(3, ERROR_INVALID_API_VER);
        errors.put(4, ERROR_SESSION_TIMEOUT);
        errors.put(5, ERROR_DATA_STRUCTURE);

    }

    public static String parseHeaderStatus(int status) {
        if (errors.containsKey(status)) {
            return errors.get(status);
        } else {
            return ERROR_UNKNOWN;
        }
    }
}
