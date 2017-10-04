package dji.midware.usb.P3;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.log.WM220LogUtil;
import dji.midware.a.a;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.k;
import dji.midware.g.a.c;
import dji.midware.g.a.d;
import dji.midware.g.a.e;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.natives.FPVController;
import dji.midware.sdr.log.DJISdrLogDataReciever;
import dji.midware.util.a.b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.msgpack.core.MessagePack.Code;

public class UsbAccessoryService implements k {
    private static final int E = 300;
    public static boolean a = true;
    private static UsbAccessoryService d = null;
    private static boolean e = true;
    private static DJIUsbAccessoryReceiver g;
    private int A = 0;
    private long B = -1;
    private long C = 0;
    private long D = 0;
    private ArrayList<byte[]> F = new ArrayList(300);
    private ArrayList<Integer> G = new ArrayList(300);
    private int H = 0;
    private int I = 0;
    private int J = 30720;
    private long K = 0;
    private long L = 0;
    private boolean M = false;
    private boolean N = false;
    private boolean O = false;
    int b = 0;
    boolean c = true;
    private final String f = getClass().getSimpleName();
    private Thread h;
    private Thread i;
    private g j;
    private boolean k;
    private boolean l;
    private InputStream m;
    private OutputStream n;
    private boolean o;
    private boolean p;
    private c q;
    private d r;
    private final boolean s = true;
    private int t = 0;
    private final boolean u = false;
    private File v = new File("/sdcard/aoa_recv.bin");
    private FileOutputStream w;
    private final boolean x = false;
    private File y = new File("/sdcard/aoa_dowon.bin");
    private FileOutputStream z;

    private class ParseVideoRunnable implements Runnable {
        public static final String TAG = "VideoStream_Parse_Thread";

        private ParseVideoRunnable() {
        }

