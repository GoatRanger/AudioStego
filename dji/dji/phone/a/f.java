package dji.phone.a;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import java.util.Arrays;

public class f {
    @NonNull
    public static Drawable a(int i) {
        if (VERSION.SDK_INT >= 21) {
            return new RippleDrawable(ColorStateList.valueOf(a(i, 0.2d)), new ColorDrawable(i), b(i));
        }
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(a(i, 0.2d)));
        stateListDrawable.addState(new int[]{16842908}, new ColorDrawable(a(i, 0.4d)));
        stateListDrawable.addState(new int[0], new ColorDrawable(i));
        return stateListDrawable;
    }

    @NonNull
    private static Drawable b(int i) {
        float[] fArr = new float[8];
        Arrays.fill(fArr, 3.0f);
        Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    private static int a(int i, double d) {
        if (d(i, d)) {
            return b(i, d);
        }
        return c(i, d);
    }

    private static int b(int i, double d) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        return Color.argb(Color.alpha(i), g(red, d), g(green, d), g(blue, d));
    }

    private static int c(int i, double d) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        return Color.argb(Color.alpha(i), f(red, d), f(green, d), f(blue, d));
    }

    private static boolean d(int i, double d) {
        return e(Color.red(i), d) && e(Color.green(i), d) && e(Color.blue(i), d);
    }

    private static boolean e(int i, double d) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        if (((double) red) + (((double) red) * d) < 255.0d) {
            if ((((double) green) * d) + ((double) green) < 255.0d && ((double) blue) + (((double) blue) * d) < 255.0d) {
                return true;
            }
        }
        return false;
    }

    private static int f(int i, double d) {
        return (int) Math.max(((double) i) - (((double) i) * d), 0.0d);
    }

    private static int g(int i, double d) {
        return (int) Math.min(((double) i) + (((double) i) * d), 255.0d);
    }
}
