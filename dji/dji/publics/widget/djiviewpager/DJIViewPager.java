package dji.publics.widget.djiviewpager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.nineoldandroids.view.ViewHelper;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.frame.widget.R;
import dji.pilot.visual.a.d;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DJIViewPager extends ViewPager {
    public static final String a = "DJIViewPager";
    public static int b = -1;
    private static final float h = 0.5f;
    private static final float i = 0.5f;
    private static final float j = 15.0f;
    private static final boolean l = (VERSION.SDK_INT >= 11);
    private boolean c;
    private boolean d;
    private boolean e;
    private b f;
    private HashMap<Integer, Object> g;
    private int k;
    private a m;
    private int n;
    private View o;
    private View p;
    private float q;
    private float r;
    private float s;
    private Matrix t;
    private Camera u;
    private float[] v;

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

    public DJIViewPager(Context context) {
        this(context, null);
    }

    public DJIViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = true;
        this.d = false;
        this.e = false;
        this.f = b.Standard;
        this.g = new LinkedHashMap();
        this.k = 0;
        this.t = new Matrix();
        this.u = new Camera();
        this.v = new float[2];
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r4) {
        /*
        r3 = this;
        r2 = 1;
        r0 = 0;
        r1 = r4.getAction();
        r1 = r1 & 255;
        switch(r1) {
            case 0: goto L_0x0010;
            case 1: goto L_0x0013;
            case 2: goto L_0x0024;
            case 3: goto L_0x000b;
            case 4: goto L_0x000b;
            case 5: goto L_0x0016;
            case 6: goto L_0x001d;
            default: goto L_0x000b;
        };
    L_0x000b:
        r1 = r3.k;
        if (r1 <= r2) goto L_0x0029;
    L_0x000f:
        return r0;
    L_0x0010:
        r3.k = r2;
        goto L_0x000b;
    L_0x0013:
        r3.k = r0;
        goto L_0x000b;
    L_0x0016:
        r1 = r3.k;
        r1 = r1 + 1;
        r3.k = r1;
        goto L_0x000b;
    L_0x001d:
        r1 = r3.k;
        r1 = r1 + -1;
        r3.k = r1;
        goto L_0x000b;
    L_0x0024:
        r1 = r3.k;
        if (r1 <= r2) goto L_0x000b;
    L_0x0028:
        goto L_0x000f;
    L_0x0029:
        r1 = r3.c;	 Catch:{ Exception -> 0x0032 }
        if (r1 == 0) goto L_0x000f;
    L_0x002d:
        r0 = super.onInterceptTouchEvent(r4);	 Catch:{ Exception -> 0x0032 }
        goto L_0x000f;
    L_0x0032:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.publics.widget.djiviewpager.DJIViewPager.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private void a(View view, String str) {
    }

    protected void a(int i, float f) {
        if (this.m != a.IDLE) {
            this.q = (((float) (1.0d - Math.cos(6.283185307179586d * ((double) f)))) / 2.0f) * 30.0f;
            ViewHelper.setRotationY(this, this.m == a.GOING_RIGHT ? this.q : -this.q);
            ViewHelper.setPivotX(this, ((float) getMeasuredWidth()) * d.c);
            ViewHelper.setPivotY(this, ((float) getMeasuredHeight()) * d.c);
        }
    }

    protected void a(View view, View view2, float f) {
        if (this.m != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.q = 30.0f * f;
                this.r = a(this.q, view.getMeasuredWidth(), view.getMeasuredHeight());
                ViewHelper.setPivotX(view, (float) (view.getMeasuredWidth() / 2));
                ViewHelper.setPivotY(view, (float) (view.getMeasuredHeight() / 2));
                ViewHelper.setTranslationX(view, this.r);
                ViewHelper.setRotationY(view, this.q);
                a(view, "Left");
            }
            if (view2 != null) {
                a(view2, true);
                this.q = DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinAngle * (1.0f - f);
                this.r = a(this.q, view2.getMeasuredWidth(), view2.getMeasuredHeight());
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setTranslationX(view2, this.r);
                ViewHelper.setRotationY(view2, this.q);
                a(view2, "Right");
            }
        }
    }

    private void a(View view, View view2, float f, boolean z) {
        float f2 = 90.0f;
        if (this.m != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.q = (z ? 90.0f : -90.0f) * f;
                ViewHelper.setPivotX(view, (float) view.getMeasuredWidth());
                ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                ViewHelper.setRotationY(view, this.q);
            }
            if (view2 != null) {
                a(view2, true);
                if (!z) {
                    f2 = -90.0f;
                }
                this.q = (-f2) * (1.0f - f);
                ViewHelper.setPivotX(view2, 0.0f);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setRotationY(view2, this.q);
            }
        }
    }

    private void c(View view, View view2, float f) {
        if (this.m != a.IDLE) {
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
        if (this.m != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.s = z ? ((1.0f - f) * d.c) + d.c : dji.midware.util.a.b.c - ((1.0f - f) * d.c);
                ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                ViewHelper.setScaleX(view, this.s);
                ViewHelper.setScaleY(view, this.s);
            }
            if (view2 != null) {
                a(view2, true);
                this.s = z ? (d.c * f) + d.c : dji.midware.util.a.b.c - (d.c * f);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setScaleX(view2, this.s);
                ViewHelper.setScaleY(view2, this.s);
            }
        }
    }

    private void c(View view, View view2, float f, boolean z) {
        if (this.m != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.q = ((float) (z ? 1 : -1)) * (15.0f * f);
                this.r = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.q) * 3.141592653589793d) / 180.0d))));
                ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view, z ? 0.0f : (float) view.getMeasuredHeight());
                ViewHelper.setTranslationY(view, this.r);
                ViewHelper.setRotation(view, this.q);
            }
            if (view2 != null) {
                a(view2, true);
                this.q = ((float) (z ? 1 : -1)) * (DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinVelocity + (15.0f * f));
                this.r = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.q) * 3.141592653589793d) / 180.0d))));
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, z ? 0.0f : (float) view2.getMeasuredHeight());
                ViewHelper.setTranslationY(view2, this.r);
                ViewHelper.setRotation(view2, this.q);
            }
        }
    }

    private void b(View view, View view2, float f, int i) {
        if (this.m != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.q = 180.0f * f;
                if (this.q > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.r = (float) i;
                    ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                    ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                    ViewHelper.setTranslationX(view, this.r);
                    ViewHelper.setRotationY(view, this.q);
                }
            }
            if (view2 != null) {
                a(view2, true);
                this.q = DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle * (1.0f - f);
                if (this.q < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.r = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setTranslationX(view2, this.r);
                ViewHelper.setRotationY(view2, this.q);
            }
        }
    }

    private void c(View view, View view2, float f, int i) {
        if (this.m != a.IDLE) {
            if (view != null) {
                a(view, true);
                this.q = 180.0f * f;
                if (this.q > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.r = (float) i;
                    ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * d.c);
                    ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * d.c);
                    ViewHelper.setTranslationX(view, this.r);
                    ViewHelper.setRotationX(view, this.q);
                }
            }
            if (view2 != null) {
                a(view2, true);
                this.q = DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle * (1.0f - f);
                if (this.q < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.r = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * d.c);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * d.c);
                ViewHelper.setTranslationX(view2, this.r);
                ViewHelper.setRotationX(view2, this.q);
            }
        }
    }

    protected void a(View view, View view2, float f, int i) {
        if (this.m != a.IDLE) {
            if (view2 != null) {
                a(view2, true);
                this.s = (d.c * f) + d.c;
                this.r = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setScaleX(view2, this.s);
                ViewHelper.setScaleY(view2, this.s);
                ViewHelper.setTranslationX(view2, this.r);
            }
            if (view != null) {
                view.bringToFront();
            }
        }
    }

    @TargetApi(11)
    private void a(View view, boolean z) {
        if (l) {
            int i = z ? 2 : 0;
            if (i != view.getLayerType()) {
                view.setLayerType(i, null);
            }
        }
    }

    @TargetApi(11)
    private void b() {
        if (l) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt.getLayerType() != 0) {
                    childAt.setLayerType(0, null);
                }
            }
        }
    }

    protected float a(float f, int i, int i2) {
        this.t.reset();
        this.u.save();
        this.u.rotateY(Math.abs(f));
        this.u.getMatrix(this.t);
        this.u.restore();
        this.t.preTranslate(((float) (-i)) * d.c, ((float) (-i2)) * d.c);
        this.t.postTranslate(((float) i) * d.c, ((float) i2) * d.c);
        this.v[0] = (float) i;
        this.v[1] = (float) i2;
        this.t.mapPoints(this.v);
        return (f > 0.0f ? 1.0f : -1.0f) * (((float) i) - this.v[0]);
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
        if (this.m != a.IDLE) {
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
        if (this.m == a.IDLE && f > 0.0f) {
            this.n = getCurrentItem();
            this.m = i == this.n ? a.GOING_RIGHT : a.GOING_LEFT;
        }
        boolean z;
        if (i == this.n) {
            z = true;
        } else {
            z = false;
        }
        if (this.m == a.GOING_RIGHT && !r0) {
            this.m = a.GOING_LEFT;
        } else if (this.m == a.GOING_LEFT && r0) {
            this.m = a.GOING_RIGHT;
        }
        if (a(f)) {
            f2 = 0.0f;
        } else {
            f2 = f;
        }
        this.o = findViewFromObject(i);
        this.p = findViewFromObject(i + 1);
        if (this.d) {
            b(this.o, this.p, f2);
        }
        if (this.e) {
            a(this.o, this.p);
        }
        switch (this.f) {
            case Stack:
                break;
            case ZoomOut:
                b(this.o, this.p, f2, false);
                break;
            case Tablet:
                a(this.o, this.p, f2);
                break;
            case CubeIn:
                a(this.o, this.p, f2, true);
                break;
            case CubeOut:
                a(this.o, this.p, f2, false);
                break;
            case FlipVertical:
                c(this.o, this.p, f, i2);
                break;
            case FlipHorizontal:
                b(this.o, this.p, f2, i2);
                break;
            case ZoomIn:
                b(this.o, this.p, f2, true);
                break;
            case RotateUp:
                c(this.o, this.p, f2, true);
                break;
            case RotateDown:
                c(this.o, this.p, f2, false);
                break;
            case Accordion:
                c(this.o, this.p, f2);
                break;
        }
        a(this.o, this.p, f2, i2);
        super.onPageScrolled(i, f, i2);
        if (f2 == 0.0f) {
            b();
            this.m = a.IDLE;
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
