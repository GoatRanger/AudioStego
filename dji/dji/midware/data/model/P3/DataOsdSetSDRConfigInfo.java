package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.ArrayList;

public class DataOsdSetSDRConfigInfo extends n implements e {
    private static DataOsdSetSDRConfigInfo a;
    private ArrayList<SDRConfigInfo> b;

    public static class SDRConfigInfo {
        public SDRConfigType a;
        public int b;

        public SDRConfigInfo(SDRConfigType sDRConfigType, int i) {
            this.a = sDRConfigType;
            this.b = i;
        }
    }

    public enum SDRConfigType {
        NFIndex(1),
        DownlinkFrequencyBand(2),
        SelectionMode(3),
        Bandwidth(4),
        UplinkFrequencyBand(5),
        Unknown(255);
        
        private int g;

        private SDRConfigType(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static SDRConfigType find(int i) {
            SDRConfigType sDRConfigType = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return sDRConfigType;
        }
    }

    public static synchronized DataOsdSetSDRConfigInfo getInstance() {
        DataOsdSetSDRConfigInfo dataOsdSetSDRConfigInfo;
        synchronized (DataOsdSetSDRConfigInfo.class) {
            if (a == null) {
                a = new DataOsdSetSDRConfigInfo();
            }
            dataOsdSetSDRConfigInfo = a;
        }
        return dataOsdSetSDRConfigInfo;
    }

    public DataOsdSetSDRConfigInfo a(ArrayList<SDRConfigInfo> arrayList) {
        this.b = arrayList;
        return this;
    }

    public DataOsdSetSDRConfigInfo a(SDRConfigInfo sDRConfigInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(sDRConfigInfo);
        this.b = arrayList;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetSDRConfigInfo.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        if (this.b != null) {
            this._sendData = new byte[(this.b.size() * 3)];
            for (int i = 0; i < this.b.size(); i++) {
                this._sendData[i * 3] = (byte) ((SDRConfigInfo) this.b.get(i)).a.a();
                System.arraycopy(dji.midware.util.c.a(((SDRConfigInfo) this.b.get(i)).b), 0, this._sendData, (i * 3) + 1, 2);
            }
        }
    }
}
