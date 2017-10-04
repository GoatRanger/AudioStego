package com.alibaba.sdk.android.man;

import android.util.Log;
import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import com.alibaba.sdk.android.man.network.NetworkEvent;
import com.alibaba.sdk.android.man.util.EventCommitTool;
import com.ut.mini.UTAnalytics;
import java.util.Map;

public class MANTracker {

    private static class Singleton {
        static MANTracker instance = new MANTracker();

        private Singleton() {
        }
    }

    private MANTracker() {
    }

    protected static MANTracker getInstance() {
        return Singleton.instance;
    }

    public void send(Map<String, String> map) {
        if (UTAnalytics.getInstance().getDefaultTracker() == null) {
            Log.e("MAN", "请先初始化MAN");
        } else {
            UTAnalytics.getInstance().getDefaultTracker().send(map);
        }
    }

    public void send(MANCustomPerformance mANCustomPerformance) {
        if (UTAnalytics.getInstance().getDefaultTracker() == null) {
            Log.e("MAN", "请先初始化MAN");
        } else {
            EventCommitTool.commitCustomPerformanceEvent(mANCustomPerformance);
        }
    }

    public void send(NetworkEvent networkEvent) {
        if (UTAnalytics.getInstance().getDefaultTracker() == null) {
            Log.e("MAN", "请先初始化MAN");
        } else {
            networkEvent.reportNetworkInfo();
        }
    }
}
