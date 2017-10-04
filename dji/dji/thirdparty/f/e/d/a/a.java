package dji.thirdparty.f.e.d.a;

import dji.thirdparty.f.e.d.b.p;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;

abstract class a<E> extends AbstractQueue<E> {
    protected final AtomicReferenceArray<E> a;
    protected final int b;

    public a(int i) {
        int a = p.a(i);
        this.b = a - 1;
        this.a = new AtomicReferenceArray(a);
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

    protected final int a(long j, int i) {
        return ((int) j) & i;
    }

    protected final int a(long j) {
        return ((int) j) & this.b;
    }

    protected final E a(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    protected final E b(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    protected final E a(int i) {
        return this.a.get(i);
    }

    protected final void a(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.lazySet(i, e);
    }

    protected final void a(int i, E e) {
        this.a.lazySet(i, e);
    }

    protected final void b(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.lazySet(i, e);
    }

    protected final void b(int i, E e) {
        this.a.lazySet(i, e);
    }

    protected final void c(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.set(i, e);
    }

    protected final E b(int i) {
        return a(this.a, i);
    }
}
