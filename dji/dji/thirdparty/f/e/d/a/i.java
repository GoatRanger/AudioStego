package dji.thirdparty.f.e.d.a;

import dji.thirdparty.f.e.d.b.p;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class i<T> implements Queue<T> {
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    static final AtomicLongFieldUpdater<i> c = AtomicLongFieldUpdater.newUpdater(i.class, "b");
    static final AtomicLongFieldUpdater<i> k = AtomicLongFieldUpdater.newUpdater(i.class, "j");
    private static final Object l = new Object();
    protected volatile long b;
    protected int d;
    protected long e;
    protected int f;
    protected AtomicReferenceArray<Object> g;
    protected int h;
    protected AtomicReferenceArray<Object> i;
    protected volatile long j;

    public i(int i) {
        int a = p.a(Math.max(8, i));
        int i2 = a - 1;
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(a + 1);
        this.g = atomicReferenceArray;
        this.f = i2;
        a(a);
        this.i = atomicReferenceArray;
        this.h = i2;
        this.e = (long) (i2 - 1);
        a(0);
    }

    public final boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        AtomicReferenceArray atomicReferenceArray = this.g;
        long c = c();
        int i = this.f;
        int a = a(c, i);
        if (c < this.e) {
            return a(atomicReferenceArray, t, c, a);
        }
        int i2 = this.d;
        if (a(atomicReferenceArray, a(((long) i2) + c, i)) == null) {
            this.e = (((long) i2) + c) - 1;
            return a(atomicReferenceArray, t, c, a);
        } else if (a(atomicReferenceArray, a(1 + c, i)) != null) {
            return a(atomicReferenceArray, t, c, a);
        } else {
            a(atomicReferenceArray, c, a, t, (long) i);
            return true;
        }
    }

    private boolean a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        a(1 + j);
        a((AtomicReferenceArray) atomicReferenceArray, i, (Object) t);
        return true;
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i, T t, long j2) {
        AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(atomicReferenceArray.length());
        this.g = atomicReferenceArray2;
        this.e = (j + j2) - 1;
        a(j + 1);
        a(atomicReferenceArray2, i, (Object) t);
        a((AtomicReferenceArray) atomicReferenceArray, atomicReferenceArray2);
        a((AtomicReferenceArray) atomicReferenceArray, i, l);
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        a((AtomicReferenceArray) atomicReferenceArray, b(atomicReferenceArray.length() - 1), (Object) atomicReferenceArray2);
    }

    private AtomicReferenceArray<Object> a(AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray) a((AtomicReferenceArray) atomicReferenceArray, b(atomicReferenceArray.length() - 1));
    }

    public final T poll() {
        AtomicReferenceArray atomicReferenceArray = this.i;
        long d = d();
        int i = this.h;
        int a = a(d, i);
        T a2 = a(atomicReferenceArray, a);
        Object obj = a2 == l ? 1 : null;
        if (a2 == null || obj != null) {
            return obj != null ? a(a(atomicReferenceArray), d, i) : null;
        } else {
            b(d + 1);
            a(atomicReferenceArray, a, null);
            return a2;
        }
    }

    private T a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.i = atomicReferenceArray;
        int a = a(j, i);
        T a2 = a((AtomicReferenceArray) atomicReferenceArray, a);
        if (a2 == null) {
            return null;
        }
        b(1 + j);
        a((AtomicReferenceArray) atomicReferenceArray, a, null);
        return a2;
    }

    public final T peek() {
        AtomicReferenceArray atomicReferenceArray = this.i;
        long d = d();
        int i = this.h;
        T a = a(atomicReferenceArray, a(d, i));
        if (a == l) {
            return b(a(atomicReferenceArray), d, i);
        }
        return a;
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    private T b(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.i = atomicReferenceArray;
        return a((AtomicReferenceArray) atomicReferenceArray, a(j, i));
    }

    public final int size() {
        long b = b();
        while (true) {
            long a = a();
            long b2 = b();
            if (b == b2) {
                return (int) (a - b2);
            }
            b = b2;
        }
    }

    public boolean isEmpty() {
        return a() == b();
    }

    private void a(int i) {
        this.d = Math.min(i / 4, a);
    }

    private long a() {
        return this.b;
    }

    private long b() {
        return this.j;
    }

    private long c() {
        return this.b;
    }

    private long d() {
        return this.j;
    }

    private void a(long j) {
        c.lazySet(this, j);
    }

    private void b(long j) {
        k.lazySet(this, j);
    }

    private static int a(long j, int i) {
        return b(((int) j) & i);
    }

    private static int b(int i) {
        return i;
    }

    private static void a(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    private static <E> Object a(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    public final Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public T remove() {
        throw new UnsupportedOperationException();
    }

    public T element() {
        throw new UnsupportedOperationException();
    }
}
