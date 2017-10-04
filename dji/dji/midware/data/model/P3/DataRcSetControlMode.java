package dji.midware.data.model.P3;

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
import java.util.ArrayList;

public class DataRcSetControlMode extends n implements e {
    private static DataRcSetControlMode a = null;
    private ControlMode b = ControlMode.Japan;
    private ArrayList<ChannelCustomModel> c = new ArrayList(4);

    public static class ChannelCustomModel {
        public int a;
        public int b;

        public ChannelCustomModel(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public ChannelCustomModel a() {
            return new ChannelCustomModel(this.a, this.b);
        }
    }

    public enum ChannelType {
        None(0),
        b(1),
        E(2),
        T(3),
        R(4),
        OTHER(100);
        
        private int g;

        private ChannelType(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static ChannelType find(int i) {
            ChannelType channelType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return channelType;
        }
    }

    public enum ControlMode {
        Japan(1),
        America(2),
        China(3),
        Custom(4),
        OTHER(100);
        
        private int f;

        private ControlMode(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static ControlMode find(int i) {
            ControlMode controlMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return controlMode;
        }
    }

    public static synchronized DataRcSetControlMode getInstance() {
        DataRcSetControlMode dataRcSetControlMode;
        synchronized (DataRcSetControlMode.class) {
            if (a == null) {
                a = new DataRcSetControlMode();
            }
            dataRcSetControlMode = a;
        }
        return dataRcSetControlMode;
    }

    public DataRcSetControlMode a(ControlMode controlMode) {
        this.b = controlMode;
        return this;
    }

    public ControlMode a() {
        return this.b;
    }

    public DataRcSetControlMode a(ArrayList<ChannelCustomModel> arrayList) {
        this.c = arrayList;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = (byte) this.b.a();
        if (this.b.equals(ControlMode.Custom)) {
            for (int i = 0; i < this.c.size(); i++) {
                ChannelCustomModel channelCustomModel = (ChannelCustomModel) this.c.get(i);
                if (channelCustomModel != null) {
                    this._sendData[i + 1] = (byte) (channelCustomModel.b | (channelCustomModel.a << 7));
                }
            }
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
        cVar.n = k.a.SetControlMode.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
