package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal;
import dji.midware.data.model.P3.DataWifiGetPushFirstAppMac;
import dji.midware.data.model.P3.DataWifiGetPushSignal;
import dji.midware.data.model.P3.DataWifiGetPushSweepFrequency;

public class o implements dji.midware.e.a {

    public enum a {
        GetSSID(7),
        SetSSID(8),
        GetSignalPush(9, false, DataWifiGetPushSignal.class),
        SetWifiFrequency(16),
        GetPassword(14),
        SetPassword(13),
        GetPushFirstAppMac(17, false, DataWifiGetPushFirstAppMac.class),
        GetPushElectricSignal(18, false, DataWifiGetPushElecSignal.class),
        SetPowerMode(19),
        RestartWifi(21),
        GetWifiFrequency(32),
        SetNoiseCheckAdapt(38),
        SwitchSDR(39),
        GetChannelList(40),
        SetSweepFrequency(41),
        GetPushSweepFrequency(42, false, DataWifiGetPushSweepFrequency.class),
        SetWifiModeChannel(43),
        SetWifiCodeRate(44),
        GetWifiCurCodeRate(45),
        SetWifiFreq5GMode(46),
        GetWifiFreqMode(47),
        SetWiFiCountryCode(48),
        RequestSnrPush(41),
        Other(511);
        
        private static a[] C;
        private boolean A;
        private Class<? extends n> B;
        private int y;
        private boolean z;

        static {
            C = values();
        }

        private a(int i) {
            this.z = true;
            this.A = true;
            this.y = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.z = true;
            this.A = true;
            this.y = i;
            this.z = z;
            this.B = cls;
        }

        private a(int i, boolean z) {
            this.z = true;
            this.A = true;
            this.y = i;
            this.A = z;
        }

        public int a() {
            return this.y;
        }

        public boolean b() {
            return this.z;
        }

        public Class<? extends n> c() {
            return this.B;
        }

        public boolean d() {
            return this.A;
        }

        public boolean a(int i) {
            return this.y == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < C.length; i2++) {
                if (C[i2].a(i)) {
                    return C[i2];
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
