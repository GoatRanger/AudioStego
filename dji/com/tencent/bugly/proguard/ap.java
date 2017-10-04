package com.tencent.bugly.proguard;

public class ap {
    public static aq a(int i) {
        if (i == 1) {
            return a();
        }
        if (i == 3) {
            return b();
        }
        return null;
    }

    private static ao a() {
        return new ao();
    }

    private static an b() {
        return new an();
    }
}
