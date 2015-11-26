package com.nfyg.hswx.utils.common;


import com.nfyg.hswx.BuildConfig;

/**
 * Created by pocktynox on 2015/5/11.
 * 全局常量
 */
public class ConstUtil {

    // appkey
    public final static String APP_KEY = "55e7c490641f3cbf46734361b5c5b980";

    // 开始下载文件
    public final static String BROADCAST_DOWNLOAD_FILE = "com.nfyg.hsbb.broadcast_download_file";

    // SP key: 显示开网对话框
    public final static String SP_VALUE_SHOW_OPEN_NET_DIALOG = "ShowOpenNetDialog";

    /**
     *  SP key:sessionKey
     */
    public final  static  String  SP_VALUE_SESSIONKEY = "hswx_sessionid";

    // 打开app广播
    public final static String BROADCAST_OPEN_APP = "com.nfyg.hsbb.broadcast_open_app";

    // 天气更新广播
    public final static String BROADCAST_WEATHER_UPDATE = "com.nfyg.hsbb:broadcast_weather_update";

    // 新用户?
    public final static String TAG_IS_NEW_USER = "is_new_user";

    //已设置标签
    public final static String TAG_SET_JPUSH_TAG = "is_set_jpushtag";

    // session time out broadcast
    public final static String BROADCAST_SESSION_TIME_OUT = "com.nfyg.hsbb.broadcast_session_time_out";

    // net open result broadcast
    public final static String BROADCAST_QUEUE_OPEN_NET_RESULT = "com.nfyg.hsbb.broadcast_queue_open_net_result";

    // tag queue open net result
    public final static String TAG_QUEUE_OPEN_NET_RESULT = "queue_open_net_result";

    // tag queue open net credit cost
    public final static String TAG_QUEUE_OPEN_NET_COST = "queue_open_net_cost";

    /*
     * API CONST
     */

    // app product id
    public final static int API_APP_PRODUCT_ID = 1;

    // os type
    public final static int API_OS_TYPE = 1;

    // dcode
    public final static String API_DCODE = "dcode";

    // app ver
    public final static String API_APP_VER = BuildConfig.VERSION_NAME;

    //api resv
    public final static int API_RESV = 0;

    /*
     * errors
     */

    // json exception
    public final static String ERROR_JSON = "JSONException";

    // seqno not equal
    public final static String ERROR_SEQNO_NOT_EQUAL = "SEQNO_NOT_EQUAL";

    /* channel no */
    private static String CHANNEL_ID = null;

//    public static String getChannelId(BaseApplication application) {
//        if (CHANNEL_ID != null) {
//            return CHANNEL_ID;
//        }
//
//        try {
//            Bundle metaData = application.getPackageManager().getApplicationInfo(
//                    application.getPackageName(), PackageManager.GET_META_DATA).metaData;
//            Object o = metaData.get("TD_CHANNEL_ID");
//            return CHANNEL_ID=o.toString();
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    // scan alarm broadcast
    public final static String BROADCAST_SCAN_ALARM = "com.nfyg.hsbb:broadcast_scan_alarm";

    // reset wifi status broadcast
    public final static String BROADCAST_RESET_WIFI_STATUS = "com.nfyg.hsbb:broadcast_reset_wifi_status";

    public final static String MESSAGE_PUSH_BROADCAST = "com.nfyg.hsbb:broadcast_push";
}
