package dji.phone.g;

import android.content.Context;
import dji.phone.k.b;
import dji.phone.tracking.c;

public class a {
    private static final String a = "DJILPMainEventReceiver";
    private Context b;

    public a(Context context) {
        this.b = context;
        dji.f.a.a(this);
    }

    public void a() {
        dji.f.a.b(this);
        this.b = null;
    }

    public void onEventMainThread(c cVar) {
        b.showShort(cVar.a());
    }
}
