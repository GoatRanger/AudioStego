package com.alipay.b.c;

import android.content.Context;
import com.alipay.b.e.d;
import com.alipay.e.a.a.b.a;
import com.alipay.e.a.a.c.b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class c {
    public static Map<String, String> a(Context context) {
        String str;
        b instance = b.getInstance();
        Map<String, String> hashMap = new HashMap();
        d a = com.alipay.b.e.c.a(context);
        String a2 = instance.a(context);
        String b = instance.b(context);
        String m = instance.m(context);
        String j = instance.j();
        String o = instance.o(context);
        if (a != null) {
            com.alipay.e.a.a.b.b.a("Read deviceInfoStorageModel From local data:");
            if (a.a(a2)) {
                a2 = a.a();
            }
            if (a.a(b)) {
                b = a.b();
            }
            if (a.a(m)) {
                m = a.c();
            }
            if (a.a(j)) {
                j = a.d();
            }
            if (a.a(o)) {
                o = a.e();
            }
            str = o;
            o = j;
            j = m;
            m = b;
            b = a2;
        } else {
            com.alipay.e.a.a.b.b.a("Local deviceInfoStorageModel is null");
            str = o;
            o = j;
            j = m;
            m = b;
            b = a2;
        }
        d dVar = new d(b, m, j, o, str);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", dVar.a());
                jSONObject.put("imsi", dVar.b());
                jSONObject.put("mac", dVar.c());
                jSONObject.put("bluetoothmac", dVar.d());
                jSONObject.put("gsi", dVar.e());
                a2 = jSONObject.toString();
                com.alipay.b.f.c.a("device_feature_file_name", "device_feature_file_key", a2);
                com.alipay.b.f.c.a(context, "device_feature_prefs_name", "device_feature_prefs_key", a2);
            } catch (Throwable e) {
                com.alipay.e.a.a.b.d.a(e);
            }
        }
        hashMap.put("AD1", b);
        hashMap.put("AD2", m);
        hashMap.put("AD3", instance.h(context));
        hashMap.put("AD5", instance.i(context));
        hashMap.put("AD6", instance.k(context));
        hashMap.put("AD7", instance.l(context));
        hashMap.put("AD8", j);
        hashMap.put("AD9", instance.n(context));
        hashMap.put("AD10", str);
        hashMap.put("AD11", instance.a());
        hashMap.put("AD12", instance.c());
        hashMap.put("AD13", instance.d());
        hashMap.put("AD14", instance.f());
        hashMap.put("AD15", instance.g());
        hashMap.put("AD16", instance.h());
        hashMap.put("AD17", "");
        hashMap.put("AD18", o);
        hashMap.put("AD19", instance.p(context));
        hashMap.put("AD20", instance.k());
        hashMap.put("AD21", instance.e(context));
        hashMap.put("AD22", "");
        hashMap.put("AD23", instance.m());
        hashMap.put("AL3", instance.q(context));
        return hashMap;
    }
}
