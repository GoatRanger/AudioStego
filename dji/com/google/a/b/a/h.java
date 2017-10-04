package com.google.a.b.a;

public final class h extends q {
    private final String a;
    private final String b;
    private final String c;
    private final String d;

    h(String str, String str2, String str3, String str4) {
        super(r.EMAIL_ADDRESS);
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String q() {
        StringBuilder stringBuilder = new StringBuilder(30);
        q.a(this.a, stringBuilder);
        q.a(this.b, stringBuilder);
        q.a(this.c, stringBuilder);
        return stringBuilder.toString();
    }
}
