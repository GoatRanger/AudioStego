package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$11 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$11(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$11 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.c(this.a.b, this.a.a);
                DJICameraFnOtherView.e(this.a.b).setEnabled(true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$11 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.e(this.a.b).setEnabled(true);
                DJICameraFnOtherView.e(this.a.b).setChecked(DJICameraFnOtherView.f(this.a.b));
            }
        });
    }
}
