package dji.pilot.fpv.activity;

import dji.pilot.fpv.control.q.b;
import dji.pilot.groundStation.a.a;

class DJIPreviewActivity$43 implements b {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$43(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void a(boolean z) {
        DJIPreviewActivity.a(this.a).b(z);
        if (z) {
            DJIPreviewActivity.q(this.a).showChart();
            a instance = a.getInstance(null);
            if (instance == null || !instance.p()) {
                if (DJIPreviewActivity.r(this.a)) {
                    DJIPreviewActivity.s(this.a).show();
                    DJIPreviewActivity.t(this.a).b();
                }
                this.a.b();
                DJIPreviewActivity.u(this.a);
                DJIPreviewActivity.v(this.a);
                DJIPreviewActivity.w(this.a);
                DJIPreviewActivity.x(this.a);
            }
            this.a.a(false);
        } else {
            DJIPreviewActivity.s(this.a).go();
            DJIPreviewActivity.y(this.a).hideMenu(true);
            DJIPreviewActivity.z(this.a).hideView();
            DJIPreviewActivity.A(this.a).hideView();
            if (DJIPreviewActivity.B(this.a) != null) {
                DJIPreviewActivity.B(this.a).go();
            }
            DJIPreviewActivity.p(this.a).hideView();
            DJIPreviewActivity.d(this.a, false);
            DJIPreviewActivity.C(this.a).hideView();
            DJIPreviewActivity.D(this.a).go();
            DJIPreviewActivity.q(this.a).hideChart();
            DJIPreviewActivity.t(this.a).c();
            DJIPreviewActivity.E(this.a).hideView();
        }
        DJIPreviewActivity.F(this.a).setSmallMap(z);
        DJIPreviewActivity.G(this.a).setMapMode(z);
        if (DJIPreviewActivity.H(this.a) != null) {
            DJIPreviewActivity.H(this.a).setSmallMap(z);
        }
        DJIPreviewActivity.F(this.a).changeBg(z);
    }
}
