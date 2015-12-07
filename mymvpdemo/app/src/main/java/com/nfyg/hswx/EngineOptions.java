package com.nfyg.hswx;

import android.app.Activity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


public class EngineOptions
{
    static public final float nanoToSec = 1 / 1000000000f;

    static public final float nanoToMSec = 1 / 1000000f;

    /**
     * HttpProxy 相关
     */
    final static public String LOCAL_IP_ADDRESS = "127.0.0.1";
    final static public int HTTP_PORT = 80;
    final static public String HTTP_BODY_END = "\r\n\r\n";
    final static public String HTTP_RESPONSE_BEGIN = "HTTP/1.1 ";
    final static public String HTTP_REQUEST_BEGIN = "GET ";
    final static public String HTTP_REQUEST_LINE1_END = " HTTP/";

    public static final int choose_photo_one = 1;

    public static final int choose_photo_lst = 2;

    public static final int take_photo_one = 3;

    public static final int camera_photo_one = 4;

    public static final int rcode_photo_one = 5;

    public static final int crop_photo_one = 6;

    public enum RunningStatus {
        debug, test, release
    }

    public enum CacheType {
        Image, Data, SP
    }

    public enum ApplicationType {
        Android, Desktop, Applet, WebGL, iOS
    }

    /**
     * [描述]:资源类型
     * 
     */
    public enum ResourceType {
        png, jpg, bitmap, Color, String, Dimen, Style, Json, Audio, Video, Apk, other
    }

    /**
     * 从url得到文件类型
     * 
     * @param url
     * @return
     * @retruntype [ResourceType]
     * @exception
     */
    public static ResourceType getImageType(String url)
    {
        if (!TextUtils.isEmpty(url))
        {
            if (url.endsWith("png") || url.endsWith("PNG"))
            {
                return ResourceType.png;
            }
            else if (url.endsWith("jpg") || url.endsWith("JPG"))
            {
                return ResourceType.jpg;
            }
            else
            {
                return ResourceType.other;
            }
        }
        return ResourceType.bitmap;
    }

    // 文件后缀名
    public static final String SUFFIX_IMAGE_PNG = "png";

    public static final String SUFFIX_IMAGE_JPG = "jpg";

    public static final String SUFFIX_IMAGE_AMR = "amr";

    public static final String[] allowedContentTypes = new String[] { "application/octet-stream", "image/x-png",
            "image/gif", "image/jpg", "image/jpeg", "audio/amr", "audio/mp3", "audio/3gpp", "text/html", "text/css" };

    /**
     * 从文件类型得到对应的contentType
     * 
     * 参考：http://my.oschina.net/oncereply/blog/12718
     * 
     * @param type
     * @return
     * @retruntype [String]
     * @exception
     */
    public static String getResContentType(ResourceType type)
    {
        switch (type)
        {
            case Video:
                return "";
            case png:
                return "image/x-png";
            case jpg:
                return "image/jpg";
            case Color:
                return "text/html";
            case String:
                return "text/html";
            case Dimen:
                return "text/html";
            case Style:
                return "text/html";
            case Json:
                return "application/json";

            default:
                return "application/octet-stream";

        }
    }

    public enum FileType {
        /**
         * Path relative to the root of the classpath. Classpath files are
         * always readonly. Note that classpath files are not compatible with
         * some functionality on Android, such as
         */
        Classpath,

        /**
         * Path relative to the asset directory on Android and to the
         * application's root directory on the desktop. On the desktop, if the
         * file is not found, then the classpath is checked. This enables files
         * to be found when using JWS or applets. Internal files are always
         * readonly.
         */
        Internal,

        /**
         * Path relative to the root of the SD card on Android and to the home
         * directory of the current user on the desktop.
         */
        External,

        /**
         * Path that is a fully qualified, absolute filesystem path. To ensure
         * portability across platforms use absolute files only when absolutely
         * (heh) necessary.
         */
        Absolute,

        /**
         * Path relative to the private files directory on Android and to the
         * application's root directory on the desktop.
         */
        Local;
    }

    // cache相关
    public String spName = "spCache"; // 共享缓存名称

    public String spUserName = "userInfo"; // 共享缓存名称

    public int spMode = Activity.MODE_PRIVATE; // 共享缓存模式

    public int imageMemPre = 8; // 图片缓存百分比

    public String tmpPath = "";

    // log相关
    static public final int INFO = 1;

    static public final int DEBUG = 2;

    static public final int WARN = 3;

    static public final int ERROR = 4;

    static public final int NONE = 9;

    public int logLevel = INFO;

    public String mainTag = "com.app";

    // 数据库相关
    public String dbPath = ""; // 数据库文件所在路径

    public String ldbName = ""; // 数据库名称

    public int ldbVersion = 1; // 数据库版本

    public List<Class<?>> dbmodelClassLst = new ArrayList<Class<?>>(); // 本地数据库对应的实体类Class

    // rpc相关
    public static final String LINK_TYPE_HTTP = "http";

    public static final String LINK_TYPE_SOCKET = "socket";

    public static final String LINK_TYPE_WEBSOCKET = "websocket";

    public static final String DATA_TYPE_JSON = "json";

    public static final String DATA_TYPE_JSONP = "jsonp";

