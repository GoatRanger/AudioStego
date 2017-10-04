package dji.thirdparty.afinal.b.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import dji.thirdparty.afinal.b.c.a;

public class e {
    private static final int c = 4;
    private static final int d = 204800;
    private static final f e = new f(4, d);
    private a a;
    private b b;

    public e(a aVar, b bVar) {
        this.a = aVar;
        this.b = bVar;
    }

    public Bitmap a(String str, d dVar) {
        Bitmap b = b(str, dVar);
        if (b != null) {
            return b;
        }
        byte[] a = this.a.a(str);
        if (a == null || a.length <= 0) {
            return b;
        }
        if (dVar == null) {
            return BitmapFactory.decodeByteArray(a, 0, a.length);
        }
        b = c.a(a, 0, a.length, dVar.a(), dVar.b());
        this.b.a(str, a);
        return b;
    }

    public Bitmap b(String str, d dVar) {
        f.a a = e.a();
        Bitmap bitmap = null;
        try {
            if (this.b.a(str, a) && a.c - a.b > 0) {
                bitmap = dVar != null ? c.a(a.a, a.b, a.c, dVar.a(), dVar.b()) : BitmapFactory.decodeByteArray(a.a, a.b, a.c);
            }
            e.a(a);
            return bitmap;
        } catch (Throwable th) {
            e.a(a);
        }
    }
}
