package dji.device.widget.popview;

import android.content.Context;
import dji.device.widget.popview.DJIErrorPopViewCompat.b;

public class a {
    private static a a = null;
    private b b = null;
    private b c = null;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
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
