package dji.thirdparty.b;

import com.google.api.client.http.HttpMethods;
import com.here.posclient.UpdateOptions;
import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.a.b.q;
import dji.thirdparty.b.a.e;
import dji.thirdparty.b.a.j;
import dji.thirdparty.c.d;
import dji.thirdparty.c.f;
import dji.thirdparty.c.h;
import dji.thirdparty.c.i;
import dji.thirdparty.c.p;
import dji.thirdparty.c.v;
import dji.thirdparty.c.w;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class c implements Closeable, Flushable {
    private static final int b = 201105;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    final e a;
    private final dji.thirdparty.b.a.b f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    private final class a implements dji.thirdparty.b.a.b.a {
        final /* synthetic */ c a;
        private final dji.thirdparty.b.a.b.a b;
        private v c;
        private boolean d;
        private v e;

        public a(final c cVar, final dji.thirdparty.b.a.b.a aVar) throws IOException {
            this.a = cVar;
            this.b = aVar;
            this.c = aVar.b(1);
            this.e = new h(this, this.c) {
                final /* synthetic */ a c;

                public void close() throws IOException {
                    synchronized (this.c.a) {
                        if (this.c.d) {
                            return;
                        }
                        this.c.d = true;
                        this.c.a.g = this.c.a.g + 1;
                        super.close();
                        aVar.a();
                    }
                }
            };
        }

        public void a() {
            synchronized (this.a) {
                if (this.d) {
                    return;
                }
                this.d = true;
                this.a.h = this.a.h + 1;
                j.a(this.c);
                try {
                    this.b.b();
                } catch (IOException e) {
                }
            }
        }

        public v b() {
            return this.e;
        }
    }

    private static class b extends ae {
        private final dji.thirdparty.b.a.b.c a;
        private final dji.thirdparty.c.e b;
        private final String c;
        private final String d;

        public b(final dji.thirdparty.b.a.b.c cVar, String str, String str2) {
            this.a = cVar;
            this.c = str;
            this.d = str2;
            this.b = p.a(new i(this, cVar.a(1)) {
                final /* synthetic */ b b;

                public void close() throws IOException {
                    cVar.close();
                    super.close();
                }
            });
        }

        public w a() {
            return this.c != null ? w.a(this.c) : null;
        }

        public long b() {
            long j = -1;
            try {
                if (this.d != null) {
                    j = Long.parseLong(this.d);
                }
            } catch (NumberFormatException e) {
            }
            return j;
        }

        public dji.thirdparty.c.e c() {
            return this.b;
        }
    }

    private static final class c {
        private final String a;
        private final t b;
        private final String c;
        private final z d;
        private final int e;
        private final String f;
        private final t g;
        private final s h;

        public c(w wVar) throws IOException {
            ag agVar = null;
            int i = 0;
            try {
                dji.thirdparty.c.e a = p.a(wVar);
                this.a = a.v();
                this.c = a.v();
                dji.thirdparty.b.t.a aVar = new dji.thirdparty.b.t.a();
                int a2 = c.b(a);
                for (int i2 = 0; i2 < a2; i2++) {
                    aVar.a(a.v());
                }
                this.b = aVar.a();
                q a3 = q.a(a.v());
                this.d = a3.d;
                this.e = a3.e;
                this.f = a3.f;
                dji.thirdparty.b.t.a aVar2 = new dji.thirdparty.b.t.a();
                int a4 = c.b(a);
                while (i < a4) {
                    aVar2.a(a.v());
                    i++;
                }
                this.g = aVar2.a();
                if (a()) {
                    String v = a.v();
                    if (v.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + v + "\"");
                    }
                    i forJavaName = i.forJavaName(a.v());
                    List a5 = a(a);
                    List a6 = a(a);
                    if (!a.g()) {
                        agVar = ag.forJavaName(a.v());
                    }
                    this.h = s.a(agVar, forJavaName, a5, a6);
                } else {
                    this.h = null;
                }
                wVar.close();
            } catch (Throwable th) {
                wVar.close();
            }
        }

        public c(ad adVar) {
            this.a = adVar.a().a().toString();
            this.b = dji.thirdparty.b.a.b.j.c(adVar);
            this.c = adVar.a().b();
            this.d = adVar.b();
            this.e = adVar.c();
            this.f = adVar.e();
            this.g = adVar.g();
            this.h = adVar.f();
        }

        public void a(dji.thirdparty.b.a.b.a aVar) throws IOException {
            int i;
            int i2 = 0;
            d a = p.a(aVar.b(0));
            a.b(this.a);
            a.m(10);
            a.b(this.c);
            a.m(10);
            a.n((long) this.b.a());
            a.m(10);
            int a2 = this.b.a();
            for (i = 0; i < a2; i++) {
                a.b(this.b.a(i));
                a.b(": ");
                a.b(this.b.b(i));
                a.m(10);
            }
            a.b(new q(this.d, this.e, this.f).toString());
            a.m(10);
            a.n((long) this.g.a());
            a.m(10);
            i = this.g.a();
            while (i2 < i) {
                a.b(this.g.a(i2));
                a.b(": ");
                a.b(this.g.b(i2));
                a.m(10);
                i2++;
            }
            if (a()) {
                a.m(10);
                a.b(this.h.b().a());
                a.m(10);
                a(a, this.h.c());
                a(a, this.h.e());
                if (this.h.a() != null) {
                    a.b(this.h.a().a());
                    a.m(10);
                }
            }
            a.close();
        }

        private boolean a() {
            return this.a.startsWith("https://");
        }

        private List<Certificate> a(dji.thirdparty.c.e eVar) throws IOException {
            int a = c.b(eVar);
            if (a == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                List<Certificate> arrayList = new ArrayList(a);
                for (int i = 0; i < a; i++) {
                    String v = eVar.v();
                    dji.thirdparty.c.c cVar = new dji.thirdparty.c.c();
                    cVar.a(f.b(v));
                    arrayList.add(instance.generateCertificate(cVar.h()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void a(d dVar, List<Certificate> list) throws IOException {
            try {
                dVar.n((long) list.size());
                dVar.m(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    dVar.b(f.a(((Certificate) list.get(i)).getEncoded()).b());
                    dVar.m(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public boolean a(ab abVar, ad adVar) {
            return this.a.equals(abVar.a().toString()) && this.c.equals(abVar.b()) && dji.thirdparty.b.a.b.j.a(adVar, this.b, abVar);
        }

        public ad a(dji.thirdparty.b.a.b.c cVar) {
            String a = this.g.a(AsyncHttpClient.HEADER_CONTENT_TYPE);
            String a2 = this.g.a("Content-Length");
            return new dji.thirdparty.b.ad.a().a(new dji.thirdparty.b.ab.a().a(this.a).a(this.c, null).a(this.b).d()).a(this.d).a(this.e).a(this.f).a(this.g).a(new b(cVar, a, a2)).a(this.h).a();
        }
    }

    public c(File file, long j) {
        this(file, j, dji.thirdparty.b.a.c.a.a);
    }

    c(File file, long j, dji.thirdparty.b.a.c.a aVar) {
        this.a = new e(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public ad a(ab abVar) throws IOException {
                return this.a.a(abVar);
            }

            public dji.thirdparty.b.a.b.a a(ad adVar) throws IOException {
                return this.a.a(adVar);
            }

            public void b(ab abVar) throws IOException {
                this.a.c(abVar);
            }

            public void a(ad adVar, ad adVar2) throws IOException {
                this.a.a(adVar, adVar2);
            }

            public void a() {
                this.a.n();
            }

            public void a(dji.thirdparty.b.a.b.b bVar) {
                this.a.a(bVar);
            }
        };
        this.f = dji.thirdparty.b.a.b.a(aVar, file, b, 2, j);
    }

    private static String b(ab abVar) {
        return j.a(abVar.a().toString());
    }

    ad a(ab abVar) {
        try {
            Closeable a = this.f.a(b(abVar));
            if (a == null) {
                return null;
            }
            try {
                c cVar = new c(a.a(0));
                ad a2 = cVar.a((dji.thirdparty.b.a.b.c) a);
                if (cVar.a(abVar, a2)) {
                    return a2;
                }
                j.a(a2.h());
                return null;
            } catch (IOException e) {
                j.a(a);
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    private dji.thirdparty.b.a.b.a a(ad adVar) throws IOException {
        dji.thirdparty.b.a.b.a aVar;
        String b = adVar.a().b();
        if (dji.thirdparty.b.a.b.h.a(adVar.a().b())) {
            try {
                c(adVar.a());
                return null;
            } catch (IOException e) {
                return null;
            }
        } else if (!b.equals(HttpMethods.GET) || dji.thirdparty.b.a.b.j.b(adVar)) {
            return null;
        } else {
            c cVar = new c(adVar);
            try {
                dji.thirdparty.b.a.b.a b2 = this.f.b(b(adVar.a()));
                if (b2 == null) {
                    return null;
                }
                try {
                    cVar.a(b2);
                    return new a(this, b2);
                } catch (IOException e2) {
                    aVar = b2;
                    a(aVar);
                    return null;
                }
            } catch (IOException e3) {
                aVar = null;
                a(aVar);
                return null;
            }
        }
    }

    private void c(ab abVar) throws IOException {
        this.f.c(b(abVar));
    }

    private void a(ad adVar, ad adVar2) {
        c cVar = new c(adVar2);
        try {
            dji.thirdparty.b.a.b.a b = ((b) adVar.h()).a.b();
            if (b != null) {
                cVar.a(b);
                b.a();
            }
        } catch (IOException e) {
            a(null);
        }
    }

    private void a(dji.thirdparty.b.a.b.a aVar) {
        if (aVar != null) {
            try {
                aVar.b();
            } catch (IOException e) {
            }
        }
    }

    public void a() throws IOException {
        this.f.a();
    }

    public void b() throws IOException {
        this.f.f();
    }

    public void c() throws IOException {
        this.f.g();
    }

    public Iterator<String> d() throws IOException {
        return new Iterator<String>(this) {
            final Iterator<dji.thirdparty.b.a.b.c> a = this.d.f.h();
            String b;
            boolean c;
            final /* synthetic */ c d;

            {
                this.d = r2;
            }

            public /* synthetic */ Object next() {
                return a();
            }

            public boolean hasNext() {
                if (this.b != null) {
                    return true;
                }
                this.c = false;
                while (this.a.hasNext()) {
                    dji.thirdparty.b.a.b.c cVar = (dji.thirdparty.b.a.b.c) this.a.next();
                    try {
                        this.b = p.a(cVar.a(0)).v();
                        cVar.close();
                        return true;
                    } catch (IOException e) {
                        cVar.close();
                    } catch (Throwable th) {
                        cVar.close();
                    }
                }
                return false;
            }

            public String a() {
                if (hasNext()) {
                    String str = this.b;
                    this.b = null;
                    this.c = true;
                    return str;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                if (this.c) {
                    this.a.remove();
                    return;
                }
                throw new IllegalStateException("remove() before next()");
            }
        };
    }

    public synchronized int e() {
        return this.h;
    }

    public synchronized int f() {
        return this.g;
    }

    public long g() throws IOException {
        return this.f.d();
    }

    public long h() {
        return this.f.c();
    }

    public void flush() throws IOException {
        this.f.flush();
    }

    public void close() throws IOException {
        this.f.close();
    }

    public File i() {
        return this.f.b();
    }

    public boolean j() {
        return this.f.e();
    }

    private synchronized void a(dji.thirdparty.b.a.b.b bVar) {
        this.k++;
        if (bVar.a != null) {
            this.i++;
        } else if (bVar.b != null) {
            this.j++;
        }
    }

    private synchronized void n() {
        this.j++;
    }

    public synchronized int k() {
        return this.i;
    }

    public synchronized int l() {
        return this.j;
    }

    public synchronized int m() {
        return this.k;
    }

    private static int b(dji.thirdparty.c.e eVar) throws IOException {
        try {
            long q = eVar.q();
            String v = eVar.v();
            if (q >= 0 && q <= UpdateOptions.SOURCE_ANY && v.isEmpty()) {
                return (int) q;
            }
            throw new IOException("expected an int but was \"" + q + v + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }
}
