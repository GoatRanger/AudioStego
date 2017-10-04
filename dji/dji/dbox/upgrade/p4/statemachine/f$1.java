package dji.dbox.upgrade.p4.statemachine;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class f$1 implements d {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public void onSuccess(Object obj) {
        f.a(this.a).c();
    }

    public void onFailure(a aVar) {
        dji.dbox.upgrade.p4.a.a.a("", "recover DataCameraControlUpgrade =" + aVar);
    }
}
