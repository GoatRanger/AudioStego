package dji.d.a;

import dji.sdksharedlib.hardware.abstractions.a.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.j.e;
import java.util.concurrent.TimeUnit;

public class a extends b {
    int a = 0;
    boolean b = true;

    public a() {
        b();
    }

    private void b() {
        d.b(100, TimeUnit.MILLISECONDS, e.c()).n(new o<Long, d<Boolean>>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public d<Boolean> a(Long l) {
                this.a.b(Integer.valueOf(this.a.a), dji.sdksharedlib.b.a.a.b);
                a aVar;
                if (this.a.b) {
                    aVar = this.a;
                    aVar.a++;
                } else {
                    aVar = this.a;
                    aVar.a--;
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
