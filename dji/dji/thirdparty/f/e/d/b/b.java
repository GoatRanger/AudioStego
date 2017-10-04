package dji.thirdparty.f.e.d.b;

import dji.thirdparty.f.e.d.a.c;

abstract class b<E> extends d<E> {
    protected static final long q = an.a(b.class, "consumerNode");
    protected c<E> r;

    b() {
    }

    protected final void a(c<E> cVar) {
        this.r = cVar;
    }

    protected final c<E> a() {
        return (c) an.a.getObjectVolatile(this, q);
    }

    protected final c<E> b() {
        return this.r;
    }
}
