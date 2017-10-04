package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryCell;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$9 implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$9(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void run() {
        int i = 0;
        if (this.b.g == null) {
            this.b.g = (DJIBatteryCell[]) a.d(dji.sdksharedlib.b.a.p);
            if (this.b.g == null) {
                DJIBatteryCell[] dJIBatteryCellArr;
                dji.sdksharedlib.d.a a = a.a(b.d(dji.sdksharedlib.b.a.p), 1000);
                a aVar = this.b;
                if (a == null || a.e() == null || !(a.e() instanceof DJIBatteryCell[])) {
                    dJIBatteryCellArr = null;
                } else {
                    dJIBatteryCellArr = (DJIBatteryCell[]) a.e();
                }
                aVar.g = dJIBatteryCellArr;
            }
        }
        if (this.b.g != null) {
            int i2 = 0;
            while (i2 < this.b.g.length && this.b.g != null && this.b.g[i2].getVoltage() != 0) {
                i++;
                i2++;
            }
        }
        if (i > 0) {
            CallbackUtils.onSuccess(this.a, Integer.valueOf(i));
        } else {
            CallbackUtils.onFailure(this.a, DJIError.COMMON_UNKNOWN);
        }
    }
}
