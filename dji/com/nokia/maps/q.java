package com.nokia.maps;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RouteOptions.Type;
import com.nokia.maps.dd.c;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.android.tpush.common.Constants;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q implements p {
    static final /* synthetic */ boolean a;
    private static final String l = q.class.getName();
    private static final Map<String, String> t = new HashMap();
    private final ApplicationContext$c A = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.h = false;
        }

        public void b() {
            this.a.h = true;
        }
    };
    private final ApplicationContext$c B = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.i = false;
        }

        public void b() {
            this.a.i = true;
        }
    };
    private final ApplicationContext$c C = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.j = false;
        }

        public void b() {
            this.a.j = true;
        }
    };
    private final ApplicationContext$c D = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.k = false;
        }

        public void b() {
            this.a.k = true;
        }
    };
    private final Object E = new Object();
    private final Runnable F = new Runnable(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void run() {
            synchronized (this.a.E) {
                com.here.b.a.a.a(true);
            }
        }
    };
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private JSONObject m;
    private JSONObject n;
    private JSONObject o;
    private Context p;
    private int q = 0;
    private Date r;
    private Date s;
    private final ApplicationContext$c u = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.b = false;
        }

        public void b() {
            this.a.b = true;
        }
    };
    private final ApplicationContext$c v = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.c = false;
        }

        public void b() {
            this.a.c = true;
        }
    };
    private final ApplicationContext$c w = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.d = false;
        }

        public void b() {
            this.a.d = true;
        }
    };
    private final ApplicationContext$c x = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.e = false;
        }

        public void b() {
            this.a.e = true;
        }
    };
    private final ApplicationContext$c y = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.f = false;
        }

        public void b() {
            this.a.f = true;
        }
    };
    private final ApplicationContext$c z = new ApplicationContext$c(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.g = false;
        }

        public void b() {
            this.a.g = true;
        }
    };

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[TransportMode.values().length];

        static {
            d = new int[com.nokia.maps.RouteManagerImpl.a.a().length];
            try {
                d[com.nokia.maps.RouteManagerImpl.a.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                d[com.nokia.maps.RouteManagerImpl.a.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                d[com.nokia.maps.RouteManagerImpl.a.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            c = new int[c.a().length];
            try {
                c[c.GEOCODE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                c[c.REVERSE_GEOCODE.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                c[c.DISCOVER_AROUND.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                c[c.DISCOVER_EXPLORE.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                c[c.DISCOVER_HERE.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                c[c.DISCOVER_SEARCH.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                c[c.DISCOVER.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                c[c.p.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                c[c.TEXT_SUGGESTIONS.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
            try {
                c[c.TEXT_AUTOSUGGESTIONS.ordinal()] = 10;
            } catch (NoSuchFieldError e13) {
            }
            b = new int[a.a().length];
            try {
                b[a.ADDRESS_GEOCODE.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                b[a.ONE_BOX_GEOCODE.ordinal()] = 2;
            } catch (NoSuchFieldError e15) {
            }
            try {
                b[a.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[TransportMode.CAR.ordinal()] = 1;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[TransportMode.TRUCK.ordinal()] = 2;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[TransportMode.PEDESTRIAN.ordinal()] = 3;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[TransportMode.PUBLIC_TRANSPORT.ordinal()] = 4;
            } catch (NoSuchFieldError e20) {
            }
        }
    }

    private static class a extends JSONObject {
        a() {
            try {
                put("appId", m.e());
                put("appCode", m.f());
                put("appVersion", m.g());
                put("platformName", m.d());
                put("platformVersion", m.c());
                put("sdkName", m.h() + (m.k() ? "-internal" : ""));
                put("sdkVersion", m.i());
                put(Constants.FLAG_DEVICE_ID, m.j());
                put("startTime", m.a());
            } catch (JSONException e) {
                bj.c(q.l, "Unable to create session object", new Object[0]);
            }
        }
    }

    private static class b extends JSONObject {
        b() throws JSONException {
            put("last", m.b());
        }
    }

    static {
        boolean z;
        if (q.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
        t.put("display-online-sli", "sliUsageCount");
        t.put("display-hybrid-sli", "sliUsageCount");
        t.put("display-offline-sli", "sliUsageCount");
        t.put("display-online-ar", "arUsageCount");
        t.put("display-hybrid-ar", "arUsageCount");
        t.put("display-offline-ar", "arUsageCount");
        t.put("guidance-online-car", "basicNavUsageCount");
        t.put("guidance-hybrid-car", "basicNavUsageCount");
        t.put("guidance-offline-car", "basicNavUsageCount");
        t.put("guidance-online-car-traffic", "basicNavUsageCount");
        t.put("guidance-hybrid-car-traffic", "basicNavUsageCount");
        t.put("nav_tracking-online-car", "basicNavUsageCount");
        t.put("nav_tracking-hybrid-car", "basicNavUsageCount");
        t.put("nav_tracking-offline-car", "basicNavUsageCount");
        t.put("advanced-guidance-online-car", "advNavUsageCount");
        t.put("advanced-guidance-hybrid-car", "advNavUsageCount");
        t.put("advanced-guidance-offline-car", "advNavUsageCount");
        t.put("advanced-guidance-online-car-traffic", "advNavUsageCount");
        t.put("advanced-guidance-hybrid-car-traffic", "advNavUsageCount");
        t.put("traffic-update-route", "advNavUsageCount");
        t.put("traffic-update-route-elements", "advNavUsageCount");
        t.put("guidance-online-pedestrian", "walkNavUsageCount");
        t.put("guidance-hybrid-pedestrian", "walkNavUsageCount");
        t.put("guidance-offline-pedestrian", "walkNavUsageCount");
        t.put("nav_tracking-online-pedestrian", "walkNavUsageCount");
        t.put("nav_tracking-hybrid-pedestrian", "walkNavUsageCount");
        t.put("nav_tracking-offline-pedestrian", "walkNavUsageCount");
        t.put("fleetMapCount", "mamUsageCount");
        t.put("truckAttributesCount", "mamUsageCount");
        t.put("congestionZonesCount", "mamUsageCount");
        t.put("historicalSpeedPatternCount", "mamUsageCount");
        t.put("routing-online-truck", "mamUsageCount");
    }

    q() {
    }

    public void a(Context context, boolean z) {
        if (a || context != null) {
            String str;
            this.p = context;
            if (z) {
                try {
                    str = new String(Base64.decode("ZjVyanlrcWFxOA==\n", 0), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new Error("Cannot initialize segment.io");
                }
            }
            str = new String(s.a(Base64.decode("0QLS/6jl2s6fmA==\n", 0), 13), "UTF-8");
            com.here.b.d.c.a(false);
            com.here.b.a.c cVar = new com.here.b.a.c(context, str);
            cVar.a(true);
            cVar.a(Boolean.valueOf(true));
            cVar.a(1);
            cVar.a(9999999999999L);
            cVar.a(com.here.b.d.a.a.NONE);
            com.here.b.a.a.a(cVar);
            com.here.b.a.a.a(true);
            com.a.a.a a = com.here.b.a.a.a();
            try {
                Field declaredField = a.getClass().getDeclaredField("integrationManager");
                declaredField.setAccessible(true);
                ((Application) this.p.getApplicationContext()).unregisterActivityLifecycleCallbacks((ActivityLifecycleCallbacks) declaredField.get(a));
            } catch (Exception e2) {
                bj.b(l, "Unable to unregister from lifecycle callbacks", new Object[0]);
            }
            ApplicationContext.b().check(21, this.u);
            ApplicationContext.b().check(32, this.v);
            ApplicationContext.b().check(24, this.w);
            ApplicationContext.b().check(25, this.x);
            ApplicationContext.b().check(26, this.y);
            ApplicationContext.b().check(28, this.z);
            ApplicationContext.b().check(29, this.A);
            ApplicationContext.b().check(30, this.B);
            ApplicationContext.b().check(31, this.C);
            ApplicationContext.b().check(18, this.D);
            s();
            if (this.n == null || this.o == null || this.m == null || this.s == null) {
                this.r = new Date();
                q();
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    public synchronized void a() {
        if (a || this.m != null) {
            try {
                Date date = new Date();
                if (this.n.has("sdk-usage")) {
                    m();
                    int time = (int) ((date.getTime() - this.r.getTime()) / 1000);
                    JSONObject jSONObject = this.n.getJSONObject("sdk-usage");
                    a(jSONObject, ParamKey.COUNT);
                    a(jSONObject, "loadCount");
                    a(jSONObject, "totalTime", time);
                    l();
                }
                this.n.put("sdk-usage", new JSONObject());
                this.r = date;
                this.s = new Date();
                this.q++;
                p();
                r();
            } catch (JSONException e) {
                bj.c(l, "Couldn't track sdk-usage", new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    private void l() throws JSONException {
        a(this.n, this.o);
        this.n = new a();
        this.n.put("staged", this.o);
        this.m.put("data", this.n);
    }

    private void a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (str.compareTo("staged") != 0) {
                Object obj = jSONObject.get(str);
                if (jSONObject2.has(str)) {
                    Object obj2 = jSONObject2.get(str);
                    if (obj instanceof JSONObject) {
                        a((JSONObject) obj, (JSONObject) obj2);
                    } else if (!(obj instanceof JSONArray)) {
                        if (obj instanceof Integer) {
                            jSONObject2.put(str, ((Integer) obj).intValue() + ((Integer) obj2).intValue());
                        } else if (obj instanceof Long) {
                            jSONObject2.put(str, ((Long) obj2).longValue() + ((Long) obj).longValue());
                        } else if (obj instanceof Double) {
                            jSONObject2.put(str, ((Double) obj2).doubleValue() + ((Double) obj).doubleValue());
                        } else if ((obj instanceof String) && str.compareTo("startTime") != 0) {
                            jSONObject2.put(str, obj);
                        }
                    }
                } else {
                    jSONObject2.put(str, obj);
                }
            }
        }
    }

    private void m() throws JSONException {
        if (this.n.has("sdk-usage")) {
            JSONObject jSONObject = this.n.getJSONObject("sdk-usage");
            Date date = new Date();
            long time = (date.getTime() - this.r.getTime()) / 1000;
            int i = (int) (time / FlyForbidProtocol.UNLIMIT_AREA_EXPIRED_TIME);
            int i2 = ((i * 24) * 60) * 60;
            if (i > 0) {
                a(jSONObject, ParamKey.COUNT, i);
                a(jSONObject, "totalTime", i2);
                l();
                this.n.put("sdk-usage", new JSONObject());
                this.r = new Date(date.getTime() - ((time - ((long) i2)) * 1000));
            }
        }
    }

    private String a(TransportMode transportMode) {
        switch (AnonymousClass4.a[transportMode.ordinal()]) {
            case 1:
                return "car";
            case 2:
                return "truck";
            case 3:
                return "pedestrian";
            case 4:
                return "transit";
            default:
                return "unknown";
        }
    }

    private void a(TransportMode transportMode, boolean z, String str) {
        if (a || this.m != null) {
            String str2 = null;
            try {
                str2 = g(transportMode, z);
                JSONObject i = i(str2);
                a(i, str);
                a(str2, i);
                return;
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str2, new Object[0]);
                return;
            }
        }
        throw new AssertionError("Analytics tracking called before engine initialized");
    }

    public synchronized void a(TransportMode transportMode, boolean z) {
        a(transportMode, z, "laneAssistanceCount");
    }

    public synchronized void b(TransportMode transportMode, boolean z) {
        a(transportMode, z, "safetySpotCount");
    }

    public synchronized void c(TransportMode transportMode, boolean z) {
        a(transportMode, z, "signPostCount");
    }

    public synchronized void d(TransportMode transportMode, boolean z) {
        a(transportMode, z, "junctionViewCount");
    }

    public synchronized void e(TransportMode transportMode, boolean z) {
        a(transportMode, z, "mapMatcherMobileCount");
    }

    public synchronized void f(TransportMode transportMode, boolean z) {
        a(transportMode, z, "mapMatcherAutomotiveCount");
    }

    private String g(TransportMode transportMode, boolean z) throws Exception {
        String str;
        Object obj = 1;
        String n = n();
        Object obj2 = (z && !n.equals("offline") && (transportMode == TransportMode.CAR || transportMode == TransportMode.TRUCK)) ? 1 : null;
        if (!(this.c && (transportMode == TransportMode.CAR || transportMode == TransportMode.TRUCK))) {
            obj = null;
        }
        StringBuffer append = new StringBuffer().append(obj != null ? "advanced" : "").append(obj != null ? "-" : "").append("guidance").append("-").append(n).append("-").append(a(transportMode)).append(obj2 != null ? "-" : "");
        if (obj2 != null) {
            str = "traffic";
        } else {
            str = "";
        }
        return append.append(str).toString();
    }

    public synchronized void a(long j) {
        if (a || this.m != null) {
            String str = null;
            try {
                str = new StringBuffer("nav_tracking").append("-").append(n()).append("-").append("car").toString();
                JSONObject i = i(str);
                a(i, ParamKey.COUNT);
                a(i, n.ay, j);
                a(str, i);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    private JSONObject h(String str) throws JSONException {
        if (this.n.has(str)) {
            return this.n.getJSONObject(str);
        }
        return new b();
    }

    private JSONObject i(String str) throws JSONException {
        JSONObject bVar = new b();
        if (this.n.has(str)) {
            bVar.put("last", this.n.getJSONObject(str).get("last"));
        }
        return bVar;
    }

    public synchronized void a(TransportMode transportMode, boolean z, long j, boolean z2, boolean z3, boolean z4) {
        if (a || this.m != null) {
            String str = null;
            try {
                str = g(transportMode, z);
                JSONObject i = i(str);
                if (z4) {
                    a(i, "errors");
                } else if (z2) {
                    a(i, "reroutes");
                } else {
                    a(i, ParamKey.COUNT);
                    a(i, n.ay, j);
                    if (z3) {
                        a(i, "completedCount");
                    }
                }
                a(str, i);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    public synchronized void a(int i, int i2, int i3) {
        if (a || this.m != null) {
            String str = null;
            try {
                str = new StringBuffer("venue3D").append("-").append("entry").append("-").append(n()).toString();
                JSONObject i4 = i(str);
                a(i4, ParamKey.COUNT);
                a(i4, "selections", i2);
                a(i4, "floorChanges", i);
                a(i4, "totalTime", i3);
                a(str, i4);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    public synchronized void a(TransportMode transportMode, Type type, boolean z, int i) {
        if (a || this.m != null) {
            String str = null;
            try {
                str = new StringBuffer("routing").append("-").append("venue3D").append("-").append(n()).append("-").append(a(transportMode)).toString();
                JSONObject i2 = i(str);
                if (z) {
                    a(i2, "errors");
                } else {
                    a(i2, ParamKey.COUNT);
                }
                switch (i) {
                    case 0:
                        a(i2, "insideCount");
                        break;
                    case 1:
                        a(i2, "outsideToInsideCount");
                        break;
                    case 2:
                        a(i2, "insideToOutsideCount");
                        break;
                    case 3:
                        a(i2, "venueToVenueCount");
                        break;
                }
                if (type == Type.SHORTEST) {
                    a(i2, "shortestCount");
                } else {
                    a(i2, "fastestCount");
                }
                a(str, i2);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    public synchronized void a(a aVar, boolean z, boolean z2) {
        String str = null;
        switch (aVar) {
            case ADDRESS_GEOCODE:
                str = "address";
                break;
            case ONE_BOX_GEOCODE:
                str = "freeform";
                break;
            case UNKNOWN:
                str = "unknown";
                break;
        }
        a(c.GEOCODE, str, z, z2);
    }

    public synchronized void a(c cVar, boolean z, boolean z2) {
        a(cVar, null, z, z2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.here.posclient.analytics.PositioningCounters r9) {
        /*
        r8 = this;
        r6 = 0;
        monitor-enter(r8);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f4 }
        r1 = "positioning";
        r0.<init>(r1);	 Catch:{ all -> 0x00f4 }
        r1 = "-";
        r0 = r0.append(r1);	 Catch:{ all -> 0x00f4 }
        r1 = r9.event;	 Catch:{ all -> 0x00f4 }
        switch(r1) {
            case 111: goto L_0x002a;
            case 121: goto L_0x00f7;
            case 131: goto L_0x010a;
            case 132: goto L_0x011d;
            case 133: goto L_0x0130;
            default: goto L_0x0015;
        };	 Catch:{ all -> 0x00f4 }
    L_0x0015:
        r0 = l;	 Catch:{ all -> 0x00f4 }
        r1 = "unknown event type: %d";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00f4 }
        r3 = 0;
        r4 = r9.event;	 Catch:{ all -> 0x00f4 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x00f4 }
        r2[r3] = r4;	 Catch:{ all -> 0x00f4 }
        com.nokia.maps.bj.c(r0, r1, r2);	 Catch:{ all -> 0x00f4 }
    L_0x0028:
        monitor-exit(r8);
        return;
    L_0x002a:
        r1 = "online";
        r1 = r0.append(r1);	 Catch:{ all -> 0x00f4 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00f4 }
        r2 = "outdoor";
        r1.append(r2);	 Catch:{ all -> 0x00f4 }
    L_0x003b:
        r1 = r0.toString();	 Catch:{ Exception -> 0x00e0 }
        r1 = r8.i(r1);	 Catch:{ Exception -> 0x00e0 }
        r2 = "usageCount";
        r8.a(r1, r2);	 Catch:{ Exception -> 0x00e0 }
        r2 = r9.updates;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x0055;
    L_0x004e:
        r2 = "updates";
        r4 = r9.updates;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x0055:
        r2 = r9.updateErrors;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x0062;
    L_0x005b:
        r2 = "errorCount";
        r4 = r9.updateErrors;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x0062:
        r2 = r9.fallbacks;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x006f;
    L_0x0068:
        r2 = "fallbacks";
        r4 = r9.fallbacks;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x006f:
        r2 = r9.estimates;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x007c;
    L_0x0075:
        r2 = "estimates";
        r4 = r9.estimates;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x007c:
        r2 = r9.externals;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x0089;
    L_0x0082:
        r2 = "externals";
        r4 = r9.externals;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x0089:
        r2 = r9.withBuilding;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x0096;
    L_0x008f:
        r2 = "buildingAwareCount";
        r4 = r9.withBuilding;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x0096:
        r2 = r9.withFloor;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x00a3;
    L_0x009c:
        r2 = "floorAwareCount";
        r4 = r9.withFloor;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x00a3:
        r2 = r9.byCell;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x00b0;
    L_0x00a9:
        r2 = "cellBasedCount";
        r4 = r9.byCell;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x00b0:
        r2 = r9.byWlan;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x00bd;
    L_0x00b6:
        r2 = "wlanBasedCount";
        r4 = r9.byWlan;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x00bd:
        r2 = r9.byBle;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x00ca;
    L_0x00c3:
        r2 = "bleBasedCount";
        r4 = r9.byBle;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x00ca:
        r2 = r9.onlines;	 Catch:{ Exception -> 0x00e0 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d7;
    L_0x00d0:
        r2 = "onlineCount";
        r4 = r9.onlines;	 Catch:{ Exception -> 0x00e0 }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x00e0 }
    L_0x00d7:
        r2 = r0.toString();	 Catch:{ Exception -> 0x00e0 }
        r8.a(r2, r1);	 Catch:{ Exception -> 0x00e0 }
        goto L_0x0028;
    L_0x00e0:
        r1 = move-exception;
        r1 = l;	 Catch:{ all -> 0x00f4 }
        r2 = "Couldn't track: %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00f4 }
        r4 = 0;
        r0 = r0.toString();	 Catch:{ all -> 0x00f4 }
        r3[r4] = r0;	 Catch:{ all -> 0x00f4 }
        com.nokia.maps.bj.c(r1, r2, r3);	 Catch:{ all -> 0x00f4 }
        goto L_0x0028;
    L_0x00f4:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x00f7:
        r1 = "hybrid";
        r1 = r0.append(r1);	 Catch:{ all -> 0x00f4 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00f4 }
        r2 = "outdoor";
        r1.append(r2);	 Catch:{ all -> 0x00f4 }
        goto L_0x003b;
    L_0x010a:
        r1 = "offline";
        r1 = r0.append(r1);	 Catch:{ all -> 0x00f4 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00f4 }
        r2 = "outdoor";
        r1.append(r2);	 Catch:{ all -> 0x00f4 }
        goto L_0x003b;
    L_0x011d:
        r1 = "offline";
        r1 = r0.append(r1);	 Catch:{ all -> 0x00f4 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00f4 }
        r2 = "common_indoor";
        r1.append(r2);	 Catch:{ all -> 0x00f4 }
        goto L_0x003b;
    L_0x0130:
        r1 = "offline";
        r1 = r0.append(r1);	 Catch:{ all -> 0x00f4 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00f4 }
        r2 = "private_indoor";
        r1.append(r2);	 Catch:{ all -> 0x00f4 }
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.q.a(com.here.posclient.analytics.PositioningCounters):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.here.posclient.analytics.RadiomapCounters r9) {
        /*
        r8 = this;
        r6 = 0;
        monitor-enter(r8);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0082 }
        r0.<init>();	 Catch:{ all -> 0x0082 }
        r1 = r9.event;	 Catch:{ all -> 0x0082 }
        switch(r1) {
            case 211: goto L_0x0022;
            case 212: goto L_0x0085;
            case 213: goto L_0x0097;
            case 214: goto L_0x000d;
            case 215: goto L_0x000d;
            case 216: goto L_0x000d;
            case 217: goto L_0x000d;
            case 218: goto L_0x000d;
            case 219: goto L_0x000d;
            case 220: goto L_0x000d;
            case 221: goto L_0x00a9;
            case 222: goto L_0x00bc;
            case 223: goto L_0x00cf;
            default: goto L_0x000d;
        };	 Catch:{ all -> 0x0082 }
    L_0x000d:
        r0 = l;	 Catch:{ all -> 0x0082 }
        r1 = "unknown event type: %d";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0082 }
        r3 = 0;
        r4 = r9.event;	 Catch:{ all -> 0x0082 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0082 }
        r2[r3] = r4;	 Catch:{ all -> 0x0082 }
        com.nokia.maps.bj.c(r0, r1, r2);	 Catch:{ all -> 0x0082 }
    L_0x0020:
        monitor-exit(r8);
        return;
    L_0x0022:
        r1 = "odnp_rm_demand";
        r1 = r0.append(r1);	 Catch:{ all -> 0x0082 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0082 }
        r2 = "outdoor";
        r1.append(r2);	 Catch:{ all -> 0x0082 }
    L_0x0033:
        r1 = r0.toString();	 Catch:{ Exception -> 0x006f }
        r1 = r8.i(r1);	 Catch:{ Exception -> 0x006f }
        r2 = "usageCount";
        r8.a(r1, r2);	 Catch:{ Exception -> 0x006f }
        r2 = r9.errors;	 Catch:{ Exception -> 0x006f }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x004d;
    L_0x0046:
        r2 = "errors";
        r4 = r9.errors;	 Catch:{ Exception -> 0x006f }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x006f }
    L_0x004d:
        r2 = r9.downloadCount;	 Catch:{ Exception -> 0x006f }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x005a;
    L_0x0053:
        r2 = "downloadCount";
        r4 = r9.downloadCount;	 Catch:{ Exception -> 0x006f }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x006f }
    L_0x005a:
        r2 = r9.downloadFileSize;	 Catch:{ Exception -> 0x006f }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x0067;
    L_0x0060:
        r2 = "downloadFileSize";
        r4 = r9.downloadFileSize;	 Catch:{ Exception -> 0x006f }
        r8.a(r1, r2, r4);	 Catch:{ Exception -> 0x006f }
    L_0x0067:
        r2 = r0.toString();	 Catch:{ Exception -> 0x006f }
        r8.a(r2, r1);	 Catch:{ Exception -> 0x006f }
        goto L_0x0020;
    L_0x006f:
        r1 = move-exception;
        r1 = l;	 Catch:{ all -> 0x0082 }
        r2 = "Couldn't track: %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0082 }
        r4 = 0;
        r0 = r0.toString();	 Catch:{ all -> 0x0082 }
        r3[r4] = r0;	 Catch:{ all -> 0x0082 }
        com.nokia.maps.bj.c(r1, r2, r3);	 Catch:{ all -> 0x0082 }
        goto L_0x0020;
    L_0x0082:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x0085:
        r1 = "odnp_rm_demand";
        r1 = r0.append(r1);	 Catch:{ all -> 0x0082 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0082 }
        r2 = "common_indoor";
        r1.append(r2);	 Catch:{ all -> 0x0082 }
        goto L_0x0033;
    L_0x0097:
        r1 = "odnp_rm_demand";
        r1 = r0.append(r1);	 Catch:{ all -> 0x0082 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0082 }
        r2 = "private_indoor";
        r1.append(r2);	 Catch:{ all -> 0x0082 }
        goto L_0x0033;
    L_0x00a9:
        r1 = "odnp_rm_manual";
        r1 = r0.append(r1);	 Catch:{ all -> 0x0082 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0082 }
        r2 = "outdoor";
        r1.append(r2);	 Catch:{ all -> 0x0082 }
        goto L_0x0033;
    L_0x00bc:
        r1 = "odnp_rm_manual";
        r1 = r0.append(r1);	 Catch:{ all -> 0x0082 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0082 }
        r2 = "common_indoor";
        r1.append(r2);	 Catch:{ all -> 0x0082 }
        goto L_0x0033;
    L_0x00cf:
        r1 = "odnp_rm_manual";
        r1 = r0.append(r1);	 Catch:{ all -> 0x0082 }
        r2 = "-";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0082 }
        r2 = "private_indoor";
        r1.append(r2);	 Catch:{ all -> 0x0082 }
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.q.a(com.here.posclient.analytics.RadiomapCounters):void");
    }

    private String a(c cVar) {
        String str = null;
        switch (cVar) {
            case GEOCODE:
                if (!this.g) {
                    str = "online";
                    break;
                }
                break;
            case REVERSE_GEOCODE:
                if (!this.h) {
                    str = "online";
                    break;
                }
                break;
            case DISCOVER_AROUND:
            case DISCOVER_EXPLORE:
            case DISCOVER_HERE:
                if (!this.i) {
                    str = "online";
                    break;
                }
                break;
            case DISCOVER_SEARCH:
                if (!this.j) {
                    str = "online";
                    break;
                }
                break;
            case DISCOVER:
                str = "online";
                break;
        }
        if (str == null) {
            return n();
        }
        return str;
    }

    private void a(c cVar, String str, boolean z, boolean z2) {
        if (a || this.m != null) {
            StringBuffer stringBuffer = new StringBuffer();
            switch (cVar) {
                case GEOCODE:
                    stringBuffer.append("geocoding");
                    break;
                case REVERSE_GEOCODE:
                    stringBuffer.append("reverse-geocoding");
                    break;
                case DISCOVER_AROUND:
                    stringBuffer.append("search");
                    str = "around";
                    break;
                case DISCOVER_EXPLORE:
                    stringBuffer.append("search");
                    str = DJISupportShareWebviewFragment.T;
                    break;
                case DISCOVER_HERE:
                    stringBuffer.append("search");
                    str = "here";
                    break;
                case DISCOVER_SEARCH:
                    stringBuffer.append("search");
                    str = "search";
                    break;
                case DISCOVER:
                    stringBuffer.append("search");
                    str = "discovery";
                    break;
                case p:
                    stringBuffer.append("place-details");
                    break;
                case TEXT_SUGGESTIONS:
                case TEXT_AUTOSUGGESTIONS:
                    stringBuffer.append("search");
                    str = "suggestion";
                    break;
                default:
                    return;
            }
            stringBuffer.append("-").append(a(cVar));
            if (!TextUtils.isEmpty(str)) {
                stringBuffer.append("-");
                stringBuffer.append(str);
            }
            a(stringBuffer.toString(), z, z2);
            return;
        }
        throw new AssertionError("Analytics tracking called before engine initialized");
    }

    private void a(String str, boolean z, boolean z2) {
        try {
            JSONObject i = i(str);
            if (z) {
                a(i, "errors");
            } else if (z2) {
                a(i, ParamKey.COUNT);
            } else {
                a(i, ParamKey.COUNT);
                a(i, "noResultsCount");
            }
            a(str, i);
        } catch (Exception e) {
            bj.c(l, "Couldn't track " + str, new Object[0]);
        }
    }

    public synchronized void a(boolean z, boolean z2) {
        if (a || this.m != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("search");
            stringBuffer.append("-");
            stringBuffer.append("custom-location");
            a(stringBuffer.toString(), z, z2);
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    public synchronized void a(int i) {
        a(TransportMode.PUBLIC_TRANSPORT, "timetable", "online", (double) i, false);
    }

    public synchronized void a(boolean z, boolean z2, int i, boolean z3) {
        if (a || this.m != null) {
            String str = "routing-online-um";
            try {
                JSONObject i2 = i(str);
                if (z3) {
                    a(i2, "errors");
                } else {
                    a(i2, ParamKey.COUNT);
                    if (i > 0) {
                        a(i2, n.ay, i);
                    }
                    if (z2) {
                        a(i2, "isRealTime");
                    }
                    if (z) {
                        a(i2, "hasFareInfo");
                    }
                }
                a(str, i2);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    private String b(TransportMode transportMode) {
        if ((transportMode != TransportMode.CAR || this.d) && ((transportMode != TransportMode.PEDESTRIAN || this.e) && (transportMode != TransportMode.PUBLIC_TRANSPORT || this.f))) {
            return n();
        }
        return "online";
    }

    public synchronized void a(TransportMode transportMode, RouteImpl routeImpl, com.nokia.maps.RouteManagerImpl.a aVar) {
        String str;
        boolean z = false;
        double d = 0.0d;
        String b = b(transportMode);
        if (transportMode == TransportMode.PUBLIC_TRANSPORT) {
            String str2 = (routeImpl == null || routeImpl.a() != com.nokia.maps.RouteImpl.a.ENHANCED_TRANSIT_ROUTE) ? "estimated" : "timetable";
            str = str2;
        } else if ((transportMode != TransportMode.CAR && transportMode != TransportMode.TRUCK) || routeImpl == null || routeImpl.l() == TrafficPenaltyMode.DISABLED || b.equals("offline")) {
            str = null;
        } else {
            str = "traffic";
        }
        if (routeImpl == null) {
            z = true;
        } else {
            d = (double) routeImpl.getLength();
        }
        switch (aVar) {
            case ONLINE:
                b = "online";
                break;
            case OFFLINE:
                b = "offline";
                break;
            case AUTO:
                break;
            default:
                b = null;
                break;
        }
        a(transportMode, str, b, d, z);
    }

    private void a(TransportMode transportMode, String str, String str2, double d, boolean z) {
        if (a || this.m != null) {
            try {
                StringBuffer append = new StringBuffer().append("routing").append("-").append(str2).append("-").append(a(transportMode)).append(str == null ? "" : "-");
                if (str == null) {
                    str = "";
                }
                String stringBuffer = append.append(str).toString();
                JSONObject i = i(stringBuffer);
                if (z) {
                    a(i, "errors");
                } else {
                    a(i, ParamKey.COUNT);
                    a(i, n.ay, d);
                }
                a(stringBuffer, i);
                return;
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + null, new Object[0]);
                return;
            }
        }
        throw new AssertionError("Analytics tracking called before engine initialized");
    }

    public synchronized void a(boolean z) {
        a("list", z);
    }

    public synchronized void b(boolean z) {
        a("install", z);
    }

    public synchronized void c(boolean z) {
        a("uninstall", z);
    }

    public synchronized void d(boolean z) {
        a("update", z);
    }

    private void a(String str, boolean z) {
        if (a || this.m != null) {
            try {
                String stringBuffer = new StringBuffer().append("odml").append("-").append(str).toString();
                JSONObject i = i(stringBuffer);
                a(i, z ? "errors" : ParamKey.COUNT);
                a(stringBuffer, i);
                return;
            } catch (Exception e) {
                bj.c(l, "Couldn't track odml-" + str, new Object[0]);
                return;
            }
        }
        throw new AssertionError("Analytics tracking called before engine initialized");
    }

    public void b() {
        j(ParamKey.COUNT);
    }

    public void c() {
        j("incomingMessagesCount");
    }

    public void d() {
        j("outgoingMessagesCount");
    }

    public void e() {
        j("errors");
    }

    private synchronized void j(String str) {
        if (a || this.m != null) {
            String stringBuffer = new StringBuffer().append("fleet-connectivity").append("-").append("service").toString();
            try {
                JSONObject i = i(stringBuffer);
                a(i, str);
                a(stringBuffer, i);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + stringBuffer, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    public synchronized void f() {
        if (a || this.m != null) {
            String stringBuffer = new StringBuffer().append("traffic-update").append("-").append("geocoord").toString();
            try {
                JSONObject i = i(stringBuffer);
                a(i, ParamKey.COUNT);
                a(stringBuffer, i);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + stringBuffer, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    public synchronized void a(boolean z, int i, int i2) {
        a(true, z, i, i2);
    }

    public synchronized void b(boolean z, int i, int i2) {
        a(false, z, i, i2);
    }

    private void a(boolean z, boolean z2, int i, int i2) {
        if (a || this.m != null) {
            String stringBuffer = new StringBuffer().append("traffic-update").append("-").append(z ? "route" : "route-elements").toString();
            try {
                JSONObject i3 = i(stringBuffer);
                if (z2) {
                    a(i3, "flowCount", i);
                    a(i3, "incidentCount", i2);
                } else {
                    a(i3, ParamKey.COUNT);
                }
                a(stringBuffer, i3);
                return;
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + stringBuffer, new Object[0]);
                return;
            }
        }
        throw new AssertionError("Analytics tracking called before engine initialized");
    }

    private boolean k(String str) {
        return str.contains("satellite") || str.contains("hybrid");
    }

    public synchronized void a(boolean z, String str) {
        b(z, k(str) ? "satellite" : "map");
    }

    public synchronized void f(boolean z) {
        b(z, "ar");
    }

    public synchronized void e(boolean z) {
        b(z, "sli");
    }

    public synchronized void g() {
        b("map", "buildingInteractions");
    }

    public synchronized void a(String str) {
        b(k(str) ? "satellite" : "map", "trafficOnMapCount");
    }

    public synchronized void a(String str, String str2) {
        boolean k = k(str);
        boolean k2 = k(str2);
        if (!k && k2) {
            b(true, "satellite");
        } else if (!k || k2) {
            b(k2 ? "satellite" : "map", "mapSchemeCount");
        } else {
            b(true, "map");
        }
    }

    public synchronized void b(String str) {
        if (str.equals("changescheme")) {
            b("map", "customizedSchemeChangeCount");
        } else if (str.equals("createscheme")) {
            b("map", "customizedSchemeCreateCount");
        } else if (str.equals("setvariable")) {
            b("map", "customizedSchemeSetVariableCount");
        }
    }

    public synchronized void h() {
        b("map", "3DLandmarkCount");
    }

    public synchronized void i() {
        b("map", "extrudedBuildingCount");
    }

    public synchronized void c(String str) {
        b(k(str) ? "satellite" : "map", "fleetMapCount");
    }

    public synchronized void d(String str) {
        b(k(str) ? "satellite" : "map", "truckAttributesCount");
    }

    public synchronized void e(String str) {
        b(k(str) ? "satellite" : "map", "congestionZonesCount");
    }

    public synchronized void f(String str) {
        b(k(str) ? "satellite" : "map", "historicalSpeedPatternCount");
    }

    public synchronized void g(String str) {
        b(k(str) ? "satellite" : "map", "customRasterTileSourceCount");
    }

    public synchronized void j() {
        b("sli", "buildingInteractions");
    }

    public void b(boolean z, boolean z2) {
        b("um-next_departures-stationBoard", z, z2);
    }

    public void c(boolean z, boolean z2) {
        b("um-next_departures-multiBoard", z, z2);
    }

    public void g(boolean z) {
        b("um-transit_coverage-cityInfoByGeoCoordinate", false, z);
    }

    public void h(boolean z) {
        b("um-transit_coverage-cityInfoByName", false, z);
    }

    public void i(boolean z) {
        b("um-transit_coverage-coverageInfoByGeoCoordinate", false, z);
    }

    public void j(boolean z) {
        b("um-station_search-name", false, z);
    }

    public void k(boolean z) {
        b("um-station_search-stationId", false, z);
    }

    public void l(boolean z) {
        b("um-station_search-geoCoordinate", false, z);
    }

    private synchronized void b(String str, boolean z, boolean z2) {
        if (a || this.m != null) {
            try {
                JSONObject i = i(str);
                if (z2) {
                    a(i, "errors");
                } else {
                    a(i, ParamKey.COUNT);
                    if (z) {
                        a(i, "isRealTime");
                    }
                }
                a(str, i);
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str, new Object[0]);
            }
        } else {
            throw new AssertionError("Analytics tracking called before engine initialized");
        }
    }

    private void b(String str, String str2) {
        if (a || this.m != null) {
            try {
                String l = l(str);
                JSONObject i = i(l);
                a(i, str2);
                a(l, i);
                return;
            } catch (Exception e) {
                bj.c(l, "Couldn't track " + str2 + " for " + str, new Object[0]);
                return;
            }
        }
        throw new AssertionError("Analytics tracking called before engine initialized");
    }

    private String n() {
        String str = "hybrid";
        if (!this.b) {
            return "online";
        }
        if (MapsEngine.b(this.p).B()) {
            return "offline";
        }
        return str;
    }

    private String l(String str) throws Exception {
        return new StringBuffer().append("display").append("-").append(n()).append("-").append(str).toString();
    }

    private void b(boolean z, String str) {
        if (a || this.m != null) {
            try {
                String l = l(str);
                JSONObject i = i(l);
                a(i, "subSessions");
                if (z) {
                    a(i, ParamKey.COUNT);
                    if (!(str.compareTo("sli") == 0 || str.compareTo("ar") == 0)) {
                        a(i, "mapSchemeCount");
                    }
                    if (!(str.compareTo("sli") == 0 || str.compareTo("satellite") == 0)) {
                        a(i, "extrudedBuildingCount");
                        if (this.k) {
                            a(i, "3DLandmarkCount");
                        }
                    }
                    this.m.put("lastDisplaySessionName", l);
                }
                a(l, i);
                return;
            } catch (Exception e) {
                bj.c(l, "Couldn't track display-" + str, new Object[0]);
                return;
            }
        }
        throw new AssertionError("Analytics tracking called before engine initialized");
    }

    private void a(String str, JSONObject jSONObject) throws JSONException {
        m();
        b(str, jSONObject);
        this.s = new Date();
        this.q++;
        JSONObject h = h(str);
        a(jSONObject, h);
        h.put("last", m.a());
        this.n.put(str, h);
        p();
        r();
    }

    private void o() throws JSONException {
        String str = this.m.getString("data").toString();
        String a = n.a(str, this.p);
        this.m.put("data", str);
        this.m.put(d.L, a);
    }

    private void m(String str) throws Exception {
        if ((this.m.has(d.L) || this.q > 0) && !n.a(str, this.m.getString(d.L), this.p)) {
            throw new Exception("SDK-usage data may have been tampered with");
        }
    }

    private void b(String str, JSONObject jSONObject) throws JSONException {
        if (this.r.after(m.a(jSONObject.getString("last")))) {
            a(jSONObject, "usageCount");
        }
        String str2 = (String) t.get(str);
        if (str2 != null) {
            JSONObject jSONObject2 = this.n.getJSONObject("sdk-usage");
            String str3 = "Last";
            String optString = jSONObject2.optString(str3);
            if (optString.length() == 0 || this.r.after(m.a(optString))) {
                a(jSONObject2, str2);
                c(str2, jSONObject2);
            }
            jSONObject2.put(str3, m.a());
        }
    }

    private void n(String str) throws JSONException {
        String str2 = (String) t.get(str);
        if (str2 != null) {
            JSONObject jSONObject = this.n.getJSONObject("sdk-usage");
            String str3 = "Last";
            String optString = jSONObject.optString(str3);
            if (optString.length() == 0 || this.r.after(m.a(optString))) {
                a(jSONObject, str2);
                c(str2, jSONObject);
            }
            jSONObject.put(str3, m.a());
        }
    }

    private void c(String str, JSONObject jSONObject) throws JSONException {
        if (str.equals("basicNavUsageCount") || str.equals("advNavUsageCount") || str.equals("walkNavUsageCount")) {
            String optString = jSONObject.optString("basicNavUsageCount" + "Last");
            String optString2 = jSONObject.optString("advNavUsageCount" + "Last");
            String optString3 = jSONObject.optString("walkNavUsageCount" + "Last");
            if (optString.length() != 0 && !this.r.after(m.a(optString))) {
                return;
            }
            if (optString2.length() != 0 && !this.r.after(m.a(optString2))) {
                return;
            }
            if (optString3.length() == 0 || this.r.after(m.a(optString3))) {
                a(jSONObject, "navUsageCount");
            }
        }
    }

    private void p() throws JSONException {
        boolean z = true;
        if (m.a(this.p)) {
            boolean z2;
            Calendar instance = Calendar.getInstance();
            instance.setTime(m.a(this.o.getString("startTime")));
            Calendar instance2 = Calendar.getInstance();
            boolean z3 = instance2.getActualMaximum(5) - instance2.get(5) <= 1;
            if (instance2.get(5) <= 5) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (instance.after(instance2)) {
                z3 = true;
                z = false;
            } else if (z3 && m.a(instance.getTime(), instance2.getTime(), TimeUnit.DAYS) >= 1) {
                z3 = true;
            } else if (instance.get(2) != instance2.get(2)) {
                if (z2) {
                    z3 = true;
                } else {
                    z3 = true;
                    z = false;
                }
            } else if (m.a(instance.getTime(), instance2.getTime(), TimeUnit.DAYS) >= 7) {
                z3 = true;
                z = false;
            } else if (this.q >= 200) {
                z3 = true;
                z = false;
            } else {
                z = false;
                z3 = false;
            }
            if (!z3) {
                return;
            }
            if (z || !(MapsEngine.b(this.p).B() || m.b(this.p))) {
                m(false);
            }
        }
    }

    private void q() {
        this.p.deleteFile("cd63818e-a03d-11e4-89d3-123b93f75cba");
        this.q = 0;
        this.s = new Date();
        try {
            if (this.n == null) {
                this.n = new a();
            }
            if (this.o == null) {
                this.o = new a();
            }
            this.n.put("staged", this.o);
            this.m = new JSONObject().put("index", 1).put("trackingId", UUID.randomUUID().toString()).put("data", this.n).put(ParamKey.COUNT, 1);
        } catch (JSONException e) {
            bj.c(l, "Failed to create a tracking session", new Object[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void r() {
        /*
        r6 = this;
        r4 = 0;
        r0 = l;
        r1 = "save()";
        r2 = new java.lang.Object[r4];
        com.nokia.maps.bj.a(r0, r1, r2);
        r0 = 0;
        r1 = r6.m;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r2 = "lastAccess";
        r3 = r6.s;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r3 = com.nokia.maps.m.a(r3);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r1.put(r2, r3);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r1 = r6.m;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r2 = "sdkStarted";
        r3 = r6.r;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r3 = com.nokia.maps.m.a(r3);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r1.put(r2, r3);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r1 = r6.m;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r2 = "eventCount";
        r3 = r6.q;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r1.put(r2, r3);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r6.o();	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r1 = r6.p;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r2 = "cd63818e-a03d-11e4-89d3-123b93f75cba";
        r3 = 0;
        r0 = r1.openFileOutput(r2, r3);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f, all -> 0x00a2 }
        r1 = r6.m;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        r1 = r1.toString();	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        r2 = "UTF-8";
        r2 = java.nio.charset.Charset.forName(r2);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        r1 = r1.getBytes(r2);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        r0.write(r1);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        r1 = r6.m;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        r2 = "data";
        r3 = r6.n;	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        r1.put(r2, r3);	 Catch:{ RuntimeException -> 0x007c, Exception -> 0x008f }
        if (r0 == 0) goto L_0x005b;
    L_0x0058:
        r0.close();	 Catch:{ IOException -> 0x00ac }
    L_0x005b:
        r0 = l;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "saved: ";
        r1 = r1.append(r2);
        r2 = r6.m;
        r2 = r2.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.Object[r4];
        com.nokia.maps.bj.a(r0, r1, r2);
        return;
    L_0x007c:
        r1 = move-exception;
        r1 = l;	 Catch:{ all -> 0x00b0 }
        r2 = "Could not create file to save tracking session";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00b0 }
        com.nokia.maps.bj.c(r1, r2, r3);	 Catch:{ all -> 0x00b0 }
        if (r0 == 0) goto L_0x005b;
    L_0x0089:
        r0.close();	 Catch:{ IOException -> 0x008d }
        goto L_0x005b;
    L_0x008d:
        r0 = move-exception;
        goto L_0x005b;
    L_0x008f:
        r1 = move-exception;
        r1 = l;	 Catch:{ all -> 0x00b0 }
        r2 = "Could not create file to save tracking session";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00b0 }
        com.nokia.maps.bj.c(r1, r2, r3);	 Catch:{ all -> 0x00b0 }
        if (r0 == 0) goto L_0x005b;
    L_0x009c:
        r0.close();	 Catch:{ IOException -> 0x00a0 }
        goto L_0x005b;
    L_0x00a0:
        r0 = move-exception;
        goto L_0x005b;
    L_0x00a2:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x00a6:
        if (r1 == 0) goto L_0x00ab;
    L_0x00a8:
        r1.close();	 Catch:{ IOException -> 0x00ae }
    L_0x00ab:
        throw r0;
    L_0x00ac:
        r0 = move-exception;
        goto L_0x005b;
    L_0x00ae:
        r1 = move-exception;
        goto L_0x00ab;
    L_0x00b0:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00a6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.q.r():void");
    }

    private void s() {
        InputStream openFileInput;
        Throwable th;
        BufferedReader bufferedReader = null;
        bj.a(l, "restore()", new Object[0]);
        int i = 1;
        try {
            openFileInput = this.p.openFileInput("cd63818e-a03d-11e4-89d3-123b93f75cba");
        } catch (FileNotFoundException e) {
            openFileInput = null;
            i = 0;
        }
        if (i != 0) {
            Reader inputStreamReader = new InputStreamReader(openFileInput, Charset.forName("UTF-8"));
            BufferedReader bufferedReader2;
            try {
                bufferedReader2 = new BufferedReader(inputStreamReader);
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    if (stringBuilder != null) {
                        this.m = new JSONObject(stringBuilder.toString());
                        String string = this.m.getString("data");
                        m(string);
                        this.n = new JSONObject(string);
                        this.o = this.n.getJSONObject("staged");
                        this.q = this.m.getInt("eventCount");
                        this.s = m.a(this.m.getString("lastAccess"));
                        this.r = m.a(this.m.getString("sdkStarted"));
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (RuntimeException e4) {
                    try {
                        bj.c(l, "Cannot restore tracking session", new Object[0]);
                        this.r = new Date();
                        q();
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e6) {
                            }
                        }
                        if (this.m != null) {
                            bj.c(l, "Error restoring tracking data", new Object[0]);
                        } else {
                            bj.a(l, "restored: " + this.m.toString(), new Object[0]);
                        }
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        bufferedReader = bufferedReader2;
                        th = th3;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e7) {
                            }
                        }
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e8) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception e9) {
                    bj.c(l, "Cannot restore tracking session", new Object[0]);
                    this.r = new Date();
                    q();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e10) {
                        }
                    }
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e11) {
                        }
                    }
                    if (this.m != null) {
                        bj.a(l, "restored: " + this.m.toString(), new Object[0]);
                    } else {
                        bj.c(l, "Error restoring tracking data", new Object[0]);
                    }
                }
            } catch (RuntimeException e12) {
                bufferedReader2 = null;
                bj.c(l, "Cannot restore tracking session", new Object[0]);
                this.r = new Date();
                q();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (this.m != null) {
                    bj.c(l, "Error restoring tracking data", new Object[0]);
                } else {
                    bj.a(l, "restored: " + this.m.toString(), new Object[0]);
                }
            } catch (Exception e13) {
                bufferedReader2 = null;
                bj.c(l, "Cannot restore tracking session", new Object[0]);
                this.r = new Date();
                q();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (this.m != null) {
                    bj.a(l, "restored: " + this.m.toString(), new Object[0]);
                } else {
                    bj.c(l, "Error restoring tracking data", new Object[0]);
                }
            } catch (Throwable th4) {
                th = th4;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                throw th;
            }
        }
        this.r = new Date();
        q();
        if (this.m != null) {
            bj.a(l, "restored: " + this.m.toString(), new Object[0]);
        } else {
            bj.c(l, "Error restoring tracking data", new Object[0]);
        }
    }

    String m(boolean z) throws JSONException {
        this.m.remove("lastAccess");
        this.m.remove("eventCount");
        this.m.remove("sdkStarted");
        this.m.remove("lastDisplaySessionName");
        Iterator keys = this.o.keys();
        while (keys.hasNext()) {
            JSONObject optJSONObject = this.o.optJSONObject((String) keys.next());
            if (optJSONObject != null) {
                if (optJSONObject.has("last")) {
                    optJSONObject.remove("last");
                }
                if (optJSONObject.has("lastDisplayTime")) {
                    optJSONObject.remove("lastDisplayTime");
                }
            }
        }
        if (this.o.has("sdk-usage")) {
            optJSONObject = this.o.getJSONObject("sdk-usage");
            if (optJSONObject.length() == 0) {
                this.o.remove("sdk-usage");
            } else {
                keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    if (((String) keys.next()).contains("Last")) {
                        keys.remove();
                    }
                }
            }
        }
        this.o.put("endTime", m.a(this.s));
        this.m.put("data", this.o);
        String jSONObject = z ? this.m.toString(4) : "Analytics flush called in non-debug mode";
        o();
        ez.b(this.F);
        synchronized (this.E) {
            com.here.b.a.a.a(false);
        }
        com.here.b.c.d dVar = new com.here.b.c.d();
        dVar.a("SDK-DATA", new com.here.b.c.d(this.m));
        dVar.a("hereKind", "SDKUsage");
        com.here.b.a.a.a("sdk-data-01", dVar);
        com.here.b.a.a.d();
        ez.a(this.F, 150000);
        this.o = null;
        q();
        r();
        return jSONObject;
    }

    private void a(JSONObject jSONObject, String str) throws JSONException {
        a(jSONObject, str, 1);
    }

    private void a(JSONObject jSONObject, String str, int i) throws JSONException {
        if (jSONObject.has(str)) {
            jSONObject.put(str, jSONObject.getInt(str) + i);
        } else {
            jSONObject.put(str, i);
        }
        n(str);
    }

    private void a(JSONObject jSONObject, String str, long j) throws JSONException {
        if (jSONObject.has(str)) {
            jSONObject.put(str, jSONObject.getLong(str) + j);
        } else {
            jSONObject.put(str, j);
        }
        n(str);
    }

    private void a(JSONObject jSONObject, String str, double d) throws JSONException {
        if (jSONObject.has(str)) {
            jSONObject.put(str, jSONObject.getDouble(str) + d);
        } else {
            jSONObject.put(str, d);
        }
        n(str);
    }
}
