package com.nfyg.hswx.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SPValueUtils
 */
public class SPValueUtils {
    public static String readSPString(Context context, String k) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getString(k, "");
    }

    public static String readSPString(Context context, String k, String dv) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getString(k, dv);
    }

    public static String readSPString(Context context, String k, String dv, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        return pref.getString(k, dv);
    }

    public static int readSPInt(Context context, String k) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getInt(k, 0);
    }

    public static int readSPInt(Context context, String k, int dv) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getInt(k, dv);
    }

    public static int readSPInt(Context context, String k, int dv, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        return pref.getInt(k, dv);
    }

    public static long readSPLong(Context context, String k) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getLong(k, 0l);
    }

    public static long readSPLong(Context context, String k, long dv) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getLong(k, dv);
    }

    public static long readSPLong(Context context, String k, long dv, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        return pref.getLong(k, dv);
    }

    public static float readSPFloat(Context context, String k) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getFloat(k, 0f);
    }

    public static float readSPFloat(Context context, String k, float dv) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getFloat(k, dv);
    }

    public static float readSPFloat(Context context, String k, float dv, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        return pref.getFloat(k, dv);
    }

    public static boolean readSPBoolean(Context context, String k) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getBoolean(k, false);
    }

    public static boolean readSPBoolean(Context context, String k, boolean dv) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getBoolean(k, dv);
    }

    public static boolean readSPBoolean(Context context, String k, boolean dv, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        return pref.getBoolean(k, dv);
    }

    public static void saveSPString(Context context, String k, String v) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(k, v);
        editor.apply();
    }

    public static void saveSPString(Context context, String k, String v, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(k, v);
        editor.apply();
    }

    public static void saveSPInt(Context context, String k, int v) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(k, v);
        editor.apply();
    }

    public static void saveSPInt(Context context, String k, int v, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(k, v);
        editor.apply();
    }

    public static void saveSPLong(Context context, String k, long v) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(k, v);
        editor.apply();
    }

    public static void saveSPLong(Context context, String k, long v, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(k, v);
        editor.apply();
    }

    public static void saveSPFloat(Context context, String k, float v) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(k, v);
        editor.apply();
    }

    public static void saveSPFloat(Context context, String k, float v, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(k, v);
        editor.apply();
    }

    public static void saveSPBoolean(Context context, String k, boolean v) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(k, v);
        editor.apply();
    }

    public static void saveSPBoolean(Context context, String k, boolean v, int m) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, m);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(k, v);
        editor.apply();
    }

    public static void removeSPValue(Context context, String... key) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        for (String k : key) {
            editor.remove(k);
        }
        editor.apply();
    }

    private final static String SHARED_PREFERENCE = "com.nfyg.hsbb";
}
