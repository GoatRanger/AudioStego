package dji.g.b.a;

import android.media.MediaFormat;
import dji.g.a.a;
import dji.g.b.b;
import dji.g.b.e;
import dji.midware.media.d.d;
import dji.midware.util.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Vector;

public class f {
    int a;
    int b = -1;
    boolean c;
    private String d = "VideoPreview_FrameReader";
    private final int e = 50;
    private long f = 0;
    private Vector<MediaFormat> g = new Vector();
    private Vector<MediaFormat> h = new Vector();
    private Vector<d> i = new Vector();
    private Vector<e> j = new Vector();
    private Vector<Double> k = new Vector();
    private Vector<Long> l = new Vector();
    private String m;

    public f(String str) {
        this.m = str;
        this.a = 0;
        this.c = true;
    }

    protected String a() {
        if (this.m == null) {
            return this.d;
        }
        return this.d + "_" + this.m;
    }

    public boolean b() {
        return this.a >= this.j.size();
    }

    public void c() {
        int i = 0;
        while (this.i != null && i < this.i.size()) {
            d dVar = (d) this.i.get(i);
            if (dVar != null) {
                dVar.f();
            }
            i++;
        }
        if (this.i != null) {
            this.i.clear();
        }
        if (this.g != null) {
            this.g.clear();
        }
    }

    public int a(long j) {
        if (j < 0) {
            return -1;
        }
        int i = 0;
        while (i < this.j.size() && j >= ((Long) this.l.get(i)).longValue()) {
            i++;
        }
        if (i >= this.j.size()) {
            return this.j.size() - 1;
        }
        return i;
    }

    public int a(int i, long j) {
        long longValue = (j - (i == 0 ? 0 : ((Long) this.l.get(i - 1)).longValue())) - (((e) this.j.get(i)).g * 1000);
        if (longValue < 0) {
            return 1;
        }
        if (((long) (((Double) this.k.get(i)).doubleValue() * ((double) longValue))) + (((e) this.j.get(i)).e * 1000) >= ((e) this.j.get(i)).f * 1000) {
            return 3;
        }
        return 2;
    }

    public long b(int i, long j) {
        long j2 = 0;
        long longValue = (j - (i == 0 ? 0 : ((Long) this.l.get(i - 1)).longValue())) - (((e) this.j.get(i)).g * 1000);
        if (longValue >= 0) {
            j2 = longValue;
        }
        j2 = ((long) (((Double) this.k.get(i)).doubleValue() * ((double) j2))) + (((e) this.j.get(i)).e * 1000);
        if (j2 > (((e) this.j.get(i)).f - 50) * 1000) {
            return ((((e) this.j.get(i)).f - 50) * 1000) + 1;
        }
        return j2;
    }

    public int a(ByteBuffer byteBuffer) {
        this.b = ((d) this.i.get(this.a)).a(byteBuffer, 0);
        return this.b;
    }

    public int d() {
        int i;
        int i2 = 4;
        int c = ((d) this.i.get(this.a)).c();
        if (this.b >= 0) {
            if (this.c) {
                dji.midware.media.e.c(false, b.b, "reader sees the first");
                i = 1;
                this.c = false;
            } else {
                i = 0;
            }
            if ((c & 1) != 0) {
                i |= 1;
            }
        } else if (this.a == this.j.size() - 1) {
            i = 4;
        } else {
            i = 0;
        }
        if (!b()) {
            i2 = 0;
        }
        return i | i2;
    }

    public long e() {
        long d = ((d) this.i.get(this.a)).d();
        if (d >= 0) {
            return d;
        }
        dji.midware.media.e.d(this.d, "original re" + d);
        return 0;
    }

    public long f() {
        return g.a(this.a, e());
    }

    public int b(long j) {
        return g.a(j);
    }

    public long c(long j) {
        return g.b(j);
    }

    public long c(int i, long j) {
        if (i >= this.j.size()) {
            throw new RuntimeException("invalid file index from output of decoder. fileIndex=" + i + " inputfile.size=" + this.j.size());
        } else if (i < 0 || j < 0) {
            return -1;
        } else {
            long j2;
            if (i == 0) {
                j2 = 0;
            } else {
                j2 = ((Long) this.l.get(i - 1)).longValue();
            }
            j2 = ((((e) this.j.get(i)).g * 1000) + j2) - 1;
            long j3 = ((e) this.j.get(i)).e * 1000;
            if (j < j3) {
                return -1;
            }
            if (j > (((e) this.j.get(i)).f - 50) * 1000) {
                j2 = ((((((e) this.j.get(i)).f * 1000) - j) + (((e) this.j.get(i)).h * 1000)) - 1) + j2;
            }
            return ((long) (((double) (j - j3)) / ((Double) this.k.get(i)).doubleValue())) + j2;
        }
    }

    public void a(int i) {
        dji.midware.media.e.d(true, this.d, "removeInputFile index=" + i);
        this.j.remove(i);
        f(i);
        l();
        d(0);
    }

