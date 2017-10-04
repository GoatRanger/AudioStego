package com.facebook.login;

import com.facebook.internal.ab;

public enum a {
    NONE(null),
    ONLY_ME(ab.aB),
    FRIENDS(ab.aC),
    EVERYONE(ab.aD);
    
    private final String e;

    private a(String str) {
        this.e = str;
    }

    public String a() {
        return this.e;
    }
}
