package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcGetSlaveList.RcModel;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataRcSetConnectMaster extends n implements e {
    private static DataRcSetConnectMaster a = null;
    private RcModel b;

    public enum RcConnectError {
        WrongPwd(1),
        Refused(2),
        Exceed(3),
        TimeOut(4),
        OTHER(100);
        
        private int f;

        private RcConnectError(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static RcConnectError find(int i) {
            RcConnectError rcConnectError = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return rcConnectError;
        }
    }

    public static synchronized DataRcSetConnectMaster getInstance() {
        DataRcSetConnectMaster dataRcSetConnectMaster;
        synchronized (DataRcSetConnectMaster.class) {
            if (a == null) {
                a = new DataRcSetConnectMaster();
            }
            dataRcSetConnectMaster = a;
        }
        return dataRcSetConnectMaster;
    }

    public DataRcSetConnectMaster a(RcModel rcModel) {
        this.b = rcModel;
        return this;
    }

    public RcConnectError a(a aVar) {
        return RcConnectError.find(aVar.b());
    }

    protected void doPack() {
        this._sendData = new byte[12];
        System.arraycopy(c.a(this.b.id), 0, this._sendData, 0, 4);
        Object a = c.a(this.b.name);
        System.arraycopy(a, 0, this._sendData, 4, a.length);
        System.arraycopy(c.b(this.b.password), 0, this._sendData, 10, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetConnectMaster.b();
        start(cVar, dVar);
    }

    protected void LogPack(String str) {
        DJILogHelper.getInstance().LOGD("", "name " + this.b.name + " pwd " + this.b.password, false, true);
        DJILogHelper.getInstance().LOGD("", "send " + str, false, true);
    }
}
