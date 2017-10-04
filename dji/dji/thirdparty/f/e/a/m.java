package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class m implements b$a {
    final b[] a;

    public m(b[] bVarArr) {
        this.a = bVarArr;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        final l bVar = new dji.thirdparty.f.m.b();
        final AtomicInteger atomicInteger = new AtomicInteger(this.a.length + 1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        dji_thirdparty_f_b_c.a(bVar);
        b[] bVarArr = this.a;
        int length = bVarArr.length;
        int i = 0;
        while (i < length) {
            b bVar2 = bVarArr[i];
            if (!bVar.b()) {
                if (bVar2 == null) {
                    bVar.n_();
                    Throwable nullPointerException = new NullPointerException("A completable source is null");
                    if (atomicBoolean.compareAndSet(false, true)) {
                        dji_thirdparty_f_b_c.a(nullPointerException);
                        return;
                    }
                    d.getInstance().b().a(nullPointerException);
                }
                final b$c dji_thirdparty_f_b_c2 = dji_thirdparty_f_b_c;
                bVar2.a(new b$c(this) {
                    final /* synthetic */ m e;

                    public void a(l lVar) {
                        bVar.a(lVar);
                    }

                    public void a(Throwable th) {
                        bVar.n_();
                        if (atomicBoolean.compareAndSet(false, true)) {
                            dji_thirdparty_f_b_c2.a(th);
                        } else {
                            d.getInstance().b().a(th);
                        }
                    }

                    public void b() {
                        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                            dji_thirdparty_f_b_c2.b();
                        }
                    }
                });
                i++;
            } else {
                return;
            }
        }
        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
            dji_thirdparty_f_b_c.b();
        }
    }
}
