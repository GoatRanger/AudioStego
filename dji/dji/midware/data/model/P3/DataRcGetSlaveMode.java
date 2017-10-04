package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcSetSlaveMode.ControlMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode.SlaveCustomModel;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.ArrayList;

public class DataRcGetSlaveMode extends n implements e {
    private static DataRcGetSlaveMode instance = null;
    private ArrayList<SlaveCustomModel> arrayList;
    private ControlMode controlMode;

    public static synchronized DataRcGetSlaveMode getInstance() {
        DataRcGetSlaveMode dataRcGetSlaveMode;
        synchronized (DataRcGetSlaveMode.class) {
            if (instance == null) {
                instance = new DataRcGetSlaveMode();
            }
            dataRcGetSlaveMode = instance;
        }
        return dataRcGetSlaveMode;
    }

    public ControlMode getControlType() {
        this.controlMode = ControlMode.find(((Integer) get(0, 1, Integer.class)).intValue());
        return this.controlMode;
    }

    public ArrayList<SlaveCustomModel> getChannels() {
        if (this.arrayList == null) {
            this.arrayList = new ArrayList(4);
        }
        for (int i = 0; i < 4; i++) {
            int intValue = ((Integer) get(i + 1, 1, Integer.class)).intValue();
            SlaveCustomModel slaveCustomModel = new SlaveCustomModel();
            slaveCustomModel.a = intValue >> 7;
            slaveCustomModel.b = intValue & -129;
            if (this.arrayList.size() > i) {
                this.arrayList.set(i, slaveCustomModel);
            } else {
                this.arrayList.add(i, slaveCustomModel);
            }
        }
        return this.arrayList;
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
        cVar.n = k.a.GetSlaveMode.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
