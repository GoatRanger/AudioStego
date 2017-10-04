package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataGimbalRollFinetune extends n implements e {
    private static DataGimbalRollFinetune instance = null;
    private byte mData = (byte) 0;
    private int mRepeatTimes = -1;
    private int mTimeOut = -1;

    public static synchronized DataGimbalRollFinetune getInstance() {
        DataGimbalRollFinetune dataGimbalRollFinetune;
        synchronized (DataGimbalRollFinetune.class) {
            if (instance == null) {
                instance = new DataGimbalRollFinetune();
            }
            dataGimbalRollFinetune = instance;
        }
        return dataGimbalRollFinetune;
    }

    public DataGimbalRollFinetune setFineTuneValue(byte b) {
        this.mData = b;
        return this;
    }

    public void setTimeOut(int i) {
        this.mTimeOut = i;
    }

    public void setRepeatTimes(int i) {
        this.mRepeatTimes = i;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = this.mData;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.RollFinetune.a();
        cVar.p = getSendData();
        if (this.mTimeOut > 0) {
            cVar.v = this.mTimeOut;
        }
        if (this.mRepeatTimes > 0) {
            cVar.w = this.mRepeatTimes;
        }
        start(cVar, dVar);
    }
}
