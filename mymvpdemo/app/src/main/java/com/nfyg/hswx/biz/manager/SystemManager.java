
package com.nfyg.hswx.biz.manager;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.nfyg.hswx.Engine;
import com.nfyg.hswx.utils.common.ConstUtil;
import com.nfyg.hswx.utils.common.SPValueUtils;
import com.nfyg.hswx.web.BaseWebService;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;


public class SystemManager extends BaseManager
{
    private static BaseWebService webService;
    private ActivityManager activityManager;

    private ConnectivityManager connectivityManager;

    private LocationManager locationManager;

    public SystemManager()
    {
        super();
        preInit(null);
    }

    /********************************************************************
     * 
     * 系统相关
     * 
     * 
     ********************************************************************/
    /**
     * sd卡是否存在
     */
    public boolean isExternalStorageAvailable()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 得到内存空间大小
     * 
     * @return
     */
    public int getMemSize()
    {
        return activityManager.getMemoryClass();
    }

    /**
     * [描述]:判断指定服务是否运行
     * 
     * @date [2014年4月1日 上午11:08:05]
     * @param name
     * @return
     * @exception
     */
    public boolean chkService(String name)
    {
        boolean running = false;
        ArrayList<RunningServiceInfo> runningServices = (ArrayList<RunningServiceInfo>) activityManager
                .getRunningServices(40);
        for (int i = 0; i < runningServices.size(); i++)
        {
            if (runningServices.get(i).service.getClassName().toString().equals(name))
            {
                running = true;
                break;
            }
        }
        return running;
    }

    /**
     * [描述]:获取前台的activity的类名
     * 
     * @date [2014年4月1日 上午11:09:41]
     * @return
     * @exception
     */
    public String getForegroundActivity()
    {
        RunningTaskInfo info = activityManager.getRunningTasks(1).get(0);
        // String shortClassName = info.topActivity.getShortClassName(); //类名
        String className = info.topActivity.getClassName(); // 完整类名
        // String packageName = info.topActivity.getPackageName(); //包名
        return className;
    }

    /**
     * 获取设备的信息
     * 
     * @return
     */
    public static String getDesc()
    {
        String retStr = "";
        retStr = "手机型号:" + android.os.Build.MODEL + ",SDK版本:" + android.os.Build.VERSION.SDK + ",系统版本:"
                + android.os.Build.VERSION.RELEASE;
        retStr = "{" + android.os.Build.MODEL + "}";
        return retStr;
    }

    /**
     * 获取本机的MAC地址
     * 
     * @return
     */
    public String getLocalMacAddress()
    {
        WifiManager wifi = (WifiManager) Engine.application.getApplicationContext().getSystemService(
                Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 获取本机的IP地址
     * 
     * @return
     */
    public static String getLocalIpAddress()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        // 去除百分号
                        String tmpStr = inetAddress.getHostAddress().toString();
                        return tmpStr.replace("%", "");
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            ex.printStackTrace();

        }
        return null;
    }

