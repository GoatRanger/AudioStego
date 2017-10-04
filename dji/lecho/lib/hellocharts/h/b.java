package lecho.lib.hellocharts.h;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import dji.pilot.visual.a.d;

public abstract class b {
    public static final int a = Color.parseColor("#DFDFDF");
    public static final int b = Color.parseColor("#DDDDDD");
    public static final int c = Color.parseColor("#33B5E5");
    public static final int d = Color.parseColor("#AA66CC");
    public static final int e = Color.parseColor("#99CC00");
    public static final int f = Color.parseColor("#FFBB33");
    public static final int g = Color.parseColor("#FF4444");
    public static final int[] h = new int[]{c, d, e, f, g};
    private static final float i = 1.1f;
    private static final float j = 0.9f;
    private static int k = 0;

    public static final int a() {
        return h[(int) Math.round(Math.random() * ((double) (h.length - 1)))];
    }

    public static final int b() {
        if (k >= h.length) {
            k = 0;
        }
        int[] iArr = h;
        int i = k;
        k = i + 1;
        return iArr[i];
    }

    public static int a(float f, int i) {
        if (i == 0) {
            return 0;
        }
        return (int) ((((float) i) * f) + d.c);
    }

    public static int b(float f, int i) {
        return (int) Math.ceil((double) (((float) i) / f));
    }

    public static int c(float f, int i) {
        if (i == 0) {
            return 0;
        }
        return (int) ((((float) i) * f) + d.c);
    }

    public static int d(float f, int i) {
        return (int) Math.ceil((double) (((float) i) / f));
    }

    public static int a(Context context, int i) {
        return (int) (TypedValue.applyDimension(5, (float) i, context.getResources().getDisplayMetrics()) + d.c);
    }

    public static int a(int i) {
        r0 = new float[3];
        int alpha = Color.alpha(i);
        Color.colorToHSV(i, r0);
        r0[1] = Math.min(r0[1] * i, 1.0f);
        r0[2] = r0[2] * j;
        int HSVToColor = Color.HSVToColor(r0);
        return Color.argb(alpha, Color.red(HSVToColor), Color.green(HSVToColor), Color.blue(HSVToColor));
    }
}
