package dji.pilot.visual.beginner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIBeginnerPointGuideView extends DJIRelativeLayout {
    private DJIImageView a = null;
    private DJIImageView b = null;
    private Animation c = null;

    public DJIBeginnerPointGuideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            this.b.startAnimation(this.c);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            this.b.clearAnimation();
            setVisibility(8);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.a = (DJIImageView) findViewById(R.id.d88);
            this.b = (DJIImageView) findViewById(R.id.d89);
            this.c = AnimationUtils.loadAnimation(getContext(), R.anim.am);
        }
    }
}
