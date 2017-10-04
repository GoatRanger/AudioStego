package com.alipay.b.g;

import android.content.Context;
import com.alipay.b.a.a;
import java.util.Map;

public class e {
    public static synchronized String a(Context context, Map<String, String> map) {
        String a;
        synchronized (e.class) {
            a = new a(context).a((Map) map);
        }
        return a;
    }
}
