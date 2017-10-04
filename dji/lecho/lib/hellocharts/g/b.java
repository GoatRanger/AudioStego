package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import java.lang.reflect.Array;
import lecho.lib.hellocharts.h.a;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.c;
import lecho.lib.hellocharts.model.h;

public class b {
    private static final int a = 2;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final char[] f = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
    private int[] A = new int[4];
    private float[][] B = ((float[][]) Array.newInstance(Float.TYPE, new int[]{4, 0}));
    private float[][] C = ((float[][]) Array.newInstance(Float.TYPE, new int[]{4, 0}));
    private c[][] D = ((c[][]) Array.newInstance(c.class, new int[]{4, 0}));
    private float[][] E = ((float[][]) Array.newInstance(Float.TYPE, new int[]{4, 0}));
    private a[] F = new a[]{new a(), new a(), new a(), new a()};
    private lecho.lib.hellocharts.view.a g;
    private lecho.lib.hellocharts.b.a h;
    private int i;
    private float j;
    private float k;
    private Paint[] l = new Paint[]{new Paint(), new Paint(), new Paint(), new Paint()};
    private Paint[] m = new Paint[]{new Paint(), new Paint(), new Paint(), new Paint()};
    private Paint[] n = new Paint[]{new Paint(), new Paint(), new Paint(), new Paint()};
    private float[] o = new float[4];
    private float[] p = new float[4];
    private float[] q = new float[4];
    private int[] r = new int[4];
    private int[] s = new int[4];
    private int[] t = new int[4];
    private int[] u = new int[4];
    private int[] v = new int[4];
    private int[] w = new int[4];
    private int[] x = new int[4];
    private FontMetricsInt[] y = new FontMetricsInt[]{new FontMetricsInt(), new FontMetricsInt(), new FontMetricsInt(), new FontMetricsInt()};
    private char[] z = new char[64];

    public b(Context context, lecho.lib.hellocharts.view.a aVar) {
        this.g = aVar;
        this.h = aVar.getChartComputator();
        this.j = context.getResources().getDisplayMetrics().density;
        this.k = context.getResources().getDisplayMetrics().scaledDensity;
        this.i = lecho.lib.hellocharts.h.b.a(this.j, 2);
        for (int i = 0; i < 4; i++) {
            this.l[i].setStyle(Style.FILL);
            this.l[i].setAntiAlias(true);
            this.m[i].setStyle(Style.FILL);
            this.m[i].setAntiAlias(true);
            this.n[i].setStyle(Style.STROKE);
            this.n[i].setAntiAlias(true);
        }
    }

    public void a() {
        d();
    }

    public void b() {
        d();
    }

    private void d() {
        a(this.g.getChartData().c(), 0);
        a(this.g.getChartData().a(), 3);
        a(this.g.getChartData().b(), 1);
        a(this.g.getChartData().d(), 2);
    }

    public void c() {
        this.h = this.g.getChartComputator();
    }

    private void a(lecho.lib.hellocharts.model.b bVar, int i) {
        if (bVar != null) {
            b(bVar, i);
            f(bVar, i);
            h(bVar, i);
        }
    }

    private void b(lecho.lib.hellocharts.model.b bVar, int i) {
        c(bVar, i);
        d(bVar, i);
        if (bVar.m()) {
            a(i);
            e(bVar, i);
            return;
        }
        b(i);
    }

    private void c(lecho.lib.hellocharts.model.b bVar, int i) {
        Typeface j = bVar.j();
        if (j != null) {
            this.l[i].setTypeface(j);
            this.m[i].setTypeface(j);
        }
        this.l[i].setColor(bVar.e());
        this.l[i].setTextSize((float) lecho.lib.hellocharts.h.b.c(this.k, bVar.h()));
        this.l[i].getFontMetricsInt(this.y[i]);
        this.m[i].setColor(bVar.e());
        this.m[i].setTextSize((float) lecho.lib.hellocharts.h.b.c(this.k, bVar.h()));
        this.n[i].setColor(bVar.g());
        this.s[i] = Math.abs(this.y[i].ascent);
        this.t[i] = Math.abs(this.y[i].descent);
        this.r[i] = (int) this.l[i].measureText(f, 0, bVar.i());
    }

    private void d(lecho.lib.hellocharts.model.b bVar, int i) {
        this.m[i].setTextAlign(Align.CENTER);
        if (i == 0 || 3 == i) {
            this.l[i].setTextAlign(Align.CENTER);
        } else if (1 == i) {
            if (bVar.f()) {
                this.l[i].setTextAlign(Align.LEFT);
            } else {
                this.l[i].setTextAlign(Align.RIGHT);
            }
        } else if (2 != i) {
        } else {
            if (bVar.f()) {
                this.l[i].setTextAlign(Align.RIGHT);
            } else {
                this.l[i].setTextAlign(Align.LEFT);
            }
        }
    }

