package dji.pilot.visual.selfcal;

import android.content.Context;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataEyeEasySelfCalibration;
import dji.midware.data.model.P3.DataEyeEasySelfCalibration.SelfRequest;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration.CaliStatusCode;
import dji.midware.e.d;
import dji.pilot.fpv.model.n.a;
import dji.pilot.visual.util.c;

public class b {
    private final Context a;
    private CaliStatusCode b = CaliStatusCode.OTHER;
    private a c = null;

    public b(Context context) {
        this.a = context;
        c();
    }

    public void onEventMainThread(a aVar) {
        if (a.g == aVar) {
            DataEyeEasySelfCalibration.getInstance().a(SelfRequest.b).start(new d(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    c.a("Self Cal start success");
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    c.a("Self Cal start fail-" + aVar);
                }
            });
        }
    }

    public void onEventMainThread(DataEyeGetPushEasySelfCalibration dataEyeGetPushEasySelfCalibration) {
        CaliStatusCode caliStatusCode = dataEyeGetPushEasySelfCalibration.getCaliStatusCode();
        if (caliStatusCode != this.b) {
            if (CaliStatusCode.NotCalibrating == caliStatusCode) {
                b();
            } else {
                a();
            }
            this.b = caliStatusCode;
        }
    }

    public void onEventMainThread(o oVar) {
        if (o.a == oVar) {
            this.b = CaliStatusCode.OTHER;
            b();
        }
    }

    protected void a() {
        if (this.c == null) {
            this.c = new a(this.a);
        }
        if (!this.c.isShowing()) {
            this.c.show();
        }
    }

    protected void b() {
        if (this.c != null && this.c.isShowing()) {
            this.c.dismiss();
        }
    }

    public void c() {
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        if (DataEyeGetPushEasySelfCalibration.getInstance().isGetted()) {
            onEventMainThread(DataEyeGetPushEasySelfCalibration.getInstance());
        }
    }

    public void d() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }
}
