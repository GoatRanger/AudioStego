package dji.pilot.fpv.camera.newfn;

import dji.common.error.DJIError;
import dji.sdksharedlib.c.h;

class DJICameraFnOtherView$1 implements h {
    final /* synthetic */ boolean a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$1(DJICameraFnOtherView dJICameraFnOtherView, boolean z) {
        this.b = dJICameraFnOtherView;
        this.a = z;
    }

    public void a() {
        DJICameraFnOtherView.a(this.b, this.a);
        DJICameraFnOtherView.a(this.b).c(this.a);
        this.b.postDelayed(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$1 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.b(this.a.b).setEnabled(true);
            }
        }, 500);
    }

    public void a(DJIError dJIError) {
        DJICameraFnOtherView.a(this.b).c(!this.a);
        this.b.postDelayed(new Runnable(this) {
            final /* synthetic */ DJICameraFnOtherView$1 a;

            {
                this.a = r1;
            }

            public void run() {
                DJICameraFnOtherView.b(this.a.b).setEnabled(true);
            }
        }, 500);
    }
}
