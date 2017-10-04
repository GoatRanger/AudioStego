package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import lecho.lib.hellocharts.f.d;
import lecho.lib.hellocharts.h.b;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.a;

public class k extends h {
    private static final int q = 64;
    private static final int r = 255;
    private static final int s = 2;
    private Paint t = new Paint();

    public k(Context context, a aVar, d dVar) {
        super(context, aVar, dVar);
        this.t.setAntiAlias(true);
        this.t.setColor(-3355444);
        this.t.setStrokeWidth((float) b.a(this.i, 2));
    }

    public void b(Canvas canvas) {
        super.b(canvas);
        Viewport d = this.c.d();
        float a = this.c.a(d.a);
        float b = this.c.b(d.b);
        float a2 = this.c.a(d.c);
        float b2 = this.c.b(d.d);
        this.t.setAlpha(64);
        this.t.setStyle(Style.FILL);
        canvas.drawRect(a, b, a2, b2, this.t);
        this.t.setStyle(Style.STROKE);
        this.t.setAlpha(255);
        canvas.drawRect(a, b, a2, b2, this.t);
    }

    public int k() {
        return this.t.getColor();
    }

    public void a(int i) {
        this.t.setColor(i);
    }
}
