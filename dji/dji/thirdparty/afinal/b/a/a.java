package dji.thirdparty.afinal.b.a;

import android.graphics.Bitmap;
import dji.thirdparty.afinal.g.c;

public class a implements h {
    private final i<String, Bitmap> a;

    public a(int i) {
        this.a = new i<String, Bitmap>(this, i) {
            final /* synthetic */ a a;

            protected int a(String str, Bitmap bitmap) {
                return c.a(bitmap);
            }
        };
    }

    public void a(String str, Bitmap bitmap) {
        this.a.b(str, bitmap);
    }

    public Bitmap a(String str) {
        return (Bitmap) this.a.a((Object) str);
    }

    public void a() {
        this.a.a();
    }

    public void b(String str) {
        this.a.b(str);
    }
}