    private void a(int i) {
        this.u[i] = ((int) Math.sqrt(Math.pow((double) this.r[i], 2.0d) / 2.0d)) + ((int) Math.sqrt(Math.pow((double) this.s[i], 2.0d) / 2.0d));
        this.v[i] = Math.round(((float) this.u[i]) * h.l);
    }

    private void b(int i) {
        if (1 == i || 2 == i) {
            this.u[i] = this.r[i];
            this.v[i] = this.s[i];
        } else if (i == 0 || 3 == i) {
            this.u[i] = this.s[i] + this.t[i];
            this.v[i] = this.r[i];
        }
    }

    private void e(lecho.lib.hellocharts.model.b bVar, int i) {
        int i2 = 0;
        int sqrt = (int) Math.sqrt(Math.pow((double) this.r[i], 2.0d) / 2.0d);
        int sqrt2 = (int) Math.sqrt(Math.pow((double) this.s[i], 2.0d) / 2.0d);
        int i3;
        if (bVar.f()) {
            if (1 != i) {
                if (2 == i) {
                    sqrt2 = 0;
                    i2 = (-sqrt) / 2;
                } else if (i == 0) {
                    i3 = (sqrt2 + (sqrt / 2)) - this.s[i];
                    sqrt2 = 0;
                    i2 = i3;
                } else {
                    if (3 == i) {
                        sqrt2 = 0;
                        i2 = (-sqrt) / 2;
                    }
                    sqrt2 = 0;
                }
            }
        } else if (1 == i) {
            sqrt2 = 0;
            i2 = (-sqrt) / 2;
        } else if (2 != i) {
            if (i == 0) {
                sqrt2 = 0;
                i2 = (-sqrt) / 2;
            } else {
                if (3 == i) {
                    i3 = (sqrt2 + (sqrt / 2)) - this.s[i];
                    sqrt2 = 0;
                    i2 = i3;
                }
                sqrt2 = 0;
            }
        }
        this.w[i] = sqrt2;
        this.x[i] = i2;
    }

    private void f(lecho.lib.hellocharts.model.b bVar, int i) {
        int i2 = 0;
        if (!bVar.f() && (bVar.c() || !bVar.a().isEmpty())) {
            i2 = 0 + (this.i + this.u[i]);
        }
        a(i2 + g(bVar, i), i);
    }

    private int g(lecho.lib.hellocharts.model.b bVar, int i) {
        if (TextUtils.isEmpty(bVar.b())) {
            return 0;
        }
        return ((0 + this.s[i]) + this.t[i]) + this.i;
    }

    private void a(int i, int i2) {
        if (1 == i2) {
            this.g.getChartComputator().a(i, 0, 0, 0);
        } else if (2 == i2) {
            this.g.getChartComputator().a(0, 0, i, 0);
        } else if (i2 == 0) {
            this.g.getChartComputator().a(0, i, 0, 0);
        } else if (3 == i2) {
            this.g.getChartComputator().a(0, 0, 0, i);
        }
    }

    private void h(lecho.lib.hellocharts.model.b bVar, int i) {
        if (1 == i) {
            if (bVar.f()) {
                this.p[i] = (float) (this.h.b().left + this.i);
                this.o[i] = (float) ((this.h.c().left - this.i) - this.t[i]);
            } else {
                this.p[i] = (float) (this.h.c().left - this.i);
                this.o[i] = ((this.p[i] - ((float) this.i)) - ((float) this.t[i])) - ((float) this.u[i]);
            }
            this.q[i] = (float) this.h.b().left;
        } else if (2 == i) {
            if (bVar.f()) {
                this.p[i] = (float) (this.h.b().right - this.i);
                this.o[i] = (float) ((this.h.c().right + this.i) + this.s[i]);
            } else {
                this.p[i] = (float) (this.h.c().right + this.i);
                this.o[i] = ((this.p[i] + ((float) this.i)) + ((float) this.s[i])) + ((float) this.u[i]);
            }
            this.q[i] = (float) this.h.b().right;
        } else if (3 == i) {
            if (bVar.f()) {
                this.p[i] = (float) ((this.h.b().bottom - this.i) - this.t[i]);
                this.o[i] = (float) ((this.h.c().bottom + this.i) + this.s[i]);
            } else {
                this.p[i] = (float) ((this.h.c().bottom + this.i) + this.s[i]);
                this.o[i] = (this.p[i] + ((float) this.i)) + ((float) this.u[i]);
            }
            this.q[i] = (float) this.h.b().bottom;
        } else if (i == 0) {
            if (bVar.f()) {
                this.p[i] = (float) ((this.h.b().top + this.i) + this.s[i]);
                this.o[i] = (float) ((this.h.c().top - this.i) - this.t[i]);
            } else {
                this.p[i] = (float) ((this.h.c().top - this.i) - this.t[i]);
                this.o[i] = (this.p[i] - ((float) this.i)) - ((float) this.u[i]);
            }
            this.q[i] = (float) this.h.b().top;
        } else {
            throw new IllegalArgumentException("Invalid axis position: " + i);
        }
    }

