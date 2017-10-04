package dji.thirdparty.e;

import dji.thirdparty.b.ac;
import dji.thirdparty.b.ae;
import dji.thirdparty.e.b.u;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

final class a extends dji.thirdparty.e.e.a {

    static final class a implements e<ae, ae> {
        static final a a = new a();

        a() {
        }

        public ae a(ae aeVar) throws IOException {
            try {
                ae a = o.a(aeVar);
                return a;
            } finally {
                aeVar.close();
            }
        }
    }

    static final class b implements e<ac, ac> {
        static final b a = new b();

        b() {
        }

        public ac a(ac acVar) throws IOException {
            return acVar;
        }
    }

    static final class c implements e<ae, ae> {
        static final c a = new c();

        c() {
        }

        public ae a(ae aeVar) throws IOException {
            return aeVar;
        }
    }

    static final class d implements e<String, String> {
        static final d a = new d();

        d() {
        }

        public String a(String str) throws IOException {
            return str;
        }
    }

    static final class e implements e<Object, String> {
        static final e a = new e();

        e() {
        }

        public /* synthetic */ Object a(Object obj) throws IOException {
            return b(obj);
        }

        public String b(Object obj) {
            return obj.toString();
        }
    }

    static final class f implements e<ae, Void> {
        static final f a = new f();

        f() {
        }

        public Void a(ae aeVar) throws IOException {
            aeVar.close();
            return null;
        }
    }

    a() {
    }

    public e<ae, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (type == ae.class) {
            if (o.a(annotationArr, u.class)) {
                return c.a;
            }
            return a.a;
        } else if (type == Void.class) {
            return f.a;
        } else {
            return null;
        }
    }

    public e<?, ac> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        if (ac.class.isAssignableFrom(o.a(type))) {
            return b.a;
        }
        return null;
    }

    public e<?, String> b(Type type, Annotation[] annotationArr, m mVar) {
        if (type == String.class) {
            return d.a;
        }
        return null;
    }
}
