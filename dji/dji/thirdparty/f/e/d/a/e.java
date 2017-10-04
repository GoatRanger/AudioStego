package dji.thirdparty.f.e.d.a;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class e<E> extends a<E> {
    private static final Integer g = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    final AtomicLong c = new AtomicLong();
    protected long d;
    final AtomicLong e = new AtomicLong();
    final int f;

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public e(int i) {
        super(i);
        this.f = Math.min(i / 4, g.intValue());
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray atomicReferenceArray = this.a;
        int i = this.b;
        long j = this.c.get();
        int a = a(j, i);
        if (j >= this.d) {
            int i2 = this.f;
            if (a(atomicReferenceArray, a(((long) i2) + j, i)) == null) {
                this.d = ((long) i2) + j;
            } else if (a(atomicReferenceArray, a) != null) {
                return false;
            }
        }
        b(j + 1);
        b(atomicReferenceArray, a, e);
        return true;
    }

    public E poll() {
        long j = this.e.get();
        int a = a(j);
        AtomicReferenceArray atomicReferenceArray = this.a;
        E a2 = a(atomicReferenceArray, a);
        if (a2 == null) {
            return null;
        }
        c(j + 1);
        b(atomicReferenceArray, a, null);
        return a2;
    }

    public E peek() {
        return b(a(this.e.get()));
    }

    public int size() {
        long a = a();
        while (true) {
            long b = b();
            long a2 = a();
            if (a == a2) {
                return (int) (b - a2);
            }
            a = a2;
        }
    }

    public boolean isEmpty() {
        return b() == a();
    }

    private void b(long j) {
        this.c.lazySet(j);
    }

    private void c(long j) {
        this.e.lazySet(j);
    }

    private long a() {
        return this.e.get();
    }

    private long b() {
        return this.c.get();
    }
}
