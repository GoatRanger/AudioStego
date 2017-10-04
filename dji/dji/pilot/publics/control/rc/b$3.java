package dji.pilot.publics.control.rc;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.pilot.usercenter.protocol.e;

class b$3 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ int b;
    final /* synthetic */ b c;

    b$3(b bVar, DataCommonGetVersion dataCommonGetVersion, int i) {
        this.c = bVar;
        this.a = dataCommonGetVersion;
        this.b = i;
    }

    public void onSuccess(Object obj) {
        DeviceType deviceType = this.a.getDeviceType();
        if (DeviceType.FPGA_G == deviceType && !b.a(this.c, deviceType, b.a(this.c, deviceType.value(), 0))) {
            b.a(this.c).obtainMessage(e.ax, this.b + 1, 0, this.a).sendToTarget();
        }
    }

    public void onFailure(a aVar) {
        b.a(this.c).obtainMessage(e.ax, this.b + 1, 0, this.a).sendToTarget();
    }
}
