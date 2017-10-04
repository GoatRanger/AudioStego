package dji.pilot.fpv.control;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.e.d;

class b$10 implements d {
    final /* synthetic */ MODE a;
    final /* synthetic */ boolean b;
    final /* synthetic */ b c;

    b$10(b bVar, MODE mode, boolean z) {
        this.c = bVar;
        this.a = mode;
        this.b = z;
    }

    public void onSuccess(Object obj) {
        b.a(this.c, this.a);
        this.c.b.post(new Runnable(this) {
            final /* synthetic */ b$10 a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c.e.setEnabled(true);
                b.b(this.a.c, this.a.b);
                b.a(this.a.c, DataCameraGetPushStateInfo.getInstance(), true);
            }
        });
    }

    public void onFailure(a aVar) {
        this.c.b.post(new Runnable(this) {
            final /* synthetic */ b$10 a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c.e.setEnabled(true);
                this.a.c.e.setChecked(!this.a.b);
                b.a(this.a.c, DataCameraGetPushStateInfo.getInstance(), true);
            }
        });
    }
}
