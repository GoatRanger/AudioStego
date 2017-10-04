package dji.thirdparty.f;

import dji.thirdparty.f.m.b;
import java.util.concurrent.atomic.AtomicBoolean;

class b$23 implements b$a {
    final /* synthetic */ b[] a;

    b$23(b[] bVarArr) {
        this.a = bVarArr;
    }

    public void a(final b$c dji_thirdparty_f_b_c) {
        final l bVar = new b();
        dji_thirdparty_f_b_c.a(bVar);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        b$c anonymousClass1 = new b$c(this) {
            final /* synthetic */ b$23 d;

            public void b() {
                if (atomicBoolean.compareAndSet(false, true)) {
                    bVar.n_();
                    dji_thirdparty_f_b_c.b();
                }
            }

            public void a(Throwable th) {
                if (atomicBoolean.compareAndSet(false, true)) {
                    bVar.n_();
                    dji_thirdparty_f_b_c.a(th);
                    return;
                }
                b.c.a(th);
            }

            public void a(l lVar) {
                bVar.a(lVar);
            }
        };
        b[] bVarArr = this.a;
        int length = bVarArr.length;
        int i = 0;
        while (i < length) {
            b bVar2 = bVarArr[i];
            if (!bVar.b()) {
                if (bVar2 == null) {
                    Throwable nullPointerException = new NullPointerException("One of the sources is null");
                    if (atomicBoolean.compareAndSet(false, true)) {
                        bVar.n_();
                        dji_thirdparty_f_b_c.a(nullPointerException);
                        return;
                    }
                    b.c.a(nullPointerException);
                    return;
                } else if (!atomicBoolean.get() && !bVar.b()) {
                    bVar2.a(anonymousClass1);
                    i++;
                } else {
                    return;
                }
            }
            return;
        }
    }
}
