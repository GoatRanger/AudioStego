package dji.pilot2.nativeexplore.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ListView;

public class GLExploreList extends ListView {
    public GLExploreList(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public GLExploreList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public GLExploreList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GLExploreList(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
