package dji.gs.d;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import dji.gs.c.e;
import dji.gs.e.b;
import dji.gs.utils.a;

public class d implements LocationListener {
    private b a;

    public d(e eVar) {
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public b a() {
        return this.a;
    }

    public void onLocationChanged(Location location) {
        Log.d("", "onLocationChanged");
        this.a = a.a(new b(location.getLatitude(), location.getLongitude()));
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }
}