    public void a(Canvas canvas) {
        lecho.lib.hellocharts.model.b b = this.g.getChartData().b();
        if (b != null) {
            i(b, 1);
            a(canvas, b, 1);
        }
        b = this.g.getChartData().d();
        if (b != null) {
            i(b, 2);
            a(canvas, b, 2);
        }
        b = this.g.getChartData().a();
        if (b != null) {
            i(b, 3);
            a(canvas, b, 3);
        }
        b = this.g.getChartData().c();
        if (b != null) {
            i(b, 0);
            a(canvas, b, 0);
        }
    }

    private void i(lecho.lib.hellocharts.model.b bVar, int i) {
        if (bVar.c()) {
            k(bVar, i);
        } else {
            j(bVar, i);
        }
    }

    public void b(Canvas canvas) {
        lecho.lib.hellocharts.model.b b = this.g.getChartData().b();
        if (b != null) {
            b(canvas, b, 1);
        }
        b = this.g.getChartData().d();
        if (b != null) {
            b(canvas, b, 2);
        }
        b = this.g.getChartData().a();
        if (b != null) {
            b(canvas, b, 3);
        }
        b = this.g.getChartData().c();
        if (b != null) {
            b(canvas, b, 0);
        }
    }

    private void j(lecho.lib.hellocharts.model.b bVar, int i) {
        float f;
        Viewport e = this.h.e();
        Viewport f2 = this.h.f();
        Rect b = this.h.b();
        boolean c = c(i);
        float f3 = 1.0f;
        float f4;
        float f5;
        if (c) {
            if (e.d() > 0.0f && f2.d() > 0.0f) {
                f3 = ((float) b.height()) * (e.d() / f2.d());
            }
            f4 = f2.d;
            f5 = f2.b;
            f = f4;
        } else {
            if (e.c() > 0.0f && f2.c() > 0.0f) {
                f3 = ((float) b.width()) * (e.c() / f2.c());
            }
            f4 = f2.a;
            f5 = f2.c;
            f = f4;
        }
        if (f3 == 0.0f) {
            f3 = 1.0f;
        }
        int max = (int) Math.max(1.0d, Math.ceil((((double) (bVar.a().size() * this.v[i])) * 1.5d) / ((double) f3)));
        if (bVar.d() && this.E[i].length < bVar.a().size() * 4) {
            this.E[i] = new float[(bVar.a().size() * 4)];
        }
        if (this.B[i].length < bVar.a().size()) {
            this.B[i] = new float[bVar.a().size()];
        }
        if (this.D[i].length < bVar.a().size()) {
            this.D[i] = new c[bVar.a().size()];
        }
        int i2 = 0;
        int i3 = 0;
        for (c cVar : bVar.a()) {
            int i4;
            int i5;
            f3 = cVar.a();
            if (f3 < f || f3 > r11) {
                i4 = i2;
                i5 = i3;
            } else {
                if (i3 % max == 0) {
                    float b2;
                    if (c) {
                        b2 = this.h.b(f3);
                    } else {
                        b2 = this.h.a(f3);
                    }
                    if (a(b, b2, bVar.f(), i, c)) {
                        this.B[i][i2] = b2;
                        this.D[i][i2] = cVar;
                        i2++;
                    }
                }
                i4 = i2;
                i5 = i3 + 1;
            }
            i2 = i4;
            i3 = i5;
        }
        this.A[i] = i2;
    }

    private void k(lecho.lib.hellocharts.model.b bVar, int i) {
        float f;
        float f2;
        int height;
        Viewport f3 = this.h.f();
        Rect b = this.h.b();
        boolean c = c(i);
        if (c) {
            f = f3.d;
            f2 = f3.b;
            height = b.height();
        } else {
            f = f3.a;
            f2 = f3.c;
            height = b.width();
        }
        lecho.lib.hellocharts.h.c.a(f, f2, (Math.abs(height) / this.v[i]) / 2, this.F[i]);
        if (bVar.d() && this.E[i].length < this.F[i].b * 4) {
            this.E[i] = new float[(this.F[i].b * 4)];
        }
        if (this.B[i].length < this.F[i].b) {
            this.B[i] = new float[this.F[i].b];
        }
        if (this.C[i].length < this.F[i].b) {
            this.C[i] = new float[this.F[i].b];
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.F[i].b) {
            int i4;
            if (c) {
                f2 = this.h.b(this.F[i].a[i2]);
            } else {
                f2 = this.h.a(this.F[i].a[i2]);
            }
            if (a(b, f2, bVar.f(), i, c)) {
                this.B[i][i3] = f2;
                this.C[i][i3] = this.F[i].a[i2];
                i4 = i3 + 1;
            } else {
                i4 = i3;
            }
            i2++;
            i3 = i4;
        }
        this.A[i] = i3;
    }

