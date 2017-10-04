package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;

public class c implements dji.midware.e.a {

    public enum a {
        SetBatteryCommon(5),
        GetPushBatteryCommon(6, false, DataCenterGetPushBatteryCommon.class),
        GetSmartBatteryCurrentStatus(7),
        GetBatteryHistory(8),
        SelfDischarge(9),
        GetSelfDischarge(49),
        SetSelfDischarge(50),
        GetBoardNumber(51),
        Other(511);
        
        private static a[] m;
        private int j;
        private boolean k;
        private Class<? extends n> l;

        static {
            m = values();
        }

        private a(int i) {
            this.k = true;
            this.j = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.k = true;
            this.j = i;
            this.k = z;
            this.l = cls;
        }

        public int a() {
            return this.j;
        }

        public boolean b() {
            return this.k;
        }

        public Class<? extends n> c() {
            return this.l;
        }

        public boolean a(int i) {
            return this.j == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < m.length; i2++) {
                if (m[i2].a(i)) {
                    return m[i2];
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
