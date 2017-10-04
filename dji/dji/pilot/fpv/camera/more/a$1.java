package dji.pilot.fpv.camera.more;

import android.os.Handler;
import android.os.Message;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetShotInfo;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.protocol.e;
import dji.thirdparty.a.c;
import java.util.Locale;

class a$1 extends Handler {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void handleMessage(Message message) {
        boolean z = false;
        switch (message.what) {
            case 4096:
                this.a.a(message.arg1);
                return;
            case 4097:
                String format = String.format(Locale.US, "%d%d%d", new Object[]{Integer.valueOf(DataCameraGetShotInfo.getInstance().getMemberId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getModelId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getHardVersion())});
                if (b.d(a.a(this.a))) {
                    a.a(this.a, g.b(a.b(this.a), format, true));
                    a aVar = this.a;
                    if (!a.c(this.a)) {
                        z = true;
                    }
                    a.b(aVar, z);
                    if (a.c(this.a)) {
                        FuselageFocusMode fuselageFocusMode = DataCameraGetPushShotInfo.getInstance().getFuselageFocusMode();
                        if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                            a.c(this.a, true);
                            c.a().e(a$a.MF_DEMARCATE);
                        }
                    } else {
                        a.a(this.a, g.b(a.b(this.a), format + "_value", a.d(this.a)));
                    }
                }
                c.a().e(a$a.CAMERAINFO_GETTED);
                return;
            case 8192:
                if (message.arg1 == 1) {
                    a.e(this.a);
                    return;
                }
                return;
            case e.an /*8193*/:
                if (1 == message.arg1) {
                    a.f(this.a);
                    return;
                }
                return;
            case 8194:
                if (1 == message.arg1) {
                    a.g(this.a);
                    return;
                }
                return;
            case 8195:
                if (1 != message.arg1) {
                    return;
                }
                return;
            default:
                return;
        }
    }
}
