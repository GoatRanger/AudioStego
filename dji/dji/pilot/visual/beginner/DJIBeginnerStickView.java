package dji.pilot.visual.beginner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.lang.reflect.Array;

public class DJIBeginnerStickView extends DJILinearLayout {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    private static final long g = 300;
    private static final int h = 3;
    private DJIRelativeLayout[] i = new DJIRelativeLayout[2];
    private DJIImageView[] j = new DJIImageView[2];
    private DJIImageView[] k = new DJIImageView[2];
    private DJIImageView[] l = new DJIImageView[2];
    private DJIImageView[] m = new DJIImageView[2];
    private DJIImageView[] n = new DJIImageView[2];
    private final Animation[][] o = ((Animation[][]) Array.newInstance(Animation.class, new int[]{2, 4}));

    public DJIBeginnerStickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.i[0] = (DJIRelativeLayout) findViewById(R.id.d8_);
            this.j[0] = (DJIImageView) findViewById(R.id.d8a);
            this.m[0] = (DJIImageView) findViewById(R.id.d8c);
            this.n[0] = (DJIImageView) findViewById(R.id.d8d);
            this.k[0] = (DJIImageView) findViewById(R.id.d8e);
            this.l[0] = (DJIImageView) findViewById(R.id.d8f);
            this.i[1] = (DJIRelativeLayout) findViewById(R.id.d8g);
            this.j[1] = (DJIImageView) findViewById(R.id.d8h);
            this.m[1] = (DJIImageView) findViewById(R.id.d8j);
            this.n[1] = (DJIImageView) findViewById(R.id.d8k);
            this.k[1] = (DJIImageView) findViewById(R.id.d8l);
            this.l[1] = (DJIImageView) findViewById(R.id.d8m);
        }
    }

    public void animByDirection(int i, int i2) {
        int i3 = i == 0 ? 1 : 0;
        this.j[i3].setAlpha(0.3f);
        this.k[i3].go();
        this.m[i3].go();
        this.l[i3].go();
        this.n[i3].go();
        this.j[i].setAlpha(1.0f);
        if (i2 == 0) {
            this.m[i].go();
            this.n[i].go();
            this.k[i].show();
            this.l[i].go();
            this.k[i].startAnimation(a(i, 0));
            return;
        }
        this.k[i].go();
        this.l[i].go();
        this.m[i].show();
        this.n[i].go();
        this.m[i].startAnimation(a(i, 2));
    }

    private Animation a(final int i, int i2) {
        Animation translateAnimation;
        if (i2 == 0) {
            if (this.o[i][0] != null) {
                return this.o[i][0];
            }
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, -0.3f, 1, 0.0f, 1, 0.2f);
            translateAnimation.setDuration(300);
            translateAnimation.setRepeatMode(2);
            translateAnimation.setRepeatCount(3);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DJIBeginnerStickView b;

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.k[i].go();
                    this.b.l[i].show();
                    this.b.l[i].startAnimation(this.b.a(i, 1));
                }
            });
            this.o[i][0] = translateAnimation;
            return translateAnimation;
        } else if (i2 == 1) {
            if (this.o[i][1] != null) {
                return this.o[i][1];
            }
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.3f, 1, 0.0f, 1, 0.2f);
            translateAnimation.setDuration(300);
            translateAnimation.setRepeatMode(2);
            translateAnimation.setRepeatCount(3);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DJIBeginnerStickView b;

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.l[i].go();
                    this.b.k[i].show();
                    this.b.k[i].startAnimation(this.b.a(i, 0));
                }
            });
            this.o[i][1] = translateAnimation;
            return translateAnimation;
        } else if (i2 == 2) {
            if (this.o[i][2] != null) {
                return this.o[i][2];
            }
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -0.3f);
            translateAnimation.setDuration(300);
            translateAnimation.setRepeatMode(2);
            translateAnimation.setRepeatCount(3);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DJIBeginnerStickView b;

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.m[i].go();
                    this.b.n[i].show();
                    this.b.n[i].startAnimation(this.b.a(i, 3));
                }
            });
            this.o[i][2] = translateAnimation;
            return translateAnimation;
        } else if (i2 != 3) {
            return null;
        } else {
            if (this.o[i][3] != null) {
                return this.o[i][3];
            }
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 0.3f);
            translateAnimation.setDuration(300);
            translateAnimation.setRepeatMode(2);
            translateAnimation.setRepeatCount(3);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DJIBeginnerStickView b;

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.n[i].go();
                    this.b.m[i].show();
                    this.b.m[i].startAnimation(this.b.a(i, 2));
                }
            });
            this.o[i][3] = translateAnimation;
            return translateAnimation;
        }
    }
}
