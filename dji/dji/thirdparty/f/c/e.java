package dji.thirdparty.f.c;

public class e extends RuntimeException {
    private static final long a = -419289748403337611L;

    public e(String str, Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        super(str, th);
    }

    public e(Throwable th) {
        String message = th != null ? th.getMessage() : null;
        if (th == null) {
            th = new NullPointerException();
        }
        super(message, th);
    }
}
