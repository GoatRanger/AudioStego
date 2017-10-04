package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.Log;
import com.google.api.client.http.UrlEncodedParser;
import com.loopj.android.http.AsyncHttpClient;
import dji.sdksharedlib.b.d;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@Deprecated
public class dm {
    public static int a = -1;
    public static String b = "";
    private static dv c;
    private static String d = "http://apiinit.amap.com/v3/log/init";
    private static String e = null;

    private static boolean a(Context context, dv dvVar, boolean z) {
        boolean z2 = true;
        c = dvVar;
        try {
            String a = a();
            Map hashMap = new HashMap();
            hashMap.put(AsyncHttpClient.HEADER_CONTENT_TYPE, UrlEncodedParser.CONTENT_TYPE);
            hashMap.put(AsyncHttpClient.HEADER_ACCEPT_ENCODING, AsyncHttpClient.ENCODING_GZIP);
            hashMap.put(d.ck, "Keep-Alive");
            hashMap.put("User-Agent", c.c());
            hashMap.put("X-INFO", dn.a(context, c, null, z));
            hashMap.put("logversion", "2.1");
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{c.b(), c.a()}));
            fq a2 = fq.a();
            fw dyVar = new dy();
            dyVar.a(dt.a(context));
            dyVar.a(hashMap);
            dyVar.b(a(context));
            dyVar.a(a);
            z2 = a(a2.b(dyVar));
        } catch (Throwable th) {
            eb.a(th, "Auth", "getAuth");
        }
        return z2;
    }

    @Deprecated
    public static synchronized boolean a(Context context, dv dvVar) {
        boolean a;
        synchronized (dm.class) {
            a = a(context, dvVar, false);
        }
        return a;
    }

    public static void a(String str) {
        dl.a(str);
    }

    private static String a() {
        return d;
    }

    private static boolean a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(dx.a(bArr));
            if (jSONObject.has("status")) {
                int i = jSONObject.getInt("status");
                if (i == 1) {
                    a = 1;
                } else if (i == 0) {
                    a = 0;
                }
            }
            if (jSONObject.has("info")) {
                b = jSONObject.getString("info");
            }
            if (a == 0) {
                Log.i("AuthFailure", b);
            }
            if (a != 1) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            eb.a(e, "Auth", "lData");
            return false;
        } catch (Throwable e2) {
            eb.a(e2, "Auth", "lData");
            return false;
        }
    }

    private static Map<String, String> a(Context context) {
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", "UTF-8");
            String a = dn.a();
            hashMap.put("ts", a);
            hashMap.put(dji.pilot.usercenter.protocol.d.M, dl.f(context));
            hashMap.put("scode", dn.a(context, a, dx.d("resType=json&encode=UTF-8&key=" + dl.f(context))));
        } catch (Throwable th) {
            eb.a(th, "Auth", "gParams");
        }
        return hashMap;
    }
}
