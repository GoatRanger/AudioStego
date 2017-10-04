package com.here.b.d;

import android.content.SharedPreferences;

public class b<T> {
    private final SharedPreferences a;
    private final String b;
    private final T c;

    public b(SharedPreferences sharedPreferences, String str, T t) {
        this.a = sharedPreferences;
        this.b = str;
        this.c = t;
    }

    public boolean a() {
        return this.a.getBoolean(this.b, ((Boolean) this.c).booleanValue());
    }

    public String b() {
        return this.a.getString(this.b, (String) this.c);
    }

    public void a(T t) {
        if (t instanceof String) {
            this.a.edit().putString(this.b, (String) t).apply();
        } else if (t instanceof Boolean) {
            this.a.edit().putBoolean(this.b, ((Boolean) t).booleanValue()).apply();
        }
    }
}
