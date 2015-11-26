
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

    }

}
