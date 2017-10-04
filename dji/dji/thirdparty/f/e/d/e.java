package dji.thirdparty.f.e.d;

import dji.thirdparty.f.d.o;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class e<E> implements l {
    static int c;
    static final int d = c;
    private static final g<e<?>> e = new g<e<?>>() {
        protected /* synthetic */ Object b() {
            return a();
        }

        protected e<?> a() {
            return new e();
        }
    };
    final AtomicInteger a = new AtomicInteger();
    final AtomicInteger b = new AtomicInteger();
    private final a<E> f = new a();
    private final b g = new b();

    private static class a<E> {
        final AtomicReferenceArray<E> a = new AtomicReferenceArray(e.d);
        final AtomicReference<a<E>> b = new AtomicReference();

        a() {
        }

        a<E> a() {
            if (this.b.get() != null) {
                return (a) this.b.get();
            }
            a<E> aVar = new a();
            return !this.b.compareAndSet(null, aVar) ? (a) this.b.get() : aVar;
        }
    }

    private static class b {
        private final AtomicIntegerArray a = new AtomicIntegerArray(e.d);
        private final AtomicReference<b> b = new AtomicReference();

        b() {
        }

        public int a(int i, int i2) {
            return this.a.getAndSet(i, i2);
        }

        public void b(int i, int i2) {
            this.a.set(i, i2);
        }

        b a() {
            if (this.b.get() != null) {
                return (b) this.b.get();
            }
            b bVar = new b();
            return !this.b.compareAndSet(null, bVar) ? (b) this.b.get() : bVar;
        }
    }

    static {
        c = 256;
        if (h.a()) {
            c = 8;
        }
        String property = System.getProperty("rx.indexed-ring-buffer.size");
        if (property != null) {
            try {
                c = Integer.parseInt(property);
            } catch (Exception e) {
                System.err.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
    }

    public static <T> e<T> getInstance() {
        return (e) e.e();
    }

    public void c() {
        int i = this.a.get();
        a aVar = this.f;
        int i2 = 0;
        loop0:
        while (aVar != null) {
            int i3 = i2;
            i2 = 0;
            while (i2 < d) {
                if (i3 >= i) {
                    break loop0;
                }
                aVar.a.set(i2, null);
                i2++;
                i3++;
            }
            aVar = (a) aVar.b.get();
            i2 = i3;
        }
        this.a.set(0);
        this.b.set(0);
        e.a((Object) this);
    }

    public void n_() {
        c();
    }

    e() {
    }

    public int a(E e) {
        int d = d();
        if (d < d) {
            this.f.a.set(d, e);
        } else {
            c(d).a.set(d % d, e);
        }
        return d;
    }

    public E a(int i) {
        E andSet;
        if (i < d) {
            andSet = this.f.a.getAndSet(i, null);
        } else {
            andSet = c(i).a.getAndSet(i % d, null);
        }
        d(i);
        return andSet;
    }

    private b b(int i) {
        if (i < d) {
            return this.g;
        }
        int i2 = i / d;
        b bVar = this.g;
        int i3 = 0;
        while (i3 < i2) {
            i3++;
            bVar = bVar.a();
        }
        return bVar;
    }

    private a<E> c(int i) {
        if (i < d) {
            return this.f;
        }
        int i2 = i / d;
        a<E> aVar = this.f;
        int i3 = 0;
        while (i3 < i2) {
            i3++;
            aVar = aVar.a();
        }
        return aVar;
    }

    private synchronized int d() {
        int e;
        e = e();
        if (e >= 0) {
            if (e < d) {
                e = this.g.a(e, -1);
            } else {
                e = b(e).a(e % d, -1);
            }
            if (e == this.a.get()) {
                this.a.getAndIncrement();
            }
        } else {
            e = this.a.getAndIncrement();
        }
        return e;
    }

    private synchronized int e() {
        int i;
        do {
            i = this.b.get();
            if (i <= 0) {
                i = -1;
                break;
            }
        } while (!this.b.compareAndSet(i, i - 1));
        i--;
        return i;
    }

    private synchronized void d(int i) {
        int andIncrement = this.b.getAndIncrement();
        if (andIncrement < d) {
            this.g.b(andIncrement, i);
        } else {
            b(andIncrement).b(andIncrement % d, i);
        }
    }

    public boolean b() {
        return false;
    }

    public int a(o<? super E, Boolean> oVar) {
        return a(oVar, 0);
    }

    public int a(o<? super E, Boolean> oVar, int i) {
        int a = a(oVar, i, this.a.get());
        if (i > 0 && a == this.a.get()) {
            return a(oVar, 0, i);
        }
        if (a != this.a.get()) {
            return a;
        }
        return 0;
    }

    private int a(o<? super E, Boolean> oVar, int i, int i2) {
        a c;
        int i3;
        int i4;
        int i5 = this.a.get();
        a aVar = this.f;
        if (i >= d) {
            c = c(i);
            i3 = i % d;
            i4 = i;
        } else {
            c = aVar;
            i4 = i;
            i3 = i;
        }
        while (c != null) {
            int i6 = i4;
            for (i4 = i3; i4 < d; i4++) {
                if (i6 >= i5 || i6 >= i2) {
                    return i6;
                }
                Object obj = c.a.get(i4);
                if (obj != null && !((Boolean) oVar.a(obj)).booleanValue()) {
                    return i6;
                }
                i6++;
            }
            c = (a) c.b.get();
            i4 = i6;
            i3 = 0;
        }
        return i4;
    }
}
