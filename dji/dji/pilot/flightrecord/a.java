package dji.pilot.flightrecord;

import android.content.Context;
import android.util.Log;
import dji.gs.e.b;
import dji.pilot.fpv.model.f;
import dji.pilot.fpv.model.h;
import dji.pilot.fpv.model.i;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class a {
    private static final String a = a.class.getName();
    private Context b;
    private RandomAccessFile c;
    private f d;
    private List<h> e;
    private b f = new b(0.0d, 0.0d);
    private b g = new b(0.0d, 0.0d);
    private float h;
    private long i;
    private long j;
    private int k;
    private float l = 0.0f;
    private float m = 0.0f;
    private float n = 0.0f;
    private long o;
    private long p;
    private int q;
    private int r;

    public a(Context context) {
        this.b = context;
    }

    public void a(File file) {
        this.d = new f();
        try {
            this.c = new RandomAccessFile(file, "rw");
            this.d.q = file.getAbsolutePath();
            this.d.r = file.getName();
            byte[] bArr = new byte[100];
            this.c.read(bArr, 0, 100);
            this.d.c(bArr);
            if ((this.d.l <= (short) 0 || this.d.m <= (short) 0) && a() != -1) {
                this.e = i.f(this.b, this.d);
                if (this.e != null) {
                    b();
                    Log.e(a, "Fix ModelList size: " + this.e.size());
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e2) {
        }
    }

    protected int a() throws IOException {
        this.c.seek(12);
        int read = this.c.read();
        int read2 = this.c.read();
        if (dji.pilot.fpv.model.h.a.find(read) != dji.pilot.fpv.model.h.a.v && read2 != 0) {
            this.c.seek((long) (read2 + 14));
            if (dji.pilot.fpv.model.h.a.find(this.c.read()) != dji.pilot.fpv.model.h.a.u) {
                return -1;
            }
            this.d.m = (short) 6;
            return 12;
        } else if (read != 0 && read2 != 0) {
            return -1;
        } else {
            this.c.seek(100);
            read = this.c.read();
            read2 = this.c.read();
            if (dji.pilot.fpv.model.h.a.find(read) == dji.pilot.fpv.model.h.a.v) {
                return -1;
            }
            this.c.seek((long) (read2 + 102));
            if (dji.pilot.fpv.model.h.a.find(this.c.read()) != dji.pilot.fpv.model.h.a.u) {
                return -1;
            }
            this.d.m = (short) 8;
            return 100;
        }
    }

    protected void b() throws IOException {
        for (h hVar : this.e) {
            if (hVar.l != null) {
                this.d.W = 2;
                this.d.T = hVar.l.e();
                this.d.U = hVar.l.f();
                this.d.V = hVar.l.g();
                this.d.S = hVar.l.d();
                this.d.W = hVar.l.a;
                this.d.R = hVar.l.c();
                this.d.X = hVar.l.a();
            }
            if (hVar.a != null) {
                float ySpeed = ((float) hVar.a.getYSpeed()) * 0.1f;
                float xSpeed = ((float) hVar.a.getXSpeed()) * 0.1f;
                ySpeed = (float) Math.sqrt((double) ((ySpeed * ySpeed) + (xSpeed * xSpeed)));
                xSpeed = ((float) hVar.a.getZSpeed()) * 0.1f;
                float height = ((float) hVar.a.getHeight()) * 0.1f;
                if (ySpeed > this.l && f.a(ySpeed)) {
                    this.l = ySpeed;
                }
                if (xSpeed > this.m && f.b(xSpeed)) {
                    this.m = xSpeed;
                }
                if (height > this.n) {
                    this.n = height;
                }
                this.g.b = hVar.a.getLatitude();
                this.g.c = hVar.a.getLongitude();
                if (!this.f.a(this.g) && this.g.a()) {
                    if (this.f.a()) {
                        this.h = (float) (dji.gs.utils.a.a(this.g.b, this.g.c, this.f.b, this.f.c) + ((double) this.h));
                    }
                    this.f.b = this.g.b;
                    this.f.c = this.g.c;
                }
                this.k++;
            }
            if (hVar.k != null) {
                if (this.i == 0) {
                    this.i = hVar.k.e();
                }
                if (hVar.k.e() != 0) {
                    this.j = hVar.k.e();
                }
                if (hVar.k.a() == (byte) 1) {
                    this.q++;
                }
                if (hVar.k.b() == (byte) 1) {
                    this.o = this.j;
                } else if (hVar.k.b() == (byte) 2) {
                    this.p = this.j;
                    this.r += (int) (((double) (this.p - this.o)) / 1000.0d);
                }
            }
        }
        this.i = this.j - this.i;
        this.d.l = (short) 400;
        this.d.F = this.h;
        this.d.G = (int) this.i;
        this.d.B = this.k;
        this.d.H = this.n;
        this.d.H = this.l;
        this.d.J = this.m;
        this.d.E = this.g.b;
        this.d.D = this.g.c;
        this.d.K = this.q;
        this.d.L = (long) this.r;
        this.d.C = this.j;
        i.c(this.b, this.d);
        c();
    }

    protected void c() throws IOException {
        this.c.seek(10);
        this.c.write(this.d.m);
        this.c.getFD().sync();
        this.c.close();
    }
}
