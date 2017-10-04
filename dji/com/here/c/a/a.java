package com.here.c.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.os.Looper;
import com.here.posclient.PositioningFeature;
import com.here.posclient.Status;
import com.here.posclient.analytics.PositioningCounters;
import com.here.posclient.analytics.RadiomapCounters;
import com.here.posclient.analytics.Tracker;
import com.here.services.Api;
import com.here.services.HereLocationApiClient;
import com.here.services.HereLocationApiClient.Builder;
import com.here.services.HereLocationApiClient.ConnectionCallbacks;
import com.here.services.HereLocationApiClient.Options;
import com.here.services.HereLocationApiClient.Reason;
import com.here.services.HereLocationApiClient.SdkOptions;
import com.here.services.location.LocationListener;
import com.here.services.location.LocationServices;
import com.here.services.location.hybrid.HybridLocationApi;
import com.here.services.playback.MeasurementPlaybackServices;
import com.here.services.positioning.analytics.UsageTrackingApi.Listener;
import com.here.services.positioning.analytics.UsageTrackingServices;
import com.here.services.radiomap.RadioMapServices;
import com.here.services.test.LocationTestServices;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ApplicationContext$c;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.bj;
import com.nokia.maps.l;
import com.nokia.maps.p;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

@Internal
public class a implements ConnectionCallbacks, com.nokia.maps.MapsEngine.f {
    private static final String a = a.class.getSimpleName();
    private static volatile a b;
    private final Context c;
    private HereLocationApiClient d;
    private f e = f.NotConnected;
    private final List<WeakReference<d>> f = new ArrayList();
    private Listener g;

    private interface a {
        void a(d dVar);
    }

    static class b {
        private final long a;

        b(long j) {
            this.a = j;
        }

        long a(int i) {
            if (new c().a(i)) {
                return this.a;
            }
            return 0;
        }
    }

    public static class c implements ApplicationContext$c {
        private boolean a;

        public boolean a(int i) {
            ApplicationContext.b().check(i, this);
            return this.a;
        }

        public void b() {
            this.a = true;
        }

        public void a() {
            this.a = false;
        }
    }

    public interface d {
        void a();

        void a(Reason reason);

        void b();
    }

    public interface e {
        String a();

        boolean b();
    }

    enum f {
        NotConnected,
        Connecting,
        Connected
    }

