package adSdk;

import android.app.Activity;

import adSdk.tengxun.InterstitialTengXun;

/*
* 激励视频工具类
* date: 2019-08-16
* author: 崔
* */
public class InterstitialUtils {

    /*
     * 初始化插屏对象
     * */
    public static void initInterstitialAd(Activity mActivity){
//        RewardVideoTengXun.getInstance(mActivity);
        InterstitialTengXun.getInstance(mActivity);
    }

    /*
     * 加载插屏
     * */
    public static void loadInterstitialAd(String interstitialPos){
//        RewardVideoTengXun.loadRewardVideoAd(rewardVideoPos);
        InterstitialTengXun.loadInterstitialAd(interstitialPos);
    }

    /*
     * 展示插屏
     * */
    public static void showInterstitialAd(String interstitialPos){
//        RewardVideoTengXun.showRewardVideoAd(rewardVideoPos, gameObjectName, methodName);
        InterstitialTengXun.showInterstitialAd(interstitialPos);
    }

    /*
     * 移除插屏
     * */
    public static void removeInterstitial(String interstitialPos){
        InterstitialTengXun.closeInterstitialAd(interstitialPos);
    }

    /*
    * 插屏是否加载好
    * */
    public static boolean isInterstitialSuccess(String interstitialPos){
        return InterstitialTengXun.interstitialLoadSuccess(interstitialPos);
    }

}
