package dji.gs.b;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class a implements AnimationListener {
    private View a;
    private Animation b;
    private View c;
    private Animation d;
    private boolean e = false;

    public a(View view, Animation animation, View view2, Animation animation2) {
        this.a = view;
        this.b = animation;
        this.c = view2;
        this.d = animation2;
    }

    public boolean a() {
        return this.e;
    }

    public void b() {
        this.b.setAnimationListener(this);
        this.a.startAnimation(this.b);
        this.d.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
            }
        });
    }

    public void a(View view) {
        this.c = view;
    }

    public void onAnimationStart(Animation animation) {
        this.e = true;
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(4);
        this.c.setVisibility(0);
        this.c.startAnimation(this.d);
        this.e = false;
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
