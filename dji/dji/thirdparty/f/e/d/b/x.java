package dji.thirdparty.f.e.d.b;

abstract class x<E> extends t<E> {
    protected static final long aD = an.a(x.class, "producerIndex");
    private volatile long u;

    protected final long b() {
        return this.u;
    }

    protected final void d(long j) {
        an.a.putOrderedLong(this, aD, j);
    }

    public x(int i) {
        super(i);
    }
}
