
package com.nfyg.hswx.utils;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;

public class BundleBuilder
{
    /**
     * 工厂方法
     * 
     * @author [sicmi]
     * @method [build]
     * @return
     * @retruntype [BundleBuilder]
     * @exception
     */
    public static BundleBuilder build()
    {
        return new BundleBuilder();
    }

    private Bundle b;

    public BundleBuilder()
    {
        b = new Bundle();
    }

    public Bundle commit()
    {
        return b;
    }

    public BundleBuilder append(String key , boolean value)
    {
        b.putBoolean(key, value);
        return this;
    }

    public BundleBuilder append(String key , double value)
    {
        b.putDouble(key, value);
        return this;
    }

    public BundleBuilder append(String key , float value)
    {
        b.putFloat(key, value);
        return this;
    }

    public BundleBuilder append(String key , int value)
    {
        b.putInt(key, value);
        return this;
    }

    public BundleBuilder append(String key , long value)
    {
        b.putLong(key, value);
        return this;
    }

    public BundleBuilder append(String key , String value)
    {
        b.putString(key, value);
        return this;
    }
    public BundleBuilder append(String key , Serializable value)
    {
        b.putSerializable(key, value);
        return this;
    }
    
    public BundleBuilder append(String key,Parcelable value )
    {
        b.putParcelable(key, value);
        return this;
    }
}
