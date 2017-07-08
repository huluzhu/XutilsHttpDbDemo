package com.baway.xutilshttpdbdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 胡计强 on 2017/07/07.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
