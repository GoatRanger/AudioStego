package dji.thirdparty.f.e.d.b;

import java.util.Iterator;

public abstract class f<E> extends g<E> {
    protected static final int a = Integer.getInteger("sparse.shift", 0).intValue();
    protected static final int b = 32;
    private static final long u = ((long) (an.a.arrayBaseOffset(Object[].class) + (32 << (v - a))));
    private static final int v;
    protected final long c;
    protected final E[] d;

    static {
        int arrayIndexScale = an.a.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            v = a + 2;
        } else if (8 == arrayIndexScale) {
            v = a + 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
    }

    public f(int i) {
        int a = p.a(i);
        this.c = (long) (a - 1);
        this.d = new Object[((a << a) + 64)];
    }

    protected final long a(long j) {
        return a(j, this.c);
    }

    protected final long a(long j, long j2) {
        return u + ((j & j2) << v);
    }

    protected final void a(long j, E e) {
        a(this.d, j, e);
    }

    protected final void a(E[] eArr, long j, E e) {
        an.a.putObject(eArr, j, e);
    }

    protected final void b(long j, E e) {
        b(this.d, j, e);
    }

    protected final void b(E[] eArr, long j, E e) {
        an.a.putOrderedObject(eArr, j, e);
    }

    protected final E b(long j) {
        return a(this.d, j);
    }

    protected final E a(E[] eArr, long j) {
        return an.a.getObject(eArr, j);
    }

    protected final E c(long j) {
        return b(this.d, j);
    }

    protected final E b(E[] eArr, long j) {
        return an.a.getObjectVolatile(eArr, j);
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
}
