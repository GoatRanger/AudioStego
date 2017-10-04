package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataOsdGetHdvtPushException;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdGetPushDebugInfo;
import dji.midware.data.model.P3.DataOsdGetPushDevicesState;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushMaxMcs;
import dji.midware.data.model.P3.DataOsdGetPushMicInfo;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.midware.data.model.P3.DataOsdGetPushSDRBarInterference;
import dji.midware.data.model.P3.DataOsdGetPushSDRNfParams;
import dji.midware.data.model.P3.DataOsdGetPushSdrConfigInfo;
import dji.midware.data.model.P3.DataOsdGetPushSdrStatusGroundInfo;
import dji.midware.data.model.P3.DataOsdGetPushSdrStatusInfo;
import dji.midware.data.model.P3.DataOsdGetPushSdrSweepFrequency;
import dji.midware.data.model.P3.DataOsdGetPushSdrUpwardSelectChannel;
import dji.midware.data.model.P3.DataOsdGetPushSdrUpwardSweepFrequency;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.data.model.P3.DataOsdGetPushSweepFrequency;
import dji.midware.data.model.P3.DataOsdGetPushWirelessState;
import dji.midware.data.model.P3.DataOsdGetSDRPushCustomCodeRate;
import dji.midware.data.model.P3.DataOsdOsmoPushCalibration;

public class i implements dji.midware.e.a {

    public enum a {
        GetPushCommon(1, false, DataOsdGetPushCommon.class),
        GetPushHome(2, false, DataOsdGetPushHome.class),
        GetPushBasebandState(3, false, null),
        SetFPGA(4),
        GetFPGA(5),
        Set9363(6),
        Get9363(7),
        GetPushSignalQuality(8, false, DataOsdGetPushSignalQuality.class),
        SetSweepFrequency(9),
        GetPushSweepFrequency(10, false, DataOsdGetPushSweepFrequency.class),
        GetPushDevicesState(11, false, DataOsdGetPushDevicesState.class),
        GetPushConfig(12, false, true, DataOsdGetPushConfig.class),
        SetConfig(13),
        SetUsbTransform(14),
        SetUpgradeTip(16),
        GetPushChannelStatus(17, false, DataOsdGetPushChannalStatus.class),
        SetMaxMcs(20),
        GetPushMaxMcs(21, false, DataOsdGetPushMaxMcs.class),
        SetLED(80),
        GetPushDebugInfo(22, false, DataOsdGetPushDebugInfo.class),
        GetPushSdrSweepFrequency(32, false, DataOsdGetPushSdrSweepFrequency.class),
        GetSdrConfig(33),
        GetPushSdrConfigInfo(34, false, DataOsdGetPushSdrConfigInfo.class),
        SetSdrStatus(35),
        GetPushSdrStatusInfo(36, false, DataOsdGetPushSdrStatusInfo.class),
        GetPushSdrStatusGroundInfo(37, false, DataOsdGetPushSdrStatusGroundInfo.class),
        SetSdrAssitantRead(38),
        SetSdrAssitantWrite(39),
        SetSdrStartLog(40),
        GetPushSdrUpwardSweepFrequency(41, false, DataOsdGetPushSdrUpwardSweepFrequency.class),
        GetPushSdrUpwardSelectChannel(42, false, DataOsdGetPushSdrUpwardSelectChannel.class),
        GetSdrLBT(45),
        SetSdrLBT(46),
        GetPushWirelessState(48, false, DataOsdGetPushWirelessState.class),
        SetSDRImageTransmissionMode(52),
        GetSDRImageTransmissionMode(53),
        GetSDRPushCustomCodeRate(54, false, DataOsdGetSDRPushCustomCodeRate.class),
        GetHdtvPushException(55, false, DataOsdGetHdvtPushException.class),
        SetSDRConfigInfo(57),
        GetPushSDRNfParams(58, false, DataOsdGetPushSDRNfParams.class),
        GetPushSDRBarDisturb(59, false, DataOsdGetPushSDRBarInterference.class),
        SetSDRForceBoost(60),
        SetPower(81),
        GetPushPowerStatus(82, false, DataOsdGetPushPowerStatus.class),
        OsmoCalibration(83),
        OsmoPushCalibration(84, false, DataOsdOsmoPushCalibration.class),
        SetMicGain(87),
        GetMicGain(88),
        GetPushMicInfo(89, false, DataOsdGetPushMicInfo.class),
        GetMicEnable(98),
        SetMicEnable(99),
        Other(511);
        
        private static a[] ae;
        private int aa;
        private boolean ab;
        private boolean ac;
        private Class<? extends n> ad;

        static {
            ae = values();
        }

        private a(int i) {
            this.ab = true;
            this.ac = false;
            this.aa = i;
        }

        private a(int i, boolean z, boolean z2, Class<? extends n> cls) {
            this.ab = true;
            this.ac = false;
            this.aa = i;
            this.ab = z;
            this.ac = z2;
            this.ad = cls;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.ab = true;
            this.ac = false;
            this.aa = i;
            this.ab = z;
            this.ad = cls;
        }

        public int a() {
            return this.aa;
        }

        public boolean b() {
            return this.ac;
        }

        public boolean c() {
            return this.ab;
        }

        public Class<? extends n> d() {
            return this.ad;
        }

        public boolean a(int i) {
            return this.aa == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < ae.length; i2++) {
                if (ae[i2].a(i)) {
                    return ae[i2];
                }
            }
            return aVar;
        }
    }

    public boolean a(int i) {
        return a.find(i).b();
    }

    public boolean b(int i) {
        return a.find(i).c();
    }

    public boolean c(int i) {
        return true;
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
