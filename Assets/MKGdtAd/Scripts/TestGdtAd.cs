using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TestGdtAd : MonoBehaviour
{
    public Button m_btn_initSdk;

    public Button m_btn_loadRewardVideo;
    public Button m_btn_showRewardVideo;
    public Button m_btn_rewardVideoComplete;

    public Button m_btn_loadBanner;
    public Button m_btn_showBanner;
    public Button m_btn_removeBanner;

    public Button m_btn_loadInterstitial;
    public Button m_btn_showInterstitial;
    public Button m_btn_interstitialLoadSuccess;

    public Text m_text_message;

    public Button m_btn_test_is_liuhai;//检测是否是刘海屏幕

    // Start is called before the first frame update
    void Start()
    {
        this.m_btn_initSdk.onClick.AddListener(this.onInitSdkBtn);

        this.m_btn_loadRewardVideo.onClick.AddListener(this.onLoadRewardVideoBtn);
        this.m_btn_showRewardVideo.onClick.AddListener(this.onShowRewardVideoBtn);
        this.m_btn_rewardVideoComplete.onClick.AddListener(this.onRewardVideoCompleteBtn);

        this.m_btn_loadBanner.onClick.AddListener(this.onLoadBannerBtn);
        this.m_btn_showBanner.onClick.AddListener(this.onShowBannerBtn);
        this.m_btn_removeBanner.onClick.AddListener(this.onRemoveBannerBtn);

        this.m_btn_loadInterstitial.onClick.AddListener(this.onLoadInterstitialBtn);
        this.m_btn_showInterstitial.onClick.AddListener(this.onShowInterstitialBtn);
        this.m_btn_interstitialLoadSuccess.onClick.AddListener(this.onInterstitialLoadSuccessBtn);

        this.m_btn_test_is_liuhai.onClick.AddListener(this.onIsLiuHaiScreenBtn);

    }

    // Update is called once per frame
    void Update()
    {
        
    }

    //--------------------------
    /// <summary>
    /// 初始化广告sdk
    /// </summary>
    private void onInitSdkBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        // 获得位于com.unity3d.player包下的UnityPlayer类，固定写法。
        AndroidJavaClass jc_unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");   // 参数是包名+类名
        // 获得jc所代表的类里的currentActivity对象，固定写法。这是Unity提供的classes.jar中的功能，可通过currentActivity获取到安卓端代表MainActivty的对象。
        AndroidJavaObject jo_activity = jc_unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");

        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
     
        jc_unityAdUtils.CallStatic("setShowToast", true);   //设置显示toast  正式包设置为false

        //AndroidJavaObject mNativeAdManager = jc.CallStatic<AndroidJavaObject>("getNativeAdManager");
        //参数依次为方法名, appid, splashid, bannerid, interstitialId, rewardvideoId
        jc_unityAdUtils.CallStatic("initAdConfig", "1101152570", "8863364436303842593", "4080052898050840", "3040652898151811", "5040942242835423");//<AndroidJavaObject>
        //参数为方法名， activity
        jc_unityAdUtils.CallStatic("initAllAd", jo_activity);
#endif
    }

    //------------------------------激励视频回调 begin---------------------
    /// <summary>
    /// 加载视频广告
    /// </summary>
    private void onLoadRewardVideoBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置
        jc_unityAdUtils.CallStatic("loadRewardVideoAd", "rewardVideoPos");
#endif
    }

    /// <summary>
    /// 展示视频广告
    /// </summary>
    private void onShowRewardVideoBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置， 节点名， 结束返回值的方法名
        jc_unityAdUtils.CallStatic("showRewardVideoAd", "RewardVideoPos", this.gameObject.name, "onRewardVideoWatchComplete");//播放结束之后会回调到onRewardVideoWatchComplete 必须是public
#endif
    }

    /// <summary>
    /// 视频广告播放完成回调
    /// </summary>
    public void onRewardVideoWatchComplete(string message)
    {
        Debug.Log("onRewardVideoWatchComplete" + message);
        this.m_text_message.text = message;
    }

    /// <summary>
    /// 视频广告是否加载完成
    /// </summary>
    private void onRewardVideoCompleteBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置
        bool isComplete = jc_unityAdUtils.CallStatic<bool>("isRewardVideoComplete", "RewardVideoPos");
        this.m_text_message.text = isComplete.ToString();
#endif
    }
    //------------------------------激励视频回调 end---------------------



    //------------------------------插屏回调 begin---------------------

    /// <summary>
    /// 加载插屏
    /// </summary>
    private void onLoadInterstitialBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置
        jc_unityAdUtils.CallStatic("loadInterstitialAd", "interstitialPos");
#endif
    }

    /// <summary>
    /// 展示插屏
    /// </summary>
    private void onShowInterstitialBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置
        jc_unityAdUtils.CallStatic("showInterstitialAd", "interstitialPos");
#endif
    }

    /// <summary>
    /// 插屏是否加载完成
    /// </summary>
    private void onInterstitialLoadSuccessBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 插屏位置
        bool isComplete = jc_unityAdUtils.CallStatic<bool>("isInterstitialLoadSuccess", "interstitialPos");
        this.m_text_message.text = isComplete.ToString();
#endif
    }

    //------------------------------插屏回调 end---------------------



    //-----------------------------banner begin---------------------
    /// <summary>
    /// 加载banner
    /// </summary>
    private void onLoadBannerBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置
        jc_unityAdUtils.CallStatic("loadBannerAd", "bannerPos");
#endif
    }

    /// <summary>
    /// 展示banner
    /// </summary>
    private void onShowBannerBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置
        jc_unityAdUtils.CallStatic("showBannerAd", "bannerPos");
#endif
    }

    /// <summary>
    /// 移除banner
    /// </summary>
    private void onRemoveBannerBtn()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        AndroidJavaClass jc_unityAdUtils = new AndroidJavaClass("adSdk.UnityAdUtils");
        //参数为方法名， 视频位置
        jc_unityAdUtils.CallStatic("removeBannerAd", "bannerPos");
#endif
    }

    //------------------------------banner begin---------------------



    //检测是否是刘海屏幕
    private void onIsLiuHaiScreenBtn()
    {
#if UNITY_IOS && !UNITY_EDITOR
        bool status = SystemIOS.isLiuHaiScreen();
        this.m_text_message.text = "刘海屏幕: " + status.ToString();
#endif
    }


}
