package dji.pilot2.media.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class DJIVideoView extends VideoView {
    public DJIVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
    }
}
