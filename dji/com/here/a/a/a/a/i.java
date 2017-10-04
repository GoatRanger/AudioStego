package com.here.a.a.a.a;

public enum i {
    REAL_TIME,
    SIMPLE_ROUTING,
    TIME_TABLE;

    public static i[] a() {
        return (i[]) d.clone();
    }

    public static i a(String str) {
        if ("RT".equalsIgnoreCase(str)) {
            return REAL_TIME;
        }
        if ("SR".equalsIgnoreCase(str)) {
            return SIMPLE_ROUTING;
        }
        if ("TT".equalsIgnoreCase(str)) {
            return TIME_TABLE;
        }
        return null;
    }
}
