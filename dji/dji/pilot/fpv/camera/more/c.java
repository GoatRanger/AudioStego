package dji.pilot.fpv.camera.more;

import android.content.Context;
import com.dji.frame.c.d;
import com.dji.frame.c.l;
import dji.pilot.publics.objects.g;
import java.io.File;
import java.util.Date;

public class c {
    public static final int a = 10000;
    private static final String b = "xtScreenshots";
    private static final String c = ".jpg";
    private static final String d = "yyyy-MM-dd HH:mm:ss";
    private static final String e = "key_tau_temp_alert";
    private static final String f = "key_tau_temp_alert_threshold";
    private final Context g;
    private boolean h = false;
    private int i = 50;
    private String j = null;

    public enum a {
        TEMPALERT_CHANGED,
        TEMPALERT_THRESHOLD_CHANGED,
        SCREEN_SHOT
    }

    c(Context context) {
        this.g = context;
        this.h = g.b(context, e, this.h);
        this.i = g.b(context, f, this.i);
        this.j = d.a(this.g, b);
        dji.pilot.usercenter.f.c.f(this.j);
    }

    public String a() {
        return this.j + File.separator + l.a(new Date(), d) + ".jpg";
    }

    public boolean b() {
        return this.h;
    }

    public void a(boolean z) {
        if (z != this.h) {
            this.h = z;
            g.a(this.g, e, z);
            dji.thirdparty.a.c.a().e(a.TEMPALERT_CHANGED);
        }
    }

    public int c() {
        return this.i;
    }

    public void a(int i) {
        if (this.i != i) {
            this.i = i;
            g.a(this.g, f, i);
            dji.thirdparty.a.c.a().e(a.TEMPALERT_THRESHOLD_CHANGED);
        }
    }
}
