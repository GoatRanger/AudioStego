package dji.thirdparty.b.a.b;

import com.alipay.sdk.b.c;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.thirdparty.b.a.a.d;
import dji.thirdparty.b.a.j;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.t;
import dji.thirdparty.b.z;
import dji.thirdparty.c.f;
import dji.thirdparty.c.i;
import dji.thirdparty.c.p;
import dji.thirdparty.c.v;
import dji.thirdparty.c.w;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class e implements i {
    private static final f b = f.a("connection");
    private static final f c = f.a(c.f);
    private static final f d = f.a("keep-alive");
    private static final f e = f.a("proxy-connection");
    private static final f f = f.a("transfer-encoding");
    private static final f g = f.a("te");
    private static final f h = f.a("encoding");
    private static final f i = f.a("upgrade");
    private static final List<f> j = j.a(b, c, d, e, f, dji.thirdparty.b.a.a.f.b, dji.thirdparty.b.a.a.f.c, dji.thirdparty.b.a.a.f.d, dji.thirdparty.b.a.a.f.e, dji.thirdparty.b.a.a.f.f, dji.thirdparty.b.a.a.f.g);
    private static final List<f> k = j.a(b, c, d, e, f);
    private static final List<f> l = j.a(b, c, d, e, g, f, h, i, dji.thirdparty.b.a.a.f.b, dji.thirdparty.b.a.a.f.c, dji.thirdparty.b.a.a.f.d, dji.thirdparty.b.a.a.f.e, dji.thirdparty.b.a.a.f.f, dji.thirdparty.b.a.a.f.g);
    private static final List<f> m = j.a(b, c, d, e, g, f, h, i);
    private final r n;
    private final d o;
    private g p;
    private dji.thirdparty.b.a.a.e q;

    class a extends i {
        final /* synthetic */ e a;

        public a(e eVar, w wVar) {
            this.a = eVar;
            super(wVar);
        }

        public void close() throws IOException {
            this.a.n.a(false, this.a);
            super.close();
        }
    }

    public e(r rVar, d dVar) {
        this.n = rVar;
        this.o = dVar;
    }

    public void a(g gVar) {
        this.p = gVar;
    }

    public v a(ab abVar, long j) throws IOException {
        return this.q.k();
    }

    public void a(ab abVar) throws IOException {
        if (this.q == null) {
            List c;
            this.p.b();
            boolean a = this.p.a(abVar);
            if (this.o.a() == z.HTTP_2) {
                c = c(abVar);
            } else {
                c = b(abVar);
            }
            this.q = this.o.a(c, a, true);
            this.q.h().a((long) this.p.b.b(), TimeUnit.MILLISECONDS);
            this.q.i().a((long) this.p.b.c(), TimeUnit.MILLISECONDS);
        }
    }

    public void a(n nVar) throws IOException {
        nVar.a(this.q.k());
    }

    public void d() throws IOException {
        this.q.k().close();
    }

    public dji.thirdparty.b.ad.a b() throws IOException {
        if (this.o.a() == z.HTTP_2) {
            return b(this.q.f());
        }
        return a(this.q.f());
    }

    public static List<dji.thirdparty.b.a.a.f> b(ab abVar) {
        t c = abVar.c();
        List<dji.thirdparty.b.a.a.f> arrayList = new ArrayList(c.a() + 5);
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.b, abVar.b()));
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.c, m.a(abVar.a())));
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.g, "HTTP/1.1"));
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.f, j.a(abVar.a(), false)));
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.d, abVar.a().c()));
        Set linkedHashSet = new LinkedHashSet();
        int a = c.a();
        for (int i = 0; i < a; i++) {
            f a2 = f.a(c.a(i).toLowerCase(Locale.US));
            if (!j.contains(a2)) {
                String b = c.b(i);
                if (linkedHashSet.add(a2)) {
                    arrayList.add(new dji.thirdparty.b.a.a.f(a2, b));
                } else {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (((dji.thirdparty.b.a.a.f) arrayList.get(i2)).h.equals(a2)) {
                            arrayList.set(i2, new dji.thirdparty.b.a.a.f(a2, a(((dji.thirdparty.b.a.a.f) arrayList.get(i2)).i.a(), b)));
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static String a(String str, String str2) {
        return '\u0000' + str2;
    }

    public static List<dji.thirdparty.b.a.a.f> c(ab abVar) {
        int i = 0;
        t c = abVar.c();
        List<dji.thirdparty.b.a.a.f> arrayList = new ArrayList(c.a() + 4);
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.b, abVar.b()));
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.c, m.a(abVar.a())));
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.e, j.a(abVar.a(), false)));
        arrayList.add(new dji.thirdparty.b.a.a.f(dji.thirdparty.b.a.a.f.d, abVar.a().c()));
        int a = c.a();
        while (i < a) {
            f a2 = f.a(c.a(i).toLowerCase(Locale.US));
            if (!l.contains(a2)) {
                arrayList.add(new dji.thirdparty.b.a.a.f(a2, c.b(i)));
            }
            i++;
        }
        return arrayList;
    }

    public static dji.thirdparty.b.ad.a a(List<dji.thirdparty.b.a.a.f> list) throws IOException {
        String str = null;
        String str2 = "HTTP/1.1";
        dji.thirdparty.b.t.a aVar = new dji.thirdparty.b.t.a();
        int size = list.size();
        int i = 0;
        while (i < size) {
            f fVar = ((dji.thirdparty.b.a.a.f) list.get(i)).h;
            String a = ((dji.thirdparty.b.a.a.f) list.get(i)).i.a();
            String str3 = str2;
            int i2 = 0;
            while (i2 < a.length()) {
                int indexOf = a.indexOf(0, i2);
                if (indexOf == -1) {
                    indexOf = a.length();
                }
                str2 = a.substring(i2, indexOf);
                if (!fVar.equals(dji.thirdparty.b.a.a.f.a)) {
                    if (fVar.equals(dji.thirdparty.b.a.a.f.g)) {
                        str3 = str2;
                        str2 = str;
                    } else {
                        if (!k.contains(fVar)) {
                            aVar.a(fVar.a(), str2);
                        }
                        str2 = str;
                    }
                }
                str = str2;
                i2 = indexOf + 1;
            }
            i++;
            str2 = str3;
        }
        if (str == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        q a2 = q.a(str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        return new dji.thirdparty.b.ad.a().a(z.SPDY_3).a(a2.e).a(a2.f).a(aVar.a());
    }

    public static dji.thirdparty.b.ad.a b(List<dji.thirdparty.b.a.a.f> list) throws IOException {
        String str = null;
        dji.thirdparty.b.t.a aVar = new dji.thirdparty.b.t.a();
        int size = list.size();
        int i = 0;
        while (i < size) {
            f fVar = ((dji.thirdparty.b.a.a.f) list.get(i)).h;
            String a = ((dji.thirdparty.b.a.a.f) list.get(i)).i.a();
            if (!fVar.equals(dji.thirdparty.b.a.a.f.a)) {
                if (!m.contains(fVar)) {
                    aVar.a(fVar.a(), a);
                }
                a = str;
            }
            i++;
            str = a;
        }
        if (str == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        q a2 = q.a("HTTP/1.1 " + str);
        return new dji.thirdparty.b.ad.a().a(z.HTTP_2).a(a2.e).a(a2.f).a(aVar.a());
    }

    public ae a(ad adVar) throws IOException {
        return new k(adVar.g(), p.a(new a(this, this.q.j())));
    }

    public void a() {
        if (this.q != null) {
            this.q.b(dji.thirdparty.b.a.a.a.CANCEL);
        }
    }
}
