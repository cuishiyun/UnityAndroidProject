package adSdk;

/*
* 广告配置入口
* date: 2019-08-19
* author: cui
* */
public class AdConfig{

    private String AppId = "1101152570";
    private String SplashId = "8863364436303842593";
    private String BannerId = "4080052898050840";
    private String InterstitialId = "3040652898151811";
    private String RewardVideoId = "5040942242835423";

    private static AdConfig mInstance = null;

    public static AdConfig getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new AdConfig();
        }
        return mInstance;
    }

    private AdConfig(){

    }

    //设置AppId
   public void setAppId(String appId){
       this.AppId = appId;
   }

   public String getAppId()
   {
       return  this.AppId;
   }

   //设置闪屏id
   public void setSplashId(String splashId)
   {
       this.SplashId = splashId;
   }

   public String getSplashId()
   {
       return  this.SplashId;
   }

   //设置banner广告位id
    public void setBannerId(String bannerId){
        this.BannerId = bannerId;
    }

    public String getBannerId()
    {
        return  this.BannerId;
    }

    //设置插屏id
    public void setInterstitialId(String interstitialId){
        this.InterstitialId = interstitialId;
    }

    public String getInterstitialId()
    {
        return this.InterstitialId;
    }

    //设置激励视频id
    public void setRewardVideoId(String rewardVideoId){
        this.RewardVideoId = rewardVideoId;
    }

    public String getRewardVideoId()
    {
        return this.RewardVideoId;
    }

}