    /**
     * 隐藏输入法键盘
     * 
     * @param view
     */
    public void hideSoftInput(View view)
    {
        InputMethodManager imm = (InputMethodManager) Engine.application.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 返回分割后的字符串
     * 
     * @param splitStr
     *            分割的字符串
     * @param split
     *            分隔符
     * @return
     */
    public String splitStr(String splitStr , String split)
    {
        if (splitStr == null)
        {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        String strNum = splitStr.trim();
        String[] strNums = strNum.split(split);
        for (String str : strNums)
        {
            buffer.append(str);
        }
        return buffer.toString();
    }

    //    /**
    //     * 拨打联系电话
    //     */
    //    public void tel(final Context context , String phoneNumber)
    //    {
    //        Engine.viewManager.showDialog(new CustomTelDialogView(splitStr(phoneNumber, "-")));
    //    }

    /********************************************************************
     * 
     * ConnectivityManager 网络连接相关
     * 
     * 需添加权限 <uses-permission
     * android:name="android.permission.ACCESS_NETWORK_STATE"/>
     * 
     * 
     ********************************************************************/

    /**
     * [描述]:当前网络是否可用
     * 
     * @date [2014年4月1日 上午11:00:51]
     * @return
     * @exception
     */
    public boolean chkNetConnectedStatus()
    {
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected())
        {
            return true;
        }
        else
        {
            //            Engine.viewManager.dismissProgressDialog();
            return false;
        }

        // return info.isAvailable();
    }

    /**
     * [描述]:判断网络连接的方式
     * 
     * 
     * 
     * @date [2014年4月1日 上午10:46:19]
     * @return
     * @exception
     */
    public String getNetConnectedType()
    {
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        String name = "";
        switch (info.getType())
        {
            case ConnectivityManager.TYPE_WIFI:
                name = "WIFI";
                break;
            case ConnectivityManager.TYPE_MOBILE:
                name = "MOBILE";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }

    /**
     * [描述]:跳转网络的设置界面
     * 
     * @date [2014年4月1日 上午11:04:26]
     * @exception
     */
    public void gotoNetSetting()
    {
        // 跳转到无线网络设置界面
        Engine.application.getApplicationContext().startActivity(
                new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        // 跳转到无限wifi网络设置界面
        // Engine.application.getApplicationContext().startActivity(new
        // Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
    }

    /**
     * 按KEY获取MAINFASET.XML 中自定义的内容 <Application> ................ <meta-data
     * android:name="EMEAPP_FILEVERSION" android:value="1.0" ></meta-data>
     * ................ </Application>
     * 
     * @param key
     * @return
     */
    public static String getMetaData(String key)
    {
        String retStr = "";
        try
        {
            // PackageManager manager =
            // AppContext.getInstance().getPackageManager();
            ApplicationInfo ai = Engine.application.getPackageManager().getApplicationInfo(
                    Engine.application.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            retStr = String.valueOf(bundle.get(key));
        }
        catch (Exception e)
        {
        }
        return retStr;
    }

    /********************************************************************
     * 
     * LocationManager相关
     * 
     * 需添加权限 <uses-permission
     * android:name="android.permission.ACCESS_FINE_LOCATION" />
     * 
     ********************************************************************/
    /**
     * [描述]:判断当前gps是否打开
     * 
     * @date [2014年4月1日 上午10:28:35]
     * @return
     * @exception
     */
    public boolean chkGPSSettings()
    {

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * [描述]:跳转至gps设置的界面
     * 
     * @date [2014年4月1日 上午10:33:33]
     * @exception
     */
    public void gotoSetting()
    {
        Intent myIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        Engine.application.getApplicationContext().startActivity(myIntent);
    }

    /********************************************************************
     * 
     * 其它
     * 
     * 
     ********************************************************************/

    /**
     * [描述]:执行系统的命令
     * 
     * @date [2014年4月1日 上午11:11:16]
     * @param command
     * @exception
     */
    public void runCommand(String command)
    {
        try
        {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备的Density. <br/>
     * =============================================
     * 
     * @return
     */
    public float getWindowDensity()
    {
        return Engine.application.getApplicationContext().getResources().getDisplayMetrics().density;
    }

    /**
     * 获取设备的屏幕宽度
     * 
     * @return
     */
    public int getWindowWidthOfPix()
    {
        return Engine.application.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备的屏幕高度
     * 
     * @return
     */
    public int getWindowHeightOfPix()
    {
        return Engine.application.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
    }

    public int dip2px(float dipValue)
    {

        final float scale = Engine.application.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(float pxValue)
    {
        final float scale = Engine.application.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param pxValue
     * （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int px2sp(float pxValue)
    {
        final float fontScale = Engine.application.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int sp2px(float spValue)
    {
        final float fontScale = Engine.application.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 拨打电话
     * @param phoneNumber
     */
    public void tel(String phoneNumber)
    {
        Uri uri = Uri.parse("tel:" + splitStr(phoneNumber, "-"));

        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        Engine.application.startActivity(intent);
    }

    /**
     * 跳转网址
     * @param siteUrl 网址
     */
    public void website(String siteUrl)
    {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(siteUrl);
        intent.setData(content_url);
        Engine.application.startActivity(intent);
    }

    /********************************************************************
     * 
     * 接口方法
     * 
     * 
     ********************************************************************/
    @Override
    public void preInit(Map<String, Object> params)
    {
        activityManager = (ActivityManager) Engine.application.getApplicationContext().getSystemService(
                Context.ACTIVITY_SERVICE);
        connectivityManager = (ConnectivityManager) Engine.application.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        locationManager = ((LocationManager) Engine.application.getApplicationContext().getSystemService(
                Context.LOCATION_SERVICE));

    }

    @Override
    public void init(Map<String, Object> params)
    {
        super.init(params);

    }

    @Override
    public void reset(Map<String, Object> params)
    {

    }

    @Override
    public void pause(Map<String, Object> params)
    {

    }

    @Override
    public void start(Map<String, Object> params)
    {

    }

    @Override
    public void dispose(Map<String, Object> params)
    {

    }

    @Override
    public void destory(Map<String, Object> params)
    {
        super.destory(params);
        this.activityManager = null;
        this.connectivityManager = null;
        this.locationManager = null;
        if(webService!=null){
            webService = null;
        }

    }

    public static String getVersion()
    {
        Context context = Engine.application.getApplicationContext();
        try
        {

            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
            // return context.getString(R.string.version_unknown);
            return "";
        }
    }

    public static int getVersionCode()// 获取版本号(内部识别号)
    {
        Context context = Engine.application.getApplicationContext();
        try
        {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    private static Typeface ft;

    /*
     * 获取app的字体
     * 方正兰亭纤黑_GBK
     */
    public static Typeface getDefaultFont(Context context)
    {
        if (ft == null)
        {
            ft = Typeface.createFromAsset(context.getAssets(), "fonts/font1.TTF");
        }

        return ft;
    }

    /**
     * 数字加千分位
     * 
     * @param text
     * @return
     * @result String
     * @date 2014-5-14 下午2:44:51
     */
    public static String fmtNum(String text)
    {
        DecimalFormat df = null;
        if (text.indexOf(".") > 0)
        {
            if (text.length() - text.indexOf(".") - 1 == 0)
            {
                df = new DecimalFormat("###,##0.");
            }
            else if (text.length() - text.indexOf(".") - 1 == 1)
            {
                df = new DecimalFormat("###,##0.0");
            }
            else
            {
                df = new DecimalFormat("###,##0.00");
            }
        }
        else
        {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try
        {
            number = Double.parseDouble(text);
        }
        catch (Exception e)
        {
            number = 0.0;
        }
        return df.format(number);
    }

    /********************************************************************
     * 
     * 内存相关的方法
     * 
     * 
     ********************************************************************/

    /**
     * 获取内存的信息
     * 
     * 
     * @author [vincent]
     * @date   [2014年5月21日]
     * @retruntype [void]
     * @exception
     */
    public String getMemoryInfoStr()
    {
        System.gc();
        Runtime rt = Runtime.getRuntime();
        String info = "总内存=" + rt.totalMemory() / 1024 + "K, 已用=" + (rt.totalMemory() - rt.freeMemory()) / 1024
                + "K, 可用=" + rt.freeMemory() / 1024 + "K, 最大=" + rt.maxMemory() / 1024 + "K";

        info = ((float) (rt.totalMemory() - rt.freeMemory())) * 100 / rt.maxMemory() + "%    " + info;

        return info;
    }


    /**
     * 释放内存
     * 
     * 
     * @author [vincent]
     * @date   [2014年5月21日]
     * @param parent
     * @retruntype [void]
     * @exception
     */
    public void destroyDrawingCache(View parent)
    {
        if (parent == null)
            return;
        parent.destroyDrawingCache();
        if (parent instanceof ViewGroup)
        {
            ViewGroup tmp = (ViewGroup) parent;
            for (int i = 0; i < tmp.getChildCount(); i++)
            {
                if (tmp.getChildAt(i) instanceof ViewGroup)
                {
                    destroyDrawingCache(tmp.getChildAt(i));

                }
                else
                {
                    tmp.getChildAt(i).destroyDrawingCache();
                }
            }
        }
    }

    /**
     *  获取sessionKey
     *  
     */

    public static  String getSessionKey(){

        return SPValueUtils.readSPString(Engine.application,ConstUtil.SP_VALUE_SESSIONKEY);
    }


    /**
     * 获取web service
     * @return
     */
    public static BaseWebService getBaseWebService() {
        if (webService == null) {
            webService = new BaseWebService(Engine.application);
        }
        return webService;
    }

    /**
     * 视频缓存目录
     * @return
     */
    public static String getBufferDir(){
        String bufferDir = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/ProxyBuffer/files";
        return bufferDir;
    }



}
