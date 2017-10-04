package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$10 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$10(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$10 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.b(this.a.b, this.a.a);
                DJICameraFnOtherView.c(this.a.b).setEnabled(true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$10 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.c(this.a.b).setEnabled(true);
                DJICameraFnOtherView.c(this.a.b).setChecked(DJICameraFnOtherView.d(this.a.b));
            }
        });
    }
}
