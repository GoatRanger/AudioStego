package com.flurry.sdk;

public class kg {
    private static final String a = kg.class.getSimpleName();
    private static boolean b;

    public static synchronized void a() {
        synchronized (kg.class) {
            if (!b) {
                ip.a(hm.class, 10);
                try {
                    ip.a(gg.class, 10);
                } catch (NoClassDefFoundError e) {
                    in.a(3, a, "Analytics module not available");
                }
                try {
                    ip.a(ke.class, 10);
                } catch (NoClassDefFoundError e2) {
                    in.a(3, a, "Crash module not available");
                }
                try {
                    ip.a(i.class, 10);
                } catch (NoClassDefFoundError e3) {
                    in.a(3, a, "Ads module not available");
                }
                b = true;
            }
        }
    }
}
