package dji.pilot.fpv.camera.more;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.usercenter.protocol.e;

class a$3 implements d {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.h(this.a).sendMessageDelayed(a.h(this.a).obtainMessage(e.an, 1, 0), 300);
    }

    public void onFailure(a aVar) {
        a.h(this.a).obtainMessage(e.an, 0, 0).sendToTarget();
    }
}