    private a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException();
        }
        this.c = context;
    }

    public static a a(Context context) {
        if (!d()) {
            return null;
        }
        if (b == null) {
            synchronized ("74d726afe570abe05ff57d42b4cf8ab6") {
                if (b == null && o()) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    public void a(e eVar) {
        if (!i()) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            Builder builder = new Builder(this.c, "74d726afe570abe05ff57d42b4cf8ab6", this);
            builder.setSdkOptions(new SdkOptions(n()));
            builder.addApi(LocationServices.API);
            builder.addApi(LocationTestServices.API);
            builder.addApi(MeasurementPlaybackServices.API);
            builder.addApi(UsageTrackingServices.API);
            if (m()) {
                builder.addApi(RadioMapServices.API);
            }
            if (l() && !b(this.c)) {
                String a = eVar.a();
                if (a != null) {
                    bj.a(a, "open: using override customerId: %s", new Object[]{a});
                } else {
                    a = ApplicationContext.b().getAppId();
                    bj.a(a, "open: using app_id as customerId: %s", new Object[]{a});
                }
                builder.setCustomerId(a);
            }
            if (eVar.b()) {
                bj.a(a, "open: forcedOffline: %s", new Object[]{Boolean.valueOf(eVar.b())});
                builder.setOptions(new Options().setOfflineMode(true));
            }
            this.d = builder.build();
            this.e = f.Connecting;
            this.d.connect();
        }
    }

    @Internal
    public boolean a() {
        return c() || b();
    }

    @Internal
    public boolean b() {
        return this.e == f.Connecting;
    }

    @Internal
    public boolean c() {
        return this.e == f.Connected;
    }

    @Internal
    public synchronized void a(d dVar) {
        if (dVar != null) {
            b(dVar);
            this.f.add(new WeakReference(dVar));
        }
    }

    @Internal
    public synchronized boolean b(d dVar) {
        boolean z;
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            d dVar2 = (d) ((WeakReference) it.next()).get();
            if (dVar2 == null) {
                it.remove();
            } else if (dVar2.equals(dVar)) {
                it.remove();
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    @Internal
    public com.here.c.a.a.a c(d dVar) {
        if (g()) {
            a(dVar);
            return new com.here.c.a.a.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean a(LocationListener locationListener) {
                    if (this.a.g()) {
                        LocationServices.HybridLocationProvider.stopLocationUpdates(this.a.d, locationListener);
                        return true;
                    }
                    bj.b(a.a, "hybrid location API not available", new Object[0]);
                    return false;
                }

                public boolean a(HybridLocationApi.Options options, LocationListener locationListener) {
                    if (this.a.g()) {
                        return LocationServices.HybridLocationProvider.startLocationUpdates(this.a.d, options, locationListener);
                    }
                    bj.b(a.a, "hybrid location API not available", new Object[0]);
                    return false;
                }

                public Location a() {
                    if (this.a.g()) {
                        return LocationServices.HybridLocationProvider.getLastLocation(this.a.d);
                    }
                    bj.b(a.a, "hybrid location API not available", new Object[0]);
                    return null;
                }
            };
        }
        bj.b(a, "hybrid location API not intialized or available", new Object[0]);
        return null;
    }

    public void onConnected() {
        p();
        a(f.Connected, new a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(d dVar) {
                dVar.a();
            }
        });
    }

    public void onConnectionFailed(final Reason reason) {
        bj.b(a, "reason: %s" + reason, new Object[0]);
        this.d = null;
        a(f.NotConnected, new a(this) {
            final /* synthetic */ a b;

            public void a(d dVar) {
                dVar.a(reason);
            }
        });
    }

    public void onDisconnected() {
        this.d = null;
        a(f.NotConnected, new a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(d dVar) {
                dVar.b();
            }
        });
    }

    public void onInitializationFailed(Api<? extends Api.Options> api) {
        bj.b(a, "api: %s" + api, new Object[0]);
    }

    private boolean g() {
        return j() && LocationServices.HybridLocationProvider != null;
    }

    private boolean h() {
        return j() && UsageTrackingServices.UsageTrackingProvider != null;
    }

    private boolean i() {
        return this.d != null && this.d.isConnected();
    }

    private boolean j() {
        return i() || b();
    }

    public static boolean d() {
        return n() != PositioningFeature.None.value;
    }

    public static boolean e() {
        return l() || k();
    }

    private static boolean k() {
        return new c().a(38);
    }

    private static boolean l() {
        return new c().a(39);
    }

    private static boolean m() {
        return new c().a(37);
    }

    private static long n() {
        return ((((PositioningFeature.None.value | new b(PositioningFeature.Online.value + PositioningFeature.Cache.value).a(35)) | new b((PositioningFeature.Offline.value + PositioningFeature.RadioMapDownload.value) + PositioningFeature.RadioMapDownloadApi.value).a(37)) | new b((PositioningFeature.HighAccuracy.value + PositioningFeature.RadioMapDownload.value) + PositioningFeature.RadioMapDownloadApi.value).a(38)) | new b((PositioningFeature.HighAccuracyCustom.value + PositioningFeature.RadioMapDownload.value) + PositioningFeature.RadioMapDownloadApi.value).a(39)) | new b(PositioningFeature.Collector.value).a(40);
    }

    private void a(f fVar, a aVar) {
        if (this.e != fVar) {
            this.e = fVar;
            if (aVar != null) {
                a(aVar);
            }
        }
    }

    private void a(a aVar) {
        synchronized (this) {
            List<WeakReference> arrayList = new ArrayList(this.f);
        }
        Collection arrayList2 = new ArrayList();
        for (WeakReference weakReference : arrayList) {
            d dVar = (d) weakReference.get();
            if (dVar == null) {
                arrayList2.add(weakReference);
            } else {
                aVar.a(dVar);
            }
        }
        synchronized (this) {
            this.f.removeAll(arrayList2);
        }
    }

    private static boolean o() {
        try {
            System.loadLibrary("posclient");
            return true;
        } catch (Exception e) {
            bj.c(a, "exception", new Object[]{e});
            return false;
        }
    }

    private boolean b(boolean z) {
        bj.a(a, "offline: %s", new Object[]{Boolean.valueOf(z)});
        if (i()) {
            return this.d.changeOptions(new Options().setOfflineMode(z));
        }
        return false;
    }

    public void a(boolean z) {
        boolean z2;
        if (z) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (!b(z2)) {
            bj.b(a, com.alipay.sdk.j.f.b, new Object[0]);
        }
    }

    private boolean b(Context context) {
        return k() && Boolean.parseBoolean(a(context, "com.here.location.force_public_rm"));
    }

    private String a(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get(str);
                if (obj instanceof String) {
                    return (String) obj;
                }
                if (obj instanceof Boolean) {
                    return obj.toString();
                }
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void p() {
        if (h()) {
            final p a = l.a();
            if (a == null) {
                bj.c(a, "analytics tracker is null", new Object[0]);
                return;
            }
            EnumSet supportedTrackers = UsageTrackingServices.UsageTrackingProvider.getSupportedTrackers(this.d);
            if (supportedTrackers.isEmpty()) {
                bj.c(a, "no trackers supported", new Object[0]);
                return;
            }
            if (UsageTrackingServices.UsageTrackingProvider.subscribe(this.d, new Listener(this) {
                final /* synthetic */ a b;

                public void onRadiomapCountersUpdated(RadiomapCounters radiomapCounters) {
                    bj.a(a.a, "%s", new Object[]{radiomapCounters.toString()});
                    a.a(radiomapCounters);
                    Listener c = this.b.g;
                    if (c != null) {
                        c.onRadiomapCountersUpdated(radiomapCounters);
                    }
                }

                public void onPositioningCountersUpdated(PositioningCounters positioningCounters) {
                    bj.a(a.a, "%s", new Object[]{positioningCounters.toString()});
                    a.a(positioningCounters);
                    Listener c = this.b.g;
                    if (c != null) {
                        c.onPositioningCountersUpdated(positioningCounters);
                    }
                }
            }, (Tracker[]) supportedTrackers.toArray(new Tracker[0])) != Status.Ok) {
                bj.c(a, "subscribe failed: %s", new Object[]{UsageTrackingServices.UsageTrackingProvider.subscribe(this.d, /* anonymous class already generated */, (Tracker[]) supportedTrackers.toArray(new Tracker[0])).toString()});
                return;
            }
            bj.a(a, "HERE positioning usage tracking started", new Object[0]);
            return;
        }
        bj.c(a, "API not available", new Object[0]);
    }
}
