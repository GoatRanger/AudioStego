package dji.midware.data.model.P3;

import android.util.SparseArray;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcGetChannelParams.DJIChannel;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcSetChannelParams extends n implements e {
    private static DataRcSetChannelParams a = null;
    private SparseArray<DJIChannel> b;

    public static synchronized DataRcSetChannelParams getInstance() {
        DataRcSetChannelParams dataRcSetChannelParams;
        synchronized (DataRcSetChannelParams.class) {
            if (a == null) {
                a = new DataRcSetChannelParams();
            }
            dataRcSetChannelParams = a;
        }
        return dataRcSetChannelParams;
    }

    public DataRcSetChannelParams a(SparseArray<DJIChannel> sparseArray) {
        this.b = sparseArray;
        return this;
    }

    protected void doPack() {
        int size = this.b.size();
        this._sendData = new byte[size];
        for (int i = 0; i < size; i++) {
            int i2;
            DJIChannel dJIChannel = (DJIChannel) this.b.get(this.b.keyAt(i));
            byte[] bArr = this._sendData;
            int i3 = dJIChannel.name;
            if (dJIChannel.direction) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            bArr[i] = (byte) (i2 | i3);
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetChannelParams.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
