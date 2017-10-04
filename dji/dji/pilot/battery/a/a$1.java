package dji.pilot.battery.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$1 implements d {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.a(this.a).obtainMessage(4106, 0, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        a.a(this.a).obtainMessage(4107, 0, 0).sendToTarget();
    }
}
