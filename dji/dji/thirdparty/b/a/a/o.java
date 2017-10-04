package dji.thirdparty.b.a.a;

import android.support.v4.view.ViewCompat;
import com.here.posclient.UpdateOptions;
import dji.thirdparty.b.a.j;
import dji.thirdparty.b.z;
import dji.thirdparty.c.c;
import dji.thirdparty.c.d;
import dji.thirdparty.c.e;
import dji.thirdparty.c.f;
import dji.thirdparty.c.g;
import dji.thirdparty.c.p;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.List;
import java.util.zip.Deflater;

public final class o implements p {
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    static final int f = 6;
    static final int g = 7;
    static final int h = 8;
    static final int i = 9;
    static final int j = 1;
    static final int k = 2;
    static final int l = 3;
    static final byte[] m;

    static final class a implements b {
        private final e a;
        private final boolean b;
        private final k c = new k(this.a);

        a(e eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        public void a() {
        }

        public boolean a(dji.thirdparty.b.a.a.b.a aVar) throws IOException {
            boolean z = false;
            try {
                int l = this.a.l();
                int l2 = this.a.l();
                int i = (-16777216 & l2) >>> 24;
                l2 &= ViewCompat.MEASURED_SIZE_MASK;
                int i2;
                if ((Integer.MIN_VALUE & l) != 0) {
                    int i3 = (2147418112 & l) >>> 16;
                    i2 = 65535 & l;
                    if (i3 != 3) {
                        throw new ProtocolException("version != 3: " + i3);
                    }
                    switch (i2) {
                        case 1:
                            a(aVar, i, l2);
                            return true;
                        case 2:
                            b(aVar, i, l2);
                            return true;
                        case 3:
                            c(aVar, i, l2);
                            return true;
                        case 4:
                            h(aVar, i, l2);
                            return true;
                        case 6:
                            f(aVar, i, l2);
                            return true;
                        case 7:
                            g(aVar, i, l2);
                            return true;
                        case 8:
                            d(aVar, i, l2);
                            return true;
                        case 9:
                            e(aVar, i, l2);
                            return true;
                        default:
                            this.a.h((long) l2);
                            return true;
                    }
                }
                i2 = Integer.MAX_VALUE & l;
                if ((i & 1) != 0) {
                    z = true;
                }
                aVar.a(z, i2, this.a, l2);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        private void a(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            boolean z;
            boolean z2 = true;
            int l = this.a.l();
            int i3 = l & Integer.MAX_VALUE;
            int l2 = this.a.l() & Integer.MAX_VALUE;
            this.a.k();
            List a = this.c.a(i2 - 10);
            if ((i & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((i & 2) == 0) {
                z2 = false;
            }
            aVar.a(z2, z, i3, l2, a, g.SPDY_SYN_STREAM);
        }

        private void b(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            boolean z;
            int l = this.a.l() & Integer.MAX_VALUE;
            List a = this.c.a(i2 - 4);
            if ((i & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            aVar.a(false, z, l, -1, a, g.SPDY_REPLY);
        }

        private void c(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw a("TYPE_RST_STREAM length: %d != 8", Integer.valueOf(i2));
            }
            int l = this.a.l() & Integer.MAX_VALUE;
            a fromSpdy3Rst = a.fromSpdy3Rst(this.a.l());
            if (fromSpdy3Rst == null) {
                throw a("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(r1));
            } else {
                aVar.a(l, fromSpdy3Rst);
            }
        }

        private void d(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            aVar.a(false, false, this.a.l() & Integer.MAX_VALUE, -1, this.c.a(i2 - 4), g.SPDY_HEADERS);
        }

        private void e(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw a("TYPE_WINDOW_UPDATE length: %d != 8", Integer.valueOf(i2));
            }
            int l = this.a.l() & Integer.MAX_VALUE;
            long l2 = (long) (this.a.l() & Integer.MAX_VALUE);
            if (l2 == 0) {
                throw a("windowSizeIncrement was 0", Long.valueOf(l2));
            } else {
                aVar.a(l, l2);
            }
        }

        private void f(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            boolean z = true;
            if (i2 != 4) {
                throw a("TYPE_PING length: %d != 4", Integer.valueOf(i2));
            }
            boolean z2;
            int l = this.a.l();
            boolean z3 = this.b;
            if ((l & 1) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z3 != z2) {
                z = false;
            }
            aVar.a(z, l, 0);
        }

        private void g(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw a("TYPE_GOAWAY length: %d != 8", Integer.valueOf(i2));
            }
            int l = this.a.l() & Integer.MAX_VALUE;
            a fromSpdyGoAway = a.fromSpdyGoAway(this.a.l());
            if (fromSpdyGoAway == null) {
                throw a("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r1));
            } else {
                aVar.a(l, fromSpdyGoAway, f.b);
            }
        }

        private void h(dji.thirdparty.b.a.a.b.a aVar, int i, int i2) throws IOException {
            boolean z = true;
            int l = this.a.l();
            if (i2 != (l * 8) + 4) {
                throw a("TYPE_SETTINGS length: %d != 4 + 8 * %d", Integer.valueOf(i2), Integer.valueOf(l));
            }
            n nVar = new n();
            for (int i3 = 0; i3 < l; i3++) {
                int l2 = this.a.l();
                int i4 = (-16777216 & l2) >>> 24;
                nVar.a(l2 & ViewCompat.MEASURED_SIZE_MASK, i4, this.a.l());
            }
            if ((i & 1) == 0) {
                z = false;
            }
            aVar.a(z, nVar);
        }

        private static IOException a(String str, Object... objArr) throws IOException {
            throw new IOException(String.format(str, objArr));
        }

        public void close() throws IOException {
            this.c.a();
        }
    }

    static final class b implements c {
        private final d a;
        private final c b = new c();
        private final d c;
        private final boolean d;
        private boolean e;

        b(d dVar, boolean z) {
            this.a = dVar;
            this.d = z;
            Deflater deflater = new Deflater();
            deflater.setDictionary(o.m);
            this.c = p.a(new g(this.b, deflater));
        }

        public void a(n nVar) {
        }

        public void a(int i, int i2, List<f> list) throws IOException {
        }

        public synchronized void a() {
        }

        public synchronized void b() throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            this.a.flush();
        }

        public synchronized void a(boolean z, boolean z2, int i, int i2, List<f> list) throws IOException {
            int i3 = 0;
            synchronized (this) {
                if (this.e) {
                    throw new IOException("closed");
                }
                a((List) list);
                int b = (int) (10 + this.b.b());
                int i4 = z ? 1 : 0;
                if (z2) {
                    i3 = 2;
                }
                i3 |= i4;
                this.a.j(-2147287039);
                this.a.j(((i3 & 255) << 24) | (b & ViewCompat.MEASURED_SIZE_MASK));
                this.a.j(i & Integer.MAX_VALUE);
                this.a.j(i2 & Integer.MAX_VALUE);
                this.a.l(0);
                this.a.a(this.b);
                this.a.flush();
            }
        }

        public synchronized void a(boolean z, int i, List<f> list) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            a((List) list);
            int i2 = z ? 1 : 0;
            int b = (int) (this.b.b() + 4);
            this.a.j(-2147287038);
            this.a.j(((i2 & 255) << 24) | (b & ViewCompat.MEASURED_SIZE_MASK));
            this.a.j(Integer.MAX_VALUE & i);
            this.a.a(this.b);
            this.a.flush();
        }

        public synchronized void a(int i, List<f> list) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            a((List) list);
            int b = (int) (this.b.b() + 4);
            this.a.j(-2147287032);
            this.a.j((b & ViewCompat.MEASURED_SIZE_MASK) | 0);
            this.a.j(Integer.MAX_VALUE & i);
            this.a.a(this.b);
        }

        public synchronized void a(int i, a aVar) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (aVar.t == -1) {
                throw new IllegalArgumentException();
            } else {
                this.a.j(-2147287037);
                this.a.j(8);
                this.a.j(Integer.MAX_VALUE & i);
                this.a.j(aVar.t);
                this.a.flush();
            }
        }

        public int c() {
            return dji.pilot.newfpv.topbar.widget.a.s;
        }

        public synchronized void a(boolean z, int i, c cVar, int i2) throws IOException {
            a(i, z ? 1 : 0, cVar, i2);
        }

        void a(int i, int i2, c cVar, int i3) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (((long) i3) > 16777215) {
                throw new IllegalArgumentException("FRAME_TOO_LARGE max size is 16Mib: " + i3);
            } else {
                this.a.j(Integer.MAX_VALUE & i);
                this.a.j(((i2 & 255) << 24) | (ViewCompat.MEASURED_SIZE_MASK & i3));
                if (i3 > 0) {
                    this.a.a_(cVar, (long) i3);
                }
            }
        }

        private void a(List<f> list) throws IOException {
            this.c.j(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                f fVar = ((f) list.get(i)).h;
                this.c.j(fVar.j());
                this.c.d(fVar);
                fVar = ((f) list.get(i)).i;
                this.c.j(fVar.j());
                this.c.d(fVar);
            }
            this.c.flush();
        }

        public synchronized void b(n nVar) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            int b = nVar.b();
            int i = (b * 8) + 4;
            this.a.j(-2147287036);
            this.a.j((i & ViewCompat.MEASURED_SIZE_MASK) | 0);
            this.a.j(b);
            for (b = 0; b <= 10; b++) {
                if (nVar.a(b)) {
                    this.a.j(((nVar.c(b) & 255) << 24) | (b & ViewCompat.MEASURED_SIZE_MASK));
                    this.a.j(nVar.b(b));
                }
            }
            this.a.flush();
        }

