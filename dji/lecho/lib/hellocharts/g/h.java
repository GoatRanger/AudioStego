package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v4.widget.AutoScrollHelper;
import lecho.lib.hellocharts.f.d;
import lecho.lib.hellocharts.h.b;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.j;
import lecho.lib.hellocharts.model.k;
import lecho.lib.hellocharts.model.m;
import lecho.lib.hellocharts.model.n;
import lecho.lib.hellocharts.model.q;
import lecho.lib.hellocharts.view.a;

public class h extends a {
    private static final float q = 0.16f;
    private static final int r = 3;
    private static final int s = 4;
    private static final int t = 0;
    private static final int u = 1;
    private Paint A = new Paint();
    private Paint B = new Paint();
    private Bitmap C;
    private Canvas D = new Canvas();
    private Viewport E = new Viewport();
    private d v;
    private int w;
    private float x;
    private int y;
    private Path z = new Path();

    public h(Context context, a aVar, d dVar) {
        super(context, aVar);
        this.v = dVar;
        this.y = b.a(this.i, 4);
        this.A.setAntiAlias(true);
        this.A.setStyle(Style.STROKE);
        this.A.setStrokeCap(Cap.ROUND);
        this.A.setStrokeWidth((float) b.a(this.i, 3));
        this.B.setAntiAlias(true);
        this.B.setStyle(Style.FILL);
        this.w = b.a(this.i, 2);
    }

    public void a(Shader shader) {
        this.A.setShader(shader);
    }

    public void i() {
        int l = l();
        this.c.b(l, l, l, l);
        if (this.c.i() > 0 && this.c.j() > 0) {
            this.C = Bitmap.createBitmap(this.c.i(), this.c.j(), Config.ARGB_8888);
            this.D.setBitmap(this.C);
        }
    }

    public void b() {
        super.b();
        int l = l();
        this.c.b(l, l, l, l);
        this.x = this.v.getLineChartData().n();
        j();
    }

    public void j() {
        if (this.h) {
            k();
            this.c.b(this.E);
            this.c.a(this.c.e());
        }
    }

    public void a(Canvas canvas) {
        Canvas canvas2;
        k lineChartData = this.v.getLineChartData();
        if (this.C != null) {
            Canvas canvas3 = this.D;
            canvas3.drawColor(0, Mode.CLEAR);
            canvas2 = canvas3;
        } else {
            canvas2 = canvas;
        }
        for (j jVar : lineChartData.m()) {
            if (jVar.i()) {
                if (jVar.m()) {
                    c(canvas2, jVar);
                } else if (jVar.n()) {
                    b(canvas2, jVar);
                } else {
                    a(canvas2, jVar);
                }
            }
        }
        if (this.C != null) {
            canvas.drawBitmap(this.C, 0.0f, 0.0f, null);
        }
    }

    public void b(Canvas canvas) {
        int i = 0;
        for (j jVar : this.v.getLineChartData().m()) {
            if (a(jVar)) {
                a(canvas, jVar, i, 0);
            }
            i++;
        }
        if (c()) {
            c(canvas);
        }
    }

    private boolean a(j jVar) {
        return jVar.h() || jVar.b().size() == 1;
    }

    public boolean a(float f, float f2) {
        this.k.a();
        int i = 0;
        for (j jVar : this.v.getLineChartData().m()) {
            if (a(jVar)) {
                int a = b.a(this.i, jVar.l());
                int i2 = 0;
                for (m mVar : jVar.b()) {
                    if (a(this.c.a(mVar.b()), this.c.b(mVar.c()), f, f2, (float) (this.y + a))) {
                        this.k.a(i, i2, n.a.LINE);
                    }
                    i2++;
                }
            }
            i++;
        }
        return c();
    }

    private void k() {
        this.E.a(AutoScrollHelper.NO_MAX, Float.MIN_VALUE, Float.MIN_VALUE, AutoScrollHelper.NO_MAX);
        for (j b : this.v.getLineChartData().m()) {
            for (m mVar : b.b()) {
                if (mVar.b() < this.E.a) {
                    this.E.a = mVar.b();
                }
                if (mVar.b() > this.E.c) {
                    this.E.c = mVar.b();
                }
                if (mVar.c() < this.E.d) {
                    this.E.d = mVar.c();
                }
                if (mVar.c() > this.E.b) {
                    this.E.b = mVar.c();
                }
            }
        }
    }

