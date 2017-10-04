package dji.thirdparty.f.e.d.a;

import java.util.concurrent.atomic.AtomicReference;

public final class c<E> extends AtomicReference<c<E>> {
    private static final long a = 2404266111789071508L;
    private E b;

    public c(E e) {
        a((Object) e);
    }

    public E a() {
        E b = b();
        a(null);
        return b;
    }

    public E b() {
        return this.b;
    }

    public void a(E e) {
        this.b = e;
    }

    public void a(c<E> cVar) {
        lazySet(cVar);
    }

    public c<E> c() {
        return (c) get();
    }
}
