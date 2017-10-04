package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import android.util.SparseArray;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetChannelParams extends n implements e {
    private static DataRcGetChannelParams instance = null;

    public static class DJIChannel {
        public boolean direction;
        public int name;
        public int value;
    }

    public static synchronized DataRcGetChannelParams getInstance() {
        DataRcGetChannelParams dataRcGetChannelParams;
        synchronized (DataRcGetChannelParams.class) {
            if (instance == null) {
                instance = new DataRcGetChannelParams();
            }
            dataRcGetChannelParams = instance;
        }
        return dataRcGetChannelParams;
    }

    public SparseArray<DJIChannel> getList() {
        SparseArray<DJIChannel> sparseArray = new SparseArray();
        if (this._recData == null) {
            return sparseArray;
        }
        int length = this._recData.length / 3;
        for (int i = 0; i < length; i++) {
            boolean z;
            DJIChannel dJIChannel = new DJIChannel();
            int intValue = ((Integer) get(i * 3, 3, Integer.class)).intValue();
            dJIChannel.name = intValue & TransportMediator.KEYCODE_MEDIA_PAUSE;
            dJIChannel.value = intValue >> 8;
            if ((intValue & 128) == 1) {
                z = true;
            } else {
                z = false;
            }
            dJIChannel.direction = z;
            sparseArray.put(i, dJIChannel);
        }
        return sparseArray;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetChannelParams.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
