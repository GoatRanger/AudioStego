package dji.pilot.fpv.camera.newfn;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$16 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$16(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$16 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.h(this.a.b, this.a.a);
                DJICameraFnOtherView.o(this.a.b).setEnabled(true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$16 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.o(this.a.b).setEnabled(true);
                DJICameraFnOtherView.o(this.a.b).setChecked(DJICameraFnOtherView.p(this.a.b));
            }
        });
    }
}
