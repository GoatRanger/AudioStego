package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import lecho.lib.hellocharts.h.b;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.f;
import lecho.lib.hellocharts.model.n;

public abstract class a implements d {
    public int a = 4;
    protected lecho.lib.hellocharts.view.a b;
    protected lecho.lib.hellocharts.b.a c;
    protected Paint d = new Paint();
    protected Paint e = new Paint();
    protected RectF f = new RectF();
    protected FontMetricsInt g = new FontMetricsInt();
    protected boolean h = true;
    protected float i;
    protected float j;
    protected n k = new n();
    protected char[] l = new char[64];
    protected int m;
    protected int n;
    protected boolean o;
    protected boolean p;

    public a(Context context, lecho.lib.hellocharts.view.a aVar) {
        this.i = context.getResources().getDisplayMetrics().density;
        this.j = context.getResources().getDisplayMetrics().scaledDensity;
        this.b = aVar;
        this.c = aVar.getChartComputator();
        this.n = b.a(this.i, this.a);
        this.m = this.n;
        this.d.setAntiAlias(true);
        this.d.setStyle(Style.FILL);
        this.d.setTextAlign(Align.LEFT);
        this.d.setTypeface(Typeface.defaultFromStyle(1));
        this.d.setColor(-1);
        this.e.setAntiAlias(true);
        this.e.setStyle(Style.FILL);
    }

    public void a() {
        this.c = this.b.getChartComputator();
    }

    public void b() {
        f chartData = this.b.getChartData();
        Typeface g = this.b.getChartData().g();
        if (g != null) {
            this.d.setTypeface(g);
        }
        this.d.setColor(chartData.e());
        this.d.setTextSize((float) b.c(this.j, chartData.f()));
        this.d.getFontMetricsInt(this.g);
        this.o = chartData.h();
        this.p = chartData.i();
        this.e.setColor(chartData.j());
        this.k.a();
    }

    protected void a(Canvas canvas, char[] cArr, int i, int i2, int i3) {
        float f;
        float f2;
        if (this.o) {
            if (this.p) {
                this.e.setColor(i3);
            }
            canvas.drawRect(this.f, this.e);
            f = this.f.left + ((float) this.n);
            f2 = this.f.bottom - ((float) this.n);
        } else {
            f = this.f.left;
            f2 = this.f.bottom;
        }
        canvas.drawText(cArr, i, i2, f, f2, this.d);
    }

    public boolean c() {
        return this.k.b();
    }

    public void d() {
        this.k.a();
    }

    public Viewport e() {
        return this.c.e();
    }

    public void a(Viewport viewport) {
        if (viewport != null) {
            this.c.b(viewport);
        }
    }

    public Viewport f() {
        return this.c.d();
    }

    public void b(Viewport viewport) {
        if (viewport != null) {
            this.c.a(viewport);
        }
    }

    public boolean g() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void a(n nVar) {
        this.k.a(nVar);
    }

    public n h() {
        return this.k;
    }
}
