package dji.setting.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.setting.ui.R;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIView;

public class DJITouchCurveView extends DJIView {
    public static float a = 0.8f;
    public static float b = 0.2f;
    Path c = new Path();
    Path d = new Path();
    private float e;
    private float f;
    private Paint g = new Paint();
    private Paint h = new Paint();
    private Paint i = new Paint();
    private Paint j = new Paint();
    private Paint k = new Paint();
    private Paint l = new Paint();
    private float m;
    private float n;
    private float o = 1.0f;
    private float p;
    private float q = 0.0f;
    private float r = 0.0f;
    private a s;
    private boolean t;
    private float u;
    private float v = 0.0f;

    public interface a {
        void a(View view);

        void a(View view, float f);

        void b(View view);
    }

    public static void setMaxMin(float f, float f2) {
        a = f;
        b = f2;
    }

    public DJITouchCurveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setClickable(true);
        if (!isInEditMode()) {
            this.e = context.getResources().getDimension(R.dimen.setting_ui_exp_width);
            this.f = this.e;
            this.m = this.e / 2.0f;
            this.n = this.f / 2.0f;
            this.g.setColor(context.getResources().getColor(R.color.setting_ui_exp_gray));
            this.g.setAntiAlias(true);
            this.g.setStyle(Style.STROKE);
            this.g.setStrokeWidth(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
            this.g.setTextSize(20.0f);
            this.h.setColor(context.getResources().getColor(R.color.setting_ui_exp_blue));
            this.h.setAntiAlias(true);
            this.h.setStyle(Style.STROKE);
            this.h.setStrokeWidth(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
            this.i.setColor(context.getResources().getColor(R.color.setting_ui_exp_gray));
            this.i.setAntiAlias(true);
            this.i.setStyle(Style.STROKE);
            this.i.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
            this.i.setStrokeWidth(2.0f);
            this.j.setColor(context.getResources().getColor(R.color.setting_ui_exp_gray));
            this.j.setAntiAlias(true);
            this.j.setStyle(Style.STROKE);
            this.j.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
            this.j.setStrokeWidth(2.0f);
            this.k.setColor(context.getResources().getColor(R.color.setting_ui_exp_orange));
            this.k.setAntiAlias(true);
            this.k.setStyle(Style.FILL);
            this.l.setColor(context.getResources().getColor(17170443));
            this.l.setAntiAlias(true);
            this.l.setStyle(Style.STROKE);
            this.l.setStrokeWidth(2.0f);
            a(d.c, 0.0f);
            this.p = c(this.m);
        }
    }

    public void setOnDJICurveTouchListener(a aVar) {
        this.s = aVar;
    }

    public void setOrder(float f) {
        a(d.c, f);
        postInvalidate();
    }

    public void setDoubleVertical(boolean z) {
        this.t = z;
    }

    public void setVerticalX(float f) {
        this.q = f;
        postInvalidate();
    }

    public void setVerticalX2(float f) {
        this.r = f;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        int i;
        int i2 = 0;
        canvas.drawText("x", this.e - 20.0f, this.n + 20.0f, this.g);
        canvas.drawText("y", this.m + 20.0f, 20.0f, this.g);
        canvas.drawRect(0.0f, 0.0f, this.e, this.f, this.i);
        this.c.reset();
        this.c.moveTo(0.0f, this.f);
        this.c.lineTo(this.e, 0.0f);
        canvas.drawPath(this.c, this.j);
        canvas.drawLine(0.0f, this.n, this.e, this.n, this.g);
        canvas.drawLine(this.m, 0.0f, this.m, this.f, this.g);
        this.c.reset();
        this.c.moveTo(this.m, this.n);
        int i3 = (int) (this.m + (this.q * this.m));
        int i4 = (int) (this.m + (this.r * this.m));
        int i5 = 0;
        for (i = (int) this.m; ((float) i) <= this.e; i++) {
            float c = c((float) i);
            this.c.lineTo((float) i, c);
            if (i == i3) {
                i2 = (int) c;
            }
            if (i == i4) {
                i5 = (int) c;
            }
        }
        this.d.reset();
        this.d.moveTo(this.m, this.n);
        int i6 = (int) this.m;
        int i7 = i5;
        int i8 = i2;
        while (i6 >= 0) {
            float d = d((float) i6);
            this.d.lineTo((float) i6, d);
            if (i6 == i3) {
                i5 = (int) d;
            } else {
                i5 = i8;
            }
            if (i6 == i4) {
                i = (int) d;
            } else {
                i = i7;
            }
            i6--;
            i7 = i;
            i8 = i5;
        }
        canvas.drawPath(this.c, this.h);
        canvas.drawPath(this.d, this.h);
        canvas.drawLine((float) i3, (float) i8, (float) i3, this.n, this.l);
        canvas.drawCircle((float) i3, (float) i8, 10.0f, this.k);
        if (this.t) {
            canvas.drawLine((float) i4, (float) i7, (float) i4, this.n, this.l);
            canvas.drawCircle((float) i4, (float) i7, 10.0f, this.k);
        }
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (motionEvent.getAction() == 0) {
            this.u = motionEvent.getY();
            if (this.s != null) {
                this.s.a(this);
            }
            parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        } else if (motionEvent.getAction() == 2) {
            this.v = (motionEvent.getY() - this.u) + this.p;
            float a = a(d.c, (this.f - this.v) / this.f);
            if (this.s != null) {
                this.s.a(this, a);
            }
            invalidate();
        } else if (motionEvent.getAction() == 1) {
            this.p = this.v;
            if (this.s != null) {
                this.s.b(this);
            }
            parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
        return true;
    }

    private float a(float f) {
        if (f > a) {
            f = a;
        }
        if (f < b) {
            return b;
        }
        return f;
    }

    private float a(float f, float f2) {
        float a = a(f2);
        this.o = (float) (Math.log((double) a) / Math.log((double) f));
        return a;
    }

    private float b(float f) {
        if (f == 0.0f) {
            return 0.0f;
        }
        double pow = Math.pow((double) f, (double) this.o);
        if (Double.isNaN(pow)) {
            pow = 0.0d;
        }
        return (float) pow;
    }

    private float c(float f) {
        return (1.0f - b((f - this.m) / this.m)) * this.n;
    }

    private float d(float f) {
        return (1.0f + b((this.m - f) / this.m)) * this.n;
    }
}
