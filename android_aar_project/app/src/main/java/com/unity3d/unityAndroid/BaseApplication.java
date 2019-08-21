package com.unity3d.unityAndroid;

import android.app.Application;
import android.content.Context;

/*
 * Application入口文件
 * date: 2019-08-19
 * author: 崔
 * */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();//5d42a9c84ca3578d4e000458    5d4287583fc1952efe0006c2

//        UMU3DCommonSDK.init(this, "5d4287583fc1952efe0006c2", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
//                null);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
