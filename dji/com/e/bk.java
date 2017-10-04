package com.e;

import android.content.Context;
import android.net.NetworkInfo;
import com.alipay.sdk.b.b;
import com.google.api.client.http.UrlEncodedParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import dji.sdksharedlib.b.d;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public class bk {
    public static bk a = null;
    private static String d = null;
    aj b;
    al c;
    private int e;
    private int f;

    private bk(Context context) {
        this.b = null;
        this.c = null;
        this.e = bc.i;
        this.f = bc.i;
        this.b = aj.a();
    }

    public static int a(NetworkInfo networkInfo) {
        return (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) ? networkInfo.getType() : -1;
    }

    public static synchronized bk a(Context context, boolean z) {
        bk bkVar;
        synchronized (bk.class) {
            if (a == null) {
                a = new bk(context);
            }
            if (z) {
                d = cw.a(context);
            }
            bkVar = a;
        }
        return bkVar;
    }

    public String a(byte[] bArr, Context context, String str, boolean z) {
        if (a(br.c(context)) == -1) {
            return null;
        }
        byte[] a;
        Map hashMap = new HashMap();
        ao blVar = new bl();
        hashMap.clear();
        hashMap.put(AsyncHttpClient.HEADER_CONTENT_TYPE, UrlEncodedParser.CONTENT_TYPE);
        hashMap.put(d.ck, "Keep-Alive");
        if (z) {
            hashMap.put(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
            hashMap.put("User-Agent", "AMAP_Location_SDK_Android 2.5.0");
            hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", new Object[]{"2.5.0", "loc", Integer.valueOf(3)}));
            hashMap.put("logversion", "2.1");
        }
        blVar.a(hashMap);
        blVar.a(str);
        blVar.a(bArr);
        blVar.a(da.a(context));
        blVar.a(bc.i);
        blVar.b(bc.i);
        if (z) {
            try {
                a = this.b.a(blVar);
            } catch (Throwable e) {
                bc.a(e, "LocNetManager", "post");
                return null;
            } catch (Throwable e2) {
                bc.a(e2, "LocNetManager", "post");
                return null;
            }
        }
        a = this.b.b(blVar);
        return new String(a, "utf-8");
    }

    public HttpURLConnection a(Context context, String str, HashMap<String, String> hashMap, byte[] bArr) throws Exception {
        HttpURLConnection httpURLConnection = null;
        try {
            if (a(br.c(context)) != -1) {
                boolean z = false;
                ao blVar = new bl();
                blVar.a((Map) hashMap);
                blVar.a(str);
                blVar.a(bArr);
                blVar.a(da.a(context));
                blVar.a(bc.i);
                blVar.b(bc.i);
                if (str.toLowerCase(Locale.US).startsWith(b.a)) {
                    z = true;
                }
                httpURLConnection = this.b.a(blVar, z);
            }
        } catch (Throwable th) {
            bc.a(th, "LocNetManager", "doHttpPost");
        }
        return httpURLConnection;
    }

    public byte[] a(Context context, JSONObject jSONObject, bn bnVar, String str, boolean z) throws Exception {
        if (br.a(jSONObject, "httptimeout")) {
            try {
                this.e = jSONObject.getInt("httptimeout");
            } catch (Throwable th) {
                bc.a(th, "LocNetManager", "req");
            }
        }
        if (a(br.c(context)) == -1) {
            return null;
        }
        Map hashMap = new HashMap();
        ao blVar = new bl();
        hashMap.clear();
        hashMap.put(AsyncHttpClient.HEADER_CONTENT_TYPE, RequestParams.APPLICATION_OCTET_STREAM);
        hashMap.put(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
        hashMap.put("gzipped", "1");
        hashMap.put(d.ck, "Keep-Alive");
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 2.5.0");
        if (z && d != null) {
            hashMap.put("X-INFO", d);
        }
        hashMap.put("KEY", cu.f(context));
        hashMap.put("enginever", "4.2");
        String a = cw.a();
        String a2 = cw.a(context, a, "key=" + cu.f(context));
        hashMap.put("ts", a);
        hashMap.put("scode", a2);
        a = "loc";
        if (!z) {
            a = "locf";
        }
        hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", new Object[]{"2.5.0", a, Integer.valueOf(3)}));
        hashMap.put("logversion", "2.1");
        hashMap.put("encr", "1");
        blVar.a(hashMap);
        blVar.a(str);
        blVar.a(br.a(bnVar.a()));
        blVar.a(da.a(context));
        blVar.a(this.e);
        blVar.b(this.e);
        return this.b.b(blVar);
    }
}
