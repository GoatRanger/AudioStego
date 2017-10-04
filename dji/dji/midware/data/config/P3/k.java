package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcAckGimbalCtrPermission;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataRcGetPushConnectStatus;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus2;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcGetPushRcCustomButtonsStatus;
import dji.midware.data.model.P3.DataRcSimPushParams;

public class k implements dji.midware.e.a {

    public enum a {
        GetChannelParams(1),
        SetChannelParams(2),
        SetCalibration(3),
        GetHardwareParams(4),
        GetPushParams(5, false, DataRcGetPushParams.class),
        SetMaster(6),
        GetMaster(7, false),
        SetName(8),
        GetName(9, false),
        SetPassword(10),
        GetPassword(11, false),
        SetConnectMaster(12),
        GetConnectMaster(13, false),
        GetSearchMasters(14, false),
        SetSearchMode(15),
        GetSearchMode(16, false),
        SetToggle(17),
        GetToggle(18, false),
        RequestSlaveJoin(19),
        GetSlaveList(20, false),
        DeleteSlave(21),
        DeleteMaster(22),
        SetSlavePermission(23, false),
        GetSlavePermission(24, false),
        SetControlMode(25),
        GetControlMode(26),
        GetPushGpsInfo(27, false, DataRcGetPushGpsInfo.class),
        GetPushBatteryInfo(30, false, DataRcGetPushBatteryInfo.class),
        GetPushConnectStatus(31, false, DataRcGetPushConnectStatus.class),
        SetPowerMode(32),
        RequestGimbalCtrPermission(34),
        AckGimbalCtrPermission(35, false, DataRcAckGimbalCtrPermission.class),
        SetSimulation(36),
        GetSimFlyStatus(37),
        GetSimPushParams(38, false, DataRcSimPushParams.class),
        SetSlaveMode(41),
        GetSlaveMode(42),
        SetGimbalSpeed(43),
        GetGimbalSpeed(44),
        SetCustomFuction(45),
        GetCustomFuction(46),
        SetFrequency(47),
        SetRTC(49),
        SetWheelGain(51),
        GetWheelGain(52),
        SetGimbalControlMode(53),
        GetGimbalControlMode(54),
        CoachMode(60),
        GetPushFollowFocus(66, false, DataRcGetPushFollowFocus.class),
        GetPushFollowFocus2(152, false, DataRcGetPushFollowFocus2.class),
        SetFollowFocusInfo(153),
        GetPushRcCustomButtonsStatus(81, false, DataRcGetPushRcCustomButtonsStatus.class),
        GetRcUnitNLang(83),
        SetRcUnitNLang(84),
        GetFDRcCalibrationStatue(248),
        Other(511);
        
        private static a[] aj;
        private int ae;
        private boolean af;
        private boolean ag;
        private boolean ah;
        private Class<? extends n> ai;

        static {
            aj = values();
        }

        private a(int i) {
            this.af = true;
            this.ag = true;
            this.ah = false;
            this.ae = i;
        }

        private a(int i, boolean z, boolean z2, Class<? extends n> cls) {
            this.af = true;
            this.ag = true;
            this.ah = false;
            this.ae = i;
            this.af = z;
            this.ah = z2;
            this.ai = cls;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.af = true;
            this.ag = true;
            this.ah = false;
            this.ae = i;
            this.af = z;
            this.ai = cls;
        }

        private a(int i, boolean z) {
            this.af = true;
            this.ag = true;
            this.ah = false;
            this.ae = i;
            this.ag = z;
        }

        public boolean a() {
            return this.ah;
        }

        public int b() {
            return this.ae;
        }

        public boolean c() {
            return this.af;
        }

        public boolean d() {
            return this.ag;
        }

        public Class<? extends n> e() {
            return this.ai;
        }

        public boolean a(int i) {
            return this.ae == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < aj.length; i2++) {
                if (aj[i2].a(i)) {
                    return aj[i2];
                }
            }
            return aVar;
        }
    }

    public boolean a(int i) {
        return a.find(i).a();
    }

    public boolean b(int i) {
        return a.find(i).c();
    }

    public boolean c(int i) {
        return a.find(i).d();
    }

    public Class<? extends n> d(int i) {
        return a.find(i).e();
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
