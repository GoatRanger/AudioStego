package dji.pilot2.multimoment.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import dji.pilot2.media.e;

public abstract class a {
    private String a;
    private String b;

    protected abstract Bitmap e();

    protected a(String str) {
        this.a = str;
        this.b = null;
    }

    protected a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public Bitmap b() {
        Bitmap g = g();
        if (g != null) {
            return g;
        }
        if (f() != null) {
            return BitmapFactory.decodeFile(f());
        }
        return e();
    }

    public boolean c() {
        return e.getInstance().b(a());
    }

    public boolean d() {
        return f() != null;
    }

    public String f() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    protected Bitmap g() {
        String a = a();
        if (a == null) {
            return null;
        }
        return e.getInstance().a(a);
    }

    public void a(Bitmap bitmap) {
        e.getInstance().a(a(), bitmap);
    }

    public boolean a(a aVar) {
        if (this.a == null || aVar.a() == null) {
            return false;
        }
        return this.a.equals(aVar.a());
    }
}
