package dji.thirdparty.f.m;

import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class d implements l {
    static final b a = new b(false, 0);
    final AtomicReference<b> b = new AtomicReference(a);
    private final l c;

    private static final class a extends AtomicInteger implements l {
        final d a;

        public a(d dVar) {
            this.a = dVar;
        }

        public void n_() {
            if (compareAndSet(0, 1)) {
                this.a.d();
            }
        }

        public boolean b() {
            return get() != 0;
        }
    }

    private static final class b {
        final boolean a;
        final int b;

        b(boolean z, int i) {
            this.a = z;
            this.b = i;
        }

        b a() {
            return new b(this.a, this.b + 1);
        }

        b b() {
            return new b(this.a, this.b - 1);
        }

        b c() {
            return new b(true, this.b);
        }
    }

    public d(l lVar) {
        if (lVar == null) {
            throw new IllegalArgumentException("s");
        }
        this.c = lVar;
    }

    public l c() {
        AtomicReference atomicReference = this.b;
        b bVar;
        do {
            bVar = (b) atomicReference.get();
            if (bVar.a) {
                return f.b();
            }
        } while (!atomicReference.compareAndSet(bVar, bVar.a()));
        return new a(this);
    }

    public boolean b() {
        return ((b) this.b.get()).a;
    }

    public void n_() {
        b c;
        AtomicReference atomicReference = this.b;
        b bVar;
        do {
            bVar = (b) atomicReference.get();
            if (!bVar.a) {
                c = bVar.c();
            } else {
                return;
            }
        } while (!atomicReference.compareAndSet(bVar, c));
        a(c);
    }

    private void a(b bVar) {
        if (bVar.a && bVar.b == 0) {
            this.c.n_();
        }
    }

    void d() {
        b b;
        AtomicReference atomicReference = this.b;
        b bVar;
        do {
            bVar = (b) atomicReference.get();
            b = bVar.b();
        } while (!atomicReference.compareAndSet(bVar, b));
        a(b);
    }
}
