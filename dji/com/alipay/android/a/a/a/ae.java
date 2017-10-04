package com.alipay.android.a.a.a;

import java.lang.reflect.Proxy;

public final class ae {
    private n a;
    private ag b = new ag(this);

    public ae(n nVar) {
        this.a = nVar;
    }

    public final n a() {
        return this.a;
    }

    public final <T> T a(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new af(this.a, cls, this.b));
    }
}
