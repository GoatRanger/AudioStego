package dji.pilot.groundStation.a;

import dji.midware.data.model.P3.DataFlycGetPushRTKLocationData;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.fpv.flightmode.c;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.flightmode.c$d;

public class b {
    private static b a = null;
    private DataFlycGetPushRTKLocationData b = DataFlycGetPushRTKLocationData.getInstance();
    private DataOsdGetPushCommon c = DataOsdGetPushCommon.getInstance();

    public static b getInstance() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    private b() {
    }

    private boolean d() {
        return this.b.isRTKConnected() && this.b.isRTKCanbeUsed();
    }

    public double a() {
        if (e() && d()) {
            return this.b.getLongitude();
        }
        return this.c.getLongitude();
    }

    public double b() {
        if (e() && d()) {
            return this.b.getLatitude();
        }
        return this.c.getLatitude();
    }

    public float c() {
        if (d()) {
            return this.b.getHeight();
        }
        return 0.1f * ((float) this.c.getHeight());
    }

    private boolean e() {
        if (c.getInstance().a() != c$b.SMART) {
            return false;
        }
        c$d c = c.getInstance().c();
        if (c == c$d.POI_AUTO || c == c$d.WP_AUTO) {
            return true;
        }
        return false;
    }
}
