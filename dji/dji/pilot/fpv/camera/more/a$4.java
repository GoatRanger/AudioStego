package dji.pilot.fpv.camera.more;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$4 implements d {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.h(this.a).obtainMessage(8194, 1, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        a.h(this.a).obtainMessage(8194, 0, 0).sendToTarget();
    }
}
