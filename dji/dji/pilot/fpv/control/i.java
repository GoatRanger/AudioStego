package dji.pilot.fpv.control;

import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycGetPushFlycInstallError;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;

public class i {
    private static i a = new i();
    private c b = null;
    private c c = null;
    private c d = null;
    private c e = null;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;

    public static i getInstance() {
        return a;
    }

    private i() {
    }

    public void a() {
        DJILogHelper.getInstance().LOGE("tracklog", "start init install warning");
        b();
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        onEventBackgroundThread(DataFlycGetPushFlycInstallError.getInstance());
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
    }

    private void b() {
        this.b = b.f(e.cV);
        this.c = b.f(e.cW);
        this.d = b.f(e.cX);
        this.e = b.f(e.cY);
    }

    private void a(c cVar) {
        if (cVar.equals(this.b)) {
            if (!this.f) {
                this.f = true;
                a((int) R.string.fpv_flyc_device_install_yaw_install_error);
            }
        } else if (cVar.equals(this.c)) {
            if (!this.g) {
                this.g = true;
                a((int) R.string.fpv_flyc_device_install_mass_center_error);
            }
        } else if (cVar.equals(this.d)) {
            if (!this.h) {
                this.h = true;
                a((int) R.string.fpv_flyc_device_install_vibration_error);
            }
        } else if (cVar.equals(this.e) && !this.i) {
            this.i = true;
            a((int) R.string.fpv_flyc_device_install_hover_thrust_low_error);
        }
    }

    private void a(int i) {
        DJIErrorPopView.b.b(d.b, i, 0, DJIErrorPopView.c.c, f.a);
    }

    public void onEventBackgroundThread(DataFlycGetPushFlycInstallError dataFlycGetPushFlycInstallError) {
        if (dataFlycGetPushFlycInstallError.getYawInstallErrorLevel() >= 2) {
            a(this.b);
        }
        if (dataFlycGetPushFlycInstallError.getRollInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getPitchInstallErrorLevel() >= 2) {
            a(this.c);
        }
        if (dataFlycGetPushFlycInstallError.getGyroXInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getGyroYInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getGyroZInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getAccXInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getAccYInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getAccZInstallErrorLevel() >= 2) {
            a(this.d);
        }
        if (dataFlycGetPushFlycInstallError.getThrustInstallErrorLevel() >= 2) {
            a(this.e);
        }
    }
}
