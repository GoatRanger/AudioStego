package dji.thirdparty.afinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import dji.thirdparty.afinal.b.a.b;
import dji.thirdparty.afinal.b.a.d;
import dji.thirdparty.afinal.b.a.e;
import dji.thirdparty.afinal.g.c;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class a {
    private static a j;
    private d a;
    private b b;
    private e c;
    private boolean d = false;
    private boolean e = false;
    private final Object f = new Object();
    private Context g;
    private boolean h = false;
    private ExecutorService i;
    private HashMap<String, d> k = new HashMap();

    private a(Context context) {
        this.g = context;
        this.a = new d(this, context);
        a(c.a(context, "afinalCache").getAbsolutePath());
        a(new dji.thirdparty.afinal.b.b.b());
        a(new dji.thirdparty.afinal.b.c.b());
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (j == null) {
                j = new a(context.getApplicationContext());
            }
            aVar = j;
        }
        return aVar;
    }

    public a a(Bitmap bitmap) {
        this.a.d.a(bitmap);
        return this;
    }

    public a a(int i) {
        this.a.d.a(BitmapFactory.decodeResource(this.g.getResources(), i));
        return this;
    }

    public a b(Bitmap bitmap) {
        this.a.d.b(bitmap);
        return this;
    }

    public a b(int i) {
        this.a.d.b(BitmapFactory.decodeResource(this.g.getResources(), i));
        return this;
    }

    public a c(int i) {
        this.a.d.b(i);
        return this;
    }

    public a d(int i) {
        this.a.d.a(i);
        return this;
    }

    public a a(dji.thirdparty.afinal.b.c.a aVar) {
        this.a.c = aVar;
        return this;
    }

    public a a(dji.thirdparty.afinal.b.b.a aVar) {
        this.a.b = aVar;
        return this;
    }

    public a a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.a = str;
        }
        return this;
    }

    public a e(int i) {
        this.a.f = i;
        return this;
    }

    public a a(float f) {
        this.a.e = f;
        return this;
    }

    public a f(int i) {
        this.a.g = i;
        return this;
    }

    public a g(int i) {
        if (i >= 1) {
            this.a.h = i;
        }
        return this;
    }

    public a a(boolean z) {
        this.a.i = z;
        return this;
    }

    private a g() {
        if (!this.h) {
            dji.thirdparty.afinal.b.a.b.a aVar = new dji.thirdparty.afinal.b.a.b.a(this.a.a);
            if (((double) this.a.e) > 0.05d && ((double) this.a.e) < 0.8d) {
                aVar.a(this.g, this.a.e);
            } else if (this.a.f > 2097152) {
                aVar.a(this.a.f);
            } else {
                aVar.a(this.g, 0.3f);
            }
            if (this.a.g > 5242880) {
                aVar.b(this.a.g);
            }
            aVar.a(this.a.i);
            this.b = new b(aVar);
            this.i = Executors.newFixedThreadPool(this.a.h, new 1(this));
            this.c = new e(this.a.c, this.b);
            this.h = true;
        }
        return this;
    }

    public void a(View view, String str) {
        b(view, str, null);
    }

    public void a(View view, String str, int i, int i2) {
        d dVar = (d) this.k.get(i + "_" + i2);
        if (dVar == null) {
            dVar = h();
            dVar.b(i2);
            dVar.a(i);
            this.k.put(i + "_" + i2, dVar);
        }
        b(view, str, dVar);
    }

    public void a(View view, String str, Bitmap bitmap) {
        d dVar = (d) this.k.get(String.valueOf(bitmap));
        if (dVar == null) {
            dVar = h();
            dVar.a(bitmap);
            this.k.put(String.valueOf(bitmap), dVar);
        }
        b(view, str, dVar);
    }

    public void a(View view, String str, Bitmap bitmap, Bitmap bitmap2) {
        d dVar = (d) this.k.get(String.valueOf(bitmap) + "_" + String.valueOf(bitmap2));
        if (dVar == null) {
            dVar = h();
            dVar.a(bitmap);
            dVar.b(bitmap2);
            this.k.put(String.valueOf(bitmap) + "_" + String.valueOf(bitmap2), dVar);
        }
        b(view, str, dVar);
    }

    public void a(View view, String str, int i, int i2, Bitmap bitmap, Bitmap bitmap2) {
        d dVar = (d) this.k.get(i + "_" + i2 + "_" + String.valueOf(bitmap) + "_" + String.valueOf(bitmap2));
        if (dVar == null) {
            dVar = h();
            dVar.b(i2);
            dVar.a(i);
            dVar.a(bitmap);
            dVar.b(bitmap2);
            this.k.put(i + "_" + i2 + "_" + String.valueOf(bitmap) + "_" + String.valueOf(bitmap2), dVar);
        }
        b(view, str, dVar);
    }

    public void a(View view, String str, d dVar) {
        b(view, str, dVar);
    }

    private void b(View view, String str, d dVar) {
        if (!this.h) {
            g();
        }
        if (!TextUtils.isEmpty(str) && view != null) {
            if (dVar == null) {
                dVar = this.a.d;
            }
            Bitmap bitmap = null;
            if (this.b != null) {
                bitmap = this.b.a(str);
            }
            if (bitmap != null) {
                if (view instanceof ImageView) {
                    ((ImageView) view).setImageBitmap(bitmap);
                } else {
                    view.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            } else if (a((Object) str, view)) {
                b bVar = new b(this, view, dVar);
                Drawable aVar = new a(this.g.getResources(), dVar.e(), bVar);
                if (view instanceof ImageView) {
                    ((ImageView) view).setImageDrawable(aVar);
                } else {
                    view.setBackgroundDrawable(aVar);
                }
                bVar.a(this.i, new Object[]{str});
            }
        }
    }

    private d h() {
        d dVar = new d();
        dVar.a(this.a.d.c());
        dVar.c(this.a.d.d());
        dVar.b(this.a.d.b());
        dVar.a(this.a.d.a());
        dVar.b(this.a.d.f());
        dVar.a(this.a.d.e());
        return dVar;
    }

    private void i() {
        if (this.b != null) {
            this.b.a();
        }
    }

    private void j() {
        if (this.b != null) {
            this.b.b();
        }
    }

    private void h(String str) {
        if (this.b != null) {
            this.b.b(str);
        }
    }

    private void i(String str) {
        if (this.b != null) {
            this.b.c(str);
        }
    }

    private void k() {
        if (this.b != null) {
            this.b.d();
            this.b = null;
            j = null;
        }
    }

    private Bitmap b(String str, d dVar) {
        if (this.c != null) {
            return this.c.a(str, dVar);
        }
        return null;
    }

    public Bitmap b(String str) {
        Bitmap c = c(str);
        if (c == null) {
            return d(str);
        }
        return c;
    }

    public Bitmap c(String str) {
        return this.b.a(str);
    }

    public Bitmap d(String str) {
        return a(str, null);
    }

    public Bitmap a(String str, d dVar) {
        return this.c.b(str, dVar);
    }

    private void d(boolean z) {
        this.d = z;
    }

    public void a() {
        d(false);
    }

    public void b() {
        d(true);
    }

    public void c() {
        l();
    }

    public void d() {
        new c(this, null).d(new Object[]{Integer.valueOf(1)});
    }

    public void e(String str) {
        new c(this, null).d(new Object[]{Integer.valueOf(4), str});
    }

    public void e() {
        if (this.b != null) {
            this.b.c();
        }
    }

    public void f(String str) {
        if (this.b != null) {
            this.b.d(str);
        }
    }

    public void f() {
        new c(this, null).d(new Object[]{Integer.valueOf(3)});
    }

    public void g(String str) {
        new c(this, null).d(new Object[]{Integer.valueOf(5), str});
    }

    private void l() {
        new c(this, null).d(new Object[]{Integer.valueOf(2)});
    }

    public void b(boolean z) {
        this.d = z;
        if (z) {
            c(false);
        }
    }

    public void c(boolean z) {
        synchronized (this.f) {
            this.e = z;
            if (!this.e) {
                this.f.notifyAll();
            }
        }
    }

    private static b b(View view) {
        if (view != null) {
            Drawable drawable;
            if (view instanceof ImageView) {
                drawable = ((ImageView) view).getDrawable();
            } else {
                drawable = view.getBackground();
            }
            if (drawable instanceof a) {
                return ((a) drawable).a();
            }
        }
        return null;
    }

    private static boolean a(Object obj, View view) {
        b b = b(view);
        if (b == null) {
            return true;
        }
        Object a = b.a(b);
        if (a != null && a.equals(obj)) {
            return false;
        }
        b.a(true);
        return true;
    }
}
