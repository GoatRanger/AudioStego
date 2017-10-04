package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataEyeGetPushAvoidanceParam;
import dji.midware.data.model.P3.DataEyeGetPushEasySelfCalibration;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushFlatCheck;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance;
import dji.midware.data.model.P3.DataEyeGetPushFunctionList;
import dji.midware.data.model.P3.DataEyeGetPushLog;
import dji.midware.data.model.P3.DataEyeGetPushPointAvoidance;
import dji.midware.data.model.P3.DataEyeGetPushPointLog;
import dji.midware.data.model.P3.DataEyeGetPushPointState;
import dji.midware.data.model.P3.DataEyeGetPushPreciseLandingEnergy;
import dji.midware.data.model.P3.DataEyeGetPushSensorException;
import dji.midware.data.model.P3.DataEyeGetPushTrackLog;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus;
import dji.midware.data.model.P3.DataEyePushVisionTip;

public class f implements dji.midware.e.a {

    public enum a {
        GetEyePushLog(1, false, DataEyeGetPushLog.class),
        GetPushAvoidance(6, false, DataEyeGetPushAvoidanceParam.class),
        GetPushFrontAvoidance(7, false, DataEyeGetPushFrontAvoidance.class),
        GetPushPointAvoidance(8, false, DataEyeGetPushPointAvoidance.class),
        GetPushTrackLog(13, false, DataEyeGetPushTrackLog.class),
        GetPushPointLog(14, false, DataEyeGetPushPointLog.class),
        GetPushFlatCheck(25, false, DataEyeGetPushFlatCheck.class),
        SetTrackSelect(32),
        CtrlTrackSelect(33),
        MoveTrackSelect(34),
        GetPushTrackStatus(35, false, DataEyeGetPushTrackStatus.class),
        SetPointPos(36),
        SetFlyYaw(37),
        GetPushPointState(38, false, DataEyeGetPushPointState.class),
        CommonCtrl(39),
        GetParams(40),
        SetParam(41),
        GetPushException(42, false, DataEyeGetPushException.class),
        GetPushFunctionList(46, false, DataEyeGetPushFunctionList.class),
        GetPushSensorException(47, false, DataEyeGetPushSensorException.class),
        EasySelfCal(48),
        SendTrackingUserLocation(49),
        GetPushEasySelfCal(50, false, DataEyeGetPushEasySelfCalibration.class),
        PushVisionTips(57, false, DataEyePushVisionTip.class),
        GetPushPreciseLandingEnergy(58, false, DataEyeGetPushPreciseLandingEnergy.class),
        Other(511);
        
        private static a[] E;
        private int A;
        private boolean B;
        private boolean C;
        private Class<? extends n> D;

        static {
            E = values();
        }

        private a(int i) {
            this.B = true;
            this.C = true;
            this.A = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.B = true;
            this.C = true;
            this.A = i;
            this.B = z;
            this.D = cls;
        }

        private a(int i, boolean z) {
            this.B = true;
            this.C = true;
            this.A = i;
            this.C = z;
        }

        public int a() {
            return this.A;
        }

        public boolean b() {
            return this.B;
        }

        public Class<? extends n> c() {
            return this.D;
        }

        public boolean d() {
            return this.C;
        }

        public boolean a(int i) {
            return this.A == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < E.length; i2++) {
                if (E[i2].a(i)) {
                    return E[i2];
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
        return a.find(i).d();
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
