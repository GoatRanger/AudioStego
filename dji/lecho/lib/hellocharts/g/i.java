package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import dji.pilot.visual.a.d;
import lecho.lib.hellocharts.c.e;
import lecho.lib.hellocharts.h.b;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.l;
import lecho.lib.hellocharts.model.n;
import lecho.lib.hellocharts.model.o;
import lecho.lib.hellocharts.view.a;

public class i extends a {
    private static final float q = 100.0f;
    private static final int r = 45;
    private static final float s = 0.7f;
    private static final float t = 1.0f;
    private static final int u = 8;
    private static final int v = 0;
    private static final int w = 1;
    private float A;
    private RectF B = new RectF();
    private RectF C = new RectF();
    private PointF D = new PointF();
    private int E;
    private float F = 1.0f;
    private boolean G;
    private float H;
    private Paint I = new Paint();
    private Paint J = new Paint();
    private FontMetricsInt K = new FontMetricsInt();
    private Paint L = new Paint();
    private FontMetricsInt M = new FontMetricsInt();
    private Paint N = new Paint();
    private boolean O;
    private boolean P;
    private boolean Q;
    private e R;
    private Viewport S = new Viewport();
    private Bitmap T;
    private Canvas U = new Canvas();
    private int x = 45;
    private lecho.lib.hellocharts.f.e y;
    private Paint z = new Paint();

    public i(Context context, a aVar, lecho.lib.hellocharts.f.e eVar) {
        super(context, aVar);
        this.y = eVar;
        this.E = b.a(this.i, 8);
        this.z.setAntiAlias(true);
        this.z.setStyle(Style.FILL);
        this.I.setAntiAlias(true);
        this.I.setStyle(Style.FILL);
        this.I.setXfermode(new PorterDuffXfermode(Mode.SRC));
        this.J.setAntiAlias(true);
        this.J.setTextAlign(Align.CENTER);
        this.L.setAntiAlias(true);
        this.L.setTextAlign(Align.CENTER);
        this.N.setAntiAlias(true);
        this.N.setStyle(Style.STROKE);
        this.N.setStrokeCap(Cap.ROUND);
        this.N.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.N.setColor(0);
    }

    public void i() {
        n();
        if (this.c.i() > 0 && this.c.j() > 0) {
            this.T = Bitmap.createBitmap(this.c.i(), this.c.j(), Config.ARGB_8888);
            this.U.setBitmap(this.T);
        }
    }

    public void b() {
        super.b();
        l pieChartData = this.y.getPieChartData();
        this.O = pieChartData.p();
        this.P = pieChartData.n();
        this.Q = pieChartData.o();
        this.R = pieChartData.C();
        this.G = pieChartData.q();
        this.H = pieChartData.s();
        this.I.setColor(pieChartData.r());
        if (pieChartData.v() != null) {
            this.J.setTypeface(pieChartData.v());
        }
        this.J.setTextSize((float) b.c(this.j, pieChartData.u()));
        this.J.setColor(pieChartData.t());
        this.J.getFontMetricsInt(this.K);
        if (pieChartData.A() != null) {
            this.L.setTypeface(pieChartData.A());
        }
        this.L.setTextSize((float) b.c(this.j, pieChartData.z()));
        this.L.setColor(pieChartData.y());
        this.L.getFontMetricsInt(this.M);
        j();
    }

    public void j() {
        if (this.h) {
            o();
            this.c.b(this.S);
            this.c.a(this.c.e());
        }
    }

    public void a(Canvas canvas) {
        Canvas canvas2;
        if (this.T != null) {
            canvas2 = this.U;
            canvas2.drawColor(0, Mode.CLEAR);
        } else {
            canvas2 = canvas;
        }
        e(canvas2);
        f(canvas2);
        if (this.G) {
            d(canvas2);
        }
        c(canvas2);
        if (this.T != null) {
            canvas.drawBitmap(this.T, 0.0f, 0.0f, null);
        }
    }

    public void b(Canvas canvas) {
    }

