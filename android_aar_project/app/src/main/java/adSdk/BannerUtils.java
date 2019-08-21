package adSdk;

import android.app.Activity;
import android.widget.FrameLayout;

import adSdk.tengxun.BannerTengXun;

/*
 * banner工具类  内部使用，接不同广告sdk时修改这里
 * date: 2019-08-19
 * author: cui
 * */
public class BannerUtils {

    /*
     * 初始化banner对象
     * */
    public static void initBannerAd(Activity mActivity, FrameLayout parent){
//        RewardVideoTengXun.getInstance(mActivity);
        BannerTengXun.getInstance(mActivity, parent);
    }

    /*
     * 加载banner
     * */
    public static void loadBannerAd(String bannerPos){
//        RewardVideoTengXun.loadRewardVideoAd(rewardVideoPos);
        BannerTengXun.loadBannerAd(bannerPos);
    }

    /*
     * 展示banner
     * */
    public static void showBannerAd(String bannerPos){
//        RewardVideoTengXun.showRewardVideoAd(rewardVideoPos, gameObjectName, methodName);
        BannerTengXun.showBannerAd(bannerPos);
    }

    /*
    * 移除banner
    * */
    public static void removeBanner(String bannerPos){
        BannerTengXun.removeBanner(bannerPos);
    }

}
