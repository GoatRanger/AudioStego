package dji.midware.sockets.P3;

import android.util.Log;
import com.google.android.gms.location.places.PlacesStatusCodes;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.o;
import dji.midware.sockets.a.i;
import dji.thirdparty.a.c;

public class d extends i {
    private static String k = "192.168.1.2";
    private static int l = 5678;
    private static String m = "192.168.2.1";
    private static int n = PlacesStatusCodes.KEY_INVALID;
    private static d o;
    private g p = g.getInstance();
    private long q = 0;
    private long r = 0;

    public static void a() {
        k = m;
        l = n;
    }

    private d() {
        super(k, l);
    }

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (o == null) {
                o = new d();
            }
            dVar = o;
        }
        return dVar;
    }

    public static void d() {
        if (o != null) {
            o.destroy();
        }
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public void destroy() {
        super.destroy();
        stopStream();
        o = null;
    }

    public void onDisconnect() {
        c.a().e(o.a);
        f.getInstance().onDisconnect();
    }

    public void onConnect() {
        c.a().e(o.b);
        f.getInstance().onConnect();
    }

    public void a(byte[] bArr, int i, int i2) {
        this.p.parse(bArr, i, i2);
    }

    private void a(int i) {
        this.r += (long) i;
        if (j() - this.q > 1000) {
            if ((((float) this.r) * 1.0f) / 1024.0f > 1024.0f) {
                Log.d("download", String.format("rate %.2f MB\n", new Object[]{Float.valueOf(((((float) this.r) * 1.0f) / 1024.0f) / 1024.0f)}));
            } else {
                Log.d("download", String.format("rate %.2f KB\n", new Object[]{Float.valueOf((((float) this.r) * 1.0f) / 1024.0f)}));
            }
            this.q = j();
            this.r = 0;
        }
    }

    private long j() {
        return System.currentTimeMillis();
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
