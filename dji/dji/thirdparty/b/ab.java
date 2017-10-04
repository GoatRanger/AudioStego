package dji.thirdparty.b;

import com.google.api.client.http.HttpMethods;
import dji.thirdparty.b.a.b.h;
import java.net.URI;
import java.net.URL;
import java.util.List;

public final class ab {
    private final u a;
    private final String b;
    private final t c;
    private final ac d;
    private final Object e;
    private volatile URI f;
    private volatile d g;

    public static class a {
        private u a;
        private String b;
        private dji.thirdparty.b.t.a c;
        private ac d;
        private Object e;

        public a() {
            this.b = HttpMethods.GET;
            this.c = new dji.thirdparty.b.t.a();
        }

        private a(ab abVar) {
            this.a = abVar.a;
            this.b = abVar.b;
            this.d = abVar.d;
            this.e = abVar.e;
            this.c = abVar.c.c();
        }

        public a a(u uVar) {
            if (uVar == null) {
                throw new IllegalArgumentException("url == null");
            }
            this.a = uVar;
            return this;
        }

        public a a(String str) {
            if (str == null) {
                throw new IllegalArgumentException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else {
                if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                    str = "https:" + str.substring(4);
                }
            }
            u g = u.g(str);
            if (g != null) {
                return a(g);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }

        public a a(URL url) {
            if (url == null) {
                throw new IllegalArgumentException("url == null");
            }
            u a = u.a(url);
            if (a != null) {
                return a(a);
            }
            throw new IllegalArgumentException("unexpected url: " + url);
        }

        public a a(String str, String str2) {
            this.c.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.c.a(str, str2);
            return this;
        }

        public a b(String str) {
            this.c.c(str);
            return this;
        }

        public a a(t tVar) {
            this.c = tVar.c();
            return this;
        }

        public a a(d dVar) {
            String dVar2 = dVar.toString();
            if (dVar2.isEmpty()) {
                return b("Cache-Control");
            }
            return a("Cache-Control", dVar2);
        }

        public a a() {
            return a(HttpMethods.GET, null);
        }

        public a b() {
            return a(HttpMethods.HEAD, null);
        }

        public a a(ac acVar) {
            return a(HttpMethods.POST, acVar);
        }

        public a b(ac acVar) {
            return a(HttpMethods.DELETE, acVar);
        }

        public a c() {
            return b(ac.a(null, new byte[0]));
        }

        public a c(ac acVar) {
            return a(HttpMethods.PUT, acVar);
        }

        public a d(ac acVar) {
            return a("PATCH", acVar);
        }

        public a a(String str, ac acVar) {
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("method == null || method.length() == 0");
            } else if (acVar != null && !h.c(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            } else if (acVar == null && h.b(str)) {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            } else {
                this.b = str;
                this.d = acVar;
                return this;
            }
        }

        public a a(Object obj) {
            this.e = obj;
            return this;
        }

        public ab d() {
            if (this.a != null) {
                return new ab();
            }
            throw new IllegalStateException("url == null");
        }
    }

    private ab(a aVar) {
        Object e;
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c.a();
        this.d = aVar.d;
        if (aVar.e != null) {
            e = aVar.e;
        } else {
            ab abVar = this;
        }
        this.e = e;
    }

    public u a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public t c() {
        return this.c;
    }

    public String a(String str) {
        return this.c.a(str);
    }

    public List<String> b(String str) {
        return this.c.c(str);
    }

    public ac d() {
        return this.d;
    }

    public Object e() {
        return this.e;
    }

    public a f() {
        return new a();
    }

    public d g() {
        d dVar = this.g;
        if (dVar != null) {
            return dVar;
        }
        dVar = d.a(this.c);
        this.g = dVar;
        return dVar;
    }

    public boolean h() {
        return this.a.d();
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.a + ", tag=" + (this.e != this ? this.e : null) + '}';
    }
}
