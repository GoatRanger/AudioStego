package dji.thirdparty.f.i;

import dji.thirdparty.f.c.b;

public abstract class a {
    protected static final String a = ".errorRendering";

    public void a(Throwable th) {
    }

    @dji.thirdparty.f.b.a
    public final String a(Object obj) {
        try {
            return b(obj);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return obj.getClass().getName() + a;
        } catch (Throwable th) {
            b.b(th);
            return obj.getClass().getName() + a;
        }
    }

    @dji.thirdparty.f.b.a
    protected String b(Object obj) throws InterruptedException {
        return null;
    }
}
