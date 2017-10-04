package dji.thirdparty.afinal.b.a;

import android.graphics.Bitmap;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class j implements h {
    private final HashMap<String, SoftReference<Bitmap>> a = new HashMap();

    public j(int i) {
    }

    public void a(String str, Bitmap bitmap) {
        this.a.put(str, new SoftReference(bitmap));
    }

    public Bitmap a(String str) {
        SoftReference softReference = (SoftReference) this.a.get(str);
        if (softReference != null) {
            return (Bitmap) softReference.get();
        }
        return null;
    }

    public void a() {
        this.a.clear();
    }

    public void b(String str) {
        this.a.remove(str);
    }
}
