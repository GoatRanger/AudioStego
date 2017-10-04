package dji.pilot.flyforbid;

import com.here.android.mpa.mapping.Map;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetPushRequestLimitUpdate;
import dji.midware.e.d;
import dji.thirdparty.a.c;

class a$5 implements d {
    final /* synthetic */ int a;
    final /* synthetic */ a b;

    a$5(a aVar, int i) {
        this.b = aVar;
        this.a = i;
    }

    public void onSuccess(Object obj) {
        a.h(this.b);
        a.f(this.b, false);
        if (a.i(this.b) != null || (this.a == 0 && a.i(this.b) == null)) {
            a.j(this.b).sendMessage(a.j(this.b).obtainMessage(1, null));
        }
    }

    public void onFailure(a aVar) {
        a.c(this.b, Map.MOVE_PRESERVE_ZOOM_LEVEL);
        a.d(this.b, Map.MOVE_PRESERVE_ZOOM_LEVEL);
        a.f(this.b, false);
        c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
    }
}
