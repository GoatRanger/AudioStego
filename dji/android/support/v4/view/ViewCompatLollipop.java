package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewParent;
import android.view.WindowInsets;

class ViewCompatLollipop {
    private static ThreadLocal<Rect> sThreadLocalRect;

    ViewCompatLollipop() {
    }

    public static void setTransitionName(View view, String str) {
        view.setTransitionName(str);
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    public static void requestApplyInsets(View view) {
        view.requestApplyInsets();
    }

    public static void setElevation(View view, float f) {
        view.setElevation(f);
    }

    public static float getElevation(View view) {
        return view.getElevation();
    }

    public static void setTranslationZ(View view, float f) {
        view.setTranslationZ(f);
    }

    public static float getTranslationZ(View view) {
        return view.getTranslationZ();
    }

    public static void setOnApplyWindowInsetsListener(View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        if (onApplyWindowInsetsListener == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    return ((WindowInsetsCompatApi21) onApplyWindowInsetsListener.onApplyWindowInsets(view, new WindowInsetsCompatApi21(windowInsets))).unwrap();
                }
            });
        }
    }

    public static boolean isImportantForAccessibility(View view) {
        return view.isImportantForAccessibility();
    }

    static ColorStateList getBackgroundTintList(View view) {
        return view.getBackgroundTintList();
    }

    static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
        if (VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            Object obj = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? null : 1;
            if (background != null && obj != null) {
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
            }
        }
    }

    static Mode getBackgroundTintMode(View view) {
        return view.getBackgroundTintMode();
    }

    static void setBackgroundTintMode(View view, Mode mode) {
        view.setBackgroundTintMode(mode);
        if (VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            Object obj = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? null : 1;
            if (background != null && obj != null) {
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
            }
        }
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        if (!(windowInsetsCompat instanceof WindowInsetsCompatApi21)) {
            return windowInsetsCompat;
        }
        WindowInsets unwrap = ((WindowInsetsCompatApi21) windowInsetsCompat).unwrap();
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(unwrap);
        if (onApplyWindowInsets != unwrap) {
            return new WindowInsetsCompatApi21(onApplyWindowInsets);
        }
        return windowInsetsCompat;
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        if (!(windowInsetsCompat instanceof WindowInsetsCompatApi21)) {
            return windowInsetsCompat;
        }
        WindowInsets unwrap = ((WindowInsetsCompatApi21) windowInsetsCompat).unwrap();
        WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(unwrap);
        if (dispatchApplyWindowInsets != unwrap) {
            return new WindowInsetsCompatApi21(dispatchApplyWindowInsets);
        }
        return windowInsetsCompat;
    }

    public static void setNestedScrollingEnabled(View view, boolean z) {
        view.setNestedScrollingEnabled(z);
    }

    public static boolean isNestedScrollingEnabled(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static boolean startNestedScroll(View view, int i) {
        return view.startNestedScroll(i);
    }

    public static void stopNestedScroll(View view) {
        view.stopNestedScroll();
    }

    public static boolean hasNestedScrollingParent(View view) {
        return view.hasNestedScrollingParent();
    }

    public static boolean dispatchNestedScroll(View view, int i, int i2, int i3, int i4, int[] iArr) {
        return view.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public static boolean dispatchNestedPreScroll(View view, int i, int i2, int[] iArr, int[] iArr2) {
        return view.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public static boolean dispatchNestedFling(View view, float f, float f2, boolean z) {
        return view.dispatchNestedFling(f, f2, z);
    }

    public static boolean dispatchNestedPreFling(View view, float f, float f2) {
        return view.dispatchNestedPreFling(f, f2);
    }

    public static float getZ(View view) {
        return view.getZ();
    }

    public static void setZ(View view, float f) {
        view.setZ(f);
    }

    static void offsetTopAndBottom(View view, int i) {
        Object obj;
        Rect emptyTempRect = getEmptyTempRect();
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            obj = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) ? 1 : null;
        } else {
            obj = null;
        }
        ViewCompatHC.offsetTopAndBottom(view, i);
        if (obj != null && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(emptyTempRect);
        }
    }

    static void offsetLeftAndRight(View view, int i) {
        Object obj;
        Rect emptyTempRect = getEmptyTempRect();
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            obj = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) ? 1 : null;
        } else {
            obj = null;
        }
        ViewCompatHC.offsetLeftAndRight(view, i);
        if (obj != null && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(emptyTempRect);
        }
    }

    private static Rect getEmptyTempRect() {
        if (sThreadLocalRect == null) {
            sThreadLocalRect = new ThreadLocal();
        }
        Rect rect = (Rect) sThreadLocalRect.get();
        if (rect == null) {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }
}
