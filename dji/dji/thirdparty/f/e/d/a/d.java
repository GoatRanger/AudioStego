package dji.thirdparty.f.e.d.a;

public final class d<E> extends b<E> {
    public d() {
        c cVar = new c();
        c(cVar);
        b(cVar);
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        c cVar = new c(e);
        b(cVar).a(cVar);
        return true;
    }

    public final E poll() {
        c d = d();
        c c = d.c();
        E a;
        if (c != null) {
            a = c.a();
            c(c);
            return a;
        } else if (d == a()) {
            return null;
        } else {
            do {
                c = d.c();
            } while (c == null);
            a = c.a();
            c(c);
            return a;
        }
    }

    public final E peek() {
        c d = d();
        c c = d.c();
        if (c != null) {
            return c.b();
        }
        if (d == a()) {
            return null;
        }
        do {
            c = d.c();
        } while (c == null);
        return c.b();
    }
}
