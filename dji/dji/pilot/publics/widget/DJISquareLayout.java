package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class DJISquareLayout extends View {
    public DJISquareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        int min = Math.min(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(min, 1073741824), MeasureSpec.makeMeasureSpec(min, 1073741824));
    }
}
