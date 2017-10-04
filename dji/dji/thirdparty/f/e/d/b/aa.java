package dji.thirdparty.f.e.d.b;

abstract class aa<E> extends f<E> {
    private static final Integer v = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    protected final int u;

    public aa(int i) {
        super(i);
        this.u = Math.min(i / 4, v.intValue());
    }
}
