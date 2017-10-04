package dji.device.common.a;

import android.content.Context;
import android.content.res.TypedArray;
import dji.pilot.fpv.R;

public class d {
    private static final String a = "LonganGimbalUtils";
    private static final int b = R.array.longan_gimbal_profiles_array;
    private static final int c = R.array.longan_gimbal_profiles_img_array;
    private static String[] d = null;
    private static int[] e = null;

    public static String a(Context context, int i) {
        c(context);
        return d[i];
    }

    public static int b(Context context, int i) {
        d(context);
        return e[i];
    }

    public static int[] a(Context context) {
        d(context);
        return e;
    }

    private static void c(Context context) {
        if (d == null) {
            d = context.getResources().getStringArray(b);
        }
    }

    private static void d(Context context) {
        if (e == null) {
            TypedArray obtainTypedArray = context.getResources().obtainTypedArray(c);
            int length = obtainTypedArray.length();
            e = new int[length];
            for (int i = 0; i < length; i++) {
                e[i] = obtainTypedArray.getResourceId(i, -1);
            }
            obtainTypedArray.recycle();
        }
    }

    public static String[] b(Context context) {
        c(context);
        return d;
    }
}
