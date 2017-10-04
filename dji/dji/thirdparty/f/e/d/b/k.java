package dji.thirdparty.f.e.d.b;

abstract class k<E> extends m<E> {
    private static final long v = an.a(k.class, "consumerIndex");
    private volatile long w;

    public k(int i) {
        super(i);
    }

    protected final long a() {
        return this.w;
    }

    protected final boolean b(long j, long j2) {
        return an.a.compareAndSwapLong(this, v, j, j2);
    }
}
