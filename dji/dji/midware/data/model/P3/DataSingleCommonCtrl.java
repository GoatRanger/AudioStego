package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.f;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSingleCommonCtrl extends n implements e {
    private CtrlState a = CtrlState.NORMAL;

    public enum CtrlState {
        NORMAL(0),
        STOP(1),
        OTHER(8);
        
        private int d;

        private CtrlState(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static CtrlState find(int i) {
            CtrlState ctrlState = NORMAL;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return ctrlState;
        }
    }

    public DataSingleCommonCtrl a(CtrlState ctrlState) {
        this.a = ctrlState;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.a.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.EYE.a();
        cVar.n = f.a.o.a();
        cVar.v = 1000;
        cVar.w = 2;
        start(cVar, dVar);
    }
}