    public void a(int i, e eVar) {
        dji.midware.media.e.d(true, dji.g.b.d.b, "addInputFile index=" + i + " inputFile=" + eVar.b);
        this.j.add(i, eVar);
        try {
            e(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        l();
        d(0);
    }

    public void a(int i, double d) {
        if (((Double) this.k.get(i)).doubleValue() != d) {
            this.k.set(i, Double.valueOf(d));
            l();
        }
    }

    public double b(int i) {
        return ((Double) this.k.get(i)).doubleValue();
    }

    private void e(int i) throws IOException {
        int i2 = 0;
        e eVar = (e) this.j.get(i);
        dji.midware.media.e.c(true, dji.g.b.d.b, String.format("deal with file %s", new Object[]{eVar.b}));
        d a = a.a(eVar.b, eVar.q);
        a.a(eVar.b);
        while (i2 < a.b()) {
            dji.midware.media.e.c(true, dji.g.b.d.b, "Input File " + eVar.b + ". Track No. " + i2 + " Format=" + a.a(i2));
            i2++;
        }
        i2 = a.h();
        if (i2 >= 0) {
            MediaFormat a2 = a.a(i2);
            dji.midware.media.e.d(true, dji.g.b.d.b, "find a video track: " + i2);
            a.b(i2);
            this.i.add(i, a);
            this.g.add(i, a2);
            MediaFormat createVideoFormat = MediaFormat.createVideoFormat(a2.getString("mime"), a2.getInteger("width"), a2.getInteger("height"));
            if (a2.getByteBuffer("csd-0") != null) {
                createVideoFormat.setByteBuffer("csd-0", a2.getByteBuffer("csd-0"));
                dji.midware.media.e.c(true, this.d, "csd-0 = " + c.i(a2.getByteBuffer("csd-0").array()));
            } else {
                dji.midware.media.e.c(true, this.d, "csd-0 = None ");
            }
            if (a2.getByteBuffer("csd-1") != null) {
                createVideoFormat.setByteBuffer("csd-1", a2.getByteBuffer("csd-1"));
                dji.midware.media.e.c(true, this.d, "csd-1 = " + c.i(a2.getByteBuffer("csd-1").array()));
            } else {
                dji.midware.media.e.c(true, this.d, "csd-1 = None ");
            }
            if (dji.midware.media.d.a() >= 18) {
                createVideoFormat.setInteger("color-format", 2130708361);
            }
            this.h.add(i, createVideoFormat);
            this.k.add(i, Double.valueOf(eVar.c));
            return;
        }
        throw new RuntimeException("there is no video track in the given input file");
    }

    private void f(int i) {
        ((d) this.i.get(i)).f();
        this.i.remove(i);
        this.g.remove(i);
        this.h.remove(i);
    }

    private void l() {
        this.l.clear();
        for (int i = 0; i < this.j.size(); i++) {
            long j = 0;
            if (i > 0) {
                j = ((Long) this.l.get(i - 1)).longValue();
            }
            j += ((((e) this.j.get(i)).g * 1000) + (((e) this.j.get(i)).h * 1000)) + ((long) (((double) ((((e) this.j.get(i)).f * 1000) - (((e) this.j.get(i)).e * 1000))) / ((Double) this.k.get(i)).doubleValue()));
            this.l.add(Long.valueOf(j));
            dji.midware.media.e.c(true, dji.g.b.d.b, "extractorSwitchPoint[" + i + "]=" + j);
        }
    }

    public MediaFormat g() {
        if (this.a < 0 || this.a >= this.h.size()) {
            return null;
        }
        return (MediaFormat) this.h.get(this.a);
    }

    public int h() {
        return this.a;
    }

    public MediaFormat c(int i) {
        return (MediaFormat) this.g.get(i);
    }

    public void d(long j) {
        this.a = a(j);
        dji.midware.media.e.c(true, this.d, "seek to: " + j + " lies at seg No. " + this.a);
        ((d) this.i.get(this.a)).a(b(this.a, j), 0);
        this.c = true;
    }

    public boolean i() {
        if (b()) {
            return false;
        }
        if (((d) this.i.get(this.a)).a() && ((d) this.i.get(this.a)).d() < ((e) this.j.get(this.a)).f * 1000) {
            return true;
        }
        this.a++;
        this.c = true;
        if (b()) {
            return false;
        }
        dji.midware.media.e.c(true, this.d, "advance to segment No. " + this.a);
        ((d) this.i.get(this.a)).a(((e) this.j.get(this.a)).e * 1000, 0);
        return true;
    }

    public void j() {
        this.f = 0;
        for (int i = 0; i < this.j.size(); i++) {
            this.f = (long) (((double) this.f) + ((((double) (((e) this.j.get(i)).g * 1000)) + (((double) ((((e) this.j.get(i)).f * 1000) - (((e) this.j.get(i)).e * 1000))) / ((Double) this.k.get(i)).doubleValue())) + ((double) (((e) this.j.get(i)).h * 1000))));
        }
        dji.midware.media.e.d(a(), "updateMaxPts=" + this.f);
    }

    public long k() {
        return this.f;
    }

    public long d(int i) {
        return ((Long) this.l.get(i)).longValue();
    }
}
