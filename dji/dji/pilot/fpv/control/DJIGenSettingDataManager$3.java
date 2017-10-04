package dji.pilot.fpv.control;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIGenSettingDataManager$3 implements d {
    final /* synthetic */ DJIGenSettingDataManager a;

    DJIGenSettingDataManager$3(DJIGenSettingDataManager dJIGenSettingDataManager) {
        this.a = dJIGenSettingDataManager;
    }

    public void onSuccess(Object obj) {
        DJIGenSettingDataManager.a(this.a).obtainMessage(4097, 13, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("", "==== Histogram code[" + aVar + dji.pilot.usercenter.protocol.d.H, false, true);
        DJIGenSettingDataManager.a(this.a).obtainMessage(4097, 13, 1, aVar).sendToTarget();
    }
}
