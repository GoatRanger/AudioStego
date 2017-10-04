package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import dji.pilot.visual.a.d;
import lecho.lib.hellocharts.f.b;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.g;
import lecho.lib.hellocharts.model.h;
import lecho.lib.hellocharts.model.n;
import lecho.lib.hellocharts.model.p;
import lecho.lib.hellocharts.view.a;

public class e extends a {
    public static final int q = 1;
    public static final int r = 4;
    private static final int s = 0;
    private static final int t = 1;
    private static final int u = 2;
    private PointF A = new PointF();
    private float B;
    private float C;
    private Viewport D = new Viewport();
    private b v;
    private int w;
    private int x;
    private Paint y = new Paint();
    private RectF z = new RectF();

    public e(Context context, a aVar, b bVar) {
        super(context, aVar);
        this.v = bVar;
        this.x = lecho.lib.hellocharts.h.b.a(this.i, 1);
        this.w = lecho.lib.hellocharts.h.b.a(this.i, 4);
        this.y.setAntiAlias(true);
        this.y.setStyle(Style.FILL);
        this.y.setStrokeCap(Cap.SQUARE);
    }

    public void i() {
    }

    public void b() {
        super.b();
        h columnChartData = this.v.getColumnChartData();
        this.B = columnChartData.o();
        this.C = columnChartData.p();
        j();
    }

    public void j() {
        if (this.h) {
            k();
            this.c.b(this.D);
            this.c.a(this.c.e());
        }
    }

    public void a(Canvas canvas) {
        if (this.v.getColumnChartData().n()) {
            e(canvas);
            if (c()) {
                f(canvas);
                return;
            }
            return;
        }
        c(canvas);
        if (c()) {
            d(canvas);
        }
    }

    public void b(Canvas canvas) {
    }

    public boolean a(float f, float f2) {
        this.k.a();
        if (this.v.getColumnChartData().n()) {
            c(f, f2);
        } else {
            b(f, f2);
        }
        return c();
    }

    private void k() {
        h columnChartData = this.v.getColumnChartData();
        this.D.a(-0.5f, this.C, ((float) columnChartData.m().size()) - d.c, this.C);
        if (columnChartData.n()) {
            b(columnChartData);
        } else {
            a(columnChartData);
        }
    }

    private void a(h hVar) {
        for (g b : hVar.m()) {
            for (p pVar : b.b()) {
                if (pVar.b() >= this.C && pVar.b() > this.D.b) {
                    this.D.b = pVar.b();
                }
                if (pVar.b() < this.C && pVar.b() < this.D.d) {
                    this.D.d = pVar.b();
                }
            }
        }
    }

    private void b(h hVar) {
        for (g gVar : hVar.m()) {
            float f = this.C;
            float f2 = this.C;
            for (p pVar : gVar.b()) {
                float f3;
                if (pVar.b() >= this.C) {
                    f += pVar.b();
                    f3 = f2;
                    f2 = f;
                } else {
                    f3 = pVar.b() + f2;
                    f2 = f;
                }
                f = f2;
                f2 = f3;
            }
            if (f > this.D.b) {
                this.D.b = f;
            }
            if (f2 < this.D.d) {
                this.D.d = f2;
            }
        }
    }

    private void c(Canvas canvas) {
        h columnChartData = this.v.getColumnChartData();
        float l = l();
        int i = 0;
        for (g a : columnChartData.m()) {
            a(canvas, a, l, i, 0);
            i++;
        }
    }

    private void d(Canvas canvas) {
        h columnChartData = this.v.getColumnChartData();
        g gVar = (g) columnChartData.m().get(this.k.c());
        a(canvas, gVar, l(), this.k.c(), 2);
    }

    private void b(float f, float f2) {
        this.A.x = f;
        this.A.y = f2;
        h columnChartData = this.v.getColumnChartData();
        float l = l();
        int i = 0;
        for (g a : columnChartData.m()) {
            a(null, a, l, i, 1);
            i++;
        }
    }

    private void a(Canvas canvas, g gVar, float f, int i, int i2) {
        float f2;
        float size = (f - ((float) (this.x * (gVar.b().size() - 1)))) / ((float) gVar.b().size());
        if (size < 1.0f) {
            f2 = 1.0f;
        } else {
            f2 = size;
        }
        float a = this.c.a((float) i);
        float f3 = f / 2.0f;
        float b = this.c.b(this.C);
        float f4 = a - f3;
        int i3 = 0;
        for (p pVar : gVar.b()) {
            this.y.setColor(pVar.c());
            if (f4 <= a + f3) {
                a(pVar, f4, f4 + f2, b, this.c.b(pVar.b()));
                switch (i2) {
                    case 0:
                        a(canvas, gVar, pVar, false);
                        break;
                    case 1:
                        a(i, i3);
                        break;
                    case 2:
                        a(canvas, gVar, pVar, i3, false);
                        break;
                    default:
                        throw new IllegalStateException("Cannot process column in mode: " + i2);
                }
                f4 += ((float) this.x) + f2;
                i3++;
            } else {
                return;
            }
        }
    }

