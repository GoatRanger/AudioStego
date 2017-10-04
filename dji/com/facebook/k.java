package com.facebook;

public class k extends RuntimeException {
    static final long b = 1;

    public k(String str) {
        super(str);
    }

    public k(String str, Object... objArr) {
        this(String.format(str, objArr));
    }

    public k(String str, Throwable th) {
        super(str, th);
    }

    public k(Throwable th) {
        super(th);
    }

    public String toString() {
        return getMessage();
    }
}
