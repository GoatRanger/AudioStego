package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataDm368GetPushStatus;

public class e implements dji.midware.e.a {

    public enum a {
        SetGParams(1),
        GetGParams(2),
        SetParams(3),
        GetParams(4),
        GetPushStatus(6, false, DataDm368GetPushStatus.class),
        SetWifiCodeRate(32),
        GetWifiCurCodeRate(33),
        SetForesightShowed(48),
        GetForesightShowed(49),
        Other(511);
        
        private static a[] n;
        private int k;
        private boolean l;
        private Class<? extends n> m;

        static {
            n = values();
        }

        private a(int i) {
            this.l = true;
            this.k = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.l = true;
            this.k = i;
            this.l = z;
            this.m = cls;
        }

        public int a() {
            return this.k;
        }

        public boolean b() {
            return this.l;
        }

        public Class<? extends n> c() {
            return this.m;
        }

        public boolean a(int i) {
            return this.k == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < n.length; i2++) {
                if (n[i2].a(i)) {
                    return n[i2];
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
