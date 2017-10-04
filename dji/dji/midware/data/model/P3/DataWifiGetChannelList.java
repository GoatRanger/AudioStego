package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.o;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataWifiGetChannelList extends n implements e {
    private static DataWifiGetChannelList mInstance = null;
    private boolean isSupport5G = true;
    private int mCurChannel = -1;

    public static synchronized DataWifiGetChannelList getInstance() {
        DataWifiGetChannelList dataWifiGetChannelList;
        synchronized (DataWifiGetChannelList.class) {
            if (mInstance == null) {
                mInstance = new DataWifiGetChannelList();
            }
            dataWifiGetChannelList = mInstance;
        }
        return dataWifiGetChannelList;
    }

    public DataWifiGetChannelList setSupport5G(boolean z) {
        this.isSupport5G = z;
        return this;
    }

    public int[] get24GChannelList() {
        int i = 0;
        int intValue = ((Integer) get(13, 4, Integer.class)).intValue();
        if (((Integer) get(17, 2, Integer.class)).intValue() > 6) {
            intValue = 0;
        }
        int[] iArr = new int[intValue];
        while (i != intValue) {
            iArr[i] = ((Integer) get((i * 2) + 17, 2, Integer.class)).intValue();
            i++;
        }
        return iArr;
    }

    public int[] get5GChannelList() {
        int i = 0;
        int intValue = ((Integer) get(13, 4, Integer.class)).intValue();
        if (((Integer) get(17, 2, Integer.class)).intValue() > 6) {
            intValue = 0;
        }
        int i2 = ((intValue * 2) + 17) + 4;
        if (intValue == 0) {
            intValue = i2 - 8;
        } else {
            intValue = i2;
        }
        int intValue2 = ((Integer) get(intValue, 4, Integer.class)).intValue();
        int[] iArr = new int[intValue2];
        while (i != intValue2) {
            iArr[i] = ((Integer) get((intValue + 4) + (i * 2), 2, Integer.class)).intValue();
            i++;
        }
        return iArr;
    }

    public int getCurChannel() {
        this.mCurChannel = ((Integer) get(4, 1, Integer.class)).intValue();
        return this.mCurChannel;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        if (this.isSupport5G) {
            this._sendData[0] = (byte) 1;
        } else {
            this._sendData[0] = (byte) 0;
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.WIFI.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.h.a();
        cVar.n = o.a.GetChannelList.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
