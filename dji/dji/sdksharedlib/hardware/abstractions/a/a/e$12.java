package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.airlink.LBAirLinkFPVVideoQualityLatency;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetSDRImageTransmMode;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode.SDRImageTransmMode;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class e$12 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ e b;

    e$12(e eVar, e eVar2) {
        this.b = eVar;
        this.a = eVar2;
    }

    public void onSuccess(Object obj) {
        Object obj2;
        SDRImageTransmMode mode = DataOsdGetSDRImageTransmMode.getInstance().getMode();
        if (mode.equals(SDRImageTransmMode.b)) {
            obj2 = LBAirLinkFPVVideoQualityLatency.HighQuality;
        } else if (mode.equals(SDRImageTransmMode.a)) {
            obj2 = LBAirLinkFPVVideoQualityLatency.LowLatency;
        } else {
            obj2 = LBAirLinkFPVVideoQualityLatency.Unknown;
        }
        CallbackUtils.onSuccess(this.a, obj2);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}
