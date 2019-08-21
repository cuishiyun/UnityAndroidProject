package adSdk.tengxun;

import android.app.Activity;
import android.util.Log;
import android.widget.CompoundButton;

import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.util.AdError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import adSdk.AdConfig;

/*
 * 插屏广告工具类
 * date: 2019-08-16
 * author: 崔
 * */
public class InterstitialTengXun  implements
        UnifiedInterstitialADListener, CompoundButton.OnCheckedChangeListener {

    private static String interstitialPos = "";

    private static InterstitialTengXun mInstance;
    private static final String TAG = InterstitialTengXun.class.getSimpleName();
    private static UnifiedInterstitialAD iad;//插屏广告id
    private static String posId;
    private static boolean m_interstitial_loadSuccess;//插屏广告是否加载成功
    private static  Activity m_activity = null;

    public static InterstitialTengXun getInstance(Activity activity)
    {
        if(mInstance == null){
            mInstance = new InterstitialTengXun(activity);
        }
        return  mInstance;
    }

    private InterstitialTengXun(Activity activity)
    {
        this.m_activity = activity;
//        mInstance = this;
    }

    private static UnifiedInterstitialAD getIAD() {
        String posId = getPosID();
        if (iad != null && posId.equals(posId)) {
            return iad;
        }
        posId = posId;
        if (iad != null) {
            iad.close();
            iad.destroy();
            iad = null;
        }
        if (iad == null) {
            Map<String, String> tags = new HashMap<>();
            tags.put("tag_i1", "value_i1");
            tags.put("tag_i2", "value_i2");
            iad = new UnifiedInterstitialAD(m_activity, AdConfig.getInstance().getAppId(), posId, mInstance, tags);
            // 不需要传递tags使用下面构造函数
            // iad = new UnifiedInterstitialAD(this, Constants.APPID, posId, this);
        }
        return iad;
    }

    private static void showAD() {
        m_activity.runOnUiThread(new Runnable()
        {
            public void run() {
                // TODO Auto-generated method stub
                if (iad != null) {//m_interstitial_loadSuccess == true &&
                    m_interstitial_loadSuccess = false;
                    iad.show();
                } else {
//            Toast.makeText(this, "请加载广告后再进行展示 ！ ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private static void showAsPopup() {
        if (iad != null) {
            iad.showAsPopupWindow();
        } else {
//            Toast.makeText(this, "请加载广告后再进行展示 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    private static void close() {
        if (iad != null) {
            iad.close();
        } else {
//            Toast.makeText(this, "广告尚未加载 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    //获取插屏广告ID
    private static String getPosID() {
        return AdConfig.getInstance().getInterstitialId();
    }

    @Override
    public void onADReceive() {
        m_interstitial_loadSuccess = true;
//        Toast.makeText(m_activity, "广告加载成功 ！ ", Toast.LENGTH_LONG).show();
        // onADReceive之后才可调用getECPM()
//        this.showInterstitialAd();
        Log.d(TAG, "eCPM = " + iad.getECPM());
    }

    @Override
    public void onNoAD(AdError error) {
        String msg = String.format(Locale.getDefault(), "onNoAD, error code: %d, error msg: %s",
                error.getErrorCode(), error.getErrorMsg());
//        Toast.makeText(m_activity, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onADOpened() {
        Log.i(TAG, "onADOpened");
    }

    @Override
    public void onADExposure() {
        Log.i(TAG, "onADExposure");
    }

    @Override
    public void onADClicked() {
        Log.i(TAG, "onADClicked : " + (iad.getExt() != null? iad.getExt().get("clickUrl") : ""));
    }

    @Override
    public void onADLeftApplication() {
        Log.i(TAG, "onADLeftApplication");
    }

    @Override
    public void onADClosed() {
        Log.i(TAG, "onADClosed");
        this.loadInterstitialAd(interstitialPos); //elviscui todo
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked) {
//            ((EditText) findViewById(R.id.posId)).setText(Constants.UNIFIED_INTERSTITIAL_ID_LARGE_SMALL);
//        } else {
//            ((EditText) findViewById(R.id.posId)).setText(Constants.UNIFIED_INTERSTITIAL_ID_ONLY_SMALL);
//        }
    }

    //-----------unity需要用到的方法--------------
    public static void loadInterstitialAd(String interstitialPos)
    {
        if(mInstance == null || m_activity == null){
            return;
        }
        m_activity.runOnUiThread(new Runnable()
        {

            public void run() {
                // TODO Auto-generated method stub
                getIAD().loadAD();
            }
        });
    }

    public static void showInterstitialAd(String interstitialPos)
    {
        if(mInstance == null || m_activity == null){
            return;
        }
        InterstitialTengXun.interstitialPos = interstitialPos;
        showAD();
    }

    public static void showInterstitialPopUp()
    {
        if(mInstance == null || m_activity == null){
            return;
        }
        showAsPopup();
    }

    public static void closeInterstitialAd(String interstitialPos)
    {
        if(mInstance == null || m_activity == null){
            return;
        }
        close();
    }

    public static boolean interstitialLoadSuccess(String interstitialPos)
    {
        return m_interstitial_loadSuccess;
    }

}
