package antistatic.spinnerwheel.a;

import android.content.Context;

public class d extends b {
    public static final int k = 9;
    private static final int l = 0;
    private int m;
    private int n;
    private String o;

    public d(Context context) {
        this(context, 0, 9);
    }

    public d(Context context, int i, int i2) {
        this(context, i, i2, null);
    }

    public d(Context context, int i, int i2, String str) {
        super(context);
        this.m = i;
        this.n = i2;
        this.o = str;
    }

    public void g(int i) {
        this.m = i;
        b();
    }

    public void h(int i) {
        this.n = i;
        b();
    }

    public CharSequence f(int i) {
        if (i < 0 || i >= h()) {
            return null;
        }
        int i2 = this.m + i;
        if (this.o == null) {
            return Integer.toString(i2);
        }
        return String.format(this.o, new Object[]{Integer.valueOf(i2)});
    }

    public int h() {
        return (this.n - this.m) + 1;
    }
}
