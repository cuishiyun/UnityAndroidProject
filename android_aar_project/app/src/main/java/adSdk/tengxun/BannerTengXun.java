package adSdk.tengxun;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.comm.util.AdError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import adSdk.AdConfig;
import adSdk.UnityAdUtils;

/*
 * banner广告
 * date: 2019-08-16
 * author: 崔
 * */
public class BannerTengXun  implements UnifiedBannerADListener, CompoundButton.OnCheckedChangeListener {

    private static BannerTengXun mInstance;
    //------------banner相关-----------------------------
//    ViewGroup bannerContainer;//banner容器
    private static Activity m_activity;
    static UnifiedBannerView bv;//banner广告
    static String banner_posId;//banner广告id
    private static FrameLayout mBannerParentLayout = null;//banner父类

    public static BannerTengXun getInstance(Activity mActivity, FrameLayout parent){
        if(mInstance == null){
            mInstance = new BannerTengXun(mActivity, parent);
        }
        return  mInstance;
    }

    private BannerTengXun(Activity mActivity, FrameLayout parent){
        this.m_activity = mActivity;
        this.mBannerParentLayout = parent;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (bv != null) {
            bv.setLayoutParams(getUnifiedBannerLayoutParams());
        }
    }

    /**
     * banner2.0规定banner宽高比应该为6.4:1 , 开发者可自行设置符合规定宽高比的具体宽度和高度值
     *
     * @return
     */
    private static FrameLayout.LayoutParams getUnifiedBannerLayoutParams() {
//        Point screenSize = new Point();
//        getWindowManager().getDefaultDisplay().getSize(screenSize);
//        return new FrameLayout.LayoutParams(screenSize.x,  Math.round(screenSize.x / 6.4F));

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        return lp;
    }

    private static UnifiedBannerView getBanner() {
        if(bv != null){
            //bannerContainer.removeView(bv);
            mBannerParentLayout.removeView(bv);
            bv.destroy();
        }
        String posId = getBannerPosID();
        banner_posId = posId;
        Map<String, String> tags = new HashMap<>();
        tags.put("tag_b1", "value_b1");
        tags.put("tag_b2", "value_b2");
        bv = new UnifiedBannerView(m_activity, AdConfig.getInstance().getAppId(), posId, mInstance, tags);
        int refreshInterval = 30;
        bv.setRefresh(refreshInterval);
        // 不需要传递tags使用下面构造函数
        // this.bv = new UnifiedBannerView(this, Constants.APPID, posId, this);
//        bannerContainer.addView(bv, getUnifiedBannerLayoutParams());
        mBannerParentLayout.addView(bv, getUnifiedBannerLayoutParams());
        if(UnityAdUtils.getIsShowToast()){
            Toast.makeText(m_activity, "开始加载腾讯banner广告", Toast.LENGTH_LONG).show();
        }
        return bv;
    }

    private void doRefreshBanner() {
//        DemoUtil.hideSoftInput(this);
        getBanner().loadAD();
    }

    private void doCloseBanner() {
//        mBannerParentLayout.removeAllViews();
        //bannerContainer.removeAllViews();
        mBannerParentLayout.removeView(bv);
        if (bv != null) {
            bv.destroy();
            bv = null;
        }
    }

    private static String getBannerPosID() {
        return AdConfig.getInstance().getBannerId();
    }

    @Override
    public void onNoAD(AdError adError) {
        String msg = String.format(Locale.getDefault(), "腾讯banner加载失败, error code: %d, error msg: %s",
                adError.getErrorCode(), adError.getErrorMsg());
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        if(UnityAdUtils.getIsShowToast()){
            Toast.makeText(m_activity, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onADReceive() {
//        Log.i(TAG, "onADReceive");
//        this.showBannerAd();
//        Toast.makeText(this, "banner广告加载成功", Toast.LENGTH_LONG).show();
        if(UnityAdUtils.getIsShowToast()){
            Toast.makeText(m_activity, "腾讯banner加载成功", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onADExposure() {
//        Log.i(TAG, "onADExposure");
    }

    @Override
    public void onADClosed() {
//        Log.i(TAG, "onADClosed");

    }

    @Override
    public void onADClicked() {
//        Log.i(TAG, "onADClicked : " + (bv.getExt() != null? bv.getExt().get("clickUrl") : ""));
    }

    @Override
    public void onADLeftApplication() {
//        Log.i(TAG, "onADLeftApplication");
    }

    @Override
    public void onADOpenOverlay() {
//        Log.i(TAG, "onADOpenOverlay");
    }

    @Override
    public void onADCloseOverlay() {
//        Log.i(TAG, "onADCloseOverlay");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (this.bv != null) {
            if (isChecked) {
                try {
                    int refreshInterval = 30;
                    this.bv.setRefresh(refreshInterval);
//                    Toast.makeText(this, "轮播时间间隔设置为:" + refreshInterval, Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e) {
//                    Toast.makeText(this, "请输入合法的轮播时间间隔!", Toast.LENGTH_LONG).show();
                }
            } else {
                this.bv.setRefresh(30);
//                Toast.makeText(this, "轮播时间间隔恢复默认", Toast.LENGTH_LONG).show();
            }
        } else {
//            Toast.makeText(this, "轮播时间间隔设置失败!", Toast.LENGTH_LONG).show();
        }
    }

    //-----------unity需要用到的方法--------------
    public static void removeBanner(String bannerPos){
        if(m_activity == null){
            return;
        }
        m_activity.runOnUiThread(new Runnable()
        {
            public void run() {
                // TODO Auto-generated method stub
                if (bv != null) {
                    bv.setVisibility(View.INVISIBLE);
                    mBannerParentLayout.removeView(bv);
                    bv.destroy();
                    bv = null;
                }
            }
        });
    }

    public static void loadBannerAd(String bannerPos)
    {
        if(m_activity == null){
            return;
        }
        m_activity.runOnUiThread(new Runnable()
        {
            public void run() {
                // TODO Auto-generated method stub
                getBanner().loadAD();
            }
        });
    }

    public static  void showBannerAd(String bannerPos)
    {

    }

}
