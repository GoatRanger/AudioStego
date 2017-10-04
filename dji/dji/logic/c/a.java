package dji.logic.c;

import dji.logic.d.b;

public class a {
    private static a a = null;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    private a() {
        b.getInstance();
        dji.logic.d.a.getInstance();
        dji.logic.b.a.getInstance();
        dji.logic.e.a.getInstance();
        dji.logic.g.a.getInstance();
        dji.logic.h.a.getInstance();
    }
}
