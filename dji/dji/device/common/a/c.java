package dji.device.common.a;

import android.content.res.Configuration;
import android.view.View;

public class c {
    public static final int a = 60;

    public static int[] a(int i) {
        r0 = new int[3];
        int i2 = i / 60;
        r0[1] = i2 % 60;
        r0[2] = i2 / 60;
        return r0;
    }

    public static boolean a(String str, String str2) {
        boolean z = str == str2;
        if (z || str == null) {
            return z;
        }
        return str.equals(str2);
    }

    public static void a(View view, Configuration configuration, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        if (configuration.orientation == 2) {
            if (-1 == i) {
                view.setLeft((i5 / 2) - (i3 / 2));
            } else {
                view.setLeft(i);
            }
            if (-1 == i2) {
                view.setTop((i6 / 2) - (i4 / 2));
            } else {
                view.setTop(i2);
            }
            if (z) {
                view.setRotation(90.0f);
                return;
            }
            return;
        }
        if (-1 == i) {
            view.setTop((i6 / 2) - (i4 / 2));
        } else {
            view.setTop((i6 - i) - i4);
        }
        if (-1 == i2) {
            view.setLeft((i5 / 2) - (i3 / 2));
        } else {
            view.setLeft(i2);
        }
        if (z) {
            view.setRotation(90.0f);
        }
    }
}
