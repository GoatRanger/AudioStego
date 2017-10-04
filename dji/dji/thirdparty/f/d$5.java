package dji.thirdparty.f;

import dji.thirdparty.f.d.o;

class d$5 implements o<T, Boolean> {
    final /* synthetic */ Class a;
    final /* synthetic */ d b;

    d$5(d dVar, Class cls) {
        this.b = dVar;
        this.a = cls;
    }

    public /* synthetic */ Object a(Object obj) {
        return b(obj);
    }

    public final Boolean b(T t) {
        return Boolean.valueOf(this.a.isInstance(t));
    }
}
