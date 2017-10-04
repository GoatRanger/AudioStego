package dji.publics.DJIUI;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;
import dji.frame.widget.R;
import java.util.HashMap;

public class DJISengmentedGroup extends RadioGroup {
    private OnCheckedChangeListener mCheckedChangeListener;
    private int mCheckedTextColor;
    private Float mCornerRadius;
    private HashMap<Integer, TransitionDrawable> mDrawableMap;
    private int mLastCheckId;
    private LayoutSelector mLayoutSelector;
    private int mMarginDp;
    private int mTintColor;
    private int mUnCheckedTintColor;
    private Resources resources;

    private class LayoutSelector {
        private final int DISABLE_LAYOUT = R.drawable.widget_radio_disable;
        private final int SELECTED_LAYOUT = R.drawable.widget_radio_checked;
        private final int UNSELECTED_LAYOUT = R.drawable.widget_radio_unchecked;
        private int child = -1;
        private int children = -1;
        private float r;
        private final float r1 = TypedValue.applyDimension(1, 0.1f, DJISengmentedGroup.this.getResources().getDisplayMetrics());
        private final float[] rBot;
        private final float[] rDefault;
        private final float[] rLeft;
        private final float[] rMiddle;
        private final float[] rRight;
        private final float[] rTop;
        private float[] radii;

        public LayoutSelector(float f) {
            this.r = f;
            this.rLeft = new float[]{this.r, this.r, this.r1, this.r1, this.r1, this.r1, this.r, this.r};
            this.rRight = new float[]{this.r1, this.r1, this.r, this.r, this.r, this.r, this.r1, this.r1};
            this.rMiddle = new float[]{this.r1, this.r1, this.r1, this.r1, this.r1, this.r1, this.r1, this.r1};
            this.rDefault = new float[]{this.r, this.r, this.r, this.r, this.r, this.r, this.r, this.r};
            this.rTop = new float[]{this.r, this.r, this.r, this.r, this.r1, this.r1, this.r1, this.r1};
            this.rBot = new float[]{this.r1, this.r1, this.r1, this.r1, this.r, this.r, this.r, this.r};
        }

        private int getChildren() {
            return DJISengmentedGroup.this.getChildCount();
        }

        private int getChildIndex(View view) {
            return DJISengmentedGroup.this.indexOfChild(view);
        }

        private void setChildRadii(int i, int i2) {
            if (this.children != i || this.child != i2) {
                this.children = i;
                this.child = i2;
                if (this.children == 1) {
                    this.radii = this.rDefault;
                } else if (this.child == 0) {
                    this.radii = DJISengmentedGroup.this.getOrientation() == 0 ? this.rLeft : this.rTop;
                } else if (this.child == this.children - 1) {
                    this.radii = DJISengmentedGroup.this.getOrientation() == 0 ? this.rRight : this.rBot;
                } else {
                    this.radii = this.rMiddle;
                }
            }
        }

        public int getSelected() {
            return this.SELECTED_LAYOUT;
        }

        public int getUnselected() {
            return this.UNSELECTED_LAYOUT;
        }

        public int getDisable() {
            return this.DISABLE_LAYOUT;
        }

        public float[] getChildRadii(View view) {
            setChildRadii(getChildren(), getChildIndex(view));
            return this.radii;
        }
    }

    public DJISengmentedGroup(Context context) {
        super(context);
        this.mCheckedTextColor = -1;
        this.mLastCheckId = -1;
        this.resources = getResources();
        this.mTintColor = this.resources.getColor(17170443);
        this.mUnCheckedTintColor = this.resources.getColor(17170444);
        this.mMarginDp = (int) getResources().getDimension(R.dimen.dp_1_in_sw320dp);
        this.mCornerRadius = Float.valueOf(getResources().getDimension(R.dimen.dp_5_in_sw320dp));
        this.mLayoutSelector = new LayoutSelector(this.mCornerRadius.floatValue());
    }

