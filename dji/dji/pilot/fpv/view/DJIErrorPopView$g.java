package dji.pilot.fpv.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LayoutAnimationController.AnimationParameters;

final class DJIErrorPopView$g extends LayoutAnimationController {
    final /* synthetic */ DJIErrorPopView a;
    private long b = 0;
    private long c = 0;

    public DJIErrorPopView$g(DJIErrorPopView dJIErrorPopView, Animation animation) {
        this.a = dJIErrorPopView;
        super(animation, 0.0f);
        this.c = DJIErrorPopView.b(dJIErrorPopView).getDuration();
    }

    public void setAnimation(Animation animation) {
    }

    public Animation getAnimation() {
        return super.getAnimation();
    }

    public void start() {
        this.b = AnimationUtils.currentAnimationTimeMillis();
    }

    public boolean isDone() {
        return AnimationUtils.currentAnimationTimeMillis() > this.b + this.c;
    }

    protected long getDelayForView(View view) {
        int indexOfChild = this.a.indexOfChild(view);
        if (indexOfChild < DJIErrorPopView.c(this.a)) {
            super.setAnimation(DJIErrorPopView.d(this.a));
        } else if (indexOfChild == DJIErrorPopView.c(this.a)) {
            super.setAnimation(DJIErrorPopView.b(this.a));
        } else if (indexOfChild == DJIErrorPopView.a().length - 1) {
            super.setAnimation(DJIErrorPopView.e(this.a));
            view.setVisibility(4);
            DJIErrorPopView.a(this.a, DJIErrorPopView.f(this.a) == 0 ? 0 : DJIErrorPopView.f(this.a) - 1);
        } else {
            super.setAnimation(DJIErrorPopView.g(this.a));
        }
        if (DJIErrorPopView.f(this.a) - 1 == indexOfChild) {
            DJIErrorPopView.b(this.a, DJIErrorPopView.h(this.a));
        }
        return 0;
    }

    protected int getTransformedIndex(AnimationParameters animationParameters) {
        return super.getTransformedIndex(animationParameters);
    }
}
