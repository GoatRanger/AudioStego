package com.alipay.sdk.c;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.h.b;
import com.alipay.sdk.j.j;
import org.json.JSONObject;

public final class a {
    public static final int a = 3500;
    public static final String b = "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&";
    public static final int c = 1000;
    public static final int d = 20000;
    public static final String e = "alipay_cashier_dynamic_config";
    public static final String f = "timeout";
    public static final String g = "st_sdk_config";
    public static final String h = "tbreturl";
    private static a k;
    public String i = b;
    private int j = a;

    private static /* synthetic */ void a(a aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f, aVar.a());
            jSONObject.put(h, aVar.i);
            j.a(b.a().a, e, jSONObject.toString());
        } catch (Exception e) {
        }
    }

    public final int a() {
        if (this.j < 1000 || this.j > 20000) {
            return a;
        }
        new StringBuilder("DynamicConfig::getJumpTimeout >").append(this.j);
        return this.j;
    }

    private String c() {
        return this.i;
    }

    public static a b() {
        if (k == null) {
            a aVar = new a();
            k = aVar;
            aVar.a(j.b(b.a().a, e, null));
        }
        return k;
    }

    private void d() {
        a(j.b(b.a().a, e, null));
    }

    private void e() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f, a());
            jSONObject.put(h, this.i);
            j.a(b.a().a, e, jSONObject.toString());
        } catch (Exception e) {
        }
    }

    final void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject(g);
                this.j = optJSONObject.optInt(f, a);
                this.i = optJSONObject.optString(h, b).trim();
            } catch (Throwable th) {
            }
        }
    }

    public final void a(Context context) {
        new Thread(new b(this, context)).start();
    }
}
