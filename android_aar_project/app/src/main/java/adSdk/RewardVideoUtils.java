package adSdk;

import android.app.Activity;

import adSdk.tengxun.RewardVideoTengXun;

/*
* 激励视频工具类  内部使用，接不同广告sdk时修改这里
* date: 2019-08-19
* author: cui
* */
public class RewardVideoUtils {

    /*
    * 初始化激励视频对象
    * */
    public static void initRewardVideo(Activity mActivity){
        RewardVideoTengXun.getInstance(mActivity);
    }

    /*
     * 加载激励视频
     * */
    public static void loadRewardVideoAd(String rewardVideoPos){
        RewardVideoTengXun.loadRewardVideoAd(rewardVideoPos);
    }

    /*
     * 展示激励视频
     * */
    public static void showRewardVideoAd(String rewardVideoPos, String gameObjectName, String methodName){
        RewardVideoTengXun.showRewardVideoAd(rewardVideoPos, gameObjectName, methodName);
    }

    /*
     * 激励视频是否加载完成
     * */
    public static boolean isRewardVideoComplete(String rewardVideoPos){
        return RewardVideoTengXun.isRewardVideoComplete(rewardVideoPos);
    }

}
