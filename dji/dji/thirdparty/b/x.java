package dji.thirdparty.b;

import com.f.a.a.g;
import com.loopj.android.http.AsyncHttpClient;
import dji.thirdparty.b.a.j;
import dji.thirdparty.c.c;
import dji.thirdparty.c.d;
import dji.thirdparty.c.f;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class x extends ac {
    public static final w a = w.a("multipart/mixed");
    public static final w b = w.a("multipart/alternative");
    public static final w c = w.a("multipart/digest");
    public static final w d = w.a("multipart/parallel");
    public static final w e = w.a("multipart/form-data");
    private static final byte[] f = new byte[]{(byte) 58, (byte) 32};
    private static final byte[] g = new byte[]{g.SIMPLE_LIST, (byte) 10};
    private static final byte[] h = new byte[]{(byte) 45, (byte) 45};
    private final f i;
    private final w j;
    private final w k;
    private final List<b> l;
    private long m = -1;

    public static final class a {
        private final f a;
        private w b;
        private final List<b> c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a(String str) {
            this.b = x.a;
            this.c = new ArrayList();
            this.a = f.a(str);
        }

        public a a(w wVar) {
            if (wVar == null) {
                throw new NullPointerException("type == null");
            } else if (wVar.a().equals("multipart")) {
                this.b = wVar;
                return this;
            } else {
                throw new IllegalArgumentException("multipart != " + wVar);
            }
        }

        public a a(ac acVar) {
            return a(b.a(acVar));
        }

        public a a(t tVar, ac acVar) {
            return a(b.a(tVar, acVar));
        }

        public a a(String str, String str2) {
            return a(b.a(str, str2));
        }

        public a a(String str, String str2, ac acVar) {
            return a(b.a(str, str2, acVar));
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("part == null");
            }
            this.c.add(bVar);
            return this;
        }

        public x a() {
            if (!this.c.isEmpty()) {
                return new x(this.a, this.b, this.c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    public static final class b {
        private final t a;
        private final ac b;

        public static b a(ac acVar) {
            return a(null, acVar);
        }

        public static b a(t tVar, ac acVar) {
            if (acVar == null) {
                throw new NullPointerException("body == null");
            } else if (tVar != null && tVar.a(AsyncHttpClient.HEADER_CONTENT_TYPE) != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            } else if (tVar == null || tVar.a("Content-Length") == null) {
                return new b(tVar, acVar);
            } else {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
        }

        public static b a(String str, String str2) {
            return a(str, null, ac.a(null, str2));
        }

        public static b a(String str, String str2, ac acVar) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder stringBuilder = new StringBuilder("form-data; name=");
            x.a(stringBuilder, str);
            if (str2 != null) {
                stringBuilder.append("; filename=");
                x.a(stringBuilder, str2);
            }
            return a(t.a(AsyncHttpClient.HEADER_CONTENT_DISPOSITION, stringBuilder.toString()), acVar);
        }

        private b(t tVar, ac acVar) {
            this.a = tVar;
            this.b = acVar;
        }
    }

    x(f fVar, w wVar, List<b> list) {
        this.i = fVar;
        this.j = wVar;
        this.k = w.a(wVar + "; boundary=" + fVar.a());
        this.l = j.a((List) list);
    }

    public w a() {
        return this.j;
    }

    public String d() {
        return this.i.a();
    }

    public int e() {
        return this.l.size();
    }

    public List<b> f() {
        return this.l;
    }

    public b a(int i) {
        return (b) this.l.get(i);
    }

    public w b() {
        return this.k;
    }

    public long c() throws IOException {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        j = a(null, true);
        this.m = j;
        return j;
    }

    public void a(d dVar) throws IOException {
        a(dVar, false);
    }

    private long a(d dVar, boolean z) throws IOException {
        c cVar;
        long j = 0;
        if (z) {
            c cVar2 = new c();
            cVar = cVar2;
            dVar = cVar2;
        } else {
            cVar = null;
        }
        int size = this.l.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) this.l.get(i);
            t a = bVar.a;
            ac b = bVar.b;
            dVar.d(h);
            dVar.d(this.i);
            dVar.d(g);
            if (a != null) {
                int a2 = a.a();
                for (int i2 = 0; i2 < a2; i2++) {
                    dVar.b(a.a(i2)).d(f).b(a.b(i2)).d(g);
                }
            }
            w b2 = b.b();
            if (b2 != null) {
                dVar.b("Content-Type: ").b(b2.toString()).d(g);
            }
            long c = b.c();
            if (c != -1) {
                dVar.b("Content-Length: ").n(c).d(g);
            } else if (z) {
                cVar.y();
                return -1;
            }
            dVar.d(g);
            if (z) {
                j += c;
            } else {
                b.a(dVar);
            }
            dVar.d(g);
        }
        dVar.d(h);
        dVar.d(this.i);
        dVar.d(h);
        dVar.d(g);
        if (!z) {
            return j;
        }
        j += cVar.b();
        cVar.y();
        return j;
    }

    static StringBuilder a(StringBuilder stringBuilder, String str) {
        stringBuilder.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\n':
                    stringBuilder.append("%0A");
                    break;
                case '\r':
                    stringBuilder.append("%0D");
                    break;
                case '\"':
                    stringBuilder.append("%22");
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
        }
        stringBuilder.append('\"');
        return stringBuilder;
    }
}
