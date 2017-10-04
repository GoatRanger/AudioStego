package com.tencent.android.tpush.service.a;

import android.content.Context;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.d.e;

public class b {
    public static void a(Context context, c cVar) {
        if (context != null) {
            c a = a(context);
            String packageName = context.getPackageName();
            if (a.a != cVar.a || a.b != cVar.b) {
                try {
                    e.a(context, packageName + ".com.tencent.tpush.cache.ver", cVar.a);
                    e.a(context, packageName + ".com.tencent.tpush.cache.pri", cVar.b);
                    return;
                } catch (Throwable th) {
                    a.c(Constants.ServiceLogTag, "setSetting", th);
                    return;
                }
            }
            return;
        }
        a.h(Constants.ServiceLogTag, ">> context is null");
    }

    public static c a(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            return new c(e.b(context, packageName + ".com.tencent.tpush.cache.ver", 0.0f), e.b(context, packageName + ".com.tencent.tpush.cache.pri", 0));
        }
        a.h(Constants.LogTag, ">>> get version and priority from Settings error");
        return new c(0.0f, 0);
    }

    public static c a(Context context, String str) {
        c cVar = null;
        if (context != null) {
            try {
                cVar = a(context.createPackageContext(str, 2));
            } catch (Throwable e) {
                a.c(Constants.LogTag, "Create package context exception:" + str, e);
            }
        }
        return cVar;
    }
}
