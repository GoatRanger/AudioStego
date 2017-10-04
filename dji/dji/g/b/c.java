package dji.g.b;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.media.e;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.PriorityQueue;

public class c {
    public static final String a = "VideoConvertor";
    public static final String b = "VideoConvertor_Main";
    public static final String c = "VideoConvertor_CopyOrigin";
    public static final String d = "VideoConvertor_Muxer";
    public static final boolean e = true;
    private static final int k = 33333;
    private ByteBuffer A = null;
    private long B = 0;
    private Surface C;
    private dji.midware.media.g.b D;
    private int E;
    private long[] F = new long[2];
    private long G;
    private final int H = 0;
    private final int I = 0;
    private long J = 0;
    private long K;
    private MediaFormat L = null;
    private boolean M = false;
    private boolean N = false;
    private boolean O;
    int f;
    MediaCodec g = null;
    int h;
    a i;
    Object j = new Object();
    private h l = null;
    private MediaFormat m;
    private dji.midware.media.d.d n = null;
    private ByteBuffer o;
    private ByteBuffer p;
    private int q;
    private MediaCodec r = null;
    private boolean s;
    private boolean t = false;
    private int u = -1;
    private long v = -1;
    private final float[] w = new float[16];
    private c x = new c();
    private Surface y;
    private ByteBuffer z = null;

    private class a extends Thread implements OnFrameAvailableListener {
        final long a;
        final long b;
        final double c;
        boolean d = false;
        boolean e = true;
        boolean f = true;
        long g;
        boolean h = false;
        boolean i = true;
        boolean j = false;
        long k;
        long l;
        final /* synthetic */ c m;
        private String n = "VideoConvertor_Decoder";
        private Object o = new Object();

        public a(c cVar, long j, long j2, double d) {
            this.m = cVar;
            super("VideoConvertor_Decoder");
            this.b = j;
            this.a = j2;
            this.c = d;
        }

        public void a() {
            BufferInfo bufferInfo = new BufferInfo();
            if (!this.j) {
                try {
                    int dequeueOutputBuffer = this.m.r.dequeueOutputBuffer(bufferInfo, 0);
                    if (dequeueOutputBuffer >= 0) {
                        try {
                            e.c(true, this.n, "decoder output index=" + dequeueOutputBuffer + " pts=" + bufferInfo.presentationTimeUs + " flags=" + bufferInfo.flags);
                            if ((bufferInfo.flags & 4) > 0) {
                                this.j = true;
                                this.m.r.releaseOutputBuffer(dequeueOutputBuffer, false);
                                e.c(true, this.n, "decoder sees EOS");
                            } else if (dequeueOutputBuffer == -2) {
                                e.c(true, this.n, "decoder outputs input_format_changed");
                                MediaFormat outputFormat = this.m.r.getOutputFormat();
                                Log.i(this.n, "decoder's output format: width=" + outputFormat.getInteger("width") + " height=" + outputFormat.getInteger("height"));
                            } else if (dequeueOutputBuffer >= 0) {
                                long j = -1;
                                if (bufferInfo.presentationTimeUs >= this.b && bufferInfo.presentationTimeUs < this.a) {
                                    j = (long) (((double) (bufferInfo.presentationTimeUs - this.b)) / this.c);
                                }
                                if (j < 0 || j < this.l || (dji.midware.media.d.a() >= 18 && bufferInfo.size <= 0)) {
                                    try {
                                        this.m.r.releaseOutputBuffer(dequeueOutputBuffer, false);
                                        e.c(true, this.n, "decoder drops a frames. orgin_pts=" + bufferInfo.presentationTimeUs + " cvt_pts=" + j + " target_pts=" + this.l + " start_us=" + this.b);
                                        return;
                                    } catch (Exception e) {
                                        e.a(this.n, e);
                                        this.m.e();
                                        return;
                                    }
                                }
                                DJILogHelper.getInstance().LOGI("bob", "before synchronized (frameAvailableSync) ");
                                synchronized (this.o) {
                                    DJILogHelper.getInstance().LOGI("bob", "after synchronized (frameAvailableSync) mFlag =" + this.h);
                                    this.h = false;
                                    try {
                                        this.m.r.releaseOutputBuffer(dequeueOutputBuffer, true);
                                        if (this.i) {
                                            this.i = false;
                                            Thread.sleep(200);
                                        }
                                        while (!this.h) {
                                            DJILogHelper.getInstance().LOGI("bob", "before frameAvailableSync.wait() ");
                                            this.o.wait(1000);
                                            DJILogHelper.getInstance().LOGI("bob", "after frameAvailableSync.wait() mFlag =" + this.h);
                                        }
                                    } catch (Exception e2) {
                                        e.a(this.n, e2);
                                        this.m.e();
                                        return;
                                    }
                                }
                                this.m.x.d();
                                this.k = this.m.J + j;
                                this.m.x.a(this.k, false);
                                e.c(true, this.n, "decoder Renders buffer " + dequeueOutputBuffer + ". size=" + bufferInfo.size + " org_pts=" + bufferInfo.presentationTimeUs + " cvt_pts=" + j + " encoderPts=" + this.k + " flags=" + bufferInfo.flags);
                                while (j >= this.l) {
                                    this.l += dji.midware.media.d.e();
                                }
                            }
                        } catch (Exception e22) {
                            e.a(this.n, e22);
                        }
                    }
                } catch (Exception e222) {
                    e.a(this.n, e222);
                    this.m.e();
                }
            }
        }

        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            DJILogHelper.getInstance().LOGI("bob", "before onFrameAvailable  ");
            synchronized (this.o) {
                DJILogHelper.getInstance().LOGI("bob", "after fonFrameAvailable mFlag =" + this.h);
                this.h = true;
                this.o.notify();
                DJILogHelper.getInstance().LOGI("bob", "after NOTIFY mFlag =" + this.h);
            }
        }

