package dji.pilot.usercenter.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import dji.pilot.R;

public class DJIProgressBar extends ProgressBar {
    private int a = R.color.je;

    public DJIProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setMaxColor(int i) {
        this.a = i;
    }

    protected synchronized void onDraw(Canvas canvas) {
        if (getProgress() == getMax()) {
            setProgressDrawable(getContext().getResources().getDrawable(this.a));
        } else {
            setProgressDrawable(getContext().getResources().getDrawable(R.drawable.upload_progressbar_style));
        }
        super.onDraw(canvas);
    }
}
