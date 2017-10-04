package dji.thirdparty.f.e.d.b;

abstract class n<E> extends l<E> {
    private static final long v = an.a(n.class, "producerIndex");
    private volatile long w;

    public n(int i) {
        super(i);
    }

    protected final long b() {
        return this.w;
    }

    protected final boolean c(long j, long j2) {
        return an.a.compareAndSwapLong(this, v, j, j2);
    }
}