    public static final String DATA_TYPE_XML = "xml";

    public static final String DATA_TYPE_BINARY = "binary";

    public final static String GET = "GET";

    public final static String POST = "POST";

    public final static String POSTFILE = "POSTFILE";

    public int httpPoolSize = 3; // http加载器池大小

    public int resPoolSize = 3; // 资源加载器大小

    public int uploadPoolSize = 3; // 资源加载器大小

    /******************************************************
     * 
     * 系统编码相关
     * 
     * 
     ******************************************************/
    public static final int server_code_success = 0;

    public static final int server_code_paramsError = 1;

    public static final int server_code_urlError = 2;

    public static final int server_code_authError = 1000;

    public static final int server_code_serverError = 1001;

    public static final int server_code_noLogin = 1002;

    public static final int server_code_appCodeError = 1003;

    public static final int server_code_tokenError = 1004;

    public static final int server_code_apiError = 1005;

    public static final int server_code_jsonError = 1006;

    // 标示相关
    public final static String flag_notify_adapter = "notifyAdapter";

    public final static String back_data_flag = "backDataFlag";

    public final static String flag_back_home = "backHome";

    public final static String listData = "listData";

    public final static int image_success = 1;

    public final static String flag_flag = "flag";

    public final static String flag_data = "data";

    public static final String flag_msg = "msg";

    public final static String flag_viewStack = "viewStack";

    public final static String flag_keyCode = "keyCode";

    public final static String flag_success = "success";

    public final static String flag_failure = "failure";

    public static final String flag_isSuccess = "isSuccess";

    public static final String flag_errorType = "errorType";

    public static final String flag_errorType_sys = "errorType_sys";

    public static final String flag_errorType_bus = "errorType_bus";

    public static final String flag_error_unkonw_resourcetype = "flag_error_unkonw_resourcetype";

    public static final String flag_local = "local";

    public static final String flag_net = "net";

    // 服务端返回API的标记
    public final static String flag_api_rsflag = "rsflag";

    public final static String flag_api_message = "message";

    public final static String flag_api_code = "code";

    public final static String flag_api_rs = "rs";

    public final static String flag_api_data = "data";

    // 系统信号常量相关
    public final static String sys_net_err = "sys.net.err"; // 网络连接错误

    public final static String sys_net_ok = "sys.net.ok"; // 网络连接成功

    public final static String sys_engine_err = "sys.engine.err";

    public final static String sys_engine_ok = "sys.engine.ok";

    public final static String sys_helper_err = "sys.helper.err";

    public final static String sys_helper_ok = "sys.helper.ok";

    public final static String sys_manager_err = "sys.manager.err";

    public final static String sys_manager_ok = "sys.manager.ok";

    public final static String sys_module_err = "sys.module.err";

    public final static String sys_module_ok = "sys.module.ok";

    public final static String sys_bus_err = "sys.bus.err";

    public final static String sys_bus_ok = "sys.bus.ok";

    public final static String sys_loader_http_err = "sys.loader.http.err"; // http请求错误

    public final static String sys_loader_http_ok = "sys.loader.http.ok"; // http请求成功

    public final static String sys_loader_res_err = "sys.loader.res.err"; // res请求错误

    public final static String sys_loader_res_ok = "sys.loader.res.ok"; // res请求成功

    public final static String sys_upload_err = "sys.upload.err"; // 上传错误

    public final static String sys_upload_ok = "sys.upload.ok"; // 上传成功

    public final static String sys_socket_err = "sys.socket.err";

    public final static String sys_socket_ok = "sys.socket.ok";

    public final static String sys_parase_err = "sys.parase.err";

    public final static String sys_parase_ok = "sys.parase.ok";

    public final static String sys_other_err = "sys.other.err";

    //

    // 消息相关
    public final static String msg_common_btn_ok = "msg.common.btn.ok";

    public final static String msg_common_btn_cancel = "msg.common.btn.cancel";

    // 国际化
    public final static String lang_zhCN = "zhCN";

    public final static String lang_en = "en";

    // 应用程序所需的本地目录结构
    public List<String> sdFilePath = new ArrayList<String>();

    public EngineOptions()
    {

    }

    /*********************************************************************************
     * 
     * 应用相关
     * 
     * 
     *********************************************************************************/
    /**
     * [描述]:当前运行的状态（调试，发布，测试）
     * 
     */
    public RunningStatus currStatus;

    /**
     * 应用编码
     */
    public String appCode;

    /**
     * 应用版本
     */
    public String appVer;

    /**
     * api版本
     */
    public int apiVer;

    /**
     * 用户登录后的ID
     */
    public String userId;

    /**
     * 用户登录后的sessionId
     */
    public String sessionId;

    /**
     * 应用是否处于测试状态
     */
    public boolean isTest = true;

    /**
     * 当前是否有网络
     */
    public boolean hasNet = true;

    /**
     * 正式API地址
     */
    public String http_url;

    /**
     * 正式upload地址
     */
    public String upload_url;

    /**
     * 测试API地址
     */
    public String test_http_url;

    /**
     * 测试upload地址
     */
    public String test_upload_url;

    /**
     * 主机地址
     */
    public String host;
}
