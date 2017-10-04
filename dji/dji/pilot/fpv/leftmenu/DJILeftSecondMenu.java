package dji.pilot.fpv.leftmenu;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import dji.pilot.R;
import dji.publics.DJIUI.DJILinearLayout;

public abstract class DJILeftSecondMenu extends DJILinearLayout {
    private static final long a = 200;
    protected final Rect c = new Rect();
    protected int d = 0;
    protected boolean e = false;
    protected Drawable f = null;
    protected ValueAnimator g = null;
    protected Context h = null;
    protected AnimatorListener i = null;
    protected AnimatorUpdateListener j = null;
    protected boolean k = false;
    protected int l = 0;

    public DJILeftSecondMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.h = context;
            b();
        }
    }

    private void b() {
        this.f = this.h.getResources().getDrawable(R.drawable.fpv_leftmenu_second_bg);
        this.i = new AnimatorListener(this) {
            final /* synthetic */ DJILeftSecondMenu a;

            {
                this.a = r1;
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (this.a.e) {
                    this.a.k = false;
                } else {
                    this.a.k = false;
                    this.a.c();
                }
                this.a.a(1.0f);
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                this.a.k = true;
            }
        };
        this.j = new AnimatorUpdateListener(this) {
            final /* synthetic */ DJILeftSecondMenu a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.a(valueAnimator.getAnimatedFraction());
            }
        };
        this.g = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.g.setInterpolator(null);
        this.g.setDuration(200);
        this.g.addListener(this.i);
        this.g.addUpdateListener(this.j);
    }

    protected void a() {
        int childCount = getChildCount();
        for (int i = 1; i < childCount; i++) {
            getChildAt(i).setVisibility(0);
        }
    }

    protected void c() {
        for (int childCount = getChildCount() - 1; childCount >= 1; childCount--) {
            getChildAt(childCount).setVisibility(8);
        }
    }

    public void autoHandle() {
        if (this.e) {
            hideMenu(false);
        } else {
            showMenu();
        }
    }

    public boolean isShown() {
        return this.e;
    }

    public void showMenu() {
        if (!this.e) {
            this.e = true;
            a();
            this.d = getMeasuredHeight();
            this.g.cancel();
            this.g.setDuration(200);
            this.g.start();
        }
    }

    public void hideMenu(boolean z) {
        if (z) {
            this.e = false;
            c();
            a(1.0f);
        } else if (this.e) {
            this.e = false;
            this.d = this.l;
            this.g.cancel();
            this.g.start();
        }
    }

    private void a(float f) {
        int measuredHeight;
        if (this.e) {
            measuredHeight = getMeasuredHeight();
            int i = this.l;
            if (this.d < i) {
                this.d = measuredHeight + ((int) (((float) (i - measuredHeight)) * f));
                if (this.d >= i) {
                    this.d = i;
                }
            }
        } else {
            measuredHeight = getMeasuredHeight();
            if (this.d > measuredHeight) {
                this.d = ((int) (((float) (this.l - measuredHeight)) * (1.0f - f))) + measuredHeight;
                if (this.d <= measuredHeight) {
                    this.d = measuredHeight;
                }
            }
        }
        postInvalidate();
    }

    private void a(Canvas canvas) {
        int height = getHeight();
        if (this.d < height) {
            this.d = height;
        }
        int i = this.d;
        if (this.e || i > height) {
            this.f.setBounds(0, 0, i, getHeight());
            this.f.draw(canvas);
        }
        this.c.set(0, 0, this.d, getHeight());
        canvas.clipRect(this.c);
    }

    protected void onDraw(Canvas canvas) {
        a(canvas);
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
