package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout.LayoutParams;
import dji.publics.DJIUI.DJIImageView;

public class GrayView extends DJIImageView {
    public GrayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (layoutParams.width <= 0) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(layoutParams.width, layoutParams.height);
        }
    }
}
