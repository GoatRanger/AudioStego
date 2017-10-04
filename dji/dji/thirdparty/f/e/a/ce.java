package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.e.d.q;

public final class ce {
    static final Object a = new Object();

    private ce() {
        throw new IllegalStateException("No instances!");
    }

    static <T> d<Object> a(d<T> dVar) {
        return d.b(dVar.r(new o<T, Object>() {
            public Object a(T t) {
                return t;
            }
        }), d.b(a));
    }

    public static <T> d<Boolean> a(d<? extends T> dVar, d<? extends T> dVar2, final p<? super T, ? super T, Boolean> pVar) {
        return d.c(a(dVar), a(dVar2), new p<Object, Object, Boolean>() {
            public /* synthetic */ Object b(Object obj, Object obj2) {
                return a(obj, obj2);
            }

            public Boolean a(Object obj, Object obj2) {
                boolean z = obj == ce.a;
                boolean z2;
                if (obj2 == ce.a) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z && r3) {
                    return Boolean.valueOf(true);
                }
                if (z || r3) {
                    return Boolean.valueOf(false);
                }
                return (Boolean) pVar.b(obj, obj2);
            }
        }).b(q.c());
    }
}
