package dji.thirdparty.f.e.d.b;

import java.util.Iterator;

public class ah<E> extends aj<E> implements q {
    private static final Object A = new Object();
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final long w;
    private static final long x;
    private static final long y = ((long) an.a.arrayBaseOffset(Object[].class));
    private static final int z;

    static {
        int arrayIndexScale = an.a.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            z = 2;
        } else if (8 == arrayIndexScale) {
            z = 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        try {
            w = an.a.objectFieldOffset(am.class.getDeclaredField("v"));
            try {
                x = an.a.objectFieldOffset(aj.class.getDeclaredField("d"));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    public ah(int i) {
        int a = p.a(i);
        long j = (long) (a - 1);
        Object[] objArr = new Object[(a + 1)];
        this.u = objArr;
        this.t = j;
        a(a);
        this.c = objArr;
        this.b = j;
        this.s = j - 1;
        a(0);
    }

    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        Object[] objArr = this.u;
        long j = this.v;
        long j2 = this.t;
        long a = a(j, j2);
        if (j < this.s) {
            return a(objArr, e, j, a);
        }
        int i = this.r;
        if (a(objArr, a(((long) i) + j, j2)) == null) {
            this.s = (((long) i) + j) - 1;
            return a(objArr, e, j, a);
        } else if (a(objArr, a(1 + j, j2)) != null) {
            return a(objArr, e, j, a);
        } else {
            a(objArr, j, a, e, j2);
            return true;
        }
    }

    private boolean a(E[] eArr, E e, long j, long j2) {
        a(1 + j);
        a((Object[]) eArr, j2, (Object) e);
        return true;
    }

    private void a(E[] eArr, long j, long j2, E e, long j3) {
        Object[] objArr = new Object[eArr.length];
        this.u = objArr;
        this.s = (j + j3) - 1;
        a(j + 1);
        a(objArr, j2, (Object) e);
        a((Object[]) eArr, objArr);
        a((Object[]) eArr, j2, A);
    }

    private void a(E[] eArr, E[] eArr2) {
        a((Object[]) eArr, c((long) (eArr.length - 1)), (Object) eArr2);
    }

    private E[] a(E[] eArr) {
        return (Object[]) a((Object[]) eArr, c((long) (eArr.length - 1)));
    }

    public final E poll() {
        Object[] objArr = this.c;
        long j = this.d;
        long j2 = this.b;
        long a = a(j, j2);
        E a2 = a(objArr, a);
        Object obj = a2 == A ? 1 : null;
        if (a2 == null || obj != null) {
            return obj != null ? a(a(objArr), j, j2) : null;
        } else {
            b(j + 1);
            a(objArr, a, null);
            return a2;
        }
    }

    private E a(E[] eArr, long j, long j2) {
        this.c = eArr;
        long a = a(j, j2);
        E a2 = a((Object[]) eArr, a);
        if (a2 == null) {
            return null;
        }
        b(1 + j);
        a((Object[]) eArr, a, null);
        return a2;
    }

    public final E peek() {
        Object[] objArr = this.c;
        long j = this.d;
        long j2 = this.b;
        E a = a(objArr, a(j, j2));
        if (a == A) {
            return b(a(objArr), j, j2);
        }
        return a;
    }

    private E b(E[] eArr, long j, long j2) {
        this.c = eArr;
        return a((Object[]) eArr, a(j, j2));
    }

    public final int size() {
        long d = d();
        while (true) {
            long c = c();
            long d2 = d();
            if (d == d2) {
                return (int) (c - d2);
            }
            d = d2;
        }
    }

    private void a(int i) {
        this.r = Math.min(i / 4, a);
    }

    private long c() {
        return an.a.getLongVolatile(this, w);
    }

    private long d() {
        return an.a.getLongVolatile(this, x);
    }

    private void a(long j) {
        an.a.putOrderedLong(this, w, j);
    }

    private void b(long j) {
        an.a.putOrderedLong(this, x, j);
    }

    private static long a(long j, long j2) {
        return c(j & j2);
    }

    private static long c(long j) {
        return y + (j << z);
    }

    private static void a(Object[] objArr, long j, Object obj) {
        an.a.putOrderedObject(objArr, j, obj);
    }

    private static <E> Object a(E[] eArr, long j) {
        return an.a.getObjectVolatile(eArr, j);
    }

    public long a() {
        return c();
    }

    public long b() {
        return d();
    }
}
