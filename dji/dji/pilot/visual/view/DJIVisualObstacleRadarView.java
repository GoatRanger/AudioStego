package dji.pilot.visual.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.dji.frame.c.e;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.pilot.visual.a.d;

public class DJIVisualObstacleRadarView extends View {
    private static final float d = 12.857142f;
    private int a = 100;
    private float b = 400.0f;
    private int c = 4;
    private int[] e = new int[]{5, 5, 5, 5, 5, 5, 5};
    private final Paint f = new Paint();
    private int g = 1442840575;
    private int h = 0;
    private final RectF i = new RectF();
    private boolean j = false;

    public DJIVisualObstacleRadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            this.f.setAntiAlias(true);
            this.f.setColor(getContext().getResources().getColor(R.color.db));
            this.f.setStyle(Style.STROKE);
            this.h = e.b(getContext(), 1.0f);
            if (this.h > 2) {
                this.h = 2;
            }
            this.f.setStrokeWidth((float) this.h);
        }
    }

    public void setPathEffect() {
        this.f.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
    }

    public void setInterval(int i) {
        this.a = i;
        postInvalidate();
    }

    public void setDistance(float f) {
        if (this.b != f) {
            this.b = f;
            postInvalidate();
        }
    }

    public void setLineDistance(int i) {
        this.c = i / this.a;
        postInvalidate();
    }

    public void setLines(int i) {
        this.c = i;
        postInvalidate();
    }

    public void setHasVisual(boolean z) {
        postInvalidate();
    }

    private float a(float f) {
        if (f <= ((float) (this.a * this.c))) {
            return 0.0f;
        }
        float log = (float) (Math.log((double) (f / ((float) (this.a * this.c)))) / Math.log(2.0d));
        return log - ((float) ((int) log));
    }

    private int b(float f) {
        return Color.argb((int) (((float) Color.alpha(this.g)) * (1.0f - f)), Color.red(this.g), Color.green(this.g), Color.blue(this.g));
    }

    private void a(Canvas canvas) {
        float a = a(this.b);
        float width = ((float) (getWidth() - 6)) * d.c;
        float f = 3.0f + width;
        float f2 = width / (DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity * (1.0f + a));
        int b = b(a);
        this.f.setStyle(Style.STROKE);
        this.f.setColor(this.g);
        canvas.drawCircle(f, f, width, this.f);
        for (int i = 1; ((float) i) * f2 < width; i++) {
            if (i % 2 == 0) {
                this.f.setColor(this.g);
            } else {
                this.f.setColor(b);
            }
            canvas.drawCircle(f, f, ((float) i) * f2, this.f);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c(canvas);
    }

    private int a(int i) {
        if (4 == i) {
            return -1442061547;
        }
        if (3 == i) {
            return 1426842389;
        }
        if (2 == i) {
            return 1439695387;
        }
        if (1 == i) {
            return -1429208549;
        }
        if (i == 0) {
            return -3145189;
        }
        return -15998187;
    }

    private void b(Canvas canvas) {
        int width = getWidth() - 1;
        RectF rectF = this.i;
        this.f.setStyle(Style.FILL);
        for (int i = 0; i < 7; i++) {
            this.f.setColor(a(this.e[i]));
            rectF.set(1.0f, 1.0f, (float) width, (float) width);
            canvas.drawArc(rectF, (-135.0f + (((float) i) * d)) + 1.0f, 13.857142f, true, this.f);
        }
    }

    private void c(Canvas canvas) {
        if (this.j) {
            b(canvas);
        }
        a(canvas);
    }
}
