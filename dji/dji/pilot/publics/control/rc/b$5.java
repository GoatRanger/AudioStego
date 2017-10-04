package dji.pilot.publics.control.rc;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetDeviceStatus;
import dji.midware.e.d;

class b$5 implements d {
    final /* synthetic */ DataCommonGetDeviceStatus a;
    final /* synthetic */ int b;
    final /* synthetic */ b c;

    b$5(b bVar, DataCommonGetDeviceStatus dataCommonGetDeviceStatus, int i) {
        this.c = bVar;
        this.a = dataCommonGetDeviceStatus;
        this.b = i;
    }

    public void onSuccess(Object obj) {
        b.a(this.c).obtainMessage(20480, 0, this.a.getMode()).sendToTarget();
    }

    public void onFailure(a aVar) {
        b.a(this.c).obtainMessage(20480, 1, this.b, aVar).sendToTarget();
    }
}
