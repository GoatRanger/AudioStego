package dji.publics.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.frame.widget.R;

public class FpvPopWarnView extends RelativeLayout {
    private static FpvPopWarnView l = null;
    boolean a = false;
    ImageView b;
    TextView c;
    LinearLayout d;
    ObjectAnimator e;
    ObjectAnimator f;
    AnimatorSet g;
    AnimatorSet h;
    int i = 1000;
    int j = 150;
    int k = 2000;

    public enum a {
        LENGTH_SHORT,
        LENGTH_LONG
    }

    public static synchronized FpvPopWarnView getInstance(Context context) {
        FpvPopWarnView fpvPopWarnView;
        synchronized (FpvPopWarnView.class) {
            if (l == null) {
                l = new FpvPopWarnView(context);
            }
            fpvPopWarnView = l;
        }
        return fpvPopWarnView;
    }

    private FpvPopWarnView(Context context) {
        super(context);
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fpv_pop_warn_view, null);
        this.d = (LinearLayout) inflate.findViewById(R.id.longan_pop_warning_ly);
        this.b = (ImageView) inflate.findViewById(R.id.longan_pop_warn_iv);
        this.c = (TextView) inflate.findViewById(R.id.longan_pop_warn_tv);
        if (inflate.getParent() != null) {
            ((ViewGroup) inflate.getParent()).removeView(inflate);
        }
        addView(inflate, new LayoutParams(-1, -1));
        ((ViewGroup) ((Activity) getContext()).getWindow().getDecorView().getRootView()).addView(this);
        b();
    }

    private void b() {
        this.e = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f});
        this.e.setInterpolator(new DecelerateInterpolator());
        this.e.setDuration((long) this.i);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", new float[]{0.1f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", new float[]{0.1f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, "alpha", new float[]{10.0f});
        this.g = new AnimatorSet();
        this.g.setDuration((long) this.j);
        this.g.setInterpolator(new DecelerateInterpolator());
        this.g.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ FpvPopWarnView a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                this.a.setVisibility(4);
                this.a.setAlpha(1.0f);
                this.a.setScaleX(1.0f);
                this.a.setScaleY(1.0f);
            }
        });
        this.g.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        this.f = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f});
        this.f.setDuration((long) this.k);
        this.h = new AnimatorSet();
        this.h.playSequentially(new Animator[]{this.e, this.f, this.g});
        this.h.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ FpvPopWarnView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                this.a.setVisibility(0);
                this.a.setAlpha(0.3f);
                this.a.a = true;
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                this.a.a = false;
            }
        });
    }

    public boolean isPlaying() {
        return this.a;
    }

    protected void a(int i, String str) {
        if (this.a) {
            this.h.cancel();
        }
        this.b.setImageResource(i);
        this.c.setText(str);
        if (i == 0) {
            this.b.setVisibility(8);
            this.d.setMinimumWidth(getResources().getDimensionPixelOffset(R.dimen.dp_50_in_sw320dp));
        } else {
            this.b.setVisibility(0);
            this.d.setMinimumWidth(getResources().getDimensionPixelOffset(R.dimen.dp_170_in_sw320dp));
        }
        this.h.start();
    }

    protected void a(int i, int i2) {
        a(i, getContext().getString(i2));
    }

    public void pop(int i, int i2, a aVar) {
        if (aVar == a.LENGTH_SHORT) {
            this.f.setDuration(0);
        } else if (aVar == a.LENGTH_LONG) {
            this.f.setDuration((long) this.k);
        }
        a(i, i2);
    }

    public void pop(int i, String str, a aVar) {
        if (aVar == a.LENGTH_SHORT) {
            this.f.setDuration(0);
        } else if (aVar == a.LENGTH_LONG) {
            this.f.setDuration((long) this.k);
        }
        a(i, str);
    }

    public static void dispatchOnDestroy() {
        l = null;
    }
}
