package dji.midware.sockets.P3;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.p;
import dji.midware.f.a;
import dji.midware.f.b;
import dji.midware.natives.FPVController;
import dji.midware.sdr.log.DJISdrLogDataReciever;
import dji.midware.sockets.a.g;
import dji.thirdparty.a.c;

public class e extends g {
    private static String k = j;
    private static int l = 22345;
    private static e m;
    private dji.midware.data.manager.P3.g n = dji.midware.data.manager.P3.g.getInstance();

    private e() {
        super(k, l);
        startStream();
    }

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (m == null) {
                m = new e();
            }
            eVar = m;
        }
        return eVar;
    }

    public static void a() {
        if (m != null) {
            m.destroy();
        }
    }

    public void startStream() {
        FPVController.native_startRecvThread();
    }

    public void stopStream() {
        FPVController.native_stopRecvThread();
    }

    public void destroy() {
        super.destroy();
        stopStream();
        DJISdrLogDataReciever.getInstance().onDestroy();
        m = null;
    }

    public void onDisconnect() {
        a.getInstance().a(b.f);
        c.a().e(p.a);
    }

    public void onConnect() {
        a.getInstance().a(b.d);
        c.a().e(p.b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.n.parse(bArr, i, i2);
    }

    protected boolean b() {
        return true;
    }

    public boolean isConnected() {
        return super.isConnected();
    }

    protected void c() {
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD(this.a, str, false, false);
    }

    public void b(String str) {
        DJILogHelper.getInstance().LOGE(this.a, str, false, false);
    }

    public boolean isRemoteOK() {
        return this.n.c();
    }

    public void setDataMode(boolean z) {
        FPVController.native_setDataMode(z);
    }

    public void pauseService(boolean z) {
    }

    public void pauseRecvThread() {
        FPVController.native_pauseRecvThread(true);
    }

    public void resumeRecvThread() {
        FPVController.native_pauseRecvThread(false);
    }

    public void pauseParseThread() {
    }

    public void resumeParseThread() {
    }
}
