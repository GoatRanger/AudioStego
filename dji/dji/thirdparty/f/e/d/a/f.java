package dji.thirdparty.f.e.d.a;

import dji.thirdparty.f.e.d.b.p;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class f<T> extends AtomicReferenceArray<T> implements Queue<T> {
    static final AtomicLongFieldUpdater<f> e = AtomicLongFieldUpdater.newUpdater(f.class, "c");
    static final AtomicLongFieldUpdater<f> f = AtomicLongFieldUpdater.newUpdater(f.class, "d");
    private static final long g = 6210984603741293445L;
    final int a;
    final int b;
    volatile long c;
    volatile long d;

    public f(int i) {
        super(p.a(i));
        int length = length();
        this.a = length - 1;
        this.b = length - i;
    }

    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        long j = this.c;
        int i = this.a;
        if (get(((int) (((long) this.b) + j)) & i) != null) {
            return false;
        }
        i &= (int) j;
        e.lazySet(this, j + 1);
        lazySet(i, t);
        return true;
    }

    public T poll() {
        long j = this.d;
        int i = this.a & ((int) j);
        T t = get(i);
        if (t == null) {
            return null;
        }
        f.lazySet(this, j + 1);
        lazySet(i, null);
        return t;
    }

    public T peek() {
        return get(((int) this.d) & this.a);
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public boolean isEmpty() {
        return this.c == this.d;
    }

    public int size() {
        long j = this.d;
        while (true) {
            long j2 = this.c;
            long j3 = this.d;
            if (j == j3) {
                return (int) (j2 - j3);
            }
            j = j3;
        }
    }

    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
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
