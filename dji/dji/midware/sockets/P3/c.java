package dji.midware.sockets.P3;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.natives.FPVController;
import dji.midware.sockets.a.j;
import dji.midware.util.a.a;

public class c extends j {
    private static String g = "192.168.1.3";
    private static String h = "9000";
    private static String i = "192.168.2.1";
    private static String j = "9003";
    private static c k;

    public static void a() {
        g = i;
        h = j;
    }

    private c() {
        super(g, h);
        this.f = 900;
    }

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (k == null) {
                k = new c();
            }
            cVar = k;
        }
        return cVar;
    }

    public static void b() {
        if (k != null) {
            k.destroy();
        }
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public void destroy() {
        super.destroy();
        stopStream();
        k = null;
    }

    public void onDisconnect() {
        f.getInstance().onDisconnect();
    }

    public void onConnect() {
        f.getInstance().onConnect();
    }

    public void a(byte[] bArr, int i) {
        if (ServiceManager.getInstance().e() != null) {
            if (dji.midware.util.a.c.c) {
                dji.midware.util.a.c.getInstance(dji.midware.util.a.c.e).a(bArr, 0, i);
            }
            if (!ServiceManager.getInstance().j()) {
                DJIVideoDataRecver.getInstance().onVideoRecv(bArr, i, true);
            } else if (ServiceManager.getInstance().k()) {
                DJIVideoDataRecver.getInstance().onVideoRecv(bArr, i, true);
            } else {
                a.getInstance("P3CLiveStreamService.parse(need pack not raw)").a(a.a, Integer.valueOf(i));
                FPVController.native_transferVideoData(bArr, i);
            }
        }
    }

    public boolean isConnected() {
        return super.isConnected();
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD(this.a, str, false, false);
    }

    public void b(String str) {
        DJILogHelper.getInstance().LOGE(this.a, str, false, false);
    }

    public boolean isRemoteOK() {
        return true;
    }

    public void setDataMode(boolean z) {
    }

    public void pauseService(boolean z) {
    }

    public void pauseRecvThread() {
    }

    public void resumeRecvThread() {
    }

    public void pauseParseThread() {
    }

    public void resumeParseThread() {
    }
}
