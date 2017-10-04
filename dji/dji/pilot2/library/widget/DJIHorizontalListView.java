package dji.pilot2.library.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.meetme.android.horizontallistview.HorizontalListView;

public class DJIHorizontalListView extends HorizontalListView {
    private ViewPager e;

    public DJIHorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setViewPager(ViewPager viewPager) {
        this.e = viewPager;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.e != null) {
            this.e.requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
