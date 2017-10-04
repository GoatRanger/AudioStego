package dji.pilot.fpv.control;

import dji.midware.data.params.P3.a;
import dji.midware.e.d;

class v$2 implements d {
    final /* synthetic */ v$a a;
    final /* synthetic */ v b;

    v$2(v vVar, v$a dji_pilot_fpv_control_v_a) {
        this.b = vVar;
        this.a = dji_pilot_fpv_control_v_a;
    }

    public void onSuccess(Object obj) {
        Object obj2 = 1;
        v.a(this.b, dji.midware.data.manager.P3.d.read(a.B).value.intValue() == 1);
        if (!v.g(this.b)) {
            Object obj3;
            v.a(this.b, dji.midware.data.manager.P3.d.read("g_config.flying_limit.max_height_0").value.intValue());
            if (v.h(this.b) > 50) {
                v.a(this.b, 50);
                v.i(this.b);
                obj3 = 1;
            } else {
                obj3 = null;
            }
            v.b(this.b, dji.midware.data.manager.P3.d.read("g_config.flying_limit.max_radius_0").value.intValue());
            if (v.j(this.b) > 80) {
                v.b(this.b, 80);
                v.k(this.b);
            } else {
                obj2 = null;
            }
            if (obj3 != null && obj2 != null) {
                this.a.c();
            } else if (obj3 != null) {
                this.a.a();
            } else if (obj2 != null) {
                this.a.b();
            }
        }
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
    }
}
