package dji.pilot2.account.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.j;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.thirdparty.a.c;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class b implements c$m, dji.pilot2.account.profile.a.a {
    private static final String G = "DJIEditProfilePresenter";
    private static final String H = "https://maps.google.com/maps/api/geocode/json?latlng=";
    private static final String P = "DJI_Temp/tmp_avatar.png";
    private dji.pilot2.account.profile.a.b I;
    private Context J;
    private boolean K = false;
    private boolean L = false;
    private Bitmap M = null;
    private int N;
    private int O;
    private e$a Q = new e$a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(int i, int i2, int i3, Object obj, Object obj2) {
            switch (i) {
                case d.o /*196656*/:
                    f.getInstance().g();
                    this.a.j();
                    return;
                case d.p /*196672*/:
                    this.a.L = false;
                    this.a.i();
                    return;
                case d.q /*196688*/:
                    if (this.a.K) {
                        dji.pilot.usercenter.f.a.a(this.a.d(), this.a.N, this.a.O, f.getInstance().e(), true);
                        c.a().e(dji.pilot2.account.profile.DJIEditProfileActivity.a.TRUE);
                        this.a.K = false;
                        this.a.i();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void a(int i, int i2, int i3, Object obj) {
            String str = null;
            Log.i(b.G, "onFail:cmdid=" + i + ";errorCode=" + i2);
            switch (i) {
                case d.o /*196656*/:
                    str = this.a.J.getString(R.string.v2_get_profile_info_fail);
                    break;
                case d.p /*196672*/:
                    str = this.a.J.getString(R.string.v2_save_profile_info_fail);
                    break;
                case d.q /*196688*/:
                    str = this.a.J.getString(R.string.v2_save_profile_info_fail);
                    break;
            }
            this.a.I.a(false, str);
        }

        public void a(int i, long j, long j2, int i2, Object obj) {
        }

        public void a(int i, boolean z, int i2, Object obj) {
        }
    };
    private LocationListener R = new LocationListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onLocationChanged(Location location) {
            DJILogHelper.getInstance().LOGD(b.G, "onLocationChanged");
            LocationManager locationManager = (LocationManager) this.a.J.getSystemService(n.C);
            this.a.a(false);
            locationManager.removeUpdates(this);
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            DJILogHelper.getInstance().LOGD(b.G, "onStatusChanged");
        }

        public void onProviderEnabled(String str) {
            DJILogHelper.getInstance().LOGD(b.G, "onProviderEnabled");
        }

        public void onProviderDisabled(String str) {
            DJILogHelper.getInstance().LOGD(b.G, "onProviderDisabled");
        }
    };

    public enum a {
        TRUE,
        FALSE
    }

    public b(dji.pilot2.account.profile.a.b bVar, Context context) {
        this.I = bVar;
        this.J = context;
        this.I.a(this);
    }

    public void a(String str, int i, String str2, String str3) {
        f instance = f.getInstance();
        HashMap hashMap = new HashMap();
        instance.h().q = str;
        hashMap.put("name", str);
        hashMap.put(n.aG, b(i));
        hashMap.put("country", str3);
        e.b(c$m.dw_);
        f.getInstance().a(hashMap);
        if (this.K) {
            e.b(c$m.dv_);
            k();
        }
        if (hashMap.isEmpty() && !this.K) {
            this.I.a(true, null);
        }
    }

    private String b(int i) {
        switch (i) {
            case 1:
                return n.aY;
            case 2:
                return n.aZ;
            default:
                return n.ba;
        }
    }

    public void a(int i) {
        f.getInstance().a(this.Q);
        f();
    }

    public void a(String str, int i, int i2) {
        dji.pilot.usercenter.f.a.a(str, i, i2, d(), true);
        e();
        this.K = true;
    }

    private String d() {
        return com.dji.frame.c.d.a(this.J, P);
    }

    public void a(Uri uri, int i, int i2) {
        dji.pilot.usercenter.f.a.a(this.J, uri, i, i2, d(), true);
        e();
        this.K = true;
    }

    public void a(int i, int i2) {
        this.O = i2;
        this.N = i;
    }

    public void a() {
        f.getInstance().b(this.Q);
        c();
    }

    private void e() {
        if (!(this.M == null || this.M.isRecycled())) {
            this.M.recycle();
        }
        this.M = BitmapFactory.decodeFile(d());
        this.I.a(this.M);
    }

    private void f() {
        h();
        j();
    }

    private void g() {
        dji.pilot.usercenter.e.a.getInstance().a();
        while (!dji.pilot.usercenter.e.a.getInstance().c()) {
            dji.pilot.usercenter.e.a.getInstance().c();
        }
        dji.pilot.usercenter.e.a.getInstance().a(this.J.getApplicationContext());
    }

    public void b() {
        a(true);
    }

    public void a(final String str) {
        dji.pilot.usercenter.e.a.getInstance().a(this.J);
        if (!this.J.getResources().getConfiguration().locale.getLanguage().equals(dji.pilot.usercenter.e.a.getInstance().b())) {
            g();
        }
        dji.pilot.usercenter.e.a.getInstance().a(null, new dji.pilot.usercenter.e.a.c(this) {
            final /* synthetic */ b b;

            public void a(List<dji.pilot.usercenter.e.b> list, dji.pilot.usercenter.e.b bVar) {
                this.b.I.a(true, this.b.a(str, (List) list), str);
            }
        });
    }

    private String a(String str, List<dji.pilot.usercenter.e.b> list) {
        if (list == null || str == null) {
            return str;
        }
        String str2 = str;
        for (int i = 0; i < list.size(); i++) {
            dji.pilot.usercenter.e.b bVar = (dji.pilot.usercenter.e.b) list.get(i);
            if (str.equals(bVar.a)) {
                str2 = bVar.b;
            }
        }
        return str2;
    }

    private void h() {
        f.getInstance().q();
        f.getInstance().g();
    }

    private void i() {
        if (!this.K && !this.L) {
            this.I.a(true, null);
        }
    }

    private void j() {
        j f = f.getInstance().f();
        this.I.a(true, f.l, f.q, f.n, f.R != null ? f.R.b : f.t, f.R != null ? f.R.a : f.u);
    }

    private void k() {
        dji.pilot.usercenter.f.a.a(d(), this.N, this.O, d(), true);
        f.getInstance().b(d());
    }

    public void c() {
        DJILogHelper.getInstance().LOGD(G, "stopLocation()");
        ((LocationManager) this.J.getSystemService(n.C)).removeUpdates(this.R);
    }

    private void a(boolean z) {
        LocationManager locationManager = (LocationManager) this.J.getSystemService(n.C);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        DJILogHelper.getInstance().LOGD(G, "bestProvider = " + bestProvider);
        Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
        if (lastKnownLocation == null) {
            DJILogHelper.getInstance().LOGD(G, "best provider know location null");
            if (z) {
                locationManager.requestLocationUpdates(bestProvider, 0, 0.0f, this.R);
                return;
            } else {
                a(Locale.getDefault().getCountry());
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append(H);
        stringBuilder.append(lastKnownLocation.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(lastKnownLocation.getLongitude());
        com.dji.frame.c.c.a().a(HorizonalSegmentView.N);
        com.dji.frame.c.c.b(this.J).b(stringBuilder.toString(), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("results").getJSONObject(0).getJSONArray("address_components");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        JSONArray jSONArray2 = jSONObject.getJSONArray("types");
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            if ("country".equals(jSONArray2.getString(i2))) {
                                this.a.a(jSONObject.getString("short_name"));
                            }
                        }
                    }
                } catch (Exception e) {
                    this.a.a(Locale.getDefault().getCountry());
                }
            }

            public void a(Throwable th, int i, String str) {
                this.a.a(Locale.getDefault().getCountry());
            }
        });
    }
}
