package dji.g.b;

import android.annotation.SuppressLint;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class b implements dji.midware.media.i.g {
    public static final String a = "AudioPreview";
    public static final String b = "AudioPreview_Main";
    public static final String c = "AudioPreview_Muxer";
    public static final boolean d = false;
    public static final boolean e = false;
    public static final boolean f = false;
    public static final int g = dji.midware.media.b.a.AV_SAMPLE_FMT_S16.ordinal();
    public static final int h = dji.midware.media.b.a.AV_SAMPLE_FMT_S16.ordinal();
    public static final int i = 44100;
    public static final int j = 2;
    private static final int m = 100;
    private static final int n = 2;
    private static final int o = dji.midware.media.b.a.AV_SAMPLE_FMT_S16.ordinal();
    private static final int p = 44100;
    private static final int q = 2;
    private boolean A = false;
    private int B = 0;
    private long C = 0;
    private dji.midware.media.i.g.a D = null;
    private dji.midware.media.i.g.b E = null;
    HandlerThread k = null;
    private short[] l = new short[20480];
    private f r = null;
    private h s = null;
    private e t;
    private dji.g.b.a.d u;
    private dji.g.b.a.d v;
    private c w;
    private dji.g.b.a.c x;
    private dji.g.b.a.c y;
    private d z;

    public static class a {
        public final int a;
        public final long b;
        public final Object c;

        public a(int i, long j) {
            this.a = i;
            this.b = j;
            this.c = null;
        }

        public a(int i, long j, Object obj) {
            this.a = i;
            this.b = j;
            this.c = obj;
        }

        public int a() {
            return this.a;
        }
    }

    public static class b {
        static final int a = 0;
        static final int b = 1;
        static final int c = 2;
        static final int d = 4;
        static final int e = 6;
        static final int f = 7;
        static final int g = 8;
        static final int h = 9;
        static final int i = 10;
        static final int j = 11;
        static final int k = 12;
        static final int l = 13;

        public static String a(int i) {
            switch (i) {
                case 0:
                    return "SeekTo";
                case 1:
                    return "Start";
                case 2:
                    return "Pause";
                case 4:
                    return "Stop";
                case 6:
                    return "Display";
                case 7:
                    return "RemoveInputFile";
                case 8:
                    return "AddInputFile";
                case 9:
                    return "SetSpeed";
                case 10:
                    return "Init";
                case 11:
                    return "Save";
                case 12:
                    return "Refresh";
                default:
                    return "Unrecognised";
            }
        }
    }

    private class c extends Handler {
        boolean a;
        boolean b = true;
        long c = -1;
        final /* synthetic */ b d;
        private String e = "AudioPreview_Controller";
        private f f = f.Uninitialized;
        private a g = null;

        public c(b bVar, Looper looper) {
            this.d = bVar;
            super(looper);
        }

        private void a(int i) {
            a aVar = new a(i, 0, null);
            this.d.w.sendMessage(this.d.w.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(false, this.e, "Signal to Controller. CMD: " + b.a(i));
        }

        private void a(int i, long j, Object obj) {
            a aVar = new a(i, j, obj);
            this.d.w.sendMessage(this.d.w.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(false, this.e, "Signal to Controller. CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
        }

        private void a(int i, long j, long j2, Object obj) {
            a aVar = new a(i, j2, obj);
            this.d.w.sendMessageDelayed(this.d.w.obtainMessage(aVar.a(), aVar), j);
            dji.midware.media.e.c(false, this.e, "Signal to Controller. CMD: " + b.a(i) + " param 1=" + j2 + " param2=" + obj);
        }

        private void b(int i, long j, long j2, Object obj) {
            a aVar = new a(i, j2, obj);
            this.d.w.sendMessageAtTime(this.d.w.obtainMessage(aVar.a(), aVar), j);
            dji.midware.media.e.c(false, this.e, "Signal to Controller. CMD: " + b.a(i) + " param 1=" + j2 + " param2=" + obj);
        }

        public void handleMessage(Message message) {
            if (this.b) {
                this.b = false;
                Process.setThreadPriority(Process.myTid(), -15);
            }
            this.g = (a) message.obj;
            try {
                dji.midware.media.e.c(false, this.e, "Start to process CMD " + b.a(this.g.a) + " at state " + this.f);
                this.a = true;
                switch (this.g.a()) {
                    case 0:
                        a();
                        break;
                    case 1:
                        b();
                        break;
                    case 2:
                        d();
                        break;
                    case 4:
                        e();
                        break;
                    case 6:
                        j();
                        break;
                    case 7:
                        h();
                        break;
                    case 8:
                        g();
                        break;
                    case 10:
                        c();
                        break;
                    case 11:
                        i();
                        break;
                    default:
                        this.a = false;
                        break;
                }
                if (!this.a) {
                    dji.midware.media.e.b(b.b, "Ignore CMD " + b.a(this.g.a) + " at state " + this.f);
                }
                this.d.a(this.g);
            } catch (Exception e) {
                dji.midware.media.e.a(b.b, e);
                removeCallbacksAndMessages(null);
                this.f = f.Error;
                f();
                if (this.d.r.b != null) {
                    this.d.r.b.b(-1);
                }
                this.d.a(this.g);
            } catch (Throwable th) {
                this.d.a(this.g);
            }
        }

        private void a() throws dji.g.b.a.b {
            if (this.f == f.Paused || this.f == f.Playing || this.f == f.e) {
                long j = this.g.b;
                if (Math.abs(j - this.d.x.g()) < 200000 || j >= this.d.u.g()) {
                    dji.midware.media.e.c(false, this.e, "seekTo() REJECT cur=" + this.d.x.g() + " seek To=" + j);
                    return;
                }
                dji.midware.media.e.c(false, this.e, "Accept seekTo cur=" + this.d.x.g() + " seek To=" + j);
                this.d.C = j;
                this.d.u.d(j);
                this.d.x.d();
                if (this.d.v != null) {
                    this.d.v.d(j);
                }
                if (this.d.y != null) {
                    this.d.y.d();
                }
                this.d.z.a();
                this.d.t.a(j);
                if (this.d.E != null) {
                    this.d.E.a(this.d);
                }
                if (this.f == f.e) {
                    this.f = f.Paused;
                    return;
                }
                return;
            }
            this.a = false;
        }

        private void b() throws dji.g.b.a.b {
            if (this.f == f.Initialized || this.f == f.e) {
                if (this.f == f.e) {
                    this.d.z.a();
                }
                this.f = f.Playing;
                this.d.u.d(0);
                this.d.x.d();
                if (this.d.v != null) {
                    this.d.v.d(0);
                    this.d.y.d();
                }
                this.d.t.a(0);
                a(6, 0, null);
            } else if (this.f == f.Paused) {
                this.f = f.Playing;
                this.d.t.a();
                a(6, 0, null);
            } else {
                this.a = false;
            }
        }

        private void c() {
            if (this.f == f.Uninitialized) {
                this.d.n();
                this.f = f.Initialized;
                return;
            }
            this.a = false;
        }

        private void d() {
            if (this.f == f.Playing) {
                this.f = f.Paused;
                removeMessages(6);
                this.d.t.c();
                dji.midware.media.e.c(false, this.e, "paused()");
                return;
            }
            this.a = false;
        }

        private void e() {
            if (this.f == f.Uninitialized) {
                this.f = f.Stopped;
            } else if (this.f == f.Initialized || this.f == f.Paused || this.f == f.Playing || this.f == f.e) {
                this.f = f.Stopped;
                f();
            } else {
                this.a = false;
            }
        }

        private void f() {
            if (this.d.t != null) {
                this.d.t.b();
                this.d.t = null;
            }
            this.d.z.b();
            if (this.d.u != null) {
                this.d.u.j();
                this.d.u = null;
            }
            if (this.d.x != null) {
                this.d.x.i();
                this.d.x = null;
            }
            if (this.d.v != null) {
                this.d.v.j();
                this.d.v = null;
            }
            if (this.d.y != null) {
                this.d.y.i();
                this.d.y = null;
            }
        }

        private void g() {
            if (this.f == f.Paused || this.f == f.Playing || this.f == f.e) {
                this.d.u.a((int) this.g.b, (e) this.g.c);
                return;
            }
            this.a = false;
        }

        private void h() {
            if (this.f == f.Paused || this.f == f.Playing || this.f == f.e) {
                this.d.u.a((int) this.g.b);
            } else {
                this.a = false;
            }
        }

        private void i() {
            if (this.f == f.e || this.f == f.Paused || this.f == f.Playing || this.f == f.Initialized) {
                dji.midware.media.e.b(this.e, "starting Save...");
                try {
                    long f = (long) this.d.f();
                    this.d.u.d(0);
                    this.d.x.d();
                    if (this.d.v != null) {
                        this.d.v.d(0);
                    }
                    if (this.d.y != null) {
                        this.d.y.d();
                    }
                    g gVar = new g(this.d);
                    gVar.a(0);
                    this.d.z.a();
                    dji.midware.media.e.d(this.e, "saving 1");
                    while (!gVar.c) {
                        this.d.x.k();
                        if (this.d.y != null) {
                            this.d.y.k();
                        }
                        gVar.c();
                        do {
                        } while (gVar.d());
                        gVar.e();
                        Thread.sleep(1);
                    }
                    dji.midware.media.e.d(this.e, "end of saving");
                    if (this.d.r.b != null) {
                        dji.midware.media.e.c(false, this.e, "callback progress 3:got=" + this.d.x.g());
                        try {
                            this.d.r.b.a(this.d.x.g() / 1000);
                        } catch (Exception e) {
                            dji.midware.media.e.a(b.b, e);
                        }
                        try {
                            this.d.r.b.a(0);
                        } catch (Exception e2) {
                            dji.midware.media.e.a(b.b, e2);
                        }
                        dji.midware.media.e.c(false, b.b, "call back onFinish()");
                    }
                    if (this.d.D != null) {
                        this.d.D.a(this.d);
                    }
                    gVar.a();
                    this.d.u.d(f);
                    this.d.x.d();
                    if (this.d.v != null) {
                        this.d.v.d(f);
                    }
                    if (this.d.y != null) {
                        this.d.y.d();
                    }
                    this.d.t.a(f);
                    return;
                } catch (Exception e22) {
                    dji.midware.media.e.a("VideoConcat", e22);
                    e22.printStackTrace();
                    return;
                }
            }
            this.a = false;
        }

        private void j() throws dji.g.b.a.b {
            if (this.f == f.Playing) {
                long j = 0;
                if (this.c != -1) {
                    j = System.currentTimeMillis() - this.c;
                }
                long currentTimeMillis = System.currentTimeMillis();
                this.c = currentTimeMillis;
                dji.midware.media.e.c(false, this.e, "doPresent between=" + j);
                int i = 0;
                int i2 = 1;
                int i3 = 1;
                int i4 = 1;
                int i5 = 1;
                int i6 = 1;
                boolean z = true;
                do {
                    int e;
                    int i7;
                    int l;
                    dji.midware.media.e.b(false, this.e, "doPresent_loop_" + i + " HandlerQueueLength=" + this.d.t.d.get());
                    i++;
                    if (i2 > 0) {
                        this.c = System.currentTimeMillis();
                        e = this.d.t.e();
                        i2 = (e > 0 ? 1 : 0) | 0;
                        dji.midware.media.e.c(false, this.e, "doPresent_1 moveFromFilterToPlayer=" + e + " delay=" + (System.currentTimeMillis() - this.c));
                        i7 = e;
                        e = i2;
                        i2 = i7;
                    } else {
                        e = 0;
                    }
                    if (z) {
                        this.c = System.currentTimeMillis();
                        z = this.d.t.d();
                        e |= z;
                        dji.midware.media.e.c(false, this.e, "doPresent_2_all moveFromDecoderToFilter_all=" + z + " delay=" + (System.currentTimeMillis() - this.c));
                    }
                    if (this.d.v != null) {
                        if (i3 > 0) {
                            this.c = System.currentTimeMillis();
                            l = this.d.y.l();
                            e |= l > 0 ? 1 : 0;
                            dji.midware.media.e.c(false, this.e, "doPresent_3 bgDecoder.dealDecoderOutput=" + l + " delay=" + (System.currentTimeMillis() - this.c));
                            i7 = l;
                            l = e;
                            e = i7;
                        } else {
                            l = e;
                            e = i3;
                        }
                        if (i4 > 0) {
                            this.c = System.currentTimeMillis();
                            i3 = this.d.y.k();
                            l |= i3 > 0 ? 1 : 0;
                            dji.midware.media.e.c(false, this.e, "doPresent_4 bgDecoder.dealDecoderInput=" + i3 + " delay=" + (System.currentTimeMillis() - this.c));
                            i4 = i3;
                            i3 = e;
                        } else {
                            i3 = e;
                        }
                    } else {
                        l = e;
                    }
                    if (i5 > 0) {
                        this.c = System.currentTimeMillis();
                        e = this.d.x.l();
                        l |= e > 0 ? 1 : 0;
                        dji.midware.media.e.c(false, this.e, "doPresent_5 orgDecoder.dealDecoderOutput()=" + e + " delay=" + (System.currentTimeMillis() - this.c));
                        i5 = e;
                    }
                    if (i6 > 0) {
                        this.c = System.currentTimeMillis();
                        e = this.d.x.k();
                        i6 = (e > 0 ? 1 : 0) | l;
                        dji.midware.media.e.c(false, this.e, "doPresent_6 orgDecoder.dealDecoderInput()=" + e + " delay=" + (System.currentTimeMillis() - this.c));
                        i7 = e;
                        e = i6;
                        i6 = i7;
                    } else {
                        e = l;
                    }
                    if (e == 0) {
                        break;
                    }
                } while (i < 2);
                this.c = System.currentTimeMillis();
                if (this.d.t.a) {
                    this.f = f.e;
                } else {
                    long j2;
                    if (this.d.t.d.get() > 100) {
                        j2 = 50;
                    } else {
                        j2 = (long) (this.d.t.d.get() < 50 ? 0 : 3);
                    }
                    a(6, j2, 0, null);
                }
                dji.midware.media.e.c(false, this.e, "doPresent_span=" + (System.currentTimeMillis() - currentTimeMillis));
                return;
            }
            this.a = false;
        }
    }

    private static class d {
        private dji.g.c.a.a a;
        private Vector<dji.g.c.a.d> b;
        private Vector<dji.g.c.a.d> c;

        private d() {
            this.b = new Vector();
            this.c = new Vector();
        }

        public void a() {
            int i = 0;
            if (this.a != null) {
                this.a.b();
            }
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                dji.g.c.a.d dVar = (dji.g.c.a.d) this.c.get(i2);
                if (dVar != null) {
                    dVar.b();
                }
            }
            while (i < this.b.size()) {
                dVar = (dji.g.c.a.d) this.b.get(i);
                if (dVar != null) {
                    dVar.b();
                }
                i++;
            }
        }

        public void b() {
            dji.g.c.a.d dVar;
            int i = 0;
            if (this.a != null) {
                this.a.c();
                this.a = null;
            }
            if (this.c != null) {
                for (int i2 = 0; i2 < this.c.size(); i2++) {
                    dVar = (dji.g.c.a.d) this.c.get(i2);
                    if (dVar != null) {
                        dVar.c();
                    }
                }
                this.c = null;
            }
            if (this.b != null) {
                while (i < this.b.size()) {
                    dVar = (dji.g.c.a.d) this.b.get(i);
                    if (dVar != null) {
                        dVar.c();
                    }
                    i++;
                }
                this.b = null;
            }
        }
    }

    private class e {
        boolean a = false;
        boolean b = false;
        boolean c = false;
        AtomicInteger d = new AtomicInteger(0);
        final /* synthetic */ b e;
        private final String f = "AudioPreview_Displayer";
        private final String g = "AudioPreview_Handler";
        private AudioTrack h = null;
        private ByteBuffer i = ByteBuffer.allocate(20480);
        private BufferInfo j = new BufferInfo();
        private Handler k;
        private HandlerThread l;
        private int m = 0;
        private LinkedBlockingQueue<short[]> n = new LinkedBlockingQueue();

        public e(b bVar) {
            int i = 20480;
            this.e = bVar;
            int minBufferSize = AudioTrack.getMinBufferSize(44100, 12, 2);
            if (minBufferSize >= 20480) {
                i = minBufferSize;
            }
            dji.midware.media.e.b("AudioPreview_Displayer", "AudioTrack bufferSize=" + i);
            this.h = new AudioTrack(3, 44100, 12, 2, i, 1);
            this.h.play();
            this.l = new HandlerThread("AudioPlay");
            this.l.start();
            this.k = new Handler(this.l.getLooper());
        }

        public void a() {
            this.h.play();
        }

        @SuppressLint({"NewApi"})
        public void b() {
            if (this.k != null) {
                this.k.removeCallbacksAndMessages(null);
                this.d.set(0);
                this.m = 0;
                this.n.clear();
            }
            if (this.h != null) {
                this.h.flush();
            }
            try {
                if (dji.midware.media.d.a() >= 18) {
                    this.l.quitSafely();
                } else {
                    this.l.quit();
                }
                this.l.join();
            } catch (Exception e) {
                dji.midware.media.e.a(b.b, e);
            }
            if (this.h != null) {
                this.h.release();
            }
        }

        public void c() {
            this.h.pause();
        }

        public void a(long j) {
            this.a = false;
            this.b = false;
            this.c = false;
            this.h.flush();
            this.k.removeCallbacksAndMessages(null);
            this.d.set(0);
            this.m = 0;
            this.n.clear();
        }

        public boolean d() throws dji.g.b.a.b {
            boolean z = false;
            if (!this.b) {
                boolean z2;
                boolean z3;
                long currentTimeMillis;
                int l;
                if (this.e.x.c()) {
                    z2 = false;
                    z3 = true;
                } else {
                    currentTimeMillis = System.currentTimeMillis();
                    l = this.e.x.l();
                    z2 = (l > 0 ? 1 : 0) | 0;
                    dji.midware.media.e.c(false, "AudioPreview_Displayer", "doPresent_21 org_decoder_output=" + l + " delay=" + (System.currentTimeMillis() - currentTimeMillis));
                    z3 = false;
                }
                if (this.e.y == null || this.e.y.c()) {
                    z = z2;
                } else {
                    currentTimeMillis = System.currentTimeMillis();
                    int l2 = this.e.y.l();
                    if (l2 > 0) {
                        l = 1;
                    } else {
                        l = 0;
                    }
                    z2 |= l;
                    dji.midware.media.e.c(false, "AudioPreview_Displayer", "doPresent_22 bg_decoder_output=" + l2 + " delay=" + (System.currentTimeMillis() - currentTimeMillis));
                    z3 = false;
                    z = z2;
                }
                if (z3) {
                    this.b = true;
                }
            }
            return z;
        }

        public int e() {
            if (this.c) {
                return -1;
            }
            boolean z = true;
            int i = 0;
            while (z) {
                int i2 = i;
                i = this.e.z.a.a(this.i, this.j);
                z = false;
                while (i == 0) {
                    short[] sArr;
                    int i3 = i2 + 1;
                    this.i.position(this.j.offset);
                    this.i.limit(this.j.offset + this.j.size);
                    final int i4 = this.j.size / 2;
                    this.i.order(ByteOrder.LITTLE_ENDIAN);
                    this.i.asShortBuffer().get(this.e.l, 0, i4);
                    long currentTimeMillis = System.currentTimeMillis();
                    if (this.m < 100) {
                        sArr = (short[]) this.e.l.clone();
                        this.m++;
                    } else {
                        try {
                            sArr = (short[]) this.n.take();
                            System.arraycopy(this.e.l, 0, sArr, 0, i4);
                        } catch (Exception e) {
                            dji.midware.media.e.a("AudioPreview_Displayer", e);
                            return -2;
                        }
                    }
                    dji.midware.media.e.c(false, "AudioPreview_Handler", "Handler stat 1: handler_queue_length=" + this.d.get() + " taken time=" + (System.currentTimeMillis() - currentTimeMillis) + " FramePoolSize=" + this.n.size() + " atFrameGenerated=" + this.m + (this.e.y != null ? " bgDecoder=" + this.e.y.g() : "") + (this.e.x != null ? " orgDecoder=" + this.e.x.g() : ""));
                    final BufferInfo bufferInfo = new BufferInfo();
                    bufferInfo.set(this.j.offset, this.j.size, this.j.presentationTimeUs, this.j.flags);
                    this.d.incrementAndGet();
                    this.k.post(new Runnable(this) {
                        final /* synthetic */ e d;

                        public void run() {
                            long currentTimeMillis = System.currentTimeMillis();
                            int write = this.d.e.t.h.write(sArr, 0, i4);
                            this.d.e.B = this.d.e.B + write;
                            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                            dji.midware.media.e.c(false, "AudioPreview_Displayer", "Handler->AudioTrack. got_pts=" + bufferInfo.presentationTimeUs + ". " + write + " shorts. Taken Time=" + currentTimeMillis2 + " flags=" + bufferInfo.flags + " totalPlay=" + this.d.e.B);
                            this.d.e.C = bufferInfo.presentationTimeUs;
                            try {
                                this.d.n.put(sArr);
                            } catch (Exception e) {
                                dji.midware.media.e.a("AudioPreview_Displayer", e);
                            }
                            if (this.d.e.r.b != null) {
                                dji.midware.media.e.c(false, "AudioPreview_Displayer", "callback progress 1: filter_out=" + bufferInfo.presentationTimeUs);
                                try {
                                    this.d.e.r.b.a(bufferInfo.presentationTimeUs / 1000);
                                } catch (Exception e2) {
                                    dji.midware.media.e.a(b.b, e2);
                                }
                            }
                            dji.midware.media.e.c(false, "AudioPreview_Handler", "Handler stat 2: handler_queue_length=" + this.d.d.decrementAndGet() + " taken time=" + currentTimeMillis2 + " FramePoolSize=" + this.d.n.size() + " atFrameGenerated=" + this.d.m + (this.d.e.y != null ? " bgDecoder=" + this.d.e.y.g() : "") + (this.d.e.x != null ? " orgDecoder=" + this.d.e.x.g() : ""));
                        }
                    });
                    dji.midware.media.e.c(false, "AudioPreview_Displayer", "AudioMixer->handler. got_pts=" + bufferInfo.presentationTimeUs);
                    i = this.e.z.a.a(this.i, this.j);
                    z = true;
                    i2 = i3;
                }
                i = i2;
            }
            if (!this.b || i != 0) {
                return i;
            }
            this.c = true;
            this.k.post(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.e.t.a = true;
                    if (this.a.e.r.b != null) {
                        dji.midware.media.e.c(false, "AudioPreview_Displayer", "callback progress 3:got=" + this.a.e.x.g());
                        try {
                            this.a.e.r.b.a(this.a.e.x.g() / 1000);
                        } catch (Exception e) {
                            dji.midware.media.e.a(b.b, e);
                        }
                        try {
                            this.a.e.r.b.a(0);
                        } catch (Exception e2) {
                            dji.midware.media.e.a(b.b, e2);
                        }
                        dji.midware.media.e.c(false, b.b, "call back onFinish()");
                    }
                    if (this.a.e.D != null) {
                        this.a.e.D.a(this.a.e);
                    }
                }
            });
            return i;
        }
    }

    public enum f {
        Uninitialized,
        Initialized,
        Paused,
        Playing,
        e,
        Stopped,
        Error
    }

    private class g {
        boolean a = false;
        boolean b = false;
        boolean c = false;
        final /* synthetic */ b d;
        private String e = "AudioPreview_Saver";
        private MediaCodec f = null;
        private dji.midware.media.g.b g;
        private MediaFormat h = null;
        private boolean i;
        private long j;
        private int k;
        private long l;
        private long m = -1;

        public g(b bVar) {
            this.d = bVar;
            f();
        }

        private void f() {
            try {
                this.f = MediaCodec.createEncoderByType(dji.midware.media.d.d[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat(dji.midware.media.d.d[0], 44100, 2);
            createAudioFormat.setInteger("bitrate", 48000);
            this.f.configure(createAudioFormat, null, null, 1);
            this.f.start();
        }

        public void a() {
            if (this.g != null) {
                this.g.d();
                this.g.b();
                this.g = null;
            }
            if (this.f != null) {
                this.f.stop();
                this.f.release();
                this.f = null;
            }
        }

        public int b() {
            int g = (int) (g() * 100.0d);
            dji.midware.media.e.c(false, this.e, "progress: " + g);
            return g;
        }

        private double g() {
            double d = 0.0d;
            dji.midware.media.e.c(false, this.e, "pts=" + this.j + " max_pts=" + this.d.u.g());
            double g = ((double) this.j) / ((double) this.d.u.g());
            if (g < 0.0d) {
                dji.midware.media.e.b(this.e, "progress<0: " + 0.0d);
            } else {
                d = g;
            }
            if (d <= 1.0d) {
                return d;
            }
            dji.midware.media.e.b(this.e, "progress>100: " + 1.0d);
            return 1.0d;
        }

        private void h() {
            this.g = dji.midware.media.g.e.a();
            try {
                this.g.a(this.d.s.b);
                this.g.a(this.h);
                this.g.c();
            } catch (Exception e) {
                dji.midware.media.e.a(b.b, e);
                throw new RuntimeException("failed to create output file");
            }
        }

        private void a(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo) {
            if (!this.i) {
                this.i = true;
                h();
            }
            if (bufferInfo.presentationTimeUs < this.m) {
                dji.midware.media.e.c(false, b.c, "write sample (SKIP): track=" + i + " size=" + bufferInfo.size + " flags=" + bufferInfo.flags + " pts=" + bufferInfo.presentationTimeUs);
                return;
            }
            this.m = bufferInfo.presentationTimeUs;
            if (bufferInfo.size > 0) {
                bufferInfo.flags |= 1;
                dji.midware.media.e.c(false, b.c, "write sample: track=" + i + " size=" + bufferInfo.size + " flags=" + bufferInfo.flags + " pts=" + bufferInfo.presentationTimeUs);
                this.g.a(i, byteBuffer, bufferInfo, 1);
            }
            this.j = bufferInfo.presentationTimeUs;
            if (System.currentTimeMillis() - this.l > 500) {
                int b = b();
                if (b - this.k > 1) {
                    this.k = b;
                    this.l = System.currentTimeMillis();
                    if (this.d.s.c != null) {
                        this.d.s.c.a(b);
                    }
                }
            }
        }

        public boolean c() {
            boolean z = true;
            BufferInfo bufferInfo = new BufferInfo();
            if (this.c) {
                return false;
            }
            try {
                int dequeueOutputBuffer = this.f.dequeueOutputBuffer(bufferInfo, 0);
                if (dequeueOutputBuffer == -2 && this.h == null) {
                    dji.midware.media.e.c(false, b.b, "encoder output changed:" + this.f.getOutputFormat());
                    this.h = this.f.getOutputFormat();
                }
                if (dequeueOutputBuffer >= 0) {
                    ByteBuffer byteBuffer = this.f.getOutputBuffers()[dequeueOutputBuffer];
                    if (!(bufferInfo.flags == 2 || bufferInfo.size == 0)) {
                        byteBuffer.position(bufferInfo.offset);
                        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                        dji.midware.media.e.c(false, this.e, "Encoder sends a frame to muxer. pts=" + bufferInfo.presentationTimeUs + " flags=" + bufferInfo.flags + " size=" + bufferInfo.size);
                        a(0, byteBuffer, bufferInfo);
                    }
                    this.f.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((bufferInfo.flags & 4) > 0) {
                        this.c = true;
                        dji.midware.media.e.c(false, this.e, "encoder output sees EOS");
                    }
                }
                if (this.c) {
                    z = false;
                }
                return z;
            } catch (Exception e) {
                dji.midware.media.e.a(this.e, e);
                this.c = true;
                return false;
            }
        }

        public void a(long j) {
            this.a = false;
            this.b = false;
            this.c = false;
        }

        public boolean d() {
            if (this.a) {
                return false;
            }
            BufferInfo bufferInfo = new BufferInfo();
            int dequeueInputBuffer = this.f.dequeueInputBuffer(0);
            if (dequeueInputBuffer == -1) {
                return false;
            }
            if (dequeueInputBuffer == -2) {
                this.h = this.f.getOutputFormat();
                return false;
            } else if (dequeueInputBuffer < 0) {
                return false;
            } else {
                boolean z;
                bufferInfo.set(0, 0, 0, 0);
                if (this.d.z.a.a(this.f.getInputBuffers()[dequeueInputBuffer], bufferInfo) == 0) {
                    this.f.getInputBuffers()[dequeueInputBuffer].order(ByteOrder.LITTLE_ENDIAN);
                    dji.midware.media.e.c(false, this.e, "AudioMixer OUTPUTs to encoder. got_pts=" + bufferInfo.presentationTimeUs + " ." + bufferInfo.size + " bytes, flags=" + bufferInfo.flags);
                    z = true;
                } else {
                    z = false;
                }
                if (!z && this.b) {
                    this.a = true;
                    bufferInfo.flags |= 4;
                }
                this.f.queueInputBuffer(dequeueInputBuffer, bufferInfo.offset, bufferInfo.size, bufferInfo.presentationTimeUs, bufferInfo.flags);
                return z;
            }
        }

        public boolean e() throws dji.g.b.a.b {
            boolean z = false;
            if (!this.b) {
                boolean z2;
                boolean z3;
                if (this.d.x.c()) {
                    z2 = false;
                    z3 = true;
                } else {
                    z2 = (this.d.x.l() > 0 ? 1 : 0) | 0;
                    z3 = false;
                }
                if (this.d.y == null || this.d.y.c()) {
                    z = z2;
                } else {
                    int i;
                    if (this.d.y.l() > 0) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    z2 |= i;
                    z3 = false;
                    z = z2;
                }
                if (z3) {
                    this.b = true;
                }
            }
            return z;
        }
    }

    public b() {
        dji.midware.media.e.b(b, "create an AudioEditorPlayer object");
        this.A = false;
        this.k = new HandlerThread("AudioPlayer");
        this.k.setPriority(this.k.getThreadGroup().getMaxPriority());
        this.k.start();
        dji.midware.media.e.d(b, "thread started");
        this.w = new c(this, this.k.getLooper());
        dji.midware.media.e.d(b, "controller created");
    }

    private void a(Handler handler, int i, long j, Object obj) {
        if (handler == null) {
            dji.midware.media.e.d(b, "handler is not yet initialized.");
            return;
        }
        a aVar = new a(i, j, obj);
        synchronized (aVar) {
            handler.sendMessage(this.w.obtainMessage(0, aVar));
            dji.midware.media.e.c(false, b, "UI Signal " + handler.getClass().getSimpleName() + " CMD: " + b.a(i) + " param 1=" + j + " param2=" + obj);
            try {
                aVar.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dji.midware.media.e.c(false, b, "Done " + b.a(i));
    }

    private void a(a aVar) {
        synchronized (aVar) {
            aVar.notifyAll();
        }
    }

    private void n() {
        try {
            int i;
            this.u = new dji.g.b.a.d("OrgReader");
            for (i = 0; i < this.r.a[0].length; i++) {
                e eVar = this.r.a[0][i];
                StringBuilder stringBuilder = new StringBuilder("input: " + eVar.b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuilder.append(" (" + eVar.e + " ms -> " + eVar.f + " ms ). speed=" + eVar.c);
                dji.midware.media.e.c(true, b, stringBuilder.toString());
                this.u.a(i, this.r.a[0][i]);
            }
            if (this.r.a.length > 1) {
                this.v = new dji.g.b.a.d("BgReader");
                dji.midware.media.e.c(true, b, "Background music: " + this.r.a[1][0].b);
                this.v.a(0, this.r.a[1][0]);
            }
            this.x = new dji.g.b.a.c("OrgDecoder");
            this.x.a(this.u);
            if (this.v != null) {
                this.y = new dji.g.b.a.c("BgDecoder");
                this.y.a(this.v);
            }
            this.z = new d();
            this.z.a = new dji.g.c.a.a("Mixer", 2);
            this.z.a.a(2, new int[]{h, h}, new int[]{44100, 44100}, new int[]{2, 2}, o, 44100, 2);
            for (i = 0; i < this.u.k(); i++) {
                dji.midware.media.e.d(a, "dealing audio org_format=" + this.u.b(i));
                this.z.b.add(i, null);
                this.z.c.add(i, null);
            }
            this.x.a(new dji.g.b.a.a(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(MediaFormat mediaFormat, ByteBuffer byteBuffer, int i, int i2, long j) {
                    long b = this.a.u.b(j);
                    int a = this.a.u.a(j);
                    System.currentTimeMillis();
                    if (a < this.a.z.b.size()) {
                        e eVar = this.a.r.a[0][a];
                        double d = eVar.c;
                        if (mediaFormat.getInteger("sample-rate") != 44100 || mediaFormat.getInteger("channel-count") != 2 || Math.abs(d - 1.0d) > 1.0E-5d || Math.abs(eVar.d - 1.0d) > 1.0E-5d) {
                            dji.g.c.a.d dVar;
                            dji.g.c.a.d dVar2 = (dji.g.c.a.d) this.a.z.b.get(a);
                            if (dVar2 == null) {
                                while (d >= 2.0d) {
                                    d /= 2.0d;
                                }
                                dVar2 = new dji.g.c.a.b("OrgResampler", eVar.d, d);
                                dVar2.a(1, new int[]{b.g}, new int[]{mediaFormat.getInteger("sample-rate")}, new int[]{mediaFormat.getInteger("channel-count")}, b.h, 44100, 2);
                                this.a.z.b.set(a, dVar2);
                                dVar = dVar2;
                            } else {
                                dVar = dVar2;
                            }
                            dVar.a(0, byteBuffer, i, i2, b);
                            this.a.z.a.a(0, dVar);
                        } else {
                            this.a.z.a.a(0, byteBuffer, i, i2, b);
                        }
                        dji.midware.media.e.c(false, this.a.x.e(), "Sink: orgDecoder->OrgResampler pts=" + b + " sink index=" + a);
                    }
                }
            });
            if (this.y != null) {
                this.y.a(new dji.g.b.a.a(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void a(MediaFormat mediaFormat, ByteBuffer byteBuffer, int i, int i2, long j) {
                        long b = this.a.v.b(j);
                        int a = this.a.v.a(j);
                        if (a < this.a.z.c.size()) {
                            dji.g.c.a.d dVar;
                            dji.g.c.a.d dVar2 = (dji.g.c.a.d) this.a.z.c.get(a);
                            if (dVar2 == null) {
                                for (double d = this.a.r.a[1][a].c; d >= 2.0d; d /= 2.0d) {
                                }
                                dVar2 = new dji.g.c.a.b("bgResampler", 1.0d, 1.0d);
                                dVar2.a(1, new int[]{b.g}, new int[]{mediaFormat.getInteger("sample-rate")}, new int[]{mediaFormat.getInteger("channel-count")}, b.h, 44100, 2);
                                this.a.z.c.add(a, dVar2);
                                dVar = dVar2;
                            } else {
                                dVar = dVar2;
                            }
                            dVar.a(0, byteBuffer, i, i2, b);
                            dji.midware.media.e.c(false, this.a.y.e(), "Sink: bgDecoder->bgResampler pts=" + b + " sink index=" + a);
                            this.a.z.a.a(1, dVar);
                        }
                    }
                });
            }
            this.t = new e(this);
            if (this.r.b != null) {
                try {
                    this.r.b.a();
                } catch (Exception e) {
                    dji.midware.media.e.a(b, e);
                }
            }
        } catch (Exception e2) {
            dji.midware.media.e.a(b, e2);
        }
    }

    public void a(int i, e eVar) throws IOException {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(this.w, 8, (long) i, eVar);
        }
    }

    public void b(int i) {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(this.w, 7, (long) i, null);
        }
    }

    private void b(long j) {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w == null) {
            dji.midware.media.e.d(b, "handler is not yet initialized.");
        } else {
            this.w.removeMessages(0);
            a aVar = new a(0, j, null);
            this.w.sendMessage(this.w.obtainMessage(aVar.a(), aVar));
            dji.midware.media.e.c(false, b, "Signal to Controller. CMD: " + b.a(0) + " param 1=" + j + " param2=" + null);
        }
    }

    private void c(long j) {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (Math.abs(j - this.x.g()) < 200000) {
            dji.midware.media.e.c(false, b, "Reject seek. target_pts=" + this.x.g() + " request pts=" + j);
        } else {
            a(this.w, 0, j, null);
        }
    }

    public void k() {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(this.w, 12, 0, null);
        }
    }

    public void e() {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(this.w, 2, 0, null);
        }
    }

    public void c() {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            this.B = 0;
            a(this.w, 1, 0, null);
        }
    }

    public int a(h hVar) {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
            return -1;
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return -1;
        } else {
            this.s = hVar;
            a(this.w, 11, 0, hVar);
            return 0;
        }
    }

    public void a(int i, double d) {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(this.w, 9, (long) i, Double.valueOf(d));
        }
    }

    @SuppressLint({"NewApi"})
    public synchronized void d() {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else if (!this.A) {
            this.A = true;
            a(this.w, 4, 0, null);
            try {
                if (dji.midware.media.d.a() >= 18) {
                    this.k.quitSafely();
                } else {
                    this.k.quit();
                }
                this.k.join();
            } catch (Exception e) {
                dji.midware.media.e.a(b, e);
            }
            dji.midware.media.e.b(b, "Stop an AudioEditorPlayer object");
        }
    }

    public void l() {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(this.w, 1, 0, null);
        }
    }

    public void a(String str) {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        }
    }

    public void a() {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            d();
        }
    }

    public void a(Object obj) {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        }
    }

    public void b() {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            a(this.w, 10, 0, null);
        }
    }

    public int f() {
        if (this.w.f != f.Error) {
            return (int) (this.x.g() / 1000);
        }
        Log.e(a, "PreviewState.Error. Simply return");
        return 0;
    }

    public int g() {
        if (this.w.f != f.Error) {
            return (int) (this.u.g() / 1000);
        }
        Log.e(a, "PreviewState.Error. Simply return");
        return 1;
    }

    public void a(long j) {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            b(1000 * j);
        }
    }

    public boolean h() {
        return this.w != null && this.w.f == f.Playing;
    }

    public boolean i() {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return false;
        } else if (this.w == null || this.w.f != f.Paused) {
            return false;
        } else {
            return true;
        }
    }

    public void j() {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            d();
        }
    }

    public boolean m() {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
            return true;
        } else if (this.w == null || this.w.f != f.e) {
            return false;
        } else {
            return true;
        }
    }

    public void a(int i) {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        }
    }

    public void a(dji.midware.media.i.g.a aVar) {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            this.D = aVar;
        }
    }

    public void a(dji.midware.media.i.g.b bVar) {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            this.E = bVar;
        }
    }

    public void a(g gVar) {
        if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        }
    }

    public void a(f fVar) {
        if (this.A) {
            dji.midware.media.e.b(a, "the videopreview has been stopped. Can't accept commands any more");
        } else if (this.w.f == f.Error) {
            Log.e(a, "PreviewState.Error. Simply return");
        } else {
            this.r = fVar;
            dji.midware.media.e.b(a, "AudioPlayer.setDataSource(): " + fVar.toString());
        }
    }
}
