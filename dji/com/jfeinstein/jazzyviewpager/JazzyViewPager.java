package com.jfeinstein.jazzyviewpager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.nineoldandroids.view.ViewHelper;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.frame.widget.R;
import dji.pilot.visual.a.d;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JazzyViewPager extends ViewPager {
    public static final String a = "JazzyViewPager";
    public static int b = -1;
    private static final float h = 0.5f;
    private static final float i = 0.5f;
    private static final float j = 15.0f;
    private static final boolean k = (VERSION.SDK_INT >= 11);
    private boolean c;
    private boolean d;
    private boolean e;
    private b f;
    private HashMap<Integer, Object> g;
    private a l;
    private int m;
    private View n;
    private View o;
    private float p;
    private float q;
    private float r;
    private Matrix s;
    private Camera t;
    private float[] u;

    private enum a {
        IDLE,
        GOING_LEFT,
        GOING_RIGHT
    }

    public enum b {
        Standard,
        Tablet,
        CubeIn,
        CubeOut,
        FlipVertical,
        FlipHorizontal,
        Stack,
        ZoomIn,
        ZoomOut,
        RotateUp,
        RotateDown,
        Accordion
    }

    public JazzyViewPager(Context context) {
        this(context, null);
    }

    public JazzyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = true;
        this.d = false;
        this.e = false;
        this.f = b.Standard;
        this.g = new LinkedHashMap();
        this.s = new Matrix();
        this.t = new Camera();
        this.u = new float[2];
        setClipChildren(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JazzyViewPager);
        setTransitionEffect(b.valueOf(getResources().getStringArray(R.array.jazzy_effects)[obtainStyledAttributes.getInt(R.styleable.JazzyViewPager_style, 0)]));
        setFadeEnabled(obtainStyledAttributes.getBoolean(R.styleable.JazzyViewPager_fadeEnabled, false));
        setOutlineEnabled(obtainStyledAttributes.getBoolean(R.styleable.JazzyViewPager_outlineEnabled, false));
        setOutlineColor(obtainStyledAttributes.getColor(R.styleable.JazzyViewPager_outlineColor, -1));
        switch (this.f) {
            case Stack:
            case ZoomOut:
                setFadeEnabled(true);
                break;
        }
        obtainStyledAttributes.recycle();
    }

    public void setTransitionEffect(b bVar) {
        this.f = bVar;
    }

    public void setPagingEnabled(boolean z) {
        this.c = z;
    }

    public void setFadeEnabled(boolean z) {
        this.d = z;
    }

    public boolean getFadeEnabled() {
        return this.d;
    }

    public void setOutlineEnabled(boolean z) {
        this.e = z;
        a();
    }

    public void setOutlineColor(int i) {
        b = i;
    }

    private void a() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof OutlineContainer)) {
                removeView(childAt);
                super.addView(a(childAt), i);
            }
        }
    }

    private View a(View view) {
        if (!this.e || (view instanceof OutlineContainer)) {
            return view;
        }
        View outlineContainer = new OutlineContainer(getContext());
        outlineContainer.setLayoutParams(generateDefaultLayoutParams());
        view.setLayoutParams(new LayoutParams(-1, -1));
        outlineContainer.addView(view);
        return outlineContainer;
    }

    public void addView(View view) {
        super.addView(a(view));
    }

    public void addView(View view, int i) {
        super.addView(a(view), i);
    }

    public void addView(View view, ViewPager.LayoutParams layoutParams) {
        super.addView(a(view), layoutParams);
    }

    public void addView(View view, int i, int i2) {
        super.addView(a(view), i, i2);
    }

    public void addView(View view, int i, ViewPager.LayoutParams layoutParams) {
        super.addView(a(view), i, layoutParams);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.c ? super.onInterceptTouchEvent(motionEvent) : false;
    }

    private void a(View view, String str) {
        Log.v(a, str + ": ROT (" + ViewHelper.getRotation(view) + ", " + ViewHelper.getRotationX(view) + ", " + ViewHelper.getRotationY(view) + "), TRANS (" + ViewHelper.getTranslationX(view) + ", " + ViewHelper.getTranslationY(view) + "), SCALE (" + ViewHelper.getScaleX(view) + ", " + ViewHelper.getScaleY(view) + "), ALPHA " + ViewHelper.getAlpha(view));
    }

    protected void a(int i, float f) {
        if (this.l != a.IDLE) {
            this.p = (((float) (1.0d - Math.cos(6.283185307179586d * ((double) f)))) / 2.0f) * 30.0f;
            ViewHelper.setRotationY(this, this.l == a.GOING_RIGHT ? this.p : -this.p);
            ViewHelper.setPivotX(this, ((float) getMeasuredWidth()) * d.c);
            ViewHelper.setPivotY(this, ((float) getMeasuredHeight()) * d.c);
        }
    }

    protected void a(View view, View view2, float f) {
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.p = 30.0f * f;
                this.q = a(this.p, view.getMeasuredWidth(), view.getMeasuredHeight());
                ViewHelper.setPivotX(view, (float) (view.getMeasuredWidth() / 2));
                ViewHelper.setPivotY(view, (float) (view.getMeasuredHeight() / 2));
                ViewHelper.setTranslationX(view, this.q);
                ViewHelper.setRotationY(view, this.p);
                a(view, "Left");
            }
            if (view2 != null) {
                a(view2, true);
                this.p = DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinAngle * (1.0f - f);
                this.q = a(this.p, view2.getMeasuredWidth(), view2.getMeasuredHeight());
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setTranslationX(view2, this.q);
                ViewHelper.setRotationY(view2, this.p);
                a(view2, "Right");
            }
        }
    }

    private void a(View view, View view2, float f, boolean z) {
        float f2 = 90.0f;
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.p = (z ? 90.0f : -90.0f) * f;
                ViewHelper.setPivotX(view, (float) view.getMeasuredWidth());
                ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                ViewHelper.setRotationY(view, this.p);
            }
            if (view2 != null) {
                a(view2, true);
                if (!z) {
                    f2 = -90.0f;
                }
                this.p = (-f2) * (1.0f - f);
                ViewHelper.setPivotX(view2, 0.0f);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setRotationY(view2, this.p);
            }
        }
    }

    private void c(View view, View view2, float f) {
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                ViewHelper.setPivotX(view, (float) view.getMeasuredWidth());
                ViewHelper.setPivotY(view, 0.0f);
                ViewHelper.setScaleX(view, 1.0f - f);
            }
            if (view2 != null) {
                a(view2, true);
                ViewHelper.setPivotX(view2, 0.0f);
                ViewHelper.setPivotY(view2, 0.0f);
                ViewHelper.setScaleX(view2, f);
            }
        }
    }

    private void b(View view, View view2, float f, boolean z) {
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.r = z ? ((1.0f - f) * d.c) + d.c : dji.midware.util.a.b.c - ((1.0f - f) * d.c);
                ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                ViewHelper.setScaleX(view, this.r);
                ViewHelper.setScaleY(view, this.r);
            }
            if (view2 != null) {
                a(view2, true);
                this.r = z ? (d.c * f) + d.c : dji.midware.util.a.b.c - (d.c * f);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setScaleX(view2, this.r);
                ViewHelper.setScaleY(view2, this.r);
            }
        }
    }

    private void c(View view, View view2, float f, boolean z) {
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.p = ((float) (z ? 1 : -1)) * (15.0f * f);
                this.q = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.p) * 3.141592653589793d) / 180.0d))));
                ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view, z ? 0.0f : (float) view.getMeasuredHeight());
                ViewHelper.setTranslationY(view, this.q);
                ViewHelper.setRotation(view, this.p);
            }
            if (view2 != null) {
                a(view2, true);
                this.p = ((float) (z ? 1 : -1)) * (DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinVelocity + (15.0f * f));
                this.q = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.p) * 3.141592653589793d) / 180.0d))));
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, z ? 0.0f : (float) view2.getMeasuredHeight());
                ViewHelper.setTranslationY(view2, this.q);
                ViewHelper.setRotation(view2, this.p);
            }
        }
    }

    private void b(View view, View view2, float f, int i) {
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.p = 180.0f * f;
                if (this.p > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.q = (float) i;
                    ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                    ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                    ViewHelper.setTranslationX(view, this.q);
                    ViewHelper.setRotationY(view, this.p);
                }
            }
            if (view2 != null) {
                a(view2, true);
                this.p = DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle * (1.0f - f);
                if (this.p < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.q = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setTranslationX(view2, this.q);
                ViewHelper.setRotationY(view2, this.p);
            }
        }
    }

    private void c(View view, View view2, float f, int i) {
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.p = 180.0f * f;
                if (this.p > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.q = (float) i;
                    ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                    ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                    ViewHelper.setTranslationX(view, this.q);
                    ViewHelper.setRotationX(view, this.p);
                }
            }
            if (view2 != null) {
                a(view2, true);
                this.p = DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle * (1.0f - f);
                if (this.p < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.q = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setTranslationX(view2, this.q);
                ViewHelper.setRotationX(view2, this.p);
            }
        }
    }

    protected void a(View view, View view2, float f, int i) {
        if (this.l != a.IDLE) {
            if (view2 != null) {
                a(view2, true);
                this.r = (d.c * f) + d.c;
                this.q = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setScaleX(view2, this.r);
                ViewHelper.setScaleY(view2, this.r);
                ViewHelper.setTranslationX(view2, this.q);
            }
            if (view != null) {
                view.bringToFront();
            }
        }
    }

    @TargetApi(11)
    private void a(View view, boolean z) {
        if (k) {
            int i = z ? 2 : 0;
            if (i != view.getLayerType()) {
                view.setLayerType(i, null);
            }
        }
    }

    @TargetApi(11)
    private void b() {
        if (k) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt.getLayerType() != 0) {
                    childAt.setLayerType(0, null);
                }
            }
        }
    }

    protected float a(float f, int i, int i2) {
        this.s.reset();
        this.t.save();
        this.t.rotateY(Math.abs(f));
        this.t.getMatrix(this.s);
        this.t.restore();
        this.s.preTranslate(((float) (-i)) * d.c, ((float) (-i2)) * d.c);
        this.s.postTranslate(((float) i) * d.c, ((float) i2) * d.c);
        this.u[0] = (float) i;
        this.u[1] = (float) i2;
        this.s.mapPoints(this.u);
        return (f > 0.0f ? 1.0f : -1.0f) * (((float) i) - this.u[0]);
    }

    protected void b(View view, View view2, float f) {
        if (view != null) {
            ViewHelper.setAlpha(view, 1.0f - f);
        }
        if (view2 != null) {
            ViewHelper.setAlpha(view2, f);
        }
    }

    protected void a(View view, View view2) {
        if (!(view instanceof OutlineContainer)) {
            return;
        }
        if (this.l != a.IDLE) {
            if (view != null) {
                a(view, true);
                ((OutlineContainer) view).setOutlineAlpha(1.0f);
            }
            if (view2 != null) {
                a(view2, true);
                ((OutlineContainer) view2).setOutlineAlpha(1.0f);
                return;
            }
            return;
        }
        if (view != null) {
            ((OutlineContainer) view).start();
        }
        if (view2 != null) {
            ((OutlineContainer) view2).start();
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        float f2;
        if (this.l == a.IDLE && f > 0.0f) {
            this.m = getCurrentItem();
            this.l = i == this.m ? a.GOING_RIGHT : a.GOING_LEFT;
        }
        boolean z;
        if (i == this.m) {
            z = true;
        } else {
            z = false;
        }
        if (this.l == a.GOING_RIGHT && !r0) {
            this.l = a.GOING_LEFT;
        } else if (this.l == a.GOING_LEFT && r0) {
            this.l = a.GOING_RIGHT;
        }
        if (a(f)) {
            f2 = 0.0f;
        } else {
            f2 = f;
        }
        this.n = findViewFromObject(i);
        this.o = findViewFromObject(i + 1);
        if (this.d) {
            b(this.n, this.o, f2);
        }
        if (this.e) {
            a(this.n, this.o);
        }
        switch (this.f) {
            case Stack:
                break;
            case ZoomOut:
                b(this.n, this.o, f2, false);
                break;
            case Tablet:
                a(this.n, this.o, f2);
                break;
            case CubeIn:
                a(this.n, this.o, f2, true);
                break;
            case CubeOut:
                a(this.n, this.o, f2, false);
                break;
            case FlipVertical:
                c(this.n, this.o, f, i2);
                break;
            case FlipHorizontal:
                b(this.n, this.o, f2, i2);
                break;
            case ZoomIn:
                b(this.n, this.o, f2, true);
                break;
            case RotateUp:
                c(this.n, this.o, f2, true);
                break;
            case RotateDown:
                c(this.n, this.o, f2, false);
                break;
            case Accordion:
                c(this.n, this.o, f2);
                break;
        }
        a(this.n, this.o, f2, i2);
        super.onPageScrolled(i, f, i2);
        if (f2 == 0.0f) {
            b();
            this.l = a.IDLE;
        }
    }

    private boolean a(float f) {
        return ((double) Math.abs(f)) < 1.0E-4d;
    }

    public void setObjectForPosition(Object obj, int i) {
        this.g.put(Integer.valueOf(i), obj);
    }

    public View findViewFromObject(int i) {
        Object obj = this.g.get(Integer.valueOf(i));
        if (obj == null) {
            return null;
        }
        PagerAdapter adapter = getAdapter();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (adapter.isViewFromObject(childAt, obj)) {
                return childAt;
            }
        }
        return null;
    }
}
