package dji.midware.h;

import android.content.Context;

public class a {
    public static int a(Context context, String str) {
        return context.getResources().getIdentifier(str, "string", context.getPackageName());
    }

    public static String a(Context context, int i) {
        return context.getResources().getString(i);
    }

    public static String b(Context context, String str) {
        return a(context, a(context, str));
    }
}
