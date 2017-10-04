package dji.midware.media;

import android.os.SystemClock;
import android.util.Log;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.j.g;
import dji.midware.natives.FPVController;
import dji.midware.util.a.d;
import java.io.IOException;
import java.nio.ByteBuffer;

public class DJIVideoDataRecver {
    private static DJIVideoDataRecver h;
    private static String i = "DJIVideoDataRecver";
    private static boolean j = false;
    long a = -1;
    int b = 0;
    long c = 0;
    long d = 0;
    long e = 0;
    int f = 0;
    long g = 0;
    private a k = a.None;
    private boolean l = true;
    private boolean m = false;
    private dji.midware.media.j.b n;
    private b o;
    private Object p = new Object();
    private long q = -1;
    private c r = new c();

    public enum a {
        Hardware(0),
        Software(1),
        None(2);
        
        private int d;

        private a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static a find(int i) {
            a aVar = None;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aVar;
        }
    }

    public interface b {
        void a(byte[] bArr, int i);
    }

    public static class c {
        public int a;
    }

    public static DJIVideoDataRecver getInstance() {
        if (h == null) {
            h = new DJIVideoDataRecver();
        }
        return h;
    }

    public DJIVideoDataRecver setVideoDataListener(boolean z, b bVar) {
        this.l = z;
        this.o = bVar;
        FPVController.native_setIsNeedPacked(z);
        return this;
    }

    public DJIVideoDataRecver setDecoderType(a aVar) {
        this.k = aVar;
        ServiceManager.getInstance().a(aVar.a());
        return this;
    }

    public a getDecoderType() {
        return this.k;
    }

    public DJIVideoDataRecver setNeedVideoDataPacked(boolean z) {
        this.l = z;
        ServiceManager.getInstance().b(z);
        return this;
    }

    public DJIVideoDataRecver setNeedRawVideoData(boolean z) {
        this.m = z;
        ServiceManager.getInstance().c(z);
        return this;
    }

    public DJIVideoDataRecver setH264FrameListener(boolean z, dji.midware.media.j.b bVar) {
        synchronized (this.p) {
            this.n = bVar;
        }
        return this;
    }

    public void onAudioRecv(byte[] bArr, int i, long j) {
        if (this.l) {
            putAudioBufferToDecoder(bArr, i, j);
        }
    }

    public void onAudioRecv(byte[] bArr, int i, int i2, long j) {
        if (this.l) {
            putAudioBufferToDecoder(bArr, i, i2, j);
        }
    }

