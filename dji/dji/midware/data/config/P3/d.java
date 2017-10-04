package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetPushFile;
import dji.midware.data.model.P3.DataCameraGetPushFiles;
import dji.midware.data.model.P3.DataCommonGetPushAppGpsConfig;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataNotifyDisconnect;
import dji.midware.data.model.P3.DataUpgradeSelfRequest;
import dji.midware.data.model.b.c;

public class d implements dji.midware.e.a {

    public enum a {
        GetVersion(1),
        SetMultiParam(5),
        RequestUpgrade(7),
        RequestReceiveData(8),
        TranslateData(9),
        TranslateComplete(10),
        RestartDevice(11),
        GetDeviceStatus(12),
        HeartBeat(14, false, c.class),
        UpgradeSelfRequest(15, false, DataUpgradeSelfRequest.class),
        GetPushLog(240, false, null),
        RequestSendFiles(34),
        AckReceiveFiles(35, false, null),
        GetPushFiles(36, false, DataCameraGetPushFiles.class),
        SetResendFiles(37),
        RequestFile(38),
        GetPushFile((String) 39, (int) false, (int) DataCameraGetPushFile.class, (boolean) DataCameraGetPushFile.getInstance()),
        DeleteFile(40),
        ActiveStatus((String) 50, (int) false, (int) true, (boolean) null),
        GetPushRequestUpgrade(64, false, null),
        ControlUpgrade(65),
        GetPushUpgradeStatus(66, false, DataCommonGetPushUpgradeStatus.class),
        AckUpgradeStop(67, false, null),
        GetPushAppGpsConfig(82, false, DataCommonGetPushAppGpsConfig.class),
        SetAppGpsCyclic(83),
        NotifyDisconnect(71, false, DataNotifyDisconnect.class),
        SetDate(74),
        GetCfgFile(79),
        GetPushCheckStatus(241, false, null),
        NewGetPushCheckStatus(251, false, null),
        GetDeviceInfo(255),
        Other(511);
        
        private static a[] L;
        private int G;
        private boolean H;
        private boolean I;
        private Class<? extends n> J;
        private n K;

        static {
            L = values();
        }

        private a(int i) {
            this.H = true;
            this.I = false;
            this.G = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.H = true;
            this.I = false;
            this.G = i;
            this.H = z;
            this.J = cls;
        }

        private a(int i, boolean z, boolean z2, Class<? extends n> cls) {
            this.H = true;
            this.I = false;
            this.G = i;
            this.I = z2;
            this.H = z;
            this.J = cls;
        }

        private a(int i, boolean z, Class<? extends n> cls, n nVar) {
            this.H = true;
            this.I = false;
            this.G = i;
            this.H = z;
            this.J = cls;
            this.K = nVar;
        }

        public int a() {
            return this.G;
        }

        public boolean b() {
            return this.I;
        }

        public boolean c() {
            return this.H;
        }

        public Class<? extends n> d() {
            return this.J;
        }

        public n e() {
            return this.K;
        }

        public boolean a(int i) {
            return this.G == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < L.length; i2++) {
                if (L[i2].a(i)) {
                    return L[i2];
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
        String str;
        if (i2 == a.GetPushCheckStatus.a() || i2 == a.NewGetPushCheckStatus.a()) {
            i2 = a.GetPushCheckStatus.a();
            if (i == DeviceType.OFDM.value()) {
                str = "OFDM";
            } else {
                str = DeviceType.find(i).toString();
            }
        } else {
            str = DeviceType.find(i).toString();
        }
        return r.getDataModelName(str, a.find(i2).toString());
    }

    public String a(int i, int i2, int i3) {
        if (i3 == a.GetPushCheckStatus.a() || i3 == a.NewGetPushCheckStatus.a()) {
            i3 = a.GetPushCheckStatus.a();
            if (i == DeviceType.DM368.value()) {
                if (1 == i2) {
                    return r.getDataModelNameNon("1860", a.find(i3).toString());
                }
            } else if (i == DeviceType.DOUBLE.value() && i2 == 0) {
                return r.getDataModelNameNon("2100", a.find(i3).toString());
            }
        }
        return a(i, i3);
    }

    public n e(int i) {
        return a.find(i).e();
    }
}
