package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.JoinedMasterNameAndPasswordResult;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetConnectMaster;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.text.NumberFormat;

class a$14 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$14(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        int i = 9999;
        JoinedMasterNameAndPasswordResult joinedMasterNameAndPasswordResult = new JoinedMasterNameAndPasswordResult();
        joinedMasterNameAndPasswordResult.id = Integer.valueOf(DataRcGetConnectMaster.getInstance().getMaster().id);
        int i2 = DataRcGetConnectMaster.getInstance().getMaster().password;
        if (i2 <= 9999) {
            i = i2;
        }
        if (i < 0) {
            i = 0;
        }
        NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        instance.setMaximumIntegerDigits(4);
        instance.setMinimumIntegerDigits(4);
        joinedMasterNameAndPasswordResult.password = instance.format((long) i);
        joinedMasterNameAndPasswordResult.name = DataRcGetConnectMaster.getInstance().getMaster().name;
        if (this.a != null) {
            this.a.a(joinedMasterNameAndPasswordResult);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}
