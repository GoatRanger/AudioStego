package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCControlChannel;
import dji.common.remotecontroller.DJIRCControlChannelName;
import dji.common.remotecontroller.DJIRCControlMode;
import dji.common.remotecontroller.DJIRCControlStyle;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetSlaveMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode.SlaveCustomModel;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;

class a$9 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$9(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        DJIRCControlMode dJIRCControlMode = new DJIRCControlMode();
        dJIRCControlMode.controlStyle = DataRcGetSlaveMode.getInstance().getControlType().a() == 0 ? DJIRCControlStyle.SlaveDefault : DJIRCControlStyle.SlaveCustom;
        if (dJIRCControlMode.controlStyle != DJIRCControlStyle.SlaveDefault) {
            ArrayList channels = DataRcGetSlaveMode.getInstance().getChannels();
            int size = channels.size();
            for (int i = 0; i < size; i++) {
                boolean z;
                DJIRCControlChannel dJIRCControlChannel = new DJIRCControlChannel();
                dJIRCControlChannel.channel = DJIRCControlChannelName.find(((SlaveCustomModel) channels.get(i)).b);
                if (((SlaveCustomModel) channels.get(i)).a == 1) {
                    z = true;
                } else {
                    z = false;
                }
                dJIRCControlChannel.isReverse = z;
                dJIRCControlMode.controlChannel[i] = dJIRCControlChannel;
            }
            if (this.a != null) {
                this.a.a(dJIRCControlMode);
            }
        } else if (this.a != null) {
            this.a.a(dJIRCControlMode);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}