        public synchronized void a(boolean z, int i, int i2) throws IOException {
            boolean z2 = true;
            synchronized (this) {
                if (this.e) {
                    throw new IOException("closed");
                }
                boolean z3;
                boolean z4 = this.d;
                if ((i & 1) == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z4 == z3) {
                    z2 = false;
                }
                if (z != z2) {
                    throw new IllegalArgumentException("payload != reply");
                }
                this.a.j(-2147287034);
                this.a.j(4);
                this.a.j(i);
                this.a.flush();
            }
        }

        public synchronized void a(int i, a aVar, byte[] bArr) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (aVar.u == -1) {
                throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
            } else {
                this.a.j(-2147287033);
                this.a.j(8);
                this.a.j(i);
                this.a.j(aVar.u);
                this.a.flush();
            }
        }

        public synchronized void a(int i, long j) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (j == 0 || j > UpdateOptions.SOURCE_ANY) {
                throw new IllegalArgumentException("windowSizeIncrement must be between 1 and 0x7fffffff: " + j);
            } else {
                this.a.j(-2147287031);
                this.a.j(8);
                this.a.j(i);
                this.a.j((int) j);
                this.a.flush();
            }
        }

        public synchronized void close() throws IOException {
            this.e = true;
            j.a(this.a, this.c);
        }
    }

    public z a() {
        return z.SPDY_3;
    }

    static {
        try {
            m = "\u0000\u0000\u0000\u0007options\u0000\u0000\u0000\u0004head\u0000\u0000\u0000\u0004post\u0000\u0000\u0000\u0003put\u0000\u0000\u0000\u0006delete\u0000\u0000\u0000\u0005trace\u0000\u0000\u0000\u0006accept\u0000\u0000\u0000\u000eaccept-charset\u0000\u0000\u0000\u000faccept-encoding\u0000\u0000\u0000\u000faccept-language\u0000\u0000\u0000\raccept-ranges\u0000\u0000\u0000\u0003age\u0000\u0000\u0000\u0005allow\u0000\u0000\u0000\rauthorization\u0000\u0000\u0000\rcache-control\u0000\u0000\u0000\nconnection\u0000\u0000\u0000\fcontent-base\u0000\u0000\u0000\u0010content-encoding\u0000\u0000\u0000\u0010content-language\u0000\u0000\u0000\u000econtent-length\u0000\u0000\u0000\u0010content-location\u0000\u0000\u0000\u000bcontent-md5\u0000\u0000\u0000\rcontent-range\u0000\u0000\u0000\fcontent-type\u0000\u0000\u0000\u0004date\u0000\u0000\u0000\u0004etag\u0000\u0000\u0000\u0006expect\u0000\u0000\u0000\u0007expires\u0000\u0000\u0000\u0004from\u0000\u0000\u0000\u0004host\u0000\u0000\u0000\bif-match\u0000\u0000\u0000\u0011if-modified-since\u0000\u0000\u0000\rif-none-match\u0000\u0000\u0000\bif-range\u0000\u0000\u0000\u0013if-unmodified-since\u0000\u0000\u0000\rlast-modified\u0000\u0000\u0000\blocation\u0000\u0000\u0000\fmax-forwards\u0000\u0000\u0000\u0006pragma\u0000\u0000\u0000\u0012proxy-authenticate\u0000\u0000\u0000\u0013proxy-authorization\u0000\u0000\u0000\u0005range\u0000\u0000\u0000\u0007referer\u0000\u0000\u0000\u000bretry-after\u0000\u0000\u0000\u0006server\u0000\u0000\u0000\u0002te\u0000\u0000\u0000\u0007trailer\u0000\u0000\u0000\u0011transfer-encoding\u0000\u0000\u0000\u0007upgrade\u0000\u0000\u0000\nuser-agent\u0000\u0000\u0000\u0004vary\u0000\u0000\u0000\u0003via\u0000\u0000\u0000\u0007warning\u0000\u0000\u0000\u0010www-authenticate\u0000\u0000\u0000\u0006method\u0000\u0000\u0000\u0003get\u0000\u0000\u0000\u0006status\u0000\u0000\u0000\u0006200 OK\u0000\u0000\u0000\u0007version\u0000\u0000\u0000\bHTTP/1.1\u0000\u0000\u0000\u0003url\u0000\u0000\u0000\u0006public\u0000\u0000\u0000\nset-cookie\u0000\u0000\u0000\nkeep-alive\u0000\u0000\u0000\u0006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(j.c.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }

    public b a(e eVar, boolean z) {
        return new a(eVar, z);
    }

    public c a(d dVar, boolean z) {
        return new b(dVar, z);
    }
}
