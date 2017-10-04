package dji.pilot.publics.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.R;
import dji.pilot.visual.a.d;
import dji.publics.d.c;
import java.util.ArrayList;

public class DJILineChartView extends View implements c {
    private static final boolean r = false;
    protected final Path a = new Path();
    protected final Paint b = new Paint();
    protected float[] c = null;
    protected boolean d = true;
    protected float e = 0.2f;
    protected boolean f = true;
    protected int g = 0;
    protected int h = 0;
    protected int i = 0;
    protected int j = 0;
    protected int k = 2;
    protected final ArrayList<a> l = new ArrayList();
    protected float m = 0.0f;
    protected float n = 0.0f;
    protected float o = 256.0f;
    protected float p = 58.0f;
    protected float q = 0.0f;

    private static final class a {
        public float a;
        public float b;
        public float c;
        public float d;

        private a(float f, float f2) {
            this.a = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.a = f;
            this.b = f2;
        }
    }

    public DJILineChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setData(float[] fArr) {
        this.c = fArr;
        if (this.m != 0.0f) {
            int width = getWidth();
            int height = getHeight();
            c(width, height);
            a(width, height);
            postInvalidate();
        }
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void hide() {
        if (getVisibility() != 4) {
            setVisibility(4);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            Resources resources = getResources();
            this.g = resources.getColor(R.color.ak);
            this.h = resources.getColor(R.color.bh);
            this.i = resources.getColor(R.color.bj);
            this.j = resources.getColor(R.color.be);
            setWillNotDraw(false);
            this.b.setAntiAlias(true);
        }
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(this.g);
        if (this.f) {
            a(canvas);
        }
        this.b.setStyle(Style.FILL);
        this.b.setColor(this.h);
        canvas.drawPath(this.a, this.b);
    }

    private void a(Canvas canvas) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        this.b.setStrokeWidth((float) this.k);
        this.b.setStyle(Style.STROKE);
        this.b.setColor(this.j);
        canvas.drawRect(0.0f, 0.0f, width, height, this.b);
        this.b.setColor(this.i);
        float f = (width - ((float) (this.k * 6))) / 5.0f;
        float round = ((float) this.k) + (((float) Math.round(((float) this.k) * d.c)) + f);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(round, 1.0f, round, height, this.b);
            round += ((float) this.k) + f;
        }
    }

    private void a(int i, int i2) {
        if (this.d) {
            e(i, i2);
        } else {
            d(i, i2);
        }
    }

    private void b(int i, int i2) {
        this.q = 20.0f;
        this.m = ((float) i) / (this.p - 1.0f);
        this.n = (((float) i2) - this.q) / this.o;
    }

    private void c(int i, int i2) {
        int i3 = 1;
        this.l.clear();
        if (this.c != null && this.c.length > 1) {
            this.l.add(new a(0.0f, ((float) i2) - (this.c[0] * this.n)));
            int length = this.c.length;
            while (i3 < length - 1) {
                this.l.add(new a(((float) i3) * this.m, ((float) i2) - (this.c[i3] * this.n)));
                i3++;
            }
            this.l.add(new a((float) i, ((float) i2) - (this.c[length - 1] * this.n)));
        }
    }

    private void d(int i, int i2) {
        this.a.reset();
        if (!this.l.isEmpty()) {
            this.a.moveTo(((a) this.l.get(0)).a, ((a) this.l.get(0)).b);
            int size = this.l.size();
            for (int i3 = 1; i3 < size; i3++) {
                a aVar = (a) this.l.get(i3);
                this.a.lineTo(aVar.a, aVar.b);
            }
            this.a.lineTo(((a) this.l.get(size - 1)).a, (float) i2);
            this.a.lineTo(0.0f, (float) i2);
        }
        this.a.close();
    }

    private void e(int i, int i2) {
        this.a.reset();
        if (!this.l.isEmpty()) {
            int size = this.l.size();
            for (int i3 = 0; i3 < size; i3++) {
                a aVar = (a) this.l.get(i3);
                a aVar2;
                if (i3 == 0) {
                    aVar2 = (a) this.l.get(i3 + 1);
                    aVar2.c = (aVar2.a - aVar.a) * this.e;
                    aVar2.d = (aVar2.b - aVar.b) * this.e;
                } else if (i3 == size - 1) {
                    aVar2 = (a) this.l.get(i3 - 1);
                    aVar.c = (aVar.a - aVar2.a) * this.e;
                    aVar.d = (aVar.b - aVar2.b) * this.e;
                } else {
                    aVar2 = (a) this.l.get(i3 + 1);
                    a aVar3 = (a) this.l.get(i3 - 1);
                    aVar.c = (aVar2.a - aVar3.a) * this.e;
                    aVar.d = (aVar2.b - aVar3.b) * this.e;
                }
                if (i3 == 0) {
                    this.a.moveTo(aVar.a, aVar.b);
                } else {
                    a aVar4 = (a) this.l.get(i3 - 1);
                    this.a.cubicTo(aVar4.a + aVar4.c, aVar4.d + aVar4.b, aVar.a - aVar.c, aVar.b - aVar.d, aVar.a, aVar.b);
                }
            }
            this.a.lineTo(((a) this.l.get(size - 1)).a, (float) i2);
            this.a.lineTo(0.0f, (float) i2);
        }
        this.a.close();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!isInEditMode() && i != i3) {
            b(i, i2);
            c(i, i2);
            a(i, i2);
        }
    }
}
