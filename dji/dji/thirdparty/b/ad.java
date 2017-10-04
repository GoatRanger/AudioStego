package dji.thirdparty.b;

import com.google.api.client.http.HttpStatusCodes;
import dji.thirdparty.b.a.b.j;
import dji.thirdparty.b.a.b.q;
import dji.thirdparty.c.c;
import dji.thirdparty.c.e;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class ad {
    private final ab a;
    private final z b;
    private final int c;
    private final String d;
    private final s e;
    private final t f;
    private final ae g;
    private ad h;
    private ad i;
    private final ad j;
    private volatile d k;

    public static class a {
        private ab a;
        private z b;
        private int c;
        private String d;
        private s e;
        private dji.thirdparty.b.t.a f;
        private ae g;
        private ad h;
        private ad i;
        private ad j;

        public a() {
            this.c = -1;
            this.f = new dji.thirdparty.b.t.a();
        }

        private a(ad adVar) {
            this.c = -1;
            this.a = adVar.a;
            this.b = adVar.b;
            this.c = adVar.c;
            this.d = adVar.d;
            this.e = adVar.e;
            this.f = adVar.f.c();
            this.g = adVar.g;
            this.h = adVar.h;
            this.i = adVar.i;
            this.j = adVar.j;
        }

        public a a(ab abVar) {
            this.a = abVar;
            return this;
        }

        public a a(z zVar) {
            this.b = zVar;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a a(s sVar) {
            this.e = sVar;
            return this;
        }

        public a a(String str, String str2) {
            this.f.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a b(String str) {
            this.f.c(str);
            return this;
        }

        public a a(t tVar) {
            this.f = tVar.c();
            return this;
        }

        public a a(ae aeVar) {
            this.g = aeVar;
            return this;
        }

        public a a(ad adVar) {
            if (adVar != null) {
                a("networkResponse", adVar);
            }
            this.h = adVar;
            return this;
        }

        public a b(ad adVar) {
            if (adVar != null) {
                a("cacheResponse", adVar);
            }
            this.i = adVar;
            return this;
        }

        private void a(String str, ad adVar) {
            if (adVar.g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            } else if (adVar.h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            } else if (adVar.i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            } else if (adVar.j != null) {
                throw new IllegalArgumentException(str + ".priorResponse != null");
            }
        }

        public a c(ad adVar) {
            if (adVar != null) {
                d(adVar);
            }
            this.j = adVar;
            return this;
        }

        private void d(ad adVar) {
            if (adVar.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public ad a() {
            if (this.a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.b == null) {
                throw new IllegalStateException("protocol == null");
            } else if (this.c >= 0) {
                return new ad();
            } else {
                throw new IllegalStateException("code < 0: " + this.c);
            }
        }
    }

    private ad(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
    }

    public ab a() {
        return this.a;
    }

    public z b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.c >= 200 && this.c < 300;
    }

    public String e() {
        return this.d;
    }

    public s f() {
        return this.e;
    }

    public List<String> a(String str) {
        return this.f.c(str);
    }

    public String b(String str) {
        return a(str, null);
    }

    public String a(String str, String str2) {
        String a = this.f.a(str);
        return a != null ? a : str2;
    }

    public t g() {
        return this.f;
    }

    public ae a(long j) throws IOException {
        e c = this.g.c();
        c.b(j);
        c D = c.c().D();
        if (D.b() > j) {
            c = new c();
            c.a_(D, j);
            D.y();
        } else {
            Object obj = D;
        }
        return ae.a(this.g.a(), c.b(), c);
    }

    public ae h() {
        return this.g;
    }

    public a i() {
        return new a();
    }

    public boolean j() {
        switch (this.c) {
            case 300:
            case HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY /*301*/:
            case HttpStatusCodes.STATUS_CODE_FOUND /*302*/:
            case HttpStatusCodes.STATUS_CODE_SEE_OTHER /*303*/:
            case 307:
            case q.b /*308*/:
                return true;
            default:
                return false;
        }
    }

    public ad k() {
        return this.h;
    }

    public ad l() {
        return this.i;
    }

    public ad m() {
        return this.j;
    }

    public List<h> n() {
        String str;
        if (this.c == 401) {
            str = "WWW-Authenticate";
        } else if (this.c != dji.pilot.flyunlimit.a.C) {
            return Collections.emptyList();
        } else {
            str = "Proxy-Authenticate";
        }
        return j.a(g(), str);
    }

    public d o() {
        d dVar = this.k;
        if (dVar != null) {
            return dVar;
        }
        dVar = d.a(this.f);
        this.k = dVar;
        return dVar;
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.a.a() + '}';
    }
}
