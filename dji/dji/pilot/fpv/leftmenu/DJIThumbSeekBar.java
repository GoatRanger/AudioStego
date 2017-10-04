package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class DJIThumbSeekBar extends SeekBar {
    private boolean a = true;

    public DJIThumbSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Drawable thumb = getThumb();
        if (thumb == null) {
            this.a = true;
        } else if (motionEvent.getAction() == 0) {
            if (thumb.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                this.a = true;
            } else {
                this.a = false;
            }
        }
        if (this.a) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }
}
