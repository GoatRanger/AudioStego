package com.alibaba.sdk.android.ut.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.app.AppContext;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.ut.UTConstants;
import com.alibaba.sdk.android.ut.UTCrashCaughtListener;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.TraceHelper;
import com.ut.device.UTDevice;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import com.ut.mini.core.sign.UTBaseRequestAuthentication;
import com.ut.mini.crashhandler.IUTCrashCaughtListner;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import java.util.HashMap;
import java.util.Map;

public class AlibabaUserTrackerService implements UserTrackerService, IUTCrashCaughtListner {
    public static final UserTrackerService INSTANCE = new AlibabaUserTrackerService();
    private static final String a = AlibabaUserTrackerService.class.getSimpleName();
    private Context b;
    private String c;
    private String d;

    public void sendCustomHit(String str, Activity activity) {
        String obj;
        if (activity != null) {
            obj = activity.getTitle().toString();
        } else {
            obj = str;
        }
        sendCustomHit(str, 60, obj, null);
    }

    public void sendCustomHit(String str, String str2, Map<String, String> map) {
        sendCustomHit(str, 60, str2, map);
    }

    public void sendCustomHit(String str, long j, String str2, Map<String, String> map) {
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder(str);
        uTCustomHitBuilder.setDurationOnEvent(j);
        uTCustomHitBuilder.setEventPage(str2);
        uTCustomHitBuilder.setProperties(map);
        UTAnalytics.getInstance().getTracker("onesdk").send(uTCustomHitBuilder.build());
    }

    public void sendCustomHit(String str, int i, String str2, long j, String str3, Map<String, String> map) {
        UTAnalytics.getInstance().getTracker("onesdk").send(new UTOriginalCustomHitBuilder(str3, i, str, str2, String.valueOf(j), map).build());
    }

    public synchronized void init(AppContext appContext) {
        try {
            this.c = appContext.getAppKey();
            this.b = appContext.getAndroidContext();
            if ("true".equals(AlibabaSDK.getProperty("ut", "enableDebug"))) {
                UTAnalytics.getInstance().turnOnDebug();
            }
            if (Boolean.valueOf(AlibabaSDK.getProperty("ut", "disableInit")).booleanValue()) {
                AliSDKLogger.i(a, "ut.disableInit is set with true, ignore the ut initialization");
            } else {
                UTAnalytics.getInstance().setContext(this.b);
                UTAnalytics.getInstance().setAppApplicationInstance((Application) this.b);
                UTAnalytics.getInstance().setChannel(TraceHelper.channel);
                if (Boolean.valueOf(AlibabaSDK.getProperty("ut", "useBaseRequestAuthentication")).booleanValue()) {
                    AliSDKLogger.i(a, "ut.useBaseRequestAuthentication is set with true, use UTBaseRequestAuthentication for UT initialization");
                    b();
                } else if (a()) {
                    UTAnalytics.getInstance().setRequestAuthentication(new a(this.c, this.b));
                } else {
                    AliSDKLogger.i(a, "Security Guard is not avaiable, use UTBaseRequestAuthentication for UT initialization");
                    b();
                }
                UTAnalytics.getInstance().getTracker("onesdk").setGlobalProperty("sdk_version", ConfigManager.SDK_INTERNAL_VERSION);
                UTAnalytics.getInstance().setCrashCaughtListener(this);
            }
        } catch (Throwable e) {
            AliSDKLogger.printStackTraceAndMore(e);
            throw new RuntimeException(e);
        }
    }

    private static boolean a() {
        try {
            Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void b() {
        String globalProperty = AlibabaSDK.getGlobalProperty(SdkConstants.APP_SECRET);
        UTAnalytics instance = UTAnalytics.getInstance();
        String str = this.c;
        if (globalProperty == null) {
            globalProperty = "appSecret";
        }
        instance.setRequestAuthentication(new UTBaseRequestAuthentication(str, globalProperty));
    }

    public Map<String, String> onCrashCaught(Thread thread, Throwable th) {
        Map<String, String> hashMap = new HashMap();
        if (b.a != null) {
            for (UTCrashCaughtListener onCrashCaught : (UTCrashCaughtListener[]) b.a.getServices(UTCrashCaughtListener.class, null)) {
                Map onCrashCaught2 = onCrashCaught.onCrashCaught(thread, th);
                if (onCrashCaught2 != null) {
                    hashMap.putAll(onCrashCaught2);
                }
            }
        }
        return hashMap;
    }

    public String getDefaultUserTrackerId() {
        return UTDevice.getUtdid(this.b);
    }

    public void updateUserTrackerProperties(Map<String, Object> map) {
        if (map != null) {
            String str;
            if (map.containsKey(UTConstants.USER_NICK)) {
                str = (String) map.get(UTConstants.USER_NICK);
                UTAnalytics.getInstance().userRegister(str);
                if (map.containsKey("user_id")) {
                    String str2 = (String) map.get("user_id");
                    UTAnalytics instance = UTAnalytics.getInstance();
                    if (str2 == null) {
                        str2 = str;
                    }
                    instance.updateUserAccount(str, str2);
                }
            }
            str = (String) map.get(UTConstants.APP_VERSION);
            if (str != null && !str.equals(this.d)) {
                this.d = str;
                UTAnalytics.getInstance().setAppVersion(str);
            }
        }
    }
}
