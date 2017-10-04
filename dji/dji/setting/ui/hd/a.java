package dji.setting.ui.hd;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.e.d;
import dji.thirdparty.a.c;

public class a {
    private static long a = 0;
    private static long b = 0;

    public static boolean a() {
        if ((i.getInstance().a() == ProductType.Grape2 || i.getInstance().c() == ProductType.A2 || i.getInstance().c() == ProductType.Orange2 || i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3) && !dji.midware.c.a.a.a()) {
            return true;
        }
        return false;
    }

    public static boolean b() {
        if (i.getInstance().a() == ProductType.Grape2 && dji.midware.c.a.a.a()) {
            return true;
        }
        return false;
    }

    public static boolean c() {
        return b() || a();
    }

    public static boolean d() {
        if (dji.pilot.publics.e.a.c(null)) {
            return true;
        }
        return false;
    }

    public static boolean e() {
        ProductType c = i.getInstance().c();
        if (c == ProductType.Pomato || c == ProductType.Orange2) {
            return true;
        }
        return false;
    }

    public static void f() {
        if (System.currentTimeMillis() - a > 1000) {
            h();
            a = System.currentTimeMillis();
        }
    }

    public static void g() {
        dji.publics.a.a().postDelayed(new Runnable() {
            public void run() {
                a.f();
            }
        }, 1000);
    }

    private static void h() {
        DataDm368GetGParams.getInstance().setType(c());
        DataDm368GetGParams.getInstance().start(new d() {
            public void onSuccess(Object obj) {
                c.a().e(new a());
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                c.a().e(new a());
                DJILogHelper.getInstance().LOGD("", "DataDm368GetGParams=" + aVar, false, true);
            }
        });
    }
}
