package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequest.b;
import com.facebook.GraphRequest.f;

class af {
    private final GraphRequest a;
    private final Handler b;
    private final long c = o.j();
    private long d;
    private long e;
    private long f;

    af(Handler handler, GraphRequest graphRequest) {
        this.a = graphRequest;
        this.b = handler;
    }

    long a() {
        return this.d;
    }

    long b() {
        return this.f;
    }

    void a(long j) {
        this.d += j;
        if (this.d >= this.e + this.c || this.d >= this.f) {
            c();
        }
    }

    void b(long j) {
        this.f += j;
    }

    void c() {
        if (this.d > this.e) {
            b k = this.a.k();
            if (this.f > 0 && (k instanceof f)) {
                final long j = this.d;
                final long j2 = this.f;
                final f fVar = (f) k;
                if (this.b == null) {
                    fVar.a(j, j2);
                } else {
                    this.b.post(new Runnable(this) {
                        final /* synthetic */ af d;

                        public void run() {
                            fVar.a(j, j2);
                        }
                    });
                }
                this.e = this.d;
            }
        }
    }
}
