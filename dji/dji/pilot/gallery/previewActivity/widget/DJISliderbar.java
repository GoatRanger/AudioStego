package dji.pilot.gallery.previewActivity.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.log.DJILogHelper;
import dji.pilot.R;

public class DJISliderbar extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private Paint e;
    private double f;
    private double g;
    private double h = 0.0d;
    private Rect i;
    private final int j = 8;
    private final int k = 30;
    private double l = 30.0d;
    private a m;
    private Context n;

    public interface a {
        void a(double d);
    }

    public DJISliderbar(Context context) {
        super(context);
        this.n = context;
        a();
    }

    public DJISliderbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = context;
        a();
    }

    private void a() {
        this.a = Color.parseColor("#050303");
        this.b = this.n.getResources().getColor(R.color.om);
        this.e = new Paint();
        this.e.setAntiAlias(true);
    }

    public void setmListener(a aVar) {
        this.m = aVar;
    }

    public void setRange(double d, double d2) {
        this.f = d2;
        this.g = d;
        DJILogHelper.getInstance().LOGI("bob", "setRange min = " + d + " max=" + d2);
    }

    public void setValue(int i) {
        this.h = (double) i;
        DJILogHelper.getInstance().LOGI("bob", "setValue value=" + i);
        invalidate();
    }

    public double getCurValue() {
        return this.h;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.i == null) {
            this.i = new Rect(0, (this.d / 2) - 8, this.c, (this.d / 2) + 8);
        }
        this.e.setColor(this.a);
        canvas.drawRect(this.i, this.e);
        this.l = ((this.h - this.g) * ((double) this.c)) / (this.f - this.g);
        if (this.l >= ((double) (this.c - 30))) {
            this.l = (double) (this.c - 30);
        }
        if (this.l <= 30.0d) {
            this.l = 30.0d;
        }
        Rect rect = new Rect(0, (this.d / 2) - 8, (int) this.l, (this.d / 2) + 8);
        this.e.setColor(this.b);
        canvas.drawRect(rect, this.e);
        this.e.setColor(this.b);
        canvas.drawCircle((float) ((int) this.l), (float) (this.d / 2), 30.0f, this.e);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 30;
        int action = motionEvent.getAction();
        if (action == 0 || action == 2 || action == 1) {
            action = (int) motionEvent.getX();
            if (action >= this.c - 30) {
                action = this.c - 30;
            }
            if (action > 30) {
                i = action;
            }
            this.l = (double) i;
            this.h = ((this.l * (this.f - this.g)) / ((double) (this.c - 30))) + this.g;
            if (this.m != null) {
                this.m.a(this.h);
            }
            invalidate();
        }
        return true;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.c = getMeasuredWidth();
        this.d = getMeasuredHeight();
        this.i = new Rect(0, this.d / 3, this.c, (this.d * 2) / 3);
    }
}
