package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.fpv.view.DJIStageView.e;
import dji.pilot.publics.e.f;
import dji.pilot.publics.widget.DJIRoundLinearLayout;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;

public abstract class DJIBaseTabStageView extends DJIRoundLinearLayout {
    @c(a = 2131362158)
    protected DJILinearLayout a = null;
    @c(a = 2131362164)
    protected DJIRelativeLayout b = null;
    @c(a = 2131362165)
    protected DJIImageView c = null;
    @c(a = 2131362166)
    protected DJITextView d = null;
    @c(a = 2131362163)
    protected DJIImageView e = null;
    @c(a = 2131362167)
    protected ViewAnimator f = null;
    protected DJIStateImageView[] g = null;
    protected Animation h = null;
    protected Animation i = null;
    protected Animation j = null;
    protected Animation k = null;
    protected Animation l = null;
    protected Animation m = null;
    protected b[] n = null;
    protected Context o = null;
    protected e p = null;
    protected OnClickListener q = null;
    protected int r = -1;
    protected int s = -1;
    protected int t = 0;
    protected int[] u = null;
    protected int[] v = null;
    protected int[] w = null;
    protected boolean x = true;
    private final Runnable y = new a(this, null);

    protected abstract void a(boolean z);

    protected abstract int getRequestedWidth();

    public DJIBaseTabStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = context;
        c();
    }

    protected void a(int[] iArr, int[] iArr2, int[] iArr3, boolean z) {
        this.u = iArr;
        this.v = iArr2;
        this.w = iArr3;
        this.x = z;
        this.g = new DJIStateImageView[iArr3.length];
        this.n = new b[iArr3.length];
    }

    protected void a(int i) {
        a(i, false);
    }

    protected void a(int i, boolean z) {
        if (getVisibility() != 0 || z) {
            setVisibility(0);
            this.s = i;
            a();
            a(true);
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            setVisibility(8);
            b();
            a(false);
        }
    }

    protected int b(int i) {
        int i2 = 0;
        int length = this.g.length;
        int i3 = 0;
        while (i3 < i && i3 < length) {
            if (this.g[i3].getVisibility() != 0) {
                i2++;
            }
            i3++;
        }
        return i2;
    }

    protected void a() {
        if (this.s == -1) {
            if (this.r == -1) {
                b(0, 0);
            } else {
                this.n[this.r].a.dispatchOnStart(false);
            }
        } else if (this.r == this.s) {
            this.n[this.r].a.dispatchOnStart(false);
        } else {
            this.r = -1;
            b(this.s, b(this.s));
        }
        this.s = -1;
    }

    protected void b() {
        this.n[this.r].a.dispatchOnStop(false);
        for (int i = 0; i < this.u.length; i++) {
            this.n[i].a.clearAllStage();
        }
        c(false);
    }

    protected void a(int i, int i2, int i3) {
        if (i == 1) {
            c(false);
            return;
        }
        b(false);
        this.d.setText(i2);
    }

    protected void b(boolean z) {
        if (this.b.getVisibility() == 0) {
            return;
        }
        if (z) {
            postDelayed(new 1(this), 100);
            return;
        }
        this.b.show();
        this.a.hide();
        this.e.hide();
    }

    protected void c(boolean z) {
        this.a.show();
        this.e.show();
        if (z && this.b.getVisibility() == 0) {
            postDelayed(new 2(this), 100);
        } else {
            this.b.go();
        }
    }

    protected void c(int i) {
        int b = i - b(i);
        int measuredWidth = this.e.getMeasuredWidth();
        if (this.x) {
            this.e.animate().translationX((float) (b * measuredWidth)).setDuration((long) this.t).start();
        } else {
            this.e.setTranslationX((float) (b * measuredWidth));
        }
    }

    protected void a(int i, int i2) {
        for (int i3 = 0; i3 < this.g.length; i3++) {
            boolean z;
            DJIStateImageView dJIStateImageView = this.g[i3];
            if (i == i3) {
                z = true;
            } else {
                z = false;
            }
            dJIStateImageView.setSelected(z);
        }
    }

    protected void a(View view) {
    }

    protected void b(int i, int i2) {
        if (i != this.r) {
            int i3 = this.r;
            boolean z = i3 != -1;
            this.r = i;
            if (z) {
                if (i3 > this.r) {
                    this.f.setInAnimation(this.h);
                    this.f.setOutAnimation(this.k);
                } else {
                    this.f.setInAnimation(this.j);
                    this.f.setOutAnimation(this.i);
                }
                this.n[i3].a.stopAllStage();
            } else {
                this.f.setInAnimation(null);
                this.f.setOutAnimation(null);
            }
            if (this.n[i].a.isEmpty()) {
                a(this.n[i].a.createStageView(this.v[i], 0, false).getView());
                d(i);
            } else {
                this.n[i].a.startAllStage();
                a(this.n[i].a.getCurrentStage(), this.n[i].a.getCurrentStageTitleResId(), 0);
            }
            this.f.setDisplayedChild(i);
            a(i, i3);
            removeCallbacks(this.y);
            postDelayed(this.y, 150);
        }
    }

    protected void d(int i) {
        a currentStageView = this.n[i].a.getCurrentStageView();
        currentStageView.dispatchOnStart();
        currentStageView.dispatchOnResume();
    }

    protected void onFinishInflate() {
        int i = 0;
        super.onFinishInflate();
        if (!isInEditMode()) {
            int i2;
            f.a(this);
            int length = this.w.length;
            for (i2 = 0; i2 < length; i2++) {
                this.g[i2] = (DJIStateImageView) findViewById(this.w[i2]);
                this.g[i2].setOnClickListener(this.q);
            }
            i2 = this.u.length;
            while (i < i2) {
                b bVar = new b(null);
                bVar.a = (DJIStageView) findViewById(this.u[i]);
                bVar.a.setOnStageChangeListener(this.p);
                this.n[i] = bVar;
                i++;
            }
            this.c.setOnClickListener(new 3(this));
            LayoutParams layoutParams = this.e.getLayoutParams();
            i = getRequestedWidth() / 3;
            if (i != layoutParams.width) {
                layoutParams.width = i;
                dji.pilot.fpv.camera.more.a.a("width-" + layoutParams.width);
                this.e.setLayoutParams(layoutParams);
            }
        }
    }

    protected void c() {
        if (!isInEditMode()) {
            this.t = 200;
            this.h = AnimationUtils.loadAnimation(this.o, R.anim.bi);
            this.i = AnimationUtils.loadAnimation(this.o, R.anim.bk);
            this.j = AnimationUtils.loadAnimation(this.o, R.anim.bp);
            this.k = AnimationUtils.loadAnimation(this.o, R.anim.bq);
            this.l = AnimationUtils.loadAnimation(this.o, R.anim.bt);
            this.m = AnimationUtils.loadAnimation(this.o, R.anim.bu);
            this.l.setAnimationListener(new 4(this));
            this.m.setAnimationListener(new 5(this));
            this.q = new 6(this);
            this.p = new 7(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
