package dji.thirdparty.f.e.d.a;

public final class h<E> extends b<E> {
    public h() {
        c cVar = new c();
        a(cVar);
        c(cVar);
        cVar.a(null);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        c cVar = new c(e);
        b().a(cVar);
        a(cVar);
        return true;
    }

    public E poll() {
        c c = d().c();
        if (c == null) {
            return null;
        }
        E a = c.a();
        c(c);
        return a;
    }

    public E peek() {
        c c = d().c();
        if (c != null) {
            return c.b();
        }
        return null;
    }
}
