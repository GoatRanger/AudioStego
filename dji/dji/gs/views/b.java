package dji.gs.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import com.dji.frame.c.e;
import com.dji.frame.c.g;
import dji.gs.R;

public class b {
    private static Bitmap a;
    private static Bitmap b;
    private static Bitmap p;
    private static Bitmap q;
    private static Bitmap r;
    private Bitmap c;
    private int d;
    private int e;
    private float[] f = new float[2];
    private float[] g = new float[2];
    private float[] h = new float[2];
    private float[] i = new float[2];
    private Paint j = new Paint(257);
    private Paint k;
    private float l = 0.0f;
    private float m = 0.0f;
    private int n;
    private float o;
    private float s = dji.midware.util.a.b.c;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;

    public b(Context context) {
        this.j.setColor(-1);
        this.j.setTextSize((float) e.b(context, 12.0f));
        this.j.setTypeface(Typeface.DEFAULT_BOLD);
        this.j.setTextAlign(Align.CENTER);
        this.k = new Paint(257);
        this.k.setColor(-1);
        this.k.setTextSize((float) e.b(context, 10.0f));
        this.k.setTypeface(Typeface.DEFAULT_BOLD);
        this.k.setTextAlign(Align.CENTER);
        this.l = a(this.k);
        if (a == null) {
            a = g.a(context, R.drawable.gs_marker_normal);
        }
        if (p == null) {
            p = g.a(context, R.drawable.gs_marker_selected);
        }
        if (q == null) {
            q = g.a(context, R.drawable.gs_marker_disabled);
        }
        if (b == null) {
            b = g.a(context, R.drawable.gs_lable);
        }
        int width = a.getWidth();
        int height = a.getHeight();
        int width2 = b.getWidth();
        int height2 = b.getHeight();
        this.h[0] = ((float) width) / 2.0f;
        this.h[1] = (float) height;
        this.d = Math.round(this.h[0]) + width2;
        this.e = Math.round(this.h[1]) + height2;
        this.f[0] = this.h[0];
        this.f[1] = (this.h[1] * 2.0f) / 5.0f;
        this.g[0] = this.h[0] + (((float) width2) / 2.0f);
        this.g[1] = ((this.h[1] + ((((float) height2) - this.l) / 2.0f)) + this.l) - this.m;
        this.c = Bitmap.createBitmap(this.d, this.e, Config.ARGB_8888);
        if (r == null) {
            r = Bitmap.createBitmap(Math.round(((float) this.d) * this.s), Math.round(((float) this.e) * this.s), Config.ARGB_8888);
        }
    }

    public static void a() {
    }

    public float[] b() {
        this.i[0] = this.h[0] / ((float) this.d);
        this.i[1] = this.h[1] / ((float) this.e);
        return this.i;
    }

    public boolean c() {
        return this.u;
    }

    public Bitmap a(int i, float f) {
        this.n = i;
        this.o = f;
        return a(this.t);
    }

    public Bitmap a(boolean z) {
        this.t = z;
        Bitmap e = this.v ? e() : z ? Bitmap.createBitmap(p) : this.u ? Bitmap.createBitmap(q) : Bitmap.createBitmap(a);
        return a(e);
    }

    public Bitmap b(boolean z) {
        this.u = z;
        return a(z ? Bitmap.createBitmap(q) : Bitmap.createBitmap(a));
    }

    public Bitmap b(int i, float f) {
        this.n = i;
        this.o = f;
        return b(a);
    }

    public Bitmap c(boolean z) {
        Bitmap createBitmap = z ? Bitmap.createBitmap(p) : Bitmap.createBitmap(a);
        System.out.println(createBitmap);
        return b(createBitmap);
    }

    public Bitmap d(boolean z) {
        return b(z ? Bitmap.createBitmap(q) : Bitmap.createBitmap(a));
    }

    private Bitmap a(Bitmap bitmap) {
        Canvas canvas = new Canvas(f());
        canvas.drawColor(0, Mode.CLEAR);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        canvas.drawText(String.valueOf(this.n + 1), this.f[0], this.f[1], this.j);
        if (this.o != 0.0f) {
            canvas.drawBitmap(b, this.h[0], this.h[1], null);
            if (dji.gs.b.a()) {
                canvas.drawText(Math.round(((double) this.o) * 3.28d) + "ft", this.g[0], this.g[1], this.k);
            } else {
                canvas.drawText(Math.round(this.o) + "M", this.g[0], this.g[1], this.k);
            }
        }
        return this.c;
    }

    private Bitmap b(Bitmap bitmap) {
        Canvas canvas = new Canvas(f());
        canvas.drawColor(0, Mode.CLEAR);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        canvas.drawText(String.valueOf(this.n + 1), this.f[0], this.f[1], this.j);
        if (this.o != 0.0f) {
            canvas.drawBitmap(b, this.h[0], this.h[1], null);
            if (dji.gs.b.a()) {
                canvas.drawText(Math.round(((double) this.o) * 3.28d) + "ft", this.g[0], this.g[1], this.k);
            } else {
                canvas.drawText(Math.round(this.o) + "M", this.g[0], this.g[1], this.k);
            }
        }
        e(true);
        return r;
    }

    private Bitmap f() {
        if (this.c.isRecycled()) {
            this.c = Bitmap.createBitmap(this.d, this.e, Config.ARGB_8888);
            a(this.n, this.o);
        }
        return this.c;
    }

    private Bitmap f(boolean z) {
        if (r.isRecycled()) {
            r = Bitmap.createBitmap(Math.round(((float) this.d) * this.s), Math.round(((float) this.e) * this.s), Config.ARGB_8888);
            if (z) {
                e(true);
            }
        }
        return r;
    }

    public void e(boolean z) {
        if (z) {
            Canvas canvas = new Canvas(f(false));
            canvas.drawColor(0, Mode.CLEAR);
            canvas.save();
            canvas.scale(this.s, this.s);
            canvas.drawBitmap(f(), 0.0f, 0.0f, null);
            canvas.restore();
        }
    }

    public Bitmap d() {
        return this.v ? e() : f();
    }

    public Bitmap e() {
        return f(true);
    }

    private float a(Paint paint) {
        if (this.l != 0.0f) {
            return this.l;
        }
        FontMetrics fontMetrics = paint.getFontMetrics();
        this.m = fontMetrics.descent + fontMetrics.leading;
        return fontMetrics.bottom - fontMetrics.top;
    }
}