    public boolean a(float f, float f2) {
        this.k.a();
        l pieChartData = this.y.getPieChartData();
        float centerX = this.B.centerX();
        float centerY = this.B.centerY();
        float width = this.B.width() / 2.0f;
        this.D.set(f - centerX, f2 - centerY);
        if (this.D.length() > ((float) this.E) + width) {
            return false;
        }
        if (pieChartData.q() && this.D.length() < width * pieChartData.s()) {
            return false;
        }
        centerY = ((a(f, f2, centerX, centerY) - ((float) this.x)) + 360.0f) % 360.0f;
        width = 360.0f / this.A;
        float f3 = 0.0f;
        int i = 0;
        for (o b : pieChartData.m()) {
            float abs = Math.abs(b.b()) * width;
            if (centerY >= f3) {
                this.k.a(i, i, n.a.NONE);
            }
            f3 += abs;
            i++;
        }
        return c();
    }

    private void d(Canvas canvas) {
        l pieChartData = this.y.getPieChartData();
        float width = (this.B.width() / 2.0f) * pieChartData.s();
        float centerX = this.B.centerX();
        float centerY = this.B.centerY();
        canvas.drawCircle(centerX, centerY, width, this.I);
        if (!TextUtils.isEmpty(pieChartData.w())) {
            int abs = Math.abs(this.K.ascent);
            if (TextUtils.isEmpty(pieChartData.x())) {
                canvas.drawText(pieChartData.w(), centerX, ((float) (abs / 4)) + centerY, this.J);
                return;
            }
            int abs2 = Math.abs(this.M.ascent);
            canvas.drawText(pieChartData.w(), centerX, centerY - (((float) abs) * 0.2f), this.J);
            canvas.drawText(pieChartData.x(), centerX, ((float) abs2) + centerY, this.L);
        }
    }

    private void e(Canvas canvas) {
        float f = 360.0f / this.A;
        float f2 = (float) this.x;
        int i = 0;
        for (o oVar : this.y.getPieChartData().m()) {
            float abs = Math.abs(oVar.b()) * f;
            if (c() && this.k.c() == i) {
                a(canvas, oVar, f2, abs, 1);
            } else {
                a(canvas, oVar, f2, abs, 0);
            }
            f2 += abs;
            i++;
        }
    }

    private void f(Canvas canvas) {
        l pieChartData = this.y.getPieChartData();
        if (pieChartData.m().size() >= 2) {
            int a = b.a(this.i, pieChartData.B());
            if (a >= 1) {
                float f = 360.0f / this.A;
                float f2 = (float) this.x;
                float width = this.B.width() / 2.0f;
                this.N.setStrokeWidth((float) a);
                float f3 = f2;
                for (o b : pieChartData.m()) {
                    float abs = Math.abs(b.b()) * f;
                    this.D.set((float) Math.cos(Math.toRadians((double) f3)), (float) Math.sin(Math.toRadians((double) f3)));
                    a(this.D);
                    Canvas canvas2 = canvas;
                    canvas2.drawLine(this.B.centerX(), this.B.centerY(), (this.D.x * (((float) this.E) + width)) + this.B.centerX(), (this.D.y * (((float) this.E) + width)) + this.B.centerY(), this.N);
                    f3 += abs;
                }
            }
        }
    }

    public void c(Canvas canvas) {
        float f = 360.0f / this.A;
        float f2 = (float) this.x;
        float f3 = f2;
        int i = 0;
        for (o oVar : this.y.getPieChartData().m()) {
            float abs = Math.abs(oVar.b()) * f;
            if (c()) {
                if (this.P) {
                    a(canvas, oVar, f3, abs);
                } else if (this.Q && this.k.c() == i) {
                    a(canvas, oVar, f3, abs);
                }
            } else if (this.P) {
                a(canvas, oVar, f3, abs);
            }
            f3 += abs;
            i++;
        }
    }

    private void a(Canvas canvas, o oVar, float f, float f2, int i) {
        this.D.set((float) Math.cos(Math.toRadians((double) ((f2 / 2.0f) + f))), (float) Math.sin(Math.toRadians((double) ((f2 / 2.0f) + f))));
        a(this.D);
        this.C.set(this.B);
        if (1 == i) {
            this.C.inset((float) (-this.E), (float) (-this.E));
            this.z.setColor(oVar.d());
            canvas.drawArc(this.C, f, f2, true, this.z);
            return;
        }
        this.z.setColor(oVar.c());
        canvas.drawArc(this.C, f, f2, true, this.z);
    }

