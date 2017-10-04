package com.nokia.maps;

import com.here.android.mpa.a.a;
import com.here.android.mpa.common.ConnectionInfo;
import com.nokia.maps.annotation.Internal;
import java.util.HashMap;

@Internal
public class ck extends BaseNativeObject {
    private static volatile ck c;
    private static Object d = new Object();
    private static k<a, ck> e = null;
    private HashMap<String, ci> a = new HashMap();
    private ConnectionInfo b = new ConnectionInfo();

    public static ck a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new ck();
                }
            }
        }
        return c;
    }

    private ck() {
    }

    public static void a(k<a, ck> kVar) {
        e = kVar;
    }

    public void a(String str, double d, double d2, boolean z) {
        synchronized (this.a) {
            ci ciVar = (ci) this.a.get(str);
            if (ciVar == null) {
                this.a.put(str, new ci(str, d, d2, z));
            } else {
                ciVar.a(d, d2, z);
            }
        }
    }

    public static boolean b() {
        return !MapsEngine.isInternalBuild();
    }
}
