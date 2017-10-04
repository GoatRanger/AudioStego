package dji.thirdparty.f.e.d.b;

import dji.thirdparty.f.e.d.a.c;

abstract class e<E> extends c<E> {
    protected static final long Y = an.a(e.class, "producerNode");
    protected c<E> Z;

    e() {
    }

    protected final void b(c<E> cVar) {
        this.Z = cVar;
    }

    protected final c<E> c() {
        return (c) an.a.getObjectVolatile(this, Y);
    }

    protected final c<E> d() {
        return this.Z;
    }
}
