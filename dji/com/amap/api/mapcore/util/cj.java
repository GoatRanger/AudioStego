package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMapException;
import com.google.api.client.http.UrlEncodedParser;
import com.loopj.android.http.AsyncHttpClient;
import java.util.HashMap;
import java.util.Map;

public abstract class cj<T, V> extends fw {
    protected T a;
    protected int b = 1;
    protected String c = "";
    protected Context d;
    protected final int e = 5000;
    protected final int f = 50000;
    private int j = 1;

    protected abstract V b(String str) throws AMapException;

    public cj(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.d = context;
        this.a = t;
    }

    public V d() throws AMapException {
        if (this.a != null) {
            return h();
        }
        return null;
    }

    private V h() throws AMapException {
        int i = 0;
        V v = null;
        while (i < this.b) {
            try {
                fv a = fv.a(false);
                a(dt.a(this.d));
                v = a(a.d(this));
                i = this.b;
            } catch (Throwable e) {
                ee.a(e, "ProtocalHandler", "getDataMayThrow AMapException");
                e.printStackTrace();
                i++;
                if (i >= this.b) {
                    throw new AMapException(e.getErrorMessage());
                }
            } catch (Throwable e2) {
                ee.a(e2, "ProtocalHandler", "getDataMayThrow AMapCoreException");
                e2.printStackTrace();
                i++;
                if (i < this.b) {
                    try {
                        Thread.sleep((long) (this.j * 1000));
                    } catch (InterruptedException e3) {
                        ee.a(e2, "ProtocalHandler", "getDataMayThrow InterruptedException");
                        e2.printStackTrace();
                        throw new AMapException(e2.getMessage());
                    }
                }
                e();
                throw new AMapException(e2.a());
            }
        }
        return v;
    }

    protected V b(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Throwable e) {
            ee.a(e, "ProtocalHandler", "loadData Exception");
            e.printStackTrace();
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        dj.a(str);
        return b(str);
    }

    public Map<String, String> c() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(AsyncHttpClient.HEADER_CONTENT_TYPE, UrlEncodedParser.CONTENT_TYPE);
        hashMap.put(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
        hashMap.put("User-Agent", r.d);
        hashMap.put("X-INFO", dn.a(this.d, dj.e(), null, false));
        return hashMap;
    }

    private V a(byte[] bArr) throws AMapException {
        return b(bArr);
    }

    protected V e() {
        return null;
    }
}
