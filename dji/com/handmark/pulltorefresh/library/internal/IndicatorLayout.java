package com.handmark.pulltorefresh.library.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.pilot.visual.a.d;

@SuppressLint({"ViewConstructor"})
public class IndicatorLayout extends FrameLayout implements AnimationListener {
    static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;
    private ImageView mArrowImageView;
    private Animation mInAnim;
    private Animation mOutAnim;
    private final Animation mResetRotateAnimation;
    private final Animation mRotateAnimation;

    public IndicatorLayout(Context context, Mode mode) {
        int i;
        super(context);
        this.mArrowImageView = new ImageView(context);
        Drawable drawable = getResources().getDrawable(R.drawable.indicator_arrow);
        this.mArrowImageView.setImageDrawable(drawable);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.a02);
        this.mArrowImageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        addView(this.mArrowImageView);
        switch (mode) {
            case PULL_FROM_END:
                i = R.anim.bg;
                dimensionPixelSize = R.anim.bn;
                setBackgroundResource(R.drawable.indicator_bg_bottom);
                this.mArrowImageView.setScaleType(ScaleType.MATRIX);
                Matrix matrix = new Matrix();
                matrix.setRotate(180.0f, ((float) drawable.getIntrinsicWidth()) / 2.0f, ((float) drawable.getIntrinsicHeight()) / 2.0f);
                this.mArrowImageView.setImageMatrix(matrix);
                break;
            default:
                i = R.anim.bh;
                dimensionPixelSize = R.anim.bo;
                setBackgroundResource(R.drawable.indicator_bg_top);
                break;
        }
        this.mInAnim = AnimationUtils.loadAnimation(context, i);
        this.mInAnim.setAnimationListener(this);
        this.mOutAnim = AnimationUtils.loadAnimation(context, dimensionPixelSize);
        this.mOutAnim.setAnimationListener(this);
        Interpolator linearInterpolator = new LinearInterpolator();
        this.mRotateAnimation = new RotateAnimation(0.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle, 1, d.c, 1, d.c);
        this.mRotateAnimation.setInterpolator(linearInterpolator);
        this.mRotateAnimation.setDuration(150);
        this.mRotateAnimation.setFillAfter(true);
        this.mResetRotateAnimation = new RotateAnimation(DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle, 0.0f, 1, d.c, 1, d.c);
        this.mResetRotateAnimation.setInterpolator(linearInterpolator);
        this.mResetRotateAnimation.setDuration(150);
        this.mResetRotateAnimation.setFillAfter(true);
    }

    public final boolean isVisible() {
        Animation animation = getAnimation();
        if (animation != null) {
            if (this.mInAnim == animation) {
                return true;
            }
            return false;
        } else if (getVisibility() != 0) {
            return false;
        } else {
            return true;
        }
    }

    public void hide() {
        startAnimation(this.mOutAnim);
    }

    public void show() {
        this.mArrowImageView.clearAnimation();
        startAnimation(this.mInAnim);
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.mOutAnim) {
            this.mArrowImageView.clearAnimation();
            setVisibility(8);
        } else if (animation == this.mInAnim) {
            setVisibility(0);
        }
        clearAnimation();
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        setVisibility(0);
    }

    public void releaseToRefresh() {
        this.mArrowImageView.startAnimation(this.mRotateAnimation);
    }

    public void pullToRefresh() {
        this.mArrowImageView.startAnimation(this.mResetRotateAnimation);
    }
}
