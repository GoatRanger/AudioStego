package dji.thirdparty.e;

import dji.thirdparty.b.ac;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.u;
import dji.thirdparty.b.y;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class m {
    private final Map<Method, n> a = new LinkedHashMap();
    private final dji.thirdparty.b.e.a b;
    private final u c;
    private final List<dji.thirdparty.e.e.a> d;
    private final List<dji.thirdparty.e.c.a> e;
    private final Executor f;
    private final boolean g;

    public static final class a {
        private j a;
        private dji.thirdparty.b.e.a b;
        private u c;
        private List<dji.thirdparty.e.e.a> d;
        private List<dji.thirdparty.e.c.a> e;
        private Executor f;
        private boolean g;

        a(j jVar) {
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.a = jVar;
            this.d.add(new a());
        }

        public a() {
            this(j.a());
        }

        public a a(y yVar) {
            return a((dji.thirdparty.b.e.a) o.a((Object) yVar, "client == null"));
        }

        public a a(dji.thirdparty.b.e.a aVar) {
            this.b = (dji.thirdparty.b.e.a) o.a((Object) aVar, "factory == null");
            return this;
        }

        public a a(String str) {
            o.a((Object) str, "baseUrl == null");
            u g = u.g(str);
            if (g != null) {
                return a(g);
            }
            throw new IllegalArgumentException("Illegal URL: " + str);
        }

        public a a(u uVar) {
            o.a((Object) uVar, "baseUrl == null");
            List n = uVar.n();
            if ("".equals(n.get(n.size() - 1))) {
                this.c = uVar;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + uVar);
        }

        public a a(dji.thirdparty.e.e.a aVar) {
            this.d.add(o.a((Object) aVar, "factory == null"));
            return this;
        }

        public a a(dji.thirdparty.e.c.a aVar) {
            this.e.add(o.a((Object) aVar, "factory == null"));
            return this;
        }

        public a a(Executor executor) {
            this.f = (Executor) o.a((Object) executor, "executor == null");
            return this;
        }

        public a a(boolean z) {
            this.g = z;
            return this;
        }

        public m a() {
            if (this.c == null) {
                throw new IllegalStateException("Base URL required.");
            }
            dji.thirdparty.b.e.a aVar = this.b;
            if (aVar == null) {
                aVar = new y();
            }
            Executor executor = this.f;
            if (executor == null) {
                executor = this.a.b();
            }
            List arrayList = new ArrayList(this.e);
            arrayList.add(this.a.a(executor));
            return new m(aVar, this.c, new ArrayList(this.d), arrayList, executor, this.g);
        }
    }

    m(dji.thirdparty.b.e.a aVar, u uVar, List<dji.thirdparty.e.e.a> list, List<dji.thirdparty.e.c.a> list2, Executor executor, boolean z) {
        this.b = aVar;
        this.c = uVar;
        this.d = Collections.unmodifiableList(list);
        this.e = Collections.unmodifiableList(list2);
        this.f = executor;
        this.g = z;
    }

    public <T> T a(final Class<T> cls) {
        o.a((Class) cls);
        if (this.g) {
            b(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler(this) {
            final /* synthetic */ m b;
            private final j c = j.a();

            public Object invoke(Object obj, Method method, Object... objArr) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.c.a(method)) {
                    return this.c.a(method, cls, obj, objArr);
                }
                n a = this.b.a(method);
                return a.e.a(new h(a, objArr));
            }
        });
    }

    private void b(Class<?> cls) {
        j a = j.a();
        for (Method method : cls.getDeclaredMethods()) {
            if (!a.a(method)) {
                a(method);
            }
        }
    }

    n a(Method method) {
        n nVar;
        synchronized (this.a) {
            nVar = (n) this.a.get(method);
            if (nVar == null) {
                nVar = new a(this, method).a();
                this.a.put(method, nVar);
            }
        }
        return nVar;
    }

    public dji.thirdparty.b.e.a a() {
        return this.b;
    }

    public u b() {
        return this.c;
    }

    public List<dji.thirdparty.e.c.a> c() {
        return this.e;
    }

    public c<?> a(Type type, Annotation[] annotationArr) {
        return a(null, type, annotationArr);
    }

    public c<?> a(dji.thirdparty.e.c.a aVar, Type type, Annotation[] annotationArr) {
        int i;
        o.a((Object) type, "returnType == null");
        o.a((Object) annotationArr, "annotations == null");
        int indexOf = this.e.indexOf(aVar) + 1;
        int size = this.e.size();
        for (i = indexOf; i < size; i++) {
            c<?> a = ((dji.thirdparty.e.c.a) this.e.get(i)).a(type, annotationArr, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate call adapter for ").append(type).append(".\n");
        if (aVar != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((dji.thirdparty.e.c.a) this.e.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.e.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((dji.thirdparty.e.c.a) this.e.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public List<dji.thirdparty.e.e.a> d() {
        return this.d;
    }

    public <T> e<T, ac> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return a(null, type, annotationArr, annotationArr2);
    }

    public <T> e<T, ac> a(dji.thirdparty.e.e.a aVar, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        int i;
        o.a((Object) type, "type == null");
        o.a((Object) annotationArr, "parameterAnnotations == null");
        o.a((Object) annotationArr2, "methodAnnotations == null");
        int indexOf = this.d.indexOf(aVar) + 1;
        int size = this.d.size();
        for (i = indexOf; i < size; i++) {
            e<T, ac> a = ((dji.thirdparty.e.e.a) this.d.get(i)).a(type, annotationArr, annotationArr2, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (aVar != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((dji.thirdparty.e.e.a) this.d.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.d.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((dji.thirdparty.e.e.a) this.d.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<ae, T> b(Type type, Annotation[] annotationArr) {
        return a(null, type, annotationArr);
    }

    public <T> e<ae, T> a(dji.thirdparty.e.e.a aVar, Type type, Annotation[] annotationArr) {
        int i;
        o.a((Object) type, "type == null");
        o.a((Object) annotationArr, "annotations == null");
        int indexOf = this.d.indexOf(aVar) + 1;
        int size = this.d.size();
        for (i = indexOf; i < size; i++) {
            e<ae, T> a = ((dji.thirdparty.e.e.a) this.d.get(i)).a(type, annotationArr, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(".\n");
        if (aVar != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((dji.thirdparty.e.e.a) this.d.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.d.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((dji.thirdparty.e.e.a) this.d.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<T, String> c(Type type, Annotation[] annotationArr) {
        o.a((Object) type, "type == null");
        o.a((Object) annotationArr, "annotations == null");
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            e<T, String> b = ((dji.thirdparty.e.e.a) this.d.get(i)).b(type, annotationArr, this);
            if (b != null) {
                return b;
            }
        }
        return e.a;
    }

    public Executor e() {
        return this.f;
    }
}
