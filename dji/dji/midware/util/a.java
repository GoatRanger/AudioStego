package dji.midware.util;

import android.util.Log;

public class a {
    public static String a = "";
    private b b = new b();
    private int c;
    private int d;
    private int e;
    private int f;
    private int[] g = new int[2];
    private int[] h = new int[2];
    private a i;

    public interface a {
        void a(int i, int i2, int i3, int i4);
    }

    public static class b {
        public static final float a = 1.7777778f;
        public static final float b = 1.3333334f;
        public static final float c = 1.5f;
        public static final float d = 1.6f;
        private a e = a.Ratio16X9;
        private b f = b.Ratio16X9;
        private boolean g = false;
        private int h = 0;
        private int i = 0;

        public enum a {
            Ratio16X9(b.a),
            Ratio16X10(b.d),
            Ratio4X3(b.b);
            
            private float d;

            private a(float f) {
                this.d = f;
            }

            public float a() {
                return this.d;
            }
        }

        public enum b {
            Ratio16X9(b.a),
            Ratio3X2(b.c),
            Ratio4X3(b.b),
            Ratio4X3_IN16X9(b.b, b.a),
            Ratio3X2_IN16X9(b.c, b.a);
            
            private float f;
            private float g;

            private b(float f) {
                this.f = f;
                this.g = f;
            }

            private b(float f, float f2) {
                this.f = f2;
                this.g = f;
            }

            public float a() {
                return this.f;
            }

            public float b() {
                return this.g;
            }

            public boolean c() {
                return this.f == this.g;
            }
        }

        public void a(int i, int i2) {
            this.h = i;
            this.i = i2;
            float f = (((float) i) * 1.0f) / ((float) i2);
            if (Math.abs(f - a) < Math.abs(f - b)) {
                this.e = f == d ? a.Ratio16X10 : a.Ratio16X9;
            } else {
                this.e = a.Ratio4X3;
            }
        }

        public a a() {
            return this.e;
        }

        public void a(b bVar) {
            this.f = bVar;
        }

        public b b() {
            return this.f;
        }

        public void a(boolean z) {
            this.g = z;
        }

        public boolean c() {
            return this.g;
        }

        public int d() {
            return this.h;
        }

        public int e() {
            return this.i;
        }
    }

    public b a() {
        return this.b;
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public void b() {
        boolean i = i();
        if (this.i != null && i) {
            a = "videoWidth=" + this.c + ", videoHeight=" + this.d + ", videoType=" + this.b.f + ", screenType=" + this.b.e;
            Log.e("calc", a);
            int c = (int) (((float) (this.b.h - this.e)) / 2.0f);
            this.g[0] = c;
            this.g[1] = c + this.e;
            c = (int) (((float) (this.b.i - this.f)) / 2.0f);
            this.h[0] = c;
            this.h[1] = c + this.f;
            this.i.a(this.c, this.d, this.e, this.f);
        }
    }

    private boolean i() {
        int i = this.d;
        int i2 = this.c;
        int i3 = this.f;
        int i4 = this.e;
        int j = j();
        if (this.b.g) {
            this.c = (int) (((float) this.b.i) / this.b.f.b());
            this.d = (int) ((((float) this.b.i) / this.b.f.b()) * this.b.f.a());
            this.e = this.c;
            this.f = this.b.i;
        } else if (j == 1) {
            this.d = this.b.i;
            this.c = (int) (((float) this.d) * this.b.f.a());
            this.f = this.d;
            this.e = (int) (((float) this.f) * this.b.f.b());
        } else if (j == 2) {
            this.e = this.b.h;
            this.f = (int) (((float) this.e) / this.b.f.b());
            this.d = this.f;
            this.c = (int) (((float) this.d) * this.b.f.a());
        } else {
            this.c = this.b.h;
            this.d = (int) (((float) this.c) / this.b.f.a());
            this.e = (int) ((((float) this.c) * this.b.f.b()) / this.b.f.a());
            this.f = (int) (((float) this.e) / this.b.f.b());
        }
        Log.e("calc", "calcSizeNormal result=" + j);
        if (i == this.d && i2 == this.c && i3 == this.f && i4 == this.e) {
            return false;
        }
        return true;
    }

    private int j() {
        if (!this.b.f.c()) {
            float b = this.b.f.b();
            float a = this.b.e.a();
            if (b != a) {
                return b > a ? 2 : 0;
            } else {
                return 1;
            }
        } else if (this.b.f.b() < this.b.e.a()) {
            return 1;
        } else {
            return 0;
        }
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public int[] g() {
        return this.g;
    }

    public int[] h() {
        return this.h;
    }
}
