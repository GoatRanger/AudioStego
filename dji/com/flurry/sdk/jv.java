package com.flurry.sdk;

public class jv {
    private static final String a = jv.class.getSimpleName();
    private long b = 1000;
    private boolean c = true;
    private boolean d = false;
    private kb e = new kb(this) {
        final /* synthetic */ jv a;

        {
            this.a = r1;
        }

        public void a() {
            new jt().b();
            if (this.a.c && this.a.d) {
                hz.a().b(this.a.e, this.a.b);
            }
        }
    };

    public void a(long j) {
        this.b = j;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public synchronized void a() {
        if (!this.d) {
            hz.a().b(this.e, this.b);
            this.d = true;
        }
    }

    public synchronized void b() {
        if (this.d) {
            hz.a().c(this.e);
            this.d = false;
        }
    }
}
