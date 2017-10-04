package dji.midware.sockets.P3;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.f;
import dji.midware.sockets.a.i;

public class b extends i {
    private static String k = "192.168.1.1";
    private static int l = 2345;
    private static b m;
    private f n = new f();

    private b() {
        super(k, l);
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (m == null) {
                m = new b();
            }
            bVar = m;
        }
        return bVar;
    }

    public static void a() {
        if (m != null) {
            m.destroy();
        }
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public void destroy() {
        super.destroy();
        stopStream();
        m = null;
    }

    public void onDisconnect() {
        f.getInstance().onDisconnect();
    }

    public void onConnect() {
        f.getInstance().onConnect();
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
        return false;
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
