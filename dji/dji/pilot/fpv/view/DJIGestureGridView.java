package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import dji.publics.DJIUI.DJIGridView;

public class DJIGestureGridView extends DJIGridView {
    private GestureDetector a = null;

    public DJIGestureGridView(Context context) {
        super(context);
    }

    public DJIGestureGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIGestureGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setGestureListener(OnGestureListener onGestureListener) {
        if (onGestureListener != null) {
            this.a = new GestureDetector(getContext(), onGestureListener);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.a != null) {
            this.a.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
