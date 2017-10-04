package lecho.lib.hellocharts.d;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.widget.ScrollerCompat;
import lecho.lib.hellocharts.model.Viewport;

public class a {
    private Viewport a = new Viewport();
    private Point b = new Point();
    private ScrollerCompat c;

    public static class a {
        public boolean a;
        public boolean b;
    }

    public a(Context context) {
        this.c = ScrollerCompat.create(context);
    }

    public boolean a(lecho.lib.hellocharts.b.a aVar) {
        this.c.abortAnimation();
        this.a.a(aVar.d());
        return true;
    }

    public boolean a(lecho.lib.hellocharts.b.a aVar, float f, float f2, a aVar2) {
        boolean z;
        boolean z2;
        boolean z3;
        Viewport e = aVar.e();
        Viewport f3 = aVar.f();
        Viewport d = aVar.d();
        Rect b = aVar.b();
        boolean z4 = d.a > e.a;
        if (d.c < e.c) {
            z = true;
        } else {
            z = false;
        }
        if (d.b < e.b) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (d.d > e.d) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z4 && f <= 0.0f) {
            z = true;
        } else if (!z || f < 0.0f) {
            z = false;
        } else {
            z = true;
        }
        if (z2 && f2 <= 0.0f) {
            z4 = true;
        } else if (!z3 || f2 < 0.0f) {
            z4 = false;
        } else {
            z4 = true;
        }
        if (z || z4) {
            aVar.a(this.b);
            aVar.a(((f3.c() * f) / ((float) b.width())) + d.a, (((-f2) * f3.d()) / ((float) b.height())) + d.b);
        }
        aVar2.a = z;
        aVar2.b = z4;
        if (z || z4) {
            return true;
        }
        return false;
    }

    public boolean b(lecho.lib.hellocharts.b.a aVar) {
        if (!this.c.computeScrollOffset()) {
            return false;
        }
        Viewport e = aVar.e();
        aVar.a(this.b);
        aVar.a(e.a + ((e.c() * ((float) this.c.getCurrX())) / ((float) this.b.x)), e.b - ((e.d() * ((float) this.c.getCurrY())) / ((float) this.b.y)));
        return true;
    }

    public boolean a(int i, int i2, lecho.lib.hellocharts.b.a aVar) {
        aVar.a(this.b);
        this.a.a(aVar.d());
        int c = (int) ((((float) this.b.x) * (this.a.a - aVar.e().a)) / aVar.e().c());
        int d = (int) ((((float) this.b.y) * (aVar.e().b - this.a.b)) / aVar.e().d());
        this.c.abortAnimation();
        int width = aVar.b().width();
        int height = aVar.b().height();
        this.c.fling(c, d, i, i2, 0, (this.b.x - width) + 1, 0, (this.b.y - height) + 1);
        return true;
    }
}
