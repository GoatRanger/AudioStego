package dji.midware.sockets.P3;

import com.google.android.gms.location.places.PlacesStatusCodes;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.o;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.natives.FPVController;
import dji.midware.natives.UDT;
import dji.midware.sockets.a.h;
import dji.midware.util.a.a;
import dji.thirdparty.a.c;

public class SwUdpService extends h {
    private static SwUdpService instance;
    private static String ip = "192.168.2.1";
    private static int port = PlacesStatusCodes.ACCESS_NOT_CONFIGURED;
    private boolean isConnected = false;
    private g packManager = g.getInstance();

    private SwUdpService() {
        super(ip, port);
        UDT.setSwRecver(this);
    }

    public static synchronized SwUdpService getInstance() {
        SwUdpService swUdpService;
        synchronized (SwUdpService.class) {
            if (instance == null) {
                instance = new SwUdpService();
            }
            swUdpService = instance;
        }
        return swUdpService;
    }

    public static void Destroy() {
        if (instance != null) {
            instance.destroy();
        }
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public void destroy() {
        super.destroy();
        stopStream();
        instance = null;
    }

    public boolean isOK() {
        return false;
    }

    public void onDisconnect() {
        c.a().e(o.a);
        f.getInstance().onDisconnect();
    }

    public void onConnect() {
        c.a().e(o.b);
        f.getInstance().onConnect();
    }

    public void parse(int i, byte[] bArr, int i2) {
        a.getInstance("SwUdpService.parse").a(a.a, Integer.valueOf(i2));
        ServiceManager.getInstance().a(f.getInstance());
        if (i == 1) {
            this.packManager.parse(bArr, 0, i2);
        } else if (ServiceManager.getInstance().j()) {
            a.getInstance("SwUdpService.parse(stream need pack)").a(a.a, Integer.valueOf(i2));
            FPVController.native_transferVideoData(bArr, i2);
        } else {
            a.getInstance("SwUdpService.parse(stream no need pack)").a(a.a, Integer.valueOf(i2));
            if (ServiceManager.getInstance().e() != null) {
                DJIVideoDataRecver.getInstance().onVideoRecv(bArr, i2, true);
            }
        }
    }

    public boolean isConnected() {
        boolean z = false;
        try {
            z = UDT.SwUdpIsConnected();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        return z;
    }

    public void LOGD(String str) {
        DJILogHelper.getInstance().LOGD(this.TAG, str, false, false);
    }

    public void LOGE(String str) {
        DJILogHelper.getInstance().LOGE(this.TAG, str, false, false);
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
