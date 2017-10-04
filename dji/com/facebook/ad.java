package com.facebook;

import android.os.Handler;
import com.facebook.u.a;
import com.facebook.u.b;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

class ad extends FilterOutputStream implements ae {
    private final Map<GraphRequest, af> a;
    private final u b;
    private final long c = o.j();
    private long d;
    private long e;
    private long f;
    private af g;

    ad(OutputStream outputStream, u uVar, Map<GraphRequest, af> map, long j) {
        super(outputStream);
        this.b = uVar;
        this.a = map;
        this.f = j;
    }

    private void a(long j) {
        if (this.g != null) {
            this.g.a(j);
        }
        this.d += j;
        if (this.d >= this.e + this.c || this.d >= this.f) {
            c();
        }
    }

    private void c() {
        if (this.d > this.e) {
            for (a aVar : this.b.e()) {
                if (aVar instanceof b) {
                    Handler c = this.b.c();
                    final b bVar = (b) aVar;
                    if (c == null) {
                        bVar.a(this.b, this.d, this.f);
                    } else {
                        c.post(new Runnable(this) {
                            final /* synthetic */ ad b;

                            public void run() {
                                bVar.a(this.b.b, this.b.d, this.b.f);
                            }
                        });
                    }
                }
            }
            this.e = this.d;
        }
    }

    public void a(GraphRequest graphRequest) {
        this.g = graphRequest != null ? (af) this.a.get(graphRequest) : null;
    }

    long a() {
        return this.d;
    }

    long b() {
        return this.f;
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        a((long) i2);
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        a(1);
    }

    public void close() throws IOException {
        super.close();
        for (af c : this.a.values()) {
            c.c();
        }
        c();
    }
}
