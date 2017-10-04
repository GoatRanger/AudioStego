package dji.thirdparty.g.b.b.a;

public interface g {
    public static final int fN_ = -1;
    public static final int fO_ = 0;
    public static final int fP_ = 1;
    public static final int fQ_ = 1;
    public static final int fR_ = 2;
    public static final int fS_ = 3;
    public static final int fT_ = 2;
    public static final a fU_ = new a(0, "IFD0");
    public static final a fV_ = fU_;
    public static final a fW_ = new a(1, "IFD1");
    public static final a fX_ = new a(2, "IFD2");
    public static final a fY_ = new a(3, "IFD3");
    public static final a mA = new b(-3, "GPS IFD");
    public static final a mB = null;
    public static final a[] mC = new a[]{fV_, mz, fU_, mm, fW_, mp, fX_, mr, mx, my, mu, mv, mw, mA};
    public static final int mc = -2;
    public static final int md = -3;
    public static final int me = -4;
    public static final int mf = -5;
    public static final int mg = 0;
    public static final int mh = 1;
    public static final int mi = 2;
    public static final int mj = 3;
    public static final int mk = 4;
    public static final a mm = fU_;
    public static final a mp = fW_;
    public static final a mr = fX_;
    public static final a mt = fY_;
    public static final a mu = fW_;
    public static final a mv = fX_;
    public static final a mw = fY_;
    public static final a mx = new b(-4, "Interop IFD");
    public static final a my = new b(-5, "Maker Notes");
    public static final a mz = new b(-2, "Exif IFD");

    public static abstract class a {
        public final int a;
        public final String b;

        public static class a extends a {
            public a(int i, String str) {
                super(i, str);
            }

            public boolean a() {
                return true;
            }
        }

        public static class b extends a {
            public b(int i, String str) {
                super(i, str);
            }

            public boolean a() {
                return false;
            }
        }

        public abstract boolean a();

        public a(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }
}
