package com.tencent.bugly.proguard;

public class aj {
    private static al a = null;
    private static am b = null;

    public static ak a(int i) {
        if (i == 1) {
            return b();
        }
        if (i == 2) {
            return a();
        }
        return null;
    }

    public static ak a() {
        return new al();
    }

    public static ak b() {
        return new am();
    }
}
