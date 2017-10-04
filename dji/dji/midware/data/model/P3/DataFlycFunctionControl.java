package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycFunctionControl extends n implements e {
    private static DataFlycFunctionControl instance = null;
    private FLYC_COMMAND type;

    public enum FLYC_COMMAND {
        AUTO_FLY(1),
        AUTO_LANDING(2),
        HOMEPOINT_NOW(3),
        HOMEPOINT_HOT(4),
        HOMEPOINT_LOC(5),
        GOHOME(6),
        START_MOTOR(7),
        STOP_MOTOR(8),
        Calibration(9),
        DeformProtecClose(10),
        DeformProtecOpen(11),
        DropGohome(12),
        DropTakeOff(13),
        DropLanding(14),
        DynamicHomePointOpen(15),
        DynamicHomePointClose(16),
        FollowFunctioonOpen(17),
        FollowFunctionClose(18),
        IOCOpen(19),
        IOCClose(20),
        DropCalibration(21),
        PackMode(22),
        UnPackMode(23),
        EnterManaualMode(24),
        StopDeform(25),
        DownDeform(28),
        UpDeform(29),
        ForceLanding(30),
        ForceLanding2(31),
        OTHER(100);
        
        private int data;

        private FLYC_COMMAND(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FLYC_COMMAND find(int i) {
            FLYC_COMMAND flyc_command = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return flyc_command;
        }
    }

    public static synchronized DataFlycFunctionControl getInstance() {
        DataFlycFunctionControl dataFlycFunctionControl;
        synchronized (DataFlycFunctionControl.class) {
            if (instance == null) {
                instance = new DataFlycFunctionControl();
            }
            dataFlycFunctionControl = instance;
        }
        return dataFlycFunctionControl;
    }

    public DataFlycFunctionControl setCommand(FLYC_COMMAND flyc_command) {
        this.type = flyc_command;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.type.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.FLYC.a();
        cVar.n = g.a.FunctionControl.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
