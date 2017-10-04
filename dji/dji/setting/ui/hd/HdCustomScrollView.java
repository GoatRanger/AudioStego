package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class HdCustomScrollView extends ScrollView {
    private GestureDetector a;

    class a extends SimpleOnGestureListener {
        final /* synthetic */ HdCustomScrollView a;

        a(HdCustomScrollView hdCustomScrollView) {
            this.a = hdCustomScrollView;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return Math.abs(f2) > Math.abs(f);
        }
    }

    public HdCustomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new GestureDetector(context, new a(this));
        setFadingEdgeLength(0);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent) && this.a.onTouchEvent(motionEvent);
    }
}
