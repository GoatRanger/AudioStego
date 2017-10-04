package dji.pilot.support;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import dji.publics.widget.djiviewpager.DJIViewPager;

public class DJINonViewPager extends DJIViewPager {
    private boolean c = true;

    public DJINonViewPager(Context context) {
        super(context);
    }

    public DJINonViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setPagingEnabled(boolean z) {
        super.setPagingEnabled(z);
        this.c = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.c && super.onTouchEvent(motionEvent);
    }
}
