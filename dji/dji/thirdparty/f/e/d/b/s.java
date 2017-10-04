package dji.thirdparty.f.e.d.b;

abstract class s<E> extends u<E> {
    protected static final long u = an.a(s.class, "consumerIndex");
    private volatile long Z;

    public s(int i) {
        super(i);
    }

    protected final long a() {
        return this.Z;
    }

    protected final boolean b(long j, long j2) {
        return an.a.compareAndSwapLong(this, u, j, j2);
    }
}
