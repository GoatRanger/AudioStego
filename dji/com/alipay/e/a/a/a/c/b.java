package com.alipay.e.a.a.a.c;

import android.content.Context;
import com.alipay.e.a.a.a.a;
import com.alipay.e.a.a.a.b.c;
import com.alipay.e.a.a.a.b.d;

public final class b implements a {
    private static a a = null;
    private static a b = null;

    public static a a(Context context) {
        a aVar = null;
        if (context == null) {
            return null;
        }
        if (a == null) {
            if (context != null) {
                aVar = com.alipay.e.a.a.a.b.a(context);
            }
            b = aVar;
            a = new b();
        }
        return a;
    }

    public final com.alipay.e.a.a.a.b.a a(String str, String str2, String str3, String str4) {
        com.alipay.f.a.a.b.b.b a = b.a(str, str2, str3, str4);
        if (a == null) {
            return null;
        }
        com.alipay.e.a.a.a.b.a aVar = new com.alipay.e.a.a.a.b.a(a.c, a.b);
        aVar.c = a.d;
        aVar.d = a.e;
        return aVar;
    }

    public final c a(d dVar) {
        com.alipay.f.a.a.b.a.b bVar = new com.alipay.f.a.a.b.a.b();
        bVar.a = dVar.a();
        bVar.b = dVar.b();
        bVar.c = dVar.c();
        bVar.d = dVar.d();
        bVar.e = dVar.e();
        bVar.f = dVar.f();
        bVar.g = dVar.g();
        bVar.h = dVar.h();
        bVar.i = dVar.i();
        com.alipay.f.a.a.b.b.d a = b.a(bVar);
        c cVar = new c();
        if (a == null) {
            return null;
        }
        cVar.c = a.d;
        cVar.d = a.e;
        cVar.a = a.a;
        cVar.b = a.b;
        cVar.e = a.c;
        cVar.f = a.f;
        cVar.g = a.g;
        cVar.h = a.h;
        cVar.i = a.i;
        return cVar;
    }

    public final boolean a(String str) {
        return b.a(str);
    }
}
