package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.log.DJILog;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetBarCode;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class k$7 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ e b;
    final /* synthetic */ k c;

    k$7(k kVar, boolean z, e eVar) {
        this.c = kVar;
        this.a = z;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        Object barCode = DataSmartBatteryGetBarCode.getInstance().getBarCode();
        if (!(barCode == null || barCode.length() == 0 || this.a)) {
            barCode = this.c.d(barCode);
        }
        if (this.b != null) {
            this.b.a(barCode);
        }
    }

    public void onFailure(a aVar) {
        DJILog.d("DJISDKCacheSmartBatteryAbstraction", "M600 get serial number fail. index " + this.c.i);
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
