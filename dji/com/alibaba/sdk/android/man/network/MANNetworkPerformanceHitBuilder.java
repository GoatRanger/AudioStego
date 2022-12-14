package com.alibaba.sdk.android.man.network;

import com.alibaba.sdk.android.man.util.MANConfig;
import com.google.api.client.http.HttpMethods;
import java.util.HashMap;
import java.util.Map;

public class MANNetworkPerformanceHitBuilder {
    private static final String TAG = "MAN_MANNetworkPerformanceHitBuilder";
    private NetworkEvent networkEvent = new NetworkEvent();
    private String requestHost;
    private String requestMethod;
    private Map<String, String> requestProperties = new HashMap();

    private MANNetworkPerformanceHitBuilder() {
    }

    public MANNetworkPerformanceHitBuilder(String str, String str2) {
        this.requestHost = str;
        if (str2 == null || !(str2.equalsIgnoreCase(HttpMethods.GET) || str2.equalsIgnoreCase(HttpMethods.POST))) {
            this.requestMethod = HttpMethods.GET;
        } else {
            this.requestMethod = str2.toUpperCase();
        }
    }

    public MANNetworkPerformanceHitBuilder hitRequestStart() {
        this.networkEvent.requestStart();
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitConnectFinished() {
        this.networkEvent.connectionEnd();
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitRecievedFirstByte() {
        this.networkEvent.firstByteEnd();
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitRequestEndWithLoadBytes(long j) {
        this.networkEvent.requestEndNormally(j);
        return this;
    }

    public MANNetworkPerformanceHitBuilder withExtraInfo(String str, String str2) {
        if (!(str == null || str2 == null)) {
            this.requestProperties.put(str, str2);
        }
        return this;
    }

    public MANNetworkPerformanceHitBuilder hitRequestEndWithError(MANNetworkErrorInfo mANNetworkErrorInfo) {
        this.networkEvent.requestEndWithError(mANNetworkErrorInfo.getProperties());
        return this;
    }

    public NetworkEvent build() {
        this.requestProperties.put(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY, this.requestHost);
        this.requestProperties.put(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY, this.requestMethod);
        this.networkEvent.addMANEventProperty(this.requestProperties);
        return this.networkEvent;
    }
}
