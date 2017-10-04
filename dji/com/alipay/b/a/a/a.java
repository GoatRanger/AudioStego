package com.alipay.b.a.a;

import android.content.Context;
import com.alipay.b.e.b;
import com.alipay.e.a.a.b.b.i;
import com.alipay.e.a.a.d.c;
import com.tencent.android.tpush.common.Constants;
import dji.pilot.usercenter.protocol.d;
import java.util.Map;
import org.json.JSONObject;

public final class a {
    public static synchronized String a() {
        String str = null;
        synchronized (a.class) {
            String b = b();
            if (!com.alipay.e.a.a.b.a.a(b)) {
                String[] split = b.split("`");
                if (split != null && split.length >= 2) {
                    str = split[0];
                }
            }
        }
        return str;
    }

    public static synchronized String a(Context context) {
        String a;
        synchronized (a.class) {
            a = a();
            if (com.alipay.e.a.a.b.a.a(a)) {
                a = b(context);
            }
        }
        return a;
    }

    public static synchronized void a(b bVar) {
        synchronized (a.class) {
            if (!com.alipay.e.a.a.b.a.a(bVar.a())) {
                if (!bVar.a().equals(a())) {
                    String str = bVar.a() + "`" + bVar.d();
                    if (str != null) {
                        try {
                            str = i.a(i.a(), str);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("device", str);
                            com.alipay.e.a.a.d.a.a("deviceid_v2", jSONObject.toString());
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    private static String b() {
        try {
            return i.b(i.a(), new JSONObject(com.alipay.e.a.a.d.a.a("deviceid_v2")).getString("device"));
        } catch (Exception e) {
            return null;
        }
    }

    public static synchronized String b(Context context) {
        String str = null;
        synchronized (a.class) {
            String str2 = "";
            try {
                String b = c.b(context, d.x, "deviceid", "");
                b = com.alipay.e.a.a.b.a.a(b) ? null : i.b(i.a(), b);
                if (!com.alipay.e.a.a.b.a.a(b)) {
                    b bVar = new b();
                    Map a = b.a(b);
                    if (a != null) {
                        str = (String) a.get(Constants.FLAG_DEVICE_ID);
                    }
                    str = str2;
                }
            } catch (Throwable th) {
            }
        }
        return str;
    }
}
