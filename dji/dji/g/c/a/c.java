package dji.g.c.a;

import android.media.MediaCodec.BufferInfo;
import dji.midware.media.e;
import dji.midware.natives.FPVController;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class c implements d {
    public static boolean a = false;
    public static final int b = -1;
    public static final int c = -2;
    public static final int d = -3;
    private static final String h = "FFMpegFilterBase";
    private static final int i = 100000;
    ByteBuffer e = ByteBuffer.allocateDirect(100);
    ByteBuffer f = null;
    ByteBuffer g = null;
    private long j = 0;
    private int k;
    private int[] l;
    private int[] m;
    private int[] n;
    private int o;
    private int p;
    private int q;
    private long r;
    private int s;
    private String t;

    protected abstract String a();

    public c(String str) {
        this.t = str;
    }

    public int a(int i, int[] iArr, int[] iArr2, int[] iArr3, int i2, int i3, int i4) {
        this.k = i;
        this.l = iArr;
        this.m = iArr2;
        this.n = iArr3;
        this.o = i2;
        this.p = i3;
        this.q = i4;
        this.j = FPVController.jni_audio_filters_init(a(), i, iArr, iArr2, iArr3, i2, i3, i4);
        return this.j != 0 ? 0 : -1;
    }

    public void c() {
        if (this.j != 0) {
            FPVController.jni_audio_filters_release(this.j);
        }
        if (this.g != null) {
            this.g.position(0);
            this.g.limit(0);
        }
    }

    public synchronized int a(int i, ByteBuffer byteBuffer, int i2, int i3, long j) {
        int jni_audio_filters_frame_in;
        if (byteBuffer.isDirect() && i2 == 0) {
            jni_audio_filters_frame_in = FPVController.jni_audio_filters_frame_in(this.j, i, byteBuffer, i3, j);
        } else {
            if (this.f == null) {
                this.f = ByteBuffer.allocateDirect(i);
            }
            if (this.f == null) {
                jni_audio_filters_frame_in = 0;
            } else {
                this.f.clear();
                byteBuffer.position(i2);
                byteBuffer.limit(i2 + i3);
                this.f.put(byteBuffer);
                jni_audio_filters_frame_in = FPVController.jni_audio_filters_frame_in(this.j, i, this.f, i3, j);
            }
        }
        return jni_audio_filters_frame_in;
    }

    public synchronized int a(int i, d dVar) {
        int i2;
        if (this.f == null) {
            this.f = ByteBuffer.allocateDirect(i);
        }
        if (this.f == null) {
            i2 = 0;
        } else {
            BufferInfo bufferInfo = new BufferInfo();
            dVar.a(this.f, bufferInfo);
            i2 = a(i, this.f, bufferInfo.offset, bufferInfo.size, bufferInfo.presentationTimeUs);
        }
        return i2;
    }

    protected String d() {
        if (this.t == null) {
            return h;
        }
        return "FFMpegFilterBase_" + this.t;
    }

    public synchronized int a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        int i = 0;
        synchronized (this) {
            if (this.g == null) {
                this.g = ByteBuffer.allocateDirect(i);
                this.g.position(0);
                this.g.limit(0);
            }
            if (this.g != null) {
                int i2;
                byteBuffer.clear();
                if (this.g.remaining() == 0) {
                    i = FPVController.jni_audio_filters_frame_out(this.j, this.g, this.e);
                    if (i == 0) {
                        this.e.clear();
                        this.e.order(ByteOrder.LITTLE_ENDIAN);
                        i2 = (int) this.e.getLong();
                        this.g.position(0);
                        this.g.limit(i2);
                        this.r = (1000000 * this.e.getLong()) / ((long) this.p);
                        this.s = (int) this.e.getLong();
                    }
                }
                if (i == 0) {
                    bufferInfo.offset = 0;
                    bufferInfo.flags = this.s;
                    bufferInfo.presentationTimeUs = this.r;
                    if (byteBuffer.capacity() < this.g.remaining()) {
                        bufferInfo.size = byteBuffer.capacity();
                        i2 = this.g.limit();
                        this.g.limit(this.g.position() + bufferInfo.size);
                        byteBuffer.put(this.g);
                        this.g.limit(i2);
                        this.r += (long) ((bufferInfo.size * 26122) / 4609);
                        e.c(a, h, "the encoder's input buffer is not large enough, split this frame. pts=" + bufferInfo.presentationTimeUs + " capacity=" + byteBuffer.capacity() + " data size=" + bufferInfo.size + " remain=" + this.g);
                    } else {
                        bufferInfo.size = this.g.remaining();
                        byteBuffer.put(this.g);
                    }
                    byteBuffer.position(0);
                    byteBuffer.limit(bufferInfo.size);
                    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                }
            }
        }
        return i;
    }

    public void b() {
        c();
        a(this.k, this.l, this.m, this.n, this.o, this.p, this.q);
    }
}