        public void run() {
            boolean z = true;
            UsbAccessoryService.this.l = true;
            UsbAccessoryService usbAccessoryService = UsbAccessoryService.this;
            StringBuilder append = new StringBuilder().append("ParseVideoRunnable ");
            if (!(UsbAccessoryService.g != null && UsbAccessoryService.g.g() && UsbAccessoryService.this.l)) {
                z = false;
            }
            usbAccessoryService.a(append.append(z).toString());
            long j = -1;
            while (UsbAccessoryService.g != null && UsbAccessoryService.g.g() && UsbAccessoryService.this.l) {
                long uptimeMillis;
                if (UsbAccessoryService.a) {
                    if (j == -1) {
                        j = SystemClock.uptimeMillis();
                    }
                    if (SystemClock.uptimeMillis() - j > 1000) {
                        uptimeMillis = SystemClock.uptimeMillis();
                        Thread.sleep(0, 1000);
                        if (UsbAccessoryService.this.I != UsbAccessoryService.this.H) {
                            j = uptimeMillis;
                        } else {
                            UsbAccessoryService.this.a((byte[]) UsbAccessoryService.this.F.get(UsbAccessoryService.this.I), ((Integer) UsbAccessoryService.this.G.get(UsbAccessoryService.this.I)).intValue());
                            if (UsbAccessoryService.this.I != UsbAccessoryService.this.F.size() - 1) {
                                UsbAccessoryService.this.I = 0;
                            } else {
                                UsbAccessoryService.this.I = UsbAccessoryService.this.I + 1;
                            }
                            j = uptimeMillis;
                        }
                    }
                }
                uptimeMillis = j;
                try {
                    Thread.sleep(0, 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (UsbAccessoryService.this.I != UsbAccessoryService.this.H) {
                    UsbAccessoryService.this.a((byte[]) UsbAccessoryService.this.F.get(UsbAccessoryService.this.I), ((Integer) UsbAccessoryService.this.G.get(UsbAccessoryService.this.I)).intValue());
                    if (UsbAccessoryService.this.I != UsbAccessoryService.this.F.size() - 1) {
                        UsbAccessoryService.this.I = UsbAccessoryService.this.I + 1;
                    } else {
                        UsbAccessoryService.this.I = 0;
                    }
                    j = uptimeMillis;
                } else {
                    j = uptimeMillis;
                }
            }
            UsbAccessoryService.this.i = null;
            UsbAccessoryService.this.a("ParseVideoRunnable.end");
        }
    }

    private class RecvBufferRunnable implements Runnable {
        public static final String TAG = "Hybrid_Recieve_Thread";
        int frame_count;

        private RecvBufferRunnable() {
            this.frame_count = 0;
        }

        public void run() {
            boolean z = true;
            Process.setThreadPriority(-1);
            UsbAccessoryService.this.k = true;
            UsbAccessoryService usbAccessoryService = UsbAccessoryService.this;
            StringBuilder append = new StringBuilder().append("RecvOsdRunnable ");
            if (!(UsbAccessoryService.g.g() && UsbAccessoryService.this.k)) {
                z = false;
            }
            usbAccessoryService.a(append.append(z).toString());
            byte[] bArr = new byte[8192];
            long j = -1;
            while (UsbAccessoryService.g != null && UsbAccessoryService.g.g() && UsbAccessoryService.this.k && UsbAccessoryService.this.m != null) {
                if (UsbAccessoryService.a) {
                    if (j == -1) {
                        j = SystemClock.uptimeMillis();
                    }
                    if (SystemClock.uptimeMillis() - j > 1000) {
                        j = SystemClock.uptimeMillis();
                    }
                }
                if (UsbAccessoryService.this.O) {
                    try {
                        Thread.sleep(0, 200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        int read = UsbAccessoryService.this.m.read(bArr);
                        UsbAccessoryService.this.t = 0;
                        if (read > 0) {
                            if (a.d().c() && a.d().a()) {
                                a.d().a(bArr, read);
                            }
                            UsbAccessoryService.this.a(read);
                            if (b.a) {
                                try {
                                    b.getInstance().c.append(String.format("   [HybridReceived] word 0: %X word 1: %X word 2: %X size=%d time=%d \n", new Object[]{Integer.valueOf(dji.midware.util.c.b(bArr, 0)), Integer.valueOf(dji.midware.util.c.b(bArr, 4)), Integer.valueOf(dji.midware.util.c.b(bArr, 8)), Integer.valueOf(read), Long.valueOf(System.currentTimeMillis())}));
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (dji.midware.util.a.c.h) {
                                dji.midware.util.a.c.getInstance(dji.midware.util.a.c.i).a(bArr, 0, read);
                            }
                            if (UsbAccessoryService.this.c) {
                                UsbAccessoryService.this.r.a(bArr, 0, read);
                            }
                        } else if (read < 0) {
                            UsbAccessoryService.this.a("osdEndpoint recv err");
                        }
                    } catch (IOException e22) {
                        Log.d("socketclose", "client close : " + System.currentTimeMillis());
                        UsbAccessoryService.this.t = UsbAccessoryService.this.t + 1;
                        if (UsbAccessoryService.this.t % 10 == 0) {
                            UsbAccessoryService.this.k = false;
                            UsbAccessoryService.this.m = null;
                            UsbAccessoryService.this.n = null;
                            UsbAccessoryService.g.f();
                            UsbAccessoryService.this.a("RecvOsdRunnable IOException " + e22.getMessage());
                        }
                    }
                }
            }
            UsbAccessoryService.this.h = null;
            UsbAccessoryService.this.a("recvBufferThread.end");
        }
    }

    public static synchronized UsbAccessoryService getInstance() {
        UsbAccessoryService usbAccessoryService;
        synchronized (UsbAccessoryService.class) {
            if (d == null) {
                d = new UsbAccessoryService();
            }
            usbAccessoryService = d;
        }
        return usbAccessoryService;
    }

    public static void a() {
        if (d != null) {
            d.destroy();
        }
    }

    public static void b() {
        if (d != null) {
            if (g != null) {
                g.e();
            }
            d.destroy();
        }
    }

    public UsbAccessoryService() {
        startStream();
        this.j = g.getInstance();
        e eVar = new e();
        eVar.a = new byte[]{(byte) 85, Code.UINT8};
        eVar.b = 6;
        this.r = new d(dji.midware.media.b.a, eVar, new d.a() {
            private int dataLen = 0;
            private short dataType = (short) 0;

            public int parseSecondHeader(byte[] bArr, int i, int i2) {
                this.dataType = dji.midware.util.c.a(bArr, i);
                this.dataLen = dji.midware.util.c.b(bArr, i + 2);
                if (this.dataType <= (short) 22349 && this.dataType >= (short) 22345) {
                    return this.dataLen;
                }
                UsbAccessoryService usbAccessoryService = UsbAccessoryService.this;
                usbAccessoryService.b++;
                DJILogHelper.getInstance().LOGD(UsbAccessoryService.this.f, "错包数量=" + UsbAccessoryService.this.b);
                return -1;
            }

            public void onGetBody(byte[] bArr, int i, int i2) {
                dji.midware.util.a.a.getInstance("UsbAccessoryService.onGetBody").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
                if (this.dataType == (short) 22346) {
                    dji.midware.util.a.a.getInstance("UsbAccessoryService.onGetBody(dataType==22346)").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
                    if (UsbAccessoryService.this.o && !UsbAccessoryService.this.M) {
                        switch (a.getInstance().d()) {
                            case DEFAULT:
                                dji.midware.util.a.a.getInstance("UsbAccessoryService.onGetBody(mode=defaule)").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
                                UsbAccessoryService.this.b(bArr, i, i2);
                                return;
                            case SINGLE:
                                dji.midware.util.a.a.getInstance("UsbAccessoryService.onGetBody(mode=single)").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
                                UsbAccessoryService.this.b(bArr, i, i2);
                                return;
                            case DUAL:
                                dji.midware.util.a.a.getInstance("UsbAccessoryService.onGetBody(mode=dual)").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
                                UsbAccessoryService.this.a(bArr, i, i2);
                                return;
                            default:
                                return;
                        }
                    }
                } else if (this.dataType == (short) 22347) {
                    if (UsbAccessoryService.this.o && !UsbAccessoryService.this.M && a.getInstance().b() == a.c.EXT) {
                        UsbAccessoryService.this.b(bArr, i, i2);
                    }
                    DJISdrLogDataReciever.getInstance().onAoaRecvLogPort1(bArr, i, i2);
                } else if (this.dataType == (short) 22345) {
                    if (UsbAccessoryService.this.c) {
                        UsbAccessoryService.this.j.parse(bArr, i, i2);
                    }
                } else if (this.dataType == (short) 22348) {
                    DJISdrLogDataReciever.getInstance().onAoaRecvLogPort2(bArr, i, i2);
                } else if (this.dataType == (short) 22349) {
                    DJISdrLogDataReciever.getInstance().onAoaRecvLogPort3(bArr, i, i2);
                }
            }
        });
        this.q = new c(new c.b() {
            public void onRecv(int i, byte[] bArr, int i2, int i3) {
                if (i == c.a.LiveView.a()) {
                    if (ServiceManager.getInstance().e() != null && !ServiceManager.getInstance().i() && a.getInstance().c() == a.a.HDMI) {
                        UsbAccessoryService.this.c(bArr, i2, i3);
                    }
                } else if (i == c.a.FileDownload.a()) {
                    DJIVideoPackManager.getInstance().parseData(bArr, i2, i3);
                } else if (i == c.a.SecondaryLiveView.a() && ServiceManager.getInstance().e() != null && !ServiceManager.getInstance().i() && a.getInstance().c() == a.a.AV) {
                    UsbAccessoryService.this.c(bArr, i2, i3);
                }
            }
        });
    }

    public static void registerAoaReceiver(Context context) {
        Context applicationContext = context.getApplicationContext();
        g = new DJIUsbAccessoryReceiver();
        g.a(applicationContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DJIUsbAccessoryReceiver.a);
        intentFilter.addAction("android.hardware.usb.action.USB_ACCESSORY_DETACHED");
        intentFilter.addAction(DJIUsbAccessoryReceiver.ACTION_USB_ACCESSORY_ATTACHED);
        applicationContext.registerReceiver(g, intentFilter);
    }

    public void destroy() {
        this.m = null;
        this.n = null;
        d = null;
        this.t = 0;
        this.k = false;
        this.l = false;
        Log.e(this.f, "final destroy() 71");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(this.f, "final destroy() 72");
        if (g != null) {
            g.f();
        }
        Log.e(this.f, "final destroy() 73");
        if (dji.midware.util.a.c.c) {
            dji.midware.util.a.c.getInstance(dji.midware.util.a.c.d).a();
        }
        Log.e(this.f, "final destroy() 75");
        this.h = null;
        this.i = null;
        Log.e(this.f, "final destroy() 76");
    }

    public void startStream() {
        this.o = true;
        Log.d(this.f, "accessory startStream");
    }

    public void stopStream() {
        this.o = false;
        Log.d(this.f, "accessory stopStream");
    }

    protected void c() {
        onConnect();
        this.m = g.b();
        this.n = g.c();
        if (this.m != null) {
            g();
            if (e) {
                d();
            }
        }
    }

    private void g() {
        if (this.h == null) {
            this.h = new Thread(new RecvBufferRunnable(), "recvBufferThread");
            this.h.setPriority(9);
            this.h.start();
            a("recvBufferThread.start");
        }
    }

    public void d() {
        if (this.F.size() == 0) {
            for (int i = 0; i < 300; i++) {
                this.F.add(new byte[this.J]);
                this.G.add(Integer.valueOf(0));
            }
        }
        if (this.i == null) {
            this.i = new Thread(new ParseVideoRunnable(), "parseVideoThread");
            this.i.start();
            a("parseVideoThread.start");
        }
    }

    public void e() {
        boolean z;
        if (this.c) {
            z = false;
        } else {
            z = true;
        }
        this.c = z;
        DJILogHelper.getInstance().LOGD(this.f, "isParse=" + this.c, false, true);
    }

    public void a(byte[] bArr) {
        a(bArr.length);
        this.r.a(bArr, 0, bArr.length);
    }

    private boolean h() {
        ProductType c = i.getInstance().c();
        return c == ProductType.KumquatX || c == ProductType.KumquatS;
    }

    private void b(byte[] bArr, int i, int i2) {
        dji.midware.util.a.a.getInstance("UsbAccessoryService.handleOldMethod").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
        if (!this.p || h()) {
            dji.midware.util.a.a.getInstance("UsbAccessoryService.handleOldMethod(is live stream)").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
            if (ServiceManager.getInstance().e() != null) {
                c(bArr, i, i2);
                return;
            }
            return;
        }
        DJIVideoPackManager.getInstance().parseData(bArr, i, i2);
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.q != null) {
            dji.midware.util.a.a.getInstance("UsbAccessoryService.handleNewMethod").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
            this.q.a(bArr, i, i2);
        }
    }

    private void a(byte[] bArr, int i) {
        if (ServiceManager.getInstance().j()) {
            dji.midware.util.a.a.getInstance("UsbAccessoryService.toTransVideoData(need packed)").a(dji.midware.util.a.a.a, Integer.valueOf(i));
            if (ServiceManager.getInstance().k()) {
                DJIVideoDataRecver.getInstance().onVideoRecv(bArr, i, true);
            } else {
                FPVController.native_transferVideoData(bArr, i);
            }
            if (b.a) {
                try {
                    if (this.B == -1) {
                        this.B = SystemClock.uptimeMillis();
                    }
                    String format = String.format("[After Sending To FFMpeg] word 0: %X word 1: %X word 2: %X size=%d time=%d \n", new Object[]{Integer.valueOf(dji.midware.util.c.b(bArr, 0)), Integer.valueOf(dji.midware.util.c.b(bArr, 4)), Integer.valueOf(dji.midware.util.c.b(bArr, 8)), Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())});
                    this.C += (long) i;
                    this.D++;
                    if (i != 2048) {
                        long uptimeMillis = SystemClock.uptimeMillis() - this.B;
                        format = format + String.format("frameReceiveDelay=%d frame_size=%d num_packet=%d", new Object[]{Long.valueOf(uptimeMillis), Long.valueOf(this.C), Long.valueOf(this.D)});
                        this.B = -1;
                        this.C = 0;
                        this.D = 0;
                    }
                    b.getInstance().c.append(format + "\n");
                    this.A = (this.A + 1) % 100;
                    if (this.A == 0) {
                        b.getInstance().c.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dji.midware.util.a.c.c) {
                dji.midware.util.a.c.getInstance(dji.midware.util.a.c.d).a(bArr, 0, i);
                return;
            }
            return;
        }
        dji.midware.util.a.a.getInstance("UsbAccessoryService.toTransVideoData(no need to pack)").a(dji.midware.util.a.a.a, Long.valueOf(this.L));
        DJIVideoDataRecver.getInstance().onVideoRecv(bArr, i, true);
    }

    private void c(byte[] bArr, int i, int i2) {
        if (e) {
            this.G.set(this.H, Integer.valueOf(i2));
            Object obj = (byte[]) this.F.get(this.H);
            if (i2 > obj.length) {
                DJILogHelper.getInstance().LOGD(this.f, "putVideoBuffer length超过100k=" + ((((float) i2) * 1.0f) / 1024.0f), false, true);
                obj = new byte[i2];
                this.F.set(this.H, obj);
                System.arraycopy(bArr, i, obj, 0, i2);
            } else {
                if (i2 < this.J && obj.length > this.J) {
                    DJILogHelper.getInstance().LOGD(this.f, "putVideoBuffer length超过100k=" + ((((float) i2) * 1.0f) / 1024.0f), false, true);
                    obj = new byte[this.J];
                    this.F.set(this.H, obj);
                }
                System.arraycopy(bArr, i, obj, 0, i2);
            }
            if (this.I - this.H > 0 && this.I - this.H < 4) {
                WM220LogUtil.LOGI("***getVideoIndex: " + this.I + " setVideoIndex: " + this.H + " length: " + i2);
            }
            if (this.H == this.F.size() - 1) {
                this.H = 0;
                return;
            }
            this.H++;
            if (this.H == this.I) {
                WM220LogUtil.LOGD("**set catch get");
                return;
            }
            return;
        }
        a(bArr, i2);
    }

    public synchronized void sendmessage(byte[] bArr) {
        if (this.n != null) {
            try {
                synchronized (this.n) {
                    this.n.write(bArr, 0, bArr.length);
                    this.n.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return g != null && g.g();
    }

    public boolean isOK() {
        return isConnected();
    }

    private void a(int i) {
        this.L += (long) i;
        if (i() - this.K > 2000) {
            if ((((float) this.L) * dji.pilot.visual.a.d.c) / 1024.0f > 1024.0f) {
                a(String.format("rate %.2f MB\n", new Object[]{Float.valueOf(((((float) this.L) * dji.pilot.visual.a.d.c) / 1024.0f) / 1024.0f)}));
            } else {
                a(String.format("rate %.2f KB\n", new Object[]{Float.valueOf((((float) this.L) * dji.pilot.visual.a.d.c) / 1024.0f)}));
            }
            this.K = i();
            this.L = 0;
        }
    }

    private long i() {
        return System.currentTimeMillis();
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGE(this.f, str, false, true);
    }

    public boolean isRemoteOK() {
        return this.j.c();
    }

    public void setDataMode(boolean z) {
        this.p = z;
    }

    public void pauseService(boolean z) {
        if (this.O != z) {
            this.O = z;
            if (this.O) {
                g.getInstance().a();
            } else {
                g.getInstance().b();
            }
        }
    }

    public void pauseRecvThread() {
        this.M = true;
    }

    public void resumeRecvThread() {
        this.M = false;
    }

    public void pauseParseThread() {
        this.N = true;
    }

    public void resumeParseThread() {
        this.N = false;
    }

    public void onDisconnect() {
        this.m = null;
        this.n = null;
        d = null;
        this.t = 0;
        this.k = false;
        this.l = false;
        this.h = null;
        this.i = null;
        dji.midware.f.a.getInstance().a(dji.midware.f.b.f);
    }

    public void onConnect() {
        dji.midware.f.a.getInstance().a(dji.midware.f.b.a);
    }
}
