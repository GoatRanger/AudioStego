package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.widget.AutoScrollHelper;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import lecho.lib.hellocharts.c.b;
import lecho.lib.hellocharts.f.a;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.d;
import lecho.lib.hellocharts.model.e;
import lecho.lib.hellocharts.model.h;
import lecho.lib.hellocharts.model.n;
import lecho.lib.hellocharts.model.q;

public class c extends a {
    private static final int q = 4;
    private static final int r = 0;
    private static final int s = 1;
    private PointF A = new PointF();
    private Paint B = new Paint();
    private RectF C = new RectF();
    private boolean D;
    private boolean E;
    private b F;
    private Viewport G = new Viewport();
    private a t;
    private int u;
    private float v;
    private float w;
    private boolean x = true;
    private float y;
    private float z;

    public c(Context context, lecho.lib.hellocharts.view.a aVar, a aVar2) {
        super(context, aVar);
        this.t = aVar2;
        this.u = lecho.lib.hellocharts.h.b.a(this.i, 4);
        this.B.setAntiAlias(true);
        this.B.setStyle(Style.FILL);
    }

    public void i() {
        Rect b = this.b.getChartComputator().b();
        if (b.width() < b.height()) {
            this.x = true;
        } else {
            this.x = false;
        }
    }

    public void b() {
        super.b();
        d bubbleChartData = this.t.getBubbleChartData();
        this.D = bubbleChartData.n();
        this.E = bubbleChartData.o();
        this.F = bubbleChartData.r();
        j();
    }

    public void j() {
        if (this.h) {
            l();
            this.c.b(this.G);
            this.c.a(this.c.e());
        }
    }

    public void a(Canvas canvas) {
        c(canvas);
        if (c()) {
            d(canvas);
        }
    }

    public void b(Canvas canvas) {
    }

    public boolean a(float f, float f2) {
        this.k.a();
        int i = 0;
        for (e eVar : this.t.getBubbleChartData().m()) {
            float a = a(eVar, this.A);
            if (q.SQUARE.equals(eVar.g())) {
                if (this.C.contains(f, f2)) {
                    this.k.a(i, i, n.a.NONE);
                }
            } else if (q.CIRCLE.equals(eVar.g())) {
                float f3 = f - this.A.x;
                float f4 = f2 - this.A.y;
                if (((float) Math.sqrt((double) ((f3 * f3) + (f4 * f4)))) <= a) {
                    this.k.a(i, i, n.a.NONE);
                }
            } else {
                throw new IllegalArgumentException("Invalid bubble shape: " + eVar.g());
            }
            i++;
        }
        return c();
    }

    public void k() {
        float f = 0.0f;
        Rect b = this.c.b();
        if (b.height() != 0 && b.width() != 0) {
            float c = this.c.c(this.y * this.v);
            float d = this.c.d(this.y * this.w);
            float c2 = this.c.e().c() / ((float) b.width());
            float d2 = this.c.e().d() / ((float) b.height());
            if (this.x) {
                d2 = (d2 * (d - c)) * h.l;
            } else {
                f = ((c - d) * c2) * h.l;
                d2 = 0.0f;
            }
            Viewport e = this.c.e();
            e.c(f, d2);
            Viewport d3 = this.c.d();
            d3.c(f, d2);
            this.c.b(e);
            this.c.a(d3);
        }
    }

    private void c(Canvas canvas) {
        for (e a : this.t.getBubbleChartData().m()) {
            a(canvas, a);
        }
    }

    private void a(Canvas canvas, e eVar) {
        float a = a(eVar, this.A) - ((float) this.u);
        this.C.inset((float) this.u, (float) this.u);
        this.B.setColor(eVar.e());
        a(canvas, eVar, a, 0);
    }

    private void a(Canvas canvas, e eVar, float f, int i) {
        if (q.SQUARE.equals(eVar.g())) {
            canvas.drawRect(this.C, this.B);
        } else if (q.CIRCLE.equals(eVar.g())) {
            canvas.drawCircle(this.A.x, this.A.y, f, this.B);
        } else {
            throw new IllegalArgumentException("Invalid bubble shape: " + eVar.g());
        }
        if (1 == i) {
            if (this.D || this.E) {
                a(canvas, eVar, this.A.x, this.A.y);
            }
        } else if (i != 0) {
            throw new IllegalStateException("Cannot process bubble in mode: " + i);
        } else if (this.D) {
            a(canvas, eVar, this.A.x, this.A.y);
        }
    }

