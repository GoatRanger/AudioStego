package dji.thirdparty.f.c;

public final class d extends RuntimeException {
    private static final long a = 8622579378868820554L;

    public d(Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(th);
    }

    public d(String str, Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(str, th);
    }
}
