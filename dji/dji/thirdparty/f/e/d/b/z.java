package dji.thirdparty.f.e.d.b;

public final class z<E> extends ae<E> {
    public z(int i) {
        super(i);
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        Object[] objArr = this.d;
        long j = this.ar;
        long a = a(j);
        if (b(objArr, a) != null) {
            return false;
        }
        d(j + 1);
        b(objArr, a, e);
        return true;
    }

    public E poll() {
        long j = this.v;
        long a = a(j);
        Object[] objArr = this.d;
        E b = b(objArr, a);
        if (b == null) {
            return null;
        }
        e(j + 1);
        b(objArr, a, null);
        return b;
    }

    public E peek() {
        return c(a(this.v));
    }

    public int size() {
        long b = b();
        while (true) {
            long a = a();
            long b2 = b();
            if (b == b2) {
                return (int) (a - b2);
            }
            b = b2;
        }
    }

    public boolean isEmpty() {
        return a() == b();
    }

    private void d(long j) {
        an.a.putOrderedLong(this, aq, j);
    }

    private void e(long j) {
        an.a.putOrderedLong(this, w, j);
    }

    private long a() {
        return an.a.getLongVolatile(this, aq);
    }

    private long b() {
        return an.a.getLongVolatile(this, w);
    }
}
