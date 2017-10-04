package dji.logic.f;

import android.location.Location;
import dji.midware.data.model.P3.DataWifiSetPowerMode;
import dji.midware.data.model.P3.DataWifiSetPowerMode.DJIWifiPowerMode;
import dji.midware.e.d;
import java.util.ArrayList;
import java.util.Iterator;

public class a {
    private static ArrayList<a> a = new ArrayList();

    private static class a {
        protected double a;
        protected double b;
        protected int c;

        public a(double d, double d2, int i) {
            this.a = d;
            this.b = d2;
            this.c = i;
        }
    }

    static {
        a.add(new a(55.529627d, 15.702531d, 2824707));
        a.add(new a(-29.63077d, 24.718358d, 1038280));
        a.add(new a(18.20013d, 78.10576d, 1585814));
        a.add(new a(24.41194d, 93.17285d, 1044521));
        a.add(new a(-23.950101d, 135.518546d, 4285467));
        a.add(new a(35.628727d, 129.950682d, 643505));
    }

    public static boolean a(double d, double d2) {
        float[] fArr = new float[3];
        Iterator it = a.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            Location.distanceBetween(aVar.a, aVar.b, d, d2, fArr);
            if (fArr[0] <= ((float) aVar.c)) {
                return true;
            }
        }
        return false;
    }

    public static void a(boolean z, d dVar) {
        DJIWifiPowerMode dJIWifiPowerMode;
        if (z) {
            dJIWifiPowerMode = DJIWifiPowerMode.CE;
        } else {
            dJIWifiPowerMode = DJIWifiPowerMode.FCC;
        }
        DataWifiSetPowerMode.getInstance().a(dJIWifiPowerMode).start(dVar);
    }
}
