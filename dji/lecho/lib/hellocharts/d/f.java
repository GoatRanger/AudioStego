package lecho.lib.hellocharts.d;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

public class f extends b {

    protected class a extends SimpleOnScaleGestureListener {
        final /* synthetic */ f a;

        protected a(f fVar) {
            this.a = fVar;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (!this.a.h) {
                return false;
            }
            float currentSpan = scaleGestureDetector.getCurrentSpan() / scaleGestureDetector.getPreviousSpan();
            if (Float.isInfinite(currentSpan)) {
                currentSpan = 1.0f;
            }
            return this.a.d.a(this.a.f, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), currentSpan);
        }
    }

    protected class b extends a {
        final /* synthetic */ f c;

        protected b(f fVar) {
            this.c = fVar;
            super(fVar);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onScroll(motionEvent, motionEvent2, -f, -f2);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onFling(motionEvent, motionEvent2, -f, -f2);
        }
    }

    public f(Context context, lecho.lib.hellocharts.view.a aVar) {
        super(context, aVar);
        this.a = new GestureDetector(context, new b(this));
        this.b = new ScaleGestureDetector(context, new a(this));
        this.j = false;
        this.k = false;
    }
}
