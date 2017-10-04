package dji.g.b;

import dji.g.a.a.a;
import dji.pilot.usercenter.protocol.d;

public class e {
    public static final String a = "InputFileParam";
    public String b;
    public double c = 1.0d;
    public double d = 1.0d;
    public long e;
    public long f;
    public long g = 0;
    public long h = 0;
    public double i = 0.0d;
    public boolean j = false;
    public boolean k = false;
    public boolean l = false;
    public double m = 0.0d;
    public double n = 0.0d;
    public double o = 0.0d;
    public double p = 0.0d;
    public a q;

    public e(String str, a aVar, long j, long j2, boolean z, double d) {
        dji.midware.media.e.d(a, "MediaFileType=" + aVar);
        this.q = aVar;
        this.b = str;
        this.e = j;
        this.f = j2;
        this.c = d;
        this.k = z;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void a(double d) {
        this.d = d;
    }

    public void a(long j) {
        this.g = j;
    }

    public void b(long j) {
        this.h = j;
    }

    public void b(double d) {
        this.i = d;
    }

    public void a(double d, double d2, double d3, double d4) {
        if (d < 0.0d || d >= d3 || d > 1.0d || d3 < 0.0d || d3 > 1.0d || d2 < 0.0d || d2 >= d4 || d2 > 1.0d || d4 < 0.0d || d4 > 1.0d) {
            throw new RuntimeException(String.format("invalid parameters (valid ranges are within [0,1]: left=%f, bottom=%f, right=%f, top=%f", new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4)}));
        }
        this.l = true;
        this.m = d;
        this.n = d2;
        this.o = d3;
        this.p = d4;
        dji.midware.media.e.d(a, String.format("SelectedWindow=[left=%f bottom=%f right=%f top=%f]", new Object[]{Double.valueOf(this.m), Double.valueOf(this.n), Double.valueOf(this.o), Double.valueOf(this.p)}));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d.G);
        stringBuilder.append("filePath=" + this.b);
        stringBuilder.append(" start_ms=" + this.e);
        stringBuilder.append(" end_ms=" + this.f);
        stringBuilder.append(" duration_at_begin=" + this.g);
        stringBuilder.append(" duration_at_end=" + this.h);
        stringBuilder.append(" toApplyFilter=" + this.k);
        stringBuilder.append(" setOutputRange=" + this.l);
        stringBuilder.append(" repeat=" + this.j);
        stringBuilder.append(d.H);
        return stringBuilder.toString();
    }
}
