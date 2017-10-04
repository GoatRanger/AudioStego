package dji.d.a;

import dji.sdksharedlib.b.f;
import dji.sdksharedlib.hardware.abstractions.e.a;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;
import java.util.concurrent.TimeUnit;

public class e extends a {
    Integer a = Integer.valueOf(0);

    public e() {
        r();
    }

    private void r() {
        d.b(1000, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new o<Long, d<Boolean>>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public d<Boolean> a(Long l) {
                this.a.b(this.a.a, f.d);
                Integer num = this.a.a;
                this.a.a = Integer.valueOf(this.a.a.intValue() + 1);
                if (this.a.a.intValue() >= 360) {
                    this.a.a = Integer.valueOf(0);
                }
                return d.b(Boolean.valueOf(true));
            }
        }).w().C();
    }
}
