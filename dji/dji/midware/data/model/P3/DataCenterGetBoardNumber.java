package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.model.a.b;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCenterGetBoardNumber extends b implements e {
    private static DataCenterGetBoardNumber instance;

    public static synchronized DataCenterGetBoardNumber getInstance() {
        DataCenterGetBoardNumber dataCenterGetBoardNumber;
        synchronized (DataCenterGetBoardNumber.class) {
            if (instance == null) {
                instance = new DataCenterGetBoardNumber();
            }
            dataCenterGetBoardNumber = instance;
        }
        return dataCenterGetBoardNumber;
    }

    public String getBoardNumber() {
        StringBuilder stringBuilder = new StringBuilder(13);
        if (this._recData != null && this._recData.length > 0) {
            int i = 1;
            int length = this._recData.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (c.e(this._recData[i2])) {
                    stringBuilder.append((char) this._recData[i2]);
                } else {
                    stringBuilder.append(this._recData[i2]);
                }
                if (this._recData[i2] != (byte) 0) {
                    i = 0;
                }
            }
            if (i != 0) {
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
        return stringBuilder.toString();
    }

    protected void doPack() {
        this._sendData = new byte[9];
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.f.a();
        cVar.n = dji.midware.data.config.P3.c.a.GetBoardNumber.a();
        cVar.v = 1500;
        start(cVar, dVar);
    }
}
