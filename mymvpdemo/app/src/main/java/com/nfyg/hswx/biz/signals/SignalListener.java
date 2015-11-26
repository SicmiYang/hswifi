
package com.nfyg.hswx.biz.signals;

import android.os.Message;

/**
 * 在SignalListener中的execute方法内不允许启用新线程操作
 *	
 * @version [版本号,2014年5月22日]
 *
 */
public abstract class SignalListener
{

    protected int priority;

    public SignalListener()
    {
        this(0);
    }

    public SignalListener(int priority)
    {
        this.priority = priority;
    }

    public abstract void execute(Signal signal , Message msg);

}
