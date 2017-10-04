package dji.thirdparty.f.c;

public final class h extends RuntimeException {
    private static final long a = 4594672310593167598L;

    public h(Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(th);
    }

    public h(String str, Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(str, th);
    }
}
