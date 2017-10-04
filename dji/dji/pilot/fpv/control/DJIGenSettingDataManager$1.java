package dji.pilot.fpv.control;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIGenSettingDataManager$1 implements d {
    final /* synthetic */ DJIGenSettingDataManager a;

    DJIGenSettingDataManager$1(DJIGenSettingDataManager dJIGenSettingDataManager) {
        this.a = dJIGenSettingDataManager;
    }

    public void onSuccess(Object obj) {
        DJIGenSettingDataManager.a(this.a).obtainMessage(4096, 11, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        DJIGenSettingDataManager.a(this.a).obtainMessage(4096, 11, 1, aVar).sendToTarget();
    }
}
