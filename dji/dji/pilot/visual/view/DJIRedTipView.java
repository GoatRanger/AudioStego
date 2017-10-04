package dji.pilot.visual.view;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.pilot.visual.util.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIRedTipView extends DJIRelativeLayout implements Callback, a {
    private static final int n = 4096;
    private static final int o = 4097;
    private static final long p = 400;
    private DJIRelativeLayout a = null;
    private DJIImageView b = null;
    private DJIImageView c = null;
    private DJIImageView d = null;
    private DJIRelativeLayout e = null;
    private DJIImageView f = null;
    private DJIImageView g = null;
    private DJIImageView h = null;
    private Animation i = null;
    private AnimationListener j = null;
    private k k = null;
    private final Animation[] l = new Animation[2];
    private float m = 0.0f;

    public DJIRedTipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.k = new k(this, this);
            this.i = new AlphaAnimation(1.0f, 0.0f);
            this.i.setDuration(1000);
            this.j = new AnimationListener(this) {
                final /* synthetic */ DJIRedTipView a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.f.go();
                    this.a.b.go();
                }
            };
            this.i.setAnimationListener(this.j);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIRelativeLayout) findViewById(R.id.d_1);
            this.b = (DJIImageView) findViewById(R.id.d_2);
            this.c = (DJIImageView) findViewById(R.id.d_3);
            this.d = (DJIImageView) findViewById(R.id.d_4);
            this.e = (DJIRelativeLayout) findViewById(R.id.d_5);
            this.f = (DJIImageView) findViewById(R.id.d_6);
            this.g = (DJIImageView) findViewById(R.id.d_7);
            this.h = (DJIImageView) findViewById(R.id.d_8);
            this.d.setLayerType(1, null);
            this.h.setLayerType(1, null);
        }
    }

    public void handleGimbalChanged(float f) {
        int i = 0;
        if (Math.abs(this.m - f) > 0.2f) {
            this.m = f;
            float a = c.a(dji.pilot.visual.a.c.getInstance().i);
            int i2 = dji.pilot.visual.a.c.getInstance().e;
            float f2 = (a / 2.0f) + f;
            float f3 = f - (a / 2.0f);
            float a2 = c.a(i.getInstance().c());
            float f4 = 54.0f - a2;
            f4 = 0.0f - (f4 / 2.0f);
            int i3 = (int) (((f2 - (a2 + (f4 / 2.0f))) * ((float) i2)) / a);
            if (i3 < 0) {
                i3 = 0;
            }
            LayoutParams layoutParams = this.a.getLayoutParams();
            if (layoutParams.height != i3) {
                layoutParams.height = i3;
                this.a.setLayoutParams(layoutParams);
                this.d.clearAnimation();
                this.d.show();
                this.k.removeMessages(4096);
                this.k.sendEmptyMessageDelayed(4096, p);
            }
            i3 = (int) (((f4 - f3) * ((float) i2)) / a);
            if (i3 >= 0) {
                if (i3 > i2) {
                    i = i2;
                } else {
                    i = i3;
                }
            }
            LayoutParams layoutParams2 = this.e.getLayoutParams();
            if (layoutParams2.height != i) {
                layoutParams2.height = i;
                this.e.setLayoutParams(layoutParams2);
                this.h.clearAnimation();
                this.h.show();
                this.k.removeMessages(4097);
                this.k.sendEmptyMessageDelayed(4097, p);
            }
        }
    }

    public boolean isInTipBounds(float f, float f2) {
        int i = dji.pilot.visual.a.c.getInstance().e;
        LayoutParams layoutParams = this.a.getLayoutParams();
        LayoutParams layoutParams2 = this.e.getLayoutParams();
        if (((float) layoutParams.height) >= f2) {
            this.f.go();
            this.b.show();
            this.b.startAnimation(this.i);
            return true;
        } else if (((float) layoutParams2.height) <= ((float) i) - f2) {
            return false;
        } else {
            this.b.go();
            this.f.show();
            this.f.startAnimation(this.i);
            return true;
        }
    }

    private Animation a(final int i) {
        if (this.l[i] == null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(200);
            alphaAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DJIRedTipView b;

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (i == 0) {
                        this.b.d.go();
                    } else if (i == 1) {
                        this.b.h.go();
                    }
                }
            });
            this.l[i] = alphaAnimation;
        }
        return this.l[i];
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                this.d.startAnimation(a(0));
                break;
            case 4097:
                this.h.startAnimation(a(1));
                break;
        }
        return false;
    }

    public boolean isFinished() {
        return getVisibility() != 0;
    }
}
