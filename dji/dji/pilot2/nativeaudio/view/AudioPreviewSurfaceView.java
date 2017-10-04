package dji.pilot2.nativeaudio.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class AudioPreviewSurfaceView extends SurfaceView {
    @SuppressLint({"NewApi"})
    public AudioPreviewSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public AudioPreviewSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AudioPreviewSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AudioPreviewSurfaceView(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, (measuredWidth * 9) / 16);
    }
}
