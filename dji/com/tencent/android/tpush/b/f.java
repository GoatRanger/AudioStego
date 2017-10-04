package com.tencent.android.tpush.b;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.service.l;
import java.util.ArrayList;

public class f {
    static ArrayList a;
    private static volatile f b = null;
    private Context c = null;

    public static f a(Context context) {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    b = new f();
                    b.c = context.getApplicationContext();
                    l.c(b.c);
                }
            }
        }
        return b;
    }

    public void a(Intent intent) {
        g.a().a(new g(this, this.c, intent, null));
    }

    protected static synchronized boolean a(Long l) {
        boolean z = false;
        synchronized (f.class) {
            try {
                if (a == null) {
                    a = new ArrayList();
                }
                if (!a.contains(l)) {
                    a.add(l);
                    if (a.size() > 200) {
                        a.remove(0);
                    }
                    z = true;
                }
            } catch (Throwable th) {
                a.c("PushMessageHandler", "addCachedmsgID", th);
            }
        }
        return z;
    }
}
