package com.facebook.internal;

import android.content.Intent;
import com.facebook.o;
import java.util.HashMap;
import java.util.Map;

public final class f implements com.facebook.f {
    private static Map<Integer, a> a = new HashMap();
    private Map<Integer, a> b = new HashMap();

    public interface a {
        boolean a(int i, Intent intent);
    }

    public enum b {
        Login(0),
        Share(1),
        Message(2),
        Like(3),
        GameRequest(4),
        AppGroupCreate(5),
        AppGroupJoin(6),
        AppInvite(7);
        
        private final int i;

        private b(int i) {
            this.i = i;
        }

        public int a() {
            return o.p() + this.i;
        }
    }

    public static synchronized void a(int i, a aVar) {
        synchronized (f.class) {
            ai.a((Object) aVar, com.alipay.sdk.a.a.c);
            if (!a.containsKey(Integer.valueOf(i))) {
                a.put(Integer.valueOf(i), aVar);
            }
        }
    }

    private static synchronized a a(Integer num) {
        a aVar;
        synchronized (f.class) {
            aVar = (a) a.get(num);
        }
        return aVar;
    }

    private static boolean b(int i, int i2, Intent intent) {
        a a = a(Integer.valueOf(i));
        if (a != null) {
            return a.a(i2, intent);
        }
        return false;
    }

    public void b(int i, a aVar) {
        ai.a((Object) aVar, com.alipay.sdk.a.a.c);
        this.b.put(Integer.valueOf(i), aVar);
    }

    public boolean a(int i, int i2, Intent intent) {
        a aVar = (a) this.b.get(Integer.valueOf(i));
        if (aVar != null) {
            return aVar.a(i2, intent);
        }
        return b(i, i2, intent);
    }
}
