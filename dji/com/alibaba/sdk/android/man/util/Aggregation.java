package com.alibaba.sdk.android.man.util;

import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

public class Aggregation {
    private static String tag = "MAN_Aggregation";
    private final String AGGREGATION_CUSTOM_PERFORMANCE_LABLE;
    private final String AGGREGATION_NETWORK_PERFORMANCE_LABLE;
    private final Map<String, AggregationSend> hashMap;
    private final ArrayList<String> networkDefineKey;
    private Timer timer;
    private AggregationTimerTask timerTask;
    private long totalNum;

    interface AggregationSend {
        void send();
    }

    private class AggregationCustomPerf implements AggregationSend {
        private int count = 0;
        private long duration = 0;
        private String eventLabel = "";

        public AggregationCustomPerf(String str) {
            this.eventLabel = str;
        }

        public void addCustomPerf(long j) {
            this.duration += j;
            this.count++;
        }

        public void send() {
            if (this.count != 0) {
                EventCommitTool.commitEventToUT("UT", MANConfig.CUSTOM_PERFORMANCE_EVENT_ID, this.eventLabel, String.valueOf(this.count), String.valueOf(this.duration / ((long) this.count)), new HashMap());
            }
        }
    }

    private class AggregationNetworkPerformance implements AggregationSend {
        private long connectTimeCount;
        private long connectTimeSum;
        private long count;
        private long firstByteCount;
        private long firstByteSum;
        private String requestHost;
        private String requestMethod;
        private long requestRTSum;
        private long resourceBytesSum;

        private AggregationNetworkPerformance() {
            this.requestRTSum = 0;
            this.connectTimeSum = 0;
            this.firstByteSum = 0;
            this.resourceBytesSum = 0;
            this.connectTimeCount = 0;
            this.firstByteCount = 0;
            this.count = 0;
            this.requestMethod = null;
            this.requestHost = null;
        }

        public void addNetworkPerformance(long j, long j2, long j3, long j4, String str, String str2) {
            if (j2 != -1) {
                this.connectTimeSum += j2;
                this.connectTimeCount++;
            }
            if (j3 != -1) {
                this.firstByteSum += j3;
                this.firstByteCount++;
            }
            this.resourceBytesSum += j4;
            this.requestRTSum += j;
            this.count++;
            this.requestHost = str2;
            this.requestMethod = str;
        }

