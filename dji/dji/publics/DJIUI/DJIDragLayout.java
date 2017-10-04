package dji.publics.DJIUI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DJIDragLayout extends DJIRelativeLayout {
    protected int mDeltaX = 0;
    protected int mDeltaY = 0;
    protected int mHeight = 0;
    protected boolean mIsDragging = false;
    protected boolean mSupportDrag = false;
    protected int mWidth = 0;

    public DJIDragLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
    }

    protected void trackMotion(MotionEvent motionEvent) {
        trackXY(motionEvent.getRawX(), motionEvent.getRawY());
    }

    protected void trackXY(float f, float f2) {
        setX(f - ((float) (this.mWidth / 2)));
        setY(f2 - ((float) (this.mHeight / 2)));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mSupportDrag) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.mIsDragging = true;
                    this.mDeltaX = (int) motionEvent.getX();
                    this.mDeltaY = (int) motionEvent.getY();
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case 1:
                case 3:
                    if (this.mIsDragging) {
                        this.mIsDragging = false;
                        break;
                    }
                    break;
                case 2:
                    if (this.mIsDragging) {
                        trackMotion(motionEvent);
                        break;
                    }
                    break;
            }
        }
        return this.mIsDragging;
    }
}