    private int l() {
        int i = 0;
        for (j jVar : this.v.getLineChartData().m()) {
            int l;
            if (a(jVar)) {
                l = jVar.l() + 4;
                if (l > i) {
                    i = l;
                }
            }
            l = i;
            i = l;
        }
        return b.a(this.i, i);
    }

    private void a(Canvas canvas, j jVar) {
        b(jVar);
        int i = 0;
        for (m mVar : jVar.b()) {
            float a = this.c.a(mVar.b());
            float b = this.c.b(mVar.c());
            if (i == 0) {
                this.z.moveTo(a, b);
            } else {
                this.z.lineTo(a, b);
            }
            i++;
        }
        canvas.drawPath(this.z, this.A);
        if (jVar.o()) {
            d(canvas, jVar);
        }
        this.z.reset();
    }

    private void b(Canvas canvas, j jVar) {
        b(jVar);
        int i = 0;
        float f = 0.0f;
        for (m mVar : jVar.b()) {
            float a = this.c.a(mVar.b());
            float b = this.c.b(mVar.c());
            if (i == 0) {
                this.z.moveTo(a, b);
            } else {
                this.z.lineTo(a, f);
                this.z.lineTo(a, b);
            }
            i++;
            f = b;
        }
        canvas.drawPath(this.z, this.A);
        if (jVar.o()) {
            d(canvas, jVar);
        }
        this.z.reset();
    }

    private void c(Canvas canvas, j jVar) {
        b(jVar);
        int size = jVar.b().size();
        float f = Float.NaN;
        float f2 = Float.NaN;
        float f3 = Float.NaN;
        float f4 = Float.NaN;
        float f5 = Float.NaN;
        float f6 = Float.NaN;
        int i = 0;
        while (i < size) {
            float f7;
            float f8;
            float b;
            float f9;
            if (Float.isNaN(f5)) {
                m mVar = (m) jVar.b().get(i);
                f5 = this.c.a(mVar.b());
                f6 = this.c.b(mVar.c());
            }
            if (!Float.isNaN(f3)) {
                f7 = f4;
                f8 = f3;
            } else if (i > 0) {
                mVar = (m) jVar.b().get(i - 1);
                f4 = this.c.a(mVar.b());
                f7 = this.c.b(mVar.c());
                f8 = f4;
            } else {
                f7 = f6;
                f8 = f5;
            }
            if (!Float.isNaN(f)) {
                f3 = f2;
                f2 = f;
            } else if (i > 1) {
                mVar = (m) jVar.b().get(i - 2);
                f4 = this.c.a(mVar.b());
                f3 = this.c.b(mVar.c());
                f2 = f4;
            } else {
                f3 = f7;
                f2 = f8;
            }
            if (i < size - 1) {
                mVar = (m) jVar.b().get(i + 1);
                f4 = this.c.a(mVar.b());
                b = this.c.b(mVar.c());
                f9 = f4;
            } else {
                b = f6;
                f9 = f5;
            }
            if (i == 0) {
                this.z.moveTo(f5, f6);
            } else {
                this.z.cubicTo(f8 + ((f5 - f2) * q), f7 + (q * (f6 - f3)), f5 - (q * (f9 - f8)), f6 - (q * (b - f7)), f5, f6);
            }
            i++;
            f4 = f6;
            f3 = f5;
            f2 = f7;
            f = f8;
            f6 = b;
            f5 = f9;
        }
        canvas.drawPath(this.z, this.A);
        if (jVar.o()) {
            d(canvas, jVar);
        }
        this.z.reset();
    }

    private void b(j jVar) {
        this.A.setStrokeWidth((float) b.a(this.i, jVar.g()));
        this.A.setColor(jVar.c());
        this.A.setPathEffect(jVar.q());
    }

    private void a(Canvas canvas, j jVar, int i, int i2) {
        this.B.setColor(jVar.d());
        int i3 = 0;
        for (m mVar : jVar.b()) {
            int a = b.a(this.i, jVar.l());
            float a2 = this.c.a(mVar.b());
            float b = this.c.b(mVar.c());
            if (this.c.a(a2, b, (float) this.w)) {
                if (i2 == 0) {
                    a(canvas, jVar, mVar, a2, b, (float) a);
                    if (jVar.j()) {
                        b(canvas, jVar, mVar, a2, b, (float) (this.m + a));
                    }
                } else if (1 == i2) {
                    a(canvas, jVar, mVar, a2, b, i, i3);
                } else {
                    throw new IllegalStateException("Cannot process points in mode: " + i2);
                }
            }
            i3++;
        }
    }

