package com.alibaba.sdk.android.dpa.util;

import com.alipay.sdk.b.c;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.protocol.d;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpdnsMini {
    private static final int EMPTY_RESULT_HOST_TTL = 30;
    private static final int MAX_HOLD_HOST_NUM = 100;
    private static final int MAX_THREAD_NUM = 5;
    private static final int RESOLVE_TIMEOUT_IN_SEC = 10;
    private static final String SERVER_HOST = "httpdns.aliyuncs.com";
    private static final String SERVER_IP = "140.205.143.143";
    private static final String TAG = "HttpDnsMini";
    private static final int THRESHOLD_DEGRADE_HOST = 5;
    private static AtomicInteger globalNetworkError = new AtomicInteger(0);
    private static HttpdnsMini instance = new HttpdnsMini();
    private ConcurrentMap<String, HostObject> hostManager = new ConcurrentHashMap();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    class HostObject {
        private String hostName;
        private String ip;
        private long queryTime;
        private long ttl;

        HostObject() {
        }

        public String toString() {
            return "HostObject [hostName=" + this.hostName + ", ip=" + this.ip + ", ttl=" + this.ttl + ", queryTime=" + this.queryTime + d.H;
        }

        public boolean isExpired() {
            return getQueryTime() + this.ttl < System.currentTimeMillis() / 1000;
        }

        public boolean isStillAvailable() {
            return (getQueryTime() + this.ttl) + 600 > System.currentTimeMillis() / 1000;
        }

        public String getIp() {
            return this.ip;
        }

        public void setIp(String str) {
            this.ip = str;
        }

        public void setHostName(String str) {
            this.hostName = str;
        }

        public String getHostName() {
            return this.hostName;
        }

        public long getTtl() {
            return this.ttl;
        }

        public void setTtl(long j) {
            this.ttl = j;
        }

        public long getQueryTime() {
            return this.queryTime;
        }

        public void setQueryTime(long j) {
            this.queryTime = j;
        }
    }

    class QueryHostTask implements Callable<String> {
        private boolean hasRetryed = false;
        private String hostName;

        public QueryHostTask(String str) {
            this.hostName = str;
        }

        public String call() {
            String str = HttpdnsMini.SERVER_IP;
            if (HttpdnsMini.globalNetworkError.get() > 5) {
                str = HttpdnsMini.SERVER_HOST;
            }
            str = "http://" + str + "/d?host=" + this.hostName;
            DpaLog.logD(HttpdnsMini.TAG, "[run] - buildUrl: " + str);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                if (httpURLConnection.getResponseCode() != 200) {
                    DpaLog.logD(HttpdnsMini.TAG, "[run] - responseCodeNot 200, but: " + httpURLConnection.getResponseCode());
                } else {
                    HttpdnsMini.globalNetworkError.decrementAndGet();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    JSONObject jSONObject = new JSONObject(stringBuilder.toString());
                    String string = jSONObject.getString(c.f);
                    long j = jSONObject.getLong(MessageKey.MSG_TTL);
                    JSONArray jSONArray = jSONObject.getJSONArray("ips");
                    if (string != null) {
                        if (j == 0) {
                            j = 30;
                        }
                        HostObject hostObject = new HostObject();
                        if (jSONArray == null) {
                            str = null;
                        } else {
                            str = jSONArray.getString(0);
                        }
                        DpaLog.logD(HttpdnsMini.TAG, "[run] - resolve host:" + string + " ip:" + str + " ttl:" + j);
                        hostObject.setHostName(string);
                        hostObject.setTtl(j);
                        hostObject.setIp(str);
                        hostObject.setQueryTime(System.currentTimeMillis() / 1000);
                        if (HttpdnsMini.this.hostManager.size() >= 100) {
                            return str;
                        }
                        HttpdnsMini.this.hostManager.put(this.hostName, hostObject);
                        return str;
                    }
                }
            } catch (Exception e) {
                HttpdnsMini.globalNetworkError.incrementAndGet();
                if (DpaLog.isEnableLog()) {
                    e.printStackTrace();
                }
            }
            if (this.hasRetryed) {
                return null;
            }
            this.hasRetryed = true;
            return call();
        }
    }

    private HttpdnsMini() {
    }

    public static HttpdnsMini getInstance() {
        return instance;
    }

    public String getIpByHost(String str) {
        HostObject hostObject = (HostObject) this.hostManager.get(str);
        if (hostObject != null && !hostObject.isExpired()) {
            return hostObject.getIp();
        }
        DpaLog.logD(TAG, "[getIpByHost] - refresh host: " + str);
        try {
            return (String) this.pool.submit(new QueryHostTask(str)).get();
        } catch (Exception e) {
            if (DpaLog.isEnableLog()) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public String getIpByHostAsync(String str) {
        HostObject hostObject = (HostObject) this.hostManager.get(str);
        if (hostObject == null || hostObject.isExpired()) {
            DpaLog.logD(TAG, "[getIpByHostAsync] - refresh host: " + str);
            this.pool.submit(new QueryHostTask(str));
        }
        if (hostObject == null) {
            return null;
        }
        if (hostObject.isStillAvailable()) {
            return hostObject.getIp();
        }
        return null;
    }
}
