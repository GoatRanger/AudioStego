package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataGimbalGetPushAutoCalibrationStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushTimelapseStatus;
import dji.midware.data.model.P3.DataGimbalGetPushTutorialStatus;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushUserParams;
import dji.midware.data.model.P3.DataGimbalPushBatteryInfo;

public class h implements dji.midware.e.a {

    public enum a {
        Control(1),
        GetPushParams(5, false, DataGimbalGetPushParams.class),
        RollFinetune(7),
        AutoCalibration(8),
        SetAngle(10),
        SetOnOrOff(13),
        SpeedControl(12),
        AngleControl(10),
        SetUserParams(15),
        GetUserParams(16),
        SaveUserParams(17),
        ResetUserParams(19),
        AbsAngleControl(20),
        GetPushType(28, false, DataGimbalGetPushType.class),
        GetPushUserParams(36, false, DataGimbalGetPushUserParams.class),
        GetPushAbnormalStatus(39, false, DataGimbalGetPushAbnormalStatus.class),
        GetPushTutorialStatus(43, false, DataGimbalGetPushTutorialStatus.class),
        SetTutorialStep(44),
        GetPushAutoCalibrationStatus(48, false, DataGimbalGetPushAutoCalibrationStatus.class),
        RobinSetParams(49),
        RobinGetParams(50),
        RobinPushBattery(51, false, DataGimbalPushBatteryInfo.class),
        SetHandleParams(53),
        GetHandleParams(54),
        SetTimelapseParams(55),
        GetPushTimelapseStatus(56, false, DataGimbalGetPushTimelapseStatus.class),
        NotiFyCameraId(86),
        Other(511);
        
        private static a[] F;
        private int C;
        private boolean D;
        private Class<? extends n> E;

        static {
            F = values();
        }

        private a(int i) {
            this.D = true;
            this.C = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.D = true;
            this.C = i;
            this.D = z;
            this.E = cls;
        }

        public int a() {
            return this.C;
        }

        public boolean b() {
            return this.D;
        }

        public Class<? extends n> c() {
            return this.E;
        }

        public boolean a(int i) {
            return this.C == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < F.length; i2++) {
                if (F[i2].a(i)) {
                    return F[i2];
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
        return true;
    }

    public Class<? extends n> d(int i) {
        return a.find(i).c();
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
