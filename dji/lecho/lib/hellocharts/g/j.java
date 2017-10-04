package lecho.lib.hellocharts.g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import lecho.lib.hellocharts.f.b;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.a;

public class j extends e {
    private static final int s = 64;
    private static final int t = 255;
    private static final int u = 2;
    private Paint v = new Paint();

    public j(Context context, a aVar, b bVar) {
        super(context, aVar, bVar);
        this.v.setAntiAlias(true);
        this.v.setColor(-3355444);
        this.v.setStrokeWidth((float) lecho.lib.hellocharts.h.b.a(this.i, 2));
    }

    public void b(Canvas canvas) {
        super.b(canvas);
        Viewport d = this.c.d();
        float a = this.c.a(d.a);
        float b = this.c.b(d.b);
        float a2 = this.c.a(d.c);
        float b2 = this.c.b(d.d);
        this.v.setAlpha(64);
        this.v.setStyle(Style.FILL);
        canvas.drawRect(a, b, a2, b2, this.v);
        this.v.setStyle(Style.STROKE);
        this.v.setAlpha(255);
        canvas.drawRect(a, b, a2, b2, this.v);
    }

    public int k() {
        return this.v.getColor();
    }

    public void a(int i) {
        this.v.setColor(i);
    }
}
