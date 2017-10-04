package dji.pilot.publics.control;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;

class a$7 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ a b;

    a$7(a aVar, DataCommonGetVersion dataCommonGetVersion) {
        this.b = aVar;
        this.a = dataCommonGetVersion;
    }

    public void onSuccess(Object obj) {
        if (this.a.getFirmVer("").equals("00000000")) {
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "版本获取 68013 获取到00", true, true);
            this.b.r();
            return;
        }
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "版本获取 68013 success fm=" + this.a.getFirmVer(""), true, true);
        this.b.r();
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "版本获取 68013 " + aVar, true, true);
        this.b.r();
    }
}
