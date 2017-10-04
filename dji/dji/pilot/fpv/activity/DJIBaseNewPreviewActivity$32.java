package dji.pilot.fpv.activity;

import dji.pilot.fpv.control.q.b;
import dji.pilot.groundStation.a.a;
import dji.pilot.visual.a.c;

class DJIBaseNewPreviewActivity$32 implements b {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$32(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void a(boolean z) {
        if (z) {
            DJIBaseNewPreviewActivity.j(this.a).setBackgroundColor(this.a.getResources().getColor(17170445));
            this.a.f();
        } else {
            DJIBaseNewPreviewActivity.j(this.a).setBackgroundColor(this.a.getResources().getColor(17170444));
            DJIBaseNewPreviewActivity.c(this.a).postDelayed(new Runnable(this) {
                final /* synthetic */ DJIBaseNewPreviewActivity$32 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.f();
                }
            }, 300);
        }
        DJIBaseNewPreviewActivity.a(this.a).b(z);
        if (z) {
            DJIBaseNewPreviewActivity.k(this.a).showChart();
            a instance = a.getInstance(null);
            if (instance == null || !instance.p()) {
                DJIBaseNewPreviewActivity.l(this.a).show();
                DJIBaseNewPreviewActivity.m(this.a).show();
            }
            this.a.z();
            this.a.A();
            this.a.q();
            DJIBaseNewPreviewActivity.n(this.a).showByOuter();
            DJIBaseNewPreviewActivity.o(this.a).show();
        } else {
            DJIBaseNewPreviewActivity.l(this.a).go();
            DJIBaseNewPreviewActivity.p(this.a).hideMenu(true);
            DJIBaseNewPreviewActivity.q(this.a).hideView();
            DJIBaseNewPreviewActivity.k(this.a).hideChart();
            DJIBaseNewPreviewActivity.m(this.a).hide();
            DJIBaseNewPreviewActivity.r(this.a).go();
            this.a.d(false);
            DJIBaseNewPreviewActivity.n(this.a).hideByOuter();
            this.a.i.hideView();
            DJIBaseNewPreviewActivity.o(this.a).go();
            this.a.j.hideView();
            this.a.h.hideView();
        }
        this.a.e.setSmallMap(z);
        c.getInstance().a(z);
    }
}
