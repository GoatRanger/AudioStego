package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryWarningInformation;
import dji.common.error.DJIBatteryError;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetHistory;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;

class k$15 implements d {
    final /* synthetic */ DataSmartBatteryGetHistory a;
    final /* synthetic */ e b;
    final /* synthetic */ k c;

    k$15(k kVar, DataSmartBatteryGetHistory dataSmartBatteryGetHistory, e eVar) {
        this.c = kVar;
        this.a = dataSmartBatteryGetHistory;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int i = 1;
        long[] history = this.a.getHistory();
        DJILogHelper.getInstance().LOGD("DJISDKCacheSmartBatteryAbstraction", "history is null " + (history == null));
        ArrayList arrayList = new ArrayList();
        if (history != null) {
            DJILogHelper.getInstance().LOGD("DJISDKCacheSmartBatteryAbstraction", "history length " + history.length);
        }
        if (history != null && history.length > 0) {
            while (i < history.length) {
                arrayList.add(new DJIBatteryWarningInformation(history[i]));
                i++;
            }
            DJIBatteryWarningInformation[] dJIBatteryWarningInformationArr = (DJIBatteryWarningInformation[]) arrayList.toArray(new DJIBatteryWarningInformation[arrayList.size()]);
            if (this.b != null) {
                this.b.a(dJIBatteryWarningInformationArr);
            }
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null && this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
