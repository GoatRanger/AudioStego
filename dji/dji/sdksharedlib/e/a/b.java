package dji.sdksharedlib.e.a;

import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;

public class b {
    public int a;
    public Number b;
    public Number c;
    public Number d;
    public int e;
    public DJISDKCacheUpdateType f;
    public String g;
    public String h;
    public int i;
    public int j;
    public String k;
    public Class l;

    public static class a {
        private int a;
        private Number b;
        private Number c;
        private Number d;
        private int e;
        private DJISDKCacheUpdateType f;
        private String g;
        private String h;
        private int i;
        private int j;
        private String k;
        private Class l;

        public b a() {
            return new b(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l);
        }

        public a a(Class cls) {
            this.l = cls;
            return this;
        }

        public a a(DJISDKCacheUpdateType dJISDKCacheUpdateType) {
            this.f = dJISDKCacheUpdateType;
            return this;
        }

        public a a(String str) {
            this.k = str;
            return this;
        }

        public a b(String str) {
            this.g = str;
            return this;
        }

        public a c(String str) {
            this.h = str;
            return this;
        }

        public a a(int i) {
            this.i = i;
            return this;
        }

        public a b(int i) {
            this.j = i;
            return this;
        }

        public a c(int i) {
            this.a = i;
            return this;
        }

        public a d(int i) {
            this.e = i;
            return this;
        }

        public a a(Number number) {
            this.b = number;
            return this;
        }

        public a b(Number number) {
            this.c = number;
            return this;
        }

        public a c(Number number) {
            this.d = number;
            return this;
        }
    }

    public b(int i, Number number, Number number2, Number number3, int i2, DJISDKCacheUpdateType dJISDKCacheUpdateType, String str, String str2, int i3, int i4, String str3, Class cls) {
        this.a = i;
        this.b = number;
        this.c = number2;
        this.d = number3;
        this.e = i2;
        this.f = dJISDKCacheUpdateType;
        this.g = str;
        this.h = str2;
        this.i = i3;
        this.j = i4;
        this.k = str3;
        this.l = cls;
    }
}
