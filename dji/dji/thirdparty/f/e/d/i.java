package dji.thirdparty.f.e.d;

import dji.thirdparty.f.i.d;

public final class i {
    public static void a(Throwable th) {
        try {
            d.getInstance().b().a(th);
        } catch (Throwable th2) {
            b(th2);
        }
    }

    private static void b(Throwable th) {
        System.err.println("RxJavaErrorHandler threw an Exception. It shouldn't. => " + th.getMessage());
        th.printStackTrace();
    }
}
