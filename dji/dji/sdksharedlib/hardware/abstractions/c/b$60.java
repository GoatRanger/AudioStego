package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.common.util.DJILensFeatureUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetShotInfo;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$60 implements d {
    final /* synthetic */ DataCameraGetShotInfo a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$60(b bVar, DataCameraGetShotInfo dataCameraGetShotInfo, e eVar) {
        this.c = bVar;
        this.a = dataCameraGetShotInfo;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        String name = this.a.getName();
        if (name == null || name.trim().length() == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            DJILensFeatureUtils dJILensFeatureUtils = this.c.s;
            stringBuilder.append(DJILensFeatureUtils.getProductName(this.a.getMemberId(), this.a.getModelId(), this.a.getHardVersion()));
            if (stringBuilder.length() != 0) {
                this.b.a(stringBuilder.toString());
                return;
            } else {
                this.b.a(DJICameraError.COMMON_UNKNOWN);
                return;
            }
        }
        this.b.a(name);
    }

    public void onFailure(a aVar) {
        this.b.a(DJICameraError.COMMON_UNKNOWN);
    }
}
