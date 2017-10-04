package dji.thirdparty.afinal;

import android.graphics.Bitmap;
import android.view.View;
import dji.thirdparty.afinal.c.d;
import java.lang.ref.WeakReference;

class a$b extends d<Object, Void, Bitmap> {
    final /* synthetic */ a a;
    private Object b;
    private final WeakReference<View> c;
    private final dji.thirdparty.afinal.b.a.d d;

    protected /* synthetic */ void a(Object obj) {
        b((Bitmap) obj);
    }

    protected /* synthetic */ Object b(Object[] objArr) {
        return a(objArr);
    }

    protected /* synthetic */ void b(Object obj) {
        a((Bitmap) obj);
    }

    public a$b(a aVar, View view, dji.thirdparty.afinal.b.a.d dVar) {
        this.a = aVar;
        this.c = new WeakReference(view);
        this.d = dVar;
    }

    protected Bitmap a(Object... objArr) {
        this.b = objArr[0];
        String valueOf = String.valueOf(this.b);
        Bitmap bitmap = null;
        synchronized (a.d(this.a)) {
            while (a.e(this.a) && !e()) {
                try {
                    a.d(this.a).wait();
                } catch (InterruptedException e) {
                }
            }
        }
        if (!(null != null || e() || g() == null || a.f(this.a))) {
            bitmap = a.a(this.a, valueOf, this.d);
        }
        if (bitmap != null) {
            a.g(this.a).a(valueOf, bitmap);
        }
        return bitmap;
    }

    protected void a(Bitmap bitmap) {
        if (!e() && !a.f(this.a)) {
            View g = g();
            if (bitmap != null && g != null) {
                a.h(this.a).b.a(g, bitmap, this.d);
            } else if (bitmap == null && g != null) {
                a.h(this.a).b.a(g, this.d.f());
            }
        }
    }

    protected void b(Bitmap bitmap) {
        super.a((Object) bitmap);
        synchronized (a.d(this.a)) {
            a.d(this.a).notifyAll();
        }
    }

    private View g() {
        View view = (View) this.c.get();
        return this == a.a(view) ? view : null;
    }
}
