package android.databinding;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

public class l {
    public static int a(View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            return view.getContext().getColor(i);
        }
        return view.getResources().getColor(i);
    }

    public static ColorStateList b(View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            return view.getContext().getColorStateList(i);
        }
        return view.getResources().getColorStateList(i);
    }

    public static Drawable c(View view, int i) {
        if (VERSION.SDK_INT >= 21) {
            return view.getContext().getDrawable(i);
        }
        return view.getResources().getDrawable(i);
    }

    public static boolean a(String str, boolean z) {
        return str == null ? z : Boolean.parseBoolean(str);
    }

    public static byte a(String str, byte b) {
        try {
            b = Byte.parseByte(str);
        } catch (NumberFormatException e) {
        }
        return b;
    }

    public static short a(String str, short s) {
        try {
            s = Short.parseShort(str);
        } catch (NumberFormatException e) {
        }
        return s;
    }

    public static int a(String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return i;
    }

    public static long a(String str, long j) {
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException e) {
        }
        return j;
    }

    public static float a(String str, float f) {
        try {
            f = Float.parseFloat(str);
        } catch (NumberFormatException e) {
        }
        return f;
    }

    public static double a(String str, double d) {
        try {
            d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
        }
        return d;
    }

    public static char a(String str, char c) {
        return (str == null || str.isEmpty()) ? c : str.charAt(0);
    }
}
