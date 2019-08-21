package com.unity3d.unityAndroid;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayerActivity;

import adSdk.tengxun.BannerTengXun;

/*
* unity 的入口文件
* banner广告放在这里
* date: 2019-08-19
* author: cui
 *  */
public class UnityAppActivity extends UnityPlayerActivity { //UnityPlayerActivity  Activity

    private static UnityAppActivity unityAppActivity = null;

    @Override
    protected void onCreate(Bundle bundle) {
        unityAppActivity = this;
        super.onCreate(bundle);
//        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BannerTengXun.getInstance(this, this.mUnityPlayer).removeBanner("");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        BannerTengXun.getInstance(this, this.mUnityPlayer).onConfigurationChanged(newConfig);
    }

    public static UnityAppActivity getInstance(){
        return unityAppActivity;
    }

    public FrameLayout getBannerParent(){
        if(unityAppActivity == null){
            return  null;
        }
        return this.mUnityPlayer;
    }

}
