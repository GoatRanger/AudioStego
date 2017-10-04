package dji.device.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.pilot.fpv.R;

public class LonganPopWarnView extends RelativeLayout {
    private static LonganPopWarnView l = null;
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

    public static synchronized LonganPopWarnView getInstance(Context context) {
        LonganPopWarnView longanPopWarnView;
        synchronized (LonganPopWarnView.class) {
            if (l == null) {
                l = new LonganPopWarnView(context);
            }
            longanPopWarnView = l;
        }
        return longanPopWarnView;
    }

    private LonganPopWarnView(Context context) {
        super(context);
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.longan_pop_warn_view, null);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.longan_gimbal_status_center_size);
        this.d = (LinearLayout) inflate.findViewById(R.id.longan_pop_warning_ly);
        this.b = (ImageView) inflate.findViewById(R.id.longan_pop_warn_iv);
        this.c = (TextView) inflate.findViewById(R.id.longan_pop_warn_tv);
        if (inflate.getParent() != null) {
            ((ViewGroup) inflate.getParent()).removeView(inflate);
        }
        addView(inflate, dimensionPixelOffset, dimensionPixelOffset);
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
            final /* synthetic */ LonganPopWarnView a;

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
            final /* synthetic */ LonganPopWarnView a;

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
            this.d.setMinimumWidth(getResources().getDimensionPixelOffset(R.dimen.longan_pop_warn_no_img_min_width));
        } else {
            this.b.setVisibility(0);
            this.d.setMinimumWidth(getResources().getDimensionPixelOffset(R.dimen.longan_pop_warn_min_width));
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
}
