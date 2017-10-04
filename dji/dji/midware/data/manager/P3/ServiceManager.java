package dji.midware.data.manager.P3;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanFilter.Builder;
import android.content.Context;
import android.os.ParcelUuid;
import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.data.config.P3.p;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.a;
import dji.midware.natives.FPVController;
import dji.midware.sdr.log.DJISdrLogDataReciever;
import dji.midware.stat.StatService;
import dji.midware.usb.P3.UsbAccessoryService;
import dji.midware.usbhost.P3.UsbHostServiceRC;
import dji.midware.util.a.d;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager implements k {
    private static ServiceManager d = null;
    private static Context e = null;
    private static volatile boolean f = false;
    private static final int m = 10;
    protected String a = getClass().getSimpleName();
    protected DJIVideoDecoder b;
    protected a c;
    private k g = null;
    private boolean h = false;
    private boolean i = false;
    private boolean j = true;
    private boolean k = false;
    private boolean l = false;

    public static synchronized ServiceManager getInstance() {
        ServiceManager serviceManager;
        synchronized (ServiceManager.class) {
            if (d == null) {
                d = new ServiceManager();
            }
            serviceManager = d;
        }
        return serviceManager;
    }

    public static void Destroy() {
        if (d != null) {
            d.destroy();
        }
    }

    public static boolean isAlive() {
        return f;
    }

    public static void setContext(Context context) {
        e = context;
    }

    public ServiceManager() {
        f = true;
        d.a(e);
    }

    public void a() {
        c.a().a((Object) this);
        d.a(e);
        e.a(e);
        if (!this.l) {
            FPVController.native_init(e);
        }
        i.build(e);
        dji.midware.b.c.a(e);
        DJISdrLogDataReciever.getInstance(e);
        b.getInstance();
        dji.midware.f.a.getInstance();
        dji.midware.f.a.getInstance().a(this.l);
    }

    public static Context getContext() {
        return e;
    }

    public synchronized void b() {
        dji.logic.album.a.b.getInstance(e);
        dji.midware.media.d.a(e);
        StatService.setContext(e);
        dji.midware.f.a.getInstance().a(this.l);
        dji.midware.f.a.getInstance().b();
        dji.midware.b.c.getInstance().a(10);
        UsbHostServiceRC.getInstance().start(e);
        if (!this.l) {
            dji.logic.c.a.getInstance();
            m();
        }
    }

    @TargetApi(21)
    private List<ScanFilter> l() {
        ScanFilter build = new Builder().setServiceUuid(ParcelUuid.fromString(dji.midware.b.a.a)).setManufacturerData(58816, new byte[]{(byte) 0}).build();
        List<ScanFilter> arrayList = new ArrayList(1);
        arrayList.add(build);
        return arrayList;
    }

    public void pauseService(boolean z) {
        if (this.g != null) {
            this.g.pauseService(z);
        }
    }

    public void pauseRecvThread() {
        if (this.g != null) {
            this.g.pauseRecvThread();
        }
    }

    public void resumeRecvThread() {
        if (this.g != null) {
            this.g.resumeRecvThread();
        }
    }

    public void pauseParseThread() {
        if (this.g != null) {
            this.g.pauseParseThread();
        }
        FPVController.native_pauseParseThread(true);
    }

    public void c() {
        DJIVideoDecoder e = e();
        if (e != null) {
            e.resetKeyFrame();
        }
    }

    public void resumeParseThread() {
        if (this.g != null) {
            this.g.resumeParseThread();
        }
        FPVController.native_pauseParseThread(false);
    }

    private void m() {
        FPVController.native_startParseThread();
    }

    private void n() {
        FPVController.native_stopParseThread();
    }

    @Deprecated
    public void a(Object obj) {
        this.b = (DJIVideoDecoder) obj;
        FPVController.native_setCallObject(obj);
    }

    public void a(DJIVideoDecoder dJIVideoDecoder) {
        this.b = dJIVideoDecoder;
        FPVController.native_setCallObject(dJIVideoDecoder);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void d() {
        if (this.c == null) {
            a(new a(a.a.a, e.getApplicationContext()));
        }
    }

    public void a(int i) {
        if (!this.l) {
            FPVController.native_setDecoderType(i);
        }
    }

    public DJIVideoDecoder e() {
        return this.b;
    }

    public boolean f() {
        return this.b != null && this.b.isDecoderOK();
    }

    public boolean g() {
        return this.b != null && this.b.isHasVideoData();
    }

    public a h() {
        return this.c;
    }

    public void a(boolean z) {
        this.h = z;
        FPVController.native_setIsFixRate(z);
    }

    public boolean i() {
        return this.h;
    }

    public void startStream() {
    }

    public void stopStream() {
    }

    public void sendmessage(byte[] bArr) {
        if (this.g != null) {
            this.g.sendmessage(bArr);
        }
    }

    public boolean isConnected() {
        if (this.g == null) {
            return false;
        }
        return this.g.isOK();
    }

    public void setDataMode(boolean z) {
        if (this.i != z) {
            if (this.g != null) {
                this.g.setDataMode(z);
            }
            this.i = z;
        }
    }

    public boolean isOK() {
        if (this.g == null) {
            return false;
        }
        return this.g.isOK();
    }

    public boolean isRemoteOK() {
        if (this.g == null) {
            return false;
        }
        return this.g.isRemoteOK();
    }

    public void destroy() {
        f = false;
        if (!this.l) {
            n();
            FPVController.native_unInit();
        }
        dji.midware.f.a.getInstance().c();
        c.a().d((Object) this);
        c.a().d();
        d = null;
    }

    public void onEventBackgroundThread(dji.publics.DJIObject.b.a aVar) {
        switch (1.a[aVar.ordinal()]) {
            case 1:
                pauseService(true);
                UsbAccessoryService.a();
                return;
            default:
                return;
        }
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD(this.a, str, false, false);
    }

    public void b(String str) {
        DJILogHelper.getInstance().LOGE(this.a, str, false, false);
    }

    public dji.midware.data.b.a.b a(p pVar) {
        return j.getInstance().a(pVar.a());
    }

    public dji.midware.data.b.a.b b(int i) {
        return j.getInstance().a(i);
    }

    public void b(boolean z) {
        this.j = z;
        FPVController.native_setIsNeedPacked(z);
    }

    public boolean j() {
        return this.j;
    }

    public void c(boolean z) {
        this.k = z;
        FPVController.native_setIsNeedRawData(z);
    }

    public boolean k() {
        return this.k;
    }

    public void a(k kVar) {
        this.g = kVar;
    }

    public void onDisconnect() {
        if (this.b != null) {
            this.b.freshDecodeStatus(500);
        }
    }

    public void onConnect() {
    }

    public void d(boolean z) {
        this.l = z;
    }
}
