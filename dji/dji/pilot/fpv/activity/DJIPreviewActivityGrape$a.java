package dji.pilot.fpv.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.usercenter.protocol.e;
import java.lang.ref.WeakReference;

final class DJIPreviewActivityGrape$a extends Handler {
    private final WeakReference<DJIPreviewActivityGrape> a;

    public DJIPreviewActivityGrape$a(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(dJIPreviewActivityGrape);
    }

    public void handleMessage(Message message) {
        boolean z = true;
        DJIPreviewActivityGrape dJIPreviewActivityGrape = (DJIPreviewActivityGrape) this.a.get();
        if (dJIPreviewActivityGrape != null && !dJIPreviewActivityGrape.isFinishing()) {
            switch (message.what) {
                case 11:
                    i.getInstance().a(ProductType.litchiS);
                    return;
                case 12:
                    if (message.arg1 != 1) {
                        z = false;
                    }
                    DJIPreviewActivityGrape.g(dJIPreviewActivityGrape, z);
                    return;
                case 4096:
                    DJIPreviewActivityGrape.x(dJIPreviewActivityGrape);
                    return;
                case 8192:
                    DJIPreviewActivityGrape.e(dJIPreviewActivityGrape, true);
                    return;
                case e.ap /*12288*/:
                    if (message.arg1 == 0) {
                        DJIPreviewActivityGrape.y(dJIPreviewActivityGrape);
                        return;
                    } else {
                        DJIPreviewActivityGrape.a(dJIPreviewActivityGrape, message.arg1, message.arg2);
                        return;
                    }
                case 16384:
                    dJIPreviewActivityGrape.l();
                    DJIPreviewActivityGrape.e(dJIPreviewActivityGrape, false);
                    return;
                case e.ax /*24576*/:
                    DJIPreviewActivityGrape.f(dJIPreviewActivityGrape, true);
                    return;
                case 36864:
                    dJIPreviewActivityGrape.c();
                    return;
                case 36865:
                    DJIPreviewActivityGrape.z(dJIPreviewActivityGrape).a();
                    return;
                case 36866:
                    DJIPreviewActivityGrape.A(dJIPreviewActivityGrape);
                    return;
                case 36867:
                    DJIPreviewActivityGrape.B(dJIPreviewActivityGrape);
                    return;
                case 36868:
                    dJIPreviewActivityGrape.g = false;
                    return;
                case 36869:
                    DJIPreviewActivityGrape.C(dJIPreviewActivityGrape);
                    DJILogHelper.getInstance().LOGD("", "lose_osd osdchecktime=" + DJIPreviewActivityGrape.D(dJIPreviewActivityGrape) + " osdstatus=" + dJIPreviewActivityGrape.g, false, true);
                    if (DJIPreviewActivityGrape.D(dJIPreviewActivityGrape) == 9) {
                        dJIPreviewActivityGrape.a(0);
                        return;
                    } else if (dJIPreviewActivityGrape.g) {
                        sendEmptyMessageDelayed(36869, 1000);
                        return;
                    } else {
                        dJIPreviewActivityGrape.a(1);
                        return;
                    }
                case 36870:
                    dJIPreviewActivityGrape.b();
                    return;
                default:
                    return;
            }
        }
    }
}
