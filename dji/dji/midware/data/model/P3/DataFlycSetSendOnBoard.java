package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetSendOnBoard extends n implements e {
    private static DataFlycSetSendOnBoard a = null;
    private byte[] b;

    public static synchronized DataFlycSetSendOnBoard getInstance() {
        DataFlycSetSendOnBoard dataFlycSetSendOnBoard;
        synchronized (DataFlycSetSendOnBoard.class) {
            if (a == null) {
                a = new DataFlycSetSendOnBoard();
            }
            dataFlycSetSendOnBoard = a;
        }
        return dataFlycSetSendOnBoard;
    }

    public DataFlycSetSendOnBoard a(byte[] bArr) {
        this.b = bArr;
        return this;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.T.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = this.b;
    }
}