    public void onJpegFrameRecv(byte[] bArr, int i, int i2) {
        this.c++;
        if (dji.midware.util.a.b.a) {
            try {
                dji.midware.util.a.b.getInstance().b.append(String.format("[JPEG_INPUT] word 0: %d word 1: %d word 2: %d size=%d time=%d \n", new Object[]{Integer.valueOf(dji.midware.util.c.b(bArr, 0)), Integer.valueOf(dji.midware.util.c.b(bArr, 4)), Integer.valueOf(dji.midware.util.c.b(bArr, 8)), Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis())}));
                if (this.c % 16 == 0) {
                    dji.midware.util.a.b.getInstance().b.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        e.c(j, i, String.format("jpeg_frameIndex=%d, offset=%d size=%d", new Object[]{Long.valueOf(this.c), Integer.valueOf(i), Integer.valueOf(i2)}));
        if (dji.midware.util.a.c.f) {
            dji.midware.util.a.c.getInstance(dji.midware.util.a.c.g).a(bArr, i, i2);
        }
        DJIVideoDecoder e2 = ServiceManager.getInstance().e();
        if (e2 != null) {
            e2.displayJpegFrame(bArr, i, i2);
        }
    }

    public void onCmdDataRecv(byte[] bArr, int i, int i2) {
    }

    public void onVideoRecv(byte[] bArr, int i, boolean z) {
        onVideoRecv(bArr, 0, i, 0, true, -1, 0, -1, 0, 0, 0, z);
    }

    public void onVideoRecv(byte[] bArr, int i, int i2, boolean z) {
        onVideoRecv(bArr, i, i2, 0, true, -1, 0, -1, 0, 0, 0, z);
    }

    public void onVideoRecv(byte[] bArr, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7, int i8, boolean z2) {
        onVideoRecv(bArr, 0, i, i2, z, i3, i4, i5, i6, i7, i8, z2);
    }

    public void onVideoRecv(byte[] bArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2) {
        this.b = i3;
        this.r.a = i3;
        dji.thirdparty.a.c.a().e(this.r);
        if (!z2) {
            dji.midware.util.a.a.getInstance("DJIVideoDataRecever.onVideoRecv(not raw stream)").a(dji.midware.util.a.a.a, Integer.valueOf(i2));
            dji.midware.util.a.a.getInstance("DJIVideoDataRecever.onVideoRecv(not raw stream)").a("width", Integer.valueOf(i8));
            dji.midware.util.a.a.getInstance("DJIVideoDataRecever.onVideoRecv(not raw stream)").a("height", Integer.valueOf(i9));
            dji.midware.util.a.a.getInstance("DJIVideoDataRecever.onVideoRecv(not raw stream)").a(dji.midware.util.a.a.e, Integer.valueOf(z ? 1 : 0));
            dji.midware.util.a.a.getInstance("DJIVideoDataRecever.onVideoRecv(not raw stream)").a(dji.midware.util.a.a.f, Integer.valueOf(i4 >= 0 ? 1 : 0));
            this.a++;
            long a = d.a(System.currentTimeMillis(), this.a, (long) i3);
            if (dji.midware.util.a.b.a) {
                try {
                    dji.midware.util.a.b.getInstance().b.append(String.format("[DECODER_INPUT] word 0: %d word 1: %d word 2: %d size=%d pts=%d frameNum=%d, frameIndex=%d time=%d \n", new Object[]{Integer.valueOf(dji.midware.util.c.b(bArr, 0)), Integer.valueOf(dji.midware.util.c.b(bArr, 4)), Integer.valueOf(dji.midware.util.c.b(bArr, 8)), Integer.valueOf(i2), Long.valueOf(a), Integer.valueOf(i3), Long.valueOf(this.a), Long.valueOf(System.currentTimeMillis())}));
                    if (i3 % 16 == 0) {
                        dji.midware.util.a.b.getInstance().b.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            synchronized (this.p) {
                if (this.n != null) {
                    this.n.a(bArr, i2, a, z);
                }
            }
            if (this.l) {
                if (g.d == dji.midware.media.j.g.a.GDR_ONLINE && d.a(j)) {
                    Log.i(i, String.format("feed data into decoder: size=%d, frameIndex=%08X", new Object[]{Integer.valueOf(i2), Long.valueOf(a)}));
                }
                if (d.d) {
                    d.a(bArr);
                }
                putBufferToDecoder(bArr, i2, a, i3, z, this.a, i4, i5, i6, i7, i8, i9);
            }
            if (d.c) {
                a();
            }
        } else if (this.o != null) {
            this.o.a(bArr, i2);
        }
    }

    private void a() {
        long j;
        if (this.q < 0) {
            this.q = SystemClock.uptimeMillis();
        }
        long uptimeMillis = SystemClock.uptimeMillis() - this.q;
        if (uptimeMillis < 0 || uptimeMillis >= ((long) 30)) {
            j = 0;
        } else {
            j = ((long) 30) - uptimeMillis;
        }
        if (j > 0) {
            try {
                Thread.sleep(j);
            } catch (Exception e) {
                e.a(i, e);
            }
        }
        this.q = SystemClock.uptimeMillis();
    }

    public void putAudioBufferToDecoder(byte[] bArr, int i, long j) {
        putAudioBufferToDecoder(bArr, 0, i, j);
    }

    public void putAudioBufferToDecoder(byte[] bArr, int i, int i2, long j) {
        if (ServiceManager.getInstance().h() != null) {
            ServiceManager.getInstance().h().a(ByteBuffer.wrap(bArr), i, i2, j);
        }
    }

    public void putBufferToDecoder(byte[] bArr, int i, long j, int i2, boolean z, long j2, int i3, int i4, int i5, int i6, int i7, int i8) {
        switch (this.k) {
            case Hardware:
                if (ServiceManager.getInstance().e() != null) {
                    if (dji.midware.util.a.c.f) {
                        dji.midware.util.a.c.getInstance(dji.midware.util.a.c.g).a(bArr, 0, i);
                    }
                    ServiceManager.getInstance().e().queueInputBuffer(bArr, i, j, i2, z, j2, i3, i4, i5, i6, i7, i8);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
