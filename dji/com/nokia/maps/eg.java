package com.nokia.maps;

import com.google.gson.Gson;

public final class eg {
    private static eg a;
    private Gson b = new Gson();

    public static synchronized eg a() {
        eg egVar;
        synchronized (eg.class) {
            if (a == null) {
                a = new eg();
            }
            egVar = a;
        }
        return egVar;
    }

    private eg() {
    }

    public synchronized <T> T a(String str, Class<T> cls) {
        return this.b.fromJson(str, (Class) cls);
    }
}
