package com.facebook.login;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.a.b;
import com.facebook.login.LoginClient.Request;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class e {
    static final String a = "fb_mobile_login_method_start";
    static final String b = "fb_mobile_login_method_complete";
    static final String c = "skipped";
    static final String d = "fb_mobile_login_start";
    static final String e = "fb_mobile_login_complete";
    static final String f = "0_auth_logger_id";
    static final String g = "1_timestamp_ms";
    static final String h = "2_result";
    static final String i = "3_method";
    static final String j = "4_error_code";
    static final String k = "5_error_message";
    static final String l = "6_extras";
    static final String m = "try_login_activity";
    static final String n = "no_internet_permission";
    static final String o = "not_tried";
    static final String p = "new_permissions";
    static final String q = "login_behavior";
    static final String r = "request_code";
    static final String s = "permissions";
    static final String t = "default_audience";
    static final String u = "isReauthorize";
    static final String v = "facebookVersion";
    static final String w = "com.facebook.katana";
    private final b x;
    private String y;
    private String z;

    e(Context context, String str) {
        this.y = str;
        this.x = b.c(context, str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                PackageInfo packageInfo = packageManager.getPackageInfo(w, 0);
                if (packageInfo != null) {
                    this.z = packageInfo.versionName;
                }
            }
        } catch (NameNotFoundException e) {
        }
    }

    public String a() {
        return this.y;
    }

    static Bundle a(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong(g, System.currentTimeMillis());
        bundle.putString(f, str);
        bundle.putString(i, "");
        bundle.putString(h, "");
        bundle.putString(k, "");
        bundle.putString(j, "");
        bundle.putString(l, "");
        return bundle;
    }

    public void a(Request request) {
        Bundle a = a(request.e());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(q, request.b().toString());
            jSONObject.put(r, LoginClient.d());
            jSONObject.put("permissions", TextUtils.join(",", request.a()));
            jSONObject.put("default_audience", request.c().toString());
            jSONObject.put(u, request.f());
            if (this.z != null) {
                jSONObject.put(v, this.z);
            }
            a.putString(l, jSONObject.toString());
        } catch (JSONException e) {
        }
        this.x.a(d, null, a);
    }

    public void a(String str, Map<String, String> map, a aVar, Map<String, String> map2, Exception exception) {
        JSONObject jSONObject;
        Bundle a = a(str);
        if (aVar != null) {
            a.putString(h, aVar.a());
        }
        if (!(exception == null || exception.getMessage() == null)) {
            a.putString(k, exception.getMessage());
        }
        if (map.isEmpty()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject(map);
        }
        if (map2 != null) {
            JSONObject jSONObject2;
            if (jSONObject == null) {
                jSONObject2 = new JSONObject();
            } else {
                jSONObject2 = jSONObject;
            }
            try {
                for (Entry entry : map2.entrySet()) {
                    jSONObject2.put((String) entry.getKey(), entry.getValue());
                }
                jSONObject = jSONObject2;
            } catch (JSONException e) {
                jSONObject = jSONObject2;
            }
        }
        if (jSONObject != null) {
            a.putString(l, jSONObject.toString());
        }
        this.x.a(e, null, a);
    }

    public void a(String str, String str2) {
        Bundle a = a(str);
        a.putString(i, str2);
        this.x.a(a, null, a);
    }

    public void a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        Bundle a = a(str);
        if (str3 != null) {
            a.putString(h, str3);
        }
        if (str4 != null) {
            a.putString(k, str4);
        }
        if (str5 != null) {
            a.putString(j, str5);
        }
        if (!(map == null || map.isEmpty())) {
            a.putString(l, new JSONObject(map).toString());
        }
        a.putString(i, str2);
        this.x.a(b, null, a);
    }

    public void b(String str, String str2) {
        a(str, str2, "");
    }

    public void a(String str, String str2, String str3) {
        Bundle a = a("");
        a.putString(h, a.ERROR.a());
        a.putString(k, str2);
        a.putString(i, str3);
        this.x.a(str, null, a);
    }
}
