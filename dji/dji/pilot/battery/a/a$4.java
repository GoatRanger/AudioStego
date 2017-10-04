package dji.pilot.battery.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$4 implements d {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.a(this.a).obtainMessage(4104, 0, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        a.a(this.a).obtainMessage(4105, 0, 0).sendToTarget();
    }
}
