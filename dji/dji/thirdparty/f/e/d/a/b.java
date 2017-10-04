package dji.thirdparty.f.e.d.a;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

abstract class b<E> extends AbstractQueue<E> {
    private final AtomicReference<c<E>> a = new AtomicReference();
    private final AtomicReference<c<E>> b = new AtomicReference();

    protected final c<E> a() {
        return (c) this.a.get();
    }

    protected final c<E> b() {
        return (c) this.a.get();
    }

    protected final void a(c<E> cVar) {
        this.a.lazySet(cVar);
    }

    protected final c<E> b(c<E> cVar) {
        return (c) this.a.getAndSet(cVar);
    }

    protected final c<E> c() {
        return (c) this.b.get();
    }

    protected final c<E> d() {
        return (c) this.b.get();
    }

    protected final void c(c<E> cVar) {
        this.b.lazySet(cVar);
    }

    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        c c = c();
        c a = a();
        int i = 0;
        while (c != a && i < Integer.MAX_VALUE) {
            c c2;
            do {
                c2 = c.c();
            } while (c2 == null);
            i++;
            c = c2;
        }
        return i;
    }

    public final boolean isEmpty() {
        return c() == a();
    }
}
