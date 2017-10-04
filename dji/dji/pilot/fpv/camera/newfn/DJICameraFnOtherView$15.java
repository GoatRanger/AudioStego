package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$15 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$15(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$15 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.g(this.a.b, this.a.a);
                DJICameraFnOtherView.m(this.a.b).setEnabled(true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$15 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.m(this.a.b).setEnabled(true);
                DJICameraFnOtherView.m(this.a.b).setChecked(DJICameraFnOtherView.n(this.a.b));
            }
        });
    }
}
