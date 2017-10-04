package dji.d.a;

import dji.sdksharedlib.hardware.abstractions.a.a.a;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.j.e;
import java.util.concurrent.TimeUnit;

public class f extends a {
    int a = 0;
    boolean b = true;

    public f() {
        d();
    }

    private void d() {
        d.b(100, TimeUnit.MILLISECONDS, e.c()).n(new o<Long, d<Boolean>>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public d<Boolean> a(Long l) {
                this.a.b(Integer.valueOf(this.a.a), dji.sdksharedlib.b.a.a.c);
                f fVar;
                if (this.a.b) {
                    fVar = this.a;
                    fVar.a++;
                } else {
                    fVar = this.a;
                    fVar.a--;
                }
                if (this.a.b && this.a.a >= 100) {
                    this.a.b = false;
                } else if (!this.a.b && this.a.a <= 0) {
                    this.a.b = true;
                }
                return d.b(Boolean.valueOf(true));
            }
        }).w().C();
    }
}
