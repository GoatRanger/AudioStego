package dji.pilot.fpv.activity;

import dji.pilot.joystick.DJIJoyStickView.a;
import dji.pilot.publics.c.i;

class DJIPreviewActivityLitchi$3 implements a {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$3(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void a() {
        int a = i.a(this.a);
        if (a == 8) {
            this.a.setRequestedOrientation(8);
        } else if (a == 0) {
            this.a.setRequestedOrientation(0);
        }
        this.a.e();
    }

    public void b() {
        if (!this.a.c.isLeftShowed()) {
            this.a.setRequestedOrientation(6);
        }
        this.a.f();
    }

    public void c() {
        int a = i.a(this.a);
        if (a == 8) {
            this.a.setRequestedOrientation(8);
        } else if (a == 0) {
            this.a.setRequestedOrientation(0);
        }
        this.a.c();
    }

    public void d() {
        if (!this.a.c.isRightShowed()) {
            this.a.setRequestedOrientation(6);
        }
        this.a.d();
    }
}
