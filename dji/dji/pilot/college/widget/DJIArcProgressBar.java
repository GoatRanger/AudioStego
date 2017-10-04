package dji.pilot.college.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.dji.frame.c.e;
import dji.pilot.R;
import dji.pilot.R$styleable;
import dji.publics.d.c;

public class DJIArcProgressBar extends View implements c {
    private final Paint a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private final RectF j;

    public DJIArcProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIArcProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = new RectF();
        a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ArcPgb, i, 0);
        this.d = obtainStyledAttributes.getColor(1, this.d);
        this.e = obtainStyledAttributes.getColor(2, this.e);
        this.b = obtainStyledAttributes.getDimensionPixelSize(3, this.b);
        this.f = obtainStyledAttributes.getInt(0, 0);
        this.c = obtainStyledAttributes.getDimensionPixelSize(4, this.c);
        this.g = obtainStyledAttributes.getDimensionPixelSize(5, this.g);
        obtainStyledAttributes.recycle();
    }

    public void setProgress(int i) {
        this.f = i;
        postInvalidate();
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

    private void a(Context context) {
        Resources resources = context.getResources();
        this.d = resources.getColor(R.color.om);
        this.e = resources.getColor(R.color.jf);
        this.c = e.b(context, 1.0f);
        this.g = resources.getDimensionPixelSize(R.dimen.rp);
        this.b = e.b(context, 2.0f);
        this.a.setAntiAlias(true);
        this.h = resources.getDimensionPixelSize(R.dimen.fj);
        this.i = 0;
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        float f = ((float) width) / 2.0f;
        this.a.setStrokeWidth((float) this.c);
        this.a.setColor(this.d);
        this.a.setStyle(Style.STROKE);
        canvas.drawCircle(f, f, f - ((float) this.c), this.a);
        this.a.setStrokeWidth((float) this.b);
        this.a.setColor(this.e);
        this.a.setStyle(Style.STROKE);
        int i = this.c;
        this.j.set((float) i, (float) i, (float) (width - i), (float) (width - i));
        canvas.save();
        canvas.translate(f, f);
        canvas.rotate(-90.0f);
        canvas.translate(-f, -f);
        canvas.drawArc(this.j, 0.0f, (float) ((this.f * 360) / 100), false, this.a);
        canvas.restore();
        this.a.setStyle(Style.FILL);
        this.a.setColor(this.d);
        this.j.set(f - ((float) this.h), f - ((float) this.h), ((float) this.h) + f, ((float) this.h) + f);
        canvas.drawRoundRect(this.j, (float) this.i, (float) this.i, this.a);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
