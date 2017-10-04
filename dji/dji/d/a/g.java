package dji.d.a;

import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.product.Model;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.hardware.abstractions.g.a;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.j.e;
import java.util.concurrent.TimeUnit;

public class g extends a {
    private boolean a = false;
    private boolean b = true;
    private Model c = Model.Phantom_4;

    public g() {
        if (this.b) {
            c();
        } else {
            b();
        }
    }

    private void c() {
        d.b(1, TimeUnit.SECONDS, e.c()).n(new o<Long, d<Boolean>>(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public d<Boolean> a(Long l) {
                this.a.a = !this.a.a;
                this.a.a(Boolean.valueOf(this.a.a), this.a.a(dji.sdksharedlib.b.d.ck));
                this.a.a(CameraMode.RecordVideo, this.a.c(b.b));
                return d.b(Boolean.valueOf(true));
            }
        }).w().C();
    }

    protected void b() {
        d.b(5, TimeUnit.SECONDS, e.c()).n(new o<Long, d<Boolean>>(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public d<Boolean> a(Long l) {
                this.a.a(Boolean.valueOf(true), this.a.a(dji.sdksharedlib.b.d.ck));
                this.a.a(this.a.c, this.a.a(h.c));
                boolean z = this.a.c == Model.Osmo || this.a.c == Model.Osmo_Mobile || this.a.c == Model.Osmo_Pro || this.a.c == Model.Osmo_Raw;
                this.a.a(Boolean.valueOf(z), this.a.a(h.d));
                dji.d.a.getInstance().a();
                return d.b(Boolean.valueOf(true));
            }
        }).w().C();
    }
}
