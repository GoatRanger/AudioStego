package com.alipay.b.e;

import android.content.Context;
import com.alipay.e.a.a.b.a;
import com.alipay.e.a.a.b.d;
import org.json.JSONObject;

public final class c {
    public static d a(Context context) {
        if (context == null) {
            return null;
        }
        String a = com.alipay.b.f.c.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (a.a(a)) {
            a = com.alipay.b.f.c.a("device_feature_file_name", "device_feature_file_key");
        }
        if (a.a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            d dVar = new d();
            dVar.a(jSONObject.getString("imei"));
            dVar.b(jSONObject.getString("imsi"));
            dVar.c(jSONObject.getString("mac"));
            dVar.d(jSONObject.getString("bluetoothmac"));
            dVar.e(jSONObject.getString("gsi"));
            return dVar;
        } catch (Throwable e) {
            d.a(e);
            return null;
        }
    }
}
