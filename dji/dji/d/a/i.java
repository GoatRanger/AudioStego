package dji.d.a;

import dji.sdksharedlib.b.a.a;
import dji.sdksharedlib.hardware.abstractions.a.b.d;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.j.e;
import java.util.concurrent.TimeUnit;

public class i extends d {
    int a = 0;
    boolean b = true;

    public i() {
        c();
    }

    private void c() {
        dji.thirdparty.f.d.b(100, TimeUnit.MILLISECONDS, e.c()).n(new o<Long, dji.thirdparty.f.d<Boolean>>(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public dji.thirdparty.f.d<Boolean> a(Long l) {
                this.a.b(Integer.valueOf(this.a.a), a.c);
                i iVar;
                if (this.a.b) {
                    iVar = this.a;
                    iVar.a++;
                } else {
                    iVar = this.a;
                    iVar.a--;
                }
                if (this.a.b && this.a.a >= 100) {
                    this.a.b = false;
                } else if (!this.a.b && this.a.a <= 0) {
                    this.a.b = true;
                }
                return dji.thirdparty.f.d.b(Boolean.valueOf(true));
            }
        }).w().C();
    }
}
