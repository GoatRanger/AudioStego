package dji.pilot.fpv.control;

import android.content.Context;
import dji.pilot.fpv.view.DJIErrorPopView.b;

public class g {
    private static g a = null;
    private b b = null;
    private b c = null;

    public static synchronized g getInstance() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void b(b bVar) {
        this.c = bVar;
    }

    public void a() {
        this.b = null;
    }

    public void b() {
        this.c = null;
    }

    public b c() {
        return this.b;
    }

    public b d() {
        return this.c;
    }

    public synchronized String a(Context context) {
        String str;
        str = "";
        if (this.b != null) {
            if (this.b.c != null) {
                str = this.b.c;
            } else if (this.b.b != 0) {
                str = context.getText(this.b.b).toString();
            }
            if (this.b.e != null) {
                str = str.equals("") ? this.b.e : str + "__" + this.b.e;
            } else if (this.b.d != 0) {
                str = str.equals("") ? context.getText(this.b.d).toString() : str + "__" + context.getText(this.b.d).toString();
            }
        }
        a();
        return str;
    }

    public synchronized String b(Context context) {
        String str;
        str = "";
        if (this.c != null) {
            if (this.c.c != null) {
                str = this.c.c;
            } else if (this.c.b != 0) {
                str = context.getText(this.c.b).toString();
            }
            if (this.c.e != null) {
                str = str.equals("") ? this.c.e : str + "__" + this.c.e;
            } else if (this.c.d != 0) {
                str = str.equals("") ? context.getText(this.c.d).toString() : str + "__" + context.getText(this.c.d).toString();
            }
        }
        b();
        return str;
    }
}
