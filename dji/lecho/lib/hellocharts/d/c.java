package lecho.lib.hellocharts.d;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import lecho.lib.hellocharts.b.a;
import lecho.lib.hellocharts.model.Viewport;

public class c {
    public static final float a = 0.25f;
    private h b;
    private g c;
    private PointF d = new PointF();
    private PointF e = new PointF();
    private Viewport f = new Viewport();

    public c(Context context, g gVar) {
        this.b = new h(context);
        this.c = gVar;
    }

    public boolean a(MotionEvent motionEvent, a aVar) {
        this.b.a(true);
        this.f.a(aVar.d());
        if (!aVar.a(motionEvent.getX(), motionEvent.getY(), this.d)) {
            return false;
        }
        this.b.a((float) a);
        return true;
    }

    public boolean a(a aVar) {
        if (!this.b.b()) {
            return false;
        }
        float c = (1.0f - this.b.c()) * this.f.c();
        float c2 = (1.0f - this.b.c()) * this.f.d();
        float c3 = (this.d.x - this.f.a) / this.f.c();
        float d = (this.d.y - this.f.d) / this.f.d();
        a(aVar, this.d.x - (c * c3), this.d.y + ((1.0f - d) * c2), this.d.x + (c * (1.0f - c3)), this.d.y - (c2 * d));
        return true;
    }

    public boolean a(a aVar, float f, float f2, float f3) {
        float c = aVar.d().c() * f3;
        float d = aVar.d().d() * f3;
        if (!aVar.a(f, f2, this.e)) {
            return false;
        }
        float width = this.e.x - ((f - ((float) aVar.b().left)) * (c / ((float) aVar.b().width())));
        float height = this.e.y + ((f2 - ((float) aVar.b().top)) * (d / ((float) aVar.b().height())));
        a(aVar, width, height, width + c, height - d);
        return true;
    }

    private void a(a aVar, float f, float f2, float f3, float f4) {
        Viewport d = aVar.d();
        if (g.HORIZONTAL_AND_VERTICAL == this.c) {
            aVar.b(f, f2, f3, f4);
        } else if (g.HORIZONTAL == this.c) {
            aVar.b(f, d.b, f3, d.d);
        } else if (g.VERTICAL == this.c) {
            aVar.b(d.a, f2, d.c, f4);
        }
    }

    public g a() {
        return this.c;
    }

    public void a(g gVar) {
        this.c = gVar;
    }
}
