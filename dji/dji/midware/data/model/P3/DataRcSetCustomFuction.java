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

public class DataRcSetCustomFuction extends n implements e {
    private static DataRcSetCustomFuction a = null;
    private int b;
    private int c;

    public enum DJICustomType {
        CameraSetting(0),
        GimbalCenter(1),
        SwitchGimbalMode(2),
        MapSwitch(3),
        ClearRote(4),
        Bettery(5),
        GimbalDirec(6),
        CenterMetering(7),
        AeLock(8),
        ForeArm(9),
        Vision1(10),
        Vision2(11),
        Navigation(12),
        PlayBack(13),
        GimbalRecenter(14),
        LiveViewExpand(15),
        QuickCircle(16),
        EnterGSMode(64),
        ExitGSMode(65),
        ForceMapSwitch(66),
        CenterFocus(17),
        OTHER(119);
        
        private int w;

        private DJICustomType(int i) {
            this.w = i;
        }

        public int a() {
            return this.w;
        }

        public boolean a(int i) {
            return this.w == i;
        }

        public static DJICustomType find(int i) {
            DJICustomType dJICustomType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dJICustomType;
        }
    }

    public static synchronized DataRcSetCustomFuction getInstance() {
        DataRcSetCustomFuction dataRcSetCustomFuction;
        synchronized (DataRcSetCustomFuction.class) {
            if (a == null) {
                a = new DataRcSetCustomFuction();
            }
            dataRcSetCustomFuction = a;
        }
        return dataRcSetCustomFuction;
    }

    public DataRcSetCustomFuction a(int i) {
        this.b = i;
        return this;
    }

    public DataRcSetCustomFuction b(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) this.c;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.RC.a();
        cVar.n = k.a.N.b();
        cVar.p = getSendData();
        cVar.v = 1000;
        start(cVar, dVar);
    }
}
