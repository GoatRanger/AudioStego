package dji.phone.k;

import android.view.View;
import dji.phone.e.a.c;
import dji.phone.e.b;

public class a {
    private static final String a = "M_EventUtils";

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.c.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.d.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.e.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[c.f.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[c.g.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[c.h.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[c.i.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static void a(View view, b bVar) {
        switch (AnonymousClass1.a[bVar.c.ordinal()]) {
            case 2:
                view.setSelected(true);
                return;
            case 3:
                view.setSelected(false);
                return;
            case 4:
                view.setVisibility(0);
                return;
            case 5:
                view.setVisibility(4);
                return;
            case 6:
                view.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
