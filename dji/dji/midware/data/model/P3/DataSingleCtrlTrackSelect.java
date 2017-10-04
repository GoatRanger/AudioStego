package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.f;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataSingleCtrlTrackSelect extends n implements e {
    private TrackCtrlState a = TrackCtrlState.CONFIRM;
    private short b = (short) 0;

    public enum TrackCtrlState {
        CANCEL(0),
        CONFIRM(1),
        PAUSE(2),
        OTHER(8);
        
        private int e;

        private TrackCtrlState(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static TrackCtrlState find(int i) {
            TrackCtrlState trackCtrlState = CONFIRM;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return trackCtrlState;
        }
    }

    public DataSingleCtrlTrackSelect a(TrackCtrlState trackCtrlState) {
        this.a = trackCtrlState;
        return this;
    }

    public DataSingleCtrlTrackSelect a(short s) {
        this.b = s;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[4];
        this._sendData[0] = (byte) this.a.a();
        System.arraycopy(c.b(this.b), 0, this._sendData, 1, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        cVar.n = f.a.CtrlTrackSelect.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
