package com.nokia.maps;

import java.util.concurrent.atomic.AtomicInteger;

public class ce {
    private static int a = 16777214;
    private static final AtomicInteger b = new AtomicInteger(a);

    public static void a(Class<?> cls) {
        try {
            Class.forName(cls.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void a(Runnable runnable) {
        if (MapSettings.l()) {
            ez.a(runnable);
            return;
        }
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int a() {
        int i;
        int i2;
        do {
            i = b.get();
            i2 = i - 1;
            if (i2 == 1) {
                i2 = a;
            }
        } while (!b.compareAndSet(i, i2));
        return i;
    }

    static void a(StringBuilder stringBuilder, String str, String str2, boolean z) {
        if (z) {
            stringBuilder.append("\n");
        }
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
    }
}
