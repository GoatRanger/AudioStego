package dji.pilot.fpv.activity;

import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.d.a;

class DJIPreviewActivityKumquat$2 implements d {
    final /* synthetic */ DJIPreviewActivityKumquat a;

    DJIPreviewActivityKumquat$2(DJIPreviewActivityKumquat dJIPreviewActivityKumquat) {
        this.a = dJIPreviewActivityKumquat;
    }

    public void onValueChange(c cVar, a aVar, a aVar2) {
        DJIPreviewActivityKumquat.a(this.a, (CameraOrientation) dji.sdksharedlib.a.a.b(b.bW));
        if (DJIPreviewActivityKumquat.b(this.a) != null) {
            dji.thirdparty.a.c.a().e(DJIPreviewActivityKumquat.b(this.a));
        }
    }
}
