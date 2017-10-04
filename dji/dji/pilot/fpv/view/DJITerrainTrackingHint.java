package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJITerrainTrackingHint extends DJIRelativeLayout {
    private DJIImageView a;
    private DJITextView b;
    private int c = 0;
    private int d = 0;

    public DJITerrainTrackingHint setImageViewResIndex(int i) {
        if (i != -1) {
            this.c = i;
            this.a.setImageResource(this.c);
        } else {
            this.a.setVisibility(8);
        }
        return this;
    }

    public DJITerrainTrackingHint setTextViewResIndex(int i) {
        this.d = i;
        this.b.setText(this.d);
        return this;
    }

    public DJITerrainTrackingHint(Context context) {
        super(context);
    }

    public DJITerrainTrackingHint(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (DJIImageView) findViewById(R.id.c09);
        this.b = (DJITextView) findViewById(R.id.c0_);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        this.a.startAnimation(alphaAnimation);
    }
}