        public void b() {
            boolean z = false;
            int dequeueInputBuffer;
            if (!this.d && this.a - this.g > 33333) {
                this.m.o.clear();
                int a = this.m.n.a(this.m.o, 0);
                if (a < 0) {
                    this.d = true;
                    return;
                }
                int c = this.m.n.c();
                this.g = this.m.n.d();
                try {
                    dequeueInputBuffer = this.m.r.dequeueInputBuffer(0);
                    if (dequeueInputBuffer >= 0) {
                        int i;
                        ByteBuffer byteBuffer = this.m.r.getInputBuffers()[dequeueInputBuffer];
                        byteBuffer.clear();
                        this.m.o.position(0);
                        this.m.o.limit(a);
                        if (byteBuffer.capacity() < a) {
                            e.b(this.n, "decoderInputBuffer.capacity<readSize. decoder's capacity=" + byteBuffer.capacity() + " readSize" + a);
                            this.m.o.limit(0);
                            a = 0;
                        }
                        byteBuffer.put(this.m.o);
                        if (this.f) {
                            this.f = false;
                            i = !this.m.s ? 3 : 1;
                        } else {
                            i = 0;
                        }
                        if ((c & 1) != 0) {
                            i |= 1;
                        }
                        byteBuffer.position(0);
                        byteBuffer.limit(a);
                        try {
                            this.m.r.queueInputBuffer(dequeueInputBuffer, 0, a, this.g, i);
                            e.c(true, this.n, "send to decoder. src_pts=" + this.g + " size=" + a + " ex-flag=" + c + " deFlag=" + i);
                            if (!this.m.n.a()) {
                                z = true;
                            }
                            this.d = z;
                        } catch (Exception e) {
                            e.a(this.n, e);
                            this.m.e();
                        }
                    }
                } catch (Exception e2) {
                    e.a(this.n, e2);
                    this.m.e();
                }
            } else if (this.e) {
                try {
                    dequeueInputBuffer = this.m.r.dequeueInputBuffer(0);
                    if (dequeueInputBuffer >= 0) {
                        try {
                            this.m.r.queueInputBuffer(dequeueInputBuffer, 0, 0, this.g, 4);
                            this.e = false;
                            e.c(true, this.n, "moveSrcToDecoder completed. pts=" + this.g);
                        } catch (Exception e22) {
                            e.a(this.n, e22);
                        }
                    }
                } catch (Exception e222) {
                    e.a(this.n, e222);
                }
            }
        }

