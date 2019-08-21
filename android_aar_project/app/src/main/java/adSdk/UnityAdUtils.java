package adSdk;

import android.app.Activity;
import android.widget.Toast;

import com.unity3d.unityAndroid.UnityAppActivity;

/*
* 广告工具类,unity调用
* date: 2019-08-19
* author: cui
* */
public class UnityAdUtils {

    private static boolean m_isShowToast = false;

    public static boolean getIsShowToast()
    {
        return m_isShowToast;
    }

    //初始化广告
    public static void initAdConfig(String appId, String splashId, String bannerId, String interstitialId, String rewardVideoId){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "初始化广告位, appId = " + appId, Toast.LENGTH_LONG).show();
        }
        AdConfig.getInstance().setAppId(appId);
        AdConfig.getInstance().setSplashId(splashId);
        AdConfig.getInstance().setBannerId(bannerId);
        AdConfig.getInstance().setInterstitialId(interstitialId);
        AdConfig.getInstance().setRewardVideoId(rewardVideoId);
    }

    /*
    * 设置是否展示toast
    * */
    public static void setShowToast(boolean status)
    {
        m_isShowToast = status;
        Toast.makeText(UnityAppActivity.getInstance(), "setShowToast, status = " + status, Toast.LENGTH_LONG).show();
    }

    /*
    * 初始化广告对象
    * */
    public static void initAllAd(Activity mActivity){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "初始化广告对象", Toast.LENGTH_LONG).show();
        }
        RewardVideoUtils.initRewardVideo(mActivity);
        BannerUtils.initBannerAd(mActivity, UnityAppActivity.getInstance().getBannerParent());
        InterstitialUtils.initInterstitialAd(mActivity);
    }

    //-----------banner begin------------------------
    //加载banner广告
    public static void loadBannerAd(String bannerPos){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "加载banner广告, pos = " + bannerPos, Toast.LENGTH_LONG).show();
        }
        BannerUtils.loadBannerAd(bannerPos);
    }

    //展示banner广告
    public static void showBannerAd(String bannerPos){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "展示banner广告, pos = " + bannerPos, Toast.LENGTH_LONG).show();
        }
        BannerUtils.showBannerAd(bannerPos);
    }

    //移除banner广告
    public static void removeBannerAd(String bannerPos){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "移除banner广告, pos = " + bannerPos, Toast.LENGTH_LONG).show();
        }
        BannerUtils.removeBanner(bannerPos);
    }
    //-----------banner end------------------------


    //-------------插屏 begin----------------
    //加载插屏广告
    public static void loadInterstitialAd(String interstitialPos){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "加载插屏广告, pos = " + interstitialPos, Toast.LENGTH_LONG).show();
        }
        InterstitialUtils.loadInterstitialAd(interstitialPos);
    }

    //展示插屏广告
    public static void showInterstitialAd(String interstitialPos){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "展示插屏广告, pos = " + interstitialPos, Toast.LENGTH_LONG).show();
        }
        InterstitialUtils.showInterstitialAd(interstitialPos);
    }

    //插屏广告是否加载好
    public static boolean isInterstitialLoadSuccess(String interstitialPos){
        boolean status = InterstitialUtils.isInterstitialSuccess(interstitialPos);
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "插屏广告是否加载好, pos = " + interstitialPos + ", status = " + status, Toast.LENGTH_LONG).show();
        }
        return status;
    }
    //-------------插屏 end----------------


    //-------------激励视频 begin----------------
    //加载视频广告
    public static void loadRewardVideoAd(String rewardVideoPos){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "加载激励视频, pos = " + rewardVideoPos, Toast.LENGTH_LONG).show();
        }
        RewardVideoUtils.loadRewardVideoAd(rewardVideoPos);
    }

    //展示视频广告
    public static void showRewardVideoAd(String rewardVideoPos, String gameObjectName, String methodName){
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "展示激励视频, pos = " + rewardVideoPos, Toast.LENGTH_LONG).show();
        }
        RewardVideoUtils.showRewardVideoAd(rewardVideoPos, gameObjectName, methodName);
    }

    //激励视频是否加载好
    public static boolean isRewardVideoComplete(String rewardVideoPos){
        boolean status = RewardVideoUtils.isRewardVideoComplete(rewardVideoPos);
        if(m_isShowToast){
            Toast.makeText(UnityAppActivity.getInstance(), "激励视频是否加载好, pos = " + rewardVideoPos + ", status = " + status, Toast.LENGTH_LONG).show();
        }
        return status;
    }
    //-------------激励视频 end----------------

}
