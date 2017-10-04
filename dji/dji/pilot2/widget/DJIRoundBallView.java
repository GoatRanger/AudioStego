package dji.pilot2.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.R;
import java.util.ArrayList;
import java.util.Iterator;

public class DJIRoundBallView extends View {
    private int a = 8;
    private int b = 300;
    private int c = 200;
    private int d = 20;
    private int e = 10;
    private boolean f = false;
    private ArrayList<a> g = new ArrayList();
    private b h;
    private int i;
    private int j;

    public class a {
        double a;
        double b;
        float c;
        boolean d;
        double e;
        c f;
        final /* synthetic */ DJIRoundBallView g;

        public a(DJIRoundBallView dJIRoundBallView) {
            this.g = dJIRoundBallView;
        }
    }

    private class b extends Thread {
        final /* synthetic */ DJIRoundBallView a;

        public b(DJIRoundBallView dJIRoundBallView, String str) {
            this.a = dJIRoundBallView;
            super(str);
        }

        public void run() {
            while (this.a.f) {
                Iterator it = this.a.g.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    float f = (float) aVar.a;
                    float f2 = (float) aVar.b;
                    if (aVar.f == c.LEFT) {
                        aVar.a = (double) (f - (aVar.c * ((float) this.a.e)));
                    } else {
                        aVar.a = (double) (f + (aVar.c * ((float) this.a.e)));
                    }
                    if (aVar.a > ((double) this.a.i) || aVar.a < 0.0d) {
                        it.remove();
                    }
                }
                for (int i = 0; i < this.a.a - this.a.g.size(); i++) {
                    this.a.b();
                }
                this.a.postInvalidate();
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private enum c {
        LEFT,
        RIGHT
    }

    public DJIRoundBallView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
        a(context);
    }

    public DJIRoundBallView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
        a(context);
    }

    public DJIRoundBallView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        a(context);
    }

    public DJIRoundBallView(Context context) {
        super(context);
        a();
        a(context);
    }

    private void a() {
        for (int i = 0; i < this.a; i++) {
            a aVar = new a(this);
            aVar.a = Math.random() * ((double) this.b);
            aVar.b = (Math.random() * ((double) this.c)) + 10.0d;
            aVar.c = (float) (1.0d * Math.random());
            if (aVar.c == 0.0f) {
                aVar.c = 1.0f;
            }
            aVar.d = true;
            aVar.e = Math.random() * ((double) this.d);
            if (i % 2 == 0) {
                aVar.f = c.LEFT;
            } else {
                aVar.f = c.RIGHT;
            }
            this.g.add(aVar);
        }
    }

    private void a(Context context) {
        this.i = context.getResources().getDimensionPixelSize(R.dimen.a8m);
        this.j = context.getResources().getDimensionPixelSize(R.dimen.a8k);
        this.h = new b(this, "times");
        this.h.start();
        this.f = true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(-11890462);
            paint.setStyle(Style.FILL);
            for (int i = 0; i < this.g.size(); i++) {
                a aVar = (a) this.g.get(i);
                canvas.drawCircle((float) aVar.a, (float) aVar.b, (float) aVar.e, paint);
            }
        }
    }

    private void b() {
        a aVar = new a(this);
        if (Math.random() > 0.5d) {
            aVar.f = c.LEFT;
        } else {
            aVar.f = c.RIGHT;
        }
        aVar.a = Math.random() * ((double) this.b);
        aVar.b = (Math.random() * ((double) this.c)) + 10.0d;
        aVar.c = (float) (1.0d * Math.random());
        if (aVar.c == 0.0f) {
            aVar.c = 1.0f;
        }
        aVar.d = true;
        aVar.e = Math.random() * ((double) this.d);
        this.g.add(aVar);
    }
}
