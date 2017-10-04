package dji.pilot.fpv.camera.more;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$2 implements d {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.h(this.a).obtainMessage(8192, 1, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        a.h(this.a).obtainMessage(8192, 0, 0).sendToTarget();
    }
}
