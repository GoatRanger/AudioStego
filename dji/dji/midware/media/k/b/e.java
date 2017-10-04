package dji.midware.media.k.b;

import android.media.MediaCodec.BufferInfo;
import android.util.Log;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.f.a;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.d;
import dji.midware.util.c;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;

public class e implements dji.midware.media.j.b, a, Runnable {
    public static String a = "Transcoder_Decoder";
    public static String b = "Transcoder_H264";
    public static String c = "Transcoder_Internal";
    public static String d = "Transcoder_Output";
    public static boolean e = false;
    private static boolean k = false;
    private static boolean l = false;
    private static e m = null;
    private int A;
    private int B;
    private ProductType C;
    boolean f = false;
    boolean g = false;
    int h = 0;
    Object i = new Object();
    a j = null;
    private d n = new d();
    private boolean o = false;
    private c p;
    private c q;
    private LinkedList<a> r = new LinkedList();
    private Object s = new Object();
    private BufferedOutputStream t = null;
    private OutputStream u = null;
    private int v;
    private BufferInfo w = new BufferInfo();
    private Thread x;
    private long y;
    private b z = b.STANDBY;

    protected enum b {
        STANDBY,
        TRANSCODING
    }

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (m == null) {
                m = new e();
            }
            eVar = m;
        }
        return eVar;
    }

    private void e() {
        try {
            if (k) {
                this.u = new FileOutputStream(dji.midware.media.e.e.a() + "test.h264");
                if (this.u != null) {
                    this.t = new BufferedOutputStream(this.u);
                    if (d.a(e)) {
                        Log.i(c, "An H264 File has been opened");
                    }
                } else if (d.a(e)) {
                    Log.e(c, "error in creating H264 File");
                }
            }
        } catch (Exception e) {
            dji.midware.media.e.a(e);
        }
    }

    private void a(ByteBuffer byteBuffer, int i, int i2) {
        if (k) {
            try {
                if (this.t != null) {
                    this.t.write(byteBuffer.array(), i, i2);
                    if (this.v % 15 == 0) {
                        this.t.flush();
                    }
                }
            } catch (Exception e) {
                dji.midware.media.e.a(e);
            }
        }
    }

    private void f() {
        if (k) {
            try {
                if (this.t != null) {
                    this.t.close();
                    this.t = null;
                }
                if (this.u != null) {
                    this.u.close();
                    this.u = null;
                }
                if (d.a(e)) {
                    Log.i(c, "H264 file has been closed");
                }
            } catch (Exception e) {
                Log.e(c, "error when closing H264 file");
                e.printStackTrace();
            }
        }
    }

    private void g() {
        if (this.p == null) {
            this.p = new c(d.r, 30, b.a.H264);
        }
        if (this.q == null) {
            this.q = new c(d.q, 3, b.a.YUV);
        }
        this.p.a();
        this.q.a();
        this.h = 0;
        this.v = 0;
        this.B = 0;
        this.A = 0;
        this.f = false;
        this.g = true;
        this.y = -1;
        e();
        this.o = true;
        this.x = new Thread(this, "OnlineTranscoder");
        this.x.start();
    }

    public void b() {
        DJIVideoDataRecver.getInstance().setH264FrameListener(true, this);
        while (true) {
            DJIVideoDecoder e = ServiceManager.getInstance().e();
            if (e == null) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            } else {
                e.setVideoDataListener(this);
                return;
            }
        }
    }

    public void c() {
        try {
            DJIVideoDataRecver instance = DJIVideoDataRecver.getInstance();
            if (instance != null) {
                instance.setH264FrameListener(true, null);
            }
            DJIVideoDecoder e = ServiceManager.getInstance().e();
            if (e != null) {
                e.setVideoDataListener(null);
            }
        } catch (Exception e2) {
            dji.midware.media.e.a(e2);
        }
    }

    public void d() {
        this.n.a();
    }

    public synchronized void a(a aVar) {
        synchronized (this.s) {
            if (!this.r.contains(aVar)) {
                this.r.add(aVar);
            }
        }
        if (this.z == b.STANDBY) {
            this.z = b.TRANSCODING;
            g();
        }
    }

    public synchronized void b(a aVar) {
        synchronized (this.s) {
            this.r.remove(aVar);
        }
        if (this.r.isEmpty() && this.z == b.TRANSCODING) {
            i();
            this.z = b.STANDBY;
        }
    }

    private boolean h() {
        if (this.j == null) {
            this.j = a.getInstance();
        }
        return DJIVideoDecoder.getIframeRawId(i.getInstance().c(), 1920, 720) > 0;
    }

    public void a(byte[] bArr, int i, long j, boolean z) {
        if (this.o) {
            try {
                if (ServiceManager.getInstance().e() == null) {
                    return;
                }
                b c;
                if (!z || h()) {
                    c = this.p.c();
                    if (c != null) {
                        c.b().a(ByteBuffer.wrap(bArr, 0, i));
                        c.a(j);
                        c.a(false);
                        this.p.b(c);
                        return;
                    } else if (d.a(e)) {
                        Log.i(b, "notKeyFrame h264 frame pool is empty");
                        return;
                    } else {
                        return;
                    }
                }
                c = this.p.c();
                if (c != null) {
                    c.b().a(ByteBuffer.wrap(bArr, 0, i));
                    c.a(j);
                    c.a(true);
                    this.p.b(c);
                } else if (d.a(e)) {
                    Log.i(b, "notKeyFrame h264 frame pool is empty");
                }
            } catch (Exception e) {
                Log.e(b, dji.midware.media.e.b(e));
            }
        }
    }

    private boolean b(long j) {
        int c = d.c(j);
        int b = d.b(j);
        long a = d.a(j);
        boolean z = c != 0 ? false : this.g ? true : ((long) b) - this.y >= 31;
        if (d.a(e)) {
            Log.i("needWaitIFrame", "Thread " + Thread.currentThread().getName() + String.format(" queries needWaitIFrame com_pts=%s, frame_index=%d frame_num=%d, org_pts=%d, re=%b", new Object[]{Long.toHexString(j), Integer.valueOf(b), Integer.valueOf(c), Long.valueOf(a), Boolean.valueOf(z)}));
        }
        return z;
    }

    public boolean a(long j) {
        if (h()) {
            return b(j);
        }
        return false;
    }

    public b a() {
        return this.q.c();
    }

    public void a(b bVar) {
        if (this.o) {
            if (bVar != null) {
                if (d.a(e)) {
                    Log.i(a, "receive a frame from decoder with pts=" + bVar.c());
                }
                try {
                    this.q.b(bVar);
                } catch (Exception e) {
                    Log.e(a, dji.midware.media.e.b(e));
                }
                synchronized (this.i) {
                    this.i.notify();
                }
            } else if (d.a(e)) {
                Log.e(a, "error: feed a null raw frame");
            }
        } else if (bVar != null) {
            c(bVar);
        }
    }

    private void a(ByteBuffer byteBuffer, BufferInfo bufferInfo) throws IOException {
        if (l && !this.f) {
            this.f = true;
            byte[] bArr = new byte[2000000];
            OutputStream fileOutputStream = new FileOutputStream(dji.midware.media.e.e.a() + System.currentTimeMillis() + "_bi.yuv");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int i = bufferInfo.size;
            byteBuffer.position(bufferInfo.offset);
            byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
            byteBuffer.get(bArr, 0, i);
            bufferedOutputStream.write(bArr, 0, i);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.close();
            Log.i(a, "yuv dump done");
        }
    }

    public void a(int i, int i2) {
    }

    public void a(int i) {
    }

    public void a(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        Log.i(c, "onSpsPpschange");
        dji.midware.media.e.a(c, "onSpsPpschange");
    }

    private void i() {
        Log.i(c, "OnlineTranscoder service is being stopped");
        c();
        Log.i(c, "OnlineTranscoder has detached from upstream");
        this.o = false;
        try {
            this.x.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(c, "Transcoding thread has ended");
        f();
        this.n.a();
        this.p.b();
        this.q.b();
        Log.i(c, "OnlineTranscoder service has stopped");
    }

    public void run() {
        try {
            Log.i(c, "OnlineTranscoder service has started");
            b();
            while (this.o) {
                b j = j();
                if (j == null) {
                    Log.d(c, "cannot get frame");
                    Thread.sleep(50);
                } else {
                    if (this.g) {
                        if (j.a == b.a.YUV || j.d()) {
                            this.g = false;
                            dji.midware.media.e.a(c, this.h + " has been jumped");
                        } else {
                            this.h++;
                            if (e) {
                                Log.i(c, this.h + " has been jumped");
                            }
                            c(j);
                            Thread.sleep(10);
                        }
                    }
                    if (j.a == b.a.YUV || j.d()) {
                        this.y = (long) d.b(j.c());
                    }
                    a(j.b().c(), 0, j.a());
                    j.b().e();
                    if (d.a(e)) {
                        Log.i(c, "written Frames=" + this.v);
                    }
                    this.w.offset = 0;
                    this.w.size = j.a();
                    this.w.presentationTimeUs = d.a(d.b(j.c()));
                    this.w.flags = 0;
                    if (j.a == b.a.YUV || j.d()) {
                        BufferInfo bufferInfo = this.w;
                        bufferInfo.flags |= 1;
                    }
                    boolean z = e;
                    String str = d;
                    String str2 = "output: %s, Index=%d, pts=%d, size=%d, w=%d, h=%d";
                    Object[] objArr = new Object[6];
                    objArr[0] = (this.w.flags & 1) > 0 ? " I-frame" : "";
                    objArr[1] = Integer.valueOf(this.v);
                    objArr[2] = Long.valueOf(this.w.presentationTimeUs);
                    objArr[3] = Integer.valueOf(this.w.size);
                    objArr[4] = Integer.valueOf(this.B);
                    objArr[5] = Integer.valueOf(this.A);
                    dji.midware.media.e.c(z, str, String.format(str2, objArr));
                    ByteBuffer c = j.b().c();
                    synchronized (this.s) {
                        Iterator it = this.r.iterator();
                        while (it.hasNext()) {
                            a aVar = (a) it.next();
                            try {
                                long currentTimeMillis = System.currentTimeMillis();
                                aVar.onFrameInput(c, this.w, this.h + this.v, this.B, this.A);
                                dji.midware.media.e.c(e, d, "call " + aVar.getClass().getName() + " takes " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                            } catch (Exception e) {
                                dji.midware.media.e.a(c, e);
                            }
                        }
                    }
                    j.b().e();
                    this.v++;
                    c(j);
                }
            }
        } catch (Exception e2) {
            Log.e(c, dji.midware.media.e.b(e2));
        }
        Log.i(c, "Online Transcoder Thread ends");
    }

    private b j() {
        b k = k();
        if (k != null) {
            dji.midware.media.e.c(e, c, String.format("Return: Type=%s, index=%d, Origin Queue=%d, replace queue=%d", new Object[]{k.a.toString(), Long.valueOf(k.c()), Integer.valueOf(this.p.d()), Integer.valueOf(this.q.d())}));
        }
        return k;
    }

    private b k() {
        b e = this.p.e();
        if (e == null) {
            return null;
        }
        if (!a(e.c())) {
            return e;
        }
        dji.midware.media.e.c(e, c, "the frame need to be replaced");
        b f = this.q.f();
        if (f != null) {
            while (f.c() < e.c()) {
                dji.midware.media.e.a(e, c, "the replace element is out-of-date. replace=" + f.c() + " origin=" + e.c());
                this.q.e();
                c(f);
                f = this.q.f();
                if (f == null) {
                    dji.midware.media.e.c(e, c, "after removing the out-of-date elements, replace queue is empty");
                    break;
                }
            }
        }
        if (f == null) {
            dji.midware.media.e.c(e, c, "there is no element in the replace queue. will wait 1 sec");
            synchronized (this.i) {
                try {
                    this.i.wait(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            f = this.q.f();
            if (f == null) {
                dji.midware.media.e.c(e, c, "after waiting, still, there is no element in the replace queue");
                return e;
            }
        }
        while (f.c() < e.c()) {
            dji.midware.media.e.a(e, c, "the replace element is out-of-date. replace index=" + f.c() + " origin frameIndex=" + e.c());
            this.q.e();
            c(f);
            f = this.q.f();
            if (f == null) {
                dji.midware.media.e.c(e, c, "after removing the out-of-date elements, there is no proper replace element");
                return e;
            }
        }
        if (f.c() > e.c()) {
            dji.midware.media.e.c(e, c, "the got replace element is for the future. The origin progress is far behind");
            return e;
        }
        dji.midware.media.e.c(e, c, "Now we have a frame that is proper for replace");
        this.q.e();
        if (b(f)) {
            c(e);
            return f;
        }
        c(f);
        return e;
    }

    private boolean b(b bVar) {
        if (a(bVar.c())) {
            if (!(bVar.e() == this.B && bVar.f() == this.A)) {
                DJIVideoDecoder e = ServiceManager.getInstance().e();
                if (e == null || e.sps_header == null || e.sps_header.length == 0 || e.pps_header == null || e.pps_header.length == 0) {
                    Log.i(c, "the Iframemaker can't be initialized. still return origin.");
                    return false;
                }
                this.n.a();
                Log.i(c, String.format("width/height changed: [pre_Width=%d pre_height=%d] [new_width=%d new_height=%d]", new Object[]{Integer.valueOf(this.B), Integer.valueOf(this.A), Integer.valueOf(bVar.e()), Integer.valueOf(bVar.f())}));
                this.B = bVar.e();
                this.A = bVar.f();
                Object obj = new byte[(e.sps_header.length + e.pps_header.length)];
                System.arraycopy(e.sps_header, 0, obj, 0, e.sps_header.length);
                System.arraycopy(e.pps_header, 0, obj, e.sps_header.length, e.pps_header.length);
                dji.midware.media.e.a(c, "init iFrameMaker: sps_pps=" + c.i(obj));
                this.n.a(obj, 0, this.B, this.A);
                Log.i(c, "Video width and height changed. re-init ");
            }
            Log.i(c, String.format("before making I frame. origin queue=%d, replace queue=%d", new Object[]{Integer.valueOf(this.p.d()), Integer.valueOf(this.q.d())}));
            this.n.a(bVar);
            return true;
        }
        Log.i(c, "no need make I frame.");
        return true;
    }

    private void c(b bVar) {
        switch (bVar.a) {
            case H264:
                this.p.a(bVar);
                return;
            case YUV:
                this.q.a(bVar);
                return;
            default:
                return;
        }
    }
}
