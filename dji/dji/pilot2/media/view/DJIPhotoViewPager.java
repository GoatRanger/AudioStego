package dji.pilot2.media.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.ortiz.touch.TouchImageView;
import dji.publics.widget.djiviewpager.DJIViewPager;

public class DJIPhotoViewPager extends DJIViewPager {
    public DJIPhotoViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIPhotoViewPager(Context context) {
        super(context);
    }

    protected boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof TouchImageView) {
            return ((TouchImageView) view).canScrollHorizontallyFroyo(-i);
        }
        return super.canScroll(view, z, i, i2, i3);
    }
}
