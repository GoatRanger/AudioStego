package dji.pilot2.a;

import dji.pilot2.b.b;
import dji.pilot2.utils.o;

public abstract class a extends b {
    private int a = 0;
    private int b = 0;
    private long c = 0;
    private c d;
    private e e;

    public abstract void a();

    public void finalize() {
        o.b("taskRun2 " + String.format("Task [%d] finalize!", new Object[]{Long.valueOf(e())}));
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public c d() {
        return this.d;
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    public void a(e eVar) {
        this.e = eVar;
    }

    public long e() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public final void run() {
        if (this.e != null) {
            this.e.a(this);
        }
        a();
        if (this.e != null) {
            this.e.b(this);
        }
    }

    public void a(int i, int i2) {
        if (this.e != null) {
            this.e.a(this, i, i2);
        }
    }
}