    private void e(Canvas canvas) {
        h columnChartData = this.v.getColumnChartData();
        float l = l();
        int i = 0;
        for (g b : columnChartData.m()) {
            b(canvas, b, l, i, 0);
            i++;
        }
    }

    private void f(Canvas canvas) {
        h columnChartData = this.v.getColumnChartData();
        g gVar = (g) columnChartData.m().get(this.k.c());
        b(canvas, gVar, l(), this.k.c(), 2);
    }

    private void c(float f, float f2) {
        this.A.x = f;
        this.A.y = f2;
        h columnChartData = this.v.getColumnChartData();
        float l = l();
        int i = 0;
        for (g b : columnChartData.m()) {
            b(null, b, l, i, 1);
            i++;
        }
    }

    private void b(Canvas canvas, g gVar, float f, int i, int i2) {
        float a = this.c.a((float) i);
        float f2 = f / 2.0f;
        float f3 = this.C;
        float f4 = this.C;
        float f5 = this.C;
        int i3 = 0;
        f5 = f3;
        for (p pVar : gVar.b()) {
            float b;
            float f6;
            this.y.setColor(pVar.c());
            if (pVar.b() >= this.C) {
                b = f5 + pVar.b();
                f6 = f4;
            } else {
                f6 = f4 + pVar.b();
                b = f5;
                f5 = f4;
            }
            a(pVar, a - f2, a + f2, this.c.b(f5), this.c.b(f5 + pVar.b()));
            switch (i2) {
                case 0:
                    a(canvas, gVar, pVar, true);
                    break;
                case 1:
                    a(i, i3);
                    break;
                case 2:
                    a(canvas, gVar, pVar, i3, true);
                    break;
                default:
                    throw new IllegalStateException("Cannot process column in mode: " + i2);
            }
            i3++;
            f4 = f6;
            f5 = b;
        }
    }

    private void a(Canvas canvas, g gVar, p pVar, boolean z) {
        canvas.drawRect(this.z, this.y);
        if (gVar.c()) {
            a(canvas, gVar, pVar, z, (float) this.m);
        }
    }

    private void a(Canvas canvas, g gVar, p pVar, int i, boolean z) {
        if (this.k.d() == i) {
            this.y.setColor(pVar.d());
            Canvas canvas2 = canvas;
            canvas2.drawRect(this.z.left - ((float) this.w), this.z.top, ((float) this.w) + this.z.right, this.z.bottom, this.y);
            if (gVar.c() || gVar.d()) {
                a(canvas, gVar, pVar, z, (float) this.m);
            }
        }
    }

    private void a(int i, int i2) {
        if (this.z.contains(this.A.x, this.A.y)) {
            this.k.a(i, i2, n.a.COLUMN);
        }
    }

    private float l() {
        float width = (this.B * ((float) this.c.b().width())) / this.c.f().c();
        if (width < 2.0f) {
            return 2.0f;
        }
        return width;
    }

    private void a(p pVar, float f, float f2, float f3, float f4) {
        this.z.left = f;
        this.z.right = f2;
        if (pVar.b() >= this.C) {
            this.z.top = f4;
            this.z.bottom = f3 - ((float) this.x);
            return;
        }
        this.z.bottom = f4;
        this.z.top = ((float) this.x) + f3;
    }

    private void a(Canvas canvas, g gVar, p pVar, boolean z, float f) {
        int a = gVar.e().a(this.l, pVar);
        if (a != 0) {
            float f2;
            float measureText = this.d.measureText(this.l, this.l.length - a, a);
            int abs = Math.abs(this.g.ascent);
            float centerX = (this.z.centerX() - (measureText / 2.0f)) - ((float) this.n);
            float centerX2 = ((measureText / 2.0f) + this.z.centerX()) + ((float) this.n);
            if (!z || ((float) abs) >= this.z.height() - ((float) (this.n * 2))) {
                if (!z) {
                    if (pVar.b() >= this.C) {
                        f2 = ((this.z.top - f) - ((float) abs)) - ((float) (this.n * 2));
                        if (f2 < ((float) this.c.b().top)) {
                            f2 = this.z.top + f;
                            measureText = ((this.z.top + f) + ((float) abs)) + ((float) (this.n * 2));
                        } else {
                            measureText = this.z.top - f;
                        }
                    } else {
                        measureText = ((this.z.bottom + f) + ((float) abs)) + ((float) (this.n * 2));
                        if (measureText > ((float) this.c.b().bottom)) {
                            f2 = ((this.z.bottom - f) - ((float) abs)) - ((float) (this.n * 2));
                            measureText = this.z.bottom - f;
                        } else {
                            f2 = this.z.bottom + f;
                        }
                    }
                } else {
                    return;
                }
            } else if (pVar.b() >= this.C) {
                f2 = this.z.top;
                measureText = (this.z.top + ((float) abs)) + ((float) (this.n * 2));
            } else {
                f2 = (this.z.bottom - ((float) abs)) - ((float) (this.n * 2));
                measureText = this.z.bottom;
            }
            this.f.set(centerX, f2, centerX2, measureText);
            a(canvas, this.l, this.l.length - a, a, pVar.d());
        }
    }
}
