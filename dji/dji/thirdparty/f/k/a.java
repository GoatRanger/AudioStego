package dji.thirdparty.f.k;

import dji.thirdparty.f.b.b;
import dji.thirdparty.f.e.a.e;
import dji.thirdparty.f.h;
import dji.thirdparty.f.i;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

@b
public class a<T> {
    private final h<? extends T> a;

    private a(h<? extends T> hVar) {
        this.a = hVar;
    }

    @b
    public static <T> a<T> a(h<? extends T> hVar) {
        return new a(hVar);
    }

    @b
    public T a() {
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        dji.thirdparty.f.e.d.b.a(countDownLatch, this.a.a(new i<T>(this) {
            final /* synthetic */ a d;

            public void a(T t) {
                atomicReference.set(t);
                countDownLatch.countDown();
            }

            public void a(Throwable th) {
                atomicReference2.set(th);
                countDownLatch.countDown();
            }
        }));
        Throwable th = (Throwable) atomicReference2.get();
        if (th == null) {
            return atomicReference.get();
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        throw new RuntimeException(th);
    }

    @b
    public Future<T> b() {
        return e.a(this.a.b());
    }
}
