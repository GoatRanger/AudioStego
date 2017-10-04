package dji.thirdparty.e;

import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.ac;
import dji.thirdparty.b.t;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;

abstract class i<T> {

    static final class a<T> extends i<T> {
        private final e<T, ac> a;

        a(e<T, ac> eVar) {
            this.a = eVar;
        }

        void a(k kVar, T t) {
            if (t == null) {
                throw new IllegalArgumentException("Body parameter value must not be null.");
            }
            try {
                kVar.a((ac) this.a.a(t));
            } catch (Throwable e) {
                throw new RuntimeException("Unable to convert " + t + " to RequestBody", e);
            }
        }
    }

    static final class b<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        b(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.c(this.a, (String) this.b.a(t), this.c);
            }
        }
    }

    static final class c<T> extends i<Map<String, T>> {
        private final e<T, String> a;
        private final boolean b;

        c(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Field map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Field map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Field map contained null value for key '" + str + "'.");
                }
                kVar.c(str, (String) this.a.a(value), this.b);
            }
        }
    }

    static final class d<T> extends i<T> {
        private final String a;
        private final e<T, String> b;

        d(String str, e<T, String> eVar) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
        }

        void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.a(this.a, (String) this.b.a(t));
            }
        }
    }

    static final class e<T> extends i<T> {
        private final t a;
        private final e<T, ac> b;

        e(t tVar, e<T, ac> eVar) {
            this.a = tVar;
            this.b = eVar;
        }

        void a(k kVar, T t) {
            if (t != null) {
                try {
                    kVar.a(this.a, (ac) this.b.a(t));
                } catch (Throwable e) {
                    throw new RuntimeException("Unable to convert " + t + " to RequestBody", e);
                }
            }
        }
    }

    static final class f<T> extends i<Map<String, T>> {
        private final e<T, ac> a;
        private final String b;

        f(e<T, ac> eVar, String str) {
            this.a = eVar;
            this.b = str;
        }

        void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Part map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Part map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Part map contained null value for key '" + str + "'.");
                }
                kVar.a(t.a(AsyncHttpClient.HEADER_CONTENT_DISPOSITION, "form-data; name=\"" + str + "\"", "Content-Transfer-Encoding", this.b), (ac) this.a.a(value));
            }
        }
    }

    static final class g<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        g(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        void a(k kVar, T t) throws IOException {
            if (t == null) {
                throw new IllegalArgumentException("Path parameter \"" + this.a + "\" value must not be null.");
            }
            kVar.a(this.a, (String) this.b.a(t), this.c);
        }
    }

    static final class h<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        h(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.b(this.a, (String) this.b.a(t), this.c);
            }
        }
    }

    static final class i<T> extends i<Map<String, T>> {
        private final e<T, String> a;
        private final boolean b;

        i(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Query map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Query map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Query map contained null value for key '" + str + "'.");
                }
                kVar.b(str, (String) this.a.a(value), this.b);
            }
        }
    }

    static final class j extends i<dji.thirdparty.b.x.b> {
        static final j a = new j();

        private j() {
        }

        void a(k kVar, dji.thirdparty.b.x.b bVar) throws IOException {
            if (bVar != null) {
                kVar.a(bVar);
            }
        }
    }

    static final class k extends i<Object> {
        k() {
        }

        void a(k kVar, Object obj) {
            kVar.a(obj);
        }
    }

    abstract void a(k kVar, T t) throws IOException;

    i() {
    }

    final i<Iterable<T>> a() {
        return new i<Iterable<T>>(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            void a(k kVar, Iterable<T> iterable) throws IOException {
                if (iterable != null) {
                    for (T a : iterable) {
                        this.a.a(kVar, a);
                    }
                }
            }
        };
    }

    final i<Object> b() {
        return new i<Object>(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            void a(k kVar, Object obj) throws IOException {
                if (obj != null) {
                    int length = Array.getLength(obj);
                    for (int i = 0; i < length; i++) {
                        this.a.a(kVar, Array.get(obj, i));
                    }
                }
            }
        };
    }
}
