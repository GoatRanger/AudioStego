package dji.pilot.fpv.camera.more;

import dji.pilot.R;

public enum d$a {
    WHITE(0, R.color.om),
    YELLOW(1, R.color.gq),
    RED(2, R.color.gp),
    BLUE(3, R.color.gm),
    GREEN(4, R.color.go),
    BLACK(5, R.color.a_);
    
    private int g;
    private int h;

    private d$a(int i, int i2) {
        this.g = 0;
        this.h = R.color.om;
        this.g = i;
        this.h = i2;
    }

    public int a() {
        return this.h;
    }

    public int b() {
        return this.g;
    }

    private boolean a(int i) {
        return this.g == i;
    }

    public static d$a find(int i) {
        for (d$a dji_pilot_fpv_camera_more_d_a : values()) {
            if (dji_pilot_fpv_camera_more_d_a.a(i)) {
                return dji_pilot_fpv_camera_more_d_a;
            }
        }
        return WHITE;
    }
}
