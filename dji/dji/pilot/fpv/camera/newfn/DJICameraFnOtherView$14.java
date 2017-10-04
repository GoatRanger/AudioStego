package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$14 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$14(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$14 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.f(this.a.b, this.a.a);
                DJICameraFnOtherView.k(this.a.b).setEnabled(true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$14 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.k(this.a.b).setEnabled(true);
                DJICameraFnOtherView.k(this.a.b).setChecked(DJICameraFnOtherView.l(this.a.b));
            }
        });
    }
}