        public void send() {
            if (this.count != 0) {
                Map hashMap = new HashMap();
                if (this.connectTimeCount != 0) {
                    hashMap.put(MANConfig.NETWORK_SINGLE_CONNECT_TIME_KEY, String.valueOf(this.connectTimeSum / this.connectTimeCount));
                    hashMap.put(MANConfig.NETWORK_AGGREGATION_CONNECTION_TIME_NUMBER_KEY, String.valueOf(this.connectTimeCount));
                }
                if (this.firstByteCount != 0) {
                    hashMap.put(MANConfig.NETWORK_SINGLE_FIRST_PACKAGE_RT_KEY, String.valueOf(this.firstByteSum / this.firstByteCount));
                    hashMap.put(MANConfig.NETWORK_AGGREGATION_FIST_PACKAGE_NUMBER_KEY, String.valueOf(this.firstByteCount));
                }
                if (this.count != 0) {
                    hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_RT_KEY, String.valueOf(this.requestRTSum / this.count));
                    hashMap.put(MANConfig.NETWORK_AGGREGATION_PERFORMANCE_NUMBER_KEY, String.valueOf(this.count));
                    hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_SIZE_KEY, String.valueOf(this.resourceBytesSum / this.count));
                }
                if (!(this.requestHost == null || this.requestHost.equals(""))) {
                    hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY, this.requestHost);
                }
                if (!(this.requestMethod == null || this.requestMethod.equals(""))) {
                    hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY, this.requestMethod);
                }
                EventCommitTool.commitEventDirectly(3002, MANConfig.NETWORK_SIG_REQUEST_EVENT_LABEL, hashMap);
            }
        }
    }

    private class AggregationTimerTask extends TimerTask {
        private AggregationTimerTask() {
        }

        public void run() {
            MANLog.Logi(Aggregation.tag, "timer alive.");
            Aggregation.getInstance().submitAggregation();
        }
    }

    private static class Singleton {
        static Aggregation instance = new Aggregation();

        private Singleton() {
        }
    }

    private Aggregation() {
        this.AGGREGATION_NETWORK_PERFORMANCE_LABLE = "AGGREGATION_3002";
        this.AGGREGATION_CUSTOM_PERFORMANCE_LABLE = "AGGREGATION_66602";
        this.totalNum = 0;
        this.hashMap = new LinkedHashMap<String, AggregationSend>() {
            private static final long serialVersionUID = 201503121136L;

            protected boolean removeEldestEntry(Entry<String, AggregationSend> entry) {
                return size() > 200;
            }
        };
        this.networkDefineKey = new ArrayList();
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_CONNECT_TIME_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_FIRST_PACKAGE_RT_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_REQUEST_RT_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_REQUEST_SIZE_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY);
        this.timerTask = new AggregationTimerTask();
        this.timer = new Timer();
        this.timer.schedule(this.timerTask, 30000, 30000);
    }

    public static Aggregation getInstance() {
        return Singleton.instance;
    }

    public boolean addCustomPerfToAggregation(MANCustomPerformance mANCustomPerformance) {
        if (mANCustomPerformance.getProperties() != null && mANCustomPerformance.getProperties().size() != 0) {
            return false;
        }
        String str = "AGGREGATION_66602" + mANCustomPerformance.getEventLabel();
        synchronized (this.hashMap) {
            AggregationCustomPerf aggregationCustomPerf = (AggregationCustomPerf) this.hashMap.get(str);
            if (aggregationCustomPerf == null) {
                aggregationCustomPerf = new AggregationCustomPerf(mANCustomPerformance.getEventLabel());
                this.hashMap.put(str, aggregationCustomPerf);
            }
            aggregationCustomPerf.addCustomPerf(mANCustomPerformance.getDuration());
            this.totalNum++;
            if (this.totalNum >= 100) {
                submitAggregation();
            }
        }
        return true;
    }

    public boolean addToNetPerfAggregation(Map<String, String> map) {
        if (!isOnlyContainsDefineKey(map)) {
            return false;
        }
        long convertTimeStr2Long = convertTimeStr2Long((String) map.get(MANConfig.NETWORK_SINGLE_CONNECT_TIME_KEY));
        long convertTimeStr2Long2 = convertTimeStr2Long((String) map.get(MANConfig.NETWORK_SINGLE_FIRST_PACKAGE_RT_KEY));
        long convertTimeStr2Long3 = convertTimeStr2Long((String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_RT_KEY));
        try {
            long longValue = Long.valueOf((String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_SIZE_KEY)).longValue();
        } catch (NumberFormatException e) {
            longValue = 0;
        }
        String str = "AGGREGATION_3002" + ((String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY)) + ((String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY));
        synchronized (this.hashMap) {
            AggregationNetworkPerformance aggregationNetworkPerformance = (AggregationNetworkPerformance) this.hashMap.get(str);
            if (aggregationNetworkPerformance != null) {
                aggregationNetworkPerformance.addNetworkPerformance(convertTimeStr2Long3, convertTimeStr2Long, convertTimeStr2Long2, longValue, (String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY), (String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY));
            } else {
                aggregationNetworkPerformance = new AggregationNetworkPerformance();
                aggregationNetworkPerformance.addNetworkPerformance(convertTimeStr2Long3, convertTimeStr2Long, convertTimeStr2Long2, longValue, (String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY), (String) map.get(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY));
                this.hashMap.put(str, aggregationNetworkPerformance);
            }
            this.totalNum++;
            if (this.totalNum >= 100) {
                submitAggregation();
            }
        }
        return true;
    }

    private long convertTimeStr2Long(String str) {
        try {
            return Long.valueOf(str).longValue();
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isOnlyContainsDefineKey(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        for (String contains : map.keySet()) {
            if (!this.networkDefineKey.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    private void submitAggregation() {
        synchronized (this.hashMap) {
            this.totalNum = 0;
            Iterator it = this.hashMap.keySet().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (this.hashMap.get(str) != null) {
                    ((AggregationSend) this.hashMap.get(str)).send();
                }
                it.remove();
            }
        }
    }
}
