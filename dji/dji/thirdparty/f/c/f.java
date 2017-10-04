package dji.thirdparty.f.c;

public class f extends RuntimeException {
    private static final long a = -6298857009889503852L;

    public f(String str, Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(str, th);
    }

    public f(Throwable th) {
        String message = th != null ? th.getMessage() : null;
        if (th == null) {
            th = new NullPointerException();
        }
        super(message, th);
    }
}
