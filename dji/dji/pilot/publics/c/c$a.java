package dji.pilot.publics.c;

import dji.pilot.R;

public enum c$a {
    Disconnect(R.string.fpv_tip_disconnect, 0),
    SignalWeak(R.string.fpv_tip_no_video_signal, 9),
    Normal(R.string.no_value, 100);
    
    private int d;
    private int e;

    private c$a(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.d;
    }

    public boolean a(int i) {
        return this.d == i;
    }

    public static c$a find(int i) {
        c$a dji_pilot_publics_c_c_a = Normal;
        c$a[] values = values();
        for (int i2 = 0; i2 < values.length; i2++) {
            if (values[i2].a(i)) {
                return values[i2];
            }
        }
        return dji_pilot_publics_c_c_a;
    }
}
