package dji.midware.sockets.P3;

import android.util.Log;
import dji.logic.f.d;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.k;
import dji.midware.data.manager.P3.p;
import dji.midware.f.a;
import dji.midware.f.b;
import dji.midware.natives.UDT;
import dji.thirdparty.a.c;

public class f implements k {
    private static f a = null;
    private static d b = null;
    private static b c = null;
    private static c d = null;
    private static a e = null;
    private static SwUdpService f = null;
    private int g = 0;

    private f() {
        f = SwUdpService.getInstance();
        b = d.getInstance();
        c = b.getInstance();
        d = c.getInstance();
        e = a.getInstance();
        startStream();
    }

    public static synchronized f getInstance() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }

    public static void a() {
        if (a != null) {
            a.destroy();
        }
    }

    public static void b() {
        a();
        new Thread(new Runnable() {
            public void run() {
                UDT.cleanup();
            }
        }).start();
    }

    public void destroy() {
        if (b != null) {
            b.destroy();
            b = null;
        }
        Log.e("daemon", "destroy wifi 1");
        if (c != null) {
            c.destroy();
            c = null;
        }
        Log.e("daemon", "destroy wifi 2");
        if (d != null) {
            d.destroy();
            d = null;
        }
        Log.e("daemon", "destroy wifi 3");
        if (e != null) {
            e.destroy();
            e = null;
        }
        Log.e("daemon", "destroy wifi 4");
        if (f != null) {
            Log.e("daemon", "destroy wifi 4");
            a = null;
        } else {
            Log.e("daemon", "destroy wifi 4");
            a = null;
        }
    }

    public void sendmessage(byte[] bArr) {
        if (d.a(null)) {
            if (c != null && c.isOK()) {
                c.sendmessage(bArr);
            } else if (b != null) {
                b.sendmessage(bArr);
            }
        } else if (f != null && f.isConnected()) {
            f.sendmessage(bArr);
        } else if (c == null) {
        } else {
            if (DeviceType.isGround(bArr[5] & 31)) {
                if (c != null) {
                    c.sendmessage(bArr);
                }
            } else if (b != null) {
                b.sendmessage(bArr);
            }
        }
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public boolean isConnected() {
        boolean isConnected;
        if (b != null) {
            isConnected = b.isConnected();
        } else {
            isConnected = false;
        }
        boolean isConnected2;
        if (c != null) {
            isConnected2 = c.isConnected();
        } else {
            isConnected2 = false;
        }
        boolean isConnected3;
        if (f != null) {
            isConnected3 = f.isConnected();
        } else {
            isConnected3 = false;
        }
        if (isConnected || r2 || r3) {
            return true;
        }
        return false;
    }

    public boolean isOK() {
        return isConnected();
    }

    public boolean isRemoteOK() {
        if (f != null && f.isConnected()) {
            return true;
        }
        if (d.a(null)) {
            return isOK();
        }
        return b.isOK();
    }

    public void setDataMode(boolean z) {
    }

    public void pauseRecvThread() {
    }

    public void resumeRecvThread() {
    }

    public void pauseParseThread() {
    }

    public void resumeParseThread() {
    }

    public void pauseService(boolean z) {
    }

    public synchronized void onDisconnect() {
        this.g--;
        if (this.g == 0) {
            a.getInstance().a(b.f);
            c.a().e(p.a);
        }
    }

    public synchronized void onConnect() {
        if (this.g == 0) {
            a.getInstance().a(b.e);
            c.a().e(p.b);
        }
        this.g++;
    }
}
