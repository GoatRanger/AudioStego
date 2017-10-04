package dji.thirdparty.f;

import dji.thirdparty.f.d.o;

class d$27 implements o<T, Boolean> {
    final /* synthetic */ Object a;
    final /* synthetic */ d b;

    d$27(d dVar, Object obj) {
        this.b = dVar;
        this.a = obj;
    }

    public /* synthetic */ Object a(Object obj) {
        return b(obj);
    }

    public final Boolean b(T t) {
        boolean equals = this.a == null ? t == null : this.a.equals(t);
        return Boolean.valueOf(equals);
    }
}
