package com.alipay.android.a.a.a;

import dji.pilot.usercenter.protocol.d;

public class a extends Exception {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    private static final long k = -6320569206365033676L;
    private int l;
    private String m;

    public a(Integer num, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Http Transport error");
        if (num != null) {
            stringBuilder.append(d.G).append(num).append(d.H);
        }
        stringBuilder.append(" : ");
        if (str != null) {
            stringBuilder.append(str);
        }
        super(stringBuilder.toString());
        this.l = num.intValue();
        this.m = str;
    }

    public a(String str) {
        super(str);
        this.l = 0;
        this.m = str;
    }

    public int a() {
        return this.l;
    }

    public String b() {
        return this.m;
    }
}
