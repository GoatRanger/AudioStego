package dji.thirdparty.e;

import android.support.v4.media.TransportMediator;
import com.alipay.sdk.j.i;
import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ac;
import dji.thirdparty.b.t;
import dji.thirdparty.b.u;
import dji.thirdparty.b.w;
import dji.thirdparty.b.x;
import dji.thirdparty.b.x.b;
import dji.thirdparty.c.c;
import dji.thirdparty.c.d;
import java.io.IOException;

final class k {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String b = " \"<>^`{}|\\?#";
    private final String c;
    private final u d;
    private String e;
    private dji.thirdparty.b.u.a f;
    private final dji.thirdparty.b.ab.a g = new dji.thirdparty.b.ab.a();
    private w h;
    private final boolean i;
    private dji.thirdparty.b.x.a j;
    private dji.thirdparty.b.r.a k;
    private ac l;

    private static class a extends ac {
        private final ac a;
        private final w b;

        a(ac acVar, w wVar) {
            this.a = acVar;
            this.b = wVar;
        }

        public w b() {
            return this.b;
        }

        public long c() throws IOException {
            return this.a.c();
        }

        public void a(d dVar) throws IOException {
            this.a.a(dVar);
        }
    }

    k(String str, u uVar, String str2, t tVar, w wVar, boolean z, boolean z2, boolean z3) {
        this.c = str;
        this.d = uVar;
        this.e = str2;
        this.h = wVar;
        this.i = z;
        if (tVar != null) {
            this.g.a(tVar);
        }
        if (z2) {
            this.k = new dji.thirdparty.b.r.a();
        } else if (z3) {
            this.j = new dji.thirdparty.b.x.a();
            this.j.a(x.e);
        }
    }

    void a(Object obj) {
        if (obj == null) {
            throw new NullPointerException("@Url parameter is null.");
        }
        this.e = obj.toString();
    }

    void a(String str, String str2) {
        if (AsyncHttpClient.HEADER_CONTENT_TYPE.equalsIgnoreCase(str)) {
            this.h = w.a(str2);
        } else {
            this.g.b(str, str2);
        }
    }

    void a(String str, String str2, boolean z) {
        if (this.e == null) {
            throw new AssertionError();
        }
        this.e = this.e.replace("{" + str + i.d, a(str2, z));
    }

    private static String a(String str, boolean z) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt < 32 || codePointAt >= TransportMediator.KEYCODE_MEDIA_PAUSE || b.indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                c cVar = new c();
                cVar.a(str, 0, i);
                a(cVar, str, i, length, z);
                return cVar.t();
            }
            i += Character.charCount(codePointAt);
        }
        return str;
    }

    private static void a(c cVar, String str, int i, int i2, boolean z) {
        c cVar2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt < 32 || codePointAt >= TransportMediator.KEYCODE_MEDIA_PAUSE || b.indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                    if (cVar2 == null) {
                        cVar2 = new c();
                    }
                    cVar2.a(codePointAt);
                    while (!cVar2.g()) {
                        int j = cVar2.j() & 255;
                        cVar.b(37);
                        cVar.b(a[(j >> 4) & 15]);
                        cVar.b(a[j & 15]);
                    }
                } else {
                    cVar.a(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    void b(String str, String str2, boolean z) {
        if (this.e != null) {
            this.f = this.d.f(this.e);
            if (this.f == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.d + ", Relative: " + this.e);
            }
            this.e = null;
        }
        if (z) {
            this.f.b(str, str2);
        } else {
            this.f.a(str, str2);
        }
    }

    void c(String str, String str2, boolean z) {
        if (z) {
            this.k.b(str, str2);
        } else {
            this.k.a(str, str2);
        }
    }

    void a(t tVar, ac acVar) {
        this.j.a(tVar, acVar);
    }

    void a(b bVar) {
        this.j.a(bVar);
    }

    void a(ac acVar) {
        this.l = acVar;
    }

    ab a() {
        u c;
        dji.thirdparty.b.u.a aVar = this.f;
        if (aVar != null) {
            c = aVar.c();
        } else {
            c = this.d.e(this.e);
            if (c == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.d + ", Relative: " + this.e);
            }
        }
        ac acVar = this.l;
        if (acVar == null) {
            if (this.k != null) {
                acVar = this.k.a();
            } else if (this.j != null) {
                acVar = this.j.a();
            } else if (this.i) {
                acVar = ac.a(null, new byte[0]);
            }
        }
        w wVar = this.h;
        if (wVar != null) {
            if (acVar != null) {
                acVar = new a(acVar, wVar);
            } else {
                this.g.b(AsyncHttpClient.HEADER_CONTENT_TYPE, wVar.toString());
            }
        }
        return this.g.a(c).a(this.c, acVar).d();
    }
}
