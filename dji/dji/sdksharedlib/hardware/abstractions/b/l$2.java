package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryLowCellVoltageOperation;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.setting.ui.battery.nonsmart.SeriousLowVoltageAction;

class l$2 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ l b;

    l$2(l lVar, e eVar) {
        this.b = lVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, DJIBatteryLowCellVoltageOperation.find(dji.midware.data.manager.P3.d.read(SeriousLowVoltageAction.a).value.intValue()));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}
