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

public class DataSingleMoveTrackSelect extends n implements e {
    private MoveCtrlType a = MoveCtrlType.CONCERN;
    private float b = 0.0f;
    private float c = 0.0f;
    private float d = 0.0f;
    private float e = 0.0f;

    public enum MoveCtrlType {
        CONCERN(0),
        MODIFY(1),
        OTHER(8);
        
        private int d;

        private MoveCtrlType(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static MoveCtrlType find(int i) {
            MoveCtrlType moveCtrlType = CONCERN;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return moveCtrlType;
        }
    }

    public DataSingleMoveTrackSelect a(MoveCtrlType moveCtrlType) {
        this.a = moveCtrlType;
        return this;
    }

    public DataSingleMoveTrackSelect a(float f, float f2) {
        this.b = f;
        this.c = f2;
        return this;
    }

    public DataSingleMoveTrackSelect b(float f, float f2) {
        this.d = f;
        this.e = f2;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[17];
        this._sendData[0] = (byte) this.a.a();
        System.arraycopy(c.a(this.b), 0, this._sendData, 1, 4);
        System.arraycopy(c.a(this.c), 0, this._sendData, 5, 4);
        System.arraycopy(c.a(this.d), 0, this._sendData, 9, 4);
        System.arraycopy(c.a(this.e), 0, this._sendData, 13, 4);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        cVar.n = f.a.MoveTrackSelect.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