    private void a(Canvas canvas, j jVar, m mVar, float f, float f2, float f3) {
        if (q.SQUARE.equals(jVar.p())) {
            canvas.drawRect(f - f3, f2 - f3, f + f3, f2 + f3, this.B);
        } else if (q.CIRCLE.equals(jVar.p())) {
            canvas.drawCircle(f, f2, f3, this.B);
        } else if (q.DIAMOND.equals(jVar.p())) {
            canvas.save();
            canvas.rotate(45.0f, f, f2);
            canvas.drawRect(f - f3, f2 - f3, f + f3, f2 + f3, this.B);
            canvas.restore();
        } else {
            throw new IllegalArgumentException("Invalid point shape: " + jVar.p());
        }
    }

    private void c(Canvas canvas) {
        int c = this.k.c();
        a(canvas, (j) this.v.getLineChartData().m().get(c), c, 1);
    }

    private void a(Canvas canvas, j jVar, m mVar, float f, float f2, int i, int i2) {
        if (this.k.c() == i && this.k.d() == i2) {
            int a = b.a(this.i, jVar.l());
            this.B.setColor(jVar.e());
            a(canvas, jVar, mVar, f, f2, (float) (this.y + a));
            if (jVar.j() || jVar.k()) {
                b(canvas, jVar, mVar, f, f2, (float) (this.m + a));
            }
        }
    }

    private void b(Canvas canvas, j jVar, m mVar, float f, float f2, float f3) {
        Rect b = this.c.b();
        int a = jVar.r().a(this.l, mVar);
        if (a != 0) {
            float f4;
            float f5;
            float measureText = this.d.measureText(this.l, this.l.length - a, a);
            int abs = Math.abs(this.g.ascent);
            float f6 = (f - (measureText / 2.0f)) - ((float) this.n);
            float f7 = ((measureText / 2.0f) + f) + ((float) this.n);
            if (mVar.c() >= this.x) {
                f4 = ((f2 - f3) - ((float) abs)) - ((float) (this.n * 2));
                f5 = f2 - f3;
            } else {
                f4 = f2 + f3;
                f5 = ((f2 + f3) + ((float) abs)) + ((float) (this.n * 2));
            }
            if (f4 < ((float) b.top)) {
                f4 = f2 + f3;
                f5 = ((f2 + f3) + ((float) abs)) + ((float) (this.n * 2));
            }
            if (f5 > ((float) b.bottom)) {
                f4 = ((f2 - f3) - ((float) abs)) - ((float) (this.n * 2));
                f5 = f2 - f3;
            }
            if (f6 < ((float) b.left)) {
                f7 = (f + measureText) + ((float) (this.n * 2));
                f6 = f;
            }
            if (f7 > ((float) b.right)) {
                f6 = (f - measureText) - ((float) (this.n * 2));
            } else {
                f = f7;
            }
            this.f.set(f6, f4, f, f5);
            a(canvas, this.l, this.l.length - a, a, jVar.e());
        }
    }

    private void d(Canvas canvas, j jVar) {
        int size = jVar.b().size();
        if (size >= 2) {
            Rect b = this.c.b();
            float min = Math.min((float) b.bottom, Math.max(this.c.b(this.x), (float) b.top));
            float max = Math.max(this.c.a(((m) jVar.b().get(0)).b()), (float) b.left);
            this.z.lineTo(Math.min(this.c.a(((m) jVar.b().get(size - 1)).b()), (float) b.right), min);
            this.z.lineTo(max, min);
            this.z.close();
            this.A.setStyle(Style.FILL);
            this.A.setAlpha(jVar.f());
            canvas.drawPath(this.z, this.A);
            this.A.setStyle(Style.STROKE);
        }
    }

    private boolean a(float f, float f2, float f3, float f4, float f5) {
        return Math.pow((double) (f4 - f2), 2.0d) + Math.pow((double) (f3 - f), 2.0d) <= Math.pow((double) f5, 2.0d) * 2.0d;
    }
}
