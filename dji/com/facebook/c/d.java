package com.facebook.c;

import android.net.Uri;

public class d {
    private final Uri a;
    private final String b;
    private String c;
    private Uri d;

    d(Uri uri, String str) {
        this.a = uri;
        this.b = str;
    }

    public Uri a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public d a(String str) {
        this.c = str;
        return this;
    }

    public String c() {
        return this.c;
    }

    public d a(Uri uri) {
        this.d = uri;
        return this;
    }

    public Uri d() {
        return this.d;
    }

    public c e() {
        return new c(this);
    }
}
