package dji.pilot.fpv.activity;

import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.usb.P3.a;
import dji.midware.usb.P3.a.c;
import dji.pilot.c.d;
import dji.pilot.fpv.control.b$b;

class DJIPreviewActivity$42 implements b$b {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$42(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void a() {
        if (a.getInstance().b() == c.a && dji.pilot.publics.e.a.d(null)) {
            DataSpecialControl.getInstance().setPlayBackType(false).start(20);
            DataCameraSetMode.getInstance().a(d.a).start(null);
            return;
        }
        DJIPreviewActivity.m(this.a);
    }

    public void b() {
        DJIPreviewActivity.n(this.a);
    }

    public void c() {
        DJIPreviewActivity.o(this.a);
    }

    public void d() {
        DJIPreviewActivity.p(this.a).handleFnClick();
    }
}
