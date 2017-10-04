package dji.pilot.fpv.activity;

import dji.pilot.fpv.control.q.b;
import dji.pilot.groundStation.a.a;
import dji.pilot.visual.a.c;

class DJIPreviewActivityLitchi$36 implements b {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$36(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void a(boolean z) {
        DJIPreviewActivityLitchi.a(this.a).b(z);
        if (z) {
            DJIPreviewActivityLitchi.q(this.a).showChart();
            a instance = a.getInstance(null);
            if (instance == null || !instance.p()) {
                DJIPreviewActivityLitchi.r(this.a).show();
                DJIPreviewActivityLitchi.m(this.a).b();
            }
            if (c.getInstance().e() && this.a.n()) {
                DJIPreviewActivityLitchi.s(this.a).show();
            }
            DJIPreviewActivityLitchi.t(this.a);
            this.a.g();
        } else {
            DJIPreviewActivityLitchi.r(this.a).go();
            DJIPreviewActivityLitchi.u(this.a).hideMenu(true);
            DJIPreviewActivityLitchi.v(this.a).hideView();
            DJIPreviewActivityLitchi.p(this.a).hideView();
            DJIPreviewActivityLitchi.q(this.a).hideChart();
            DJIPreviewActivityLitchi.m(this.a).c();
            DJIPreviewActivityLitchi.s(this.a).go();
            DJIPreviewActivityLitchi.b(this.a, false);
            DJIPreviewActivityLitchi.w(this.a).hideByOuter();
            DJIPreviewActivityLitchi.x(this.a).hideView();
            DJIPreviewActivityLitchi.y(this.a).hideView();
        }
        DJIPreviewActivityLitchi.z(this.a).setSmallMap(z);
        DJIPreviewActivityLitchi.z(this.a).changeBg(z);
        c.getInstance().a(z);
    }
}
