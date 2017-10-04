package dji.midware.data.config.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRTKPushStatus;

public class j implements dji.midware.e.a {

    public enum a {
        PushStatus(9, false, false, DataRTKPushStatus.class),
        Other(511);
        
        private static a[] h;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        private Class<? extends n> g;

        static {
            h = values();
        }

        private a(int i) {
            this.d = true;
            this.e = false;
            this.f = false;
            this.c = i;
        }

        private a(int i, boolean z, boolean z2, Class<? extends n> cls) {
            this.d = true;
            this.e = false;
            this.f = false;
            this.c = i;
            this.d = z;
            this.g = cls;
            this.e = z2;
        }

        private a(int i, boolean z) {
            this.d = true;
            this.e = false;
            this.f = false;
            this.c = i;
            this.f = z;
        }

        public int a() {
            return this.c;
        }

        public boolean b() {
            return this.d;
        }

        public boolean c() {
            return this.e;
        }

        public Class<? extends n> d() {
            return this.g;
        }

        public boolean e() {
            return this.f;
        }

        public boolean a(int i) {
            return this.c == i;
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
