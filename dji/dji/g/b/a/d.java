package dji.g.b.a;

import android.media.MediaFormat;
import dji.g.a.a;
import dji.g.b.e;
import dji.midware.media.b;
import dji.midware.util.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Vector;

public class d {
    public static String a = "AudioPreview_FrameReader";
    int b;
    long c;
    int d = -1;
    boolean e = true;
    long f;
    boolean g = false;
    long h = 0;
    int i = 0;
    ByteBuffer j = ByteBuffer.allocate(b.a);
    private Vector<Long> k = new Vector();
    private Vector<MediaFormat> l = new Vector();
    private Vector<MediaFormat> m = new Vector();
    private Vector<dji.midware.media.d.d> n = new Vector();
    private Vector<e> o = new Vector();
    private Vector<Double> p = new Vector();
    private Vector<Integer> q = new Vector();
    private String r;

    public String a() {
        return a + "_" + this.r;
    }

    public d(String str) {
        this.r = str;
        this.b = 0;
    }

    public boolean b() {
        return false;
    }

    public MediaFormat c() {
        if (this.b < 0 || this.b >= this.m.size()) {
            return null;
        }
        MediaFormat mediaFormat = (MediaFormat) this.m.get(this.b);
        if (mediaFormat != null) {
            return mediaFormat;
        }
        try {
            c(this.b);
            return (MediaFormat) this.m.get(this.b);
        } catch (Exception e) {
            dji.midware.media.e.a(a, e);
            return null;
        }
    }

    public long d() {
        long j = 0;
        long d = e(this.b).d();
        if (d < 0) {
            dji.midware.media.e.d(a(), "original re" + d);
        } else {
            j = d;
        }
        if (this.g) {
            return j + (((long) this.i) * this.h);
        }
        return j;
    }

    public String e() {
        return this.r;
    }

    public long f() {
        return g.a(this.b, d());
    }

    public long g() {
        return this.f;
    }

    public int h() {
        int i;
        int i2 = 4;
        int c = e(this.b).c();
        if (this.d >= 0) {
            if (this.e) {
                dji.midware.media.e.c(false, dji.g.b.b.b, "reader sees the first");
                i = 1;
                this.e = false;
            } else {
                i = 0;
            }
            if ((c & 1) != 0) {
                i |= 1;
            }
        } else if (this.b == this.o.size() - 1) {
            i = 4;
        } else {
            i = 0;
        }
        if (!i()) {
            i2 = 0;
        }
        return i | i2;
    }

    public int a(long j) {
        return g.a(j);
    }

    public long b(long j) {
        int a = g.a(j);
        if (a >= this.o.size()) {
            throw new RuntimeException("invalid file index from output of decoder. fileIndex=" + a + " inputfile.size=" + this.o.size() + " comPts=" + j);
        }
        return ((long) (((double) (g.b(j) - (((e) this.o.get(a)).e * 1000))) / ((Double) this.p.get(a)).doubleValue())) + (a == 0 ? 0 : ((Long) this.k.get(a - 1)).longValue());
    }

    public int a(ByteBuffer byteBuffer) {
        this.d = e(this.b).a(byteBuffer, 0);
        return this.d;
    }

    public boolean i() {
        return this.b >= this.o.size();
    }

    public void j() {
        if (this.n != null) {
            for (int i = 0; i < this.n.size(); i++) {
                dji.midware.media.d.d e = e(i);
                if (e != null) {
                    e.f();
                }
            }
            this.n.clear();
            this.n = null;
        }
        if (this.l != null) {
            this.l.clear();
            this.l = null;
        }
        if (this.m != null) {
            this.m.clear();
            this.m = null;
        }
    }

    private void m() {
        this.f = 0;
        for (int i = 0; i < k(); i++) {
            this.f = (long) (((double) this.f) + (((double) ((((e) this.o.get(i)).f * 1000) - (((e) this.o.get(i)).e * 1000))) / ((Double) this.p.get(i)).doubleValue()));
        }
    }

