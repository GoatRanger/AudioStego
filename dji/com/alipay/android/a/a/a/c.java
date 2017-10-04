package com.alipay.android.a.a.a;

import dji.pilot.usercenter.protocol.d;

public class c extends RuntimeException {
    private static final long a = -2875437994101380406L;
    private String b;
    private int c;
    private String d;

    public c(Integer num, String str) {
        super(a(num, str));
        this.c = num.intValue();
        this.d = str;
    }

    public c(Integer num, String str, Throwable th) {
        super(a(num, str), th);
        this.c = num.intValue();
        this.d = str;
    }

    public c(Integer num, Throwable th) {
        super(th);
        this.c = num.intValue();
    }

    public c(String str) {
        super(str);
        this.c = 0;
        this.d = str;
    }

    private static String a(Integer num, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RPCException: ");
        if (num != null) {
            stringBuilder.append(d.G).append(num).append(d.H);
        }
        stringBuilder.append(" : ");
        if (str != null) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }
}
