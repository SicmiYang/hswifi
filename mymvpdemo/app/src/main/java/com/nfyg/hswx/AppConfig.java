
package com.nfyg.hswx;

public class AppConfig extends EngineOptions
{
    /**
     * log 开关
     */
    // public static final boolean LOG_OFF_ON = true;

    public AppConfig()
    {
        super();
        currStatus = RunningStatus.release;
        this.logLevel = INFO;

        /**
         * 应用版本
         */
        //this.appVer=SystemManager.getVersion().toString();
        /**
         * api版本
         */
        this.apiVer = 3;


        this.isTest = false;

        this.http_url = "http://192.168.1.80/v1/channel/hot";

        this.host = "http://192.168.1.80";

    }

}
