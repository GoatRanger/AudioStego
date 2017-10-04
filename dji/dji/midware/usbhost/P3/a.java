package dji.midware.usbhost.P3;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.k;
import dji.midware.g.a.c;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.natives.FPVController;
import dji.midware.usb.P3.UsbAccessoryService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class a implements k {
    private static final String A = (Environment.getExternalStorageDirectory().getPath() + "usbhost.264");
    private static a a = null;
    private ArrayList<byte[]> B = new ArrayList(50);
    private ArrayList<Integer> C = new ArrayList(50);
    private int D = 0;
    private int E = 0;
    private long F = 0;
    private int G = 0;
    private final String b = getClass().getSimpleName();
    private DJIUsbReceiver c;
    private UsbDeviceConnection d;
    private UsbEndpoint e;
    private UsbEndpoint f;
    private UsbEndpoint g;
    private Thread h;
    private Thread i;
    private Thread j;
    private Thread k;
    private g l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private c q;
    private boolean r = false;
    private boolean s = false;
    private boolean t;
    private boolean u;
    private byte[] v = new byte[16384];
    private byte[] w = new byte[4096];
    private FileOutputStream x = null;
    private final boolean y = false;
    private final boolean z = true;

    private class a implements Runnable {
        public static final String a = "VideoStream_Parse_Thread";
        final /* synthetic */ a b;
        private int c = 0;
        private long d = -1;
        private long e = 0;
        private long f = 0;

        private a(a aVar) {
            this.b = aVar;
        }

        public void run() {
            long uptimeMillis;
            this.b.p = true;
            a aVar = this.b;
            StringBuilder append = new StringBuilder().append("ParseVideoRunnable ");
            boolean z = this.b.c != null && this.b.c.a() && this.b.p;
            aVar.b(append.append(z).toString());
            long j = -1;
            while (this.b.c != null && this.b.c.a() && this.b.p) {
                byte[] bArr;
                int intValue;
                String format;
                if (UsbAccessoryService.a) {
                    if (j == -1) {
                        j = SystemClock.uptimeMillis();
                    }
                    if (SystemClock.uptimeMillis() - j > 1000) {
                        uptimeMillis = SystemClock.uptimeMillis();
                        if (this.b.E != this.b.D) {
                            try {
                                Thread.sleep(0, 500);
                                j = uptimeMillis;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                j = uptimeMillis;
                            }
                        } else {
                            bArr = (byte[]) this.b.B.get(this.b.E);
                            intValue = ((Integer) this.b.C.get(this.b.E)).intValue();
                            if (ServiceManager.getInstance().j()) {
                                DJIVideoDataRecver.getInstance().onVideoRecv(bArr, intValue, true);
                            } else {
                                FPVController.native_transferVideoData(bArr, intValue);
                                if (dji.midware.util.a.b.a) {
                                    try {
                                        if (this.d == -1) {
                                            this.d = SystemClock.uptimeMillis();
                                        }
                                        format = String.format("[After Sending To FFMpeg] word 0: %X word 1: %X word 2: %X size=%d time=%d \n", new Object[]{Integer.valueOf(dji.midware.util.c.b(bArr, 0)), Integer.valueOf(dji.midware.util.c.b(bArr, 4)), Integer.valueOf(dji.midware.util.c.b(bArr, 8)), Integer.valueOf(intValue), Long.valueOf(System.currentTimeMillis())});
                                        this.e += (long) intValue;
                                        this.f++;
                                        if (intValue != 2048) {
                                            long uptimeMillis2 = SystemClock.uptimeMillis() - this.d;
                                            format = format + String.format("frameReceiveDelay=%d frame_size=%d num_packet=%d", new Object[]{Long.valueOf(uptimeMillis2), Long.valueOf(this.e), Long.valueOf(this.f)});
                                            this.d = -1;
                                            this.e = 0;
                                            this.f = 0;
                                        }
                                        dji.midware.util.a.b.getInstance().c.append(format + "\n");
                                        this.c = (this.c + 1) % 100;
                                        if (this.c == 0) {
                                            dji.midware.util.a.b.getInstance().c.flush();
                                        }
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                if (dji.midware.util.a.c.c) {
                                    dji.midware.util.a.c.getInstance(dji.midware.util.a.c.d).a(bArr, 0, intValue);
                                }
                            }
                            if (this.b.E != this.b.B.size() - 1) {
                                this.b.E = 0;
                            } else {
                                this.b.E = this.b.E + 1;
                            }
                            try {
                                Thread.sleep(0, 100);
                            } catch (InterruptedException e3) {
                                e3.printStackTrace();
                            }
                            j = uptimeMillis;
                        }
                    }
                }
                uptimeMillis = j;
                if (this.b.E != this.b.D) {
                    bArr = (byte[]) this.b.B.get(this.b.E);
                    intValue = ((Integer) this.b.C.get(this.b.E)).intValue();
                    if (ServiceManager.getInstance().j()) {
                        DJIVideoDataRecver.getInstance().onVideoRecv(bArr, intValue, true);
                    } else {
                        FPVController.native_transferVideoData(bArr, intValue);
                        if (dji.midware.util.a.b.a) {
                            if (this.d == -1) {
                                this.d = SystemClock.uptimeMillis();
                            }
                            format = String.format("[After Sending To FFMpeg] word 0: %X word 1: %X word 2: %X size=%d time=%d \n", new Object[]{Integer.valueOf(dji.midware.util.c.b(bArr, 0)), Integer.valueOf(dji.midware.util.c.b(bArr, 4)), Integer.valueOf(dji.midware.util.c.b(bArr, 8)), Integer.valueOf(intValue), Long.valueOf(System.currentTimeMillis())});
                            this.e += (long) intValue;
                            this.f++;
                            if (intValue != 2048) {
                                long uptimeMillis22 = SystemClock.uptimeMillis() - this.d;
                                format = format + String.format("frameReceiveDelay=%d frame_size=%d num_packet=%d", new Object[]{Long.valueOf(uptimeMillis22), Long.valueOf(this.e), Long.valueOf(this.f)});
                                this.d = -1;
                                this.e = 0;
                                this.f = 0;
                            }
                            dji.midware.util.a.b.getInstance().c.append(format + "\n");
                            this.c = (this.c + 1) % 100;
                            if (this.c == 0) {
                                dji.midware.util.a.b.getInstance().c.flush();
                            }
                        }
                        if (dji.midware.util.a.c.c) {
                            dji.midware.util.a.c.getInstance(dji.midware.util.a.c.d).a(bArr, 0, intValue);
                        }
                    }
                    if (this.b.E != this.b.B.size() - 1) {
                        this.b.E = this.b.E + 1;
                    } else {
                        this.b.E = 0;
                    }
                    Thread.sleep(0, 100);
                    j = uptimeMillis;
                } else {
                    Thread.sleep(0, 500);
                    j = uptimeMillis;
                }
            }
            this.b.k = null;
            this.b.b("ParseVideoRunnable.end");
        }
    }

    private class b implements Runnable {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        public void run() {
            this.a.m = true;
            while (this.a.c.a() && this.a.m) {
                int bulkTransfer = this.a.d.bulkTransfer(this.a.e, this.a.v, this.a.v.length, 0);
                if (bulkTransfer > 0) {
                    this.a.a(bulkTransfer);
                }
            }
            this.a.b("recvVodThread.end");
        }
    }

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

    public static void a() {
        if (a != null) {
            a.destroy();
        }
    }

    public a() {
        System.out.println("xxxxxxxxxxxxxx UsbHostService construct");
        this.q = new c(new dji.midware.g.a.c.b(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onRecv(int i, byte[] bArr, int i2, int i3) {
                FPVController.native_transferVideoData(bArr, i3);
            }
        });
        startStream();
        this.l = g.getInstance();
    }

    public void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.c = new DJIUsbReceiver();
        this.c.a(applicationContext);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DJIUsbReceiver.a);
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        applicationContext.registerReceiver(this.c, intentFilter);
    }

    public void b(Context context) {
        context.getApplicationContext().unregisterReceiver(this.c);
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
    }

    public void startStream() {
        this.t = true;
        Log.d("", "xx usb host startStream");
    }

    public void stopStream() {
        this.t = false;
        Log.d("", "usb host stopStream");
        this.m = false;
        this.h = null;
    }

    protected void b() {
        onConnect();
        this.d = this.c.b();
        this.e = this.c.c();
        this.f = this.c.d();
        this.g = this.c.e();
        if (this.f != null) {
        }
        if (this.e != null) {
            c();
        }
    }

    private void c() {
        if (this.h == null) {
            this.h = new Thread(new b());
            this.h.start();
            b("recvVodThread.start");
        }
    }

    private void a(int i) {
        if (this.u) {
            DJIVideoPackManager.getInstance().parseData(this.v, 0, i);
        } else {
            a(this.v, i);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.q != null) {
            this.q.a(bArr, i, i2);
        }
    }

    private void a(byte[] bArr, int i) {
        this.C.set(this.D, Integer.valueOf(i));
        System.arraycopy(bArr, 0, (byte[]) this.B.get(this.D), 0, i);
        if (this.D == this.B.size() - 1) {
            this.D = 0;
        } else {
            this.D++;
        }
    }

    public synchronized void sendmessage(byte[] bArr) {
        if (this.g != null) {
            this.d.bulkTransfer(this.g, bArr, bArr.length, 100);
        }
    }

    public boolean isConnected() {
        return this.c.a();
    }

    public boolean isOK() {
        return isConnected();
    }

    public void destroy() {
        this.m = false;
        this.n = false;
        this.o = false;
        this.h = null;
        this.i = null;
        this.j = null;
        a = null;
    }

    private void b(int i) {
        this.G += i;
        if (d() - this.F > 1000) {
            System.out.println(String.format("video rate %d KB\n", new Object[]{Integer.valueOf(this.G / 1024)}));
            this.F = d();
            this.G = 0;
        }
    }

    private long d() {
        return System.currentTimeMillis();
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGE(this.b, str, false, true);
    }

    private void b(String str) {
        DJILogHelper.getInstance().LOGE(this.b, str, false, false);
    }

    public boolean isRemoteOK() {
        return true;
    }

    public void setDataMode(boolean z) {
    }

    public void pauseRecvThread() {
        this.r = true;
    }

    public void resumeRecvThread() {
        this.r = false;
    }

    public void pauseParseThread() {
    }

    public void resumeParseThread() {
    }

    public void pauseService(boolean z) {
        if (this.s != z) {
            this.s = z;
            if (this.s) {
                g.getInstance().a();
            } else {
                g.getInstance().b();
            }
        }
    }

    public void onDisconnect() {
        this.m = false;
        this.n = false;
        this.o = false;
        this.h = null;
        this.i = null;
        this.j = null;
        a = null;
        dji.midware.f.a.getInstance().a(dji.midware.f.b.f);
    }

    public void onConnect() {
        System.out.println("serial USBHost DJILinkDaemonService.getInstance().setLinkType(DJILinkType.HOST) ");
        dji.midware.f.a.getInstance().a(dji.midware.f.b.b);
    }
}
