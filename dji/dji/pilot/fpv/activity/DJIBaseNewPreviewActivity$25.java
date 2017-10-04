package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIBaseNewPreviewActivity$25 implements d {
    final /* synthetic */ float a;
    final /* synthetic */ float b;
    final /* synthetic */ DJIBaseNewPreviewActivity c;

    DJIBaseNewPreviewActivity$25(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity, float f, float f2) {
        this.c = dJIBaseNewPreviewActivity;
        this.a = f;
        this.b = f2;
    }

    public void onSuccess(Object obj) {
        DJIBaseNewPreviewActivity.a(this.c, this.a, this.b);
    }

    public void onFailure(a aVar) {
    }
}