    public DJISengmentedGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCheckedTextColor = -1;
        this.mLastCheckId = -1;
        this.resources = getResources();
        this.mTintColor = this.resources.getColor(17170443);
        this.mUnCheckedTintColor = this.resources.getColor(17170444);
        this.mMarginDp = (int) getResources().getDimension(R.dimen.dp_1_in_sw320dp);
        this.mCornerRadius = Float.valueOf(getResources().getDimension(R.dimen.dp_5_in_sw320dp));
        initAttrs(attributeSet);
        this.mLayoutSelector = new LayoutSelector(this.mCornerRadius.floatValue());
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.SegmentedGroup, 0, 0);
        try {
            this.mMarginDp = (int) obtainStyledAttributes.getDimension(R.styleable.SegmentedGroup_sg_border_width, getResources().getDimension(R.dimen.dp_1_in_sw320dp));
            this.mCornerRadius = Float.valueOf(obtainStyledAttributes.getDimension(R.styleable.SegmentedGroup_sg_corner_radius, getResources().getDimension(R.dimen.dp_5_in_sw320dp)));
            this.mTintColor = obtainStyledAttributes.getColor(R.styleable.SegmentedGroup_sg_tint_color, getResources().getColor(17170443));
            this.mCheckedTextColor = obtainStyledAttributes.getColor(R.styleable.SegmentedGroup_sg_checked_text_color, getResources().getColor(17170444));
            this.mUnCheckedTintColor = obtainStyledAttributes.getColor(R.styleable.SegmentedGroup_sg_unchecked_tint_color, getResources().getColor(17170444));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.mLastCheckId = getCheckedRadioButtonId();
            updateBackground();
        }
    }

    public void setTintColor(int i) {
        this.mTintColor = i;
        updateBackground();
    }

    public void setTintColor(int i, int i2) {
        this.mTintColor = i;
        this.mCheckedTextColor = i2;
        updateBackground();
    }

    public void setUnCheckedTintColor(int i, int i2) {
        this.mUnCheckedTintColor = i;
        updateBackground();
    }

    public void updateBackground() {
        this.mDrawableMap = new HashMap();
        int childCount = super.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            updateBackground(childAt);
            if (i != childCount - 1) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(layoutParams.width, layoutParams.height, layoutParams.weight);
                if (getOrientation() == 0) {
                    layoutParams2.setMargins(0, 0, -this.mMarginDp, 0);
                } else {
                    layoutParams2.setMargins(0, 0, 0, -this.mMarginDp);
                }
                childAt.setLayoutParams(layoutParams2);
                i++;
            } else {
                return;
            }
        }
    }

    private void updateBackground(View view) {
        int selected = this.mLayoutSelector.getSelected();
        int unselected = this.mLayoutSelector.getUnselected();
        int disable = this.mLayoutSelector.getDisable();
        r0 = new int[2][];
        r0[0] = new int[]{-16842912};
        r0[1] = new int[]{16842912};
        ((Button) view).setTextColor(new ColorStateList(r0, new int[]{this.mTintColor, this.mCheckedTextColor}));
        Drawable mutate = this.resources.getDrawable(selected).mutate();
        Drawable mutate2 = this.resources.getDrawable(unselected).mutate();
        Drawable mutate3 = this.resources.getDrawable(disable).mutate();
        ((GradientDrawable) mutate).setColor(this.mTintColor);
        ((GradientDrawable) mutate).setStroke(this.mMarginDp, this.mTintColor);
        ((GradientDrawable) mutate2).setStroke(this.mMarginDp, this.mTintColor);
        ((GradientDrawable) mutate2).setColor(this.mUnCheckedTintColor);
        ((GradientDrawable) mutate).setCornerRadii(this.mLayoutSelector.getChildRadii(view));
        ((GradientDrawable) mutate2).setCornerRadii(this.mLayoutSelector.getChildRadii(view));
        ((GradientDrawable) mutate3).setCornerRadii(this.mLayoutSelector.getChildRadii(view));
        GradientDrawable gradientDrawable = (GradientDrawable) this.resources.getDrawable(unselected).mutate();
        gradientDrawable.setStroke(this.mMarginDp, this.mTintColor);
        gradientDrawable.setColor(this.mUnCheckedTintColor);
        gradientDrawable.setCornerRadii(this.mLayoutSelector.getChildRadii(view));
        gradientDrawable.setColor(Color.argb(50, Color.red(this.mTintColor), Color.green(this.mTintColor), Color.blue(this.mTintColor)));
        Drawable layerDrawable = new LayerDrawable(new Drawable[]{mutate2, gradientDrawable});
        mutate = new TransitionDrawable(new Drawable[]{mutate2, mutate});
        if (((RadioButton) view).isChecked()) {
            mutate.reverseTransition(0);
        }
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842912, 16842919}, layerDrawable);
        stateListDrawable.addState(new int[]{-16842910}, mutate3);
        stateListDrawable.addState(StateSet.WILD_CARD, mutate);
        this.mDrawableMap.put(Integer.valueOf(view.getId()), mutate);
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
        super.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ((TransitionDrawable) DJISengmentedGroup.this.mDrawableMap.get(Integer.valueOf(i))).reverseTransition(200);
                if (DJISengmentedGroup.this.mLastCheckId != -1) {
                    TransitionDrawable transitionDrawable = (TransitionDrawable) DJISengmentedGroup.this.mDrawableMap.get(Integer.valueOf(DJISengmentedGroup.this.mLastCheckId));
                    if (transitionDrawable != null) {
                        transitionDrawable.reverseTransition(200);
                    }
                }
                DJISengmentedGroup.this.mLastCheckId = i;
                if (DJISengmentedGroup.this.mCheckedChangeListener != null) {
                    DJISengmentedGroup.this.mCheckedChangeListener.onCheckedChanged(radioGroup, i);
                }
            }
        });
        if (!isEnabled()) {
            if (VERSION.SDK_INT >= 16) {
                view.setBackground(mutate3);
            } else {
                view.setBackgroundDrawable(mutate3);
            }
        }
    }

    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mDrawableMap.remove(Integer.valueOf(view.getId()));
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mCheckedChangeListener = onCheckedChangeListener;
    }

    public void checkButton(int i) {
        RadioButton radioButton = (RadioButton) findViewById(i);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        updateBackground();
    }
}
