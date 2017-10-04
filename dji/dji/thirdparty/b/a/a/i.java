package dji.thirdparty.b.a.a;

import android.support.v4.view.ViewCompat;
import com.here.posclient.UpdateOptions;
import dji.thirdparty.b.z;
import dji.thirdparty.c.e;
import dji.thirdparty.c.f;
import dji.thirdparty.c.w;
import dji.thirdparty.c.x;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class i implements p {
    static final int a = 16384;
    static final byte b = (byte) 0;
    static final byte c = (byte) 1;
    static final byte d = (byte) 2;
    static final byte e = (byte) 3;
    static final byte f = (byte) 4;
    static final byte g = (byte) 5;
    static final byte h = (byte) 6;
    static final byte i = (byte) 7;
    static final byte j = (byte) 8;
    static final byte k = (byte) 9;
    static final byte l = (byte) 0;
    static final byte m = (byte) 1;
    static final byte n = (byte) 1;
    static final byte o = (byte) 4;
    static final byte p = (byte) 4;
    static final byte q = (byte) 8;
    static final byte r = (byte) 32;
    static final byte s = (byte) 32;
    private static final Logger t = Logger.getLogger(b.class.getName());
    private static final f u = f.a("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    static final class a implements w {
        int a;
        byte b;
        int c;
        int d;
        short e;
        private final e f;

        public a(e eVar) {
            this.f = eVar;
        }

        public long a(dji.thirdparty.c.c cVar, long j) throws IOException {
            while (this.d == 0) {
                this.f.h((long) this.e);
                this.e = (short) 0;
                if ((this.b & 4) != 0) {
                    return -1;
                }
                b();
            }
            long a = this.f.a(cVar, Math.min(j, (long) this.d));
            if (a == -1) {
                return -1;
            }
            this.d = (int) (((long) this.d) - a);
            return a;
        }

        public x a() {
            return this.f.a();
        }

        public void close() throws IOException {
        }

        private void b() throws IOException {
            int i = this.c;
            int a = i.b(this.f);
            this.d = a;
            this.a = a;
            byte j = (byte) (this.f.j() & 255);
            this.b = (byte) (this.f.j() & 255);
            if (i.t.isLoggable(Level.FINE)) {
                i.t.fine(b.a(true, this.c, this.a, j, this.b));
            }
            this.c = this.f.l() & Integer.MAX_VALUE;
            if (j != (byte) 9) {
                throw i.d("%s != TYPE_CONTINUATION", Byte.valueOf(j));
            } else if (this.c != i) {
                throw i.d("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    static final class b {
        private static final String[] a = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        private static final String[] b = new String[64];
        private static final String[] c = new String[256];

        b() {
        }

        static String a(boolean z, int i, int i2, byte b, byte b2) {
            String format = b < a.length ? a[b] : String.format("0x%02x", new Object[]{Byte.valueOf(b)});
            String a = a(b, b2);
            String str = "%s 0x%08x %5d %-13s %s";
            Object[] objArr = new Object[5];
            objArr[0] = z ? "<<" : ">>";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            objArr[3] = format;
            objArr[4] = a;
            return String.format(str, objArr);
        }

        static String a(byte b, byte b2) {
            if (b2 == (byte) 0) {
                return "";
            }
            switch (b) {
                case (byte) 2:
                case (byte) 3:
                case (byte) 7:
                case (byte) 8:
                    return c[b2];
                case (byte) 4:
                case (byte) 6:
                    return b2 == (byte) 1 ? "ACK" : c[b2];
                default:
                    String str = b2 < b.length ? b[b2] : c[b2];
                    if (b == (byte) 5 && (b2 & 4) != 0) {
                        return str.replace("HEADERS", "PUSH_PROMISE");
                    }
                    if (b != (byte) 0 || (b2 & 32) == 0) {
                        return str;
                    }
                    return str.replace("PRIORITY", "COMPRESSED");
            }
        }

        static {
            int i = 0;
            for (int i2 = 0; i2 < c.length; i2++) {
                c[i2] = String.format("%8s", new Object[]{Integer.toBinaryString(i2)}).replace(' ', '0');
            }
            b[0] = "";
            b[1] = "END_STREAM";
            int[] iArr = new int[]{1};
            b[8] = "PADDED";
            for (int i3 : iArr) {
                b[i3 | 8] = b[i3] + "|PADDED";
            }
            b[4] = "END_HEADERS";
            b[32] = "PRIORITY";
            b[36] = "END_HEADERS|PRIORITY";
            for (int i4 : new int[]{4, 32, 36}) {
                for (int i5 : iArr) {
                    b[i5 | i4] = b[i5] + '|' + b[i4];
                    b[(i5 | i4) | 8] = b[i5] + '|' + b[i4] + "|PADDED";
                }
            }
            while (i < b.length) {
                if (b[i] == null) {
                    b[i] = c[i];
                }
                i++;
            }
        }
    }

    static final class c implements b {
        final a a;
        private final e b;
        private final a c = new a(this.b);
        private final boolean d;

        c(e eVar, int i, boolean z) {
            this.b = eVar;
            this.d = z;
            this.a = new a(i, this.c);
        }

        public void a() throws IOException {
            if (!this.d) {
                f d = this.b.d((long) i.u.j());
                if (i.t.isLoggable(Level.FINE)) {
                    i.t.fine(String.format("<< CONNECTION %s", new Object[]{d.g()}));
                }
                if (!i.u.equals(d)) {
                    throw i.d("Expected a connection header but was %s", d.a());
                }
            }
        }

        public boolean a(dji.thirdparty.b.a.a.b.a aVar) throws IOException {
            try {
                this.b.a(9);
                int a = i.b(this.b);
                if (a < 0 || a > 16384) {
                    throw i.d("FRAME_SIZE_ERROR: %s", Integer.valueOf(a));
                }
                byte j = (byte) (this.b.j() & 255);
                byte j2 = (byte) (this.b.j() & 255);
                int l = this.b.l() & Integer.MAX_VALUE;
                if (i.t.isLoggable(Level.FINE)) {
                    i.t.fine(b.a(true, l, a, j, j2));
                }
                switch (j) {
                    case (byte) 0:
                        b(aVar, a, j2, l);
                        return true;
                    case (byte) 1:
                        a(aVar, a, j2, l);
                        return true;
                    case (byte) 2:
                        c(aVar, a, j2, l);
                        return true;
                    case (byte) 3:
                        d(aVar, a, j2, l);
                        return true;
                    case (byte) 4:
                        e(aVar, a, j2, l);
                        return true;
                    case (byte) 5:
                        f(aVar, a, j2, l);
                        return true;
                    case (byte) 6:
                        g(aVar, a, j2, l);
                        return true;
                    case (byte) 7:
                        h(aVar, a, j2, l);
                        return true;
                    case (byte) 8:
                        i(aVar, a, j2, l);
                        return true;
                    default:
                        this.b.h((long) a);
                        return true;
                }
            } catch (IOException e) {
                return false;
            }
        }

        private void a(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            if (i2 == 0) {
                throw i.d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            }
            short j;
            boolean z = (b & 1) != 0;
            if ((b & 8) != 0) {
                j = (short) (this.b.j() & 255);
            } else {
                j = (short) 0;
            }
            if ((b & 32) != 0) {
                a(aVar, i2);
                i -= 5;
            }
            aVar.a(false, z, i2, -1, a(i.b(i, b, j), j, b, i2), g.HTTP_20_HEADERS);
        }

        private List<f> a(int i, short s, byte b, int i2) throws IOException {
            a aVar = this.c;
            this.c.d = i;
            aVar.a = i;
            this.c.e = s;
            this.c.b = b;
            this.c.c = i2;
            this.a.b();
            return this.a.c();
        }

        private void b(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            boolean z;
            short s = (short) 1;
            short s2 = (short) 0;
            if ((b & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((b & 32) == 0) {
                s = (short) 0;
            }
            if (s != (short) 0) {
                throw i.d("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            }
            if ((b & 8) != 0) {
                s2 = (short) (this.b.j() & 255);
            }
            aVar.a(z, i2, this.b, i.b(i, b, s2));
            this.b.h((long) s2);
        }

        private void c(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            if (i != 5) {
                throw i.d("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            } else if (i2 == 0) {
                throw i.d("TYPE_PRIORITY streamId == 0", new Object[0]);
            } else {
                a(aVar, i2);
            }
        }

        private void a(dji.thirdparty.b.a.a.b.a aVar, int i) throws IOException {
            int l = this.b.l();
            aVar.a(i, l & Integer.MAX_VALUE, (this.b.j() & 255) + 1, (Integer.MIN_VALUE & l) != 0);
        }

        private void d(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                throw i.d("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            } else if (i2 == 0) {
                throw i.d("TYPE_RST_STREAM streamId == 0", new Object[0]);
            } else {
                a fromHttp2 = a.fromHttp2(this.b.l());
                if (fromHttp2 == null) {
                    throw i.d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(r0));
                } else {
                    aVar.a(i2, fromHttp2);
                }
            }
        }

        private void e(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            if (i2 != 0) {
                throw i.d("TYPE_SETTINGS streamId != 0", new Object[0]);
            } else if ((b & 1) != 0) {
                if (i != 0) {
                    throw i.d("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                }
                aVar.a();
            } else if (i % 6 != 0) {
                throw i.d("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            } else {
                n nVar = new n();
                for (int i3 = 0; i3 < i; i3 += 6) {
                    int k = this.b.k();
                    int l = this.b.l();
                    switch (k) {
                        case 2:
                            if (!(l == 0 || l == 1)) {
                                throw i.d("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                            }
                        case 3:
                            k = 4;
                            break;
                        case 4:
                            k = 7;
                            if (l >= 0) {
                                break;
                            }
                            throw i.d("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        case 5:
                            if (l >= 16384 && l <= ViewCompat.MEASURED_SIZE_MASK) {
                                break;
                            }
                            throw i.d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(l));
                        default:
                            break;
                    }
                    nVar.a(k, 0, l);
                }
                aVar.a(false, nVar);
                if (nVar.c() >= 0) {
                    this.a.a(nVar.c());
                }
            }
        }

        private void f(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            short s = (short) 0;
            if (i2 == 0) {
                throw i.d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            }
            if ((b & 8) != 0) {
                s = (short) (this.b.j() & 255);
            }
            aVar.a(i2, this.b.l() & Integer.MAX_VALUE, a(i.b(i - 4, b, s), s, b, i2));
        }

        private void g(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            boolean z = true;
            if (i != 8) {
                throw i.d("TYPE_PING length != 8: %s", Integer.valueOf(i));
            } else if (i2 != 0) {
                throw i.d("TYPE_PING streamId != 0", new Object[0]);
            } else {
                int l = this.b.l();
                int l2 = this.b.l();
                if ((b & 1) == 0) {
                    z = false;
                }
                aVar.a(z, l, l2);
            }
        }

        private void h(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            if (i < 8) {
                throw i.d("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            } else if (i2 != 0) {
                throw i.d("TYPE_GOAWAY streamId != 0", new Object[0]);
            } else {
                int l = this.b.l();
                int i3 = i - 8;
                a fromHttp2 = a.fromHttp2(this.b.l());
                if (fromHttp2 == null) {
                    throw i.d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r0));
                }
                f fVar = f.b;
                if (i3 > 0) {
                    fVar = this.b.d((long) i3);
                }
                aVar.a(l, fromHttp2, fVar);
            }
        }

        private void i(dji.thirdparty.b.a.a.b.a aVar, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                throw i.d("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            }
            long l = ((long) this.b.l()) & UpdateOptions.SOURCE_ANY;
            if (l == 0) {
                throw i.d("windowSizeIncrement was 0", Long.valueOf(l));
            } else {
                aVar.a(i2, l);
            }
        }

        public void close() throws IOException {
            this.b.close();
        }
    }

    static final class d implements c {
        private final dji.thirdparty.c.d a;
        private final boolean b;
        private final dji.thirdparty.c.c c = new dji.thirdparty.c.c();
        private final b d = new b(this.c);
        private int e = 16384;
        private boolean f;

        d(dji.thirdparty.c.d dVar, boolean z) {
            this.a = dVar;
            this.b = z;
        }

        public synchronized void b() throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            this.a.flush();
        }

        public synchronized void a(n nVar) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            this.e = nVar.i(this.e);
            a(0, 0, (byte) 4, (byte) 1);
            this.a.flush();
        }

        public synchronized void a() throws IOException {
            if (this.f) {
                throw new IOException("closed");
            } else if (this.b) {
                if (i.t.isLoggable(Level.FINE)) {
                    i.t.fine(String.format(">> CONNECTION %s", new Object[]{i.u.g()}));
                }
                this.a.d(i.u.k());
                this.a.flush();
            }
        }

        public synchronized void a(boolean z, boolean z2, int i, int i2, List<f> list) throws IOException {
            if (z2) {
                throw new UnsupportedOperationException();
            } else if (this.f) {
                throw new IOException("closed");
            } else {
                b(z, i, list);
            }
        }

        public synchronized void a(boolean z, int i, List<f> list) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            b(z, i, list);
        }

        public synchronized void a(int i, List<f> list) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            b(false, i, list);
        }

        public synchronized void a(int i, int i2, List<f> list) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            this.d.a((List) list);
            long b = this.c.b();
            int min = (int) Math.min((long) (this.e - 4), b);
            a(i, min + 4, (byte) 5, b == ((long) min) ? (byte) 4 : (byte) 0);
            this.a.j(Integer.MAX_VALUE & i2);
            this.a.a_(this.c, (long) min);
            if (b > ((long) min)) {
                b(i, b - ((long) min));
            }
        }

        void b(boolean z, int i, List<f> list) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            this.d.a((List) list);
            long b = this.c.b();
            int min = (int) Math.min((long) this.e, b);
            byte b2 = b == ((long) min) ? (byte) 4 : (byte) 0;
            if (z) {
                b2 = (byte) (b2 | 1);
            }
            a(i, min, (byte) 1, b2);
            this.a.a_(this.c, (long) min);
            if (b > ((long) min)) {
                b(i, b - ((long) min));
            }
        }

        private void b(int i, long j) throws IOException {
            while (j > 0) {
                int min = (int) Math.min((long) this.e, j);
                j -= (long) min;
                a(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
                this.a.a_(this.c, (long) min);
            }
        }

        public synchronized void a(int i, a aVar) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            } else if (aVar.s == -1) {
                throw new IllegalArgumentException();
            } else {
                a(i, 4, (byte) 3, (byte) 0);
                this.a.j(aVar.s);
                this.a.flush();
            }
        }

        public int c() {
            return this.e;
        }

        public synchronized void a(boolean z, int i, dji.thirdparty.c.c cVar, int i2) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            }
            byte b = (byte) 0;
            if (z) {
                b = (byte) 1;
            }
            a(i, b, cVar, i2);
        }

        void a(int i, byte b, dji.thirdparty.c.c cVar, int i2) throws IOException {
            a(i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.a.a_(cVar, (long) i2);
            }
        }

        public synchronized void b(n nVar) throws IOException {
            synchronized (this) {
                if (this.f) {
                    throw new IOException("closed");
                }
                a(0, nVar.b() * 6, (byte) 4, (byte) 0);
                for (int i = 0; i < 10; i++) {
                    if (nVar.a(i)) {
                        int i2;
                        if (i == 4) {
                            i2 = 3;
                        } else if (i == 7) {
                            i2 = 4;
                        } else {
                            i2 = i;
                        }
                        this.a.l(i2);
                        this.a.j(nVar.b(i));
                    }
                }
                this.a.flush();
            }
        }

        public synchronized void a(boolean z, int i, int i2) throws IOException {
            byte b = (byte) 0;
            synchronized (this) {
                if (this.f) {
                    throw new IOException("closed");
                }
                if (z) {
                    b = (byte) 1;
                }
                a(0, 8, (byte) 6, b);
                this.a.j(i);
                this.a.j(i2);
                this.a.flush();
            }
        }

        public synchronized void a(int i, a aVar, byte[] bArr) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            } else if (aVar.s == -1) {
                throw i.c("errorCode.httpCode == -1", new Object[0]);
            } else {
                a(0, bArr.length + 8, (byte) 7, (byte) 0);
                this.a.j(i);
                this.a.j(aVar.s);
                if (bArr.length > 0) {
                    this.a.d(bArr);
                }
                this.a.flush();
            }
        }

        public synchronized void a(int i, long j) throws IOException {
            if (this.f) {
                throw new IOException("closed");
            } else if (j == 0 || j > UpdateOptions.SOURCE_ANY) {
                throw i.c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            } else {
                a(i, 4, (byte) 8, (byte) 0);
                this.a.j((int) j);
                this.a.flush();
            }
        }

        public synchronized void close() throws IOException {
            this.f = true;
            this.a.close();
        }

        void a(int i, int i2, byte b, byte b2) throws IOException {
            if (i.t.isLoggable(Level.FINE)) {
                i.t.fine(b.a(false, i, i2, b, b2));
            }
            if (i2 > this.e) {
                throw i.c("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.e), Integer.valueOf(i2));
            } else if ((Integer.MIN_VALUE & i) != 0) {
                throw i.c("reserved bit set: %s", Integer.valueOf(i));
            } else {
                i.b(this.a, i2);
                this.a.m(b & 255);
                this.a.m(b2 & 255);
                this.a.j(Integer.MAX_VALUE & i);
            }
        }
    }

    public z a() {
        return z.HTTP_2;
    }

    public b a(e eVar, boolean z) {
        return new c(eVar, 4096, z);
    }

    public c a(dji.thirdparty.c.d dVar, boolean z) {
        return new d(dVar, z);
    }

    private static IllegalArgumentException c(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    private static IOException d(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(str, objArr));
    }

    private static int b(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            short s2 = i - 1;
        }
        if (s <= s2) {
            return (short) (s2 - s);
        }
        throw d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(s2));
    }

    private static int b(e eVar) throws IOException {
        return (((eVar.j() & 255) << 16) | ((eVar.j() & 255) << 8)) | (eVar.j() & 255);
    }

    private static void b(dji.thirdparty.c.d dVar, int i) throws IOException {
        dVar.m((i >>> 16) & 255);
        dVar.m((i >>> 8) & 255);
        dVar.m(i & 255);
    }
}