    public void a(int i) {
        dji.midware.media.e.d(false, dji.g.b.b.b, "removeInputFile index=" + i);
        this.o.remove(i);
        d(i);
        n();
        m();
        d(0);
    }

    public void a(int i, e eVar) {
        dji.midware.media.e.d(false, dji.g.b.b.b, "addInputFile index=" + i + " inputFile=" + eVar);
        this.o.add(i, eVar);
        this.p.add(i, Double.valueOf(eVar.c));
        int i2 = 1;
        double d = eVar.c;
        while (d >= 2.0d) {
            d /= 2.0d;
            i2 *= 2;
        }
        dji.midware.media.e.e(a(), e() + " insert: index=" + i + "  skip=" + i2);
        this.q.add(i, Integer.valueOf(i2));
        this.n.add(i, null);
        this.l.add(i, null);
        this.m.add(i, null);
        n();
        m();
    }

    public int k() {
        return this.o.size();
    }

    public MediaFormat b(int i) {
        MediaFormat mediaFormat = (MediaFormat) this.l.get(i);
        if (mediaFormat != null) {
            return mediaFormat;
        }
        try {
            c(i);
            return (MediaFormat) this.l.get(i);
        } catch (Exception e) {
            dji.midware.media.e.a(a, e);
            return null;
        }
    }

    private void c(int i) throws IOException {
        int i2;
        dji.midware.media.e.b(a, "AudioReader is opening File No. " + i + " Path=" + ((e) this.o.get(i)).b);
        e eVar = (e) this.o.get(i);
        dji.midware.media.e.c(false, dji.g.b.b.b, String.format("deal with file %s", new Object[]{eVar.b}));
        dji.midware.media.d.d a = a.a(eVar.b, eVar.q);
        a.a(eVar.b);
        for (i2 = 0; i2 < a.b(); i2++) {
            dji.midware.media.e.c(false, dji.g.b.b.b, "Input File " + eVar.b + ". Track No. " + i2 + " Format=" + a.a(i2));
        }
        i2 = a.g();
        dji.midware.media.e.c(false, dji.g.b.b.b, "find an audio track: " + i2);
        if (i2 >= 0) {
            MediaFormat a2 = a.a(i2);
            a.b(i2);
            if (i == 0 && eVar.j) {
                this.g = true;
                this.h = a2.getLong("durationUs");
            }
            this.n.set(i, a);
            this.l.set(i, a2);
            if (a2.containsKey("sample-rate")) {
                Integer valueOf = Integer.valueOf(a2.getInteger("sample-rate"));
                if (a2.containsKey("channel-count")) {
                    Integer valueOf2 = Integer.valueOf(a2.getInteger("channel-count"));
                    ByteBuffer byteBuffer = a2.getByteBuffer("csd-0");
                    if (byteBuffer == null) {
                        dji.midware.media.e.b(a(), "No csd-0 in the MediaFormat found");
                    } else {
                        dji.midware.media.e.c(false, a(), "csd-0=" + c.i(dji.thirdparty.afinal.c.c.a(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position())));
                    }
                    ByteBuffer byteBuffer2 = a2.getByteBuffer("csd-1");
                    if (byteBuffer2 == null) {
                        dji.midware.media.e.b(a(), "No csd-1 in the MediaFormat found");
                    } else {
                        dji.midware.media.e.c(false, a(), "csd-1=" + c.i(dji.thirdparty.afinal.c.c.a(byteBuffer2.array(), byteBuffer2.arrayOffset(), byteBuffer2.limit() - byteBuffer2.position())));
                    }
                    dji.midware.media.e.e(a(), "audio's MIME=" + a2.getString("mime"));
                    MediaFormat createAudioFormat = MediaFormat.createAudioFormat(a2.getString("mime"), valueOf.intValue(), valueOf2.intValue());
                    if (byteBuffer != null) {
                        createAudioFormat.setByteBuffer("csd-0", byteBuffer);
                    }
                    if (byteBuffer2 != null) {
                        createAudioFormat.setByteBuffer("csd-1", byteBuffer2);
                    }
                    this.m.set(i, createAudioFormat);
                    return;
                }
                throw new RuntimeException("should set channelCount");
            }
            throw new RuntimeException("should set sampleRate");
        }
        throw new RuntimeException("there is no audio track in the given input file");
    }

