package dji.thirdparty.f.e.d.b;

import dji.thirdparty.f.e.d.a.c;

public final class ag<E> extends a<E> {
    public ag() {
        b(new c());
        a(this.Z);
        this.r.a(null);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        c cVar = new c(e);
        this.Z.a(cVar);
        this.Z = cVar;
        return true;
    }

    public E poll() {
        c c = this.r.c();
        if (c == null) {
            return null;
        }
        E a = c.a();
        this.r = c;
        return a;
    }

    public E peek() {
        c c = this.r.c();
        if (c != null) {
            return c.b();
        }
        return null;
    }
}
