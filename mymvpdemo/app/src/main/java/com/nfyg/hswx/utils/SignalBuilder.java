
package com.nfyg.hswx.utils;


import com.nfyg.hswx.biz.signals.Signal;
import com.nfyg.hswx.biz.signals.SignalListener;

public class SignalBuilder
{
    /**
     * 得到一个signal
     * 
     * @param listener
     * @param priority
     * @return
     * @retruntype [Signal]
     * @exception
     */
    public static Signal build(SignalListener listener , int priority)
    {
        Signal signal = new Signal();
        signal.addListener(listener, priority);
        return signal;
    }
}
