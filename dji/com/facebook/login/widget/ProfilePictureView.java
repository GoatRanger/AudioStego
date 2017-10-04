package com.facebook.login.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.R;
import com.facebook.internal.ah;
import com.facebook.internal.q;
import com.facebook.internal.r;
import com.facebook.internal.r.b;
import com.facebook.internal.s;
import com.facebook.internal.x;
import com.facebook.k;
import com.facebook.y;

public class ProfilePictureView extends FrameLayout {
    public static final String a = ProfilePictureView.class.getSimpleName();
    public static final int b = -1;
    public static final int c = -2;
    public static final int d = -3;
    public static final int e = -4;
    private static final int f = 1;
    private static final boolean g = true;
    private static final String h = "ProfilePictureView_superState";
    private static final String i = "ProfilePictureView_profileId";
    private static final String j = "ProfilePictureView_presetSize";
    private static final String k = "ProfilePictureView_isCropped";
    private static final String l = "ProfilePictureView_bitmap";
    private static final String m = "ProfilePictureView_width";
    private static final String n = "ProfilePictureView_height";
    private static final String o = "ProfilePictureView_refresh";
    private String p;
    private int q = 0;
    private int r = 0;
    private boolean s = true;
    private Bitmap t;
    private ImageView u;
    private int v = -1;
    private r w;
    private a x;
    private Bitmap y = null;

    public interface a {
        void a(k kVar);
    }

    public ProfilePictureView(Context context) {
        super(context);
        a(context);
    }

    public ProfilePictureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        a(attributeSet);
    }

    public ProfilePictureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        a(attributeSet);
    }

    public final int getPresetSize() {
        return this.v;
    }

    public final void setPresetSize(int i) {
        switch (i) {
            case -4:
            case -3:
            case -2:
            case -1:
                this.v = i;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Must use a predefined preset size");
        }
    }

    public final boolean isCropped() {
        return this.s;
    }

    public final void setCropped(boolean z) {
        this.s = z;
        a(false);
    }

    public final String getProfileId() {
        return this.p;
    }

    public final void setProfileId(String str) {
        boolean z = false;
        if (ah.a(this.p) || !this.p.equalsIgnoreCase(str)) {
            a();
            z = true;
        }
        this.p = str;
        a(z);
    }

    public final a getOnErrorListener() {
        return this.x;
    }

    public final void setOnErrorListener(a aVar) {
        this.x = aVar;
    }

    public final void setDefaultProfilePicture(Bitmap bitmap) {
        this.y = bitmap;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        boolean z = true;
        LayoutParams layoutParams = getLayoutParams();
        boolean z2 = false;
        int size = MeasureSpec.getSize(i2);
        int size2 = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i2) != 1073741824 && layoutParams.height == -2) {
            size = c(true);
            i2 = MeasureSpec.makeMeasureSpec(size, 1073741824);
            z2 = true;
        }
        if (MeasureSpec.getMode(i) == 1073741824 || layoutParams.width != -2) {
            z = z2;
            i3 = size2;
        } else {
            i3 = c(true);
            i = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        if (z) {
            setMeasuredDimension(i3, size);
            measureChildren(i, i2);
            return;
        }
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(false);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Parcelable bundle = new Bundle();
        bundle.putParcelable(h, onSaveInstanceState);
        bundle.putString(i, this.p);
        bundle.putInt(j, this.v);
        bundle.putBoolean(k, this.s);
        bundle.putParcelable(l, this.t);
        bundle.putInt(m, this.r);
        bundle.putInt(n, this.q);
        bundle.putBoolean(o, this.w != null);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable.getClass() != Bundle.class) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable(h));
        this.p = bundle.getString(i);
        this.v = bundle.getInt(j);
        this.s = bundle.getBoolean(k);
        this.r = bundle.getInt(m);
        this.q = bundle.getInt(n);
        setImageBitmap((Bitmap) bundle.getParcelable(l));
        if (bundle.getBoolean(o)) {
            a(true);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.w = null;
    }

    private void a(Context context) {
        removeAllViews();
        this.u = new ImageView(context);
        this.u.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.u.setScaleType(ScaleType.CENTER_INSIDE);
        addView(this.u);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.com_facebook_profile_picture_view);
        setPresetSize(obtainStyledAttributes.getInt(R.styleable.com_facebook_profile_picture_view_com_facebook_preset_size, -1));
        this.s = obtainStyledAttributes.getBoolean(R.styleable.com_facebook_profile_picture_view_com_facebook_is_cropped, true);
        obtainStyledAttributes.recycle();
    }

    private void a(boolean z) {
        boolean b = b();
        if (this.p == null || this.p.length() == 0 || (this.r == 0 && this.q == 0)) {
            a();
        } else if (b || z) {
            b(true);
        }
    }

    private void a() {
        if (this.w != null) {
            q.b(this.w);
        }
        if (this.y == null) {
            setImageBitmap(BitmapFactory.decodeResource(getResources(), isCropped() ? R.drawable.com_facebook_profile_picture_blank_square : R.drawable.com_facebook_profile_picture_blank_portrait));
            return;
        }
        b();
        setImageBitmap(Bitmap.createScaledBitmap(this.y, this.r, this.q, false));
    }

    private void setImageBitmap(Bitmap bitmap) {
        if (this.u != null && bitmap != null) {
            this.t = bitmap;
            this.u.setImageBitmap(bitmap);
        }
    }

    private void b(boolean z) {
        r a = new com.facebook.internal.r.a(getContext(), r.a(this.p, this.r, this.q)).a(z).a((Object) this).a(new b(this) {
            final /* synthetic */ ProfilePictureView a;

            {
                this.a = r1;
            }

            public void a(s sVar) {
                this.a.a(sVar);
            }
        }).a();
        if (this.w != null) {
            q.b(this.w);
        }
        this.w = a;
        q.a(a);
    }

    private void a(s sVar) {
        if (sVar.a() == this.w) {
            this.w = null;
            Bitmap c = sVar.c();
            Throwable b = sVar.b();
            if (b != null) {
                a aVar = this.x;
                if (aVar != null) {
                    aVar.a(new k("Error in downloading profile picture for profileId: " + getProfileId(), b));
                } else {
                    x.a(y.REQUESTS, 6, a, b.toString());
                }
            } else if (c != null) {
                setImageBitmap(c);
                if (sVar.d()) {
                    b(false);
                }
            }
        }
    }

    private boolean b() {
        boolean z = false;
        int height = getHeight();
        int width = getWidth();
        if (width >= 1 && height >= 1) {
            int c = c(false);
            if (c != 0) {
                height = c;
            } else {
                c = width;
            }
            if (c <= height) {
                width = isCropped() ? c : 0;
            } else {
                if (isCropped()) {
                    width = height;
                } else {
                    boolean z2 = false;
                }
                c = width;
                width = height;
            }
            if (!(c == this.r && width == this.q)) {
                z = true;
            }
            this.r = c;
            this.q = width;
        }
        return z;
    }

    private int c(boolean z) {
        int i;
        switch (this.v) {
            case -4:
                i = R.dimen.com_facebook_profilepictureview_preset_size_large;
                break;
            case -3:
                i = R.dimen.com_facebook_profilepictureview_preset_size_normal;
                break;
            case -2:
                i = R.dimen.com_facebook_profilepictureview_preset_size_small;
                break;
            case -1:
                if (z) {
                    i = R.dimen.com_facebook_profilepictureview_preset_size_normal;
                    break;
                }
                return 0;
            default:
                return 0;
        }
        return getResources().getDimensionPixelSize(i);
    }
}
