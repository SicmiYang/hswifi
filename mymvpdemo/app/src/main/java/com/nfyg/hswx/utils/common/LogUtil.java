package com.nfyg.hswx.utils.common;

import android.util.Log;

import com.nfyg.hswx.BuildConfig;


/**
 * Created by pocktynox on 2015/4/23.
 * This is a log util. you can use it to print message in device logcat
 */
public class LogUtil {
    public static void logDebug(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.d(tag, msg);
        }
    }

    public static void logDebug(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.d(tag, msg, tr);
        }
    }

    public static void logInfo(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.i(tag, msg);
        }
    }

    public static void logInfo(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.i(tag, msg, tr);
        }
    }

    public static void logError(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.e(tag, msg);
        }
    }

    public static void logError(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.e(tag, msg, tr);
        }
    }

    public static void logVerbose(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.v(tag, msg);
        }
    }

    public static void logVerbose(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.v(tag, msg, tr);
        }
    }

    public static void logWarn(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.w(tag, msg);
        }
    }

    public static void logWarm(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.w(tag, msg, tr);
        }
    }

    public static void logWtf(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.wtf(tag, msg);
        }
    }

    public static void logWtf(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.wtf(tag, msg, tr);
        }
    }
}
