package dji.midware.data.config.P3;

import dji.midware.data.model.P3.DataNewSpecialControl;
import dji.midware.data.model.P3.DataOldSpecialControl;

public class n implements dji.midware.e.a {

    public enum a {
        Control(1, false, DataOldSpecialControl.class),
        JoySitckSetParams(2),
        NewControl(3, false, DataNewSpecialControl.class),
        Other(511);
        
        private static a[] h;
        private int e;
        private boolean f;
        private Class<? extends dji.midware.data.manager.P3.n> g;

        static {
            h = values();
        }

        private a(int i) {
            this.f = true;
            this.e = i;
        }

        private a(int i, boolean z, Class<? extends dji.midware.data.manager.P3.n> cls) {
            this.f = true;
            this.e = i;
            this.f = z;
            this.g = cls;
        }

        public int a() {
            return this.e;
        }

        public boolean b() {
            return this.f;
        }

        public Class<? extends dji.midware.data.manager.P3.n> c() {
            return this.g;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < h.length; i2++) {
                if (h[i2].a(i)) {
                    return h[i2];
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

    public Class<? extends dji.midware.data.manager.P3.n> d(int i) {
        return a.find(i).c();
    }

    public String a(int i, int i2) {
        return r.getDataModelName(DeviceType.find(i).toString(), a.find(i2).toString());
    }

    public String a(int i, int i2, int i3) {
        return a(i, i3);
    }

    public dji.midware.data.manager.P3.n e(int i) {
        return null;
    }
}
