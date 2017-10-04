package dji.thirdparty.g.b.b;

import java.util.Comparator;

public abstract class d {
    public static final Comparator gx_ = new Comparator() {
        public int compare(Object obj, Object obj2) {
            return ((d) obj).gv_ - ((d) obj2).gv_;
        }
    };
    public final int gv_;
    public final int gw_;

    public static abstract class a extends d {
        public final byte[] a;

        public a(int i, int i2, byte[] bArr) {
            super(i, i2);
            this.a = bArr;
        }
    }

    public static final class b extends d {
        public b(int i, int i2) {
            super(i, i2);
        }

        public String a(boolean z) {
            return "Element, offset: " + this.gv_ + ", length: " + this.gw_ + ", last: " + (this.gv_ + this.gw_) + "";
        }
    }

    public abstract String a(boolean z);

    public d(int i, int i2) {
        this.gv_ = i;
        this.gw_ = i2;
    }

    public String j() {
        return a(false);
    }
}
