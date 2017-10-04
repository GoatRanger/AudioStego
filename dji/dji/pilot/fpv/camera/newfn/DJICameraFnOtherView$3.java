package dji.pilot.fpv.camera.newfn;

import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.common.error.DJIError;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.widget.DJISwitch;
import dji.sdksharedlib.c.c;
import dji.sdksharedlib.d.a;

class DJICameraFnOtherView$3 implements c {
    final /* synthetic */ DJICameraFnOtherView a;

    DJICameraFnOtherView$3(DJICameraFnOtherView dJICameraFnOtherView) {
        this.a = dJICameraFnOtherView;
    }

    public void a(a aVar) {
        boolean z;
        boolean z2 = true;
        CameraOrientation cameraOrientation = (CameraOrientation) aVar.e();
        DJIGenSettingDataManager a = DJICameraFnOtherView.a(this.a);
        if (cameraOrientation.equals(CameraOrientation.Landscape)) {
            z = false;
        } else {
            z = true;
        }
        a.c(z);
        DJICameraFnOtherView dJICameraFnOtherView = this.a;
        if (cameraOrientation.equals(CameraOrientation.Landscape)) {
            z = false;
        } else {
            z = true;
        }
        DJICameraFnOtherView.a(dJICameraFnOtherView, z);
        DJISwitch b = DJICameraFnOtherView.b(this.a);
        if (cameraOrientation.equals(CameraOrientation.Landscape)) {
            z2 = false;
        }
        b.setChecked(z2);
    }

    public void a(DJIError dJIError) {
    }
}
