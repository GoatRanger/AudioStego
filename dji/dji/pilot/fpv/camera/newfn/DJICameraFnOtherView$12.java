package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$12 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$12(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$12 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.d(this.a.b, this.a.a);
                DJICameraFnOtherView.g(this.a.b).setEnabled(true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$12 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.g(this.a.b).setEnabled(true);
                DJICameraFnOtherView.g(this.a.b).setChecked(DJICameraFnOtherView.h(this.a.b));
            }
        });
    }
}
