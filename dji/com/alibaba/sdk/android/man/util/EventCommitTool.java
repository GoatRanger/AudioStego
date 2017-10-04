package com.alibaba.sdk.android.man.util;

import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import com.ut.mini.UTAnalytics;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import java.util.HashMap;
import java.util.Map;

public class EventCommitTool {
    private static final String TAG = "MAN_EventCommitTool";

    public static void commitEvent(int i, String str, Map<String, String> map) {
        if (str == null || map == null) {
            MANLog.Logw(TAG, "[commitEvent] - eventLabel: " + str + ", property : " + map);
        } else if (Aggregation.getInstance().addToNetPerfAggregation(map)) {
            MANLog.Logi(TAG, "ToAggregation : " + i + ", " + map.toString());
        } else {
            commitEventDirectly(i, str, map);
        }
    }

    static void commitEventDirectly(int i, String str, Map<String, String> map) {
        if (str == null || map == null) {
            MANLog.Logf(TAG, "[commitEvent] - eventLabel: " + str + ", property : " + map);
            return;
        }
        commitEventToUT("UT", i, str, "", "", map);
    }

    public static void commitCustomPerformanceEvent(MANCustomPerformance mANCustomPerformance) {
        if (!Aggregation.getInstance().addCustomPerfToAggregation(mANCustomPerformance)) {
            commitEventToUT("UT", MANConfig.CUSTOM_PERFORMANCE_EVENT_ID, mANCustomPerformance.getEventLabel(), "", String.valueOf(mANCustomPerformance.getDuration()), mANCustomPerformance.getProperties());
        } else if (MANLog.isPrintLog()) {
            MANLog.Logi(TAG, "ToAggregation : 66602, duration=" + mANCustomPerformance.getDuration() + ", label=" + mANCustomPerformance.getEventLabel());
        }
    }

    static synchronized void commitEventToUT(String str, int i, String str2, String str3, String str4, Map<String, String> map) {
        synchronized (EventCommitTool.class) {
            Map hashMap;
            if (map == null) {
                hashMap = new HashMap();
            } else {
                Map<String, String> map2 = map;
            }
            hashMap.put(MANConfig.SDK_VERSION_KEY, MANConfig.SDK_VERSION_VALUE);
            if (MANLog.isPrintLog()) {
                MANLog.Logd(TAG, "commitEventFinally : eventId=" + i + ", arg1=" + str2 + ", arg2=" + str3 + ", arg3=" + str4 + ", " + hashMap.toString());
            }
            UTAnalytics.getInstance().getTracker("aliyun_mbaas").send(new UTOriginalCustomHitBuilder(str, i, str2, str3, str4, hashMap).build());
        }
    }
}
