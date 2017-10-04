package com.handmark.pulltorefresh.library.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import dji.pilot.R;

@SuppressLint({"ViewConstructor"})
public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout {
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final String LOG_TAG = "PullToRefresh-LoadingLayout";
    protected final ImageView mHeaderImage;
    protected final ProgressBar mHeaderProgress;
    private final TextView mHeaderText;
    private FrameLayout mInnerLayout;
    protected final Mode mMode;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    protected final Orientation mScrollDirection;
    private final TextView mSubHeaderText;
    private boolean mUseIntrinsicAnimation;

    protected abstract int getDefaultDrawableResId();

    protected abstract void onLoadingDrawableSet(Drawable drawable);

    protected abstract void onPullImpl(float f);

    protected abstract void pullToRefreshImpl();

    protected abstract void refreshingImpl();

    protected abstract void releaseToRefreshImpl();

    protected abstract void resetImpl();

    public LoadingLayout(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        Drawable drawable;
        ColorStateList colorStateList;
        super(context);
        this.mMode = mode;
        this.mScrollDirection = orientation;
        switch (orientation) {
            case HORIZONTAL:
                LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_horizontal, this);
                break;
            default:
                LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical, this);
                break;
        }
        this.mInnerLayout = (FrameLayout) findViewById(R.id.bjk);
        this.mHeaderText = (TextView) this.mInnerLayout.findViewById(R.id.bjn);
        this.mHeaderProgress = (ProgressBar) this.mInnerLayout.findViewById(R.id.bjm);
        this.mSubHeaderText = (TextView) this.mInnerLayout.findViewById(R.id.bjo);
        this.mHeaderImage = (ImageView) this.mInnerLayout.findViewById(R.id.bjl);
        LayoutParams layoutParams = (LayoutParams) this.mInnerLayout.getLayoutParams();
        switch (mode) {
            case PULL_FROM_END:
                layoutParams.gravity = orientation == Orientation.VERTICAL ? 48 : 3;
                this.mPullLabel = context.getString(R.string.pull_to_refresh_from_bottom_pull_label);
                this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_from_bottom_refreshing_label);
                this.mReleaseLabel = context.getString(R.string.pull_to_refresh_from_bottom_release_label);
                break;
            default:
                layoutParams.gravity = orientation == Orientation.VERTICAL ? 80 : 5;
                this.mPullLabel = context.getString(R.string.pull_to_refresh_pull_label);
                this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_refreshing_label);
                this.mReleaseLabel = context.getString(R.string.pull_to_refresh_release_label);
                break;
        }
        if (typedArray.hasValue(1)) {
            drawable = typedArray.getDrawable(1);
            if (drawable != null) {
                ViewCompat.setBackground(this, drawable);
            }
        }
        if (typedArray.hasValue(10)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(10, typedValue);
            setTextAppearance(typedValue.data);
        }
        if (typedArray.hasValue(11)) {
            typedValue = new TypedValue();
            typedArray.getValue(11, typedValue);
            setSubTextAppearance(typedValue.data);
        }
        if (typedArray.hasValue(2)) {
            colorStateList = typedArray.getColorStateList(2);
            if (colorStateList != null) {
                setTextColor(colorStateList);
            }
        }
        if (typedArray.hasValue(3)) {
            colorStateList = typedArray.getColorStateList(3);
            if (colorStateList != null) {
                setSubTextColor(colorStateList);
            }
        }
        drawable = null;
        if (typedArray.hasValue(6)) {
            drawable = typedArray.getDrawable(6);
        }
        switch (mode) {
            case PULL_FROM_END:
                if (!typedArray.hasValue(8)) {
                    if (typedArray.hasValue(18)) {
                        Utils.warnDeprecation("ptrDrawableBottom", "ptrDrawableEnd");
                        drawable = typedArray.getDrawable(18);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(8);
                break;
                break;
            default:
                if (!typedArray.hasValue(7)) {
                    if (typedArray.hasValue(17)) {
                        Utils.warnDeprecation("ptrDrawableTop", "ptrDrawableStart");
                        drawable = typedArray.getDrawable(17);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(7);
                break;
                break;
        }
        if (drawable == null) {
            drawable = context.getResources().getDrawable(getDefaultDrawableResId());
        }
        setLoadingDrawable(drawable);
        reset();
    }

    public final void setHeight(int i) {
        getLayoutParams().height = i;
        requestLayout();
    }

    public final void setWidth(int i) {
        getLayoutParams().width = i;
        requestLayout();
    }

    public final int getContentSize() {
        switch (this.mScrollDirection) {
            case HORIZONTAL:
                return this.mInnerLayout.getWidth();
            default:
                return this.mInnerLayout.getHeight();
        }
    }

    public final void hideAllViews() {
        if (this.mHeaderText.getVisibility() == 0) {
            this.mHeaderText.setVisibility(4);
        }
        if (this.mHeaderProgress.getVisibility() == 0) {
            this.mHeaderProgress.setVisibility(4);
        }
        if (this.mHeaderImage.getVisibility() == 0) {
            this.mHeaderImage.setVisibility(4);
        }
        if (this.mSubHeaderText.getVisibility() == 0) {
            this.mSubHeaderText.setVisibility(4);
        }
    }

    public final void onPull(float f) {
        if (!this.mUseIntrinsicAnimation) {
            onPullImpl(f);
        }
    }

    public final void pullToRefresh() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mPullLabel);
        }
        pullToRefreshImpl();
    }

    public final void refreshing() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mRefreshingLabel);
        }
        if (this.mUseIntrinsicAnimation) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).start();
        } else {
            refreshingImpl();
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setVisibility(8);
        }
    }

    public final void releaseToRefresh() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mReleaseLabel);
        }
        releaseToRefreshImpl();
    }

    public final void reset() {
        if (this.mHeaderText != null) {
            this.mHeaderText.setText(this.mPullLabel);
        }
        this.mHeaderImage.setVisibility(0);
        if (this.mUseIntrinsicAnimation) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).stop();
        } else {
            resetImpl();
        }
        if (this.mSubHeaderText == null) {
            return;
        }
        if (TextUtils.isEmpty(this.mSubHeaderText.getText())) {
            this.mSubHeaderText.setVisibility(8);
        } else {
            this.mSubHeaderText.setVisibility(0);
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        setSubHeaderText(charSequence);
    }

    public final void setLoadingDrawable(Drawable drawable) {
        this.mHeaderImage.setImageDrawable(drawable);
        this.mUseIntrinsicAnimation = drawable instanceof AnimationDrawable;
        onLoadingDrawableSet(drawable);
    }

    public void setPullLabel(CharSequence charSequence) {
        this.mPullLabel = charSequence;
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        this.mRefreshingLabel = charSequence;
    }

    public void setReleaseLabel(CharSequence charSequence) {
        this.mReleaseLabel = charSequence;
    }

    public void setTextTypeface(Typeface typeface) {
        this.mHeaderText.setTypeface(typeface);
    }

    public final void showInvisibleViews() {
        if (4 == this.mHeaderText.getVisibility()) {
            this.mHeaderText.setVisibility(0);
        }
        if (4 == this.mHeaderProgress.getVisibility()) {
            this.mHeaderProgress.setVisibility(0);
        }
        if (4 == this.mHeaderImage.getVisibility()) {
            this.mHeaderImage.setVisibility(0);
        }
        if (4 == this.mSubHeaderText.getVisibility()) {
            this.mSubHeaderText.setVisibility(0);
        }
    }

    private void setSubHeaderText(CharSequence charSequence) {
        if (this.mSubHeaderText == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.mSubHeaderText.setVisibility(8);
            return;
        }
        this.mSubHeaderText.setText(charSequence);
        if (8 == this.mSubHeaderText.getVisibility()) {
            this.mSubHeaderText.setVisibility(0);
        }
    }

    private void setSubTextAppearance(int i) {
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextAppearance(getContext(), i);
        }
    }

    private void setSubTextColor(ColorStateList colorStateList) {
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextColor(colorStateList);
        }
    }

    private void setTextAppearance(int i) {
        if (this.mHeaderText != null) {
            this.mHeaderText.setTextAppearance(getContext(), i);
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextAppearance(getContext(), i);
        }
    }

    private void setTextColor(ColorStateList colorStateList) {
        if (this.mHeaderText != null) {
            this.mHeaderText.setTextColor(colorStateList);
        }
        if (this.mSubHeaderText != null) {
            this.mSubHeaderText.setTextColor(colorStateList);
        }
    }
}
