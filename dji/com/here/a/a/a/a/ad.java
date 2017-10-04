package com.here.a.a.a.a;

import java.util.NoSuchElementException;

public final class ad<T> {
    protected static final ad<?> a = new ad();
    private T b;

    public ad(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        this.b = t;
    }

    private ad() {
        this.b = null;
    }

    public static <T> ad<T> a() {
        return a;
    }

    public static <T> ad<T> a(T t) {
        return new ad(t);
    }

    public static <T> ad<T> b(T t) {
        return t == null ? a() : a(t);
    }

    public T b() throws NoSuchElementException {
        if (this.b != null) {
            return this.b;
        }
        throw new NoSuchElementException("No value present");
    }

    public T c(T t) {
        return this.b != null ? this.b : t;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ad adVar = (ad) obj;
        if (this.b != null) {
            if (this.b.equals(adVar.b)) {
                return true;
            }
        } else if (adVar.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.b != null ? this.b.hashCode() : 0;
    }

    public String toString() {
        return String.valueOf(this.b);
    }
}
