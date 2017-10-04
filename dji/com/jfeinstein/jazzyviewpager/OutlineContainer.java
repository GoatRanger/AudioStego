package com.jfeinstein.jazzyviewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.frame.widget.R;

public class OutlineContainer extends FrameLayout implements Animatable {
    private static final long e = 500;
    private static final long f = 16;
    private Paint a;
    private boolean b = false;
    private long c;
    private float d = 1.0f;
    private final Interpolator g = new Interpolator(this) {
        final /* synthetic */ OutlineContainer a;

        {
            this.a = r1;
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (f2 * f2)) + 1.0f;
        }
    };
    private final Runnable h = new Runnable(this) {
        final /* synthetic */ OutlineContainer a;

        {
            this.a = r1;
        }

        public void run() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - this.a.c;
            if (currentAnimationTimeMillis >= 500) {
                this.a.d = 0.0f;
                this.a.invalidate();
                this.a.stop();
                return;
            }
            this.a.d = this.a.g.getInterpolation(1.0f - (((float) currentAnimationTimeMillis) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition));
            this.a.invalidate();
            this.a.postDelayed(this.a.h, 16);
        }
    };

    public OutlineContainer(Context context) {
        super(context);
        a();
    }

    public OutlineContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public OutlineContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setStrokeWidth((float) a.a(getResources(), 2));
        this.a.setColor(getResources().getColor(R.color.holo_blue));
        this.a.setStyle(Style.STROKE);
        int a = a.a(getResources(), 10);
        setPadding(a, a, a, a);
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int a = a.a(getResources(), 5);
        if (this.a.getColor() != JazzyViewPager.b) {
            this.a.setColor(JazzyViewPager.b);
        }
        this.a.setAlpha((int) (this.d * 255.0f));
        canvas.drawRect(new Rect(a, a, getMeasuredWidth() - a, getMeasuredHeight() - a), this.a);
    }

    public void setOutlineAlpha(float f) {
        this.d = f;
    }

    public boolean isRunning() {
        return this.b;
    }

    public void start() {
        if (!this.b) {
            this.b = true;
            this.c = AnimationUtils.currentAnimationTimeMillis();
            post(this.h);
        }
    }

    public void stop() {
        if (this.b) {
            this.b = false;
        }
    }
}
