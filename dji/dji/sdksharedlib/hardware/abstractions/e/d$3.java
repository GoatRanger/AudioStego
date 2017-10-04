package dji.sdksharedlib.hardware.abstractions.e;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.b.f;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$3 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ boolean b;
    final /* synthetic */ d c;

    d$3(d dVar, e eVar, boolean z) {
        this.c = dVar;
        this.a = eVar;
        this.b = z;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public void onFailure(a aVar) {
        d.a(this.c, f.am, this.b, this.a);
    }
}
