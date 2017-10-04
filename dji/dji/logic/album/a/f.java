package dji.logic.album.a;

import android.content.Context;
import dji.midware.data.config.P3.ProductType;

public class f {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ProductType.values().length];

        static {
            try {
                a[ProductType.litchiC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ProductType.litchiS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static e a(Context context, ProductType productType, String str) {
        b.getInstance(context).i(str);
        switch (AnonymousClass1.a[productType.ordinal()]) {
            case 1:
                return new h();
            case 2:
                return new h();
            default:
                return new h();
        }
    }
}
