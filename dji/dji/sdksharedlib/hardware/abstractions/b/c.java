package dji.sdksharedlib.hardware.abstractions.b;

import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.a.e;
import dji.sdksharedlib.hardware.abstractions.b.f;

public class c extends k {
    private static String a = "DJISDKCacheFoldingDroneBatteryAbstraction";

    public void a(String str, int i, dji.sdksharedlib.d.c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        this.i = 0;
        this.c = 3;
        this.b = true;
        this.j = new dji.sdksharedlib.hardware.abstractions.b.a.c(Integer.MAX_VALUE);
        this.k = new e(Integer.MAX_VALUE);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CellVoltage")
    public void n(b.e eVar) {
        if (eVar != null) {
            DataSmartBatteryGetPushCellVoltage instance = DataSmartBatteryGetPushCellVoltage.getInstance();
            instance.setIndex(0).setRequestPush(false);
            instance.start(new 1(this, instance, eVar));
        }
    }
}