    private void d(int i) {
        if (e(i) != null) {
            e(i).f();
        }
        this.n.remove(i);
        this.l.remove(i);
        this.m.remove(i);
    }

    private void n() {
        this.k.clear();
        for (int i = 0; i < this.o.size(); i++) {
            long doubleValue;
            if (i == 0) {
                doubleValue = (long) (((double) ((((e) this.o.get(i)).f * 1000) - (((e) this.o.get(i)).e * 1000))) / ((Double) this.p.get(i)).doubleValue());
            } else {
                doubleValue = ((Long) this.k.get(i - 1)).longValue() + ((long) (((double) ((((e) this.o.get(i)).f * 1000) - (((e) this.o.get(i)).e * 1000))) / ((Double) this.p.get(i)).doubleValue()));
            }
            this.k.add(Long.valueOf(doubleValue));
            dji.midware.media.e.c(false, dji.g.b.b.b, "extractorSwitchPoint[" + i + "]=" + doubleValue);
        }
    }

    public int c(long j) {
        if (this.g) {
            return 0;
        }
        int i = 0;
        while (i < this.o.size() && j >= ((Long) this.k.get(i)).longValue()) {
            i++;
        }
        return i;
    }

    public void d(long j) {
        if (this.g) {
            this.i = (int) (j / this.h);
            j %= this.h;
        }
        this.b = c(j);
        dji.midware.media.e.c(false, a(), "seek to: " + j + " lies at seg No. " + this.b);
        if (this.b < this.o.size()) {
            this.c = this.b == 0 ? 0 : ((Long) this.k.get(this.b - 1)).longValue();
            e(this.b).a((long) (((double) ((j - this.c) + (((e) this.o.get(this.b)).e * 1000))) * ((Double) this.p.get(this.b)).doubleValue()), 0);
            dji.midware.media.e.c(false, a(), " seekTo_pts=" + j + " repeatDuration=" + this.h + " start_pts_in_global=" + this.c + " start_time in this file=" + (((e) this.o.get(this.b)).e * 1000) + " real seeking=" + ((j - this.c) + (((e) this.o.get(this.b)).e * 1000)) + " after seeking, fileIndex=" + this.b + " sampleTime=" + e(this.b).d());
            this.e = true;
        }
    }

    public boolean l() {
        if (i()) {
            return false;
        }
        int i = 1;
        for (int i2 = 0; i2 < ((Integer) this.q.get(this.b)).intValue(); i2++) {
            i = e(this.b).a();
        }
        if (this.g) {
            if (((e(this.b).a(this.j, 0) >= 0 ? 1 : 0) & i) == 0) {
                e(this.b).a(((e) this.o.get(this.b)).e * 1000, 0);
                dji.midware.media.e.d(a(), "sampleTime=" + e(this.b).d());
                this.i++;
            }
            if (d() < ((e) this.o.get(this.b)).f * 1000) {
                return true;
            }
        } else if (i != 0 && e(this.b).d() < ((e) this.o.get(this.b)).f * 1000) {
            return true;
        }
        this.b++;
        this.e = true;
        if (i()) {
            return false;
        }
        dji.midware.media.e.c(false, a(), "advance to segment No. " + this.b);
        this.c = this.b == 0 ? 0 : ((Long) this.k.get(this.b - 1)).longValue();
        e(this.b).a(((e) this.o.get(this.b)).e * 1000, 0);
        return true;
    }

    private dji.midware.media.d.d e(int i) {
        dji.midware.media.d.d dVar = (dji.midware.media.d.d) this.n.get(i);
        if (dVar != null) {
            return dVar;
        }
        try {
            c(i);
            return (dji.midware.media.d.d) this.n.get(i);
        } catch (Exception e) {
            dji.midware.media.e.a(a, e);
            return null;
        }
    }
}
