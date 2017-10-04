package dji.midware.f;

import android.os.Handler;
import android.os.HandlerThread;
import dji.log.DJILogHelper;
import dji.midware.b.c;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.k;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.natives.FPVController;
import dji.midware.sockets.P3.e;
import dji.midware.sockets.P3.f;
import dji.midware.usb.P3.UsbAccessoryService;
import dji.midware.usbhost.P3.UsbHostServiceRC;

public class a {
    private static a a = null;
    private Handler b;
    private volatile boolean c;
    private boolean d = false;
    private b e = b.NON;
    private k f;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    private a() {
        HandlerThread handlerThread = new HandlerThread("DJILinkDaemonService");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper(), new 1(this));
        this.c = true;
        this.b.sendEmptyMessage(0);
    }

    public void a() {
        if (!this.d) {
            UsbAccessoryService.getInstance();
            if (!UsbAccessoryService.getInstance().isConnected()) {
                f.getInstance();
                UsbHostServiceRC.getInstance().resume();
            } else {
                return;
            }
        }
        c.getInstance();
    }

    private void f() {
        DJILogHelper.getInstance().LOGE("", "stopDaemon " + this.e, false, false);
        if (this.e != b.AOA) {
            UsbAccessoryService.a();
        }
        if (this.e != b.ADB) {
            e.a();
        }
        if (this.e != b.WIFI) {
            f.a();
        }
        if (this.e != b.BLE) {
            c.h();
        }
        if (this.e != b.HOSTRC) {
            UsbHostServiceRC.Pause();
        }
    }

    public void b() {
        if (!this.d) {
            FPVController.native_setVideoDataRecver(DJIVideoDataRecver.getInstance());
            FPVController.native_setVideoPackObject(DJIVideoPackManager.getInstance());
        }
    }

    public void c() {
        this.c = false;
        if (!this.d) {
            UsbAccessoryService.b();
            e.a();
            f.b();
        }
        c.h();
    }

    public b d() {
        return this.e;
    }

    private void a(k kVar) {
        this.f = kVar;
        f();
        ServiceManager.getInstance().a(kVar);
        dji.thirdparty.a.c.a().e(this.e);
    }

    public k e() {
        return this.f;
    }

    public void a(b bVar) {
        this.e = bVar;
        if (this.b.hasMessages(0)) {
            this.b.removeMessages(0);
        }
        if (this.c) {
            switch (2.a[bVar.ordinal()]) {
                case 1:
                    g.getInstance().a(true);
                    a(UsbAccessoryService.getInstance());
                    break;
                case 2:
                    g.getInstance().a(true);
                    a(e.getInstance());
                    break;
                case 3:
                    g.getInstance().a(false);
                    a(f.getInstance());
                    break;
                case 4:
                    g.getInstance().a(true);
                    a(dji.midware.usbhost.P3.a.getInstance());
                    break;
                case 5:
                    g.getInstance().a(true);
                    a(UsbHostServiceRC.getInstance());
                    break;
                case 6:
                    g.getInstance().a(false);
                    a(c.getInstance());
                    break;
                case 7:
                    this.b.sendEmptyMessageDelayed(0, 1000);
                    break;
            }
            DJILogHelper.getInstance().LOGD("", "linkType=" + bVar, false, true);
        }
    }

    public void a(boolean z) {
        this.d = z;
    }
}
