package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.tencent.bugly.crashreport.inner.InnerAPI;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.z;
import dji.pilot.college.b.b;
import dji.pilot.usercenter.protocol.d;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

public class H5JavaScriptInterface {
    private static HashMap<H5JavaScriptInterface, WebView> a = new HashMap();
    private String b = null;
    private Thread c = null;
    private String d = null;
    private Map<String, String> e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(WebView webView) {
        if (a.values().contains(webView)) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        a.put(h5JavaScriptInterface, webView);
        h5JavaScriptInterface.c = Thread.currentThread();
        h5JavaScriptInterface.d = a(h5JavaScriptInterface.c);
        h5JavaScriptInterface.e = a(webView);
        return h5JavaScriptInterface;
    }

    private static String a(Thread thread) {
        if (thread == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 2; i < thread.getStackTrace().length; i++) {
            StackTraceElement stackTraceElement = thread.getStackTrace()[i];
            if (!stackTraceElement.toString().contains("crashreport")) {
                stringBuilder.append(stackTraceElement.toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static Map<String, String> a(WebView webView) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("[WebView] ContentDescription", "" + webView.getContentDescription());
        return hashMap;
    }

    private a a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar = new a();
            aVar.a = jSONObject.getString("projectRoot");
            if (aVar.a == null) {
                return null;
            }
            aVar.b = jSONObject.getString("context");
            if (aVar.b == null) {
                return null;
            }
            aVar.c = jSONObject.getString("url");
            if (aVar.c == null) {
                return null;
            }
            aVar.d = jSONObject.getString("userAgent");
            if (aVar.d == null) {
                return null;
            }
            aVar.e = jSONObject.getString(b.n);
            if (aVar.e == null) {
                return null;
            }
            aVar.f = jSONObject.getString("name");
            if (aVar.f == null || aVar.f.equals("null")) {
                return null;
            }
            String string = jSONObject.getString("stacktrace");
            if (string == null) {
                return null;
            }
            int indexOf = string.indexOf("\n");
            if (indexOf < 0) {
                z.d("H5 crash stack's format is wrong!", new Object[0]);
                return null;
            }
            aVar.h = string.substring(indexOf + 1);
            aVar.g = string.substring(0, indexOf);
            int indexOf2 = aVar.g.indexOf(":");
            if (indexOf2 > 0) {
                aVar.g = aVar.g.substring(indexOf2 + 1);
            }
            aVar.i = jSONObject.getString(d.A);
            if (aVar.f == null) {
                return null;
            }
            aVar.j = jSONObject.getLong("lineNumber");
            if (aVar.j < 0) {
                return null;
            }
            aVar.k = jSONObject.getLong("columnNumber");
            if (aVar.k < 0) {
                return null;
            }
            z.a("H5 crash information is following: ", new Object[0]);
            z.a("[projectRoot]: " + aVar.a, new Object[0]);
            z.a("[context]: " + aVar.b, new Object[0]);
            z.a("[url]: " + aVar.c, new Object[0]);
            z.a("[userAgent]: " + aVar.d, new Object[0]);
            z.a("[language]: " + aVar.e, new Object[0]);
            z.a("[name]: " + aVar.f, new Object[0]);
            z.a("[message]: " + aVar.g, new Object[0]);
            z.a("[stacktrace]: \n" + aVar.h, new Object[0]);
            z.a("[file]: " + aVar.i, new Object[0]);
            z.a("[lineNumber]: " + aVar.j, new Object[0]);
            z.a("[columnNumber]: " + aVar.k, new Object[0]);
            return aVar;
        } catch (Throwable th) {
            if (z.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static void a(a aVar, Thread thread, Map<String, String> map) {
        if (aVar != null) {
            InnerAPI.postH5CrashAsync(thread, aVar.f, aVar.g, aVar.h, map);
        }
    }

    @JavascriptInterface
    public void printLog(String str) {
        z.d("Log from js: %s", new Object[]{str});
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            z.d("Payload from JS is null.", new Object[0]);
            return;
        }
        String a = ag.a(str.getBytes());
        if (this.b == null || !this.b.equals(a)) {
            this.b = a;
            z.d("Handling JS exception ...", new Object[0]);
            a a2 = a(str);
            if (a2 == null) {
                z.d("Failed to parse payload.", new Object[0]);
                return;
            }
            Map linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(a2.a());
            linkedHashMap.putAll(this.e);
            linkedHashMap.put("Java Stack", this.d);
            a(a2, this.c, linkedHashMap);
            return;
        }
        z.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
    }
}
