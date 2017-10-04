package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

class ac extends OutputStream implements ae {
    private final Map<GraphRequest, af> a = new HashMap();
    private final Handler b;
    private GraphRequest c;
    private af d;
    private int e;

    ac(Handler handler) {
        this.b = handler;
    }

    public void a(GraphRequest graphRequest) {
        this.c = graphRequest;
        this.d = graphRequest != null ? (af) this.a.get(graphRequest) : null;
    }

    int a() {
        return this.e;
    }

    Map<GraphRequest, af> b() {
        return this.a;
    }

    void a(long j) {
        if (this.d == null) {
            this.d = new af(this.b, this.c);
            this.a.put(this.c, this.d);
        }
        this.d.b(j);
        this.e = (int) (((long) this.e) + j);
    }

    public void write(byte[] bArr) {
        a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        a((long) i2);
    }

    public void write(int i) {
        a(1);
    }
}
