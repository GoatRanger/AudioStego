package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.a;
import dji.pilot.fpv.control.DJIGenSettingDataManager$c;

class DJIPreviewActivity$41 implements DJIGenSettingDataManager$c {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$41(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void a(int i, boolean z) {
    }

    public void a(int i, boolean z, int i2, a aVar) {
        if (9 == i) {
            DJIPreviewActivity.h(this.a);
        }
    }

    public void a(int i) {
        if (4 == i) {
            DJIPreviewActivity.i(this.a);
        } else if (5 == i) {
            DJIPreviewActivity.j(this.a);
        } else if (8 == i) {
            DJIPreviewActivity.k(this.a);
        } else if (10 == i) {
            DJIPreviewActivity.l(this.a);
        }
    }

    public void b(int i) {
    }
}
