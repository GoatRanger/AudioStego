package dji.pilot.groundStation.c.a;

import dji.pilot.groundStation.c.a.a.a;
import java.io.Serializable;

public class c implements Serializable {
    private static final long e = 1;
    private double[][] a;
    private int b;
    private int c;
    private double[] d = new double[this.c];

    public c(b bVar) {
        this.a = bVar.d();
        this.b = bVar.g();
        this.c = bVar.h();
        for (int i = 0; i < this.c; i++) {
            int i2;
            double d = 0.0d;
            for (i2 = i; i2 < this.b; i2++) {
                d = a.a(d, this.a[i2][i]);
            }
            if (d != 0.0d) {
                if (this.a[i][i] < 0.0d) {
                    d = -d;
                }
                for (i2 = i; i2 < this.b; i2++) {
                    double[] dArr = this.a[i2];
                    dArr[i] = dArr[i] / d;
                }
                double[] dArr2 = this.a[i];
                dArr2[i] = dArr2[i] + 1.0d;
                for (i2 = i + 1; i2 < this.c; i2++) {
                    int i3;
                    double d2 = 0.0d;
                    for (i3 = i; i3 < this.b; i3++) {
                        d2 += this.a[i3][i] * this.a[i3][i2];
                    }
                    d2 = (-d2) / this.a[i][i];
                    for (i3 = i; i3 < this.b; i3++) {
                        double[] dArr3 = this.a[i3];
                        dArr3[i2] = dArr3[i2] + (this.a[i3][i] * d2);
                    }
                }
            }
            this.d[i] = -d;
        }
    }

    public boolean a() {
        for (int i = 0; i < this.c; i++) {
            if (this.d[i] == 0.0d) {
                return false;
            }
        }
        return true;
    }

    public b b() {
        b bVar = new b(this.b, this.c);
        double[][] c = bVar.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                if (i >= i2) {
                    c[i][i2] = this.a[i][i2];
                } else {
                    c[i][i2] = 0.0d;
                }
            }
        }
        return bVar;
    }

    public b c() {
        b bVar = new b(this.c, this.c);
        double[][] c = bVar.c();
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                if (i < i2) {
                    c[i][i2] = this.a[i][i2];
                } else if (i == i2) {
                    c[i][i2] = this.d[i];
                } else {
                    c[i][i2] = 0.0d;
                }
            }
        }
        return bVar;
    }

    public b d() {
        b bVar = new b(this.b, this.c);
        double[][] c = bVar.c();
        for (int i = this.c - 1; i >= 0; i--) {
            int i2;
            for (i2 = 0; i2 < this.b; i2++) {
                c[i2][i] = 0.0d;
            }
            c[i][i] = 1.0d;
            for (i2 = i; i2 < this.c; i2++) {
                if (this.a[i][i] != 0.0d) {
                    int i3;
                    double d = 0.0d;
                    for (i3 = i; i3 < this.b; i3++) {
                        d += this.a[i3][i] * c[i3][i2];
                    }
                    d = (-d) / this.a[i][i];
                    for (i3 = i; i3 < this.b; i3++) {
                        double[] dArr = c[i3];
                        dArr[i2] = dArr[i2] + (this.a[i3][i] * d);
                    }
                }
            }
        }
        return bVar;
    }

    public b a(b bVar) {
        if (bVar.g() != this.b) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        } else if (a()) {
            int i;
            int i2;
            int h = bVar.h();
            double[][] d = bVar.d();
            for (i = 0; i < this.c; i++) {
                for (int i3 = 0; i3 < h; i3++) {
                    double d2 = 0.0d;
                    for (i2 = i; i2 < this.b; i2++) {
                        d2 += this.a[i2][i] * d[i2][i3];
                    }
                    d2 = (-d2) / this.a[i][i];
                    for (i2 = i; i2 < this.b; i2++) {
                        double[] dArr = d[i2];
                        dArr[i3] = dArr[i3] + (this.a[i2][i] * d2);
                    }
                }
            }
            for (int i4 = this.c - 1; i4 >= 0; i4--) {
                for (i = 0; i < h; i++) {
                    double[] dArr2 = d[i4];
                    dArr2[i] = dArr2[i] / this.d[i4];
                }
                for (i2 = 0; i2 < i4; i2++) {
                    for (i = 0; i < h; i++) {
                        double[] dArr3 = d[i2];
                        dArr3[i] = dArr3[i] - (d[i4][i] * this.a[i2][i4]);
                    }
                }
            }
            return new b(d, this.c, h).a(0, this.c - 1, 0, h - 1);
        } else {
            throw new RuntimeException("Matrix is rank deficient.");
        }
    }
}
