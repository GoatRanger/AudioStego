package com.tencent.android.tpush.stat.event;

import android.content.Context;
import com.alipay.sdk.h.a;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.stat.a.e;
import com.tencent.android.tpush.stat.a.h;
import org.json.JSONObject;

public abstract class d {
    public static String f = "xgsdk";
    protected static String h = null;
    protected String b = null;
    protected long c = 0;
    protected long d;
    protected int e;
    protected String g = null;
    protected long i = 0;
    protected Context j;

    public abstract boolean a(JSONObject jSONObject);

    public abstract EventType b();

    d(Context context, int i, long j) {
        this.j = context;
        this.c = j;
        this.b = "Axg" + j;
        this.d = System.currentTimeMillis() / 1000;
        this.e = i;
        this.g = e.b(context, j);
        if (h == null || h.trim().length() < 40) {
            h = XGPushConfig.getToken(context);
            if (!e.b(h)) {
                h = "0";
            }
        }
    }

    public boolean b(JSONObject jSONObject) {
        try {
            h.a(jSONObject, "ky", this.b);
            jSONObject.put("et", b().a());
            jSONObject.put("ui", h.e(this.j));
            h.a(jSONObject, "mc", h.f(this.j));
            jSONObject.put("ut", 1);
            if (b() != EventType.SESSION_ENV) {
                h.a(jSONObject, a.j, this.g);
                h.a(jSONObject, "ch", f);
            }
            h.a(jSONObject, "mid", h);
            jSONObject.put("si", this.e);
            if (b() == EventType.CUSTOM) {
                jSONObject.put("ts", this.i);
                jSONObject.put("cts", this.d);
            } else {
                jSONObject.put("ts", this.d);
            }
            jSONObject.put(a.h, e.a(this.j, this.c));
            jSONObject.put("dts", e.a(this.j, false));
            return a(jSONObject);
        } catch (Throwable th) {
            return false;
        }
    }

    public String c() {
        try {
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            return jSONObject.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public String toString() {
        return c();
    }
}