    private boolean a(Rect rect, float f, boolean z, int i, boolean z2) {
        if (!z) {
            return true;
        }
        if (z2) {
            float f2 = (float) (this.s[0] + this.i);
            if (f > ((float) rect.bottom) - ((float) (this.s[3] + this.i)) || f < ((float) rect.top) + f2) {
                return false;
            }
            return true;
        }
        float f3 = (float) (this.r[i] / 2);
        if (f < ((float) rect.left) + f3 || f > ((float) rect.right) - f3) {
            return false;
        }
        return true;
    }

    private void a(Canvas canvas, lecho.lib.hellocharts.model.b bVar, int i) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        Rect c = this.h.c();
        boolean c2 = c(i);
        if (1 == i || 2 == i) {
            f = this.q[i];
            f2 = (float) c.top;
            f3 = 0.0f;
            f4 = (float) c.right;
            f5 = 0.0f;
            f6 = (float) c.left;
            f7 = (float) c.bottom;
            f8 = f;
        } else if (i == 0 || 3 == i) {
            float f9 = (float) c.left;
            f = (float) c.right;
            f2 = this.q[i];
            f3 = (float) c.bottom;
            f4 = 0.0f;
            f5 = (float) c.top;
            f6 = 0.0f;
            f7 = f2;
            f8 = f9;
        } else {
            f3 = 0.0f;
            f4 = 0.0f;
            f5 = 0.0f;
            f6 = 0.0f;
            f2 = 0.0f;
            f = 0.0f;
            f7 = 0.0f;
            f8 = 0.0f;
        }
        if (bVar.l()) {
            canvas.drawLine(f8, f7, f, f2, this.l[i]);
        }
        if (bVar.d()) {
            int i2 = 0;
            while (i2 < this.A[i]) {
                if (c2) {
                    f3 = this.B[i][i2];
                    f5 = f3;
                } else {
                    f4 = this.B[i][i2];
                    f6 = f4;
                }
                this.E[i][(i2 * 4) + 0] = f6;
                this.E[i][(i2 * 4) + 1] = f5;
                this.E[i][(i2 * 4) + 2] = f4;
                this.E[i][(i2 * 4) + 3] = f3;
                i2++;
            }
            canvas.drawLines(this.E[i], 0, i2 * 4, this.n[i]);
        }
    }

    private void b(Canvas canvas, lecho.lib.hellocharts.model.b bVar, int i) {
        float f;
        float f2 = 0.0f;
        boolean c = c(i);
        if (1 == i || 2 == i) {
            f = this.p[i];
        } else if (i == 0 || 3 == i) {
            f = 0.0f;
            f2 = this.p[i];
        } else {
            f = 0.0f;
        }
        int i2 = 0;
        while (i2 < this.A[i]) {
            int a;
            float f3;
            float f4;
            if (bVar.c()) {
                a = bVar.k().a(this.z, this.C[i][i2], this.F[i].c);
            } else {
                a = bVar.k().a(this.z, this.D[i][i2]);
            }
            if (c) {
                f3 = this.B[i][i2];
                f4 = f;
            } else {
                f4 = this.B[i][i2];
                f3 = f2;
            }
            if (bVar.m()) {
                canvas.save();
                canvas.translate((float) this.w[i], (float) this.x[i]);
                canvas.rotate(-45.0f, f4, f3);
                canvas.drawText(this.z, this.z.length - a, a, f4, f3, this.l[i]);
                canvas.restore();
            } else {
                canvas.drawText(this.z, this.z.length - a, a, f4, f3, this.l[i]);
            }
            i2++;
            f = f4;
            f2 = f3;
        }
        Rect c2 = this.h.c();
        if (!TextUtils.isEmpty(bVar.b())) {
            if (c) {
                canvas.save();
                canvas.rotate(-90.0f, (float) c2.centerY(), (float) c2.centerY());
                canvas.drawText(bVar.b(), (float) c2.centerY(), this.o[i], this.m[i]);
                canvas.restore();
                return;
            }
            canvas.drawText(bVar.b(), (float) c2.centerX(), this.o[i], this.m[i]);
        }
    }

    private boolean c(int i) {
        if (1 == i || 2 == i) {
            return true;
        }
        if (i == 0 || 3 == i) {
            return false;
        }
        throw new IllegalArgumentException("Invalid axis position " + i);
    }
}
