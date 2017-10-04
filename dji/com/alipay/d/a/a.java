package com.alipay.d.a;

import android.content.Context;
import com.alipay.b.e.f;
import com.alipay.sdk.b.b;
import java.util.HashMap;
import java.util.Map;

public class a {
    public static synchronized String a(Context context, Map<String, String> map) {
        String a;
        synchronized (a.class) {
            Map hashMap = new HashMap();
            hashMap.put(b.g, com.alipay.e.a.a.b.a.a(map, b.g, ""));
            hashMap.put(b.c, com.alipay.e.a.a.b.a.a(map, b.c, ""));
            hashMap.put("userId", com.alipay.e.a.a.b.a.a(map, "userId", ""));
            com.alipay.b.g.a.getInstance(context).a(0, hashMap, null);
            a = f.a();
            if (com.alipay.e.a.a.b.a.a(a)) {
                com.alipay.b.e.b a2 = com.alipay.b.e.a.a(context);
                if (a2 == null || com.alipay.e.a.a.b.a.a(a2.a())) {
                    a = com.alipay.b.a.a.a.a(context);
                    if (com.alipay.e.a.a.b.a.a(a)) {
                        a = com.alipay.b.f.b.a(context);
                    }
                } else {
                    a = a2.a();
                }
            }
        }
        return a;
    }
}
