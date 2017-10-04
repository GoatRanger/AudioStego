package dji.thirdparty.c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class l implements w {
    private static final byte a = (byte) 1;
    private static final byte b = (byte) 2;
    private static final byte c = (byte) 3;
    private static final byte d = (byte) 4;
    private static final byte e = (byte) 0;
    private static final byte f = (byte) 1;
    private static final byte g = (byte) 2;
    private static final byte h = (byte) 3;
    private int i = 0;
    private final e j;
    private final Inflater k;
    private final o l;
    private final CRC32 m = new CRC32();

    public l(w wVar) {
        if (wVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.k = new Inflater(true);
        this.j = p.a(wVar);
        this.l = new o(this.j, this.k);
    }

    public long a(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0;
        } else {
            if (this.i == 0) {
                b();
                this.i = 1;
            }
            if (this.i == 1) {
                long j2 = cVar.c;
                long a = this.l.a(cVar, j);
                if (a != -1) {
                    a(cVar, j2, a);
                    return a;
                }
                this.i = 2;
            }
            if (this.i == 2) {
                c();
                this.i = 3;
                if (!this.j.g()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void b() throws IOException {
        Object obj;
        long a;
        this.j.a(10);
        byte c = this.j.c().c(3);
        if (((c >> 1) & 1) == 1) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(this.j.c(), 0, 10);
        }
        a("ID1ID2", 8075, this.j.k());
        this.j.h(8);
        if (((c >> 2) & 1) == 1) {
            this.j.a(2);
            if (obj != null) {
                a(this.j.c(), 0, 2);
            }
            short n = this.j.c().n();
            this.j.a((long) n);
            if (obj != null) {
                a(this.j.c(), 0, (long) n);
            }
            this.j.h((long) n);
        }
        if (((c >> 3) & 1) == 1) {
            a = this.j.a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                a(this.j.c(), 0, 1 + a);
            }
            this.j.h(1 + a);
        }
        if (((c >> 4) & 1) == 1) {
            a = this.j.a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                a(this.j.c(), 0, 1 + a);
            }
            this.j.h(1 + a);
        }
        if (obj != null) {
            a("FHCRC", this.j.n(), (short) ((int) this.m.getValue()));
            this.m.reset();
        }
    }

    private void c() throws IOException {
        a("CRC", this.j.o(), (int) this.m.getValue());
        a("ISIZE", this.j.o(), this.k.getTotalOut());
    }

    public x a() {
        return this.j.a();
    }

    public void close() throws IOException {
        this.l.close();
    }

    private void a(c cVar, long j, long j2) {
        s sVar = cVar.b;
        while (j >= ((long) (sVar.e - sVar.d))) {
            j -= (long) (sVar.e - sVar.d);
            sVar = sVar.h;
        }
        while (j2 > 0) {
            int i = (int) (((long) sVar.d) + j);
            int min = (int) Math.min((long) (sVar.e - i), j2);
            this.m.update(sVar.c, i, min);
            j2 -= (long) min;
            sVar = sVar.h;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
