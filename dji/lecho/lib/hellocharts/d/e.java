package lecho.lib.hellocharts.d;

import android.content.Context;
import android.graphics.RectF;
import android.support.v4.widget.ScrollerCompat;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import lecho.lib.hellocharts.view.PieChartView;

public class e extends b {
    public static final int q = 4;
    protected ScrollerCompat r;
    protected PieChartView s;
    private boolean t = true;

    private class a extends SimpleOnGestureListener {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public boolean onDown(MotionEvent motionEvent) {
            if (!this.a.t) {
                return false;
            }
            this.a.r.abortAnimation();
            return true;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            return false;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!this.a.t) {
                return false;
            }
            RectF circleOval = this.a.s.getCircleOval();
            this.a.s.setChartRotation(this.a.s.getChartRotation() - (((int) a(f, f2, motionEvent2.getX() - circleOval.centerX(), motionEvent2.getY() - circleOval.centerY())) / 4), false);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!this.a.t) {
                return false;
            }
            RectF circleOval = this.a.s.getCircleOval();
            float a = a(f, f2, motionEvent2.getX() - circleOval.centerX(), motionEvent2.getY() - circleOval.centerY());
            this.a.r.abortAnimation();
            this.a.r.fling(0, this.a.s.getChartRotation(), 0, ((int) a) / 4, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            return true;
        }

        private float a(float f, float f2, float f3, float f4) {
            return ((float) Math.sqrt((double) ((f * f) + (f2 * f2)))) * Math.signum(((-f4) * f) + (f3 * f2));
        }
    }

    private class b extends SimpleOnScaleGestureListener {
        final /* synthetic */ e a;

        private b(e eVar) {
            this.a = eVar;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }
    }

    public e(Context context, PieChartView pieChartView) {
        super(context, pieChartView);
        this.s = pieChartView;
        this.r = ScrollerCompat.create(context);
        this.a = new GestureDetector(context, new a());
        this.b = new ScaleGestureDetector(context, new b());
        this.h = false;
    }

    public boolean b() {
        if (this.t && this.r.computeScrollOffset()) {
            this.s.setChartRotation(this.r.getCurrY(), false);
        }
        return false;
    }

    public boolean a(MotionEvent motionEvent) {
        boolean a = super.a(motionEvent);
        if (this.t) {
            return this.a.onTouchEvent(motionEvent) || a;
        } else {
            return a;
        }
    }

    public boolean h() {
        return this.t;
    }

    public void e(boolean z) {
        this.t = z;
    }
}
