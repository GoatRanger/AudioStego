package com.here.a.a.a;

public abstract class f<T> {
    protected f<T> a;
    protected T b;

    public abstract Object a(String str) throws e;

    protected abstract T d(String str) throws d;

    public f(String str) {
        Object d = d(str);
        if (d == null) {
            throw new NullPointerException("jsonObject can't be null");
        }
        this.b = d;
        this.a = null;
    }

    protected f(T t, f<T> fVar) {
        if (t == null) {
            throw new NullPointerException("jsonObject can't be null");
        }
        this.b = t;
        this.a = fVar;
    }

    public f<T> a() {
        return this.a;
    }
}
