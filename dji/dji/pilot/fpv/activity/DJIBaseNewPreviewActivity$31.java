package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.a;
import dji.pilot.fpv.control.DJIGenSettingDataManager$c;

class DJIBaseNewPreviewActivity$31 implements DJIGenSettingDataManager$c {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$31(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void a(int i, boolean z) {
    }

    public void a(int i, boolean z, int i2, a aVar) {
        if (9 == i) {
            DJIBaseNewPreviewActivity.f(this.a);
        }
    }

    public void a(int i) {
        if (4 == i) {
            DJIBaseNewPreviewActivity.g(this.a);
        } else if (5 == i) {
            DJIBaseNewPreviewActivity.h(this.a);
        } else if (8 == i) {
            DJIBaseNewPreviewActivity.i(this.a);
        }
    }

    public void b(int i) {
    }
}
