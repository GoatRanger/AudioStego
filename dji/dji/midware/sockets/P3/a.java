package dji.midware.sockets.P3;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.sockets.a.j;
import dji.thirdparty.a.c;

public class a extends j {
    private static String g = "192.168.1.3";
    private static String h = "9001";
    private static a i;

    public enum a {
        Connected,
        Disconnected
    }

    private a() {
        super(g, h);
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (i == null) {
                i = new a();
            }
            aVar = i;
        }
        return aVar;
    }

    public static void a() {
        if (i != null) {
            i.destroy();
        }
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public void destroy() {
        super.destroy();
        i = null;
    }

    public void onDisconnect() {
        f.getInstance().onDisconnect();
    }

    public void onConnect() {
        f.getInstance().onConnect();
        c.a().e(a.Connected);
    }

    public void a(byte[] bArr, int i) {
        DJIVideoPackManager.getInstance().parseData(bArr, 0, i);
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
