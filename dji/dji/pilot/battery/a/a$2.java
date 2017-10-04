package dji.pilot.battery.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$2 implements d {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.a(this.a).obtainMessage(4108, 0, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        a.a(this.a).obtainMessage(4109, 0, 0).sendToTarget();
    }
}
