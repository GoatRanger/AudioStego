package android.databinding;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class k {
    private static i a = new i();
    private static j b = null;

    private k() {
    }

    public static void a(j jVar) {
        b = jVar;
    }

    public static j a() {
        return b;
    }

    public static <T extends ab> T a(LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z) {
        return a(layoutInflater, i, viewGroup, z, b);
    }

    public static <T extends ab> T a(LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z, j jVar) {
        int i2 = 0;
        int i3 = (viewGroup == null || !z) ? 0 : 1;
        if (i3 != 0) {
            i2 = viewGroup.getChildCount();
        }
        View inflate = layoutInflater.inflate(i, viewGroup, z);
        if (i3 != 0) {
            return a(jVar, viewGroup, i2, i);
        }
        return a(jVar, inflate, i);
    }

    public static <T extends ab> T a(View view) {
        return a(view, b);
    }

    public static <T extends ab> T a(View view, j jVar) {
        T c = c(view);
        if (c != null) {
            return c;
        }
        Object tag = view.getTag();
        if (tag instanceof String) {
            int a = a.a((String) tag);
            if (a != 0) {
                return a.a(jVar, view, a);
            }
            throw new IllegalArgumentException("View is not a binding layout");
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }

    static <T extends ab> T a(j jVar, View[] viewArr, int i) {
        return a.a(jVar, viewArr, i);
    }

    static <T extends ab> T a(j jVar, View view, int i) {
        return a.a(jVar, view, i);
    }

    public static <T extends ab> T b(View view) {
        while (view != null) {
            T b = ab.b(view);
            if (b != null) {
                return b;
            }
            View view2;
            Object tag = view.getTag();
            if (tag instanceof String) {
                String str = (String) tag;
                if (str.startsWith("layout") && str.endsWith("_0")) {
                    char charAt = str.charAt(6);
                    int indexOf = str.indexOf(47, 7);
                    if (charAt == '/') {
                        tag = indexOf == -1 ? 1 : null;
                    } else if (charAt != '-' || indexOf == -1) {
                        tag = null;
                    } else if (str.indexOf(47, indexOf + 1) == -1) {
                        int i = 1;
                    } else {
                        tag = null;
                    }
                    if (tag != null) {
                        return null;
                    }
                }
            }
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                view2 = (View) parent;
            } else {
                view2 = null;
            }
            view = view2;
        }
        return null;
    }

    public static <T extends ab> T c(View view) {
        return ab.b(view);
    }

    public static <T extends ab> T a(Activity activity, int i) {
        return a(activity, i, b);
    }

    public static <T extends ab> T a(Activity activity, int i, j jVar) {
        activity.setContentView(i);
        return a(jVar, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 0, i);
    }

    public static String a(int i) {
        return a.a(i);
    }

    private static <T extends ab> T a(j jVar, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return a(jVar, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (childCount = 0; childCount < i3; childCount++) {
            viewArr[childCount] = viewGroup.getChildAt(childCount + i);
        }
        return a(jVar, viewArr, i2);
    }
}
