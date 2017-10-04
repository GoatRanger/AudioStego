package dji.pilot.publics.control.rc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataOsdSetUpgradeTip.UPGRADETIP;
import dji.pilot.usercenter.protocol.e;
import java.lang.ref.WeakReference;

final class b$g extends Handler {
    private final WeakReference<b> a;

    public b$g(b bVar) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(bVar);
    }

    public void handleMessage(Message message) {
        b bVar = (b) this.a.get();
        if (bVar != null) {
            switch (message.what) {
                case 4096:
                    if (b.i(bVar) == b.k) {
                        b.c(bVar, message.arg1 + 1, message.arg2);
                        return;
                    }
                    return;
                case 4097:
                    b.j(bVar);
                    return;
                case 4098:
                    b.k(bVar);
                    return;
                case 4352:
                    b.l(bVar);
                    return;
                case 8192:
                    if (message.obj instanceof UPGRADETIP) {
                        b.a(bVar, (UPGRADETIP) message.obj, message.arg1, message.arg2, true);
                        return;
                    }
                    return;
                case 20480:
                    b.a(bVar, message.arg1, message.arg2, message.obj instanceof a ? (a) message.obj : a.D);
                    return;
                case e.ax /*24576*/:
                    if (message.obj instanceof DataCommonGetVersion) {
                        b.a(bVar, message.arg1, message.arg2, (DataCommonGetVersion) message.obj, false);
                        return;
                    }
                    return;
                case 39321:
                    b.a(bVar, DeviceType.FPGA_G, true);
                    sendEmptyMessageDelayed(4098, 10000);
                    return;
                default:
                    return;
            }
        }
    }
}
