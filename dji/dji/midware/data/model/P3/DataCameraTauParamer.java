package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public abstract class DataCameraTauParamer extends n implements e {
    protected boolean a = false;
    protected ParamCmd b = ParamCmd.OTHER;
    protected byte[] c = null;

    public enum ParamCmd {
        PICTURE_ROTATE(1),
        INTEREST_REGION(2),
        DIGITAL_INC(3),
        SCENE_CONTRAST(4),
        SCENE_OPTIMIZATE(5),
        AGC(6),
        REGION_THERMOMETRIC(7),
        BRIGHTNESS(8),
        ISOTHERM_ENABLE(9),
        ISOTHERM_UNIT(10),
        ISOTHERM_LOWER(11),
        ISOTHERM_MIDDLE(12),
        ISOTHERM_UPPER(13),
        THERMOMETRIC_TYPE(14),
        GAIN_MODE(15),
        FFC_MODE(17),
        TRIGGER_FFC(18),
        EXTER_PARAM_TYPE(21),
        EXTER_PARAMS(22),
        AREA_AXIS(23),
        OTHER(100);
        
        private int v;

        private ParamCmd(int i) {
            this.v = i;
        }

        public int a() {
            return this.v;
        }

        public boolean a(int i) {
            return this.v == i;
        }

        public static ParamCmd find(int i) {
            ParamCmd paramCmd = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return paramCmd;
        }
    }

    public DataCameraTauParamer b(boolean z) {
        this.a = z;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[((this.c != null ? this.c.length : 0) + 1)];
        byte[] bArr = this._sendData;
        bArr[0] = (byte) (((byte) (this.a ? 0 : 128)) | bArr[0]);
        byte[] bArr2 = this._sendData;
        bArr2[0] = (byte) (bArr2[0] | this.b.a());
        if (this.c != null && this.c.length > 0) {
            System.arraycopy(this.c, 0, this._sendData, 1, this.c.length);
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bU.a();
        cVar.v = 2000;
        start(cVar, dVar);
    }
}
