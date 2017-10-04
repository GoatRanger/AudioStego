package dji.pilot2.usercenter.activity;

import dji.pilot.fpv.model.f;
import dji.pilot.usercenter.b.e.b;

class DJIFlightRecordPlayerActivity$2 implements b {
    final /* synthetic */ DJIFlightRecordPlayerActivity a;

    DJIFlightRecordPlayerActivity$2(DJIFlightRecordPlayerActivity dJIFlightRecordPlayerActivity) {
        this.a = dJIFlightRecordPlayerActivity;
    }

    public void a(int i, f fVar, long j, float f) {
        DJIFlightRecordPlayerActivity.a(this.a).setProgress(i);
        DJIFlightRecordPlayerActivity.a(this.a, fVar, j, f, false);
    }

    public void b(f fVar) {
        DJIFlightRecordPlayerActivity.a(this.a).setProgress(DJIFlightRecordPlayerActivity.a(this.a).getMax());
        DJIFlightRecordPlayerActivity.a(this.a, true);
        DJIFlightRecordPlayerActivity.a(this.a, fVar, (long) fVar.G, fVar.F, false);
    }

    public void a(f fVar) {
        DJIFlightRecordPlayerActivity.a(this.a, fVar, (long) fVar.G, fVar.F, true);
    }

    public void a(boolean z) {
        DJIFlightRecordPlayerActivity.b(this.a).setEnabled(z);
    }

    public void b(boolean z) {
        DJIFlightRecordPlayerActivity.c(this.a).setEnabled(z);
    }

    public void a() {
        DJIFlightRecordPlayerActivity.d(this.a).show();
        DJIFlightRecordPlayerActivity.e(this.a).show();
    }

    public void b() {
        DJIFlightRecordPlayerActivity.d(this.a).go();
        DJIFlightRecordPlayerActivity.e(this.a).go();
    }
}
