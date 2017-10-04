package com.alipay.b.f;

import android.content.Context;
import com.alipay.e.a.a.b.a;
import com.alipay.e.a.a.b.b.d;
import com.alipay.e.a.a.d.c;
import java.util.UUID;

public final class b {
    private static String a = "";

    public static synchronized String a(Context context) {
        String b;
        synchronized (b.class) {
            if (a.a(a)) {
                b = c.b(context, "alipay_vkey_random", "random", "");
                a = b;
                if (a.a(b)) {
                    a = d.b(UUID.randomUUID().toString());
                    c.a(context, "alipay_vkey_random", "random", a);
                }
            }
            b = a;
        }
        return b;
    }
}
