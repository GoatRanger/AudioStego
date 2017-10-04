package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataSimulatorGetPushConnectHeartPacket;
import dji.midware.data.model.P3.DataSimulatorGetPushFlightStatusParams;
import dji.midware.data.model.P3.DataSimulatorGetPushMainControllerReturnParams;
import dji.midware.data.model.P3.DataSimulatorSetGetWind;

public class l implements dji.midware.e.a {

    public enum a {
        GetPushConnectHeartPacket(1, false, DataSimulatorGetPushConnectHeartPacket.class),
        ConnectHeartPacket(1),
        RequestMainControllerParams(2),
        GetPushMainControllerReturnParams(3, false, DataSimulatorGetPushMainControllerReturnParams.class),
        SimulateFlightCommend(4),
        GetPushFlightStatusParams(6, false, DataSimulatorGetPushFlightStatusParams.class),
        SetGetWind(7, false, DataSimulatorSetGetWind.class),
        SetGetArea(8),
        SetGetAirParams(9),
        ForceMoment(10),
        SetGetTemperature(11),
        SetGetGravity(12),
        CrashShutDown(13),
        CtrlMotor(14),
        Momentum(15),
        SetGetArmLength(16),
        SetGetMassInertia(17),
        SetGetMotorSetting(18),
        SetGetBatterySetting(19),
        GetFrequency(20),
        ResetAll(255),
        Other(511);
        
        private static a[] A;
        private int w;
        private boolean x;
        private boolean y;
        private Class<? extends n> z;

        static {
            A = values();
        }

        private a(int i) {
            this.x = true;
            this.y = true;
            this.w = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.x = true;
            this.y = true;
            this.w = i;
            this.x = z;
            this.z = cls;
        }

        private a(int i, boolean z) {
            this.x = true;
            this.y = true;
            this.w = i;
            this.y = z;
        }

        public int a() {
            return this.w;
        }

        public boolean b() {
            return this.x;
        }

        public boolean c() {
            return this.y;
        }

        public Class<? extends n> d() {
            return this.z;
        }

        public boolean a(int i) {
            return this.w == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < A.length; i2++) {
                if (A[i2].a(i)) {
                    return A[i2];
                }
            }
            return aVar;
        }
    }

    public boolean a(int i) {
        return false;
    }

    public boolean b(int i) {
        return a.find(i).b();
    }

    public boolean c(int i) {
        return a.find(i).c();
    }

    public Class<? extends n> d(int i) {
        return a.find(i).d();
    }

    public String a(int i, int i2) {
        return r.getDataModelName(DeviceType.find(i).toString(), a.find(i2).toString());
    }

    public String a(int i, int i2, int i3) {
        return a(i, i3);
    }

    public n e(int i) {
        return null;
    }
}