    private void a(Canvas canvas, o oVar, float f, float f2) {
        this.D.set((float) Math.cos(Math.toRadians((double) ((f2 / 2.0f) + f))), (float) Math.sin(Math.toRadians((double) ((f2 / 2.0f) + f))));
        a(this.D);
        int a = this.R.a(this.l, oVar);
        if (a != 0) {
            float f3;
            float measureText = this.d.measureText(this.l, this.l.length - a, a);
            int abs = Math.abs(this.g.ascent);
            float centerX = this.B.centerX();
            float centerY = this.B.centerY();
            float width = this.B.width() / 2.0f;
            if (this.O) {
                width *= 1.0f;
            } else if (this.G) {
                width -= (width - (this.H * width)) / 2.0f;
            } else {
                width *= s;
            }
            float f4 = (this.D.x * width) + centerX;
            width = (width * this.D.y) + centerY;
            if (this.O) {
                if (f4 > centerX) {
                    f3 = f4 + ((float) this.n);
                    centerX = ((float) (this.n * 3)) + (measureText + f4);
                } else {
                    f3 = (f4 - measureText) - ((float) (this.n * 3));
                    centerX = f4 - ((float) this.n);
                }
                if (width > centerY) {
                    measureText = ((float) this.n) + width;
                    width = (width + ((float) abs)) + ((float) (this.n * 3));
                } else {
                    measureText = (width - ((float) abs)) - ((float) (this.n * 3));
                    width -= (float) this.n;
                }
            } else {
                f3 = (f4 - (measureText / 2.0f)) - ((float) this.n);
                centerX = ((float) this.n) + ((measureText / 2.0f) + f4);
                measureText = (width - ((float) (abs / 2))) - ((float) this.n);
                width = (width + ((float) (abs / 2))) + ((float) this.n);
            }
            this.f.set(f3, measureText, centerX, width);
            a(canvas, this.l, this.l.length - a, a, oVar.d());
        }
    }

    private void a(PointF pointF) {
        float length = pointF.length();
        pointF.set(pointF.x / length, pointF.y / length);
    }

    private float a(float f, float f2, float f3, float f4) {
        return ((((float) Math.toDegrees(Math.atan2(-((double) (f - f3)), (double) (f2 - f4)))) + 360.0f) % 360.0f) + 90.0f;
    }

    private void n() {
        Rect b = this.c.b();
        float min = Math.min(((float) b.width()) / 2.0f, ((float) b.height()) / 2.0f);
        float centerX = (float) b.centerX();
        float centerY = (float) b.centerY();
        this.B.set((centerX - min) + ((float) this.E), (centerY - min) + ((float) this.E), (centerX + min) - ((float) this.E), (centerY + min) - ((float) this.E));
        centerY = (d.c * this.B.width()) * (1.0f - this.F);
        this.B.inset(centerY, centerY);
    }

    private void o() {
        this.S.a(0.0f, 100.0f, 100.0f, 0.0f);
        this.A = 0.0f;
        for (o oVar : this.y.getPieChartData().m()) {
            this.A = Math.abs(oVar.b()) + this.A;
        }
    }

    public RectF k() {
        return this.B;
    }

    public void a(RectF rectF) {
        this.B = rectF;
    }

    public int l() {
        return this.x;
    }

    public void a(int i) {
        this.x = ((i % 360) + 360) % 360;
    }

    public o a(int i, n nVar) {
        float f = (((float) (i - this.x)) + 360.0f) % 360.0f;
        float f2 = 360.0f / this.A;
        float f3 = 0.0f;
        int i2 = 0;
        for (o oVar : this.y.getPieChartData().m()) {
            float abs = Math.abs(oVar.b()) * f2;
            if (f < f3) {
                f3 += abs;
                i2++;
            } else if (nVar == null) {
                return oVar;
            } else {
                nVar.a(i2, i2, n.a.NONE);
                return oVar;
            }
        }
        return null;
    }

    public float m() {
        return this.F;
    }

    public void a(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        this.F = f;
        n();
    }
}
