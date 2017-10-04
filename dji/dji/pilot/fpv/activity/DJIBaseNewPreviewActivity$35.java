package dji.pilot.fpv.activity;

import dji.pilot.joystick.DJIJoyStickView.a;
import dji.pilot.publics.c.i;

class DJIBaseNewPreviewActivity$35 implements a {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$35(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void a() {
        int a = i.a(this.a);
        if (a == 8) {
            this.a.setRequestedOrientation(8);
        } else if (a == 0) {
            this.a.setRequestedOrientation(0);
        }
        this.a.o();
    }

    public void b() {
        if (!this.a.m.isLeftShowed()) {
            this.a.setRequestedOrientation(6);
        }
        this.a.p();
    }

    public void c() {
        int a = i.a(this.a);
        if (a == 8) {
            this.a.setRequestedOrientation(8);
        } else if (a == 0) {
            this.a.setRequestedOrientation(0);
        }
        this.a.m();
    }

    public void d() {
        if (!this.a.m.isRightShowed()) {
            this.a.setRequestedOrientation(6);
        }
        this.a.n();
    }
}
