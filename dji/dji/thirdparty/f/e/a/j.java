package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;
import java.util.concurrent.atomic.AtomicInteger;

public final class j implements b$a {
    final b[] a;

    static final class a extends AtomicInteger implements b$c {
        private static final long e = -7965400327305809232L;
        final b$c a;
        final b[] b;
        int c;
        final e d = new e();

        public a(b$c dji_thirdparty_f_b_c, b[] bVarArr) {
            this.a = dji_thirdparty_f_b_c;
            this.b = bVarArr;
        }

        public void a(l lVar) {
            this.d.a(lVar);
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void b() {
            a();
        }

        void a() {
            if (!this.d.b() && getAndIncrement() == 0) {
                b[] bVarArr = this.b;
                while (!this.d.b()) {
                    int i = this.c;
                    this.c = i + 1;
                    if (i == bVarArr.length) {
                        this.a.b();
                        return;
                    }
                    bVarArr[i].a(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    public j(b[] bVarArr) {
        this.a = bVarArr;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        a aVar = new a(dji_thirdparty_f_b_c, this.a);
        dji_thirdparty_f_b_c.a(aVar.d);
        aVar.a();
    }
}
