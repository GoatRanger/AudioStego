package dji.pilot2.scan.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.a.c.b;
import com.google.a.l;
import com.google.a.w;

public class a {
    public static Bitmap a(String str) throws w {
        if (str == null || str.equals("")) {
            return null;
        }
        b a = new l().a(str, com.google.a.a.l, 300, 300);
        int f = a.f();
        int g = a.g();
        int[] iArr = new int[(f * g)];
        for (int i = 0; i < g; i++) {
            for (int i2 = 0; i2 < f; i2++) {
                if (a.a(i2, i)) {
                    iArr[(i * f) + i2] = -16777216;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(f, g, Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, f, 0, 0, f, g);
        return createBitmap;
    }
}
