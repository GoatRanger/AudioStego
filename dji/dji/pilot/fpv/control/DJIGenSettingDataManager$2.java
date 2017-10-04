package dji.pilot.fpv.control;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIGenSettingDataManager$2 implements d {
    final /* synthetic */ DJIGenSettingDataManager a;

    DJIGenSettingDataManager$2(DJIGenSettingDataManager dJIGenSettingDataManager) {
        this.a = dJIGenSettingDataManager;
    }

    public void onSuccess(Object obj) {
        DJIGenSettingDataManager.a(this.a).obtainMessage(4097, 11, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        DJIGenSettingDataManager.a(this.a).obtainMessage(4097, 11, 1, aVar).sendToTarget();
    }
}
