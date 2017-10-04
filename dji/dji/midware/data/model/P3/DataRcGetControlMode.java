package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelCustomModel;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.ArrayList;

public class DataRcGetControlMode extends n implements e {
    private static DataRcGetControlMode instance = null;
    private ArrayList<ChannelCustomModel> arrayList;
    private ControlMode controlMode;

    public static synchronized DataRcGetControlMode getInstance() {
        DataRcGetControlMode dataRcGetControlMode;
        synchronized (DataRcGetControlMode.class) {
            if (instance == null) {
                instance = new DataRcGetControlMode();
            }
            dataRcGetControlMode = instance;
        }
        return dataRcGetControlMode;
    }

    public ControlMode getControlType() {
        this.controlMode = ControlMode.find(((Integer) get(0, 1, Integer.class)).intValue());
        return this.controlMode;
    }

    public ArrayList<ChannelCustomModel> getChannels() {
        if (this.arrayList == null) {
            this.arrayList = new ArrayList(4);
        }
        for (int i = 0; i < 4; i++) {
            int intValue = ((Integer) get(i + 1, 1, Integer.class)).intValue();
            ChannelCustomModel channelCustomModel = new ChannelCustomModel();
            channelCustomModel.a = intValue >> 7;
            channelCustomModel.b = intValue & -129;
            if (this.arrayList.size() > i) {
                this.arrayList.set(i, channelCustomModel);
            } else {
                this.arrayList.add(i, channelCustomModel);
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
        cVar.n = k.a.GetControlMode.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
