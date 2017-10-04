package com.alibaba.sdk.android.util;

import android.content.Context;
import android.util.Log;
import com.alibaba.sdk.android.b.a;

public class ResourceUtils {
    public static final String TAG = "ResourceUtils";

    public static String getString(Context context, String str) {
        return context.getResources().getString(getIdentifier(context, "string", str));
    }

    public static int getRLayout(Context context, String str) {
        return getIdentifier(context, "layout", str);
    }

    public static int getRDrawable(Context context, String str) {
        return getIdentifier(context, "drawable", str);
    }

    public static String getString(String str) {
        return getString(a.a, str);
    }

    public static int getRLayout(String str) {
        return getIdentifier(a.a, "layout", str);
    }

    public static int getRId(Context context, String str) {
        return getIdentifier(context, "id", str);
    }

    public static int getRStyleable(Context context, String str) {
        Object a = a(context, str);
        if (a == null) {
            return 0;
        }
        return ((Integer) a).intValue();
    }

    public static int getRId(String str) {
        return getIdentifier(a.a, "id", str);
    }

    public static int getRDrawable(String str) {
        return getIdentifier(a.a, "drawable", str);
    }

    public static int getIdentifier(String str, String str2) {
        return getIdentifier(a.a, str, str2);
    }

    public static int getIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str2, str, context.getPackageName());
    }

    public static final int[] getRStyleableIntArray(Context context, String str) {
        return (int[]) a(context, str);
    }

    private static Object a(Context context, String str) {
        Object obj = null;
        try {
            obj = Class.forName(context.getPackageName() + ".R$styleable").getField(str).get(null);
        } catch (Throwable th) {
            Log.e(TAG, "", th);
        }
        return obj;
    }
}
