package dji.pilot.fpv.control;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIGenSettingDataManager$8 implements d {
    final /* synthetic */ DJIGenSettingDataManager a;

    DJIGenSettingDataManager$8(DJIGenSettingDataManager dJIGenSettingDataManager) {
        this.a = dJIGenSettingDataManager;
    }

    public void onSuccess(Object obj) {
        DJIGenSettingDataManager.a(this.a).obtainMessage(4097, 6, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        DJIGenSettingDataManager.a(this.a).obtainMessage(4097, 6, 1, aVar).sendToTarget();
    }
}
