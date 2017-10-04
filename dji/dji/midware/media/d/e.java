package dji.midware.media.d;

import android.media.MediaFormat;
import dji.midware.media.b;
import dji.midware.media.d;
import dji.midware.natives.FPVController;
import dji.midware.util.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class e extends c implements d {
    public static final String a = "FFMpegDemuxer";
    private static final boolean f = false;
    int d = 0;
    int e = 0;
    private long g = 0;
    private ByteBuffer h = ByteBuffer.allocateDirect(2048);
    private ByteBuffer i;
    private Vector<MediaFormat> j = new Vector();
    private Set<Integer> k = new HashSet();
    private int l;
    private long m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private int u;
    private int v = -1;
    private boolean w = false;

    public e() {
        dji.midware.media.e.d(a, "create a FFMpegDemuxer");
    }

    public boolean a() {
        int i = b.a;
        j();
        int a = d.a(this.d, this.e, 0);
        if (a >= b.a) {
            i = a;
        }
        if (this.i == null || this.i.capacity() < i) {
            this.i = ByteBuffer.allocateDirect(i);
        }
        this.s = -1;
        this.t = false;
        while (!this.t && !this.k.contains(Integer.valueOf(this.s))) {
            this.t = FPVController.jni_demuxer_readSample(this.g, this.h, this.i, false);
            if (this.t) {
                break;
            }
            this.h.clear();
            this.h.order(ByteOrder.LITTLE_ENDIAN);
            this.m = this.h.getLong();
            i = this.h.getInt();
            this.l = 0;
            if ((i & 1) != 0) {
                this.l |= 1;
            }
            this.o = this.h.getInt();
            i = this.h.getInt();
            this.p = i;
            this.n = i;
            if (this.n > this.i.capacity()) {
                dji.midware.media.e.b(a, "the input H264 frame is larger than the allocated bytebuffer. sampleSize=" + this.n + " bytebuffer's capacity=" + this.i.capacity());
                this.q = 0;
                this.n = 0;
            }
            this.s = this.h.getInt();
        }
        if (!this.t) {
            this.u = -1;
            this.r = 0;
            MediaFormat mediaFormat = (MediaFormat) this.j.get(this.s);
            while (true) {
                i = this.h.getInt();
                if (i != 0) {
                    byte[] bArr;
                    switch (i) {
                        case 1:
                            i = this.h.getInt();
                            a = this.h.getInt();
                            dji.midware.media.e.d(a, "sps offset=" + i + ", size=" + a);
                            bArr = new byte[a];
                            this.i.position(i);
                            this.i.get(bArr, 0, a);
                            dji.midware.media.e.d(a, "csd-0=" + c.i(bArr));
                            break;
                        case 2:
                            i = this.h.getInt();
                            a = this.h.getInt();
                            dji.midware.media.e.d(a, "pps offset=" + i + ", size=" + a);
                            bArr = new byte[a];
                            this.i.position(i);
                            this.i.get(bArr, 0, a);
                            dji.midware.media.e.d(a, "csd-1=" + c.i(bArr));
                            break;
                        case 3:
                            this.u = this.h.getInt();
                            break;
                        case 4:
                            this.r = this.h.getInt();
                            break;
                        default:
                            break;
                    }
                }
                switch (((MediaFormat) this.j.get(this.s)).getInteger("mediaType")) {
                    case 1:
                        if (this.u > 0 && this.u < this.r) {
                            this.q = this.u;
                            break;
                        }
                        this.q = this.r;
                        break;
                        break;
                    default:
                        this.q = 0;
                        break;
                }
                if (this.n > 0 && this.n >= this.q) {
                    this.n -= this.q;
                }
                dji.midware.media.e.c(false, a, String.format("sei_offset=%d frameDataOffset=%d sampleOffset=%d filter_in=%d filter_out=%d sampleSize=%d", new Object[]{Integer.valueOf(this.u), Integer.valueOf(this.r), Integer.valueOf(this.q), Integer.valueOf(this.o), Integer.valueOf(this.p), Integer.valueOf(this.n)}));
            }
        }
        if (this.t) {
            return false;
        }
        return true;
    }

    public int b() {
        j();
        if (this.v == -1) {
            this.v = FPVController.jni_demuxer_getTrackCount(this.g);
            dji.midware.media.e.b(a, "trackCount=" + this.v);
        }
        return this.v;
    }

    public int c() {
        j();
        if (!this.w) {
            this.w = true;
            a();
        }
        return this.l;
    }

    public long d() {
        j();
        if (!this.w) {
            this.w = true;
            a();
        }
        return this.m;
    }

    public MediaFormat a(int i) {
        j();
        if (i < this.j.size()) {
            return (MediaFormat) this.j.get(i);
        }
        throw new RuntimeException("invalid track index. index=" + i + " formats.size()=" + this.j.size());
    }

    public void a(String str) throws IOException {
        dji.midware.media.e.d(a, "setDataSource");
        long jni_demuxer_init = FPVController.jni_demuxer_init(str);
        if (jni_demuxer_init == 0) {
            dji.midware.media.e.b(a, "file " + str + " is not opened successfully");
            throw new IOException("file " + str + " is not opened successfully");
        }
        int i;
        this.g = jni_demuxer_init;
        for (i = 0; i < b(); i++) {
            FPVController.jni_demuxer_getTrackFormat(this.g, i, this.h);
            this.h.clear();
            this.h.order(ByteOrder.LITTLE_ENDIAN);
            MediaFormat mediaFormat = new MediaFormat();
            int i2 = this.h.getInt();
            byte[] bArr = new byte[96];
            this.h.get(bArr);
            String str2 = new String(bArr, 0, i2, "UTF-8");
            if (str2.equalsIgnoreCase("video/h264")) {
                str2 = d.c[0];
            } else if (str2.equalsIgnoreCase("video/mpeg4")) {
                str2 = d.c[1];
            }
            if (str2.equalsIgnoreCase("audio/aac")) {
                str2 = d.d[0];
            }
            if (str2.equalsIgnoreCase("audio/ffmpeg")) {
                str2 = d.d[0];
            }
            mediaFormat.setString("mime", str2);
            int i3 = this.h.getInt();
            mediaFormat.setInteger("mediaType", i3);
            int i4;
            if (i3 == 1) {
                byte[] bArr2;
                ByteBuffer wrap;
                i3 = this.h.getInt();
                i2 = this.h.getInt();
                if (i3 > this.d) {
                    this.d = i3;
                }
                if (i2 > this.e) {
                    this.e = i2;
                }
                long j = this.h.getLong();
                i4 = this.h.getInt();
                mediaFormat.setInteger("width", i3);
                mediaFormat.setInteger("height", i2);
                mediaFormat.setLong("durationUs", j);
                mediaFormat.setInteger("rotation", i4);
                dji.midware.media.e.d(a, "width=" + i3);
                dji.midware.media.e.d(a, "height=" + i2);
                dji.midware.media.e.d(a, "duration=" + j);
                dji.midware.media.e.d(a, "rotation=" + i4);
                this.h.getInt();
                this.h.getInt();
                this.h.getInt();
                this.h.getInt();
                i3 = this.h.getInt();
                if (i3 != 0) {
                    bArr2 = new byte[i3];
                    this.h.get(bArr2);
                    this.h.position(this.h.position() + (200 - i3));
                    wrap = ByteBuffer.wrap(bArr2, 0, i3);
                    mediaFormat.setByteBuffer("csd-0", wrap);
                    dji.midware.media.e.d(a, "csd-0=" + c.i(bArr2));
                    dji.midware.media.e.d(a, "FFMpegDemuxer gets csd-0: " + wrap);
                } else {
                    this.h.position(this.h.position() + 200);
                }
                i3 = this.h.getInt();
                if (i3 != 0) {
                    bArr2 = new byte[i3];
                    this.h.get(bArr2);
                    this.h.position(this.h.position() + (100 - i3));
                    wrap = ByteBuffer.wrap(bArr2, 0, i3);
                    mediaFormat.setByteBuffer("csd-1", wrap);
                    dji.midware.media.e.d(a, "csd-1=" + c.i(bArr2));
                    dji.midware.media.e.d(a, "FFMpegDemuxer gets csd-1: " + wrap);
                } else {
                    this.h.position(this.h.position() + 100);
                }
            } else if (i3 == 2) {
                this.h.getInt();
                this.h.getInt();
                mediaFormat.setLong("durationUs", this.h.getLong());
                this.h.getInt();
                i3 = this.h.getInt();
                i2 = this.h.getInt();
                i4 = this.h.getInt();
                int i5 = this.h.getInt();
                mediaFormat.setInteger("channel-count", i3);
                mediaFormat.setInteger("sample-rate", i2);
                mediaFormat.setInteger("aac-profile", i4);
                mediaFormat.setInteger("sample_format", i5);
                mediaFormat.setByteBuffer("csd-0", b.a(i4, i2, i3));
            }
            this.j.add(mediaFormat);
        }
        for (i = 0; i < this.j.size(); i++) {
            dji.midware.media.e.d(a, "format No. " + i + " : " + this.j.get(i));
        }
        this.w = false;
    }

    public int a(ByteBuffer byteBuffer, int i) {
        j();
        if (this.t) {
            return -1;
        }
        if (!this.w) {
            this.w = true;
            a();
        }
        int capacity = byteBuffer.capacity() - i;
        if (this.n < capacity) {
            capacity = this.n;
        }
        byteBuffer.clear();
        byteBuffer.position(i);
        this.i.clear();
        this.i.position(this.q);
        this.i.limit(this.q + capacity);
        byteBuffer.put(this.i);
        byteBuffer.position(i);
        byteBuffer.limit(i + capacity);
        return capacity;
    }

    public void a(long j, int i) {
        boolean z;
        j();
        switch (i) {
            case 0:
                z = true;
                break;
            case 1:
                z = false;
                break;
            default:
                throw new RuntimeException("unsupported mode");
        }
        int i2 = -1;
        if (!this.k.isEmpty()) {
            i2 = ((Integer) this.k.iterator().next()).intValue();
        }
        FPVController.jni_demuxer_seekTo(this.g, i2, j, z);
        this.w = false;
    }

    public int e() {
        if (!this.w) {
            this.w = true;
            a();
        }
        return this.s;
    }

    public void b(int i) {
        if (i >= b()) {
            throw new RuntimeException("invalid track index: index=" + i + " track count=" + b());
        }
        this.k.add(Integer.valueOf(i));
    }

    public void c(int i) {
        this.k.remove(Integer.valueOf(i));
    }

    public void f() {
        if (this.g != 0) {
            FPVController.jni_demuxer_release(this.g);
            this.g = 0;
        }
    }

    private void j() {
        if (this.g == 0) {
            throw new RuntimeException("the data source is not set");
        }
    }

    public boolean i() {
        return this.t;
    }
}
