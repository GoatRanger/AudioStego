package com.alibaba.sdk.android.man;

import android.app.Application;
import android.content.Context;
import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import com.alibaba.sdk.android.man.util.EventCommitTool;
import com.alibaba.sdk.android.man.util.ToolKit;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTPageHitHelper;
import com.ut.mini.core.sign.UTBaseRequestAuthentication;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;

public class MANAnalytics {
    public final String TAG;

    private static class Singleton {
        static MANAnalytics instance = new MANAnalytics();

        private Singleton() {
        }
    }

    private MANAnalytics() {
        this.TAG = "MAN_MANAnalytics";
    }

    protected static MANAnalytics getInstance() {
        return Singleton.instance;
    }

    public MANTracker getDefaultTracker() {
        return MANTracker.getInstance();
    }

    public void sendCustomPerformance(MANCustomPerformance mANCustomPerformance) {
        if (mANCustomPerformance != null && mANCustomPerformance.getDuration() != -1 && !ToolKit.isNullOrEmpty(mANCustomPerformance.getEventLabel())) {
            EventCommitTool.commitCustomPerformanceEvent(mANCustomPerformance);
        }
    }

    public void init(Application application, Context context, String str, String str2) {
        UTAnalytics.getInstance().setContext(context);
        UTAnalytics.getInstance().setAppApplicationInstance(application);
        UTAnalytics.getInstance().setRequestAuthentication(new UTBaseRequestAuthentication(str, str2));
        initMANInternal(context);
    }

    public void init(Application application, Context context, String str) {
        UTAnalytics.getInstance().setContext(context);
        UTAnalytics.getInstance().setAppApplicationInstance(application);
        UTAnalytics.getInstance().setRequestAuthentication(new UTSecuritySDKRequestAuthentication(str));
        initMANInternal(context);
    }

    public void initMANInternal(Context context) {
        setMetaDataChannel(context);
    }

    public void setAppVersion(String str) {
        UTAnalytics.getInstance().setAppVersion(str);
    }

    public void setChannel(String str) {
        UTAnalytics.getInstance().setChannel(str);
    }

    public void turnOffCrashHandler() {
        UTAnalytics.getInstance().turnOffCrashHandler();
    }

    public void turnOnDebug() {
        UTAnalytics.getInstance().turnOnDebug();
    }

    public void updateUserAccount(String str, String str2) {
        UTAnalytics.getInstance().updateUserAccount(str, str2);
    }

    public void userRegister(String str) {
        UTAnalytics.getInstance().userRegister(str);
    }

    public void turnOffAutoPageTrack() {
        UTPageHitHelper.getInstance().turnOffAutoPageTrack();
    }

    private void setMetaDataChannel(Context context) {
        String metaDataChannel = ToolKit.getMetaDataChannel(context);
        if (!metaDataChannel.equals("")) {
            UTAnalytics.getInstance().setChannel(metaDataChannel);
        }
    }
}