    private void d(Canvas canvas) {
        b(canvas, (e) this.t.getBubbleChartData().m().get(this.k.c()));
    }

    private void b(Canvas canvas, e eVar) {
        float a = a(eVar, this.A);
        this.B.setColor(eVar.f());
        a(canvas, eVar, a, 1);
    }

    private float a(e eVar, PointF pointF) {
        float a = this.c.a(eVar.b());
        float b = this.c.b(eVar.c());
        float sqrt = (float) Math.sqrt(((double) Math.abs(eVar.d())) / 3.141592653589793d);
        if (this.x) {
            sqrt = this.c.c(sqrt * this.v);
        } else {
            sqrt = this.c.d(sqrt * this.w);
        }
        if (sqrt < this.z + ((float) this.u)) {
            sqrt = this.z + ((float) this.u);
        }
        this.A.set(a, b);
        if (q.SQUARE.equals(eVar.g())) {
            this.C.set(a - sqrt, b - sqrt, a + sqrt, b + sqrt);
        }
        return sqrt;
    }

    private void a(Canvas canvas, e eVar, float f, float f2) {
        Rect b = this.c.b();
        int a = this.F.a(this.l, eVar);
        if (a != 0) {
            float measureText = this.d.measureText(this.l, this.l.length - a, a);
            int abs = Math.abs(this.g.ascent);
            float f3 = (f - (measureText / 2.0f)) - ((float) this.n);
            float f4 = ((measureText / 2.0f) + f) + ((float) this.n);
            float f5 = (f2 - ((float) (abs / 2))) - ((float) this.n);
            float f6 = (((float) (abs / 2)) + f2) + ((float) this.n);
            if (f5 < ((float) b.top)) {
                f6 = (((float) abs) + f2) + ((float) (this.n * 2));
                f5 = f2;
            }
            if (f6 > ((float) b.bottom)) {
                f5 = (f2 - ((float) abs)) - ((float) (this.n * 2));
            } else {
                f2 = f6;
            }
            if (f3 < ((float) b.left)) {
                f6 = (f + measureText) + ((float) (this.n * 2));
                f4 = f;
            } else {
                f6 = f4;
                f4 = f3;
            }
            if (f6 > ((float) b.right)) {
                f4 = (f - measureText) - ((float) (this.n * 2));
            } else {
                f = f6;
            }
            this.f.set(f4, f5, f, f2);
            a(canvas, this.l, this.l.length - a, a, eVar.f());
        }
    }

    private void l() {
        this.G.a(AutoScrollHelper.NO_MAX, Float.MIN_VALUE, Float.MIN_VALUE, AutoScrollHelper.NO_MAX);
        d bubbleChartData = this.t.getBubbleChartData();
        float f = Float.MIN_VALUE;
        for (e eVar : bubbleChartData.m()) {
            if (Math.abs(eVar.d()) > f) {
                f = Math.abs(eVar.d());
            }
            if (eVar.b() < this.G.a) {
                this.G.a = eVar.b();
            }
            if (eVar.b() > this.G.c) {
                this.G.c = eVar.b();
            }
            if (eVar.c() < this.G.d) {
                this.G.d = eVar.c();
            }
            if (eVar.c() > this.G.b) {
                this.G.b = eVar.c();
            }
        }
        this.y = (float) Math.sqrt(((double) f) / 3.141592653589793d);
        this.v = this.G.c() / (this.y * DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        if (this.v == 0.0f) {
            this.v = 1.0f;
        }
        this.w = this.G.d() / (this.y * DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        if (this.w == 0.0f) {
            this.w = 1.0f;
        }
        this.v *= bubbleChartData.q();
        this.w *= bubbleChartData.q();
        this.G.c((-this.y) * this.v, (-this.y) * this.w);
        this.z = (float) lecho.lib.hellocharts.h.b.a(this.i, this.t.getBubbleChartData().p());
    }
}
