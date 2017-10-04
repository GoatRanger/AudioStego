package dji.thirdparty.e;

import dji.thirdparty.e.c.a;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

final class f extends a {
    static final a a = new f();

    f() {
    }

    public c<?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (a.a(type) != b.class) {
            return null;
        }
        final Type e = o.e(type);
        return new c<b<?>>(this) {
            final /* synthetic */ f b;

            public /* synthetic */ Object a(b bVar) {
                return b(bVar);
            }

            public Type a() {
                return e;
            }

            public <R> b<R> b(b<R> bVar) {
                return bVar;
            }
        };
    }
}
