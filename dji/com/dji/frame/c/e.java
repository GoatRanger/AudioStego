package com.dji.frame.c;

import android.content.Context;
import dji.pilot.visual.a.d;

public class e {
    public static int a(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + d.c);
    }

    public static int b(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + d.c);
    }

    public static int c(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + d.c);
    }

    public static int d(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().scaledDensity * f) + d.c);
    }
}
