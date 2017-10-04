package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.HashMap;

public class DataDm368GetGParams extends n implements e {
    private static DataDm368GetGParams instance = null;
    private CmdId cmdId;
    private boolean isLb2 = false;
    private HashMap<CmdId, Integer> mDm368GHm = new HashMap();

    public static synchronized DataDm368GetGParams getInstance() {
        DataDm368GetGParams dataDm368GetGParams;
        synchronized (DataDm368GetGParams.class) {
            if (instance == null) {
                instance = new DataDm368GetGParams();
            }
            dataDm368GetGParams = instance;
        }
        return dataDm368GetGParams;
    }

    public DataDm368GetGParams setType(boolean z) {
        this.isLb2 = z;
        return this;
    }

    public boolean getIsShowOsd() {
        return ((Integer) get(2, 1, Integer.class)).intValue() == 1;
    }

    public boolean getIsDouble() {
        return toInt(this.mDm368GHm.get(CmdId.ShowDouble)) == 1;
    }

    public boolean getUnit() {
        return ((Integer) get(8, 1, Integer.class)).intValue() == 1;
    }

    public int get720PFps() {
        return ((Integer) get(11, 1, Integer.class)).intValue();
    }

    public int getOutputDevice() {
        return ((Integer) get(14, 1, Integer.class)).intValue();
    }

    public int getHDMIFormat() {
        return ((Integer) get(17, 1, Integer.class)).intValue();
    }

    public int getOutputMode() {
        return toInt(this.mDm368GHm.get(CmdId.SetOutputMode));
    }

    public int getSDIFormat() {
        return ((Integer) get(23, 1, Integer.class)).intValue();
    }

    public int getOsdMarginTop() {
        return ((Integer) get(26, 1, Integer.class)).intValue();
    }

    public int getOsdMarginLeft() {
        return ((Integer) get(29, 1, Integer.class)).intValue();
    }

    public int getOsdMarginBottom() {
        return ((Integer) get(32, 1, Integer.class)).intValue();
    }

    public int getOsdMarginRight() {
        return ((Integer) get(35, 1, Integer.class)).intValue();
    }

    public int getOutputLocation() {
        return ((Integer) get(38, 1, Integer.class)).intValue();
    }

    public boolean getOutputEnable() {
        return toInt(this.mDm368GHm.get(CmdId.SetOutputEnable)) == 1;
    }

    public int toInt(Object obj) {
        if (obj == null || !(obj instanceof Integer)) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        if (bArr != null && bArr.length >= 2) {
            int intValue = ((Integer) get(1, 1, Integer.class)).intValue();
            int i = 0;
            while ((intValue + i) + 1 < bArr.length) {
                CmdId find = CmdId.find(((Integer) get(i, 1, Integer.class)).intValue());
                int intValue2 = ((Integer) get(i + 1, 1, Integer.class)).intValue();
                this.mDm368GHm.put(find, Integer.valueOf(((Integer) get(i + 2, intValue2, Integer.class)).intValue()));
                i = (intValue2 + 2) + i;
                intValue = intValue2;
            }
        }
    }

    protected void doPack() {
        if (this.isLb2) {
            this._sendData = new byte[14];
            this._sendData[0] = (byte) CmdId.ShowOsd.a();
            this._sendData[1] = (byte) CmdId.ShowDouble.a();
            this._sendData[2] = (byte) CmdId.ShowUnit.a();
            this._sendData[3] = (byte) CmdId.Set720PFps.a();
            this._sendData[4] = (byte) CmdId.SetOutputDevice.a();
            this._sendData[5] = (byte) CmdId.SetHDMIFormat.a();
            this._sendData[6] = (byte) CmdId.SetOutputMode.a();
            this._sendData[7] = (byte) CmdId.SetSDIFormat.a();
            this._sendData[8] = (byte) CmdId.SetOsdTop.a();
            this._sendData[9] = (byte) CmdId.SetOsdLeft.a();
            this._sendData[10] = (byte) CmdId.SetOsdBottom.a();
            this._sendData[11] = (byte) CmdId.SetOsdRight.a();
            this._sendData[12] = (byte) CmdId.SetOutputLoc.a();
            this._sendData[13] = (byte) CmdId.SetOutputEnable.a();
            return;
        }
        this._sendData = new byte[4];
        this._sendData[0] = (byte) CmdId.ShowOsd.a();
        this._sendData[1] = (byte) CmdId.ShowDouble.a();
        this._sendData[2] = (byte) CmdId.ShowUnit.a();
        this._sendData[3] = (byte) CmdId.Set720PFps.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.DM368_G.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.GetGParams.a();
        start(cVar, dVar);
    }
}
