package dji.thirdparty.f.e.d.b;

public abstract class h<E> extends f<E> {
    private static final long v = ((long) (an.a.arrayBaseOffset(long[].class) + (32 << (w - a))));
    private static final int w = (a + 3);
    protected final long[] u;

    static {
        if (8 == an.a.arrayIndexScale(long[].class)) {
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }

    public h(int i) {
        super(i);
        int i2 = (int) (this.c + 1);
        this.u = new long[((i2 << a) + 64)];
        for (long j = 0; j < ((long) i2); j++) {
            a(this.u, d(j), j);
        }
    }

    protected final long d(long j) {
        return v + ((this.c & j) << w);
    }

    protected final void a(long[] jArr, long j, long j2) {
        an.a.putOrderedLong(jArr, j, j2);
    }

    protected final long a(long[] jArr, long j) {
        return an.a.getLongVolatile(jArr, j);
    }
}
