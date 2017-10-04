package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class DJIInterceptScrollView extends ScrollView {
    private boolean a;

    public DJIInterceptScrollView(Context context) {
        super(context);
    }

    public DJIInterceptScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setIntercept(boolean z) {
        this.a = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.a ? false : super.onInterceptTouchEvent(motionEvent);
    }
}
