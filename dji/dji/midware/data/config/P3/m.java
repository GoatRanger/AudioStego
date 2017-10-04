package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataSmartBatteryGetPushReArrangement;

public class m implements dji.midware.e.a {

    public enum a {
        GetStaticData(1),
        GetPushDynamicData(2, false, true, DataSmartBatteryGetPushDynamicData.class),
        GetPushCellVoltage(3, false, true, DataSmartBatteryGetPushCellVoltage.class),
        GetBarCode(4),
        GetHistory(5),
        GetSetSelfDischargeDays(17),
        ShutDown(18),
        ForceShutDown(19),
        StartUp(20),
        DataRecordControl(34),
        Authentication(35),
        GetPushReArrangement(49, false, false, DataSmartBatteryGetPushReArrangement.class),
        GetMultBatteryInfo(50),
        Other(511);
        
        private static a[] t;
        private int o;
        private boolean p;
        private boolean q;
        private boolean r;
        private Class<? extends n> s;

        static {
            t = values();
        }

        private a(int i) {
            this.p = true;
            this.q = false;
            this.r = false;
            this.o = i;
        }

        private a(int i, boolean z, boolean z2, Class<? extends n> cls) {
            this.p = true;
            this.q = false;
            this.r = false;
            this.o = i;
            this.p = z;
            this.s = cls;
            this.q = z2;
        }

        private a(int i, boolean z) {
            this.p = true;
            this.q = false;
            this.r = false;
            this.o = i;
            this.r = z;
        }

        public int a() {
            return this.o;
        }

        public boolean b() {
            return this.p;
        }

        public boolean c() {
            return this.q;
        }

        public Class<? extends n> d() {
            return this.s;
        }

        public boolean e() {
            return this.r;
        }

        public boolean a(int i) {
            return this.o == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < t.length; i2++) {
                if (t[i2].a(i)) {
                    return t[i2];
                }
            }
            return aVar;
        }
    }

    public boolean a(int i) {
        return a.find(i).c();
    }

    public boolean b(int i) {
        return a.find(i).b();
    }

    public boolean c(int i) {
        return a.find(i).e();
    }

    public Class<? extends n> d(int i) {
        return a.find(i).d();
    }

    public String a(int i, int i2, int i3) {
        return r.getDataModelName(DeviceType.find(i).toString(), a.find(i3).toString());
    }

    public n e(int i) {
        return null;
    }
}
