package dji.setting.ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import dji.pilot.setting.ui.R;
import dji.thirdparty.a.c;
import java.util.Stack;

public class SettingUIStageView extends FrameLayout {
    private ObjectAnimator a;
    private ObjectAnimator b;
    private ObjectAnimator c;
    private LayoutTransition d;
    private Stack<c> e = new Stack();
    private int f;

    private class a implements AnimatorListener {
        final /* synthetic */ SettingUIStageView a;
        private View b;

        public a(SettingUIStageView settingUIStageView, View view) {
            this.a = settingUIStageView;
            this.b = view;
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            this.b.setVisibility(4);
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    public SettingUIStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui).getResourceId(R.styleable.setting_ui_titleText, 0);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        c.a().a(this);
        b();
        if (getChildCount() > 0) {
            this.e.push(new c(getChildAt(0), this.f));
        }
    }

    private void b() {
        this.a = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.slide_in_right);
        this.b = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.fade_out);
        this.c = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.fade_in);
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.slide_out_right);
        this.b.setStartDelay(100);
        this.d = new LayoutTransition();
        this.d.setAnimator(3, objectAnimator);
        this.d.setDuration(objectAnimator.getDuration());
    }

    public void pushView(c cVar, boolean z) {
        if (this.e.size() > 0) {
            setLayoutTransition(null);
            View b = cVar.b();
            View b2 = ((c) this.e.peek()).b();
            this.e.push(cVar);
            addView(b);
            if (z) {
                this.b.setTarget(b2);
                this.b.addListener(new a(this, b2));
                this.b.start();
                this.a.setTarget(b);
                this.a.setFloatValues(new float[]{(float) getWidth(), 0.0f});
                this.a.start();
                return;
            }
            b2.setAlpha(0.0f);
        }
    }

    public int popView() {
        if (this.e.size() <= 1) {
            return this.e.size();
        }
        c cVar = (c) this.e.pop();
        View b = ((c) this.e.peek()).b();
        View b2 = cVar.b();
        b.setVisibility(0);
        setLayoutTransition(this.d);
        removeView(b2);
        this.c.setTarget(b);
        this.c.start();
        return this.e.size();
    }

    public void onEventMainThread(c cVar) {
        if (cVar.c() == this) {
            pushView(cVar, true);
        }
    }

    public int getTitleId() {
        if (this.e.size() <= 0) {
            return 0;
        }
        return ((c) this.e.peek()).a();
    }
}
