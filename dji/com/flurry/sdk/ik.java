package com.flurry.sdk;

import java.util.Comparator;

public class ik implements Comparator<Runnable> {
    private static final String a = ik.class.getSimpleName();

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((Runnable) obj, (Runnable) obj2);
    }

    public int a(Runnable runnable, Runnable runnable2) {
        int a = a(runnable);
        int a2 = a(runnable2);
        if (a < a2) {
            return -1;
        }
        if (a > a2) {
            return 1;
        }
        return 0;
    }

    private int a(Runnable runnable) {
        if (runnable == null) {
            return Integer.MAX_VALUE;
        }
        if (runnable instanceof il) {
            int j;
            kc kcVar = (kc) ((il) runnable).a();
            if (kcVar != null) {
                j = kcVar.j();
            } else {
                j = Integer.MAX_VALUE;
            }
            return j;
        } else if (runnable instanceof kc) {
            return ((kc) runnable).j();
        } else {
            in.a(6, a, "Unknown runnable class: " + runnable.getClass().getName());
            return Integer.MAX_VALUE;
        }
    }
}
