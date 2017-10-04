package dji.pilot.fpv.camera.newfn;

import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.d.a;

class DJICameraFnOtherView$9 implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ a b;
    final /* synthetic */ DJICameraFnOtherView c;

    DJICameraFnOtherView$9(DJICameraFnOtherView dJICameraFnOtherView, c cVar, a aVar) {
        this.c = dJICameraFnOtherView;
        this.a = cVar;
        this.b = aVar;
    }

    public void run() {
        if (this.a != null && this.b != null && this.b.e() != null) {
            DJICameraFnOtherView.a(this.c, this.b.e().equals(CameraOrientation.Portrait));
            DJICameraFnOtherView.b(this.c).setChecked(this.b.e().equals(CameraOrientation.Portrait));
        }
    }
}
