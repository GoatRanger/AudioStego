package dji.pilot.fpv.activity;

import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.d.a;
import dji.setting.ui.rc.Rc5DButtonView;

class DJIPreviewActivityKumquat$3 implements d {
    final /* synthetic */ DJIPreviewActivityKumquat a;

    DJIPreviewActivityKumquat$3(DJIPreviewActivityKumquat dJIPreviewActivityKumquat) {
        this.a = dJIPreviewActivityKumquat;
    }

    public void onValueChange(c cVar, a aVar, a aVar2) {
        if (cVar == null || aVar2 == null || aVar2.e() == null) {
            if (cVar != null) {
                DJIPreviewActivityKumquat.a(this.a, cVar.f());
            }
        } else if (!dji.sdksharedlib.a.a.b(aVar2.e())) {
            DJIPreviewActivityKumquat.a(this.a, cVar.f());
        } else if (cVar.f().equals(i.aj)) {
            DJIPreviewActivityKumquat.a(this.a, dji.midware.util.i.b(this.a.getBaseContext(), Rc5DButtonView.f[0], 0));
        } else if (cVar.f().equals(i.ak)) {
            DJIPreviewActivityKumquat.a(this.a, dji.midware.util.i.b(this.a.getBaseContext(), Rc5DButtonView.f[1], 1));
        } else if (cVar.f().equals(i.am)) {
            DJIPreviewActivityKumquat.a(this.a, dji.midware.util.i.b(this.a.getBaseContext(), Rc5DButtonView.f[2], 2));
        } else if (cVar.f().equals(i.an)) {
            DJIPreviewActivityKumquat.a(this.a, dji.midware.util.i.b(this.a.getBaseContext(), Rc5DButtonView.f[3], 3));
        } else if (cVar.f().equals(i.al)) {
            DJIPreviewActivityKumquat.a(this.a, dji.midware.util.i.b(this.a.getBaseContext(), Rc5DButtonView.f[4], 4));
        }
    }
}
