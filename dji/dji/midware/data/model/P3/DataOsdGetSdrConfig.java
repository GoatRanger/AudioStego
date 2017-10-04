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

public class DataOsdGetSdrConfig extends n implements e {
    private static DataOsdGetSdrConfig instance = null;

    public static synchronized DataOsdGetSdrConfig getInstance() {
        DataOsdGetSdrConfig dataOsdGetSdrConfig;
        synchronized (DataOsdGetSdrConfig.class) {
            if (instance == null) {
                instance = new DataOsdGetSdrConfig();
            }
            dataOsdGetSdrConfig = instance;
        }
        return dataOsdGetSdrConfig;
    }

    public int getChannel() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getBandwidthType() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getSelectionMode() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getSdrNf() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getSdrBand() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public boolean getIsAuto() {
        return ((Integer) get(2, 1, Integer.class)).intValue() != 0;
    }

    public boolean getIsMaster() {
        return ((Integer) get(3, 1, Integer.class)).intValue() == 1;
    }

    public int getMcs() {
        return ((Integer) get(9, 1, Integer.class)).intValue();
    }

    public boolean getSingleOrDouble() {
        return ((Integer) get(10, 1, Integer.class)).intValue() == 2;
    }

    public int getMcsType() {
        return ((Integer) get(13, 1, Integer.class)).intValue();
    }

    public int getAutoChannel() {
        return Math.round(((Float) get(14, 4, Float.class)).floatValue());
    }

    public float getAutoChannelShow() {
        return ((Float) get(14, 4, Float.class)).floatValue();
    }

    public float getAutoMcs() {
        return ((Float) get(18, 4, Float.class)).floatValue();
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
        cVar.m = p.j.a();
        cVar.n = i.a.GetSdrConfig.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
