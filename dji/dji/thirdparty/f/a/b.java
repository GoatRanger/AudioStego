package dji.thirdparty.f.a;

import android.os.Looper;
import dji.thirdparty.f.a.b.a;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class b implements l {
    private final AtomicBoolean a = new AtomicBoolean();

    protected abstract void d();

    public static void c() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Expected to be called on the main thread but was " + Thread.currentThread().getName());
        }
    }

    public final boolean b() {
        return this.a.get();
    }

    public final void n_() {
        if (!this.a.compareAndSet(false, true)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            d();
        } else {
            a.a().a().a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.d();
                }
            });
        }
    }
}
