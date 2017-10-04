package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCControlChannel;
import dji.common.remotecontroller.DJIRCControlChannelName;
import dji.common.remotecontroller.DJIRCControlMode;
import dji.common.remotecontroller.DJIRCControlStyle;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelCustomModel;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;

class a$2 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$2(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        DJIRCControlMode dJIRCControlMode = new DJIRCControlMode();
        dJIRCControlMode.controlStyle = DJIRCControlStyle.find(DataRcGetControlMode.getInstance().getControlType().a());
        if (dJIRCControlMode.controlStyle._equals(4)) {
            if (dJIRCControlMode.controlStyle._equals(4)) {
                ArrayList channels = DataRcGetControlMode.getInstance().getChannels();
                int size = channels.size();
                for (int i = 0; i < size; i++) {
                    boolean z;
                    DJIRCControlChannel dJIRCControlChannel = new DJIRCControlChannel();
                    dJIRCControlChannel.channel = DJIRCControlChannelName.find(((ChannelCustomModel) channels.get(i)).b);
                    if (((ChannelCustomModel) channels.get(i)).a == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    dJIRCControlChannel.isReverse = z;
                    dJIRCControlMode.controlChannel[i] = dJIRCControlChannel;
                }
                if (this.a != null) {
                    this.a.a(dJIRCControlMode);
                    return;
                }
                return;
            }
            dJIRCControlMode.controlStyle = DJIRCControlStyle.Unknown;
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
