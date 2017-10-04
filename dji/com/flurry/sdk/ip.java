package com.flurry.sdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ip {
    private static final String a = ip.class.getSimpleName();
    private static final Map<Class<? extends iq>, io> b = new LinkedHashMap();
    private final Map<Class<? extends iq>, iq> c = new LinkedHashMap();

    public static void a(Class<? extends iq> cls, int i) {
        if (cls != null) {
            synchronized (b) {
                b.put(cls, new io(cls, i));
            }
        }
    }

    public synchronized void a(Context context) {
        if (context == null) {
            in.a(5, a, "Null context.");
        } else {
            synchronized (b) {
                List<io> arrayList = new ArrayList(b.values());
            }
            for (io ioVar : arrayList) {
                try {
                    if (ioVar.b()) {
                        iq iqVar = (iq) ioVar.a().newInstance();
                        iqVar.a(context);
                        this.c.put(ioVar.a(), iqVar);
                    }
                } catch (Throwable e) {
                    in.a(5, a, "Flurry Module for class " + ioVar.a() + " is not available:", e);
                }
            }
            jn.a().a(context);
            id.a();
        }
    }

    public synchronized void a() {
        id.b();
        jn.b();
        List b = b();
        for (int size = b.size() - 1; size >= 0; size--) {
            try {
                ((iq) this.c.remove(((iq) b.get(size)).getClass())).b();
            } catch (Throwable e) {
                in.a(5, a, "Error destroying module:", e);
            }
        }
    }

    public iq a(Class<? extends iq> cls) {
        if (cls == null) {
            return null;
        }
        synchronized (this.c) {
            iq iqVar = (iq) this.c.get(cls);
        }
        if (iqVar != null) {
            return iqVar;
        }
        throw new IllegalStateException("Module was not registered/initialized. " + cls);
    }

    private List<iq> b() {
        List<iq> arrayList = new ArrayList();
        synchronized (this.c) {
            arrayList.addAll(this.c.values());
        }
        return arrayList;
    }
}
