package dji.sdksharedlib.hardware.abstractions;

public class c {
    private static final int f = 1000;
    public String a;
    public DJISDKCacheUpdateType b;
    public Class c;
    public Class[] d;
    public int e = 1000;
    private int g;

    public static class a {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 4;
        public static final int d = 8;
        public static final int e = 16;
    }

    public c(String str, int i, DJISDKCacheUpdateType dJISDKCacheUpdateType, Class... clsArr) {
        this.a = str;
        this.g = i;
        this.b = dJISDKCacheUpdateType;
        if (clsArr == null || clsArr.length <= 1) {
            if ((i & 4) == 4) {
                this.e = 0;
            }
            if (clsArr == null || clsArr.length == 0) {
                this.c = null;
                return;
            } else {
                this.c = clsArr[0];
                return;
            }
        }
        this.d = clsArr;
    }

    public boolean a() {
        return (this.g & 1) == 1;
    }

    public boolean b() {
        return (this.g & 2) == 2;
    }

    public boolean c() {
        return (this.g & 4) == 4;
    }

    public boolean a(Object... objArr) {
        if (this.d != null) {
            return b(objArr);
        }
        if (objArr.length == 0) {
            return a(null);
        }
        return a(objArr[0]);
    }

    private boolean a(Object obj) {
        if (obj != null) {
            return this.c.equals(obj.getClass());
        }
        if (this.c != null && this.c.isPrimitive()) {
            return false;
        }
        return true;
    }

    private boolean b(Object... objArr) {
        boolean z = true;
        if (objArr == null) {
            if (this.d != null) {
                z = false;
            }
            return z;
        } else if (objArr.length != this.d.length) {
            return false;
        } else {
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] == null) {
                    if (this.d[i].isPrimitive()) {
                        return false;
                    }
                } else if (objArr[i].getClass() != this.d[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean d() {
        return this.b == DJISDKCacheUpdateType.USER_DRIVEN;
    }

    public int e() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }
}
