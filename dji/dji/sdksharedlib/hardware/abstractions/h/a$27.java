package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRemoteControllerMode;
import dji.common.remotecontroller.RemoteControllerModeParam;
import dji.log.DJILog;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$27 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$27(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        RemoteControllerModeParam remoteControllerModeParam = new RemoteControllerModeParam();
        remoteControllerModeParam.workMode = DJIRemoteControllerMode.find(DataRcGetMaster.getInstance().getMode().a());
        remoteControllerModeParam.isConnected = DataRcGetMaster.getInstance().isConnected();
        DJILog.d("DJISDKCacheRemoteControllerAbstraction", "get RC mode success: " + remoteControllerModeParam.workMode, true, true);
        if (this.a != null) {
            this.a.a(remoteControllerModeParam);
        }
    }

    public void onFailure(a aVar) {
        DJILog.d("DJISDKCacheRemoteControllerAbstraction", "get RC mode fail: " + aVar.name(), true, true);
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}
