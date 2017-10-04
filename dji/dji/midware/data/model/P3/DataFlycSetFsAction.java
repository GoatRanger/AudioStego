package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycGetFsAction.FS_ACTION;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetFsAction extends n implements e {
    private static DataFlycSetFsAction a = null;
    private FS_ACTION b = FS_ACTION.GoHome;

    public static synchronized DataFlycSetFsAction getInstance() {
        DataFlycSetFsAction dataFlycSetFsAction;
        synchronized (DataFlycSetFsAction.class) {
            if (a == null) {
                a = new DataFlycSetFsAction();
            }
            dataFlycSetFsAction = a;
        }
        return dataFlycSetFsAction;
    }

    public DataFlycSetFsAction a(FS_ACTION fs_action) {
        this.b = fs_action;
        return this;
    }

    public FS_ACTION a() {
        FS_ACTION fs_action = FS_ACTION.GoHome;
        if (this._recData == null || this._recData.length == 0) {
            return this.b == FS_ACTION.Landing ? FS_ACTION.GoHome : FS_ACTION.Landing;
        } else {
            return FS_ACTION.find(((Integer) get(0, 1, Integer.class)).intValue());
        }
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.value();
        DJILogHelper.getInstance().LOGD("", "send fail safe: " + this._sendData[0], true, true);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.z.a();
        start(cVar, dVar);
    }
}
