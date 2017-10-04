package dji.pilot.fpv.control;

import android.content.Context;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.pilot.publics.e.a;
import dji.pilot.publics.objects.g;
import dji.thirdparty.a.c;

public class o {
    public static final String a = "g_config.mvo_cfg.mvo_func_en_0";
    public static final String b = "g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0";
    public static final String c = "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0";
    private static final String d = "key_vpsshow_tip";
    private static final String e = "key_show_question";
    private static volatile boolean f = false;
    private static volatile boolean g = false;
    private static boolean h = false;
    private static boolean i = true;
    private final Context j;

    public static boolean a() {
        return f;
    }

    public static boolean b() {
        return g;
    }

    public static void a(boolean z) {
        a(z, true);
    }

    public static void a(boolean z, boolean z2) {
        if (f != z) {
            f = z;
            if (!z) {
                c.a().e(a.a);
            }
        }
    }

    public static void b(boolean z) {
        b(z, true);
    }

    public static void b(boolean z, boolean z2) {
        if (g != z) {
            g = z;
        }
    }

    public static boolean c() {
        return h;
    }

    public static void a(Context context, boolean z) {
        if (h != z) {
            h = z;
            g.a(context, a.a(d), z);
            if (!z) {
                c.a().e(DataFlycGetPushAvoid.getInstance());
            }
        }
    }

    public static boolean d() {
        return i;
    }

    public static void b(Context context, boolean z) {
        if (i != z) {
            i = z;
            g.a(context, a.a(e), z);
        }
    }

    public void a(ProductType productType) {
        if (a.r(productType)) {
            h = g.b(this.j, a.a(d), false);
            i = g.b(this.j, a.a(e), true);
        }
    }

    public o(Context context) {
        this.j = context;
        a(i.getInstance().c());
    }
}
