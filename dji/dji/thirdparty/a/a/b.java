package dji.thirdparty.a.a;

import android.content.res.Resources;
import android.util.Log;
import dji.thirdparty.a.c;

public class b {
    final Resources a;
    final int b;
    final int c;
    final f d;
    c e;
    boolean f = true;
    String g;
    int h;
    Class<?> i;

    public b(Resources resources, int i, int i2) {
        this.a = resources;
        this.b = i;
        this.c = i2;
        this.d = new f();
    }

    public b a(Class<? extends Throwable> cls, int i) {
        this.d.a(cls, i);
        return this;
    }

    public int a(Throwable th) {
        Integer a = this.d.a(th);
        if (a != null) {
            return a.intValue();
        }
        Log.d(c.b, "No specific message ressource ID found for " + th);
        return this.c;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(Class<?> cls) {
        this.i = cls;
    }

    public void a() {
        this.f = false;
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    c b() {
        return this.e != null ? this.e : c.a();
    }
}
