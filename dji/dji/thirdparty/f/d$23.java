package dji.thirdparty.f;

import dji.thirdparty.f.d.p;

class d$23 implements p<T, T, Boolean> {
    d$23() {
    }

    public /* synthetic */ Object b(Object obj, Object obj2) {
        return a(obj, obj2);
    }

    public final Boolean a(T t, T t2) {
        if (t != null) {
            return Boolean.valueOf(t.equals(t2));
        }
        return Boolean.valueOf(t2 == null);
    }
}
