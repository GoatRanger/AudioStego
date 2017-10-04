package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryWarningInformation;
import dji.common.error.DJIBatteryError;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCenterGetBatteryHistory;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;
import java.util.List;

class a$6 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$6(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        int i = 1;
        int[] history = DataCenterGetBatteryHistory.getInstance().getHistory();
        DJILogHelper.getInstance().LOGD("DJISDKCacheBatteryAbstraction", "history is null  " + (history == null));
        List arrayList = new ArrayList();
        if (history != null) {
            DJILogHelper.getInstance().LOGD("DJISDKCacheBatteryAbstraction", "history length  " + history.length);
        }
        if (history != null && history.length > 0) {
            while (i < history.length) {
                arrayList.add(new DJIBatteryWarningInformation(history[i]));
                i++;
            }
            DJIBatteryWarningInformation[] dJIBatteryWarningInformationArr = (DJIBatteryWarningInformation[]) arrayList.toArray(new DJIBatteryWarningInformation[arrayList.size()]);
            if (this.a != null) {
                this.a.a(dJIBatteryWarningInformationArr);
            }
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
