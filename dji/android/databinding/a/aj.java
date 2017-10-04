package android.databinding.a;

import android.annotation.TargetApi;
import android.databinding.c;
import android.databinding.f;
import android.databinding.g;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnLongClickListener;
import com.android.databinding.library.baseAdapters.R;
import dji.pilot.visual.a.d;

@g(a = {@f(a = View.class, b = "android:backgroundTint", c = "setBackgroundTintList"), @f(a = View.class, b = "android:fadeScrollbars", c = "setScrollbarFadingEnabled"), @f(a = View.class, b = "android:getOutline", c = "setOutlineProvider"), @f(a = View.class, b = "android:nextFocusForward", c = "setNextFocusForwardId"), @f(a = View.class, b = "android:nextFocusLeft", c = "setNextFocusLeftId"), @f(a = View.class, b = "android:nextFocusRight", c = "setNextFocusRightId"), @f(a = View.class, b = "android:nextFocusUp", c = "setNextFocusUpId"), @f(a = View.class, b = "android:nextFocusDown", c = "setNextFocusDownId"), @f(a = View.class, b = "android:requiresFadingEdge", c = "setVerticalFadingEdgeEnabled"), @f(a = View.class, b = "android:scrollbarDefaultDelayBeforeFade", c = "setScrollBarDefaultDelayBeforeFade"), @f(a = View.class, b = "android:scrollbarFadeDuration", c = "setScrollBarFadeDuration"), @f(a = View.class, b = "android:scrollbarSize", c = "setScrollBarSize"), @f(a = View.class, b = "android:scrollbarStyle", c = "setScrollBarStyle"), @f(a = View.class, b = "android:transformPivotX", c = "setPivotX"), @f(a = View.class, b = "android:transformPivotY", c = "setPivotY"), @f(a = View.class, b = "android:onDrag", c = "setOnDragListener"), @f(a = View.class, b = "android:onClick", c = "setOnClickListener"), @f(a = View.class, b = "android:onApplyWindowInsets", c = "setOnApplyWindowInsetsListener"), @f(a = View.class, b = "android:onCreateContextMenu", c = "setOnCreateContextMenuListener"), @f(a = View.class, b = "android:onFocusChange", c = "setOnFocusChangeListener"), @f(a = View.class, b = "android:onGenericMotion", c = "setOnGenericMotionListener"), @f(a = View.class, b = "android:onHover", c = "setOnHoverListener"), @f(a = View.class, b = "android:onKey", c = "setOnKeyListener"), @f(a = View.class, b = "android:onLongClick", c = "setOnLongClickListener"), @f(a = View.class, b = "android:onSystemUiVisibilityChange", c = "setOnSystemUiVisibilityChangeListener"), @f(a = View.class, b = "android:onTouch", c = "setOnTouchListener")})
public class aj {
    public static int a = 0;
    public static int b = 1;
    public static int c = 2;

    @TargetApi(12)
    public interface a {
        void a(View view);
    }

    @TargetApi(12)
    public interface b {
        void a(View view);
    }

    @c(a = {"android:padding"})
    public static void a(View view, float f) {
        int a = a(f);
        view.setPadding(a, a, a, a);
    }

    @c(a = {"android:paddingBottom"})
    public static void b(View view, float f) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), a(f));
    }

    @c(a = {"android:paddingEnd"})
    public static void c(View view, float f) {
        int a = a(f);
        if (VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), a, view.getPaddingBottom());
        } else {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), a, view.getPaddingBottom());
        }
    }

    @c(a = {"android:paddingLeft"})
    public static void d(View view, float f) {
        view.setPadding(a(f), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    @c(a = {"android:paddingRight"})
    public static void e(View view, float f) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), a(f), view.getPaddingBottom());
    }

    @c(a = {"android:paddingStart"})
    public static void f(View view, float f) {
        int a = a(f);
        if (VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(a, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        } else {
            view.setPadding(a, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    @c(a = {"android:paddingTop"})
    public static void g(View view, float f) {
        view.setPadding(view.getPaddingLeft(), a(f), view.getPaddingRight(), view.getPaddingBottom());
    }

    @c(a = {"android:requiresFadingEdge"})
    public static void a(View view, int i) {
        boolean z;
        boolean z2 = true;
        if ((c & i) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((b & i) == 0) {
            z2 = false;
        }
        view.setVerticalFadingEdgeEnabled(z);
        view.setHorizontalFadingEdgeEnabled(z2);
    }

    @c(a = {"android:onClickListener", "android:clickable"})
    public static void a(View view, OnClickListener onClickListener, boolean z) {
        view.setOnClickListener(onClickListener);
        view.setClickable(z);
    }

    @c(a = {"android:onClick", "android:clickable"})
    public static void b(View view, OnClickListener onClickListener, boolean z) {
        view.setOnClickListener(onClickListener);
        view.setClickable(z);
    }

    @c(a = {"android:onLongClickListener", "android:longClickable"})
    public static void a(View view, OnLongClickListener onLongClickListener, boolean z) {
        view.setOnLongClickListener(onLongClickListener);
        view.setLongClickable(z);
    }

    @c(a = {"android:onLongClick", "android:longClickable"})
    public static void b(View view, OnLongClickListener onLongClickListener, boolean z) {
        view.setOnLongClickListener(onLongClickListener);
        view.setLongClickable(z);
    }

    @c(a = {"android:onViewDetachedFromWindow", "android:onViewAttachedToWindow"}, b = false)
    public static void a(View view, final b bVar, final a aVar) {
        if (VERSION.SDK_INT >= 12) {
            OnAttachStateChangeListener onAttachStateChangeListener;
            if (bVar == null && aVar == null) {
                onAttachStateChangeListener = null;
            } else {
                Object anonymousClass1 = new OnAttachStateChangeListener() {
                    public void onViewAttachedToWindow(View view) {
                        if (aVar != null) {
                            aVar.a(view);
                        }
                    }

                    public void onViewDetachedFromWindow(View view) {
                        if (bVar != null) {
                            bVar.a(view);
                        }
                    }
                };
            }
            OnAttachStateChangeListener onAttachStateChangeListener2 = (OnAttachStateChangeListener) r.a(view, onAttachStateChangeListener, R.id.onAttachStateChangeListener);
            if (onAttachStateChangeListener2 != null) {
                view.removeOnAttachStateChangeListener(onAttachStateChangeListener2);
            }
            if (onAttachStateChangeListener != null) {
                view.addOnAttachStateChangeListener(onAttachStateChangeListener);
            }
        }
    }

    @c(a = {"android:onLayoutChange"})
    public static void a(View view, OnLayoutChangeListener onLayoutChangeListener, OnLayoutChangeListener onLayoutChangeListener2) {
        if (VERSION.SDK_INT >= 11) {
            if (onLayoutChangeListener != null) {
                view.removeOnLayoutChangeListener(onLayoutChangeListener);
            }
            if (onLayoutChangeListener2 != null) {
                view.addOnLayoutChangeListener(onLayoutChangeListener2);
            }
        }
    }

    @c(a = {"android:background"})
    public static void a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    private static int a(float f) {
        int i = (int) (d.c + f);
        if (i != 0) {
            return i;
        }
        if (f == 0.0f) {
            return 0;
        }
        if (f > 0.0f) {
            return 1;
        }
        return -1;
    }
}
