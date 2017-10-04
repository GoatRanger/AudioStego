package dji.thirdparty.f.e.d.b;

import dji.thirdparty.f.e.d.a.c;

public final class o<E> extends a<E> {
    public o() {
        this.r = new c();
        c(this.r);
    }

    protected final c<E> c(c<E> cVar) {
        c cVar2;
        do {
            cVar2 = this.Z;
        } while (!an.a.compareAndSwapObject(this, Y, cVar2, cVar));
        return cVar2;
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        c cVar = new c(e);
        c(cVar).a(cVar);
        return true;
    }

    public final E poll() {
        c b = b();
        c c = b.c();
        E a;
        if (c != null) {
            a = c.a();
            a(c);
            return a;
        } else if (b == c()) {
            return null;
        } else {
            do {
                c = b.c();
            } while (c == null);
            a = c.a();
            this.r = c;
            return a;
        }
    }

    public final E peek() {
        c cVar = this.r;
        c c = cVar.c();
        if (c != null) {
            return c.b();
        }
        if (cVar == c()) {
            return null;
        }
        do {
            c = cVar.c();
        } while (c == null);
        return c.b();
    }
}
