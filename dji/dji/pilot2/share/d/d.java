package dji.pilot2.share.d;

import android.content.Context;
import dji.pilot2.b;
import dji.pilot2.mine.b.e;

public class d {
    private static d c = null;
    private Context a;
    private e b = null;

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (c == null) {
                c = new d(b.a.a());
            }
            dVar = c;
        }
        return dVar;
    }

    private d(Context context) {
        this.a = context;
        this.b = new e(context);
    }

    public e a() {
        return this.b;
    }

    public String b() {
        return e.getInstance().c();
    }
}
