package dji.pilot2.multimoment.videolib;

public enum b {
    SingleEdit(0),
    MultiEdit_Intelligent(1),
    MultiEdit_Normal(2),
    MultiEdit_DP(3),
    MultiEdit_tmp(4);
    
    private int f;

    private b(int i) {
        this.f = i;
    }

    public String toString() {
        return super.toString();
    }

    public int a() {
        return this.f;
    }

    public boolean a(int i) {
        return this.f == i;
    }

    public static b find(int i) {
        b bVar = SingleEdit;
        b[] values = values();
        for (int i2 = 0; i2 < values.length; i2++) {
            if (values[i2].a(i)) {
                return values[i2];
            }
        }
        return bVar;
    }
}
