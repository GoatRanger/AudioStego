package com.facebook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.facebook.a.b;
import com.facebook.internal.o;

public abstract class FacebookButtonBase extends Button {
    private String analyticsButtonCreatedEventName;
    private String analyticsButtonTappedEventName;
    private OnClickListener externalOnClickListener;
    private OnClickListener internalOnClickListener;
    private boolean overrideCompoundPadding;
    private int overrideCompoundPaddingLeft;
    private int overrideCompoundPaddingRight;
    private o parentFragment;

    protected abstract int getDefaultRequestCode();

    protected FacebookButtonBase(Context context, AttributeSet attributeSet, int i, int i2, String str, String str2) {
        super(context, attributeSet, 0);
        int defaultStyleResource = i2 == 0 ? getDefaultStyleResource() : i2;
        if (defaultStyleResource == 0) {
            defaultStyleResource = R.style.com_facebook_button;
        }
        configureButton(context, attributeSet, i, defaultStyleResource);
        this.analyticsButtonCreatedEventName = str;
        this.analyticsButtonTappedEventName = str2;
        setClickable(true);
        setFocusable(true);
    }

    public void setFragment(Fragment fragment) {
        this.parentFragment = new o(fragment);
    }

    public Fragment getFragment() {
        return this.parentFragment != null ? this.parentFragment.b() : null;
    }

    public Fragment getNativeFragment() {
        return this.parentFragment != null ? this.parentFragment.a() : null;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.externalOnClickListener = onClickListener;
    }

    public int getRequestCode() {
        return getDefaultRequestCode();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            logButtonCreated(getContext());
        }
    }

    protected void onDraw(Canvas canvas) {
        if ((getGravity() & 1) != 0) {
            int compoundPaddingLeft = getCompoundPaddingLeft();
            int compoundPaddingRight = getCompoundPaddingRight();
            int min = Math.min((((getWidth() - (getCompoundDrawablePadding() + compoundPaddingLeft)) - compoundPaddingRight) - measureTextWidth(getText().toString())) / 2, (compoundPaddingLeft - getPaddingLeft()) / 2);
            this.overrideCompoundPaddingLeft = compoundPaddingLeft - min;
            this.overrideCompoundPaddingRight = compoundPaddingRight + min;
            this.overrideCompoundPadding = true;
        }
        super.onDraw(canvas);
        this.overrideCompoundPadding = false;
    }

    public int getCompoundPaddingLeft() {
        return this.overrideCompoundPadding ? this.overrideCompoundPaddingLeft : super.getCompoundPaddingLeft();
    }

    public int getCompoundPaddingRight() {
        return this.overrideCompoundPadding ? this.overrideCompoundPaddingRight : super.getCompoundPaddingRight();
    }

    protected Activity getActivity() {
        Context context = getContext();
        while (!(context instanceof Activity) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        throw new k("Unable to get Activity.");
    }

    protected int getDefaultStyleResource() {
        return 0;
    }

    protected int measureTextWidth(String str) {
        return (int) Math.ceil((double) getPaint().measureText(str));
    }

    protected void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        parseBackgroundAttributes(context, attributeSet, i, i2);
        parseCompoundDrawableAttributes(context, attributeSet, i, i2);
        parseContentAttributes(context, attributeSet, i, i2);
        parseTextAttributes(context, attributeSet, i, i2);
        setupOnClickListener();
    }

    protected void callExternalOnClickListener(View view) {
        if (this.externalOnClickListener != null) {
            this.externalOnClickListener.onClick(view);
        }
    }

    protected void setInternalOnClickListener(OnClickListener onClickListener) {
        this.internalOnClickListener = onClickListener;
    }

    private void logButtonCreated(Context context) {
        b.c(context).a(this.analyticsButtonCreatedEventName, null, null);
    }

    private void logButtonTapped(Context context) {
        b.c(context).a(this.analyticsButtonTappedEventName, null, null);
    }

    private void parseBackgroundAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842964}, i, i2);
            try {
                if (obtainStyledAttributes.hasValue(0)) {
                    int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        setBackgroundResource(resourceId);
                    } else {
                        setBackgroundColor(obtainStyledAttributes.getColor(0, 0));
                    }
                } else {
                    setBackgroundColor(context.getResources().getColor(R.color.com_facebook_blue));
                }
                obtainStyledAttributes.recycle();
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
            }
        }
    }

    @SuppressLint({"ResourceType"})
    private void parseCompoundDrawableAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16843119, 16843117, 16843120, 16843118, 16843121}, i, i2);
        try {
            setCompoundDrawablesWithIntrinsicBounds(obtainStyledAttributes.getResourceId(0, 0), obtainStyledAttributes.getResourceId(1, 0), obtainStyledAttributes.getResourceId(2, 0), obtainStyledAttributes.getResourceId(3, 0));
            setCompoundDrawablePadding(obtainStyledAttributes.getDimensionPixelSize(4, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void parseContentAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842966, 16842967, 16842968, 16842969}, i, i2);
        try {
            setPadding(obtainStyledAttributes.getDimensionPixelSize(0, 0), obtainStyledAttributes.getDimensionPixelSize(1, 0), obtainStyledAttributes.getDimensionPixelSize(2, 0), obtainStyledAttributes.getDimensionPixelSize(3, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void parseTextAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842904}, i, i2);
        try {
            setTextColor(obtainStyledAttributes.getColorStateList(0));
            obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842927}, i, i2);
            try {
                setGravity(obtainStyledAttributes.getInt(0, 17));
                obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{16842901, 16842903, 16843087}, i, i2);
                try {
                    setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(0, 0));
                    setTypeface(Typeface.defaultFromStyle(obtainStyledAttributes.getInt(1, 1)));
                    setText(obtainStyledAttributes.getString(2));
                } finally {
                    obtainStyledAttributes.recycle();
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void setupOnClickListener() {
        super.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FacebookButtonBase a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.logButtonTapped(this.a.getContext());
                if (this.a.internalOnClickListener != null) {
                    this.a.internalOnClickListener.onClick(view);
                } else if (this.a.externalOnClickListener != null) {
                    this.a.externalOnClickListener.onClick(view);
                }
            }
        });
    }
}
