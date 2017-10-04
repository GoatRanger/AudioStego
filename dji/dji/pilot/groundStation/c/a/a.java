package dji.pilot.groundStation.c.a;

import java.io.Serializable;

public class a implements Serializable {
    private static final long f = 1;
    private double[][] a;
    private int b;
    private int c;
    private int d;
    private int[] e = new int[this.b];

    public a(b bVar) {
        int i;
        this.a = bVar.d();
        this.b = bVar.g();
        this.c = bVar.h();
        for (i = 0; i < this.b; i++) {
            this.e[i] = i;
        }
        this.d = 1;
        double[] dArr = new double[this.b];
        int i2 = 0;
        while (i2 < this.c) {
            int i3;
            for (i = 0; i < this.b; i++) {
                dArr[i] = this.a[i][i2];
            }
            for (i = 0; i < this.b; i++) {
                double[] dArr2 = this.a[i];
                double d = 0.0d;
                for (i3 = 0; i3 < Math.min(i, i2); i3++) {
                    d += dArr2[i3] * dArr[i3];
                }
                d = dArr[i] - d;
                dArr[i] = d;
                dArr2[i2] = d;
            }
            i3 = i2;
            for (i = i2 + 1; i < this.b; i++) {
                if (Math.abs(dArr[i]) > Math.abs(dArr[i3])) {
                    i3 = i;
                }
            }
            if (i3 != i2) {
                for (i = 0; i < this.c; i++) {
                    d = this.a[i3][i];
                    this.a[i3][i] = this.a[i2][i];
                    this.a[i2][i] = d;
                }
                i = this.e[i3];
                this.e[i3] = this.e[i2];
                this.e[i2] = i;
                this.d = -this.d;
            }
            i = i2 < this.b ? 1 : 0;
            if (this.a[i2][i2] != 0.0d) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            if ((i & i3) != 0) {
                for (i = i2 + 1; i < this.b; i++) {
                    double[] dArr3 = this.a[i];
                    dArr3[i2] = dArr3[i2] / this.a[i2][i2];
                }
            }
            i2++;
        }
    }

    public boolean a() {
        for (int i = 0; i < this.c; i++) {
            if (this.a[i][i] == 0.0d) {
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
                if (i > i2) {
                    c[i][i2] = this.a[i][i2];
                } else if (i == i2) {
                    c[i][i2] = 1.0d;
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
                if (i <= i2) {
                    c[i][i2] = this.a[i][i2];
                } else {
                    c[i][i2] = 0.0d;
                }
            }
        }
        return bVar;
    }

    public int[] d() {
        int[] iArr = new int[this.b];
        for (int i = 0; i < this.b; i++) {
            iArr[i] = this.e[i];
        }
        return iArr;
    }

    public double[] e() {
        double[] dArr = new double[this.b];
        for (int i = 0; i < this.b; i++) {
            dArr[i] = (double) this.e[i];
        }
        return dArr;
    }

    public double f() {
        if (this.b != this.c) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        double d = (double) this.d;
        for (int i = 0; i < this.c; i++) {
            d *= this.a[i][i];
        }
        return d;
    }

    public b a(b bVar) {
        if (bVar.g() != this.b) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        } else if (a()) {
            int i;
            int i2;
            int i3;
            double[] dArr;
            int h = bVar.h();
            b a = bVar.a(this.e, 0, h - 1);
            double[][] c = a.c();
            for (i = 0; i < this.c; i++) {
                for (i2 = i + 1; i2 < this.c; i2++) {
                    for (i3 = 0; i3 < h; i3++) {
                        dArr = c[i2];
                        dArr[i3] = dArr[i3] - (c[i][i3] * this.a[i2][i]);
                    }
                }
            }
            for (i3 = this.c - 1; i3 >= 0; i3--) {
                for (i = 0; i < h; i++) {
                    double[] dArr2 = c[i3];
                    dArr2[i] = dArr2[i] / this.a[i3][i3];
                }
                for (i2 = 0; i2 < i3; i2++) {
                    for (i = 0; i < h; i++) {
                        dArr = c[i2];
                        dArr[i] = dArr[i] - (c[i3][i] * this.a[i2][i3]);
                    }
                }
            }
            return a;
        } else {
            throw new RuntimeException("Matrix is singular.");
        }
    }
}
