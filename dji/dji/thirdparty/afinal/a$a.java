package dji.thirdparty.afinal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.lang.ref.WeakReference;

class a$a extends BitmapDrawable {
    private final WeakReference<a$b> a;

    public a$a(Resources resources, Bitmap bitmap, a$b dji_thirdparty_afinal_a_b) {
        super(resources, bitmap);
        this.a = new WeakReference(dji_thirdparty_afinal_a_b);
    }

    public a$b a() {
        return (a$b) this.a.get();
    }
}
