package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.pilot.fpv.control.DJIGenSettingDataManager$c;

class DJICameraFnOtherView$4 implements DJIGenSettingDataManager$c {
    final /* synthetic */ DJICameraFnOtherView a;

    DJICameraFnOtherView$4(DJICameraFnOtherView dJICameraFnOtherView) {
        this.a = dJICameraFnOtherView;
    }

    public void a(int i, boolean z) {
        if (z) {
            DJICameraFnOtherView.a(this.a, i);
        } else {
            DJICameraFnOtherView.b(this.a, i);
        }
    }

    public void a(int i, boolean z, int i2, a aVar) {
        if (z) {
            DJICameraFnOtherView.a(this.a, i, i2, aVar);
        } else {
            DJICameraFnOtherView.b(this.a, i, i2, aVar);
        }
    }

    public void b(int i) {
    }

    public void a(int i) {
        DJICameraFnOtherView.c(this.a, i);
    }
}
