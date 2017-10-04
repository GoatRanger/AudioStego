package dji.pilot.visual.radar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIVisionRadarView extends DJIRelativeLayout implements b, dji.pilot.visual.radar.b.b {
    private final b[] q = new b[4];
    private DJITextView r = null;
    private DJITextView s = null;
    private a t = null;
    private OnClickListener u = null;
    private final Animation[] v = new Animation[4];
    private boolean w = true;

    private final class a implements Runnable {
        final /* synthetic */ DJIVisionRadarView a;
        private final int b;

        private a(DJIVisionRadarView dJIVisionRadarView, int i) {
            this.a = dJIVisionRadarView;
            this.b = i;
        }

        public void run() {
            if (this.a.t.a(this.b)) {
                this.a.q[this.b].e.go();
            }
        }
    }

    private static final class b {
        private DJIRelativeLayout a;
        private final DJIImageView[] b;
        private DJITextView c;
        private DJIImageView d;
        private DJILinearLayout e;
        private DJIImageView f;
        private DJIImageView g;
        private DJITextView h;

        private b() {
            this.a = null;
            this.b = new DJIImageView[4];
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
        }
    }

    private final class c implements AnimationListener {
        final /* synthetic */ DJIVisionRadarView a;
        private final int b;

        private c(DJIVisionRadarView dJIVisionRadarView, int i) {
            this.a = dJIVisionRadarView;
            this.b = i;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.a.q[this.b].a.go();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public DJIVisionRadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.t = new a(context, this);
        }
    }

    public void hideByOuter() {
        this.w = false;
        go();
    }

    public void showByOuter() {
        this.w = true;
        if (a(true, 4)) {
            show();
        }
    }

    public void show() {
        if (this.w && getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }

    public void showTypeNormalView(int i, int i2) {
        if (a(true, 4)) {
            show();
        }
        b bVar = this.q[i];
        bVar.e.go();
        bVar.a.clearAnimation();
        bVar.a.show();
    }

    public void showTypeInvalidView(int i, int i2, int i3) {
        if (a(true, 4)) {
            show();
        }
        b bVar = this.q[i];
        for (int i4 = 0; i4 < 4; i4++) {
            bVar.b[i4].setBackgroundResource(0);
        }
        bVar.a.clearAnimation();
        bVar.a.go();
        bVar.e.show();
        bVar.h.setText(i2);
        a(i);
    }

    public void hideTypeView(int i, int i2) {
        b bVar = this.q[i];
        bVar.a.clearAnimation();
        bVar.a.go();
        bVar.e.go();
    }

    public void updateRadarImg(int i, int i2, int i3, int i4) {
        this.q[i].b[i3].setBackgroundResource(i2);
    }

    public void safeToHideView(int i, boolean z, int i2) {
        b bVar = this.q[i];
        bVar.c.go();
        bVar.d.go();
        if (z) {
            bVar.a.clearAnimation();
            bVar.a.go();
            return;
        }
        bVar.a.startAnimation(b(i));
    }

    public void showWarningView(int i, int i2) {
        b bVar = this.q[i];
        bVar.a.clearAnimation();
        bVar.c.show();
        bVar.d.show();
        bVar.a.show();
    }

    public void updateNormalDesc(int i, String str, int i2) {
        this.q[i].c.setText(str);
    }

    private boolean a(boolean z, int i) {
        return this.w && this.t.a(z, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            b();
        }
    }

    private void a() {
        this.u = new OnClickListener(this) {
            final /* synthetic */ DJIVisionRadarView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.d79 == id) {
                    this.a.a(0);
                } else if (R.id.d7h == id) {
                    this.a.a(1);
                } else if (R.id.d7d == id) {
                    this.a.a(2);
                } else if (R.id.d7m == id) {
                    this.a.a(3);
                }
            }
        };
    }

    private void a(int i) {
        if (this.q[i].h.getVisibility() == 0) {
            this.q[i].h.go();
            return;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.q[i2].h.go();
        }
        this.q[i].h.show();
    }

    private void b() {
        int[] iArr = new int[]{R.id.d6f, R.id.d6g, R.id.d6h, R.id.d6i, R.id.d6e, R.id.d6k, R.id.d6j, R.id.d76, R.id.d77, R.id.d79, R.id.d7_, R.id.d6v, R.id.d6w, R.id.d6x, R.id.d6y, R.id.d6s, R.id.d6t, R.id.d6u, R.id.d7f, R.id.d7j, R.id.d7h, R.id.d7g, R.id.d6m, R.id.d6n, R.id.d6o, R.id.d6p, R.id.d6l, R.id.d6r, R.id.d6q, R.id.d7a, R.id.d7b, R.id.d7d, R.id.d7e, R.id.d72, R.id.d73, R.id.d74, R.id.d75, R.id.d6z, R.id.d70, R.id.d71, R.id.d7k, R.id.d7o, R.id.d7m, R.id.d7l};
        int length = iArr.length / 4;
        for (int i = 0; i < 4; i++) {
            b bVar = new b();
            int i2 = i * length;
            int i3 = 0;
            while (i3 < 4) {
                bVar.b[i3] = (DJIImageView) findViewById(iArr[i2 + i3]);
                i3++;
            }
            int i4 = i3 + 1;
            bVar.a = (DJIRelativeLayout) findViewById(iArr[i2 + i3]);
            i3 = i4 + 1;
            bVar.c = (DJITextView) findViewById(iArr[i2 + i4]);
            i4 = i3 + 1;
            bVar.d = (DJIImageView) findViewById(iArr[i2 + i3]);
            i3 = i4 + 1;
            bVar.e = (DJILinearLayout) findViewById(iArr[i2 + i4]);
            i4 = i3 + 1;
            bVar.f = (DJIImageView) findViewById(iArr[i2 + i3]);
            i3 = i4 + 1;
            bVar.g = (DJIImageView) findViewById(iArr[i2 + i4]);
            int i5 = i3 + 1;
            bVar.h = (DJITextView) findViewById(iArr[i2 + i3]);
            bVar.g.setOnClickListener(this.u);
            this.q[i] = bVar;
        }
        this.r = (DJITextView) findViewById(R.id.d7p);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.t.a();
        }
    }

    protected void onDetachedFromWindow() {
        this.t.b();
        super.onDetachedFromWindow();
    }

    private Animation b(int i) {
        if (this.v[i] == null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(1000);
            alphaAnimation.setInterpolator(new AccelerateInterpolator(2.0f));
            alphaAnimation.setAnimationListener(new c(i));
            this.v[i] = alphaAnimation;
        }
        return this.v[i];
    }
}