        public void run() {
            this.g = this.m.n.d();
            this.l = 0;
            this.m.x.d.f();
            this.k = this.m.J;
            while (!this.j) {
                try {
                    b();
                    this.m.i.a();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.j = true;
                }
            }
            this.m.J = this.k + dji.midware.media.d.e();
            this.m.x.d.e();
            e.c(true, this.n, "decoder thread ends");
        }
    }

    private class b extends Thread {
        final /* synthetic */ c a;
        private String b = "VideoConvertor_MoveEncoderToMuxer";

        public b(c cVar) {
            this.a = cVar;
            super("MoveEncoderToMuxer");
        }

        public void run() {
            BufferInfo bufferInfo = new BufferInfo();
            boolean z = false;
            while (!z && !this.a.M) {
                try {
                    int dequeueOutputBuffer = this.a.g.dequeueOutputBuffer(bufferInfo, 5);
                    if (dequeueOutputBuffer == -2 && this.a.L == null) {
                        e.c(true, c.b, "encoder output changed:" + this.a.g.getOutputFormat());
                        this.a.L = this.a.g.getOutputFormat();
                    }
                    if (dequeueOutputBuffer >= 0) {
                        ByteBuffer byteBuffer = this.a.g.getOutputBuffers()[dequeueOutputBuffer];
                        if (!(bufferInfo.flags == 2 || bufferInfo.size == 0)) {
                            byteBuffer.position(bufferInfo.offset);
                            byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                            e.c(true, this.b, "Encoder sends a frame to muxer. track=" + this.a.E + " pts=" + bufferInfo.presentationTimeUs + " flags=" + bufferInfo.flags + " size=" + bufferInfo.size);
                            this.a.a(this.a.E, byteBuffer, bufferInfo);
                        }
                        this.a.g.releaseOutputBuffer(dequeueOutputBuffer, false);
                        if ((bufferInfo.flags & 4) > 0) {
                            z = true;
                        }
                    }
                } catch (Exception e) {
                    e.a(this.b, e);
                    this.a.M = true;
                    return;
                }
            }
            e.c(true, this.b, "encoder output sees EOS");
        }
    }

    private class c {
        final /* synthetic */ c a;
        private String b;
        private boolean c;
        private dji.midware.media.h.c d;
        private dji.midware.media.h.c e;
        private dji.midware.media.h.a.b f;
        private dji.midware.media.h.a.b g;
        private dji.midware.media.h.a.c h;
        private int i;
        private SurfaceTexture j;
        private dji.midware.media.h.d.a k;
        private dji.midware.media.h.d.a l;
        private int m;

        private c(c cVar) {
            this.a = cVar;
            this.b = "VideoConvertor_OpenGLMgr";
            this.c = false;
            this.f = new dji.midware.media.h.a.b(true);
            this.g = new dji.midware.media.h.a.b(false);
            this.h = new dji.midware.media.h.a.c(false, false);
        }

        private void e() {
            e.c(true, c.b, "start to addLogo(). output_segment_start_pts_us=" + this.a.J);
            this.d.f();
            for (int i = 0; ((long) i) < (30 * this.a.l.k) / 1000000; i++) {
                e.c(true, this.b, "add Logo: frame " + i);
                this.a.J = this.a.J + dji.midware.media.d.e();
                a(this.a.J, true);
            }
            this.d.e();
            e.c(true, c.b, "end of addLogo()");
        }

        @SuppressLint({"NewApi"})
        public void a() {
            if (dji.midware.media.d.a() >= 18) {
                this.a.g.signalEndOfInputStream();
                return;
            }
            int dequeueInputBuffer = this.a.g.dequeueInputBuffer(-1);
            this.a.g.queueInputBuffer(dequeueInputBuffer, 0, 0, this.a.J, 4);
        }

        public void b() {
            this.d.f();
            dji.midware.media.h.d.c(this.k.a);
            dji.midware.media.h.d.b(this.k.b);
            dji.midware.media.h.d.c(this.l.a);
            dji.midware.media.h.d.b(this.l.b);
            this.f.f();
            this.g.f();
            this.h.f();
            this.a.y.release();
            this.j.detachFromGLContext();
            this.j.release();
            dji.midware.media.h.d.b(this.i);
            if (this.e.k()) {
                this.d.h();
                this.d.e();
                this.e.f();
                e.b(c.b, "ctxCur detached. ctxOrgin attached.");
                return;
            }
            this.d.i();
            e.b(c.b, "ctxCur destroyed");
        }

        public void c() {
            this.e = dji.midware.media.h.c.o();
            this.d = dji.midware.media.h.c.o();
            dji.midware.media.h.d.a("at the begin of VideoConcat Init()");
            this.e.g();
            if (this.e.k()) {
                dji.midware.media.h.d.a();
                e.b(c.b, "Original thread has EXISTING OpenGL context");
                this.d.g();
            } else {
                e.b(c.b, "Original thread has NO OpenGL context");
                this.d.b();
            }
            this.d.a(this.a.C);
            this.d.f();
            if (this.a.l.i != null) {
                this.a.O = true;
                this.a.l.i.a(this.a.l.l, this.a.l.m);
            }
            this.m = dji.midware.media.h.d.b();
            this.i = dji.midware.media.h.d.a(36197);
            synchronized (this.a.j) {
                new Thread(this, "Init OpenGLMover") {
                    final /* synthetic */ c a;

                    public void run() {
                        this.a.j = new SurfaceTexture(this.a.i);
                        synchronized (this.a.a.j) {
                            this.a.a.j.notify();
                        }
                    }
                }.start();
                try {
                    this.a.j.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.a.y = new Surface(this.j);
            this.k = dji.midware.media.h.d.a(this.a.l.l, this.a.l.m);
            e.d(this.b, "filter target. FB=" + this.k.a + " TX=" + this.k.b);
            this.l = dji.midware.media.h.d.a(this.a.l.l, this.a.l.m);
            e.d(this.b, "filter Src. FB=" + this.l.a + " TX=" + this.l.b);
            this.f.e();
            this.g.e();
            this.h.e();
            this.d.e();
        }

        private void a(long j) {
            e.c(true, c.b, "padFrame");
            this.d.f();
            a(j, false);
            this.d.e();
            e.c(true, this.b, "end of padFrame()");
        }

        public void d() {
            e.c(true, this.b, "decoder onFrameAvailable");
            this.j.updateTexImage();
            this.j.getTransformMatrix(this.a.w);
            e.c(true, this.b, "decoder onFrameAvailable after updateTexImage()");
        }

        private void a(long j, boolean z) {
            dji.midware.media.h.d.d(this.l.a);
            e.c(true, this.b, "after bind frame buffer to filterSrc");
            float f = 0.0f;
            try {
                f = (float) (-this.a.m.getInteger("rotation"));
            } catch (Exception e) {
            }
            if (this.a.l.d[this.a.q].l) {
                int i = (int) (this.a.l.d[this.a.q].m * ((double) this.a.l.l));
                int i2 = (int) (this.a.l.d[this.a.q].n * ((double) this.a.l.m));
                int i3 = (int) (this.a.l.d[this.a.q].o * ((double) this.a.l.l));
                int i4 = (int) (this.a.l.d[this.a.q].p * ((double) this.a.l.m));
                if (i == i3 || i2 == i4) {
                    throw new RuntimeException("selected window range is invalid for File No. " + this.a.q);
                }
                this.f.a(this.i, 36197, this.a.w, false, f, this.a.l.l, this.a.l.m, i, i2, i3, i4);
            } else {
                this.f.a(this.i, 36197, this.a.w, false, f, this.a.l.l, this.a.l.m);
            }
            e.c(true, this.b, "after first drawing");
            int i5 = 0;
            if (this.a.l.i != null) {
                dji.midware.media.h.d.d(this.k.a);
                e.c(true, this.b, "bind frame buffer " + this.k.a);
                long currentTimeMillis = System.currentTimeMillis();
                e.c(true, this.b, "apply filter: src_tex_ID=" + this.l.b + " file_index=" + this.a.q + " pts=" + j + " logo=" + z);
                i5 = this.a.l.i.a(new int[]{this.l.b}, new int[]{this.a.q}, new int[]{0}, j, this.a.G, z);
                this.a.B = this.a.B + (System.currentTimeMillis() - currentTimeMillis);
                e.c(true, this.b, "Num. of filters applied: " + i5);
            }
            e.c(true, this.b, "after applying filters");
            if (dji.midware.media.d.a() >= 18) {
                dji.midware.media.h.d.d(this.m);
                e.c(true, this.b, "after binding framebuffer to be encoder's input buffer");
                if (i5 == 0) {
                    this.g.a(this.l.b, 3553, null, false, 0.0f, this.a.l.l, this.a.l.m);
                } else {
                    this.g.a(this.k.b, 3553, null, false, 0.0f, this.a.l.l, this.a.l.m);
                }
                e.c(true, this.b, "decoder onFrameAvailable after draw();");
                if (this.c) {
                    this.c = false;
                    dji.midware.media.h.d.a(dji.midware.media.e.e.a() + "dji_framebuffer.jpg", this.a.l.l, this.a.l.m);
                }
                this.d.a(j);
                e.c(true, this.b, "decoder onFrameAvailable after setPresentationTime();");
                this.d.j();
            } else {
                e.c(true, this.b, "running SDK<18");
                if (i5 == 0) {
                    dji.midware.media.h.d.d(this.k.a);
                    this.h.a(this.l.b, 3553, null, true, 0.0f, this.a.l.l, this.a.l.m);
                } else {
                    dji.midware.media.h.d.d(this.l.a);
                    this.h.a(this.k.b, 3553, null, true, 0.0f, this.a.l.l, this.a.l.m);
                }
                e.c(true, this.b, "decoder onFrameAvailable after draw();");
                if (this.a.z == null) {
                    e.d(this.b, "encoder's input color format: " + this.a.h);
                    this.a.z = ByteBuffer.allocateDirect(dji.midware.media.d.q);
                    this.a.A = ByteBuffer.allocateDirect(((this.a.l.l * this.a.l.m) * 3) / 2);
                }
                this.h.a(this.a.z, this.a.l.l, this.a.l.m);
                dji.midware.media.d.a(this.a.z, this.a.A, this.a.l.l, this.a.l.m, this.a.h);
                e.c(true, this.b, "decoder onFrameAvailable after read yuv data");
                int dequeueInputBuffer = this.a.g.dequeueInputBuffer(-1);
                ByteBuffer byteBuffer = this.a.g.getInputBuffers()[dequeueInputBuffer];
                byteBuffer.clear();
                this.a.A.clear();
                byteBuffer.put(this.a.A);
                byteBuffer.clear();
                e.c(true, this.b, "send to Encoder's input: bufferIndex=" + dequeueInputBuffer + " pts=" + j + " flags=" + 0);
                this.a.g.queueInputBuffer(dequeueInputBuffer, 0, this.a.A.capacity(), j, 0);
            }
            e.c(true, this.b, "decoder sendFrameFromDecoderTextureToEncoderSurface() end.");
        }
    }

    public static class d implements Comparable<Object> {
        public int a;
        public byte[] b;
        public BufferInfo c;

        public d(int i, byte[] bArr, BufferInfo bufferInfo) {
            this.a = i;
            this.b = bArr;
            this.c = bufferInfo;
        }

        public int compareTo(Object obj) {
            if (obj instanceof d) {
                d dVar = (d) obj;
                if (this.c.presentationTimeUs < dVar.c.presentationTimeUs) {
                    return -1;
                }
                if (this.c.presentationTimeUs > dVar.c.presentationTimeUs) {
                    return 1;
                }
                return 0;
            }
            throw new RuntimeException("another is not a Sample object");
        }
    }

    public void a() {
    }

    public int a(h hVar) throws IOException {
        e.a(a, "VideoConvertor: mediaFileType=" + hVar.d[0].q);
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        this.l = hVar;
        this.O = false;
        try {
            int i2;
            if (hVar.d.length == 0 || hVar.b == null) {
                RuntimeException runtimeException = new RuntimeException("illegal input parameters");
            }
            for (i2 = 0; i2 < hVar.d.length; i2++) {
                StringBuilder stringBuilder = new StringBuilder("input: " + hVar.d[i2].b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuilder.append(" (" + hVar.d[i2].e + " ms -> " + hVar.d[i2].f + " ms ). Filters=" + hVar.d[i2].k + " speed=" + hVar.d[i2].c);
                if (hVar.d[i2].c == 0.0d) {
                    throw new RuntimeException("speed of input file " + i2 + " is zero!");
                }
                e.d(true, b, stringBuilder.toString());
            }
            e.c(true, b, "audio: " + hVar.f);
            e.c(true, b, "output: " + hVar.b);
            if (hVar.c != null) {
                hVar.c.a();
            }
            this.G = hVar.k;
            for (i2 = 0; i2 < hVar.d.length; i2++) {
                this.G = (long) (((double) this.G) + (((double) ((hVar.d[i2].f * 1000) - (hVar.d[i2].e * 1000))) / hVar.d[i2].c));
            }
            long[] jArr = this.F;
            this.F[1] = 0;
            jArr[0] = 0;
            this.q = 0;
            while (this.q < hVar.d.length) {
                e.d(true, b, String.format("deal with file %d", new Object[]{Integer.valueOf(this.q)}));
                a(this.q);
                this.q++;
            }
            if (this.J != 0) {
                this.K = this.J;
                e.c(true, b, "video_actual_duration=" + this.K);
            } else {
                this.K = this.G;
            }
            if (hVar.f != null) {
                this.J = 0;
                a(hVar.f, this.K);
            }
            d();
        } catch (Exception e) {
            e.a(b, e);
            i = 1;
            if (hVar.c != null) {
                e.b(b, "save error");
                hVar.c.c(1);
            }
        }
        if (this.O && hVar.i != null) {
            hVar.i.c();
        }
        if (hVar.c != null) {
            hVar.c.b(i);
        }
        if (hVar.f.endsWith(".mp4.m4a")) {
            new File(hVar.f).delete();
        }
        e.b(b, "Successfully saved the file: " + hVar.b + " . saving total time: " + (((double) (System.currentTimeMillis() - currentTimeMillis)) / 1000.0d) + " filter consume time=" + (((double) this.B) / 1000.0d));
        return i;
    }

    private void a(int i) throws IOException {
        int i2;
        boolean z = true;
        this.n = dji.g.a.a.a(this.l.d[i].b, this.l.d[i].q);
        this.n.a(this.l.d[i].b);
        for (i2 = 0; i2 < this.n.b(); i2++) {
            this.m = this.n.a(i2);
            e.c(true, b, "Input File " + this.l.d[i].b + " Track No. " + i2 + " Format=" + this.m);
        }
        i2 = this.n.h();
        if (i2 >= 0) {
            this.N = true;
            this.m = this.n.a(i2);
            e.c(true, b, "find a video track: " + i2);
            this.n.b(i2);
            this.E = 0;
            int a = dji.midware.media.d.a(this.m.getInteger("width"), this.m.getInteger("height"), 0);
            if (this.o == null || this.o.capacity() < a) {
                this.o = ByteBuffer.allocateDirect(a);
            }
            float f = 0.0f;
            try {
                f = (float) (-this.m.getInteger("rotation"));
            } catch (Exception e) {
            }
            a(this.l.d[i].e * 1000, this.l.d[i].f * 1000, f, this.l.d[i].c, this.l.d[i].k);
        } else {
            z = false;
        }
        this.n.f();
        this.n = null;
        if (!z) {
            e.e(a, "there is no video track in the given input file");
        }
    }

    private void a(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        int i2 = 40;
        e.d(a, "isMuxerInit=" + this.t);
        if (!this.t) {
            this.t = true;
            g();
        }
        if (bufferInfo.size > 0) {
            e.c(true, d, "write sample: track=" + i + " size=" + bufferInfo.size + " flags=" + bufferInfo.flags + " pts=" + bufferInfo.presentationTimeUs);
            if (bufferInfo.size < 40) {
                i2 = bufferInfo.size;
            }
            byte[] bArr = new byte[i2];
            byteBuffer.clear();
            byteBuffer.get(bArr);
            e.c(true, d, dji.midware.util.c.i(bArr));
            this.D.a(i, byteBuffer, bufferInfo, 1);
        }
        this.F[i] = bufferInfo.presentationTimeUs;
        if (System.currentTimeMillis() - this.v > 500) {
            i2 = b();
            if (i2 - this.u > 1) {
                this.u = i2;
                this.v = System.currentTimeMillis();
                if (this.l.c != null) {
                    this.l.c.a(i2);
                }
            }
        }
    }

    public int b() {
        int c = (int) (c() * 100.0d);
        e.c(true, a, "progress: " + c);
        return c;
    }

    private double c() {
        double d;
        String str = a;
        String str2 = "video file=%d, audio file null?=%b, track 0=%d, track 1=%d, max pts=%d";
        Object[] objArr = new Object[5];
        objArr[0] = Integer.valueOf(this.l.d.length);
        objArr[1] = Boolean.valueOf(this.l.f == null);
        objArr[2] = Long.valueOf(this.F[0]);
        objArr[3] = Long.valueOf(this.F[1]);
        objArr[4] = Long.valueOf(this.G);
        e.c(true, str, String.format(str2, objArr));
        if (this.l.f != null) {
            d = ((((double) this.F[1]) / ((double) this.G)) * 0.1d) + ((((double) this.F[0]) / ((double) this.G)) * 0.9d);
        } else {
            d = ((double) this.F[0]) / ((double) this.G);
        }
        if (d < 0.0d) {
            e.b(a, "progress<0: " + 0.0d);
            d = 0.0d;
        }
        if (d <= 1.0d) {
            return d;
        }
        e.b(a, "progress>100: " + 1.0d);
        return 1.0d;
    }

    private void a(String str, long j) throws IOException {
        int i = 1;
        e.c(true, b, "deal with audio audio_input_filepath =" + str);
        this.n = dji.g.a.a.a(str, this.l.g);
        this.n.a(str);
        int g = this.n.g();
        if (g >= 0) {
            this.n.b(g);
            e.d(a, "try to seek to pts=" + this.l.n);
            this.n.a(this.l.n, 0);
            e.d(a, "actually seek to pts=" + this.n.d());
            if (!this.N) {
                i = 0;
            }
            this.E = i;
            b(this.l.n + j);
        }
        this.n.f();
        this.n = null;
    }

    private void a(long j, long j2, double d) {
        e.c(true, b, String.format("copyByCoding: input=%d, f_start=%d, f_end=%d, muxer track=%d, output_start=%d", new Object[]{Long.valueOf(this.n.d()), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(this.E), Long.valueOf(this.J)}));
        long j3 = this.J;
        try {
            this.g = MediaCodec.createEncoderByType(dji.midware.media.d.c[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        f();
        try {
            this.r = MediaCodec.createDecoderByType(this.m.getString("mime"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.x.c();
        a(this.y);
        this.i = new a(this, j, j2, d);
        this.x.j.setOnFrameAvailableListener(this.i);
        b bVar = new b(this);
        this.i.start();
        bVar.start();
        try {
            this.i.join();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        long j4 = ((j3 + j2) - j) - 1;
        if (this.l.e && this.J < j4) {
            e.c(true, this.i.n, "pad a frame with pts=" + j4);
            this.x.a(j4);
            this.J = j4;
        } else if (!this.l.e && this.l.k != 0 && this.E == 0 && this.q == this.l.d.length - 1) {
            this.x.e();
        }
        this.x.a();
        try {
            bVar.join();
        } catch (InterruptedException e32) {
            e32.printStackTrace();
        }
        if (this.C != null) {
            this.C.release();
        }
        this.r.stop();
        this.r.release();
        this.r = null;
        this.x.b();
        this.g.stop();
        this.g.release();
        this.g = null;
        e.c(true, b, "End of copyByCoding(). current output_segment_start_pts_us=" + this.J);
    }

    private void a(long j, long j2, float f, double d, boolean z) {
        e.c(true, b, String.format("dealVideoStream: input pts=%d, cut start=%d, cut end=%d, track=%d, output_start=%d", new Object[]{Long.valueOf(this.n.d()), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(this.E), Long.valueOf(this.J)}));
        if (!this.l.j && this.l.l == 0 && this.l.m == 0) {
            int integer = this.m.getInteger("width");
            int integer2 = this.m.getInteger("height");
            if (integer == 0 || integer2 == 0) {
                this.l.l = 1280;
                this.l.m = 720;
            } else {
                this.l.l = integer;
                this.l.m = integer2;
            }
        }
        this.n.a(j, 0);
        double integer3 = (((double) this.m.getInteger("width")) / ((double) this.m.getInteger("height"))) - (((double) this.l.l) / ((double) this.l.m));
        e.d(a, String.format("resolution: %dx%d ->%dx%d", new Object[]{Integer.valueOf(this.m.getInteger("width")), Integer.valueOf(this.m.getInteger("height")), Integer.valueOf(this.l.l), Integer.valueOf(this.l.m)}) + " diff ratio ratio = " + integer3);
        if (f != 0.0f || Math.abs(integer3) > 1.0E-5d) {
            e.d(a, "save method 1");
            a(j, j2, d);
        } else if (this.l.i != null) {
            e.d(a, "save method 2");
            a(j, j2, d);
        } else if (Math.abs(this.l.d[this.q].c - 1.0d) > 1.0E-5d) {
            e.d(a, "save method 3");
            a(j, j2, d);
        } else if (this.l.d[this.q].q == dji.g.a.a.a.USER_NATIVE_MUSIC || this.l.d[this.q].q == dji.g.a.a.a.USER_NATIVE_VIDEO) {
            e.d(a, "save method 4");
            a(j, j2, d);
        } else {
            this.n.a(j, 0);
            long d2 = j - this.n.d();
            if (d2 < 1000000) {
                e.d(a, "save method 5");
                a(j2 - d2);
                return;
            }
            e.d(a, "save method 6");
            a(j, j2, d);
        }
    }

    private void a(long j, long j2) {
        long j3;
        this.n.a(j, 0);
        long d = this.n.d();
        e.c(true, b, "src_previous_sync=" + d);
        if (j - d == 0) {
            j3 = j + 1;
        } else {
            j3 = j;
        }
        this.n.a(j3, 1);
        long d2 = this.n.d();
        e.c(true, b, "src_next_sync = " + d2);
        if (d2 < 0 || d2 > j2) {
            d2 = j2;
        }
        e.c(true, b, "recode_end = " + d2);
        this.n.a(j3, 0);
        a(j3, d2, 1.0d);
        if (d2 < j2) {
            this.n.a(j3, 1);
            a(j2);
        }
    }

    private void a(long j) {
        e.c(true, b, String.format("copyOrigin: input=%d, end=%d, track=%d, output_start=%d", new Object[]{Long.valueOf(this.n.d()), Long.valueOf(j), Integer.valueOf(this.E), Long.valueOf(this.J)}));
        BufferInfo bufferInfo = new BufferInfo();
        if (this.o == null) {
            this.o = ByteBuffer.allocateDirect(dji.midware.media.d.a(0, 0, 0));
        }
        Object obj = null;
        long d = this.n.d();
        long j2 = -1;
        while (this.n.d() < j && r0 == null) {
            this.o.clear();
            int a = this.n.a(this.o, 0);
            if (a < 0) {
                obj = 1;
            } else {
                int i = 0;
                if ((this.n.c() & 1) > 0) {
                    i = 1;
                }
                bufferInfo.set(0, a, this.J + (this.n.d() - d), i);
                a(this.E, this.o, bufferInfo);
                e.d(a, "write video. track=" + this.E + " pts=" + bufferInfo.presentationTimeUs + " size=" + bufferInfo.size);
                long j3 = bufferInfo.presentationTimeUs;
                do {
                    obj = !this.n.a() ? 1 : null;
                    e.d(a, "video read: track=" + this.n.e() + " pts=" + this.n.d() + " size=" + this.n.a(this.o, 0));
                    if (obj != null) {
                        break;
                    }
                } while (this.n.e() != this.n.h());
                j2 = j3;
            }
        }
        this.J = (this.J + j2) + dji.midware.media.d.e();
        e.c(true, b, "End of copyOrigin(). current output_segment_start_pts_us=" + this.J);
    }

    private void b(long j) {
        Object obj = null;
        e.c(true, b, String.format("copyOrigin: input=%d, end=%d, track=%d, output_start=%d", new Object[]{Long.valueOf(this.n.d()), Long.valueOf(j), Integer.valueOf(this.E), Long.valueOf(this.J)}));
        BufferInfo bufferInfo = new BufferInfo();
        long d = this.n.d();
        PriorityQueue priorityQueue = new PriorityQueue();
        long j2 = -1;
        while (this.n.d() < j && r0 == null) {
            if (this.o == null) {
                this.o = ByteBuffer.allocateDirect(dji.midware.media.b.a);
            }
            this.o.clear();
            int a = this.n.a(this.o, 0);
            if (a < 0) {
                obj = 1;
            } else {
                long j3;
                int i = 0;
                if ((this.n.c() & 1) > 0) {
                    i = 1;
                }
                bufferInfo.set(0, a, (this.J + this.n.d()) - d, i);
                if (bufferInfo.presentationTimeUs > j2) {
                    while (true) {
                        d dVar = (d) priorityQueue.poll();
                        if (dVar == null) {
                            break;
                        }
                        a(dVar.a, ByteBuffer.wrap(dVar.b), dVar.c);
                        e.d(a, "write music. track=" + dVar.a + " pts=" + dVar.c.presentationTimeUs + " size=" + dVar.c.size);
                    }
                    j3 = bufferInfo.presentationTimeUs;
                } else {
                    j3 = j2;
                }
                byte[] bArr = new byte[bufferInfo.size];
                this.o.position(bufferInfo.offset);
                this.o.limit(bufferInfo.offset + bufferInfo.size);
                this.o.get(bArr);
                BufferInfo bufferInfo2 = new BufferInfo();
                bufferInfo2.set(0, bufferInfo.size, bufferInfo.presentationTimeUs, bufferInfo.flags);
                d dVar2 = new d(this.E, bArr, bufferInfo2);
                priorityQueue.add(dVar2);
                e.d(a, "Queue in. src_track=" + this.n.e() + " pts=" + dVar2.c.presentationTimeUs);
                do {
                    obj = !this.n.a() ? 1 : null;
                    e.d(a, "music read: track=" + this.n.e() + " pts=" + this.n.d() + " size=" + this.n.a(this.o, 0));
                    if (obj != null) {
                        break;
                    }
                } while (this.n.e() != this.n.g());
                j2 = j3;
            }
        }
        this.J = (this.J + j2) + dji.midware.media.d.e();
        e.c(true, b, "End of copyOrigin(). current output_segment_start_pts_us=" + this.J);
    }

    private void d() {
        try {
            this.D.d();
            this.D.b();
        } catch (Exception e) {
            e.b(a, "failed to close muxer. maybe it's because the file is empty");
        } finally {
            this.D = null;
        }
        e.c(true, b, "output Muxer has been closed");
    }

    @SuppressLint({"InlinedApi"})
    private void a(Surface surface) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.m.getString("mime"), 1280, 720);
        this.s = false;
        if (this.m.getByteBuffer("csd-0") != null) {
            createVideoFormat.setByteBuffer("csd-0", this.m.getByteBuffer("csd-0"));
            e.c(true, a, "csd-0 = " + dji.midware.util.c.i(this.m.getByteBuffer("csd-0").array()));
            this.s = true;
        }
        if (this.m.getByteBuffer("csd-1") != null) {
            createVideoFormat.setByteBuffer("csd-1", this.m.getByteBuffer("csd-1"));
            e.c(true, a, "csd-1 = " + dji.midware.util.c.i(this.m.getByteBuffer("csd-1").array()));
            this.s = true;
        }
        if (dji.midware.media.d.a() >= 18) {
            createVideoFormat.setInteger("color-format", 2130708361);
        }
        this.r.configure(createVideoFormat, surface, null, 0);
        this.r.start();
        e.c(true, a, "decoder started. decoderOutputSurface=" + surface);
    }

    private void e() {
        e.b(a, "resetDecoder");
        try {
            this.r.stop();
            this.r.release();
        } catch (Exception e) {
        }
        try {
            this.r = MediaCodec.createDecoderByType(this.m.getString("mime"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a(this.y);
    }

    @SuppressLint({"NewApi"})
    private void f() {
        int i = 0;
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(dji.midware.media.d.c[0], this.l.l, this.l.m);
        createVideoFormat.setInteger("bitrate", dji.midware.media.d.n);
        createVideoFormat.setInteger("frame-rate", dji.midware.media.d.c());
        createVideoFormat.setInteger("i-frame-interval", 1);
        if (dji.midware.media.d.a() >= 18) {
            createVideoFormat.setInteger("color-format", 2130708361);
            e.d(a, "encoderFormat=" + createVideoFormat);
            this.g.configure(createVideoFormat, null, null, 1);
            this.C = this.g.createInputSurface();
            if (this.C == null) {
                throw new RuntimeException("encoderInputSurface==null");
            }
        }
        int[] h = dji.midware.media.d.h();
        int i2 = 0;
        while (i < h.length && i2 == 0) {
            this.h = h[i];
            createVideoFormat.setInteger("color-format", this.h);
            Log.i(a, "try to set color of encoder as " + this.h);
            try {
                this.g.configure(createVideoFormat, null, null, 1);
                i2 = 1;
            } catch (Exception e) {
                Log.i(a, "Failed to set color of encoder as " + this.h);
            }
            i++;
        }
        if (i2 != 0) {
            e.a("SUCCESSFULLY set the color of encoder as " + this.h);
        } else {
            throw new RuntimeException("The encoder's YUV format is not supported by our programs");
        }
        this.g.start();
    }

    private void g() {
        ByteBuffer byteBuffer = null;
        this.D = dji.midware.media.g.e.a(dji.midware.media.g.e.a.FFMPEG);
        e.c(true, a, "output width=" + this.l.l + ", output_height=" + this.l.m);
        try {
            this.D.a(this.l.b);
            if (this.N) {
                if (this.L == null) {
                    e.c(true, a, "set video format in initMuxer()");
                    if (this.g != null) {
                        ByteBuffer byteBuffer2;
                        e.d(a, "init muxer's format by our definition due to recoding");
                        this.L = MediaFormat.createVideoFormat(dji.midware.media.d.c[0], this.l.l, this.l.m);
                        this.L.setInteger("frame-rate", 30);
                        this.L.setFloat("frame-rate", 30.0f);
                        if (this.g.getOutputFormat() != null) {
                            byteBuffer2 = this.g.getOutputFormat().getByteBuffer("csd-0");
                            byteBuffer = this.g.getOutputFormat().getByteBuffer("csd-1");
                            e.d(a, "csd get 1");
                        } else {
                            byteBuffer2 = null;
                        }
                        if ((byteBuffer2 == null || byteBuffer2.capacity() == 0) && this.m != null) {
                            byteBuffer2 = this.m.getByteBuffer("csd-0");
                            e.d(a, "csd get 2");
                        }
                        if ((byteBuffer == null || byteBuffer.capacity() == 0) && this.m != null) {
                            byteBuffer = this.m.getByteBuffer("csd-1");
                            e.d(a, "csd get 3");
                        }
                        if (byteBuffer2 != null) {
                            e.d(a, "csd_0=" + byteBuffer2);
                            this.L.setByteBuffer("csd-0", byteBuffer2);
                        }
                        if (byteBuffer != null) {
                            e.d(a, "csd_1=" + byteBuffer);
                            this.L.setByteBuffer("csd-1", byteBuffer);
                        }
                    } else {
                        e.d(a, "init muxer's format using demuxer's format. demuxer's format=" + this.m);
                        this.L = this.m;
                    }
                }
                this.L.setLong("durationUs", this.G);
                Log.i(b, "muxer video format=" + this.L.toString());
                this.D.a(this.L);
            }
            if (this.l.f != null) {
                dji.midware.media.d.d a = dji.g.a.a.a(this.l.f, this.l.g);
                try {
                    a.a(this.l.f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int g = a.g();
                e.c(true, b, "find an audio track: " + g);
                if (g >= 0) {
                    MediaFormat a2 = a.a(g);
                    if ("audio/ffmpeg".equals(a2.getString("mime"))) {
                        a2.setString("mime", "audio/mp4a-latm");
                    }
                    a2.setLong("durationUs", this.G);
                    Log.i(b, "input audio format=" + a2.toString());
                    Log.i(b, "output audio format=" + a2.toString());
                    this.D.a(a2);
                }
            }
            this.D.c();
            e.e(a, "max_pts=" + this.G);
        } catch (Exception e2) {
            e.a(b, e2);
            throw new RuntimeException("failed to create output file");
        }
    }
}
