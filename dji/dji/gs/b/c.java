package dji.gs.b;

import android.content.Context;
import dji.gs.R;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.util.i;
import java.util.ArrayList;

public class c {
    public static final int a = 2;
    private static final String f = "key_warn_area_action";
    private static c g = null;
    private Context b;
    private String[] c;
    private int[] d;
    private ArrayList<Integer> e = new ArrayList();

    public enum a {
        WARN(0),
        WARN_SHOW(1),
        IGNORE(2);
        
        private int d;

        private a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    private c(Context context) {
        this.b = context;
        this.c = this.b.getResources().getStringArray(R.array.nfz_fly_warn_zone_type);
        this.d = this.b.getResources().getIntArray(R.array.nfz_fly_warn_zone_type_index);
        d();
    }

    private void d() {
        for (int i = 0; i != this.d.length; i++) {
            this.e.add(Integer.valueOf(i.b(this.b, f + this.d[i], a.WARN.a())));
        }
    }

    public static c getInstance(Context context) {
        if (g == null) {
            g = new c(context);
        }
        return g;
    }

    public String[] a() {
        return this.c;
    }

    public int[] b() {
        return this.d;
    }

    public int a(int i) {
        for (int i2 = 0; i2 != this.d.length; i2++) {
            if (i == this.d[i2]) {
                return i2;
            }
        }
        return 0;
    }

    public int b(int i) {
        if (i.b(this.b, DJIFlyForbidController.KEY_CUR_USE_GEO_SYSTEM, true)) {
            return ((Integer) this.e.get(a(i))).intValue();
        }
        return a.WARN_SHOW.a();
    }

    public ArrayList<Integer> c() {
        return this.e;
    }

    public void a(int i, int i2) {
        i.a(this.b, f + this.d[i], i2);
        this.e.set(i, Integer.valueOf(i2));
    }
}
