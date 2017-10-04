package dji.phone.tracking;

import dji.midware.data.model.P3.DataGimbalSpeedControl;

public class a {
    private static final String c = "DJILPGimbalControler";
    public int a;
    public int b;

    public void a() {
        DataGimbalSpeedControl.getInstance().setPermission(true).setYaw(this.a).setPitch(this.b).start();
    }

    public void b() {
        DataGimbalSpeedControl.getInstance().setPermission(false).start();
    }

    public a a(int i, int i2) {
        this.a = i;
        this.b = i2;
        return this;
    }
}
