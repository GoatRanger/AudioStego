package com.alibaba.sdk.android.util;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.dpa.util.HttpdnsMini;
import com.alibaba.sdk.android.man.util.MANConfig;
import com.alipay.sdk.b.b;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDNSUtils {
    public static HttpURLConnection getHttpURLConnection(String str, Context context) throws IOException {
        URL url = new URL(str);
        if ("true".equals(AlibabaSDK.getProperty(SdkConstants.KERNEL_NAME, "disableHttpDNS")) || detectIfProxyExist(context) || b.a.equals(url.getProtocol())) {
            return (HttpURLConnection) url.openConnection();
        }
        String ipByHost = HttpdnsMini.getInstance().getIpByHost(url.getHost());
        if (ipByHost == null) {
            return (HttpURLConnection) url.openConnection();
        }
        URL url2 = new URL(str.replaceFirst(url.getHost(), ipByHost));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setRequestProperty(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY, url2.getHost());
        return httpURLConnection;
    }

    public static boolean detectIfProxyExist(Context context) {
        String property;
        int parseInt;
        if ((VERSION.SDK_INT >= 14 ? 1 : null) != null) {
            String property2 = System.getProperty("http.proxyHost");
            property = System.getProperty("http.proxyPort");
            if (property == null) {
                property = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            }
            String str = property2;
            parseInt = Integer.parseInt(property);
            property = str;
        } else {
            property = Proxy.getHost(context);
            parseInt = Proxy.getPort(context);
        }
        if (property == null || r3 == -1) {
            return false;
        }
        return true;
    }
}
