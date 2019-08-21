package adSdk.tengxun;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.widget.CompoundButton;

import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.util.AdError;
import com.unity3d.player.UnityPlayer;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import adSdk.AdConfig;

/**
 * 腾讯激励视频广告
 * 激励视频广告基本接入示例，演示了基本的激励视频广告功能（1.初始化激励视频广告;2.加载激励视频广告;3.展示激励视频广告）。
 * <p>
 *     date: 2019-08-19
 *     author: cui
 */

public class RewardVideoTengXun implements RewardVideoADListener,
        CompoundButton.OnCheckedChangeListener {

    private static RewardVideoTengXun mInstance; //实例
    private static final String TAG = RewardVideoTengXun.class.getSimpleName();
    private static RewardVideoAD rewardVideoAD; //激励视频广告
    private static boolean adLoaded;//广告加载成功标志
    private static boolean videoCached;//视频素材文件下载完成标志
    private static Activity m_activity;

    private static String curRewardVideoPos = "";//当前播放激励视频的位置
    private static String gameObjectName = "";//unity对象名
    private static String methodName = "";//unity接收java消息的方法名

    public static RewardVideoTengXun getInstance(Activity activity)
    {
        if(mInstance == null)
        {
            mInstance = new RewardVideoTengXun(activity);
        }
        return  mInstance;
    }

    private RewardVideoTengXun(Activity activity)
    {
//        mInstance = this;
        this.m_activity = activity;
        adLoaded = false;
        videoCached = false;
    }

    private static String getPosID() {
        return AdConfig.getInstance().getRewardVideoId();
    }

    /**
     * 广告加载成功，可在此回调后进行广告展示
     **/
    @Override
    public void onADLoad() {
        adLoaded = true;
        String msg = "load ad success ! expireTime = " + new Date(System.currentTimeMillis() +
                rewardVideoAD.getExpireTimestamp() - SystemClock.elapsedRealtime());
//        Toast.makeText(m_activity, msg, Toast.LENGTH_LONG).show();
        Log.d(TAG,  "eCPM = " + rewardVideoAD.getECPM());

//        this.showRewardVideoAd(curRewardVideoPos, gameObjectName, methodName);
    }

    /**
     * 视频素材缓存成功，可在此回调后进行广告展示
     */
    @Override
    public void onVideoCached() {
        adLoaded = true;
        videoCached = true;
        Log.i(TAG, "onVideoCached");
    }

    /**
     * 激励视频广告页面展示
     */
    @Override
    public void onADShow() {
        Log.i(TAG, "onADShow");
        loadRewardVideoAd(curRewardVideoPos);//自动重新加载一条新的广告
    }

    /**
     * 激励视频广告曝光
     */
    @Override
    public void onADExpose() {
        Log.i(TAG, "onADExpose");
    }

    /**
     * 激励视频触发激励（观看视频大于一定时长或者视频播放完毕）
     */
    @Override
    public void onReward() {
        Log.i(TAG, "onReward");
        //Toast.makeText(m_activity, "gameObjectName = " + gameObjectName + " methodName = " + methodName + " msg = " + curRewardVideoPos, Toast.LENGTH_LONG).show();
        UnityPlayer.UnitySendMessage(gameObjectName,methodName, curRewardVideoPos);
    }

    /**
     * 激励视频广告被点击
     */
    @Override
    public void onADClick() {
        Map<String, String> map = rewardVideoAD.getExts();
        String clickUrl = map.get("clickUrl");
        Log.i(TAG, "onADClick clickUrl: " + clickUrl);
    }

    /**
     * 激励视频播放完毕
     */
    @Override
    public void onVideoComplete() {
        Log.i(TAG, "onVideoComplete");
    }

    /**
     * 激励视频广告被关闭
     */
    @Override
    public void onADClose() {
        Log.i(TAG, "onADClose");
    }

    /**
     * 广告流程出错
     */
    @Override
    public void onError(AdError adError) {
        String msg = String.format(Locale.getDefault(), "onError, error code: %d, error msg: %s",
                adError.getErrorCode(), adError.getErrorMsg());
//       Toast.makeText(m_activity, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    //----------------unity需要用到的接口-----------
    public static void loadRewardVideoAd(String rewardVideoPos)
    {
        if(mInstance == null){
            return;
        }

        if(isRewardVideoComplete(rewardVideoPos)){
            return;
        }

//        Toast.makeText(m_activity, "loadRewardVideoAd loadRewardVideoAd ！ ", Toast.LENGTH_LONG).show();
        // 1. 初始化激励视频广告
        rewardVideoAD = new RewardVideoAD(m_activity, AdConfig.getInstance().getAppId(), getPosID(), mInstance);
        Map<String, String> tags = new HashMap<>();
        tags.put("RewardVideoAD_tag_1", "RewardVideoAD_value_1");
        tags.put("RewardVideoAD_tag_2", "RewardVideoAD_value_2");
        rewardVideoAD.setTag(tags);
        adLoaded = false;
        videoCached = false;
        // 2. 加载激励视频广告
        rewardVideoAD.loadAD();
    }

    public static void showRewardVideoAd(String rewardVideoPos, String gameObjectName, String methodName)
    {
        if(mInstance == null){
            return;
        }

//        Toast.makeText(m_activity, "showRewardVideoAd", Toast.LENGTH_LONG).show();

        // 3. 展示激励视频广告
        if (adLoaded && rewardVideoAD != null)
        {//广告展示检查1：广告成功加载，此处也可以使用videoCached来实现视频预加载完成后再展示激励视频广告的逻辑
            if (!rewardVideoAD.hasShown()) {//广告展示检查2：当前广告数据还没有展示过

                adLoaded = false;
                curRewardVideoPos = rewardVideoPos;
                RewardVideoTengXun.gameObjectName = gameObjectName;
                RewardVideoTengXun.methodName = methodName;
                rewardVideoAD.showAD();

//                long delta = 1000000;//建议给广告过期时间加个buffer，单位ms，这里demo采用1000ms的buffer
//                //广告展示检查3：展示广告前判断广告数据未过期
//                if (SystemClock.elapsedRealtime() < (rewardVideoAD.getExpireTimestamp() - delta)) {
//                    curRewardVideoPos = rewardVideoPos;
//                    rewardVideoAD.showAD();
//                } else {
//                    //Toast.makeText(m_activity, "激励视频广告已过期，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show();
//                }

            } else {
                //Toast.makeText(m_activity, "此条广告已经展示过，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show();
            }
        } else {
            //Toast.makeText(m_activity, "成功加载广告后再进行广告展示！", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isRewardVideoComplete(String rewardVideoPos)
    {
        return  adLoaded;
    }

}
