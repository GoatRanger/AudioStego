package dji.a;

import android.content.Context;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClient;
import com.dji.frame.c.b;
import com.google.android.gms.common.GoogleApiAvailability;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.common.MapSettings;
import com.here.odnp.config.OdnpConfigStatic;
import dji.pilot.usercenter.mode.n;
import java.util.Locale;

public class a {
    private static a a = null;
    private static final int g = 120000;
    private static final int h = 40;
    private static final int i = 300;
    private static boolean j = false;
    private static boolean k = false;
    private static int n = 1;
    private boolean b = false;
    private LocationManager c = null;
    private AMapLocationClient d = null;
    private boolean e = true;
    private boolean f = false;
    private Context l = null;
    private boolean m = false;

    static /* synthetic */ int g() {
        int i = n;
        n = i + 1;
        return i;
    }

    public static a getInstance() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public void a(Context context, boolean z) {
        this.l = context;
        this.b = z;
        this.c = (LocationManager) context.getSystemService(n.C);
        this.d = new AMapLocationClient(context.getApplicationContext());
        dji.gs.utils.a.a(context);
        b(context, true);
        if (j) {
            b(context);
        }
    }

    public boolean a() {
        return j && k;
    }

    public boolean b() {
        return this.m;
    }

    private void b(Context context, boolean z) {
        boolean z2 = true;
        String language;
        boolean z3;
        Location e;
        if (this.b) {
            language = Locale.getDefault().getLanguage();
            z3 = !language.contains("zh") && (z || k);
            this.f = z3;
            j = this.f;
            e = e();
            if (e == null || e.getAccuracy() > 300.0f) {
                if (!this.f || (!(z || k) || language.contains("ja"))) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                j = z3;
                if (language.contains("ja")) {
                    j = false;
                    this.f = false;
                    this.m = true;
                }
            } else {
                if (!this.f || (!(z || k) || (language.contains("ja") && dji.gs.utils.a.b(e.getLatitude(), e.getLongitude())))) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                j = z3;
                if (language.contains("ja") || dji.gs.utils.a.b(e.getLatitude(), e.getLongitude())) {
                    j = false;
                    this.f = false;
                    this.m = true;
                }
            }
        } else {
            z3 = b.a(context, "com.google.android.gms") && GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0;
            this.f = z3;
            language = Locale.getDefault().getLanguage();
            e = e();
            if (e == null || e.getAccuracy() > 300.0f) {
                if (!this.f || (!(z || k) || language.contains("ja"))) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                j = z3;
                if (language.contains("ja")) {
                    if (this.f) {
                        z2 = false;
                    }
                    j = z2;
                    this.m = false;
                }
            } else {
                if (!this.f || (!(z || k) || (language.contains("ja") && dji.gs.utils.a.b(e.getLatitude(), e.getLongitude())))) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                j = z3;
                if (language.contains("ja") || dji.gs.utils.a.b(e.getLatitude(), e.getLongitude())) {
                    if (this.f) {
                        z2 = false;
                    }
                    j = z2;
                    this.m = false;
                }
            }
        }
        if (!j || !this.f) {
            this.e = this.f;
        }
    }

    public void a(Context context) {
        b(context, false);
    }

    private void b(Context context) {
        MapSettings.setIsolatedDiskCacheRootPath(context.getFilesDir() + "/.HereMap", "com.here.android.mpa.service.MapService." + context.getPackageName());
        MapEngine.getInstance().init(context, new 1(this, context));
    }

    @Deprecated
    public boolean c() {
        return this.e;
    }

    public boolean d() {
        return this.f || a();
    }

    public Location e() {
        return this.d.getLastKnownLocation();
    }

    public Location a(Location location, Location location2) {
        return a(location, location2, OdnpConfigStatic.OEM_MAX_HIGH_POWER_INTERVAL);
    }

    public Location a(Location location, Location location2, long j) {
        if (location == null && location2 == null) {
            return null;
        }
        if (location == null && location2 != null) {
            return location2;
        }
        if (location2 == null && location != null) {
            return location;
        }
        if (location.getAccuracy() == 0.0f && location2.getAccuracy() == 0.0f) {
            return location2;
        }
        if (location.getAccuracy() == 0.0f && location2.getAccuracy() != 0.0f) {
            return location2;
        }
        if (location2.getAccuracy() == 0.0f && location.getAccuracy() != 0.0f) {
            return location;
        }
        if (location.getAccuracy() >= 40.0f || location2.getAccuracy() >= 40.0f) {
            if (location.getAccuracy() >= location2.getAccuracy()) {
                location = location2;
            }
        } else if (TextUtils.equals(location.getProvider(), location2.getProvider())) {
            if (location.getTime() >= location2.getTime()) {
                return location;
            }
            return location2;
        } else if ("gps".equalsIgnoreCase(location.getProvider())) {
            return location;
        } else {
            if ("gps".equalsIgnoreCase(location2.getProvider())) {
                return location2;
            }
            if (location.getAccuracy() >= location2.getAccuracy()) {
                location = location2;
            }
        }
        return location;
    }

    public boolean a(String str) {
        return this.c.isProviderEnabled(str);
    }

    public GpsStatus a(GpsStatus gpsStatus) {
        return this.c.getGpsStatus(gpsStatus);
    }
}
