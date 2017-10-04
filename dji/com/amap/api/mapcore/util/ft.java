package com.amap.api.mapcore.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

class ft implements HostnameVerifier {
    final /* synthetic */ fs a;

    ft(fs fsVar) {
        this.a = fsVar;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
    }
}
