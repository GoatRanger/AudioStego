package dji.pilot.fpv.sport;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ViewAnimator;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataFlycGetPushPowerParam;
import dji.pilot.R;
import dji.pilot.publics.e.f;
import dji.pilot.publics.widget.DJIClipView;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJISportAttitudeView extends DJIRelativeLayout {
    private static final int[] a = new int[]{5, 26, 78, 100};
    private static final int[] b = new int[]{30, 75, 270, 330};
    private static final int[] c = new int[]{0, 0, 1, 1};
    private DJIImageView d = null;
    private ViewAnimator e = null;
    private final DJIClipView[] f = new DJIClipView[2];
    private DJITextView g = null;
    private Animation h = null;
    private float i = 32767.0f;
    private float j = 32767.0f;

    public DJISportAttitudeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(DataFlycGetPushPowerParam.getInstance());
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            if (c.a().c(this)) {
                c.a().d(this);
            }
            setVisibility(8);
        }
    }

    public void onEventMainThread(DataFlycGetPushPowerParam dataFlycGetPushPowerParam) {
        float f = 9800.0f;
        float escAverageSpeed = dataFlycGetPushPowerParam.getEscAverageSpeed();
        if (escAverageSpeed <= 9800.0f) {
            f = escAverageSpeed;
        }
        if (Math.abs(f - this.i) >= 1.0f) {
            this.i = f;
            this.g.setText(String.format("%04d", new Object[]{Integer.valueOf((int) f)}));
        }
        f = dataFlycGetPushPowerParam.getLift();
        if (f < 0.0f || Float.compare(f, Float.NaN) == 0) {
            f = 0.0f;
        } else if (f > DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
            f = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
        }
        if (Math.abs(f - this.j) >= 0.1f) {
            this.j = f;
            int i = 0;
            while (i < a.length - 1 && f > ((float) a[i + 1])) {
                i++;
            }
            float f2 = ((float) b[i]) + (((f - ((float) a[i])) / ((float) (a[i + 1] - a[i]))) * ((float) (b[i + 1] - b[i])));
            int displayedChild = this.e.getDisplayedChild();
            if (displayedChild != c[i]) {
                displayedChild = c[i];
                if (displayedChild == 1) {
                    f.a(this.d, 0);
                    this.d.startAnimation(a());
                } else {
                    this.d.clearAnimation();
                    f.a(this.d, 4);
                }
                this.e.setDisplayedChild(displayedChild);
            }
            this.f[displayedChild].updateScale(f2 / 360.0f);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.d = (DJIImageView) findViewById(R.id.a8t);
            this.e = (ViewAnimator) findViewById(R.id.a8u);
            this.f[0] = (DJIClipView) findViewById(R.id.a8v);
            this.f[1] = (DJIClipView) findViewById(R.id.a8w);
            this.g = (DJITextView) findViewById(R.id.a8x);
            this.e.setInAnimation(a(true));
            this.e.setOutAnimation(a(false));
        }
    }

    private Animation a(boolean z) {
        Animation alphaAnimation;
        if (z) {
            alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        } else {
            alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        }
        alphaAnimation.setDuration(50);
        return alphaAnimation;
    }

    private Animation a() {
        if (this.h == null) {
            Animation alphaAnimation = new AlphaAnimation(d.c, 1.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setRepeatCount(-1);
            alphaAnimation.setRepeatMode(2);
            this.h = alphaAnimation;
        }
        return this.h;
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
