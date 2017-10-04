package dji.midware.data.manager.P3;

import dji.midware.data.a.a.b;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.d.a;
import dji.midware.data.config.P3.p;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.thirdparty.a.c;

public class f extends h {
    private int a;

    public f() {
        this.a = 1000;
        this.enabledSetDataEvent = true;
    }

    protected void debug(b bVar) {
        if (this.isCheck && (bVar.m == 2 || bVar.f == DeviceType.OFDM.value() || bVar.m == 3 || bVar.m == 4 || bVar.m == 12)) {
            if (this.curCameraEvent != o.b) {
                this.curCameraEvent = o.b;
                DataSpecialControl.getInstance().init().start(20);
                c.a().e(this.curCameraEvent);
            }
            this.handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, (long) this.a);
        }
        if (bVar.m == p.a.a() && bVar.n != a.e.a()) {
        }
    }
}
