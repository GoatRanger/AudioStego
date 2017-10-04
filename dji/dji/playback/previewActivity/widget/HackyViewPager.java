package dji.playback.previewActivity.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import dji.pilot.support.DJINonViewPager;

public class HackyViewPager extends DJINonViewPager {
    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
