package dji.phone.e;

import dji.phone.e.a.b;
import dji.phone.e.a.c;
import dji.phone.e.a.e;
import dji.phone.f.d;
import java.util.List;

public class a {
    private static final String h = "UIEventControler";
    private static final boolean i = false;
    private static final a j = new a();
    b a = new b(c.d, c.f);
    b b = new b(c.c, c.f);
    b c = new b(c.d, c.g);
    b d = new b(c.e, c.f);
    b e = new b(c.e, c.g);
    b f = new b(c.f, c.g);
    b g = new b(c.g, c.f);

    private a() {
    }

    public static a getInstance() {
        return j;
    }

    public void a() {
        dji.phone.e.a.a.h.a(new b[]{new b(e.BTN_CAMERA_MODE, c.e), new b(e.BTN_SHOTCUT_CAMERA, c.e), new b(e.BTN_SHOTCUT_GIMBAL, c.e), new b(e.BTN_CAMERA_VIDEO_AUTO, c.d)});
        dji.phone.e.a.a.e.a(new b[]{new b(e.BTN_TK_QUIT, c.c), new b(dji.phone.e.a.a.m, c.i)});
        e.BTN_CAMERA_MODE.a(e.VIEW_CAMERA_MODE, this.a, this.e).a(c.d, new b(e.BTN_SHOTCUT_CAMERA, c.e), new b(e.BTN_SHOTCUT_GIMBAL, c.e)).a(c.e, new b(e.BTN_BLE_STATUS, c.f), new b(e.VIEW_UI_SWITCHER, c.f));
        e.BTN_SHOTCUT_CAMERA.a(e.VIEW_SHOTCUT_CAMERA, this.a, this.e).a(c.d, new b(e.BTN_CAMERA_VIDEO_AUTO, c.d), new b(e.BTN_SHOTCUT_GIMBAL, c.e), new b(e.BTN_CAMERA_MODE, c.e));
        e.BTN_SHOTCUT_GIMBAL.a(e.VIEW_SHOTCUT_GIMBAL, this.a, this.e).a(c.d, new b(e.BTN_CAMERA_VIDEO_AUTO, c.d), new b(e.BTN_SHOTCUT_CAMERA, c.e), new b(e.BTN_CAMERA_MODE, c.e));
        e.BTN_CAMERA_VIDEO_TLP.a(e.VIEW_TIMELAPSE_SETER, this.a, this.e).a(c.d, new b(e.BTN_SHOTCUT_GIMBAL, c.e), new b(e.BTN_SHOTCUT_CAMERA, c.e)).a(c.e, new b(e.VIEW_TIMELAPSE_SETER, c.g));
        e.BTN_BLE_STATUS.a(e.VIEW_BLE_DIALOG, this.b);
        e.VIEW_CAMERA_MODE.a(e.BTN_BLE_STATUS, this.f, this.g);
        e.BTN_CAMERA_ID_SWITCHER.a(e.BTN_TK_QUIT, c.c, c.c);
        dji.f.a.a(this);
    }

    public void b() {
        dji.f.a.b(this);
    }

    public void onEventBackgroundThread(b bVar) {
        List<b> a;
        b b;
        a("receive" + bVar.a + "type:" + bVar.c);
        e eVar = bVar.a;
        if (eVar != null) {
            a = eVar.a(bVar.c);
            if (a != null) {
                for (b b2 : a) {
                    a("post:" + b2.a + "type:" + b2.c);
                    dji.thirdparty.a.c.a().e(b2);
                }
            }
            if (bVar.a.a()) {
                b2 = bVar.a.b(bVar.c);
                if (b2 != null) {
                    a("post:" + b2.a + "type:" + b2.c);
                    dji.thirdparty.a.c.a().e(b2);
                }
            }
        }
        dji.phone.e.a.a aVar = bVar.b;
        if (aVar != null) {
            a = aVar.a(bVar.c);
            if (a != null) {
                for (b b22 : a) {
                    a("post:" + b22.a + "type:" + b22.c);
                    dji.thirdparty.a.c.a().e(b22);
                }
            }
        }
    }

    public void onEventBackgroundThread(d dVar) {
        if (dVar == d.SLEEP) {
            dji.thirdparty.a.c.a().e(new b(e.BTN_TK_QUIT, c.c));
            dji.thirdparty.a.c.a().e(new b(dji.phone.e.a.a.l, c.i));
            dji.thirdparty.a.c.a().e(new b(dji.phone.e.a.a.m, c.i));
        }
    }

    private void a(String str) {
    }
}
