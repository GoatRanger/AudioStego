package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$17 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$17(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$17 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.q(this.a.b).setEnabled(true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$17 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.q(this.a.b).setEnabled(true);
                DJICameraFnOtherView.q(this.a.b).setChecked(this.a.a);
            }
        });
    }
}
